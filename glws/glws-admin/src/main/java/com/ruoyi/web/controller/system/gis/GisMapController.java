package com.ruoyi.web.controller.system.gis;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.gis.GisMap;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.gis.IGisMapService;
import com.ruoyi.zaihai.ReserveManagement.domain.Reserve;
import com.ruoyi.zaihai.ReserveManagement.service.IReserveService;
import com.ruoyi.zaihai.caiji.domain.CGroundSurvey;
import com.ruoyi.zaihai.caiji.service.ICGroundSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private IReserveService reserveService;

    @Autowired
    private ICGroundSurveyService cGroundSurveyService;

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
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            if (StringUtils.isNotEmpty(deptName)) {
                model.put("lngLat", EntCoordSyncJob.getCoordinate(deptName));
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


    /**
     * @Describe 地面调查添加绘图
     * @Params []
     * @Return java.util.List<com.ruoyi.system.domain.SysCity>
     * @DATE 2020/7/16
     * @Author feiyanxu
     */
    @RequestMapping("/getListCaoYuanGisMap/{addrArea}/{addrCity}")
    public String getListCaoYuanGisMap(@PathVariable("addrArea") String addrArea, @PathVariable("addrCity") String addrCity, ModelMap model) {

        if (addrArea.equals("") || addrCity.equals("")) {
            return prefix + "/GisMap";

        } else {
            String addrCityLabel = iSysDictDataService.selectDictValueToLabel(addrCity);
            if (addrCityLabel == null) {
                addrCityLabel = iSysDictDataService.selectDictValueToLabels(addrCity);
            }
            String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(addrArea);
            if (addrAreaLabel == null) {
                addrAreaLabel = iSysDictDataService.selectDictValueToLabels(addrArea);

            }
            String address = addrCityLabel + "" + addrAreaLabel;

            model.put("lngLat", EntCoordSyncJob.getCoordinate(address));
            model.put("gid", null);
            return prefix + "/GisMap";

        }
    }


    /*查看绘图*/
    @RequestMapping("/getckListCaoYuanGisMap/{mapId}")
    public String getckListCaoYuanGisMap(@PathVariable("mapId") String mapId, ModelMap model) {


            //根据上报 的map_Id 获取信息 0为map_id
            GisMap gisMapBy = iGisMapService.getGisMapById(Integer.parseInt(mapId));
            model.put("lngLat", gisMapBy.getCentre());
            model.put("mapId", mapId);
            return prefix + "/GisMapView";

    }


    /*根据地面调查id查看绘图*/
    @RequestMapping("/getbjListCaoYuanGisMap/{groundId}")
    public String getbjListCaoYuanGisMap(@PathVariable("groundId") String groundId, ModelMap model) {

        //根据地面调查id获取mapid
        CGroundSurvey cGroundSurvey = cGroundSurveyService.selectCGroundSurveyById(Long.parseLong(groundId));
        if(cGroundSurvey.getMapId()==null){

        }else{
            Long mapId = cGroundSurvey.getMapId();
            //根据上报 的map_Id 获取信息 0为map_id
            GisMap gisMapBy = iGisMapService.getGisMapById(mapId.intValue());
            if(gisMapBy==null){
            }else{
                model.put("lngLat", gisMapBy.getCentre());
            }
            model.put("mapId", mapId);
        }
        return prefix + "/GisMapView";
    }

    @RequestMapping("deleteMap")
    @ResponseBody
    public int deleteMap(int id){
        return iGisMapService.deleteGisMap(id);
    }


    /**
     * @Describe 储备库绘图
     * @Params []
     * @Return java.util.List<com.ruoyi.system.domain.SysCity>
     * @DATE 2020/7/16
     * @Author feiyanxu
     */
    @GetMapping("/getListCaoYuanGisMap2")
    @ResponseBody
    public Reserve getListCaoYuanGisMap2(String addrArea, ModelMap model) {
        System.out.println("----" + addrArea);
        Reserve reserve = new Reserve();
        reserve.setAddress(addrArea);
        List<Reserve> list = reserveService.selectReserveList(reserve);
        if(list.size()>0){
            reserve.setStatus("404");
            return reserve;
        }else{
            if (StringUtils.isNotEmpty(addrArea)) {
                String address = iSysDictDataService.selectDictValueToLabel(addrArea);
                //根据经纬度转化坐标
                model.put("lngLat", EntCoordSyncJob.getCoordinate(address));
                reserve.setLatitude(EntCoordSyncJob.getCoordinate(address));
                return reserve;
            } else {
                return reserve;

            }
        }


    }


    /*地面调查修改绘图*/
    @RequestMapping("/getUpdateListCaoYuanGisMap/{mapId}")
    public String getUpdateListCaoYuanGisMap(@PathVariable("mapId") String mapId, ModelMap model) {


        //根据上报 的map_Id 获取信息 0为map_id
        GisMap gisMapBy = iGisMapService.getGisMapById(Integer.parseInt(mapId));
        model.put("lngLat", gisMapBy.getCentre());
        model.put("mapId", mapId);
        return prefix + "/GisMap";

    }
}