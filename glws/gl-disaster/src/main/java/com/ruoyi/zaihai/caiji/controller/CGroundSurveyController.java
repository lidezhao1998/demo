package com.ruoyi.zaihai.caiji.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.gis.EntCoordSyncJob;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.gis.GisMap;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.zaihai.caiji.domain.CGroundSurvey;
import com.ruoyi.zaihai.caiji.domain.CPlot;
import com.ruoyi.zaihai.caiji.domain.RegionCode;
import com.ruoyi.zaihai.caiji.service.ICGroundSurveyService;
import com.ruoyi.zaihai.caiji.service.ICPlotService;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.domain.InitialPreviewConfig;
import com.ruoyi.zaihai.common.service.ICResourceService;
import com.ruoyi.zaihai.common.utils.GenerateUUID;
import com.ruoyi.zaihai.enums.FlowStatus;
import com.ruoyi.zaihai.enums.FlowType;
import com.ruoyi.zaihai.enums.IdeaType;
import com.ruoyi.zaihai.workflow.domain.FlowIdea;
import com.ruoyi.zaihai.workflow.domain.FlowRead;
import com.ruoyi.zaihai.workflow.domain.FlowWflog;
import com.ruoyi.zaihai.workflow.domain.FlowWrite;
import com.ruoyi.zaihai.workflow.service.IFlowIdeaService;
import com.ruoyi.zaihai.workflow.service.IFlowReadService;
import com.ruoyi.zaihai.workflow.service.IFlowWflogService;
import com.ruoyi.zaihai.workflow.service.IFlowWriteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 地面调查数据Controller
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/caiji/survey")
public class CGroundSurveyController extends BaseController
{
    private String prefix = "caiji/dimian";
    private String prefix1 = "system/plot";

    @Autowired
    private ICGroundSurveyService cGroundSurveyService;

    @Autowired
    private ICResourceService CResourceService;

    @Autowired
    private ICPlotService CPlotService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private DictService dictService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysRoleService iSysRoleService;


    @Autowired
    private IFlowWriteService iFlowWriteService;

    @Autowired
    private IFlowReadService iFlowReadService;
    @Autowired
    private IFlowIdeaService ideaService;

    @Autowired
    private ICPlotService cPlotService;

    @Autowired
    private IFlowIdeaService iFlowIdeaService;

    @Autowired
    private IFlowWflogService iFlowWflogService;


    @RequiresPermissions("caiji:survey:view")
    @GetMapping()
    public String survey()
    {
        return prefix + "/survey";
    }


    @RequiresPermissions("caiji:survey:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo listTwo(CGroundSurvey cGroundSurvey)
    {
        startPage();
        Long  usrId= ShiroUtils.getSysUser().getUserId();
        cGroundSurvey.setCreateBy(String.valueOf(usrId));

        List<CGroundSurvey> list = cGroundSurveyService.selectCGroundSurveyList(cGroundSurvey);
        for (int i = 0; i < list.size(); i++) {
            CGroundSurvey groundSurvey =  list.get(i);
            /*根据省市区编码显示省市区数据*/
            String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(groundSurvey.getProvincialLevelName());
            String addr = addrProvinceLabel.substring(addrProvinceLabel.length()-1,addrProvinceLabel.length());
            if(addr.equals("市")){
                String addrCityLabel = iSysDictDataService.selectDictValueToLabels(groundSurvey.getCityLevelName());
                String addrAreaLabel = iSysDictDataService.selectDictValueToLabels(groundSurvey.getCountyLevelName());
                groundSurvey.setProvincialLevelName(addrProvinceLabel);
                groundSurvey.setCityLevelName(addrCityLabel);
                groundSurvey.setCountyLevelName(addrAreaLabel);
            }else{
                String addrCityLabel = iSysDictDataService.selectDictValueToLabel(groundSurvey.getCityLevelName());
                String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(groundSurvey.getCountyLevelName());
                groundSurvey.setProvincialLevelName(addrProvinceLabel);
                groundSurvey.setCityLevelName(addrCityLabel);
                groundSurvey.setCountyLevelName(addrAreaLabel);
            }
        }

        return getDataTable(list);

    }


    /**
     * 导出地面调查数据列表
     */
    @RequiresPermissions("caiji:survey:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CGroundSurvey cGroundSurvey)
    {
        List<CGroundSurvey> list = cGroundSurveyService.selectCGroundSurveyList(cGroundSurvey);
        ExcelUtil<CGroundSurvey> util = new ExcelUtil<CGroundSurvey>(CGroundSurvey.class);
        return util.exportExcel(list, "survey");
    }

    /**
     * 新增地面调查数据
     */
    @RequiresPermissions("caiji:survey:list")
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        CGroundSurvey cGroundSurvey = new CGroundSurvey();

        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        String   code=Dept.getCode();
           SysDictData  sysDictData2=iSysDictDataService.selectDictDataById(Long.parseLong(code));
           Long code1=Long.parseLong(sysDictData2.getDictParent());
        SysDictData  sysDictData1=iSysDictDataService.selectDictDataById(code1);
        cGroundSurvey.setProvincialLevelName(sysDictData1.getDictParent());
        cGroundSurvey.setCityLevelName(sysDictData2.getDictParent());
        cGroundSurvey.setCountyLevelName(code);
        SysDept Deptuser =sysDeptService.selectDeptById(Dept.getDeptId());
        String ancestors=Deptuser.getAncestors();

      /*  SysDept Deptnew=new SysDept();
        //获取省级地区集合
        Deptnew.setParentId(122L);
        List<SysDept> Deptlistp =sysDeptService.selectDeptList(Deptnew);
        //获取地级地区集合
        Deptnew.setParentId(122L);
        List<SysDept> Deptlistc =sysDeptService.selectDeptList(Deptnew);
         //获取县级地区集合

        String[] ayyyancestors=ancestors.split(",");
        int ii=ayyyancestors.length;*/

        /*switch(ii){
            case 4:
                cGroundSurvey.setProvincialLevelName( Dept.getDeptName());
                break;
            case 5:
                cGroundSurvey.setProvincialLevelName(sysDeptService.selectDeptById(Dept.getParentId()).getDeptName());
                cGroundSurvey.setCityLevelName( Dept.getDeptName());
                break;
            case 6:
                SysDept deptParentId=sysDeptService.selectDeptById(Dept.getParentId());
                cGroundSurvey.setProvincialLevelName(sysDeptService.selectDeptById(deptParentId.getParentId()).getDeptName());
                cGroundSurvey.setCityLevelName( deptParentId.getDeptName());
                cGroundSurvey.setCountyLevelName( Dept.getDeptName());
                break;
            default:
                cGroundSurvey.setProvincialLevelName( Dept.getDeptName());
                break;
        }*/
        List<SysDictData> dictData=dictService.getType("di_qu_Biological_species");
        List<Map<String,String>>  dictDataS=new ArrayList<Map<String,String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        List<String>  dictDataSl=new ArrayList <String>();
        List<String>  dictDataSv=new ArrayList <String>();
        String str="";
        for (int i = 0; i < dictData.size(); i++) {
            SysDictData sysDictData = dictData.get(i);
            map1.put(sysDictData.getDictValue(),sysDictData.getDictLabel());
                    if(i==0){
                        str=sysDictData.getDictLabel();
                    }else {
                        str=str+""+sysDictData.getDictLabel();
                    }
            dictDataSl.add(sysDictData.getDictLabel());
            dictDataSv.add(sysDictData.getDictValue());
        }
        mmap.put("cGroundSurvey", cGroundSurvey);
        mmap.put("dictDatalist", dictDataSv);
        mmap.put("dictDatalists",str);
        mmap.put("dictDatalist1", map1);
        return prefix + "/add";
    }




    @GetMapping("/addque")
    public String addque(ModelMap mmap,CGroundSurvey cGroundSurvey,CPlot cPlot)
    {
        cGroundSurvey = cGroundSurveyService.selectCGroundSurveyById(cGroundSurvey.getGroundId());
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
        //获取地图id
        mmap.put("mapId",  cGroundSurvey.getMapId());
        return prefix + "/addview";
    }


    @GetMapping("/editque")
    public String editque(ModelMap mmap,CGroundSurvey cGroundSurvey)
    {
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

        return prefix + "/editview";
    }
    /**
     * 查看详情
     */
    /**
     * 查看地面调查数据
     */
    @GetMapping("/detail/{groundId}")
    public String detail(@PathVariable("groundId") Long groundId, ModelMap mmap)
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
     /*   cGroundSurvey.setProvincialLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getProvincialLevelName()));
        cGroundSurvey.setCityLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCityLevelName()));
        cGroundSurvey.setCountyLevelName(iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCountyLevelName()));*/
        mmap.put("initialPreview", JSONArray.parseArray(JSON.toJSONString(initialPreview)));
        mmap.put("initialPreviewConfig",JSONArray.parseArray(JSON.toJSONString(initialPreviewConfig)));
        mmap.put("cGroundSurvey", cGroundSurvey);
        return prefix + "/detail";
    }

