package com.ruoyi.web.controller.eceas;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ModelManagement;
import com.ruoyi.system.service.IModelManagementService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 水源涵养模型Controller
 * 
 * @author hdp
 * @date 2020-08-11
 */
@Controller
@RequestMapping("/eceas/model")
public class ModelManagementController extends BaseController
{
    private String prefix = "eceas/model";

    @Autowired
    private IModelManagementService modelManagementService;

    @RequiresPermissions("eceas:model:view")
    @GetMapping()
    public String model()
    {
        return prefix + "/model";
    }

    /**
     * 查询水源涵养模型列表
     */
    @RequiresPermissions("eceas:model:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ModelManagement modelManagement)
    {
        startPage();
        List<ModelManagement> list = modelManagementService.selectModelManagementList(modelManagement);
        return getDataTable(list);
    }

    /**
     * 导出水源涵养模型列表
     */
    @RequiresPermissions("eceas:model:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ModelManagement modelManagement)
    {
        List<ModelManagement> list = modelManagementService.selectModelManagementList(modelManagement);
        ExcelUtil<ModelManagement> util = new ExcelUtil<ModelManagement>(ModelManagement.class);
        return util.exportExcel(list, "model");
    }

    /**
     * 新增水源涵养模型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存水源涵养模型
     */
    @RequiresPermissions("eceas:model:add")
    @Log(title = "水源涵养模型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ModelManagement modelManagement)
    {
        Double waterTotal = (modelManagement.getP()-modelManagement.getR()-modelManagement.getEt())* modelManagement.getA();
        modelManagement.setTq(waterTotal);
        return toAjax(modelManagementService.insertModelManagement(modelManagement));
    }

    /**
     * 修改水源涵养模型
     */
    @GetMapping("/edit/{modelId}")
    public String edit(@PathVariable("modelId") Long modelId, ModelMap mmap)
    {
        ModelManagement modelManagement = modelManagementService.selectModelManagementById(modelId);
        mmap.put("modelManagement", modelManagement);
        return prefix + "/edit";
    }

    /**
     * 修改保存水源涵养模型
     */
    @RequiresPermissions("eceas:model:edit")
    @Log(title = "水源涵养模型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ModelManagement modelManagement)
    {
        return toAjax(modelManagementService.updateModelManagement(modelManagement));
    }

    /**
     * 删除水源涵养模型
     */
    @RequiresPermissions("eceas:model:remove")
    @Log(title = "水源涵养模型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(modelManagementService.deleteModelManagementByIds(ids));
    }
}
