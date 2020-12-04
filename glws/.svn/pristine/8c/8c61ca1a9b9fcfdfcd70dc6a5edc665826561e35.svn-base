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
import com.ruoyi.system.domain.PublicData;
import com.ruoyi.system.service.IPublicDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生态效益价值评价社会公共数据Controller
 * 
 * @author hdp
 * @date 2020-06-28
 */
@Controller
@RequestMapping("/eceas/data")
public class PublicDataController extends BaseController
{
    private String prefix = "eceas/data";

    @Autowired
    private IPublicDataService publicDataService;

    @RequiresPermissions("eceas:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/data";
    }

    /**
     * 查询生态效益价值评价社会公共数据列表
     */
    @RequiresPermissions("eceas:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PublicData publicData)
    {
        startPage();
        List<PublicData> list = publicDataService.selectPublicDataList(publicData);
        return getDataTable(list);
    }

    /**
     * 导出生态效益价值评价社会公共数据列表
     */
    @RequiresPermissions("eceas:data:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PublicData publicData)
    {
        List<PublicData> list = publicDataService.selectPublicDataList(publicData);
        ExcelUtil<PublicData> util = new ExcelUtil<PublicData>(PublicData.class);
        return util.exportExcel(list, "data");
    }

    /**
     * 新增生态效益价值评价社会公共数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生态效益价值评价社会公共数据
     */
    @RequiresPermissions("eceas:data:add")
    @Log(title = "生态效益价值评价社会公共数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PublicData publicData)
    {
        return toAjax(publicDataService.insertPublicData(publicData));
    }

    /**
     * 修改生态效益价值评价社会公共数据
     */
    @GetMapping("/edit/{dataid}")
    public String edit(@PathVariable("dataid") Long dataid, ModelMap mmap)
    {
        PublicData publicData = publicDataService.selectPublicDataById(dataid);
        mmap.put("publicData", publicData);
        return prefix + "/edit";
    }

    /**
     * 修改保存生态效益价值评价社会公共数据
     */
    @RequiresPermissions("eceas:data:edit")
    @Log(title = "生态效益价值评价社会公共数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PublicData publicData)
    {
        return toAjax(publicDataService.updatePublicData(publicData));
    }

    /**
     * 删除生态效益价值评价社会公共数据
     */
    @RequiresPermissions("eceas:data:remove")
    @Log(title = "生态效益价值评价社会公共数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(publicDataService.deletePublicDataByIds(ids));
    }
}
