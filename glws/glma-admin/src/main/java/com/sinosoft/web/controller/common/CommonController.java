package com.sinosoft.web.controller.common;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.sinosoft.common.service.CommonService;
import com.sinosoft.extinterface.domain.WeatherStation;
import com.sinosoft.extinterface.mapper.WeatherStationMapper;
import com.sinosoft.extinterface.service.WeatherStationService;
import com.sinosoft.web.config.WeatherJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.reflect.generics.tree.Tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("common/sharing")
public class CommonController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private WeatherStationMapper WeatherStationMapper;
    @Autowired
    private CommonService commonService;


    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = Global.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void initWeatherStation() throws IOException {
        List<WeatherStation> weatherStations = WeatherStationMapper.selectWeatherStationList(new WeatherStation());
        for (WeatherStation weatherStation : weatherStations) {
            String cityCode = WeatherStationMapper.getCityCode(weatherStation.getCounty());
            if (cityCode != null) {
                try {
                    Map<String, Object> todayWeather = WeatherJsonUtil.getTodayWeather(cityCode);
                    //最高温度
                    weatherStation.setMaxTemperature(todayWeather.get("temp1").toString());
                    //最低温度
                    weatherStation.setMinTemperature(todayWeather.get("temp2").toString());
                    //天气状况
                    weatherStation.setWeather(todayWeather.get("weather").toString());
                    WeatherStationMapper.updateaddFields(weatherStation);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    @GetMapping("/featuresTreeData")
    @ResponseBody
    public List<Ztree> selectFeaturesTree() {
        return commonService.selectFeaturesTree();
    }

    /**
     * 功能描述
     *
     * @param
     * @return [id, treeId, treePid]
     * @author sunlei
     * @date 2020/11/12
     */
    @PostMapping("/sharedData")
    public String sharedData(String id, String treeId, String treePid) {
        String result = commonService.sharedData(id, treeId, treePid);
        return result;
    }

}
