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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.zaihai.caiji.domain.CGroundSurvey;
import com.ruoyi.zaihai.caiji.service.ICGroundSurveyService;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.domain.InitialPreviewConfig;
import com.ruoyi.zaihai.common.service.ICResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 地面调查数据Controller
 *
 * @author ruoyi
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/caiji/surveyselect")
public class CGroundSurveySelectController extends BaseController
{
    private String prefix = "caiji/dimian";

    @Autowired
    private ICGroundSurveyService cGroundSurveyService;

    @Autowired
    private ICResourceService CResourceService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private ISysDictDataService iSysDictDataService;



    @RequiresPermissions("caiji:surveyselect:view")
    @GetMapping()
    public String survey()
    {
        return prefix + "/surveyselect";
    }

    /**
     * 查询地面调查数据列表
     */
    @RequiresPermissions("caiji:surveyselect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CGroundSurvey cGroundSurvey)
    {

       /* SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        SysDept Deptuser =sysDeptService.selectDeptById(Dept.getDeptId());
        String ancestors=Deptuser.getAncestors();
        SysDept Deptnew=new SysDept();
        //获取省级地区集合
        Deptnew.setParentId(122L);
        List<SysDept> Deptlistp =sysDeptService.selectDeptList(Deptnew);
        //获取地级地区集合
        Deptnew.setParentId(122L);
        List<SysDept> Deptlistc =sysDeptService.selectDeptList(Deptnew);
        //获取县级地区集合

        String[] ayyyancestors=ancestors.split(",");
        int ii=ayyyancestors.length;
        switch(ii){
            case 4:
                cGroundSurvey.setProvincialLevelName(Dept.getDeptName());
                break;
            case 5:
                cGroundSurvey.setProvincialLevelName(sysDeptService.selectDeptById(Dept.getParentId()).getDeptName());
                cGroundSurvey.setCityLevelName(Dept.getDeptName());
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
        /*List<CGroundSurvey> list = cGroundSurveyService.selectCGroundSurveyList(cGroundSurvey);*/
        //获取部门角色


        String codeAddess ="";
        /*获取用户确认权限*/
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();

        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("区级")){
            }else{
                SysUser currentUser = ShiroUtils.getSysUser();
                currentUser.getDept();
                String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
                 codeAddess = iSysDictDataService.selectDictValueCode(deptName);
            }
        }
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();

        //List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String roleName= roles.get(0).getRoleName();
        String code =Dept.getCode();
        String codeS="";//把这个传进去查询
        if(StringUtils.isNotEmpty(code)){
            int  code1=code.indexOf("00");
            if(code1==2){
                codeS=code.substring(0,2);
            }else if(code1==4){
                codeS=code.substring(0,4);
            }else{
                codeS=code;
            }
            cGroundSurvey.setCode(codeS);
        }
       // cGroundSurvey.setStaute("03");
        startPage();
        List<CGroundSurvey> list = cGroundSurveyService.selectCGroundSurveyList1(cGroundSurvey);
        if(list!=null&& list.size()>0){
            for (int i = 0; i < list.size(); i++) {

                CGroundSurvey groundSurvey =  list.get(i);
                HashMap<String, Object> map = new HashMap<>();
                map.put("codeAddess",codeAddess);
                map.put("roleName",roleName);
                groundSurvey.setParams(map);
                long userId=Long.parseLong(groundSurvey.getCreateBy());
                SysUser user = userService.selectUserById(userId);
                groundSurvey.setCreateBy(user.getUserName());



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
        }else{
            CGroundSurvey groundSurvey =new CGroundSurvey();
            HashMap<String, Object> map = new HashMap<>();
            map.put("codeAddess",codeAddess);
            map.put("roleName",roleName);
            groundSurvey.setParams(map);
            list.add(groundSurvey);
        }

        return getDataTable(list);
    }

    /**
     * 导出地面调查数据列表
     */
    @RequiresPermissions("caiji:surveyselect:export")

    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CGroundSurvey cGroundSurvey)
    {
        List<CGroundSurvey> list = cGroundSurveyService.selectCGroundSurveyList(cGroundSurvey);
        ExcelUtil<CGroundSurvey> util = new ExcelUtil<CGroundSurvey>(CGroundSurvey.class);
        return util.exportExcel(list, "surveyselect");
    }

    /**
     * 查看详情
     */
    /**
     * 查看地面调查数据
     */
//    @RequiresPermissions("caiji:surveyselect:detail")

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
        return prefix + "/detail";
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
    @RequiresPermissions("caiji:surveyselect:add")
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
    @RequiresPermissions("caiji:surveyselect:edit")
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
    @RequiresPermissions("caiji:surveyselect:remove")
    @Log(title = "地面调查数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cGroundSurveyService.deleteCGroundSurveyByIds(ids));
    }
}
