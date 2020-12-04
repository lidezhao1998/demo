package com.sinosoft.web.controller.analyze;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.sinosoft.analyze.domain.SysGisMap;
import com.sinosoft.analyze.service.ISysGisMapService;
import com.sinosoft.analyze.service.ISysGisService;
import com.sinosoft.system.domain.TTaskReport;
import com.sinosoft.system.domain.TTaskResolve;
import com.sinosoft.system.service.ITTaskReportService;
import com.sinosoft.system.service.ITTaskResolveService;
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
@RequestMapping("analyze/gis")
public class GisAnalyzeController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(GisAnalyzeController.class);

    private String prefix = "analyze/gis";

    @Autowired
    private ISysGisService igisServiceSys;
    @Autowired
    private ISysGisMapService iSysGisMapService;

    @Autowired
    private ITTaskResolveService tTaskResolveService;

    @Autowired
    private ITTaskReportService tTaskReportService;
    @GetMapping("/gis1")
    public String gis1() {
        return prefix + "/gis1";
    }

    @GetMapping("gis2")
    public String gis2() {
        return prefix + "/gis2";
    }

    @ResponseBody
    @PostMapping("/getSpot")
    public List<HashMap<String, Object>> getSpot(String shpIds) {
        long startTime = System.currentTimeMillis();
        log.info("执行代码块/方法");
        List<HashMap<String, Object>> shps = igisServiceSys.getShpIds(shpIds);
        long endTime = System.currentTimeMillis();
        log.info("程序运行时间： " + (endTime - startTime) + "ms");
        return shps;
    }

    @RequestMapping("/addGisMap")
    public String addGisMap (SysGisMap gisMap, ModelMap model){
        if(gisMap.getId()==0){
            model.put("gisMap",gisMap);
        }else{
            SysGisMap gisMapById = iSysGisMapService.getGisMapById(gisMap.getId());
            gisMapById.setCoordinates(gisMap.getCoordinates());
            model.put("gisMap",gisMapById);
        }

        return prefix+"/addGisMap";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public AjaxResult getListGisMap(){
        List<SysGisMap>  gisMaps = iSysGisMapService.getListGisMap(ShiroUtils.getUserId());
        return  AjaxResult.success(gisMaps);
    }

    @RequestMapping("/getListCaoYuanGisMap/{id}")
    public String getListCaoYuanGisMap(@PathVariable("id") int id, ModelMap model){

        TTaskResolve tTaskResolve = tTaskResolveService.selectTTaskResolveById((long)id);
        if(tTaskResolve==null){
            TTaskReport tTaskReport = tTaskReportService.selectTTaskMapById((long)id);

            String city =tTaskReport.getProvince();
            String area =tTaskReport.getAddress();
            String address=city+""+area;
            if(StringUtils.isNotEmpty(address)){
                model.put("lngLat", EntCoordSyncJob.getCoordinate(address));
            }
        }else{
            String city =tTaskResolve.getProvince();
            String area =tTaskResolve.getAddress();
            String address=city+""+area;
            if(StringUtils.isNotEmpty(address)){
                model.put("lngLat", EntCoordSyncJob.getCoordinate(address));
            }
        }
        model.put("gisMap", iSysGisMapService.getGisMapById(id));
        return prefix+"/caoYuanGisMap";
    }

    @RequestMapping("/insertOrUpdate")
    @ResponseBody
    public AjaxResult insertGisMap(SysGisMap gisMap){
        gisMap.setCreateBy(String.valueOf(ShiroUtils.getUserId()));
        int count;
        if (gisMap.getId()>0){
            count = iSysGisMapService.updataGisMap(gisMap);

        }else{
            count = iSysGisMapService.insertGisMap(gisMap);
        }
        return AjaxResult.success(count);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult deleteGisMap(String gisId){

        int count = iSysGisMapService.deleteGisMap(Integer.valueOf(gisId.substring(0,1)));
        return AjaxResult.success(count);
    }
}