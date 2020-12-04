package com.sinosoft.web.controller.integration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.NprojectSampleData;
import com.sinosoft.integration.service.NprojectSampleDataService;
import com.sinosoft.system.domain.CResource;
import com.sinosoft.system.domain.InitialPreviewConfig;
import com.sinosoft.system.service.ICResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 14:45
 * 非工程样地数据表
 */
@Controller
@RequestMapping("/integration/nprojectSampleData")
public class NprojectSampleDataController extends BaseController {
    @Autowired
    NprojectSampleDataService nprojectSampleDataService;
    @Autowired
    private ICResourceService iCResourceService;

    private String prefix = "integration/nprojectSampleData";
    @GetMapping
    public String NprojectSampleData(){
        return "integration/integrationList";
    }


    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NprojectSampleData nprojectSampleData)
    {
        startPage();
        List<NprojectSampleData> list = nprojectSampleDataService.selectNprojectSampleDataList(nprojectSampleData);
        return getDataTable(list);
    }

    /**
     * 新增非工程样地
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存非工程样地
     */
    @RequiresPermissions("integration:nprojectsampledata:add")
    @Log(title = "非工程样地", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NprojectSampleData nprojectSampleData)
    {

        nprojectSampleData.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(nprojectSampleDataService.insertNprojectSampleData(nprojectSampleData));
    }

    /**
     * 跳转修改非工程样地数据表页面
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, ModelMap mmap)
    {
        NprojectSampleData nprojectSampleData=new NprojectSampleData();
        nprojectSampleData.setId(id);
        nprojectSampleData=nprojectSampleDataService.selectNprojectSampleData(nprojectSampleData);
        String ids = nprojectSampleData.getPhotoUrl();
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
                CResource cResource  =iCResourceService.selectCResourceById(Long.parseLong(ids));
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
        mmap.put("nprojectSampleData",nprojectSampleData );
        mmap.put("imgUrl", nprojectSampleData.getPhotoName());
        return prefix + "/edit";
    }
    /**
     * 跳转修改非工程样地数据表页面
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, ModelMap mmap)
    {
        NprojectSampleData nprojectSampleData=new NprojectSampleData();
        nprojectSampleData.setId(id);
        nprojectSampleData=nprojectSampleDataService.selectNprojectSampleData(nprojectSampleData);
        String ids = nprojectSampleData.getPhotoUrl();
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
                CResource cResource  =iCResourceService.selectCResourceById(Long.parseLong(ids));
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
        mmap.put("nprojectSampleData",nprojectSampleData );
        mmap.put("imgUrl", nprojectSampleData.getPhotoName());
        return prefix + "/detail";
    }
    /**
     * 修改非工程样地数据表
     */
    @RequiresPermissions("integration:nprojectsampledata:edit")
    @Log(title = "非工程样地数据表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated NprojectSampleData nprojectSampleData)
    {


        nprojectSampleData.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(nprojectSampleDataService.updateNprojectSampleData(nprojectSampleData));
    }

    /**
     * 删除参数配置
     */
    @Log(title = "非工程样地数据表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(nprojectSampleDataService.deleteNprojectSampleDataByIds(ids));
    }

    @Log(title = "非工程样地数据审核", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(NprojectSampleData entiy,int id){
        entiy.setId(id);
        entiy.setStatus(1);
        return toAjax(nprojectSampleDataService.updateNprojectSampleData(entiy));
    }


    @Log(title = "非工程样地数据审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditAll")
    @ResponseBody
    public AjaxResult auditAll(String ids){
        String[] id=ids.split(",");
        return toAjax(nprojectSampleDataService.updateStatusByIds(id));
    }

    @Log(title = "非工程样地数据取消审核", businessType = BusinessType.UPDATE)
    @PostMapping("/unaudit")
    @ResponseBody
    public AjaxResult unaudit(NprojectSampleData entiy,int id) {
        entiy.setId(id);
        entiy.setStatus(0);
        return toAjax(nprojectSampleDataService.updateNprojectSampleData(entiy));
    }
}
