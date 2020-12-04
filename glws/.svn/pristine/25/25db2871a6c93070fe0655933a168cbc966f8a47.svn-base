package com.ruoyi.zaihai.caiji.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.zaihai.caiji.domain.CTaskTechnology;
import com.ruoyi.zaihai.caiji.service.ICTaskTechnologyService;
import com.ruoyi.zaihai.caiji.util.ResourceUntil;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.service.ICResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

/**
 * 【科技成果Controller
 *
 * @author ruoyi
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/achievements/technology")

public class CTaskTechnologyController extends BaseController
{
    private String prefix = "system/technology";

    @Autowired
    private ICTaskTechnologyService cTaskTechnologyService;
    @Autowired
    private ICResourceService CResourceService;
    @Autowired
    private ServerConfig serverConfig;
    @RequiresPermissions("achievements:technology:view")
    @GetMapping()
    public String technology()
    {
        return prefix + "/technology";
    }

    /**
     * 查询科技成果信息列表
     */
    @RequiresPermissions("achievements:technology:list")
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
    @RequiresPermissions("achievements:technology:detail")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskTechnology cTaskTechnology = cTaskTechnologyService.selectCTaskTechnologyById(id);
        mmap.put("cTaskTechnology",cTaskTechnology);
        return prefix + "/detail";
    }

    /*
     *上报信息*/

    @GetMapping("/report/{id}")
    public String report(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskTechnology cTaskTechnology = cTaskTechnologyService.selectCTaskTechnologyById(id);
        cTaskTechnology.setReportTime(DateUtils.getNowDate());
        if(cTaskTechnology.getStatus().equals("1")){
            cTaskTechnology.setStatus("已上报");
        }else{
            cTaskTechnology.setStatus("未上报");

        }
        mmap.put("cTaskTechnology",cTaskTechnology);
        return prefix + "/report";
    }


    /**
     * 导出科技成果信息列表
     */
    @RequiresPermissions("achievements:technology:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskTechnology cTaskTechnology)
    {
        List<CTaskTechnology> list = cTaskTechnologyService.selectCTaskTechnologyList(cTaskTechnology);
        ExcelUtil<CTaskTechnology> util = new ExcelUtil<CTaskTechnology>(CTaskTechnology.class);
        return util.exportExcel(list, "technology");
    }

    /**
     * 新增科技成果
     */
    @GetMapping("/add")
    @RequiresPermissions("achievements:technology:add")
    public String add()
    {
        return prefix + "/add";
    }



    /**
     * 新增保存科技成果信息
     */
    @RequiresPermissions("achievements:technology:add")
    @Log(title = "科技成果信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskTechnology cTaskTechnology, @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {

            if(file!=null){
            Long len = file.getSize();
            String realFileName = file.getOriginalFilename();
            // 上传文件路径
            String filePath = Global.getUploadPath();
            System.out.println("一次" + 1111);
            // 上传并返回新文件名称
            String fileUrl = FileUploadUtils.upload(filePath, file);
            Integer ci = indexOfStr(fileUrl, "/", 3);
            String fileNamenew = fileUrl.substring(ci);
            String wuUrl = filePath + fileNamenew;
            CResource CResource = new CResource();
            CResource.setUrl(fileUrl);
            CResource.setFileurl(wuUrl);
            CResource.setSize(len);
            CResource.setFilename(realFileName);
            CResourceService.insertCResource(CResource);
            cTaskTechnology.setFileUrl(CResource.getId()+"");
            cTaskTechnology.setFileName(CResource.getFilename());
            }
            //上报时间
            if(cTaskTechnology.getStatus()=="1"){
                cTaskTechnology.setReportTime(new Date());
            }
            return toAjax(cTaskTechnologyService.insertCTaskTechnology(cTaskTechnology));


    }

    /**
     * 政策库附件下载
     */
    @RequiresPermissions("achievements:technology:download")
    @GetMapping("/download/{fileId}")
    public void resourceDownload(@PathVariable("fileId") Long fileId, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CResource cResource = CResourceService.selectCResourceById(fileId);
        ResourceUntil.download(cResource,request,response);
    }

    /**
     * 修改科技成果信息
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("achievements:technology:edit")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("cTaskTechnology", cTaskTechnologyService.selectCTaskTechnologyById(id));
        return prefix + "/edit";
    }

    /**
     * 修改保存科技成果信息
     */
    @RequiresPermissions("achievements:technology:edit")
    @Log(title = "科技成果信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskTechnology cTaskTechnology, @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {

        if(file!=null){//文件存在
            Long len = file.getSize();
            String realFileName = file.getOriginalFilename();
            // 上传文件路径
            String filePath = Global.getUploadPath();
            System.out.println("一次" + 1111);
            // 上传并返回新文件名称
            String fileUrl = FileUploadUtils.upload(filePath, file);
            Integer ci = indexOfStr(fileUrl, "/", 3);
            String fileNamenew = fileUrl.substring(ci);
            String wuUrl = filePath + fileNamenew;
            CResource CResource = new CResource();
            CResource.setUrl(fileUrl);
            CResource.setFileurl(wuUrl);
            CResource.setSize(len);
            CResource.setFilename(realFileName);
            CResourceService.insertCResource(CResource);
            cTaskTechnology.setFileUrl(CResource.getId()+"");
            cTaskTechnology.setFileName(CResource.getFilename());
            //上报时间
            if(cTaskTechnology.getStatus()=="1"){
                cTaskTechnology.setReportTime(new Date());
            }
        }

        return toAjax(cTaskTechnologyService.updateCTaskTechnology(cTaskTechnology));

    }

    /**
     * 删除科技成果信息
     */
    @RequiresPermissions("achievements:technology:remove")
    @Log(title = "科技成果信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids )
    {

        return toAjax(cTaskTechnologyService.deleteCTaskTechnologyByIds(ids));
    }


    public static Integer indexOfStr(String str,String findStr, int index){
        Pattern pattern = Pattern.compile(findStr);
        Matcher findMatcher = pattern.matcher(str);
        int number = 0;
        while(findMatcher.find()) {
            number++;
            if(number == index){
                break;
            }
        }
        try {
            return findMatcher.start();
        } catch (Exception e) {
            return null;
        }
    }

}
