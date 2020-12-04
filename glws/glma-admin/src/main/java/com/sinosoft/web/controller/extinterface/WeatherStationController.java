package com.sinosoft.web.controller.extinterface;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.sinosoft.extinterface.domain.WeatherStation;
import com.sinosoft.extinterface.service.WeatherStationService;
import com.sinosoft.web.config.WeatherJsonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sunlei
 * @description 终端气象站数据传输接口
 * @date 2020/09/07/15:13
 */
@Controller
@RequestMapping("/extinterface/weatherStation")
public class WeatherStationController extends BaseController {
    @Autowired
    WeatherStationService weatherStationService;
    private String prefix = "extinterface/weatherStation";

    @GetMapping
    public String MobileDeviceData() {
        return prefix + "/weatherStationList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo landSurveyList(WeatherStation weatherStation) {
        startPage();
        List<WeatherStation> spatialPlanningList = weatherStationService.selectWeatherStationList(weatherStation);
        return getDataTable(spatialPlanningList);
    }

    /**
     * 功能描述  导出
     *
     * @param
     * @return [user]
     * @author sunlei
     * @date 2020/9/7
     */
    @Log(title = "气象数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("integration:weatherStation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeatherStation weatherStation) {
        List<WeatherStation> list = weatherStationService.selectWeatherStationList(weatherStation);
        ExcelUtil<WeatherStation> util = new ExcelUtil<WeatherStation>(WeatherStation.class);
        return util.exportExcel(list, "气象数据");
    }

    /**
     * 功能描述  导入
     *
     * @param
     * @return [file, updateSupport]
     * @author sunlei
     * @date 2020/9/7
     */
    @Log(title = "气象数据", businessType = BusinessType.IMPORT)
    @RequiresPermissions("integration:weatherStation:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<WeatherStation> util = new ExcelUtil<WeatherStation>(WeatherStation.class);
        List<WeatherStation> weatherStationList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = weatherStationService.importWeatherStation(weatherStationList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 功能描述 模板Excel
     *
     * @param
     * @return []
     * @author sunlei
     * @date 2020/9/7
     */
    @RequiresPermissions("integration:weatherStation:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<WeatherStation> util = new ExcelUtil<WeatherStation>(WeatherStation.class);
        return util.importTemplateExcel("气象数据模板");
    }



    @RequestMapping("/getAddress")
    @ResponseBody
    public String getAddress(String lonLat){
        String[] split = lonLat.split(",");
        String address = EntCoordSyncJob.addGeocode(split[0], split[1]);
//        if(StringUtils.isNotEmpty(address)){
//            address=EntCoordSyncJob.provinces(address);
//        }
        return address;

    }

    /**
     * 功能描述 模
     *
     * @param
     * @return []
     * @author sunlei
     * @date 2020/9/7
     */
    @PostMapping("/getTodayWeather")
    @ResponseBody
    public TableDataInfo getTodayWeather(String cityName) throws IOException {
        ArrayList<Object> objects = new ArrayList<>();
        if (cityName==null ||"".equals(cityName)){
            return getDataTable(objects);
        }
        String[] split = cityName.split(",");
        for (String city : split) {
            String cityCode= weatherStationService.getCityCode(city);
            if (cityCode!=null){
                Map<String, Object> todayWeather = WeatherJsonUtil.getTodayWeather(cityCode);
                objects.add(todayWeather);
            }

        }
        return getDataTable(objects);
    }

    public static void main(String[] args) {
    }


}

