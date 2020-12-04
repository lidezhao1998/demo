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
import com.ruoyi.zaihai.workflow.domain.FlowIdea;
import com.ruoyi.zaihai.workflow.service.IFlowIdeaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流程意见Controller
 * 
 * @author sudogndong
 * @date 2020-07-10
 */
@Controller
@RequestMapping("/workflow/idea")
public class FlowIdeaController extends BaseController
{
    private String prefix = "workflow/idea";

    @Autowired
    private IFlowIdeaService flowIdeaService;

    @RequiresPermissions("workflow:idea:view")
    @GetMapping()
    public String idea()
    {
        return prefix + "/idea";
    }

    /**
     * 查询流程意见列表
     */
    @RequiresPermissions("workflow:idea:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FlowIdea flowIdea)
    {
        startPage();
        List<FlowIdea> list = flowIdeaService.selectFlowIdeaList(flowIdea);
        return getDataTable(list);
    }

    /**
     * 导出流程意见列表
     */
    @RequiresPermissions("workflow:idea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FlowIdea flowIdea)
    {
        List<FlowIdea> list = flowIdeaService.selectFlowIdeaList(flowIdea);
        ExcelUtil<FlowIdea> util = new ExcelUtil<FlowIdea>(FlowIdea.class);
        return util.exportExcel(list, "idea");
    }

    /**
     * 新增流程意见
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流程意见
     */
    @RequiresPermissions("workflow:idea:add")
    @Log(title = "流程意见", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FlowIdea flowIdea)
    {
        return toAjax(flowIdeaService.insertFlowIdea(flowIdea));
    }

    /**
     * 修改流程意见
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FlowIdea flowIdea = flowIdeaService.selectFlowIdeaById(id);
        mmap.put("flowIdea", flowIdea);
        return prefix + "/edit";
    }

    /**
     * 修改保存流程意见
     */
    @RequiresPermissions("workflow:idea:edit")
    @Log(title = "流程意见", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FlowIdea flowIdea)
    {
        return toAjax(flowIdeaService.updateFlowIdea(flowIdea));
    }

    /**
     * 删除流程意见
     */
    @RequiresPermissions("workflow:idea:remove")
    @Log(title = "流程意见", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(flowIdeaService.deleteFlowIdeaByIds(ids));
    }
}
