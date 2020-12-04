package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Map;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.service.ITTaskReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TTaskIdea;
import com.ruoyi.system.service.ITTaskIdeaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 意见Controller
 *
 * @author ruoyi
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/system/idea")
public class TTaskIdeaController extends BaseController
{
    private String prefix = "system/idea";

    @Autowired
    private ITTaskIdeaService tTaskIdeaService;
    @Autowired
    private ITTaskReportService tTaskReportService;
    @RequiresPermissions("system:idea:view")
    @GetMapping()
    public String idea()
    {
        return prefix + "/idea";
    }

    /**
     * 查询意见列表
     */
    @RequestMapping("/list/{id}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("id") Integer reportId)
    {
        startPage();
        List<TTaskIdea> list = tTaskIdeaService.selectTTaskIdeaById(reportId);
        return getDataTable(list);
    }
    /**
     * 意见列表
     */
    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Integer reportId,ModelMap modelMap)
    {
        modelMap.addAttribute("id",reportId);
        return prefix + "/idea";
    }


    /**
     * 新增意见
     */
    @GetMapping("/add/{id}/{status}")
    public String add(@PathVariable("id") Integer id,@PathVariable("status") Integer status,ModelMap mmap)
    {
        mmap.addAttribute("taskId",id);
        mmap.addAttribute("status",status);
        mmap.addAttribute("ideaCreateUser",ShiroUtils.getLoginName());
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(Long.parseLong(id+""));
        tTaskReport.setStatus(status+"");
        tTaskReportService.updateTTaskReport(tTaskReport);
        return prefix + "/add";
    }

    /**
     * 新增保存意见
     */
    //@RequiresPermissions("system:idea:add")
    @Log(title = "意见", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TTaskIdea tTaskIdea,@RequestParam Map map)
    {
        Long taskId = Long.parseLong((String) map.get("taskId"));
        String status = (String) map.get("status");
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(taskId);
        tTaskReport.setStatus(status);
        tTaskReportService.updateTTaskReport(tTaskReport);
        return toAjax(tTaskIdeaService.insertTTaskIdea(tTaskIdea));
    }

}
