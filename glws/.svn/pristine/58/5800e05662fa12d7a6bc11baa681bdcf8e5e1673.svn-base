package com.ruoyi.zaihai.caiji.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CGroundSurvey;
import com.ruoyi.zaihai.caiji.service.ICGroundSurveyService;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.domain.InitialPreviewConfig;
import com.ruoyi.zaihai.common.service.ICResourceService;
import com.ruoyi.zaihai.enums.FlowType;
import com.ruoyi.zaihai.workflow.domain.FlowIdea;
import com.ruoyi.zaihai.workflow.domain.FlowRead;
import com.ruoyi.zaihai.workflow.domain.FlowWrite;
import com.ruoyi.zaihai.workflow.service.IFlowReadService;
import com.ruoyi.zaihai.workflow.service.IFlowWriteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/caiji/surveyassess")
public class CGroundSurveyAssessController extends BaseController
{
    private String prefix = "caiji/dimian";
    @Autowired
    private ICResourceService CResourceService;
    @Autowired
    private ICGroundSurveyService cGroundSurveyService;
    @Autowired
    private IFlowWriteService iFlowWriteService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private IFlowReadService iFlowReadService;
    @RequiresPermissions("caiji:surveyassess:view")
    @GetMapping()
    public String survey()
    {
        return prefix + "/surveyassess";
    }

    /**
     * 查询地面调查数据待审核列表
     */
    @RequiresPermissions("caiji:surveyassess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo listWait(FlowWrite FlowWrite)
    {
        startPage();
        SysUser currentUser = ShiroUtils.getSysUser();
        List<FlowWrite> list = iFlowWriteService.selectFlowWriteListdai(String.valueOf(currentUser.getUserId()));

        return getDataTable(list);
    }
    /**
     * 查询地面调查数据已审核列表
     */
    @RequiresPermissions("caiji:surveyassess:list")
    @PostMapping("/listre")
    @ResponseBody
    public TableDataInfo listRead(FlowRead flowRead)
    {
        startPage();

        SysUser currentUser = ShiroUtils.getSysUser();
        List<FlowRead> list = iFlowReadService.selectFlowReadListRe(String.valueOf(currentUser.getUserId()));

        return getDataTable(list);
    }
    /**
     * 导出地面调查数据列表
     */
    @RequiresPermissions("caiji:surveyassess:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CGroundSurvey cGroundSurvey)
    {
        List<CGroundSurvey> list = cGroundSurveyService.selectCGroundSurveyList(cGroundSurvey);
        ExcelUtil<CGroundSurvey> util = new ExcelUtil<CGroundSurvey>(CGroundSurvey.class);
        return util.exportExcel(list, "surveyassess");
    }

    /**
     * 新增地面调查数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    @GetMapping("/add1")
    public String add1()
    {
        return prefix + "/add1";
    }
    /**
     * 取样图片跳转
     */
    @GetMapping("/upload")
    public String qyphoto()
    {
        return prefix + "/upload";
    }
    /**
     * 新增保存地面调查数据
     */
    @RequiresPermissions("caiji:surveyassess:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CGroundSurvey cGroundSurvey)
    {
        return toAjax(cGroundSurveyService.insertCGroundSurvey(cGroundSurvey));
    }

    /**
     * 修改地面调查数据
     */
    @GetMapping("/edit/{groundId}")
    public String edit(@PathVariable("groundId") Long groundId, ModelMap mmap)
    {
        CGroundSurvey cGroundSurvey = cGroundSurveyService.selectCGroundSurveyById(groundId);
        mmap.put("cGroundSurvey", cGroundSurvey);
        return prefix + "/edit";
    }

    /**
     * 修改保存地面调查数据
     */
    @RequiresPermissions("caiji:surveyassess:edit")
    @Log(title = "地面调查数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CGroundSurvey cGroundSurvey)
    {
        return toAjax(cGroundSurveyService.updateCGroundSurvey(cGroundSurvey));
    }

    /**
     * 删除地面调查数据
     */
    @RequiresPermissions("caiji:surveyassess:remove")
    @Log(title = "地面调查数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cGroundSurveyService.deleteCGroundSurveyByIds(ids));
    }


    /**
     * 审核页面跳转
     */
    @GetMapping("/check/{groundId}")
    public String check(@PathVariable("groundId") String groundId, ModelMap mmap)
    {
        FlowWrite flowWrite=iFlowWriteService.selectFlowWriteById(groundId);
        CGroundSurvey cGroundSurvey = cGroundSurveyService.selectCGroundSurveyById(Long.valueOf(flowWrite.getRecordid()));
        if(cGroundSurvey.getFlowIdea()!=null){
            cGroundSurvey.getFlowIdea().setUniqueid(groundId);
        }else{
            FlowIdea flowIdea=new FlowIdea();
            flowIdea.setUniqueid(groundId);
            cGroundSurvey.setFlowIdea(flowIdea);
        }

        String ids=cGroundSurvey.getPhotoUrl();
        List<String> initialPreview= new ArrayList<>();
        List<InitialPreviewConfig> initialPreviewConfig= new ArrayList<>();
        if(StringUtils.isNotEmpty(ids)){
            if(ids.contains(",")){
                String[]  idArry=ids.split(",");
                for(int i=0;i<idArry.length;i++){
                    if(StringUtils.isNotEmpty(idArry[i])){
                        CResource cResource  =CResourceService.selectCResourceById(Long.parseLong(idArry[i]));
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
                CResource  cResource  =CResourceService.selectCResourceById(Long.parseLong(ids));
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
        mmap.put("cGroundSurvey", cGroundSurvey);
        return prefix + "/check";
    }

    /**
     * 审核数据详情页面跳转
     */
    @GetMapping("/detailAll")
    public String detailAll(@RequestParam("groundId") Long groundIds, ModelMap mmap)
    {
         String URL="";;
        String staute="1";
          if(staute== FlowType.DMTC.getStatus()){
              CGroundSurvey cGroundSurvey = cGroundSurveyService.selectCGroundSurveyById(groundIds);
              String ids=cGroundSurvey.getPhotoUrl();
              List<String> initialPreview= new ArrayList<>();
              List<InitialPreviewConfig> initialPreviewConfig= new ArrayList<>();
              if(StringUtils.isNotEmpty(ids)){
                  if(ids.contains(",")){
                      String[]  idArry=ids.split(",");
                      for(int i=0;i<idArry.length;i++){
                          if(StringUtils.isNotEmpty(idArry[i])){
                              CResource cResource  =CResourceService.selectCResourceById(Long.parseLong(idArry[i]));
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
                      CResource  cResource  =CResourceService.selectCResourceById(Long.parseLong(ids));
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
              mmap.put("cGroundSurvey", cGroundSurvey);
              URL="caiji/dimian/detail";
          }else if(staute== FlowType.SHFZ.getStatus()){

          }else if(staute== FlowType.CHFZ.getStatus()){

          }else if(staute== FlowType.NYSY.getStatus()){

          }else if(staute== FlowType.FZNB.getStatus()){

          }else{
              URL="error/404";
          }

        return URL;
    }

}
