package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.CMeteorological;
import com.ruoyi.zaihai.caiji.service.ICMeteorologicalService;
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
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 气象管理信息Controller
 * 
 * @author ruoyi
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/caiji/meteorological")
public class CMeteorologicalController extends BaseController
{
    private String prefix = "caiji/meteorological";

    @Autowired
    private ICMeteorologicalService cMeteorologicalService;

    @RequiresPermissions("caiji:meteorological:view")
    @GetMapping()
    public String meteorological()
    {
        return prefix + "/meteorological";
    }

    /**
     * 查询气象管理信息列表
     */
    @RequiresPermissions("caiji:meteorological:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CMeteorological cMeteorological)
    {
        startPage();
        List<CMeteorological> list = cMeteorologicalService.selectCMeteorologicalList(cMeteorological);
        return getDataTable(list);
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CMeteorological cMeteorological = cMeteorologicalService.selectCMeteorologicalById(id);
        mmap.put("cMeteorological",cMeteorological);
        return prefix + "/detail";
    }

    /**
     * 导出气象管理信息列表
     */
    @RequiresPermissions("caiji:meteorological:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CMeteorological cMeteorological)
    {
        List<CMeteorological> list = cMeteorologicalService.selectCMeteorologicalList(cMeteorological);
        ExcelUtil<CMeteorological> util = new ExcelUtil<CMeteorological>(CMeteorological.class);
        return util.exportExcel(list, "meteorological");
    }

    /**
     * 新增气象管理信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存气象管理信息
     */
    @RequiresPermissions("caiji:meteorological:add")
    @Log(title = "气象管理信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CMeteorological cMeteorological)
    {
        return toAjax(cMeteorologicalService.insertCMeteorological(cMeteorological));
    }

    /**
     * 修改气象管理信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CMeteorological cMeteorological = cMeteorologicalService.selectCMeteorologicalById(id);
        mmap.put("cMeteorological", cMeteorological);
        return prefix + "/edit";
    }

    /**
     * 修改保存气象管理信息
     */
    @RequiresPermissions("caiji:meteorological:edit")
    @Log(title = "气象管理信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CMeteorological cMeteorological)
    {
        return toAjax(cMeteorologicalService.updateCMeteorological(cMeteorological));
    }


    /**
     * 修改状态
     */
    @Log(title = "状态", businessType = BusinessType.UPDATE)
    @RequiresPermissions("caiji:meteorological:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(CMeteorological cMeteorological)
    {
        return toAjax(cMeteorologicalService.changeStatus(cMeteorological));
    }

    /**
     * 删除气象管理信息
     */
    @RequiresPermissions("caiji:meteorological:remove")
    @Log(title = "气象管理信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cMeteorologicalService.deleteCMeteorologicalByIds(ids));
    }


    /**
     * 新增气象管理信息
     */
    @GetMapping("/ld")
    public String ld()
    {
        return prefix + "/ld";
    }

}
