package com.ruoyi.web.controller.eceas;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WaterEvaluateSummary;
import com.ruoyi.system.service.IWaterEvaluateSummaryService;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 涵养水源功能评估数据汇总Controller
 * 
 * @author hdp
 * @date 2020-09-15
 */
@Controller
@RequestMapping("/eceas/conservation")
public class WaterEvaluateSummaryController extends BaseController
{
    private String prefix = "eceas/conservation";

    @Autowired
    private IWaterEvaluateSummaryService waterEvaluateSummaryService;

    @RequiresPermissions("eceas:conservation:view")
    @GetMapping()
    public String conservation()
    {
        return prefix + "/conservation";
    }

    /**
     * 查询涵养水源功能评估数据汇总列表
     */
    @RequiresPermissions("eceas:conservation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WaterEvaluateSummary waterEvaluateSummary)
    {
        startPage();
        List<WaterEvaluateSummary> list = waterEvaluateSummaryService.selectWaterEvaluateSummaryList(waterEvaluateSummary);
        return getDataTable(list);
    }

    /**
     * 导出涵养水源功能评估数据汇总列表
     */
    @RequiresPermissions("eceas:conservation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WaterEvaluateSummary waterEvaluateSummary)
    {
        List<WaterEvaluateSummary> list = waterEvaluateSummaryService.selectWaterEvaluateSummaryList(waterEvaluateSummary);
        ExcelUtil<WaterEvaluateSummary> util = new ExcelUtil<WaterEvaluateSummary>(WaterEvaluateSummary.class);
        return util.exportExcel(list, "conservation");
    }

    /**
     * 新增涵养水源功能评估数据汇总
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存涵养水源功能评估数据汇总
     */
    @RequiresPermissions("eceas:conservation:add")
    @Log(title = "涵养水源功能评估数据汇总", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WaterEvaluateSummary waterEvaluateSummary)
    {
        waterEvaluateSummary.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(waterEvaluateSummaryService.insertWaterEvaluateSummary(waterEvaluateSummary));
    }

    /**
     * 修改涵养水源功能评估数据汇总
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WaterEvaluateSummary waterEvaluateSummary = waterEvaluateSummaryService.selectWaterEvaluateSummaryById(id);
        mmap.put("waterEvaluateSummary", waterEvaluateSummary);
        return prefix + "/edit";
    }

    /**
     * 修改保存涵养水源功能评估数据汇总
     */
    @RequiresPermissions("eceas:conservation:edit")
    @Log(title = "涵养水源功能评估数据汇总", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WaterEvaluateSummary waterEvaluateSummary)
    {
        return toAjax(waterEvaluateSummaryService.updateWaterEvaluateSummary(waterEvaluateSummary));
    }

    /**
     * 删除涵养水源功能评估数据汇总
     */
    @RequiresPermissions("eceas:conservation:remove")
    @Log(title = "涵养水源功能评估数据汇总", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(waterEvaluateSummaryService.deleteWaterEvaluateSummaryByIds(ids));
    }
}