    @GetMapping("/add1")
    public String add1()
    {
        return prefix + "/add1";
    }
    /*@GetMapping("/edit1")
    public String edit1()
    {
        return prefix + "/edit1";
    }*/
    /**
     * 取样图片跳转
     */
    @GetMapping("/quuploadedit")
    public String qyphotoedit()
    {
        return prefix + "/uploadedit";
    }
    /**
     * 取样图片跳转
     */
    @GetMapping("/quuploadview")
    public String qyphotoview()
    {
        return prefix + "/uploadview";
    }


    /**
     * 新增保存地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CGroundSurvey cGroundSurvey,CPlot cPlot,ModelMap mmap)
    {

        cPlot.setStatus("1");
        cGroundSurvey.setStaute(FlowStatus.DRAF.getStatus());
        List<CPlot> cPlotList=cGroundSurvey.getCPlotList();
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();
        String[] ayyyancestors=ancestors.split(",");
       long id =currentUser.getUserId();
        if(id!=0){
            cGroundSurvey.setCreateBy(String.valueOf(id));
        }

        int sum=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);

            CGroundSurvey idd=cGroundSurveyService.selectCGroundSurveyById(cGroundSurvey.getGroundId());

            CPlot cdd=cPlotService.selectCPlotById(cPlot.getPlotId());
            Long gd=idd.getGroundId();
            Long cp=cPlot.getPlotId();
            cp=gd;
            cPlot.setGroundId(cp);


            cPlot.setStatus("1");
            cPlotService.updateCPlot1(cPlot);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("groundId", cGroundSurvey.getGroundId());
        return ajax;
}

    /**
     * 新增保存地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/addx")
    @ResponseBody
    public AjaxResult addSavex(CGroundSurvey cGroundSurvey)
    {

        int sum=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("groundId", cGroundSurvey.getGroundId());
        return ajax;
    }
    /**
     * 新增保存地面调查数据2020-11-17
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/addY")
    @ResponseBody
    public AjaxResult addSaveY(CGroundSurvey cGroundSurvey,@RequestParam("file") MultipartFile[] files)
    {


        AjaxResult ajax = AjaxResult.success();
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept sysDept=currentUser.getDept();
        String  xzcode =sysDept.getCode();
        String year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        Date date=DateUtils.dateTime("yyyy-MM-dd HH:mm:ss",year+"-01-01 00:00:00");
        int count=cGroundSurveyService.countCGroundSurvey(xzcode,date);
        String countsr="";
        if(count<=999&&count>99){
            countsr="0"+count;
        }else if(count<=99&&count>9){
            countsr="00"+count;
        }else if(count<=9){
            countsr="000"+count;
        }else{
            countsr=count+"";
        }
        String code=cGroundSurvey.getCodeId()+year+xzcode+countsr;
        cGroundSurvey.setCodeId(code);
        /*cGroundSurvey.setCountyLevelName("150727");
        cGroundSurvey.setProvincialLevelName("150000");
        cGroundSurvey.setCityLevelName("150700");*/
        int sum=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
        int sumarr=0;
        String CRids="";
        int sum1=0;//图片上传完添加图片id结果
        if(sum>0){
            for (int i = 0; i < files.length; i++) {
                sumarr=sumarr+1;
                MultipartFile file=files[i];
                String fileUrl=null;
            try
            {
                    Long len = file.getSize();
                    String realFileName = file.getOriginalFilename();
                    int index=realFileName.lastIndexOf(".");
                    realFileName=cGroundSurvey.getCodeId()+"-"+sumarr+realFileName.substring(index,realFileName.length()-1);
//            String newRealFileName = codeId+realFileName;
                    // 上传文件路径
                    String filePath = Global.getUploadPath();
                    System.out.println("一次"+1111);
                    // 上传并返回新文件名称

                     fileUrl = FileUploadUtils.upload(filePath, file);
                    Integer ci=indexOfStr(fileUrl,"/",3);
                    String fileNamenew=fileUrl.substring(ci);
                    String wuUrl=filePath+fileNamenew;
                    String url = serverConfig.getUrl() + fileUrl;
                    CResource CResource=new CResource();
                    CResource.setUrl(fileUrl);
                    CResource.setFileurl(wuUrl);
                    CResource.setSize(len);
                    CResource.setFilename(realFileName);
                    int sumCr=CResourceService.insertCResource(CResource);
                    if(sumCr<=0){
                        //补充删除已上传照片，

                        return  ajax=AjaxResult.error("保存图片失败");
                    }else{
                        if(i==0){
                            CRids=CRids+(Long.toString(CResource.getId()));
                        }else{
                            CRids=CRids+","+(Long.toString(CResource.getId()));

                        }


                    }
                


            }
            catch (Exception e)
            {
                return AjaxResult.error(e.getMessage());
            }
            }

            if(CRids!=""){
                cGroundSurvey.setPhotoUrl(CRids);
                sum1=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
                if(sum1<0){
                    ajax=AjaxResult.error("保存图片失败");
                }
            }

        }else{
            ajax=AjaxResult.error();
        }


