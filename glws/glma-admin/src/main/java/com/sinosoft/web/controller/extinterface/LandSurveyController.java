package com.sinosoft.web.controller.extinterface;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.GeoServerUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.UpFile;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.gis.GisTab;
import com.sinosoft.extinterface.domain.LandSurvey;
import com.sinosoft.extinterface.domain.SpatialPlanning;
import com.sinosoft.extinterface.service.LandSurveyService;
import com.sinosoft.web.properties.GeoServerProperties;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 国土
 * author  lhf
 * date  2020/8/20 10:17
 * version 1.0
 */
@Controller
@RequestMapping("extinterface/landsurvey")
public class LandSurveyController extends BaseController {
    @Autowired
    GeoServerProperties geoServerProperties;
    @Autowired
    LandSurveyService landSurveyService;
    private String prefix = "extinterface/landsurvey";

    @GetMapping
    public String MobileDeviceData() {
        return prefix + "/landsurveyList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo landSurveyList(LandSurvey landSurvey) {
        startPage();
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String xzcode = ShiroUtils.getSysUser().getDept().getXzcode();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("省级")) {
                landSurvey.setProvince(deptName);
                landSurvey.setProvinceCode(xzcode);
            } else if (roleName.equals("市级")) {
                landSurvey.setCity(deptName);
                landSurvey.setCityCode(xzcode);
            } else if (roleName.equals("区级")) {
                landSurvey.setCounty(deptName);
                landSurvey.setCountyCode(xzcode);
            } else if (roleName.equals("国家级")) {
                landSurvey.setStatus(1);
            }
        }
        List<LandSurvey> landSurveys = landSurveyService.selectLandSurveyList(landSurvey);
        return getDataTable(landSurveys);
    }

    /**
     * 新增shpFile
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 修改shpFile
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("shpFile", landSurveyService.selectLandSurvey(id));
        return prefix + "/edit";
    }
 /**
     * 修改shpFile
     */
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("shpFile", landSurveyService.selectLandSurvey(id));
        return prefix + "/details";
    }

    /**
     * 新增保存shpFile
     */
    @Log(title = "shpFile管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("integration:remoteSensing:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("files") MultipartFile[] files, @Validated LandSurvey landSurvey) throws IOException {
        //上传文件
        Map<String, String> map = UpFile.uploadFile(files);
        //返回上传后的路径
        String filePath = map.get("filePath");

        //入库
        int i = landSurveyService.insertShpFile(filePath, landSurvey.getColor());

        return toAjax(i);

    }

    /**
     * 新增保存shpFile
     */
    @Log(title = "shpFile管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("integration:remoteSensing:add")
    @PostMapping("/addFields")
    @ResponseBody
    public AjaxResult addFields(@Validated LandSurvey landSurvey) throws IOException {

        int i = landSurveyService.addFields(landSurvey.getId(), landSurvey.getCentroid());

        return toAjax(i);

    }

    /**
     * 删除
     */
    @Log(title = "shpFile管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("integration:remoteSensing:remove")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id) {
//
        return toAjax(landSurveyService.deleteShpFileById(id));
    }

    /**
     * 审核取消
     */
    @PostMapping("/unexamine")
    @ResponseBody
    public AjaxResult unexamine(@RequestParam("id") Long id) {
        return toAjax(landSurveyService.updateunexamine(id));
    }

    /**
     * 审核通过
     */
    @PostMapping("/examine")
    @ResponseBody
    public AjaxResult examine(@RequestParam("id") Long id) {
        int examine = landSurveyService.examine(id);
        return toAjax(examine);
    }

    /**
     * 保存
     */
    @Log(title = "shpFile管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("integration:remoteSensing:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated LandSurvey landSurvey) {

        return toAjax(landSurveyService.updateLandSurvey(landSurvey));
    }


    public boolean releaseShp(String path, String fileName) {
        boolean b = false;
        try {
            b = GeoServerUtils.GeoserverPublishShapefileData(
                    geoServerProperties.getUrlYml(),
                    geoServerProperties.getUsernameYml(),
                    geoServerProperties.getPasswordYml(),
                    geoServerProperties.getWorkspaceYml(),
                    geoServerProperties.getSrsYml(),
                    path,
                    fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

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
        }
        return prefix + "/addGisTab";
    }

    @GetMapping("/selectFeaturesTree")
    public String selectFeaturesTree(ModelMap mmap) {
        return prefix + "/tree";
    }



}
