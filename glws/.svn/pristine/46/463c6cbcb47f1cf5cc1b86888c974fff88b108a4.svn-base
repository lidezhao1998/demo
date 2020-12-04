package com.sinosoft.web.controller.extinterface;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.GeoServerUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.UpFile;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.extinterface.domain.LandSurvey;
import com.sinosoft.extinterface.domain.SpatialPlanning;
import com.sinosoft.extinterface.service.SpatialPlanningService;
import com.sinosoft.integration.domain.SysShpFile;
import com.sinosoft.integration.service.ISysShpFileService;
import com.sinosoft.web.properties.GeoServerProperties;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sunlei
 * @description 国土空间规划
 * @date 2020/09/02/10:31
 */
@Controller
@RequestMapping("extinterface/spatialPlanning")
public class SpatialPlanningController extends BaseController {
    @Autowired
    SpatialPlanningService spatialPlanningService;
    @Autowired
    GeoServerProperties geoServerProperties;
    //    @Autowired
//    ISysShpFileService shpFileService;
    private String prefix = "extinterface/spatialPlanning";

    @GetMapping
    public String MobileDeviceData() {
        return prefix + "/spatialPlanningList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo landSurveyList(SpatialPlanning spatialPlanning) {
        startPage();
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String xzcode = ShiroUtils.getSysUser().getDept().getXzcode();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
                spatialPlanning.setProvince(deptName);
                spatialPlanning.setProvinceCode(xzcode);
            }else if(roleName.equals("市级")){
                spatialPlanning.setCity(deptName);
                spatialPlanning.setCityCode(xzcode);
            }else if(roleName.equals("区级")){
                spatialPlanning.setCounty(deptName);
                spatialPlanning.setCountyCode(xzcode);
            }else if(roleName.equals("国家级")){
                spatialPlanning.setStatus(1);

            }
        }
        List<SpatialPlanning> spatialPlanningList = spatialPlanningService.selectSpatialPlanningList(spatialPlanning);
//        for (SpatialPlanning survey : spatialPlanningList) {
//            spatialPlanningService.addFields(survey.getId(), survey.getGeomData());
//        }
        return getDataTable(spatialPlanningList);
    }

    /**
     * 新增shpFile
     */

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存shpFile
     */
    @Log(title = "shpFile管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("integration:remoteSensing:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam(value = "files") MultipartFile[] files, @Validated SpatialPlanning spatialPlanning) throws IOException {
        //上传文件
        Map<String, String> map = UpFile.uploadFile(files);
        //返回上传后的路径
        String filePath = map.get("filePath");
        //读取上传的shp文件
        //入库
        int i = spatialPlanningService.insertShpFile(filePath, spatialPlanning.getColor());

        return toAjax(i);
    }

    /**
     * 选择shpFile树
     */
    @GetMapping("/selectShpFileTree/{ShpFileId}")
    public String selectShpFileTree(@PathVariable("ShpFileId") Long ShpFileId, ModelMap mmap) {
        mmap.put("shpFile", spatialPlanningService.selectShpFileById(ShpFileId));
        return prefix + "/tree";
    }
//    /**
//     * 加载shpFile列表树
//     */
//    @GetMapping("/treeData")
//    @ResponseBody
//    public List<Ztree> treeData() {
//        List<Ztree> ztrees = shpFileService.selectShpFileTree(new SysShpFile());
//        return ztrees;
//    }

    /**
     * 修改
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SpatialPlanning spatialPlanning = spatialPlanningService.selectShpFileById(id);
        mmap.put("shpFile", spatialPlanning);

        return prefix + "/edit";
    }
 /**
     * 详情
     */
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, ModelMap mmap) {
        SpatialPlanning spatialPlanning = spatialPlanningService.selectShpFileById(id);
        mmap.put("shpFile", spatialPlanning);

        return prefix + "/details";
    }

    /**
     * 保存
     */
    @Log(title = "shpFile管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("integration:remoteSensing:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SpatialPlanning shpFile) {

        return toAjax(spatialPlanningService.updateEditSpatialPlanning(shpFile));
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
        return toAjax(spatialPlanningService.deleteShpFileById(id));
    }

    /**
     * 校验shpFile名称
     */
    @PostMapping("/checkShpFileNameUnique")
    @ResponseBody
    public String checkShpFileNameUnique(SpatialPlanning shpFile) {
        return spatialPlanningService.checkShpFileNameUnique(shpFile);
    }

    /**
     * 审核取消
     */
    @PostMapping("/unexamine")
    @ResponseBody
    public AjaxResult unexamine(@RequestParam("id") Long id) {
        return toAjax(spatialPlanningService.updateunexamine(id));
    }

    /**
     * 审核通过
     */
    @PostMapping("/examine")
    @ResponseBody
    public AjaxResult examine(@RequestParam("id") Long id) {
        return toAjax(spatialPlanningService.examine(id));
    }

    /**
     * @Describe 根据shp id 发布geoServer 服务
     * @Params [id]
     * @Return boolean
     * @DATE 2020/7/21
     * @Author LiRenDong
     */
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

    /**
     * @Describe 根据shp id 撤销geoServer 服务
     * @Params [id]
     * @Return boolean
     * @DATE 2020/7/21
     * @Author LiRenDong
     */
    public boolean undoShp(SpatialPlanning shpFile) {
        boolean b = false;
        try {
            b = GeoServerUtils.remaveShapefile(
                    geoServerProperties.getUrlYml(),
                    geoServerProperties.getUsernameYml(),
                    geoServerProperties.getPasswordYml(),
                    geoServerProperties.getWorkspaceYml(),
                    shpFile.getFileName());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }


}
