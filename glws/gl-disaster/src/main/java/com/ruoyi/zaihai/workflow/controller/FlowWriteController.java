package com.ruoyi.zaihai.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.zaihai.workflow.domain.FlowWrite;
import com.ruoyi.zaihai.workflow.service.IFlowWriteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程Controller
 * 
 * @author sudogndong
 * @date 2020-07-10
 */
@Controller
@RequestMapping("/workflow/write")
public class FlowWriteController extends BaseController
{
    private String prefix = "/system/switchSkin";

    @Autowired
    private IFlowWriteService flowWriteService;

    @RequiresPermissions("workflow:write:view")
    @GetMapping()
    public String write()
    {
        return prefix + "/write";
    }

    /**
     * 查询流程列表
     */
    @RequiresPermissions("workflow:write:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FlowWrite flowWrite)
    {
        startPage();
        List<FlowWrite> list = flowWriteService.selectFlowWriteList(flowWrite);
        return getDataTable(list);
    }

    /**
     * 导出流程列表
     */
    @RequiresPermissions("workflow:write:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FlowWrite flowWrite)
    {
        List<FlowWrite> list = flowWriteService.selectFlowWriteList(flowWrite);
        ExcelUtil<FlowWrite> util = new ExcelUtil<FlowWrite>(FlowWrite.class);
        return util.exportExcel(list, "write");
    }

    /**
     * 新增流程
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流程
     */
    @RequiresPermissions("workflow:write:add")
    @Log(title = "流程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FlowWrite flowWrite)
    {
        return toAjax(flowWriteService.insertFlowWrite(flowWrite));
    }

    /**
     * 修改流程
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FlowWrite flowWrite = flowWriteService.selectFlowWriteById(id);
        mmap.put("flowWrite", flowWrite);
        return prefix + "/edit";
    }

    /**
     * 修改保存流程
     */
    @RequiresPermissions("workflow:write:edit")
    @Log(title = "流程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FlowWrite flowWrite)
    {
        return toAjax(flowWriteService.updateFlowWrite(flowWrite));
    }

    /**
     * 删除流程
     */
    @RequiresPermissions("workflow:write:remove")
    @Log(title = "流程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(flowWriteService.deleteFlowWriteByIds(ids));
    }

    /**
     * 查询流程列表
     */
    @RequiresPermissions("workflow:write:list")
    @PostMapping("/write")
    @ResponseBody
    public TableDataInfo write(FlowWrite flowWrite)
    {
        startPage();
        SysUser currentUser = ShiroUtils.getSysUser();
        List<FlowWrite> list = flowWriteService.selectFlowWriteListdai(String.valueOf(currentUser.getUserId()));
        return getDataTable(list);
    }
}
