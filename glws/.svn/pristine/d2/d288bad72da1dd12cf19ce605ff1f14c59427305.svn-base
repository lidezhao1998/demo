package com.ruoyi.web.controller.system.gis;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.gis.GisFeatures;
import com.ruoyi.system.domain.gis.SysCity;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskReportService;
import com.ruoyi.system.service.gis.IGisMapService;
import com.ruoyi.system.service.gis.IGisService;
import com.ruoyi.system.service.gis.ISysCityService;
import com.ruoyi.system.service.impl.SysDeptServiceImpl;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ITTaskReportService tTaskReportService;
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private SysDeptServiceImpl sysDeptService;
    @Autowired
    private ISysDictDataService iSysDictDataService;


    @GetMapping("/gis1")
    public String gis1(ModelMap modelMap) {
        String address = ShiroUtils.getSysUser().getAddress();
        if (StringUtils.isNotEmpty(address)) {
            modelMap.put("lngLat", EntCoordSyncJob.getCoordinate(address));
        }
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        Long deptid = ShiroUtils.getSysUser().getDeptId();//部门id
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String roleNames="";
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            roleNames += sysRole.getRoleName()+",";
        }

        if(!roleNames.contains("超级管理员")){
            if (roleNames.contains("国家级")) {
                modelMap.put("user","国家级");
            } else if (roleNames.contains("省级")) {
                String provinceCode = iSysDictDataService.selectDictCodeByDictLabel(deptName);
                modelMap.put("user","省级");
                modelMap.put("provinceName",deptName);
                modelMap.put("provinceCode",provinceCode);
            } else if(roleNames.contains("市级")) {
                SysDictData sysDictData = iSysDictDataService.selectProvinceByCity(deptName);
                String provinceName = sysDictData.getDictLabel();
                String provinceCode = sysDictData.getDictValue();
                String cityCode = iSysDictDataService.selectDictCodeByDictLabel(deptName);;
                modelMap.put("user","市级");
                modelMap.put("provinceName",provinceName);
                modelMap.put("provinceCode",provinceCode);
                modelMap.put("cityName",deptName);
                modelMap.put("cityCode",cityCode);
            }
        }
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
        Long deptId = sysUserService.selectUserById(ShiroUtils.getUserId()).getDeptId();
        List gisMapList = sysDeptService.deptIdByAncestors(deptId);
        gisMapList.add(deptId);
        List list = sysUserService.selectUserIdByDeptId(gisMapList);
        hashMap.put("featuresData",StringUtils.join(igisService.getShpIds(shpIds).stream().map(ids ->ids.getId()).collect(Collectors.toList()).toArray(), ","));
        hashMap.put("userIds", StringUtils.join(list, ","));
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
    public String particulars() {
        return "";
    }

    /**
     * 查看退牧还草工程进度
     */
    @GetMapping("/detail/{reportId}")
    public String detail(@PathVariable("reportId") Long reportId, ModelMap mmap) {
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        mmap.put("tTaskReport", tTaskReport);
        return prefix + "/reportDetail";
    }
}