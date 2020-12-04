package com.ruoyi.web.controller.eceas;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.ISysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.FileResources;
import com.ruoyi.system.service.IFileResourcesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件资源Controller
 * 
 * @author hdp
 * @date 2020-08-21
 */
@Controller
@RequestMapping("/eceas/resources")
public class FileResourcesController extends BaseController
{
    private String prefix = "eceas/resources";

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private IFileResourcesService fileResourcesService;

    @RequiresPermissions("eceas:resources:view")
    @GetMapping()
    public String resources()
    {
        return prefix + "/resources";
    }

    /**
     * 查询文件资源列表
     */
    @RequiresPermissions("eceas:resources:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FileResources fileResources)
    {
        startPage();
        List<FileResources> list = fileResourcesService.selectFileResourcesList(fileResources);
        return getDataTable(list);
    }

    /**
     * 导出文件资源列表
     */
    @RequiresPermissions("eceas:resources:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FileResources fileResources)
    {
        List<FileResources> list = fileResourcesService.selectFileResourcesList(fileResources);
        ExcelUtil<FileResources> util = new ExcelUtil<FileResources>(FileResources.class);
        return util.exportExcel(list, "resources");
    }

    /**
     * 新增文件资源
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文件资源
     */
    @RequiresPermissions("eceas:resources:add")
    @Log(title = "文件资源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, FileResources fileResources) throws IOException {
/*        String fileType = iSysDictDataService.selectDictValueToLabel(fileResources.getFileType());
        fileResources.setFileType(fileType);*/
        //上传文件路径
        String fileUrl = Global.getUploadPath();
        //上传并返回新文件名称
        String fileName = FileUploadUtils.upload(fileUrl,file);
//        String fileName = FileUploadUtils.upload(file);
        fileResources.setFileUrl(fileName);
        return toAjax(fileResourcesService.insertFileResources(fileResources));
    }
/*    public AjaxResult addSave(FileResources fileResources)
    {
        return toAjax(fileResourcesService.insertFileResources(fileResources));
    }*/

    /**
     * 修改文件资源
     */
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {
        FileResources fileResources = fileResourcesService.selectFileResourcesById(fileId);
        mmap.put("fileResources", fileResources);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件资源
     */
    @RequiresPermissions("eceas:resources:edit")
    @Log(title = "文件资源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FileResources fileResources)
    {
        return toAjax(fileResourcesService.updateFileResources(fileResources));
    }

    /**
     * 删除文件资源
     */
    @RequiresPermissions("eceas:resources:remove")
    @Log(title = "文件资源", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fileResourcesService.deleteFileResourcesByIds(ids));
    }
}
