package com.ruoyi.zaihai.workflow.controller;

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
import com.ruoyi.zaihai.workflow.domain.FlowRead;
import com.ruoyi.zaihai.workflow.service.IFlowReadService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流程Controller
 * 
 * @author sudongdong
 * @date 2020-07-10
 */
@Controller
@RequestMapping("/workflow/read")
public class FlowReadController extends BaseController
{
    private String prefix = "workflow/read";

    @Autowired
    private IFlowReadService flowReadService;

    @RequiresPermissions("workflow:read:view")
    @GetMapping()
    public String read()
    {
        return prefix + "/read";
    }

    /**
     * 查询流程列表
     */
    @RequiresPermissions("workflow:read:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FlowRead flowRead)
    {
        startPage();
        List<FlowRead> list = flowReadService.selectFlowReadList(flowRead);
        return getDataTable(list);
    }

    /**
     * 导出流程列表
     */
    @RequiresPermissions("workflow:read:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FlowRead flowRead)
    {
        List<FlowRead> list = flowReadService.selectFlowReadList(flowRead);
        ExcelUtil<FlowRead> util = new ExcelUtil<FlowRead>(FlowRead.class);
        return util.exportExcel(list, "read");
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
    @RequiresPermissions("workflow:read:add")
    @Log(title = "流程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FlowRead flowRead)
    {
        return toAjax(flowReadService.insertFlowRead(flowRead));
    }

    /**
     * 修改流程
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FlowRead flowRead = flowReadService.selectFlowReadById(id);
        mmap.put("flowRead", flowRead);
        return prefix + "/edit";
    }

    /**
     * 修改保存流程
     */
    @RequiresPermissions("workflow:read:edit")
    @Log(title = "流程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FlowRead flowRead)
    {
        return toAjax(flowReadService.updateFlowRead(flowRead));
    }

    /**
     * 删除流程
     */
    @RequiresPermissions("workflow:read:remove")
    @Log(title = "流程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(flowReadService.deleteFlowReadByIds(ids));
    }
}
