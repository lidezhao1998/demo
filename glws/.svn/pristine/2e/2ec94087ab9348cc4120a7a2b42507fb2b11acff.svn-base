package com.sinosoft.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.gis.GisFeatures;
import com.ruoyi.system.domain.gis.SysCity;
import com.ruoyi.system.service.gis.IGisMapService;
import com.ruoyi.system.service.gis.IGisService;
import com.ruoyi.system.service.gis.ISysCityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Describe 初始图层加载
 * @Author LiRenDong
 * @DATE 2020/6/23
 */

@Controller
@RequestMapping("system/gis")
public class GisController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(GisController.class);

    private String prefix = "system/gis";

    @Autowired
    private IGisService igisService;
    @Autowired
    private IGisMapService iGisMapService;
    @Autowired
    private ISysCityService iSysCityService;


    @GetMapping("/gis1")
    public String gis1(ModelMap modelMap) {
        return prefix + "/gis1";
    }

    @GetMapping("gis2")
    public String gis2(ModelMap modelMap) {
        //先加载出来所有的专题图
        modelMap.put("gisMapYearList", iGisMapService.getGisMapYear());
        return prefix + "/gis2";
    }

    @ResponseBody
    @PostMapping("/getSpot")
    public HashMap<String, Object> getSpot(String shpIds) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<GisFeatures> shps = igisService.getShpIds(shpIds);
        hashMap.put("data", shps);
        return hashMap;
    }

    /**
     * @Describe 获取所有的省级
     * @Params []
     * @Return java.util.List<com.ruoyi.system.domain.SysCity>
     * @DATE 2020/7/16
     * @Author LiRenDong
     */
    @ResponseBody
    @GetMapping("/getDistrictSheng")
    public List<SysCity> getDistrictSheng() {
        return iSysCityService.getDistrictSheng();
    }

    @ResponseBody
    @GetMapping("/test")
    public List<SysCity> test() {

        return iSysCityService.getDistrictSheng();
    }

    /**
     * @Describe 根据省级Id获取旗下的所有市级, 以及省级数据
     * @Params [id]
     * @Return java.util.HashMap<java.lang.String, java.lang.Object>
     * @DATE 2020/7/16
     * @Author LiRenDong
     */
    @ResponseBody
    @GetMapping("/getDistrictShi/{id}")
    public HashMap<String, Object> getDistrictShi(@PathVariable("id") Long id) {
        HashMap<String, Object> districtShi = iSysCityService.getDistrictShi(id);
        return districtShi;
    }

    /**
     * @Describe 根据id获取对应数据
     * @Params [id]
     * @Return java.util.HashMap<java.lang.String, java.lang.Object>
     * @DATE 2020/7/16
     * @Author LiRenDong
     */
    @ResponseBody
    @GetMapping("/getDistrictById/{id}")
    public HashMap<String, Object> getDistrictById(@PathVariable("id") Long id) {
        HashMap<String, Object> hashMap = iSysCityService.getCityById(id);
        return hashMap;
    }

    /**
     * @Describe 根据市级Id获取对应区数据
     * @Params [id]
     * @Return java.util.HashMap<java.lang.String, java.lang.Object>
     * @DATE 2020/7/16
     * @Author LiRenDong
     */
    @ResponseBody
    @GetMapping("/getDistrictQu/{id}")
    public HashMap<String, Object> getDistrictQu(@PathVariable("id") Long id) {
        HashMap<String, Object> districtQu = iSysCityService.getDistrictQu(id);
        return districtQu;
    }


    @RequestMapping("/particulars")
    public String particulars(){
return "";
    }
}