        ajax.put("groundId", cGroundSurvey.getGroundId());
        return ajax;
    }


    /**
     * 修改保存地面调查数据2020-11-17
     */
    @Transactional
    @RequiresPermissions("caiji:survey:edit")
    @PostMapping("/editY")
    @ResponseBody
    public AjaxResult editSaveY(CGroundSurvey cGroundSurvey,@RequestParam("file") MultipartFile[] files)
    {


        AjaxResult ajax = AjaxResult.success();
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept sysDept=currentUser.getDept();
        int sum=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
        String PhotoUrl=cGroundSurvey.getPhotoUrl();
        int sumArr=0;//记录已储存图片张数；
        if(StringUtils.isNotEmpty(PhotoUrl)){
           String[]  PhotoUrlArr=PhotoUrl.split(",");
           if(PhotoUrlArr!=null){
               sumArr=PhotoUrlArr.length;
           }

        }
        String CRids="";
        int sum1=0;//图片上传完添加图片id结果
        if(sum>0){
            for (int i = 0; i < files.length; i++) {
                sumArr=sumArr+1;
                MultipartFile file=files[i];
                String fileUrl=null;
                try
                {
                    Long len = file.getSize();
                    String realFileName = file.getOriginalFilename();
                    int index=realFileName.lastIndexOf(".");
                    realFileName=cGroundSurvey.getCodeId()+"-"+sumArr+realFileName.substring(index,realFileName.length()-1);
//            String newRealFileName = codeId+realFileName;
                    // 上传文件路径
                    String filePath = Global.getUploadPath();
                    System.out.println("一次"+1111);
                    // 上传并返回新文件名称

                    fileUrl = FileUploadUtils.upload(filePath, file);
                    Integer ci=indexOfStr(fileUrl,"/",3);
                    String fileNamenew=fileUrl.substring(ci);
                    String wuUrl=filePath+fileNamenew;
                    String url = serverConfig.getUrl() + fileUrl;
                    CResource CResource=new CResource();
                    CResource.setUrl(fileUrl);
                    CResource.setFileurl(wuUrl);
                    CResource.setSize(len);
                    CResource.setFilename(realFileName);
                    int sumCr=CResourceService.insertCResource(CResource);
                    if(sumCr<=0){
                        //补充删除已上传照片，

                        return  ajax=AjaxResult.error("保存图片失败");
                    }else{
                        if(i==0){
                            CRids=CRids+(Long.toString(CResource.getId()));
                        }else{
                            CRids=CRids+","+(Long.toString(CResource.getId()));

                        }


                    }



                }
                catch (Exception e)
                {
                    return AjaxResult.error(e.getMessage());
                }
            }

            if(CRids!=""){
                CRids=cGroundSurvey.getPhotoUrl()+","+CRids;
                cGroundSurvey.setPhotoUrl(CRids);
                sum1=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
                if(sum1<0){
                    ajax=AjaxResult.error("保存图片失败");
                }
            }

        }else{
            ajax=AjaxResult.error();
        }


        ajax.put("groundId", cGroundSurvey.getGroundId());
        return ajax;
    }
    /**
     * 新增保存地面调查数据2020-11-17
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/addYQ")
    @ResponseBody
    public String addSaveYQ(CGroundSurvey cGroundSurvey)
    {
        cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
        return  prefix + "/addQY";
    }
    /**
     * 修改保存地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:edit")
    @Log(title = "地面调查数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave( CGroundSurvey cGroundSurvey,CPlot cPlot)
    {


        cPlot.setStatus("1");
        cGroundSurvey.setStaute(FlowStatus.DRAF.getStatus());
        List<CPlot> cPlotList=cGroundSurvey.getCPlotList();
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();
        String[] ayyyancestors=ancestors.split(",");
        long id =currentUser.getUserId();
        if(id!=0){
            cGroundSurvey.setCreateBy(String.valueOf(id));
        }
        int sum=cGroundSurveyService.updateCGroundSurvey(cGroundSurvey);


        return toAjax(sum);
    }


    /**
     * 提交地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/add11copy")
    @ResponseBody
    public AjaxResult submit(CGroundSurvey cGroundSurvey)
    {
        cGroundSurvey.setStaute(FlowStatus.DRAF.getStatus());
        String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getProvincialLevelName());

        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCityLevelName());

        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cGroundSurvey.getCountyLevelName());

        cGroundSurvey.setProvincialLevelName(addrProvinceLabel);

        cGroundSurvey.setCityLevelName(addrCityLabel);

        cGroundSurvey.setCountyLevelName(addrAreaLabel);

        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.getDept();
        SysDept Dept =currentUser.getDept();
        //获取所有上级部门ID
        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();

        String[] ayyyancestors=ancestors.split(",");
        String deptid="";
        //获取用户id
        long id =currentUser.getUserId();

        int sum=0;
        if(ayyyancestors.length==4){
             deptid =String.valueOf(currentUser.getDeptId());
        }else{
            if(ayyyancestors.length>4){
                 deptid =  ayyyancestors[4];
            }
        }
        SysUser sysUser =new SysUser();
        sysUser.setDeptId(Long.valueOf(deptid));
        List<SysUser> sysUserList=iSysUserService.selectUserListAll(sysUser);
        List<SysUser> sysUseridnew=new ArrayList<>();
        if(StringUtils.isNotEmpty(deptid)){
            if(sysUserList!=null){
                for (int i = 0; i < sysUserList.size(); i++) {
                    List<SysRole> sysRoleList=iSysRoleService.selectRolesByUserIdAll(sysUserList.get(i).getUserId());
                    if(sysRoleList!=null&&sysRoleList.size()>0){
                        for (int j = 0; j < sysRoleList.size(); j++) {
                            SysRole sysRole =  sysRoleList.get(j);
                            if(sysRole.getRoleId()==8){
                                sysUseridnew.add(sysUserList.get(i));

                            }
                        }
                    }
                }
            }
            if(sysUseridnew.size()>0){
                cGroundSurvey.setStaute(FlowStatus.RUN.getStatus());
                   cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
                    //StringBuffer idsStr = new StringBuffer();
                     String longin=GenerateUUID.crUid();
                    for (SysUser role : sysUseridnew)
                    {
                        FlowWrite flowWrite=new FlowWrite();
                        flowWrite.setId(GenerateUUID.crUid());
                        flowWrite.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
                        flowWrite.setDeptid(String.valueOf(role.getDeptId()));
                        flowWrite.setUserid(String.valueOf(role.getUserId()));
                        flowWrite.setTitle(cGroundSurvey.getCodeId());
                        //map地图id
                        flowWrite.setDocid(String.valueOf(cGroundSurvey.getMapId()));
                        flowWrite.setFiletypeid(FlowType.DMTC.getStatus());
                        flowWrite.setFiletypename(FlowType.DMTC.getName());
                        flowWrite.setWorkflowname(cGroundSurvey.getCountyLevelName());//县级名称
                        flowWrite.setStattag("0");
                        flowWrite.setCreateTime(DateUtils.getNowDate());
                        flowWrite.setReceivetime(DateUtils.getNowDate());
                        flowWrite.setDraftdept(String.valueOf(currentUser.getDeptId()));
                        flowWrite.setDraftuser(String.valueOf(currentUser.getUserId()));
                        flowWrite.setLogid(longin);
                        iFlowWriteService.insertFlowWrite(flowWrite);
                    }


            }

        }


        return toAjax(sum);
    }

    /**
     * 提交地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/add11")
    @ResponseBody
    public AjaxResult addSubmit(CGroundSurvey cGroundSurvey)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        int sum=0;
        //获取待审核用户
        AjaxResult ajax=new AjaxResult();
        List<SysUser>  SubmintUser=getSubmintUser(ajax);
        if(SubmintUser.size()>0){
            cGroundSurvey.setStaute(FlowStatus.RUN.getStatus());
            sum=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
            if(sum>0){
                //添加流程记录
                sum=flowlogSb(cGroundSurvey,SubmintUser);
                //添加待审核信息
                if(sum>0){
                    sum=saveFlowWrite(SubmintUser,cGroundSurvey);
                    ajax=AjaxResult.success();
                }else{
                    return toAjax(sum);
                }

            }else{
                return toAjax(sum);
            }

        }else{
            ajax = AjaxResult.error("请添加审核人");
        }
        //
        return  ajax;
    }

    /**
     * 提交地面调查数据新版2020-11-23
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据")
    @PostMapping("/submintY")
    @ResponseBody
    public AjaxResult addSubmitY(@RequestParam("id") Long id)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        CGroundSurvey cGroundSurvey=cGroundSurveyService.selectCGroundSurveyById(id);
        int sum=0;
        //获取待审核用户
        cGroundSurvey.setGroundId(id);
        AjaxResult ajax=new AjaxResult();
        List<SysUser>  SubmintUser=getSubmintUserY(ajax);
        if(SubmintUser.size()>0){
            cGroundSurvey.setStaute(FlowStatus.RUN.getStatus());
            sum=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
            if(sum>0){
                //添加流程记录
                sum=flowlogSb(cGroundSurvey,SubmintUser);
                //添加待审核信息
                if(sum>0){
                    sum=saveFlowWrite(SubmintUser,cGroundSurvey);
                    ajax=AjaxResult.success();
                }else{
                    return toAjax(sum);
                }

            }else{
                return toAjax(sum);
            }

        }else{
            ajax = AjaxResult.error("请添加审核人");
        }
        //
        return  ajax;
    }



    /*
    *提交流程记录
    *
    *
    * */
    public int flowlogSb(CGroundSurvey cGroundSurvey,List<SysUser>  SubmintUser)
    {
        int sum=0;
        SysUser currentUser = ShiroUtils.getSysUser();
        List<SysUser> list=new ArrayList<>();
        list.add(currentUser);
        String receiveid=getReceiveid(list);
        String receivename=getReceivename(list);
        String receiveid1=getReceiveid(SubmintUser);
        String receivename1=getReceivename(SubmintUser);
        FlowWflog flowWflog=new FlowWflog();
        flowWflog.setLogin(GenerateUUID.crUid());
        flowWflog.setSuperlogid("0");
        flowWflog.setFiletypeid(FlowType.DMTC.getStatus());
        flowWflog.setFiletypename(FlowType.DMTC.getName());
        flowWflog.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
        flowWflog.setTitle(cGroundSurvey.getCodeId());
        flowWflog.setReceiveid(receiveid1);
        flowWflog.setReceivename(receivename1);
        //flowWflog.setReceivetime(DateUtils.getTime());
        flowWflog.setSendname(receivename);
        flowWflog.setSendtime(DateUtils.getTime());
        flowWflog.setSenduserid(String.valueOf(currentUser.getUserId()));
        flowWflog.setSenddeptid(String.valueOf(currentUser.getDeptId()));
        sum=iFlowWflogService.insertFlowWflog(flowWflog);
        //flowWflog.
        //flowWflog.setReceiveid();
        return sum;
    }
    /*
    * 获取提交人信息ID
    * */
    public String getReceiveid(List<SysUser> ListSysu ){
        String str="";
        for (int i = 0; i < ListSysu.size(); i++) {
            if(ListSysu.size()-1==i){
                str=str+ String.valueOf(ListSysu.get(i).getUserId())+"*"+String.valueOf(ListSysu.get(i).getDeptId());
            }else{
                str=str+ String.valueOf(ListSysu.get(i).getUserId())+"*"+String.valueOf(ListSysu.get(i).getDeptId())+",";
            }

            
        }
          return str;
    }
    /*
     * 获取提交人信息名称
     * */
    public String getReceivename(List<SysUser> ListSysu ){
        String str="";
        for (int i = 0; i < ListSysu.size(); i++) {
            if(ListSysu.size()-1==i){
                str=str+ String.valueOf(ListSysu.get(i).getUserName())+"/"+String.valueOf(ListSysu.get(i).getDept().getDeptName());
            }else{
                str=str+ String.valueOf(ListSysu.get(i).getUserName())+"/"+String.valueOf(ListSysu.get(i).getDept().getDeptName())+",";
            }


        }
        return str;
    }
    /*
    *添加待审核记录
    * */
    public  int saveFlowWrite(List<SysUser> SubmintUser,CGroundSurvey cGroundSurvey){
           SysUser currentUser = ShiroUtils.getSysUser();
           int sum=0;
            String longin=GenerateUUID.crUid();
            //提交数据并添加待审核记录
            for (SysUser role : SubmintUser)
            {
                FlowWrite flowWrite=new FlowWrite();
                flowWrite.setId(GenerateUUID.crUid());
                flowWrite.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
                flowWrite.setDeptid(String.valueOf(role.getDeptId()));
                flowWrite.setUserid(String.valueOf(role.getUserId()));
                flowWrite.setTitle(cGroundSurvey.getCodeId());
                flowWrite.setFiletypeid(FlowType.DMTC.getStatus());
                flowWrite.setFiletypename(FlowType.DMTC.getName());
                flowWrite.setWorkflowname(cGroundSurvey.getCountyLevelName());//县级名称
                flowWrite.setStattag("0");
                flowWrite.setDocid(String.valueOf(cGroundSurvey.getMapId()));
                flowWrite.setCreateTime(DateUtils.getNowDate());
                flowWrite.setReceivetime(DateUtils.getNowDate());
                flowWrite.setDraftdept(String.valueOf(currentUser.getDeptId()));
                flowWrite.setDraftuser(String.valueOf(currentUser.getUserId()));
                flowWrite.setLogid(longin);
                sum=iFlowWriteService.insertFlowWrite(flowWrite);
            }


        return sum;
    }
    /*
     * 获取当前用户上级省级审核人
     * */
    public List<SysUser> getSubmintUser(AjaxResult ajax){
        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.getDept();
        SysDept Dept =currentUser.getDept();
        //获取所有上级部门ID
        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();

        String[] ayyyancestors=ancestors.split(",");
        String deptid="";//审核部门id
        //按当前用户层级只有省级审核获取审核部门
        if(ayyyancestors.length==4){
            deptid =String.valueOf(currentUser.getDeptId());
        }else{
            if(ayyyancestors.length>4){
                deptid =  ayyyancestors[4];
            }
        }
        SysUser sysUser =new SysUser();
        if(deptid.equals("")){

        }else{
            sysUser.setDeptId(Long.valueOf(deptid));

        }
        List<SysUser> sysUserList=iSysUserService.selectUserListAll(sysUser);
        List<SysUser> sysUseridnew=new ArrayList<>();
        if(StringUtils.isNotEmpty(deptid)) {
            if (sysUserList != null) {
                for (int i = 0; i < sysUserList.size(); i++) {
                    List<SysRole> sysRoleList = iSysRoleService.selectRolesByUserIdAll(sysUserList.get(i).getUserId());
                    if (sysRoleList != null && sysRoleList.size() > 0) {
                        for (int j = 0; j < sysRoleList.size(); j++) {
                            SysRole sysRole = sysRoleList.get(j);
                            if (sysRole.getRoleId() == 8) {
                                sysUseridnew.add(sysUserList.get(i));
                                ajax = AjaxResult.success();
                            }
                        }
                    }else{
                        ajax = AjaxResult.error("请添加审核人");
                    }
                }
            }else{
                 ajax = AjaxResult.error("请添加审核人");
            }
        }else{
             ajax = AjaxResult.error("请添加审核人");
        }

        return  sysUseridnew;
    }


    /*
     * 获取当前用户上级县级审核人
     * */
    public List<SysUser> getSubmintUserY(AjaxResult ajax){
        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.getDept();
        SysDept Dept =currentUser.getDept();
        //获取所有上级部门ID
        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();

        String[] ayyyancestors=ancestors.split(",");
        String deptid="";//审核部门id
        //按当前用户层级只有省级审核获取审核部门
        if(ayyyancestors.length==6){
            deptid =String.valueOf(currentUser.getDeptId());
        }else{
            if(ayyyancestors.length>6){
                deptid =  ayyyancestors[6];
            }
        }
        SysUser sysUser =new SysUser();
        if(deptid.equals("")){

        }else{
            sysUser.setDeptId(Long.valueOf(deptid));

        }
        List<SysUser> sysUserList=iSysUserService.selectUserListAll(sysUser);
        List<SysUser> sysUseridnew=new ArrayList<>();
        if(StringUtils.isNotEmpty(deptid)) {
            if (sysUserList != null) {
                for (int i = 0; i < sysUserList.size(); i++) {
                    List<SysRole> sysRoleList = iSysRoleService.selectRolesByUserIdAll(sysUserList.get(i).getUserId());
                    if (sysRoleList != null && sysRoleList.size() > 0) {
                        for (int j = 0; j < sysRoleList.size(); j++) {
                            SysRole sysRole = sysRoleList.get(j);
                            if (sysRole.getRoleId() == 8) {
                                sysUseridnew.add(sysUserList.get(i));
                                ajax = AjaxResult.success();
                            }
                        }
                    }else{
                        ajax = AjaxResult.error("请添加审核人");
                    }
                }
            }else{
                ajax = AjaxResult.error("请添加审核人");
            }
        }else{
            ajax = AjaxResult.error("请添加审核人");
        }

        return  sysUseridnew;
    }
    /**
     * 新增并提交保存地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @Log(title = "地面调查数据", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    @ResponseBody
    public AjaxResult addSave1(CGroundSurvey cGroundSurvey,CPlot cPlot)
    {

        cPlot.setStatus("1");
        List<CPlot> cPlotList=cGroundSurvey.getCPlotList();
        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.getDept();
        SysDept Dept =currentUser.getDept();

        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();
        String[] ayyyancestors=ancestors.split(",");
        String deptid=null;
        //获取用户id
        long id =currentUser.getUserId();
        if(id!=0){
            cGroundSurvey.setCreateBy(String.valueOf(id));
        }
        cGroundSurvey.setStaute(FlowStatus.DRAF.getStatus());
        int sum=cGroundSurveyService.insertCGroundSurvey(cGroundSurvey);
        int update=0;
        if(ayyyancestors.length==4){
            deptid =String.valueOf(currentUser.getDeptId());
        }else{
            if(ayyyancestors.length>4){
                deptid =  ayyyancestors[4];
            }
        }
        if(deptid!=null){
            SysUser sysUser =new SysUser();
            sysUser.setDeptId(Long.valueOf(deptid));
            List<SysUser> sysUserList=iSysUserService.selectUserListAll(sysUser);
            List<SysUser> sysUseridnew=new ArrayList<>();
            if(sysUserList!=null&&sysUserList.size()>0){
                for (int i = 0; i < sysUserList.size(); i++) {
                    List<SysRole> sysRoleList=iSysRoleService.selectRolesByUserIdAll(sysUserList.get(i).getUserId());
                    if(sysRoleList!=null&&sysRoleList.size()>0){
                        for (int j = 0; j < sysRoleList.size(); j++) {
                            SysRole sysRole =  sysRoleList.get(j);
                            if(sysRole.getRoleId()==8){
                                sysUseridnew.add(sysUserList.get(i));

                            }

                        }
                    }

                }
            }


            if(sysUseridnew.size()>0){

                //StringBuffer idsStr = new StringBuffer();
                String longin=GenerateUUID.crUid();
                for (SysUser role : sysUseridnew)
                {
                    //idsStr.append(role).append(",");
                    cGroundSurvey.setStaute(FlowStatus.RUN.getStatus());
                    FlowWrite flowWrite=new FlowWrite();
                    flowWrite.setId(GenerateUUID.crUid());
                    flowWrite.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
                    flowWrite.setDeptid(String.valueOf(role.getDeptId()));
                    flowWrite.setUserid(String.valueOf(role.getUserId()));
                    flowWrite.setTitle(cGroundSurvey.getCodeId());
                    flowWrite.setFiletypeid(FlowType.DMTC.getStatus());
                    flowWrite.setFiletypename(FlowType.DMTC.getName());
                    flowWrite.setWorkflowname(cGroundSurvey.getCountyLevelName());//县级名称
                    flowWrite.setStattag("0");
                    flowWrite.setCreateTime(DateUtils.getNowDate());
                    flowWrite.setReceivetime(DateUtils.getNowDate());
                    flowWrite.setDraftdept(String.valueOf(currentUser.getDeptId()));
                    flowWrite.setDraftuser(String.valueOf(currentUser.getUserId()));
                    flowWrite.setLogid(longin);
                    update=cGroundSurveyService.updateCGroundSurvey(cGroundSurvey);

                    iFlowWriteService.insertFlowWrite(flowWrite);
                }


            }

        }


        return toAjax(sum);
    }

    /**
     * 修改并提交保存地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:edit")
    @Log(title = "地面调查数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit1")
    @ResponseBody
    public AjaxResult editSave1( CGroundSurvey cGroundSurvey,CPlot cPlot)
    {


        cPlot.setStatus("1");
        List<CPlot> cPlotList=cGroundSurvey.getCPlotList();
        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.getDept();
        SysDept Dept =currentUser.getDept();
        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();
        String[] ayyyancestors=ancestors.split(",");
        String deptid=null;
        //获取用户id
        long id =currentUser.getUserId();
        if(id!=0){
            cGroundSurvey.setCreateBy(String.valueOf(id));
        }
        cGroundSurvey.setStaute(FlowStatus.DRAF.getStatus());
        int sum=cGroundSurveyService.updateCGroundSurvey(cGroundSurvey);
        int update=0;
        if(ayyyancestors.length==4){
            deptid =String.valueOf(currentUser.getDeptId());
        }else{
            if(ayyyancestors.length>4){
                deptid =  ayyyancestors[4];
            }
        }
        if(deptid!=null){
            SysUser sysUser =new SysUser();
            sysUser.setDeptId(Long.valueOf(deptid));
            List<SysUser> sysUserList=iSysUserService.selectUserListAll(sysUser);
            List<SysUser> sysUseridnew=new ArrayList<>();
            if(sysUserList!=null&&sysUserList.size()>0){
                for (int i = 0; i < sysUserList.size(); i++) {
                    List<SysRole> sysRoleList=iSysRoleService.selectRolesByUserIdAll(sysUserList.get(i).getUserId());
                    if(sysRoleList!=null&&sysRoleList.size()>0){
                        for (int j = 0; j < sysRoleList.size(); j++) {
                            SysRole sysRole =  sysRoleList.get(j);
                            if(sysRole.getRoleId()==8){
                                sysUseridnew.add(sysUserList.get(i));

                            }

                        }
                    }

                }
            }

            if(sysUseridnew.size()>0){

                //StringBuffer idsStr = new StringBuffer();
                String longin=GenerateUUID.crUid();
                for (SysUser role : sysUseridnew)
                {
                    //idsStr.append(role).append(",");
                    cGroundSurvey.setStaute(FlowStatus.RUN.getStatus());
                    FlowWrite flowWrite=new FlowWrite();
                    flowWrite.setId(GenerateUUID.crUid());
                    flowWrite.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
                    flowWrite.setDeptid(String.valueOf(role.getDeptId()));
                    flowWrite.setUserid(String.valueOf(role.getUserId()));
                    flowWrite.setTitle(cGroundSurvey.getCodeId());
                    flowWrite.setFiletypeid(FlowType.DMTC.getStatus());
                    flowWrite.setFiletypename(FlowType.DMTC.getName());
                    flowWrite.setWorkflowname(cGroundSurvey.getCountyLevelName());//县级名称
                    flowWrite.setStattag("0");
                    flowWrite.setCreateTime(DateUtils.getNowDate());
                    flowWrite.setReceivetime(DateUtils.getNowDate());
                    flowWrite.setDraftdept(String.valueOf(currentUser.getDeptId()));
                    flowWrite.setDraftuser(String.valueOf(currentUser.getUserId()));
                    flowWrite.setLogid(longin);
                    update=cGroundSurveyService.updateCGroundSurvey(cGroundSurvey);

                    iFlowWriteService.insertFlowWrite(flowWrite);
                }


            }

        }

        return toAjax(sum);
    }
    /**
     * 审核页面跳转
     */
    @GetMapping("/check/{groundId}")
    public String check(@PathVariable("groundId") String groundId, ModelMap mmap)
    {
        FlowWrite flowWrite=iFlowWriteService.selectFlowWriteById(groundId);
        CGroundSurvey cGroundSurvey = cGroundSurveyService.selectCGroundSurveyById(Long.valueOf(flowWrite.getRecordid()));
        cGroundSurvey.getFlowIdea().setUniqueid(groundId);
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
        return prefix + "/check";
    }

    /**
     * 审核通过地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:check")
    @Log(title = "审核地面调查数据")
    @PostMapping("/check")
    @ResponseBody
    public AjaxResult checkadd(CGroundSurvey cGroundSurvey)
    {
        //获取用户
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        cGroundSurvey.setStaute(FlowStatus.FINISH.getStatus());
        Long groundId=cGroundSurvey.getGroundId();
        //修改状态
        int upd=cGroundSurveyService.updateCGroundSurveySt(cGroundSurvey);
        //查询所有审核数据
        FlowWrite flowWrite=new FlowWrite();
        flowWrite.setFiletypeid(FlowType.DMTC.getStatus());
        flowWrite.setRecordid(String.valueOf(groundId));
        //查询所有审核用户
        List<FlowWrite> flowWrites=iFlowWriteService.selectFlowWriteList(flowWrite);
        //添加审核完成信息
        FlowRead flowRead=new FlowRead();
        flowRead.setId(GenerateUUID.crUid());
        flowRead.setTitle(cGroundSurvey.getCodeId());
        flowRead.setFiletypeid(FlowType.DMTC.getStatus());
        flowRead.setFiletypename(FlowType.DMTC.getName());
        flowRead.setRecordid(String.valueOf(groundId));
        flowRead.setReadtime(DateUtils.getNowDate());
        flowRead.setUserid(String.valueOf(currentUser.getUserId()));
        flowRead.setDeptid(String.valueOf(currentUser.getDeptId()));
        flowRead.setCreateTime(flowWrites.get(0).getCreateTime());
        flowRead.setDraftuser(flowWrites.get(0).getDraftuser());
        flowRead.setDraftdept(flowWrites.get(0).getDraftdept());
        int frnum=iFlowReadService.insertFlowRead(flowRead);

        int delefy=0;
        if(frnum>0){
            //删除所有待审核数据
            for(FlowWrite flw: flowWrites)
            {
                int   num=iFlowWriteService.deleteFlowWriteById(flw.getId());
       if(num>0)
         {
             delefy++;
         }
            }
       /* if(delefy>0&&delefy==flowWrites.size()){

        }*/
            flowlogEn(cGroundSurvey);



        }
        return toAjax(delefy);
    }
    /*
     *审核流程记录
     *
     *
     * */
    public int flowlogEn(CGroundSurvey cGroundSurvey)
    {
        int sum=0;
        SysUser currentUser = ShiroUtils.getSysUser();
        List<SysUser> list=new ArrayList<>();
        list.add(currentUser);
        String receiveid=getReceiveid(list);
        String receivename=getReceivename(list);
        FlowWflog flowWflog=new FlowWflog();
        flowWflog.setLogin(GenerateUUID.crUid());
        flowWflog.setSuperlogid("0");
        flowWflog.setFiletypeid(FlowType.DMTC.getStatus());
        flowWflog.setFiletypename(FlowType.DMTC.getName());
        flowWflog.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
        flowWflog.setTitle(cGroundSurvey.getCodeId());
        flowWflog.setReceivename("审核完成");
        //flowWflog.setReceivetime(DateUtils.getTime());
        flowWflog.setSendname(receivename);
        flowWflog.setSendtime(DateUtils.getTime());
        flowWflog.setSenduserid(String.valueOf(currentUser.getUserId()));
        flowWflog.setSenddeptid(String.valueOf(currentUser.getDeptId()));
        sum=iFlowWflogService.insertFlowWflog(flowWflog);
        //flowWflog.
        //flowWflog.setReceiveid();
        return sum;
    }

    /**
     * 修改地面调查数据
     */
    @GetMapping("/edit/{groundId}")
    public String edit(@PathVariable("groundId") Long groundId, ModelMap mmap,CPlot cPlot)
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
        return prefix + "/editadd";
    }


    /**
     * 修改地面调查数据新版
     */
    @GetMapping("/editY/{groundId}")
    public String editY(@PathVariable("groundId") Long groundId, ModelMap mmap,CPlot cPlot)
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
        return prefix + "/editY";
    }

    /**
     * 修改保存地面调查数据
     *//*
    @RequiresPermissions("system:survey:edit")
    @Log(title = "地面调查数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CGroundSurvey cGroundSurvey)
    {
        return toAjax(cGroundSurveyService.updateCGroundSurvey(cGroundSurvey));
    }*/

    /**
     * 删除地面调查数据
     */
    @RequiresPermissions("caiji:survey:remove")
    @Log(title = "地面调查数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cGroundSurveyService.deleteCGroundSurveyByIds(ids));
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("photo") MultipartFile[] files) throws Exception
    {
        try
        {
            MultipartFile file=files[0];
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
            int sum=CResourceService.insertCResource(CResource);
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
            CResource CResource=CResourceService.selectCResourceById(Long.parseLong(id));
            String url =CResource.getFileurl();
            boolean result=false;
            int sum=0;
            if(StringUtils.isNotEmpty(url)){
                 result=FileUtils.deleteFile(url);
                if(result){
                    sum=CResourceService.deleteCResourceById(Long.parseLong(id));
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
     * 字典查询
     */
    @GetMapping("/dict")
    @ResponseBody
    public AjaxResult dict(@RequestParam("dict") String dict)
    {

        List<SysDictData> dictData=dictService.getType(dict);
        AjaxResult ajax = new AjaxResult();
        if(dictData!=null&&dictData.size()>0){
             ajax = AjaxResult.success();
            ajax.put("dictData", dictData);
        }else{
            ajax = AjaxResult.error();
            ajax.put("dictData", dictData);
        }

        return ajax;
    }

    /**
     * 样地编号页面选择
     */
    @GetMapping("/code")
    public String code()
    {
        return prefix + "/code";
    }

    /**
     * 字典查询行政代码
     */
    @GetMapping("/dictdata")
    @ResponseBody
    public AjaxResult dictXzheng()
    {
        SysDictData sysDictData=new SysDictData();
        sysDictData.setDictType("sys_city");
        List<SysDictData> dictData=iSysDictDataService.selectDictDataList(sysDictData);
        SysDictData sysDictData1=new SysDictData();
        sysDictData1.setDictType("sys_province");
        List<SysDictData> dictData1=iSysDictDataService.selectDictDataList(sysDictData1);
        SysDictData sysDictData2=new SysDictData();
        sysDictData2.setDictType("sys_area");
        List<SysDictData> dictData2=iSysDictDataService.selectDictDataList(sysDictData2);
        List<RegionCode> regionCode=new ArrayList<>();
        dictData1.addAll(dictData2);
        dictData1.addAll(dictData);
        for(SysDictData sys :dictData1){
            RegionCode egionCode=new RegionCode();
            egionCode.setDictCode(sys.getDictCode());
            egionCode.setDictLabel(sys.getDictLabel());
            regionCode.add(egionCode);
        }
        AjaxResult ajax = new AjaxResult();
        if(dictData!=null&&dictData.size()>0){
            ajax = AjaxResult.success();
            ajax.put("value", regionCode);
        }else{
            ajax = AjaxResult.error();
            ajax.put("value", regionCode);
        }

        return ajax;
    }

    /**
     * 字典查询行政代码
     */
    @GetMapping("/dictdata/{value}")
    @ResponseBody
    public AjaxResult getDictValue(@PathVariable("value") String  data)
    {
        List<SysDictData> dictData=dictService.getType(data);
        List<RegionCode> regionCode=new ArrayList<>();
        AjaxResult ajax = new AjaxResult();
        if(dictData!=null&&dictData.size()>0){
            for(SysDictData sys :dictData){
                RegionCode egionCode=new RegionCode();
                egionCode.setDictCode(sys.getDictCode());
                egionCode.setDictLabel(sys.getDictLabel());
                regionCode.add(egionCode);
            }
            ajax = AjaxResult.success();
            ajax.put("value", regionCode);
        }else{
            ajax = AjaxResult.error();
            ajax.put("value", dictData);
        }

        return ajax;
    }
    /**
     * 样地编号页面选择
     */
    @GetMapping("/idea")
    public String idea()
    {
        return prefix + "/idea";
    }
    /**
     * 审核意见添加
     */
    @GetMapping("/idea/{id}")
    public String idea(@PathVariable("id") Long id, ModelMap mmap)
    {
        FlowIdea flowIdea=new FlowIdea();
        flowIdea.setRecordid(String.valueOf(id));
        flowIdea.setFiletypeid(FlowType.DMTC.getStatus());
        List<FlowIdea> flowIdeas=ideaService.selectFlowIdeaList(flowIdea);
        mmap.put("flowIdea", flowIdeas);
        return prefix + "/ideatimeline";
    }

    /**
     * 审核记录
     */
    @GetMapping("/flowlog/{id}/{type}")
    public String log(@PathVariable("id") String id,@PathVariable("type") String type, ModelMap mmap)
    {
        //FlowWflog flowWflog=new FlowWflog();
       // flowWflog.setRecordid(id);
        //flowWflog.setFiletypeid(FlowType.DMTC.getStatus());
        //List<FlowWflog> FlowWflogs=iFlowWflogService.selectFlowWflogList(flowWflog);
        mmap.put("id", id);
        mmap.put("type", type);
        return prefix + "/flowlog";
    }
    /**
     * 新增并提交保存地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:add")
    @PostMapping("/ideaAdd")
    @ResponseBody
    public AjaxResult ideaAdd(FlowIdea flowIdea)
    {

        flowIdea.setFiletypeid(FlowType.DMTC.getStatus());
        flowIdea.setId(GenerateUUID.crUid());
        ideaService.insertFlowIdea(flowIdea);
        return toAjax(1);
    }


    /**
     * 退回地面调查数据
     */
    @Transactional
    @RequiresPermissions("caiji:survey:back")
    @PostMapping("/back")
    @ResponseBody
    public AjaxResult back(CGroundSurvey cGroundSurvey)
    {
        CGroundSurvey newCGroundSurvey= cGroundSurveyService.selectCGroundSurveyById(cGroundSurvey.getGroundId());
        cGroundSurvey.setCreateBy(newCGroundSurvey.getCreateBy());
        FlowIdea flowIdea=cGroundSurvey.getFlowIdea();
        FlowWrite flowWrite1=iFlowWriteService.selectFlowWriteById(flowIdea.getUniqueid());
        if(StringUtils.isNotEmpty(flowIdea.getId())){
            iFlowIdeaService.updateFlowIdea(flowIdea);
        }else{
            if(StringUtils.isNotEmpty(flowIdea.getIdea())){
                flowIdea.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
                flowIdea.setFiletypeid(FlowType.DMTC.getStatus());
                flowIdea.setStattag(IdeaType.IDEA_YI.getStatus());
                iFlowIdeaService.insertFlowIdea(flowIdea);
            }
        }
        cGroundSurvey.setStaute(FlowStatus.BACK.getStatus());
        int sum1=cGroundSurveyService.updateCGroundSurvey(cGroundSurvey);
        FlowWrite flowWrite=new FlowWrite();
        flowWrite.setRecordid(String.valueOf(flowWrite1.getRecordid()));
        flowWrite.setUserid(flowWrite1.getDraftuser());
        flowWrite.setDeptid(flowWrite1.getDraftdept());
        flowWrite.setTitle(flowWrite1.getTitle());
        flowWrite.setFiletypeid(flowWrite1.getFiletypeid());
        flowWrite.setFiletypename(flowWrite1.getFiletypename());
        flowWrite.setWorkflowname(flowWrite1.getWorkflowname());//县级名称
        flowWrite.setCreateTime(DateUtils.getNowDate());
        flowWrite.setReceivetime(DateUtils.getNowDate());
        flowWrite.setDraftdept(flowWrite1.getDraftdept());
        flowWrite.setDraftuser(flowWrite1.getDraftuser());
        //退回状态
        flowWrite.setStattag(FlowStatus.BACK.getStatus());
        iFlowWriteService.insertFlowWrite(flowWrite);
        iFlowWriteService.deleteFlowWriteBylogId(flowWrite1.getLogid());
        flowlogBack(cGroundSurvey);
        return toAjax(sum1);
    }
    /*
     *退回流程记录
     *
     *
     * */
    public int flowlogBack(CGroundSurvey cGroundSurvey)
    {
        int sum=0;
        SysUser currentUser = ShiroUtils.getSysUser();
        SysUser sysUser1=iSysUserService.selectUserById(Long.valueOf(cGroundSurvey.getCreateBy()));
        List<SysUser> list=new ArrayList<>();
        List<SysUser> list1=new ArrayList<>();
        list.add(currentUser);
        list1.add(sysUser1);
        String receiveid=getReceiveid(list);
        String receivename=getReceivename(list);
        String receivename1=getReceivename(list1);
        FlowWflog flowWflog=new FlowWflog();
        flowWflog.setLogin(GenerateUUID.crUid());
        flowWflog.setSuperlogid("0");
        flowWflog.setFiletypeid(FlowType.DMTC.getStatus());
        flowWflog.setFiletypename(FlowType.DMTC.getName());
        flowWflog.setRecordid(String.valueOf(cGroundSurvey.getGroundId()));
        flowWflog.setTitle(cGroundSurvey.getCodeId());
        flowWflog.setReceivename(receivename1);
        //flowWflog.setReceivetime(DateUtils.getTime());
        flowWflog.setSendname(receivename);
        flowWflog.setSendtime(DateUtils.getTime());
        flowWflog.setSenduserid(String.valueOf(currentUser.getUserId()));
        flowWflog.setSenddeptid(String.valueOf(currentUser.getDeptId()));
        sum=iFlowWflogService.insertFlowWflog(flowWflog);
        //flowWflog.
        //flowWflog.setReceiveid();
        return sum;
    }
    /**
     * 取样鼠类添加页面
     */
    @GetMapping("/shu")
    public String addshu()
    {
        return prefix + "/addQuShu";
    }

    /**
     * 取样鼠类添加页面
     */
    @GetMapping("/shuedit")
    public String editshu()
    {
        return prefix + "/editplot";
    }


    /**
     * 获取关联样地信息列表
     */
    /**
     * 查询草原虫害发生与防治情况二三级列表
     */
    @RequiresPermissions("caiji:survey:list")
    @PostMapping("/sonList")
    @ResponseBody
    public TableDataInfo list(CPlot cPlot,CGroundSurvey cGroundSurvey)
    {
        cPlot.setGroundId(cGroundSurvey.getGroundId());
        List<CPlot> list = cPlotService.selectCPlotList(cPlot);
        return getDataTable(list);
    }


    /**
     * 获取经纬度
     */
    /**
     * 根据选择地区获取经纬度
     */
    @GetMapping("/getlngLat")
    @ResponseBody
    public GisMap getlngLat(String addrArea, String addrCity, ModelMap model) {
        if (addrArea.equals("-1") || addrCity.equals("-1")) {
            return null;
        } else {
            String addrCityLabel = iSysDictDataService.selectDictValueToLabel(addrCity);
            if (addrCityLabel == null) {
                addrCityLabel = iSysDictDataService.selectDictValueToLabels(addrCity);
            }
            String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(addrArea);
            if (addrAreaLabel == null) {
                addrAreaLabel = iSysDictDataService.selectDictValueToLabels(addrArea);

            }
            String address = addrCityLabel + "" + addrAreaLabel;
            String lngLat=EntCoordSyncJob.getCoordinate(address);
            GisMap gisMap=new GisMap();
            gisMap.setCentre(lngLat);
            return gisMap;
        }
    }


    @GetMapping("/addPest/{id}")
    public String addPest(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("groundId",id);
        return prefix + "/pestAdd";
    }


    @GetMapping("/addVermin/{id}")
    public String addVermin(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("groundId",id);
        return prefix + "/verminAdd";
    }


    @GetMapping("/addDisease/{id}")
    public String addDisease(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("groundId",id);
        return prefix + "/diseaseAdd";
    }


    @GetMapping("/addWeed/{id}")
    public String addWeed(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("groundId",id);
        return prefix + "/weedAdd";
    }


    @GetMapping("/editd")
    public String editd()
    {
        return prefix + "/editd";
    }


    @GetMapping("/edits")
    public String edits()
    {
        return prefix + "/edits";
    }


    @GetMapping("/editc")
    public String editc(CPlot cPlot,ModelMap mmap)
    {
        mmap.put("cPlot",cPlot);
        return prefix + "/editc";
    }


    @GetMapping("/editb")
    public String editb()
    {
        return prefix + "/editb";
    }


    /**
     * 三级联动省级
     */

    @GetMapping("/getProvinces")
    @ResponseBody
    public List<SysDictData> getProvinces(ModelMap mmap)
    {
        String dictType="sys_province";
        List<SysDictData> list=iSysDictDataService.selectDictDataByType(dictType);
        return list;
    }
    /**
     * 三级联动市级
     */

    @GetMapping("/getCities")
    @ResponseBody
    public List<SysDictData> getCities(ModelMap mmap, String provinceCode)
    {
        List<SysDictData> list=iSysDictDataService.getCities(provinceCode);
        return list;
    }

    @GetMapping("/getCitiesLable")
    @ResponseBody
    public List<SysDictData> getCitiesLable(ModelMap mmap, String provinceCode)
    {
        /*根据市名称查询code*/
        String code=iSysDictDataService.selectDictValueCode(provinceCode);
        List<SysDictData> list=iSysDictDataService.getCities(code);
        return list;
    }
    /**
     * 三级联动曲县级
     */
    @GetMapping("/getAreas")
    @ResponseBody
    public List<SysDictData> getAreas( ModelMap mmap,String cityCode)
    {
        List<SysDictData> list=iSysDictDataService.getAreas(cityCode);

        return list;
    }

    /**
     * 三级联动曲县级根据名称查询
     */
    @GetMapping("/getAreasLable")
    @ResponseBody
    public List<SysDictData> getAreasLable( ModelMap mmap,String cityCode)
    {
        /*根据市名称查询code*/
        String code=iSysDictDataService.selectDictValueCode(cityCode);
        List<SysDictData> list=iSysDictDataService.getAreas(code);

        return list;
    }

    /**
     * 四级联动曲县级
     */
    @GetMapping("/getdz")
    @ResponseBody
    public List<SysDictData> getdz( ModelMap mmap,String areasCode)
    {
        List<SysDictData> list=iSysDictDataService.getAreas(areasCode);

        return list;
    }


}
