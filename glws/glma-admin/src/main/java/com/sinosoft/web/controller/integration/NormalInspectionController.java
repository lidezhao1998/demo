package com.sinosoft.web.controller.integration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.NormalInspection;
import com.sinosoft.integration.service.NormalInspectionService;
import com.sinosoft.system.domain.CResource;
import com.sinosoft.system.domain.InitialPreviewConfig;
import com.sinosoft.system.service.ICResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eclipse.jetty.server.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 14:45
 * 常规监测数据
 */
@Controller
@RequestMapping("/integration/normalInspection")
public class NormalInspectionController extends BaseController {
    @Autowired
    NormalInspectionService normalInspectionService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ICResourceService iCResourceService;

    private String prefix = "integration/normalInspection";
    @GetMapping
    public String normalInspection(){
        return prefix+"/normalInspection";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NormalInspection normalInspection)
    {
        startPage();

        List<NormalInspection> list = normalInspectionService.selectNormalInspectionList(normalInspection);
        return getDataTable(list);
    }


    /**
     * 新增常规监测数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存常规监测数据
     */
    @Log(title = "非工程样地", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NormalInspection normalInspection)
    {
//        if (file == null || file.isEmpty()) {
//            return AjaxResult.error("照片不能为空");
//        }
//
//        String filePath = new File("D:/logs_app").getAbsolutePath();
//        File fileUpload = new File(filePath);
//        if (!fileUpload.exists()) {
//            fileUpload.mkdirs();
//        }
//
//        fileUpload = new File(filePath, file.getOriginalFilename());
//        if (fileUpload.exists()) {
//            return AjaxResult.error("上传的日志文件已存在");
//        }
//        try {
//            file.transferTo(fileUpload);
//        } catch (IOException e) {
//            return AjaxResult.error("上传日志文件到服务器失败");
//        }

        normalInspection.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(normalInspectionService.insertNormalInspection(normalInspection));
    }

    /**
     * 跳转修改常规监测数据页面
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, ModelMap mmap)
    {
        NormalInspection normalInspection=new NormalInspection();
        normalInspection.setId(id);
        normalInspection=normalInspectionService.selectNormalInspection(normalInspection);
        String ids = normalInspection.getPhotoUrl();
        List<String> initialPreview= new ArrayList<>();
        List<InitialPreviewConfig> initialPreviewConfig= new ArrayList<>();
        if(StringUtils.isNotEmpty(ids)){
            if(ids.contains(",")){
                String[]  idArry=ids.split(",");
                for(int i=0;i<idArry.length;i++){
                    if(StringUtils.isNotEmpty(idArry[i])){
                        CResource  cResource  =iCResourceService.selectCResourceById(Long.parseLong(idArry[i]));
                        if(cResource!=null){
                            InitialPreviewConfig initialPreviewCon=new InitialPreviewConfig();
                            initialPreview.add(cResource.getUrl());
                            initialPreviewCon.setCaption(cResource.getFilename());
                            initialPreviewCon.setSize(cResource.getSize());
                            initialPreviewCon.setKey(cResource.getId());
                            initialPreviewConfig.add(initialPreviewCon);
                        }
                    }
                }

            }else{
                CResource  cResource  =iCResourceService.selectCResourceById(Long.parseLong(ids));
                InitialPreviewConfig initialPreviewCon=new InitialPreviewConfig();
                if(cResource!=null){
                    initialPreview.add(cResource.getUrl());
                    initialPreviewCon.setCaption(cResource.getFilename());
                    initialPreviewCon.setSize(cResource.getSize());
                    initialPreviewCon.setKey(cResource.getId());
                    initialPreviewConfig.add(initialPreviewCon);
                }
            }
        }

        mmap.put("initialPreview", JSONArray.parseArray(JSON.toJSONString(initialPreview)));
        mmap.put("initialPreviewConfig",JSONArray.parseArray(JSON.toJSONString(initialPreviewConfig)));
        mmap.put("normalInspection",normalInspection);
        mmap.put("imgUrl", normalInspection.getPhotoName());
        return prefix + "/edit";
    }
    /**
     * 修改常规监测数据
     */
    @Log(title = "常规监测数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave( NormalInspection normalInspection)
    {
        normalInspection.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(normalInspectionService.updateNormalInspection(normalInspection));
    }


    /**
     * 删除参数配置
     */
    @Log(title = "常规监测数据", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(normalInspectionService.deleteNormalInspectionByIds(ids));
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, ModelMap mmap)
    {
        NormalInspection normalInspection = new NormalInspection();
        normalInspection.setId(id);
        mmap.put("entity", normalInspectionService.selectNormalInspection(normalInspection));
        return prefix + "/detail";
    }


    @Log(title = "常规监测数据审核", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(NormalInspection entiy,int id){
        entiy.setId(id);
        entiy.setStatus(1);
        return toAjax(normalInspectionService.updateNormalInspection(entiy));
    }


    @Log(title = "常规监测数据审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditAll")
    @ResponseBody
    public AjaxResult auditAll(String ids){
        String[] id=ids.split(",");
        return toAjax(normalInspectionService.updateStatusByIds(id));
    }

    @Log(title = "常规监测数据取消审核", businessType = BusinessType.UPDATE)
    @PostMapping("/unaudit")
    @ResponseBody
    public AjaxResult unaudit(NormalInspection entiy,int id) {
        entiy.setId(id);
        entiy.setStatus(0);
        return toAjax(normalInspectionService.updateNormalInspection(entiy));
    }

    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("photo")MultipartFile file) throws Exception
    {
        try
        {
            Long len = file.getSize();
            String realFileName = file.getOriginalFilename();
            realFileName=realFileName.substring(realFileName.length()-4);
//            String newRealFileName = codeId+realFileName;
            // 上传文件路径
            String filePath = Global.getUploadPath();
            System.out.println("一次"+1111);
            // 上传并返回新文件名称
            String fileUrl = FileUploadUtils.upload(filePath, file);
            Integer ci=indexOfStr(fileUrl,"/",3);
            String fileNamenew=fileUrl.substring(ci);
            String wuUrl=filePath+fileNamenew;
            String url = serverConfig.getUrl() + fileUrl;
            CResource CResource=new CResource();
            CResource.setUrl(fileUrl);
            CResource.setFileurl(wuUrl);
            CResource.setSize(len);
            CResource.setFilename(realFileName);
            int sum=iCResourceService.insertCResource(CResource);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("initialPreview", fileUrl);
            ajax.put("url", url);
            ajax.put("id", CResource.getId());
            ajax.put("size", len);
            ajax.put("filename", realFileName);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
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

    /**
     * 通用删除请求附件（图片）
     *
     * @param id 文件名称
     */
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult fileDelete(@RequestParam("key")String id) {
        try {
            if (!StringUtils.isNotEmpty(id)) {
                throw new Exception(StringUtils.format("id为空不允许删除。 ", id));
            }
            CResource CResource=iCResourceService.selectCResourceById(Long.parseLong(id));
            String url =CResource.getFileurl();
            boolean result=false;
            int sum=0;
            if(StringUtils.isNotEmpty(url)){
                result=FileUtils.deleteFile(url);
                if(result){
                    sum=iCResourceService.deleteCResourceById(Long.parseLong(id));
                }
            }
            if(!result||sum<0){
                AjaxResult ajax = AjaxResult.error();
                return ajax;
            }else {
                AjaxResult ajax = AjaxResult.success();

                return ajax;
            }

        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
