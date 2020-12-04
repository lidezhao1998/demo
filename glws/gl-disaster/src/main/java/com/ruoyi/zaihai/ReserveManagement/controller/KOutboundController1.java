package com.ruoyi.zaihai.ReserveManagement.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.ReserveManagement.domain.KOutbound;
import com.ruoyi.zaihai.ReserveManagement.service.IKOutboundService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库Controller
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
@Controller
@RequestMapping("/reserves/outbound1")
public class KOutboundController1 extends BaseController
{
    private String prefix = "ReserveManagement/outbound";

    @Autowired
    private IKOutboundService kOutboundService;

    @RequiresPermissions("reserves:outbound:view")
    @GetMapping()
    public String outbound()
    {
        return prefix + "/outbound";
    }

    /**
     * 查询出库列表
     */
    @RequiresPermissions("reserves:outbound:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KOutbound kOutbound)
    {
        startPage();
        List<KOutbound> list = kOutboundService.selectKOutboundList(kOutbound);
        return getDataTable(list);
    }

    /**
     * 导出出库列表
     */
    @RequiresPermissions("reserves:outbound:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KOutbound kOutbound)
    {
        List<KOutbound> list = kOutboundService.selectKOutboundList(kOutbound);
        ExcelUtil<KOutbound> util = new ExcelUtil<KOutbound>(KOutbound.class);
        return util.exportExcel(list, "outbound");
    }

    /**
     * 新增出库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存出库
     */
    @RequiresPermissions("reserves:outbound:add")
    @Log(title = "出库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KOutbound kOutbound)
    {
        kOutbound.setStatus("1");
        return toAjax(kOutboundService.insertKOutbound(kOutbound));
    }

    /**
     * 修改出库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KOutbound kOutbound = kOutboundService.selectKOutboundById(id);
        mmap.put("kOutbound", kOutbound);
        return prefix + "/edit";
    }

    /**
     * 修改保存出库
     */
    @RequiresPermissions("reserves:outbound:edit")
    @Log(title = "出库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KOutbound kOutbound)
    {
        return toAjax(kOutboundService.updateKOutbound(kOutbound));
    }

    /**
     * 删除出库
     */
    @RequiresPermissions("reserves:outbound:remove")
    @Log(title = "出库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kOutboundService.deleteKOutboundByIds(ids));
    }

    /**
     * 确认出库操作
     */
    @Log(title = "撤回操作", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult updateoutbound  (long outboundId)
    {
        //判断状态为待出库
        return toAjax(kOutboundService.updateoutbound(outboundId));
    }


    /**
     * 查看病害知识库
     */
    @RequiresPermissions("reserves:outbound:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap, KOutbound kOutbound)
    {
        KOutbound kOutboundLook = kOutboundService.selectKOutboundById(id);
        mmap.put("kOutbound", kOutboundLook);
        return prefix + "/look";
    }
}
