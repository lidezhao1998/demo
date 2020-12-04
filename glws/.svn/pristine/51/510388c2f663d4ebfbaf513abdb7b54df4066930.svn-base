package com.ruoyi.zaihai.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.workflow.domain.FlowWflog;
import com.ruoyi.zaihai.workflow.service.IFlowWflogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 审核记录Controller
 * 
 * @author ruoyi
 * @date 2020-09-23
 */
@Controller
@RequestMapping("/workflow/wflog")
public class FlowWflogController extends BaseController
{
    private String prefix = "system/wflog";

    @Autowired
    private IFlowWflogService flowWflogService;

    @GetMapping()
    public String wflog()
    {
        return prefix + "/wflog";
    }

    /**
     * 查询审核记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FlowWflog flowWflog)
    {
        startPage();
        List<FlowWflog> list = flowWflogService.selectFlowWflogList(flowWflog);
        return getDataTable(list);
    }
    /**
     * 查询审核记录列表
     */
    @PostMapping("/listInfo")
    @ResponseBody
    public TableDataInfo listInfo(FlowWflog flowWflog,@RequestParam("id")String id,@RequestParam("type")String type)
    {
        //startPage();
        flowWflog.setFiletypeid(type);
        flowWflog.setRecordid(id);
        List<FlowWflog> list = flowWflogService.selectFlowWflogList(flowWflog);
        return getDataTable(list);
    }
    /**
     * 导出审核记录列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FlowWflog flowWflog)
    {
        List<FlowWflog> list = flowWflogService.selectFlowWflogList(flowWflog);
        ExcelUtil<FlowWflog> util = new ExcelUtil<FlowWflog>(FlowWflog.class);
        return util.exportExcel(list, "wflog");
    }

    /**
     * 新增审核记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存审核记录
     */
    @Log(title = "审核记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FlowWflog flowWflog)
    {
        return toAjax(flowWflogService.insertFlowWflog(flowWflog));
    }

    /**
     * 修改审核记录
     */
    @GetMapping("/edit/{login}")
    public String edit(@PathVariable("login") String login, ModelMap mmap)
    {
        FlowWflog flowWflog = flowWflogService.selectFlowWflogById(login);
        mmap.put("flowWflog", flowWflog);
        return prefix + "/edit";
    }

    /**
     * 修改保存审核记录
     */
    @Log(title = "审核记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FlowWflog flowWflog)
    {
        return toAjax(flowWflogService.updateFlowWflog(flowWflog));
    }

    /**
     * 删除审核记录
     */
    @Log(title = "审核记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(flowWflogService.deleteFlowWflogByIds(ids));
    }
}
