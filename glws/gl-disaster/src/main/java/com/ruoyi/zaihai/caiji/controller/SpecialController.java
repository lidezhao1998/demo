package com.ruoyi.zaihai.caiji.controller;

import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.Special;
import com.ruoyi.zaihai.caiji.service.ISpecialService;
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

/**
 * 专题Controller
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
@Controller
@RequestMapping("/system/special")
public class SpecialController extends BaseController
{
    private String prefix = "system/special";

    @Autowired
    private ISpecialService specialService;

    @RequiresPermissions("system:special:view")
    @GetMapping()
    public String special()
    {
        return prefix + "/special";
    }

    /**
     * 查询专题列表
     */
    @RequiresPermissions("system:special:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Special special)
    {
        startPage();
        List<Special> list = specialService.selectSpecialList(special);
        return getDataTable(list);
    }

    /**
     * 导出专题列表
     */
    @RequiresPermissions("system:special:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Special special)
    {
        List<Special> list = specialService.selectSpecialList(special);
        ExcelUtil<Special> util = new ExcelUtil<Special>(Special.class);
        return util.exportExcel(list, "special");
    }

    /**
     * 新增专题
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存专题
     */
    @RequiresPermissions("system:special:add")
    @Log(title = "专题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Special special)
    {
        return toAjax(specialService.insertSpecial(special));
    }

    /**
     * 修改专题
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Special special = specialService.selectSpecialById(id);
        mmap.put("special", special);
        return prefix + "/edit";
    }

    /**
     * 修改保存专题
     */
    @RequiresPermissions("system:special:edit")
    @Log(title = "专题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Special special)
    {
        return toAjax(specialService.updateSpecial(special));
    }

    /**
     * 删除专题
     */
    @RequiresPermissions("system:special:remove")
    @Log(title = "专题", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(specialService.deleteSpecialByIds(ids));
    }


    /**
     * 三级联动省级
     */

    @PostMapping("/getProvinces")
    @ResponseBody
    public List<Special> getProvinces(ModelMap mmap,Special special)
    {
        List<Special> list=specialService.selectSpecialList(special);
        return list;
    }
}
