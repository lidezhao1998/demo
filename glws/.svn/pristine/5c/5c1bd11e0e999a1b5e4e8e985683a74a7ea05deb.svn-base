package com.sinosoft.web.controller.extinterface;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.gis.SysCity;
import com.sinosoft.extinterface.domain.APointInterface;
import com.sinosoft.extinterface.domain.LandSurvey;
import com.sinosoft.extinterface.domain.MobileDevice;
import com.sinosoft.extinterface.service.MobileDeviceDataServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author sunlei
 * @description 移动设备数据5G传输采集接口
 * @date 2020/08/19/9:51
 */
@Controller
@RequestMapping("extinterface/mobileDeviceData")
public class MobileDeviceDataController extends BaseController {
    @Autowired
    MobileDeviceDataServer mobileDeviceDataServer;
    private String prefix = "extinterface/mobileDeviceData";

    @GetMapping
    public String MobileDeviceData() {
        return prefix + "/mobileDeviceDataList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo landSurveyList(MobileDevice mobileDevice) {
        startPage();
        List<MobileDevice> mobileDeviceList = mobileDeviceDataServer.selectmobileDeviceList(mobileDevice);
        return getDataTable(mobileDeviceList);
    }

    @PostMapping("/allList")
    @ResponseBody
    public List<MobileDevice> landSurveyAllList(MobileDevice mobileDevice) {

        List<MobileDevice> mobileDeviceAllList = mobileDeviceDataServer.selectmobileDeviceList(mobileDevice);
        return mobileDeviceAllList;
    }

    /*
     *功能描述 跳转到添加页面
     * @author sunlei
     * @date 2020/11/12
     * @param
     * @return []
     */
    @GetMapping("/add")
    public String add(String lon, String lat, ModelMap mmap) {
        String region = EntCoordSyncJob.reverseGeocode(lon, lat);
        mmap.put("lon", lon);
        mmap.put("lat", lat);
        mmap.put("region", region);
        return prefix + "/add";
    }
 /*
     *功能描述 跳转到添加页面
     * @author sunlei
     * @date 2020/11/12
     * @param
     * @return []
     */
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, ModelMap mmap) {
        MobileDevice mobileDevice=   mobileDeviceDataServer.getMobileDeviceById(id);
        mmap.put("mobileDevice",mobileDevice);
        return prefix + "/details";
    }

    /*
     *功能描述  保存
     * @author sunlei
     * @date 2020/11/12
     * @param
     * @return [aPointInterface]
     */
    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult addSave(MobileDevice mobileDevice) {
        return toAjax(mobileDeviceDataServer.insertmobileDevice(mobileDevice));
    }

    @ResponseBody
    @GetMapping("/getDistrictSheng")
    public List<SysCity> getDistrictSheng() {
        return mobileDeviceDataServer.getDistrictSheng();
    }

    /**
     * @Describe 根据省级Id获取旗下的所有市级, 以及省级数据
     * @Params [id]
     * @Return java.util.HashMap<java.lang.String   ,       java.lang.Object>
     * @DATE 2020/7/16
     * @Author LiRenDong
     */
    @ResponseBody
    @GetMapping("/getDistrictShi/{id}")
    public HashMap<String, Object> getDistrictShi(@PathVariable("id") Long id) {
        HashMap<String, Object> districtShi = mobileDeviceDataServer.getDistrictShi(id);
//        districtShi.put("shpUrl", geoServerProperties.getUrlYml());
        return districtShi;
    }


    /**
     * @Describe 根据市级Id获取对应区数据
     * @Params [id]
     * @Return java.util.HashMap<java.lang.String   ,       java.lang.Object>
     * @DATE 2020/7/16
     * @Author LiRenDong
     */
    @ResponseBody
    @GetMapping("/getDistrictQu/{id}")
    public HashMap<String, Object> getDistrictQu(@PathVariable("id") Long id) {
        HashMap<String, Object> districtQu = mobileDeviceDataServer.getDistrictQu(id);
//        districtQu.put("shpUrl", geoServerProperties.getUrlYml());
        return districtQu;
    }

    /**
     * @Describe 根据id获取对视屏
     * @Params [id]
     * @Return java.util.HashMap<java.lang.String   ,       java.lang.Object>
     * @DATE 2020/7/16
     * @Author LiRenDong
     */
    @ResponseBody
    @GetMapping("/getDistrictById/{id}")
    public HashMap<String, Object> getDistrictById(@PathVariable("id") String id) {
        HashMap<String, Object> hashMap = mobileDeviceDataServer.getVideo(id);
        return hashMap;
    }
}
