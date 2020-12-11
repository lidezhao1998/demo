package com.ruoyi.web.controller.system.gis;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.gis.GisMap;
import com.ruoyi.system.service.gis.IGisMapService;
import com.ruoyi.system.service.impl.TTaskReportServiceImpl;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("system/gisMap")
public class GisMapController extends BaseController {
    private String prefix = "system/gis";
    @Autowired
    private IGisMapService iGisMapService;
    @Autowired
    private TTaskReportServiceImpl tTaskReportService;

    @RequestMapping("getGisMapById")
    public String getGisMapById(int id,ModelMap modelMap){
        modelMap.addAttribute("gisMap",iGisMapService.getGisMapById(id));
        return prefix + "/getGisMap";
    }

    @RequestMapping("/addGisMap")
    public String addGisMap(GisMap gisMap, ModelMap model) {
        if(StringUtils.isNotEmpty(gisMap.getGeo())&&!"null".equals(gisMap.getGeo())){
            gisMap.setGeo(gisMap.getGeo().replace(",", " "));
            String[] split = gisMap.getCentre().split(",");
            if (gisMap.getType().equals("2")) {
                gisMap.setGeo("POLYGON((" + gisMap.getGeo().replace(";", ",").substring(0, gisMap.getGeo().length() - 1) + "))");
            } else {
                gisMap.setGeo("Point(" + gisMap.getGeo() + ")");
                gisMap.setArea(String.valueOf(0));
            }
            String address = EntCoordSyncJob.reverseGeocode(split[0], split[1]);
            if(StringUtils.isNotEmpty(address)){
                gisMap.setName(address);
                gisMap.setAddress(address);
            }
        }
        if (gisMap.getId() == 0) {
            model.put("gisMap", gisMap);
        } else {
            GisMap gisMapById = iGisMapService.getGisMapById(gisMap.getId());
            if(StringUtils.isEmpty(gisMap.getGeo()) || gisMap.getGeo().equals("null")){
                model.put("gisMap", gisMapById);
            }else{
                gisMapById.setGeo(gisMap.getGeo());
                gisMapById.setAddress(gisMap.getAddress());
                gisMapById.setType(gisMap.getType());
                gisMapById.setName(gisMap.getAddress());
                gisMapById.setCentre(gisMap.getCentre());
                gisMapById.setArea(gisMap.getArea());
                model.put("gisMap", gisMapById);
            }
        }
        return prefix + "/addGisMap";
    }

    @RequestMapping("/getListById")
    @ResponseBody
    public List<GisMap> getListByIds(String shpIds) {
        return iGisMapService.getListById(shpIds);
    }

    //获取专题数据
    @RequestMapping("/getList")
    @ResponseBody
    public List<Ztree> getListGisMap(String year) {
        if (StringUtils.isEmpty(year)) {
            Calendar date = Calendar.getInstance();
            year = String.valueOf(date.get(Calendar.YEAR));
        }
        return iGisMapService.getListGisMap(ShiroUtils.getUserId(), year);
    }

    /**
     * 根据 地图id   添加/修改跳转
     *
     * @param mapId
     * @param model
     * @return
     */
    @RequestMapping("/getGisMap/{id}")
    public String getListCaoYuanGisMap(@PathVariable("id") int mapId, ModelMap model) {
        //添加的
        if (mapId == 0) {
            String address = ShiroUtils.getSysUser().getAddress();
            if (StringUtils.isNotEmpty(address)) {
                model.put("lngLat", EntCoordSyncJob.getCoordinate(address));
            }
            model.put("mapId", null);
        } else {
            //修改
            //根据上报 的map_Id 获取信息 0为map_id
            GisMap gisMapBy = iGisMapService.getGisMapById(mapId);
            if(gisMapBy==null){
                model.put("mapId", null);
                return prefix + "/GisMap";
            }
            model.put("lngLat", gisMapBy.getCentre());
            model.put("mapId", mapId);
        }
        return prefix + "/GisMap";
    }
    @RequestMapping("/queryGis/{id}")
    public String queryGis(@PathVariable("id") int mapId, ModelMap model) {
        //根据上报 的map_Id 获取信息 0为map_id
        GisMap gisMapBy = iGisMapService.getGisMapById(mapId);
        if(gisMapBy==null){
            model.put("mapId", -1);
            String address = ShiroUtils.getSysUser().getAddress();
            if (StringUtils.isNotEmpty(address)) {
                model.put("lngLat", EntCoordSyncJob.getCoordinate(address));
            }
            return prefix + "/queryGisMap";
        }
        model.put("lngLat", gisMapBy.getCentre());
        model.put("mapId", mapId);
        return prefix + "/queryGisMap";
    }

    @RequestMapping("/getTabById")
    @ResponseBody
    public GisMap getTabById(int id) {
        return iGisMapService.getTabById(id);
    }
    @RequestMapping("/insertOrUpdate")
    @ResponseBody
    public AjaxResult insertGisMap(GisMap gisMap) {
        gisMap.setCreateBy(String.valueOf(ShiroUtils.getUserId()));
        int count;
        if (gisMap.getId() > 0) {
            count = iGisMapService.updataGisMap(gisMap);

        } else {
            count = iGisMapService.insertGisMap(gisMap);
        }
        if (count > 0) {
            return AjaxResult.success(iGisMapService.getGisMapMaxId());
        }
        return AjaxResult.success(count);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult deleteGisMap(String gisId) {
        int count = iGisMapService.deleteGisMap(Integer.valueOf(gisId));
        return AjaxResult.success(count);
    }


    @RequestMapping("/reportId/{id}")
    public String reportId(@PathVariable("id") Long reportId,ModelMap mmap){
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String roleNames = "";
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            roleNames += sysRole.getRoleName() + ",";
        }
        List<Long> mapIds = new ArrayList<>();
        if (roleNames.contains("市级")) {
            mapIds.add(reportId);
        }else{
            List<TTaskReport> tTaskReports = tTaskReportService.reportId(reportId);
            if(tTaskReports.size()>0){
                tTaskReports.stream().forEach(report ->{
                    mapIds.add(report.getMapId());
                });
            }
        }
        //根据上报Id返回所有MapId 为 list;
        mmap.addAttribute("mapIds", mapIds);
        mmap.addAttribute("lngLat", EntCoordSyncJob.getCoordinate(ShiroUtils.getSysUser().getAddress()));
        return prefix+"/gisMaps";
    }
}














