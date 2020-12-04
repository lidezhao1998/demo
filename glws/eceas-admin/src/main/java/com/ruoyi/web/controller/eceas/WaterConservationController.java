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
import com.ruoyi.system.domain.WaterConservation;
import com.ruoyi.system.service.IWaterConservationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 涵养水源Controller
 * 
 * @author hdp
 * @date 2020-06-22
 */
@Controller
@RequestMapping("/eceas/water")
public class WaterConservationController extends BaseController
{
    private String prefix = "eceas/water";

    @Autowired
    private IWaterConservationService waterConservationService;

    @RequiresPermissions("eceas:water:view")
    @GetMapping()
    public String water()
    {
        return prefix + "/water";
    }

    /**
     * 查询涵养水源列表
     */
    @RequiresPermissions("eceas:water:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WaterConservation waterConservation)
    {
        startPage();
        List<WaterConservation> list = waterConservationService.selectWaterConservationList(waterConservation);
        return getDataTable(list);
    }

    /**
     * 导出涵养水源列表
     */
    @RequiresPermissions("eceas:water:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WaterConservation waterConservation)
    {
        List<WaterConservation> list = waterConservationService.selectWaterConservationList(waterConservation);
        ExcelUtil<WaterConservation> util = new ExcelUtil<WaterConservation>(WaterConservation.class);
        return util.exportExcel(list, "water");
    }

    /**
     * 新增涵养水源
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存涵养水源
     */
    @RequiresPermissions("eceas:water:add")
    @Log(title = "涵养水源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WaterConservation waterConservation)
    {
        return toAjax(waterConservationService.insertWaterConservation(waterConservation));
    }

    /**
     * 修改涵养水源
     */
    @GetMapping("/edit/{conservationId}")
    public String edit(@PathVariable("conservationId") Long conservationId, ModelMap mmap)
    {
        WaterConservation waterConservation = waterConservationService.selectWaterConservationById(conservationId);
        mmap.put("waterConservation", waterConservation);
        return prefix + "/edit";
    }

    /**
     * 修改保存涵养水源
     */
    @RequiresPermissions("eceas:water:edit")
    @Log(title = "涵养水源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WaterConservation waterConservation)
    {
        return toAjax(waterConservationService.updateWaterConservation(waterConservation));
    }

    /**
     * 删除涵养水源
     */
    @RequiresPermissions("eceas:water:remove")
    @Log(title = "涵养水源", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(waterConservationService.deleteWaterConservationByIds(ids));
    }
}
