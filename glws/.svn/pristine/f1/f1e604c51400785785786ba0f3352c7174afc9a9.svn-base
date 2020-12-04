package com.ruoyi.zaihai.caiji.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.zaihai.caiji.domain.CTaskTechnology;
import com.ruoyi.zaihai.caiji.service.ICTaskTechnologyService;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.service.ICResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【科技成果Controller
 *
 * @author ruoyi
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/achievements/technologyassess")
public class CTaskTechnologyAssessController extends BaseController
{
    private String prefix = "system/technologyassess";

    @Autowired
    private ICTaskTechnologyService cTaskTechnologyService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ICResourceService CResourceService;
    @RequiresPermissions("achievements:technologyassess:view")
    @GetMapping()
    public String technology()
    {
        return prefix + "/technologyassess";
    }

    /**
     * 查询科技成果信息列表
     */
    @RequiresPermissions("achievements:technologyassess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskTechnology cTaskTechnology)
    {
        startPage();
        List<CTaskTechnology> list = cTaskTechnologyService.selectCTaskTechnologyList(cTaskTechnology);
        return getDataTable(list);
    }

    /*
     *展开详情信息*/


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskTechnology cTaskTechnology = cTaskTechnologyService.selectCTaskTechnologyById(id);
        mmap.put("cTaskTechnology",cTaskTechnology);
        return prefix + "/detail";
    }

    /**
     * 导出科技成果信息列表
     */
    @RequiresPermissions("achievements:technologyassess:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskTechnology cTaskTechnology)
    {
        List<CTaskTechnology> list = cTaskTechnologyService.selectCTaskTechnologyList(cTaskTechnology);
        ExcelUtil<CTaskTechnology> util = new ExcelUtil<CTaskTechnology>(CTaskTechnology.class);
        return util.exportExcel(list, "technologyassess");
    }

    /**
     * 新增科技成果
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存科技成果信息
     */
    @RequiresPermissions("achievements:technologyassess:add")
    @Log(title = "科技成果信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskTechnology cTaskTechnology)
    {

        return toAjax(cTaskTechnologyService.insertCTaskTechnology(cTaskTechnology));
    }


    /**
     * 修改科技成果信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskTechnology cTaskTechnology = cTaskTechnologyService.selectCTaskTechnologyById(id);
        mmap.put("cTaskTechnology", cTaskTechnology);
        return prefix + "/edit";
    }

    /**
     * 撤回操作
     */
    @Log(title = "撤回操作", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult Withdraw  (CTaskTechnology cTaskTechnology)
    {
        //判断状态为未领取
        /*if(cTaskTechnology.getStatus()=="0"){*/
            return toAjax(cTaskTechnologyService.updateCTaskTechnologychehui(cTaskTechnology));
        /*}else{
            Long Id=cTaskTechnology.getId();
            cTaskTechnologyService.deleteCTaskTechnologyById(Id);
            return toAjax(cTaskTechnologyService.updateCTaskTechnologychehui(cTaskTechnology));

        }*/
    }

    /**
     * 修改保存科技成果信息
     */
    @RequiresPermissions("achievements:technologyassess:edit")
    @Log(title = "科技成果信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskTechnology cTaskTechnology)
    {
        return toAjax(cTaskTechnologyService.updateCTaskTechnology(cTaskTechnology));
    }

    /**
     * 删除科技成果信息
     */
    @RequiresPermissions("achievements:technologyassess:remove")
    @Log(title = "科技成果信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cTaskTechnologyService.deleteCTaskTechnologyByIds(ids));
    }


}
