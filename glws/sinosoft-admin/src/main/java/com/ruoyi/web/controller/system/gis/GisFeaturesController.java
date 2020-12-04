package com.ruoyi.web.controller.system.gis;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.gis.GisFeatures;
import com.ruoyi.system.service.gis.IGisFeaturesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * shpFile信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/gisFeatures")
public class GisFeaturesController extends BaseController {
    private String prefix = "system/gis";


    @Autowired
    private IGisFeaturesService gisFeatureService;

    @RequiresPermissions("system:gisFeatures:view")
    @GetMapping()
    public String gisFeatures() {
        return prefix + "/gisFeatures";
    }

    @RequiresPermissions("system:gisFeatures:list")
    @PostMapping("/list")
    @ResponseBody
    public List<GisFeatures> list(GisFeatures gisFeatures) {
        List<GisFeatures> gisFeaturesList = gisFeatureService.selectGisFeaturesList(gisFeatures);
        return gisFeaturesList;
    }

    /**
     * 新增gisFeatures
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        mmap.put("gisFeatures", gisFeatureService.selectGisFeaturesById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存gisFeatures
     */
    @Log(title = "gisFeatures管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:gisFeatures:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated GisFeatures gisFeatures, MultipartFile gisFeaturesPathIo) {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(gisFeatureService.checkGisFeaturesNameUnique(gisFeatures))) {
            return error("新增gisFeatures'" + gisFeatures.getName() + "'失败，gisFeatures名称已存在");
        }
        gisFeatures.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(gisFeatureService.insertGisFeatures(gisFeatures));
    }


    /**
     * 修改
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        GisFeatures gisFeatures = gisFeatureService.selectGisFeaturesById(id);
        mmap.put("gisFeatures", gisFeatures);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "gisFeatures管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:gisFeatures:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated GisFeatures gisFeatures, MultipartFile gisFeaturesPathIo) {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(gisFeatureService.checkGisFeaturesNameUnique(gisFeatures))) {
            return error("修改gisFeatures'" + gisFeatures.getName() + "'失败，gisFeatures名称已存在");
        } else if (gisFeatures.getParentId().equals(gisFeatures.getId())) {
            return error("修改gisFeatures'" + gisFeatures.getName() + "'失败，上级gisFeatures不能是自己");
        }
        gisFeatures.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(gisFeatureService.updateGisFeatures(gisFeatures));
    }

    /**
     * 删除
     */
    @Log(title = "gisFeatures管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:gisFeatures:remove")
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long gisFeaturesId) {
        if (gisFeatureService.selectGisFeaturesCount(gisFeaturesId) > 0) {
            return AjaxResult.warn("存在下级gisFeatures,不允许删除");
        }
        return toAjax(gisFeatureService.deleteGisFeaturesById(gisFeaturesId));
    }

    /**
     * 校验gisFeatures名称
     */
    @PostMapping("/checkGisFeaturesNameUnique")
    @ResponseBody
    public String checkGisFeaturesNameUnique(GisFeatures gisFeatures) {
        return gisFeatureService.checkGisFeaturesNameUnique(gisFeatures);
    }

    /**
     * 选择gisFeatures树
     */
    @GetMapping("/selectGisFeaturesTree/{id}")
    public String selectGisFeaturesTree(@PathVariable("id") Long GisFeaturesId, ModelMap mmap) {
        mmap.put("gisFeatures", gisFeatureService.selectGisFeaturesById(GisFeaturesId));
        return prefix + "/tree";
    }

    /**
     * 加载gisFeatures列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        List<Ztree> ztrees = gisFeatureService.selectGisFeaturesTree(new GisFeatures());
        return ztrees;
    }

    /**
     * 加载角色gisFeatures（数据权限）列表树
     */
    @GetMapping("/roleGisFeaturesTreeData")
    @ResponseBody
    public List<Ztree> GisFeaturesTreeData(SysRole role) {
        List<Ztree> ztrees = gisFeatureService.roleGisFeaturesTreeData(role);
        return ztrees;
    }
    @ResponseBody
    @RequestMapping("/getList")
    public List<Ztree> getListByType(){
        List<Ztree> ztrees=gisFeatureService.getListByType();
        return ztrees;
    }
}
