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
import com.ruoyi.system.domain.GradeSetting;
import com.ruoyi.system.service.IGradeSettingService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 等级Controller
 * 
 * @author hdp
 * @date 2020-10-12
 */
@Controller
@RequestMapping("/eceas/grade")
public class GradeSettingController extends BaseController
{
    private String prefix = "eceas/grade";

    @Autowired
    private IGradeSettingService gradeSettingService;

    @RequiresPermissions("eceas:grade:view")
    @GetMapping()
    public String grade()
    {
        return prefix + "/grade";
    }

    /**
     * 查询等级列表
     */
    @RequiresPermissions("eceas:grade:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GradeSetting gradeSetting)
    {
        startPage();
        List<GradeSetting> list = gradeSettingService.selectGradeSettingList(gradeSetting);
        return getDataTable(list);
    }

    /**
     * 导出等级列表
     */
    @RequiresPermissions("eceas:grade:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GradeSetting gradeSetting)
    {
        List<GradeSetting> list = gradeSettingService.selectGradeSettingList(gradeSetting);
        ExcelUtil<GradeSetting> util = new ExcelUtil<GradeSetting>(GradeSetting.class);
        return util.exportExcel(list, "grade");
    }

    /**
     * 新增等级
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存等级
     */
    @RequiresPermissions("eceas:grade:add")
    @Log(title = "等级", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GradeSetting gradeSetting)
    {
        return toAjax(gradeSettingService.insertGradeSetting(gradeSetting));
    }

    /**
     * 修改等级
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        GradeSetting gradeSetting = gradeSettingService.selectGradeSettingById(id);
        mmap.put("gradeSetting", gradeSetting);
        return prefix + "/edit";
    }

    /**
     * 修改保存等级
     */
    @RequiresPermissions("eceas:grade:edit")
    @Log(title = "等级", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GradeSetting gradeSetting)
    {
        return toAjax(gradeSettingService.updateGradeSetting(gradeSetting));
    }

    /**
     * 删除等级
     */
    @RequiresPermissions("eceas:grade:remove")
    @Log(title = "等级", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gradeSettingService.deleteGradeSettingByIds(ids));
    }
}
