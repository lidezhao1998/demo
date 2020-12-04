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
import com.ruoyi.system.domain.Soil;
import com.ruoyi.system.service.ISoilService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 保育土壤Controller
 * 
 * @author hdp
 * @date 2020-06-22
 */
@Controller
@RequestMapping("/eceas/soil")
public class SoilController extends BaseController
{
    private String prefix = "eceas/soil";

    @Autowired
    private ISoilService soilService;

    @RequiresPermissions("eceas:soil:view")
    @GetMapping()
    public String soil()
    {
        return prefix + "/soil";
    }

    /**
     * 查询保育土壤列表
     */
    @RequiresPermissions("eceas:soil:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Soil soil)
    {
        startPage();
        List<Soil> list = soilService.selectSoilList(soil);
        return getDataTable(list);
    }

    /**
     * 导出保育土壤列表
     */
    @RequiresPermissions("eceas:soil:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Soil soil)
    {
        List<Soil> list = soilService.selectSoilList(soil);
        ExcelUtil<Soil> util = new ExcelUtil<Soil>(Soil.class);
        return util.exportExcel(list, "soil");
    }

    /**
     * 新增保育土壤
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存保育土壤
     */
    @RequiresPermissions("eceas:soil:add")
    @Log(title = "保育土壤", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Soil soil)
    {
        return toAjax(soilService.insertSoil(soil));
    }

    /**
     * 修改保育土壤
     */
    @GetMapping("/edit/{soilId}")
    public String edit(@PathVariable("soilId") Long soilId, ModelMap mmap)
    {
        Soil soil = soilService.selectSoilById(soilId);
        mmap.put("soil", soil);
        return prefix + "/edit";
    }

    /**
     * 修改保存保育土壤
     */
    @RequiresPermissions("eceas:soil:edit")
    @Log(title = "保育土壤", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Soil soil)
    {
        return toAjax(soilService.updateSoil(soil));
    }

    /**
     * 删除保育土壤
     */
    @RequiresPermissions("eceas:soil:remove")
    @Log(title = "保育土壤", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(soilService.deleteSoilByIds(ids));
    }
}
