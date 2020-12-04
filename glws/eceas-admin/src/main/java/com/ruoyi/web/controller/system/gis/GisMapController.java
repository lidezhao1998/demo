package com.ruoyi.web.controller.system.gis;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.gis.GisMap;
import com.ruoyi.system.service.gis.IGisMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("system/gisMap")
public class GisMapController extends BaseController {
    private String prefix = "system/gis";
    @Autowired
    private IGisMapService iGisMapService;


    @RequestMapping("/addGisMap")
    public String addGisMap(GisMap gisMap, ModelMap model) {
        gisMap.setGeo(gisMap.getGeo().replace(",", " "));
        String[] split = gisMap.getCentre().split(",");
        if (gisMap.getType().equals("2")) {
            gisMap.setGeo("POLYGON((" + gisMap.getGeo().replace(";", ",").substring(0, gisMap.getGeo().length() - 1) + "))");
            gisMap.setArea(iGisMapService.getArea(gisMap.getGeo()));
        } else {
            gisMap.setGeo("Point(" + gisMap.getGeo() + ")");
            gisMap.setArea(String.valueOf(0));
        }
        gisMap.setAddress(EntCoordSyncJob.reverseGeocode(split[0], split[1]));
        if (gisMap.getId() == 0) {
            model.put("gisMap", gisMap);
        } else {
            GisMap gisMapById = iGisMapService.getGisMapById(gisMap.getId());
            gisMapById.setGeo(gisMap.getGeo());
            model.put("gisMap", gisMapById);
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
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            if (StringUtils.isNotEmpty(deptName)) {
                model.put("lngLat", EntCoordSyncJob.getCoordinate(deptName));
            }
            model.put("gid", null);
        } else {
            //修改
            //根据上报 的map_Id 获取信息 0为map_id
            GisMap gisMapBy = iGisMapService.getGisMapById(mapId);
            model.put("lngLat", gisMapBy.getCentre());
            model.put("gid", gisMapBy.getGid());
        }
        model.put("layes", "sinosoft-gis");
        return prefix + "/GisMap";
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
            if (count > 0) {
                return AjaxResult.success(iGisMapService.getGisMapMaxId());
            }
        }
        return AjaxResult.success(count);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult deleteGisMap(String gisId) {
        int count = iGisMapService.deleteGisMap(Integer.valueOf(gisId.substring(0, 1)));
        return AjaxResult.success(count);
    }

}














