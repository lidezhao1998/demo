package com.ruoyi.web.controller.system.gis;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.JavaRunPython;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.gis.GisTab;
import com.ruoyi.system.service.gis.IGisTabService;
import com.ruoyi.web.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("system/gisTab")
public class GisTabController extends BaseController {
    private String prefix = "system/gis";
    @Autowired
    private IGisTabService iGisTabService;
    @Autowired
    private RedisProperties redisProperties;


    @RequestMapping("/addGisTab")
    public String addGisTab(GisTab gisTab, ModelMap model) {
        gisTab.setGeo(gisTab.getGeo().replace(",", " "));
        String[] split = gisTab.getCentre().split(",");
        if (gisTab.getType().equals("2")) {
            gisTab.setGeo("POLYGON((" + gisTab.getGeo().replace(";", ",").substring(0, gisTab.getGeo().length() - 1) + "))");
        } else {
            gisTab.setGeo("Point(" + gisTab.getGeo() + ")");
        }
        String address = EntCoordSyncJob.reverseGeocode(split[0], split[1]);
        gisTab.setAddress(address);
        gisTab.setName(address);
        if (gisTab.getId() == 0) {
            model.put("gisTab", gisTab);
        } else {
            GisTab gisTabById = iGisTabService.getGisTabById(gisTab.getId());
            gisTabById.setGeo(gisTab.getGeo());
            model.put("gisTab", gisTabById);
        }
        return prefix + "/addGisTab";
    }

    @RequestMapping("/getListById")
    @ResponseBody
    public List<GisTab> getListByIds(String shpIds) {
        return iGisTabService.getListById(shpIds);
    }

    /**
     * 获取标注可见信息
     * @param
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public TableDataInfo getListGisTab(GisTab gisTab) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        List<GisTab> listGisTab = iGisTabService.getListGisTab(ShiroUtils.getUserId(), gisTab);
        List<GisTab> gisTabs = iGisTabService.listLimit(pageDomain.getPageNum(), pageDomain.getPageSize(), listGisTab);
        return getDataTable(gisTabs,listGisTab);
    }
    @RequestMapping("/getTabById")
    @ResponseBody
    public GisTab getTabById(int id) {
        return iGisTabService.getTabById(id);
    }
    /**
     * 根据 地图id   添加/修改跳转
     *
     * @param mapId
     * @param model
     * @return
     */
    @RequestMapping("/getGisTab/{id}")
    public String getListCaoYuanGisTab(@PathVariable("id") int mapId, ModelMap model) {
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
            GisTab gisTabBy = iGisTabService.getGisTabById(mapId);
            model.put("lngLat", gisTabBy.getCentre());
            model.put("gid", gisTabBy.getGid());
        }
        model.put("layes", "sinosoft-gis");
        return prefix + "/addGisTab";
    }

    @RequestMapping("/insertOrUpdate")
    @ResponseBody
    public AjaxResult insertGisTab(GisTab gisTab, ModelMap modelTab) {
        gisTab.setCreateBy(String.valueOf(ShiroUtils.getUserId()));
        int count;
        if (gisTab.getId() > 0) {
            count = iGisTabService.updataGisTab(gisTab);

        } else {
            count = iGisTabService.insertGisTab(gisTab);
            if (count > 0) {
                return AjaxResult.success(iGisTabService.getGisTabMaxId());
            }
        }
        return AjaxResult.success(count);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult deleteGisTab(String gisId) {
        int count = iGisTabService.deleteGisTab(Integer.valueOf(gisId.substring(0, 1)));
        return AjaxResult.success(count);
    }

    @RequestMapping("getGrassLandArea")
    @ResponseBody
    public Map getGrassLandArea(String geo) throws IOException, InterruptedException {
        JavaRunPython.areaPython(geo.substring(0, geo.length() - 1), redisProperties.getHost());
        Map map = iGisTabService.getGrassLandArea(geo.substring(0, geo.length() - 1), redisProperties.getHost());
        return map;
    }

}














