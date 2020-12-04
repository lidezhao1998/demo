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
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CGroundSurvey;
import com.ruoyi.zaihai.caiji.domain.CPlot;
import com.ruoyi.zaihai.caiji.service.ICGroundSurveyService;
import com.ruoyi.zaihai.caiji.service.ICPlotService;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.domain.InitialPreviewConfig;
import com.ruoyi.zaihai.common.service.ICResourceService;
import com.ruoyi.zaihai.common.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 样地Controller
 *
 *
 * @author ruoyi
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/caiji/plot")
public class CPlotController extends BaseController
{
    private String prefix = "caiji/plot";
    private String prefix2 = "caiji/survey";


    private String prefix1 = "caiji/dimian";

    @Autowired
    private UploadFile uploadFile;

    @Autowired
    private ICPlotService cPlotService;

    @Autowired
    private ICGroundSurveyService cGroundSurveyService;
    @Autowired
    private ICResourceService CResourceService;
    @Autowired
    private ISysDictDataService iSysDictDataService;

/*
    @RequiresPermissions("caiji:plot:view")
*/
    @GetMapping()
    public String plot(ModelMap model,CPlot cPlot)
    {
        return prefix + "/plot";
    }


    /**
     * 查询列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CPlot cPlot, CGroundSurvey cGroundSurvey)
    {
        startPage();
        Long  usrId= ShiroUtils.getSysUser().getUserId();
        cPlot.setCreateBy(String.valueOf(usrId));
        List<CPlot> list = cPlotService.selectCPlotList(cPlot);
        for (int i = 0; i <list.size() ; i++) {
            CPlot cPlota = list.get(i);
            iSysDictDataService.selectDictdictCodeList(cPlota.getSamplingArea());
        }
        return getDataTable(list);

    }
    /**
     * 查询列表
     */
    @PostMapping("/list3")
    @ResponseBody
    public TableDataInfo list(Long id)
    {
        startPage();
        List<CPlot> list = cPlotService.selectCPlotGyId(id);
        return getDataTable(list);

    }

