package com.ruoyi.zaihai.ReserveManagement.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;
import com.ruoyi.zaihai.ReserveManagement.service.IKManufactorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资Controller
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/reserves/manufactor")
public class KManufactorController extends BaseController
{
    private String prefix = "ReserveManagement/manufactor";

    @Autowired
    private IKManufactorService kManufactorService;

    @GetMapping()
    public String manufactor()
    {
        return prefix + "/manufactor";
    }

    /**
     * 查询物资列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KManufactor kManufactor)
    {
        startPage();
        List<KManufactor> list = kManufactorService.selectKManufactorList(kManufactor);
        return getDataTable(list);
    }

    /**
     * 导出物资列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KManufactor kManufactor)
    {
        List<KManufactor> list = kManufactorService.selectKManufactorList(kManufactor);
        ExcelUtil<KManufactor> util = new ExcelUtil<KManufactor>(KManufactor.class);
        return util.exportExcel(list, "manufactor");
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap mmap)
    {
        mmap.put("dictType", dictType);
        return prefix + "/add";
    }


    /**
     * 新增保存物资
     */
    @Log(title = "物资", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KManufactor kManufactor)
    {
        kManufactor.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(kManufactorService.insertKManufactor(kManufactor));
    }

    /**
     * 修改物资
     */
    @GetMapping("/edit/{materialId}")
    public String edit(@PathVariable("materialId") Long materialId, ModelMap mmap)
    {
        KManufactor kManufactor = kManufactorService.selectKManufactorById(materialId);
        mmap.put("kManufactor", kManufactor);
        return prefix + "/edit";
    }

    /**
     * 修改保存物资
     */
    @Log(title = "物资", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KManufactor kManufactor)
    {
        return toAjax(kManufactorService.updateKManufactor(kManufactor));
    }

    /**
     * 删除物资
     */
    @Log(title = "物资", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kManufactorService.deleteKManufactorByIds(ids));
    }
}