    /**
     * 查询列表新版查询
     */
    @PostMapping("/listY/{groundID}")
    @ResponseBody
    public TableDataInfo listY(@PathVariable("groundID") Long id)
    {
        startPage();
        List<CPlot> list = cPlotService.selectCPlotGyId(id);
        return getDataTable(list);

    }
    /**
     * 查看详情
     */
    /**
     * 查看地面调查数据
     */
    @GetMapping("/detail/{groundId}")
    public String detail1(@PathVariable("groundId") Long groundId, ModelMap mmap)
    {
        CGroundSurvey cGroundSurvey = cGroundSurveyService.selectCGroundSurveyById(groundId);
        String ids=cGroundSurvey.getPhotoUrl();
        List<String> initialPreview= new ArrayList<>();
        List<InitialPreviewConfig> initialPreviewConfig= new ArrayList<>();
        if(StringUtils.isNotEmpty(ids)){
            if(ids.contains(",")){
                String[]  idArry=ids.split(",");
                for(int i=0;i<idArry.length;i++){
                    if(StringUtils.isNotEmpty(idArry[i])){
                        CResource  cResource  =CResourceService.selectCResourceById(Long.parseLong(idArry[i]));
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
        return prefix1 + "/detail";
    }




    /**
     * 导出样地列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CPlot cPlot)
    {
        List<CPlot> list = cPlotService.selectCPlotList(cPlot);
        ExcelUtil<CPlot> util = new ExcelUtil<CPlot>(CPlot.class);
        return util.exportExcel(list, "plot");
    }

    /*
     *展开详情信息*/
    @GetMapping("/samplingDetail/{id}")
    public String samplingDetail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CPlot cPlot = cPlotService.selectCPlotDetailById(id);
        String url=prefix1+"/pestEditdt";
        if(cPlot.getHarmfulSpeciesType().equals("害虫")){
            url=prefix1+"/pestEditdt";
        }else if(cPlot.getHarmfulSpeciesType().equals("害鼠")){
            url=prefix1+"/verminEditdt";
        }else if(cPlot.getHarmfulSpeciesType().equals("病害")){
            url=prefix1+"/diseaseEditdt";
        }else if(cPlot.getHarmfulSpeciesType().equals("毒害草")){
            url=prefix1+"/weedEditdt";
        }
        String ids=cPlot.getPhotoUrl();
        List<String> initialPreview= new ArrayList<>();
        List<InitialPreviewConfig> initialPreviewConfig= new ArrayList<>();
        if(StringUtils.isNotEmpty(ids)){
            if(ids.contains(",")){
                String[]  idArry=ids.split(",");
                for(int i=0;i<idArry.length;i++){
                    if(StringUtils.isNotEmpty(idArry[i])){
                        CResource  cResource  =CResourceService.selectCResourceById(Long.parseLong(idArry[i]));
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
     /*   cGroundSurvey.setProvincialLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getProvincialLevelName()));
        cGroundSurvey.setCityLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCityLevelName()));
        cGroundSurvey.setCountyLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCountyLevelName()));*/
        mmap.put("initialPreview", JSONArray.parseArray(JSON.toJSONString(initialPreview)));
        mmap.put("initialPreviewConfig",JSONArray.parseArray(JSON.toJSONString(initialPreviewConfig)));
        mmap.put("cPlot",cPlot);
        return url;
    }

    /**
     * 新增样地
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存样地
     * @return
     */
    @Log(title = "样地", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CPlot cPlot, ModelMap model, Long plotId){
       String HarmfulSpeciesType=cPlot.getHarmfulSpeciesType();
        List<CPlot> cPlotList =cPlotService.selectCPlotGyIdCount(cPlot.getGroundId());
        int jishu=1;
        int num=0;
        if(cPlotList!=null&&cPlotList.size()>0){
            num=Integer.valueOf(cPlotList.get(0).getSamplingNumber().substring(3,4));
            jishu=num+1;
        }
        if(HarmfulSpeciesType.equals("001")){
            cPlot.setSamplingNumber("C_0"+jishu);
        }else if(HarmfulSpeciesType.equals("002")){
            cPlot.setSamplingNumber("S_0"+jishu);
        }else if(HarmfulSpeciesType.equals("003")){
            cPlot.setSamplingNumber("B_0"+jishu);
        }else if(HarmfulSpeciesType.equals("004")){
            cPlot.setSamplingNumber("D_0"+jishu);
        }else{
            cPlot.setSamplingNumber("Z_0"+jishu);
        }
        int  sum =cPlotService.insertCPlot(cPlot);

            return toAjax(sum);
    }

    /**
     * 新增保存样地新版
     * @return
     */
    @Log(title = "样地", businessType = BusinessType.INSERT)
    @PostMapping("/addY")
    @ResponseBody
    public AjaxResult addSaveY(CPlot cPlot, @RequestParam("file") MultipartFile[] files ){
        AjaxResult ajax = AjaxResult.success();
        String HarmfulSpeciesType=cPlot.getHarmfulSpeciesType();
        List<CPlot> cPlotList =cPlotService.selectCPlotGyIdCount(cPlot.getGroundId());
        CGroundSurvey cGroundSurvey= cGroundSurveyService.selectCGroundSurveyById(cPlot.getGroundId());
        int jishu=1;
        int num=0;


        if(cPlotList!=null&&cPlotList.size()>0){
            num=Integer.valueOf(cPlotList.get(0).getSamplingNumber().substring(3,4));
            jishu=num+1;
        }
        String Tpid="";

        if(HarmfulSpeciesType.equals("001")){
            Tpid="C_0"+jishu;
            cPlot.setSamplingNumber("C_0"+jishu);
        }else if(HarmfulSpeciesType.equals("002")){
            Tpid="S_0"+jishu;
            cPlot.setSamplingNumber("S_0"+jishu);
        }else if(HarmfulSpeciesType.equals("003")){
            Tpid="B_0"+jishu;
            cPlot.setSamplingNumber("B_0"+jishu);
        }else if(HarmfulSpeciesType.equals("004")){
            Tpid="D_0"+jishu;
            cPlot.setSamplingNumber("D_0"+jishu);
        }else{
            Tpid="Z_0"+jishu;
            cPlot.setSamplingNumber("Z_0"+jishu);
        }
        int  sum =cPlotService.insertCPlot(cPlot);
        if(cGroundSurvey!=null){
            Tpid=cGroundSurvey.getCodeId()+"-"+Tpid;
        }
        if(sum>0){
           String  CRids=uploadFile.upload(files,Tpid,0,ajax);
            if(StringUtils.isNotEmpty(CRids)){
                cPlot.setPhotoUrl(CRids);
                int  sum1 =cPlotService.updateCPlot(cPlot);
                if(sum1<0){
                    ajax=AjaxResult.error();
                }
            }
        }
        return ajax;
    }

  /*  *//**
     * 修改样地
     *//*
    @GetMapping("/edit/{plotId}")
    public String edit(@PathVariable("plotId") Long plotId, ModelMap mmap)
    {
        int cPlot = cPlotService.selectCPlotById(plotId);
        mmap.put("cPlot", cPlot);
        return prefix + "/edit";
    }*/

       /* */
    /**
         * 修改样地
         */
    @GetMapping("/edit/{plotId}")
    public String edit(@PathVariable("plotId") Long plotId, ModelMap mmap)
    {   String url=prefix1+"/pestEdit";
        CPlot cPlot = cPlotService.selectCPlotById(plotId);

        if(cPlot.getHarmfulSpeciesType().equals("害虫")){
            url=prefix1+"/pestEdit";
        }else if(cPlot.getHarmfulSpeciesType().equals("害鼠")){
            url=prefix1+"/verminEdit";
        }else if(cPlot.getHarmfulSpeciesType().equals("病害")){
            url=prefix1+"/diseaseEdit";
        }else if(cPlot.getHarmfulSpeciesType().equals("毒害草")){
            url=prefix1+"/weedEdit";
        }
        String ids=cPlot.getPhotoUrl();
        List<String> initialPreview= new ArrayList<>();
        List<InitialPreviewConfig> initialPreviewConfig= new ArrayList<>();
        if(StringUtils.isNotEmpty(ids)){
            if(ids.contains(",")){
                String[]  idArry=ids.split(",");
                for(int i=0;i<idArry.length;i++){
                    if(StringUtils.isNotEmpty(idArry[i])){
                        CResource  cResource  =CResourceService.selectCResourceById(Long.parseLong(idArry[i]));
                        if(cResource!=null){
                            InitialPreviewConfig initialPreviewCon=new InitialPreviewConfig();
                            initialPreview.add(cResource.getUrl().replace("\"", ""));
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
                    initialPreview.add(cResource.getUrl().replace("\"", ""));
                    initialPreviewCon.setCaption(cResource.getFilename());
                    initialPreviewCon.setSize(cResource.getSize());
                    initialPreviewCon.setKey(cResource.getId());
                    initialPreviewConfig.add(initialPreviewCon);
                }
            }
        }
     /*   cGroundSurvey.setProvincialLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getProvincialLevelName()));
        cGroundSurvey.setCityLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCityLevelName()));
        cGroundSurvey.setCountyLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCountyLevelName()));*/
        mmap.put("initialPreview",JSONArray.parseArray(JSON.toJSONString(initialPreview)));
        mmap.put("initialPreviewConfig",JSONArray.parseArray(JSON.toJSONString(initialPreviewConfig)));
        mmap.put("cPlot", cPlot);
        return url;
    }

    /**
     * 修改保存样地
     */
    @Log(title = "样地", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CPlot cPlot)
    {
        return toAjax(cPlotService.updateCPlot(cPlot));
    }
    /**
     * 修改保存样地新版
     */
    @Log(title = "样地", businessType = BusinessType.UPDATE)
    @PostMapping("/editY")
    @ResponseBody
    public AjaxResult editSaveY(CPlot cPlot, @RequestParam("file") MultipartFile[] files)
    {
        AjaxResult ajax = AjaxResult.success();
        CGroundSurvey cGroundSurvey= cGroundSurveyService.selectCGroundSurveyById(cPlot.getGroundId());
        String Tpid="";
        int  sum=cPlotService.updateCPlot(cPlot);
        String PhotoUrl=cPlot.getPhotoUrl();
        int sumArr=0;//记录已储存图片张数；
        if(StringUtils.isNotEmpty(PhotoUrl)){
            String[]  PhotoUrlArr=PhotoUrl.split(",");
            if(PhotoUrlArr!=null){
                sumArr=PhotoUrlArr.length;
            }

        }

        if(sum>0){
            Tpid=cPlot.getSamplingNumber();
            if(cGroundSurvey!=null){
                Tpid=cGroundSurvey.getCodeId()+"-"+Tpid;
            }
            String  CRids=uploadFile.upload(files,Tpid,sumArr,ajax);
            if(StringUtils.isNotEmpty(CRids)){
                CRids=cPlot.getPhotoUrl()+","+CRids;
                cPlot.setPhotoUrl(CRids);
                int  sum1 =cPlotService.updateCPlot(cPlot);
                if(sum1<0){
                    ajax=AjaxResult.error();
                }
            }
        }
        return ajax;
    }




    /**
     * 删除样地
     */
    @Log(title = "样地", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
       int sum=cPlotService.deleteCPlotByIds(ids);
        return toAjax(sum);
    }


   /* @GetMapping("/editd")
    public String editd()
    {
        return prefix1 + "/editd";
    }


    @GetMapping("/edits")
    public String edits()
    {
        return prefix1 + "/edits";
    }


    @GetMapping("/editc")
    public String editc()
    {
        return prefix1 + "/editc";
    }


    @GetMapping("/editb")
    public String editb()
    {
        return prefix1 + "/editb";
    }*/
   @GetMapping("/addY/{groundID}")
   public String addY(@PathVariable("groundID") Long groundID, ModelMap mmap)
   {
       CPlot cPlot=new CPlot();
       cPlot.setGroundId(groundID);
       mmap.put("cplot",cPlot);
       mmap.put("groundID",groundID);
       return prefix1 + "/addQY";
   }
}

