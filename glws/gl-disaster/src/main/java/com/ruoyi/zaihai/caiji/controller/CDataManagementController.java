package com.ruoyi.zaihai.caiji.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.zaihai.caiji.domain.CDataManagement;
import com.ruoyi.zaihai.caiji.service.ICDataManagementService;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.domain.InitialPreviewConfig;
import com.sun.rowset.internal.Row;
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
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/*
 * 防治组织信息管理Controller
 *
 * @author ruoyi
 * @date 2020-05-29*/


@Controller
@RequestMapping("/prevention/management")
public class CDataManagementController extends BaseController
{
    private String prefix = "caiji/management";

    @Autowired
    private ICDataManagementService cDataManagementService;

    @Autowired
    private ISysDeptService sysDeptService;

    @RequiresPermissions("prevention:management:view")
    @GetMapping()
    public String management()
    {
        return prefix + "/management";
    }

/*
     * 查询防治组织信息管理列表*/


    @RequiresPermissions("prevention:management:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CDataManagement cDataManagement)
    {
        startPage();
        List<CDataManagement> list = cDataManagementService.selectCDataManagementList(cDataManagement);

        return getDataTable(list);
    }


/*
     *展开详情信息*/


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CDataManagement cDataManagement = cDataManagementService.selectCDataManagementById(id);
        if(cDataManagement.getStatus().equals("1")){
            cDataManagement.setStatus("已上报");
        }else{
            cDataManagement.setStatus("未上报");

        }
        if(cDataManagement.getControlType()!=null){
        if(cDataManagement.getControlType().equals("1")){
            cDataManagement.setControlType("鼠害防治");
        }else{
            cDataManagement.setControlType("虫害防治");

        }}
        mmap.put("cDataManagement",cDataManagement);
        return prefix + "/detail";
    }

    /*
     *上报信息*/

    @GetMapping("/report/{id}")
    @RequiresPermissions("prevention:management:report")
    public String report(@PathVariable("id") Long id, ModelMap mmap)
    {
        CDataManagement cDataManagement = cDataManagementService.selectCDataManagementById(id);
        cDataManagement.setReportTime(DateUtils.getNowDate());
        mmap.put("cDataManagement",cDataManagement);
        return prefix + "/report";
    }

    /*
     * 导出防治组织信息管理列表*/


    @RequiresPermissions("prevention:management:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CDataManagement cDataManagement)
    {
        List<CDataManagement> list = cDataManagementService.selectCDataManagementList(cDataManagement);
        ExcelUtil<CDataManagement> util = new ExcelUtil<CDataManagement>(CDataManagement.class);
        return util.exportExcel(list, "management");
    }

/*
     * 新增防治组织信息管理*/


    @GetMapping("/add")
    @RequiresPermissions("prevention:management:add")
    public String add(ModelMap mmap)
    {
        CDataManagement cDataManagement = new CDataManagement();
        SysUser currentUser = ShiroUtils.getSysUser();
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
                cDataManagement.setProvincialLevelName(Dept.getDeptName());
                break;
            case 5:
                cDataManagement.setProvincialLevelName(sysDeptService.selectDeptById(Dept.getParentId()).getDeptName());
                cDataManagement.setCityLevelName(Dept.getDeptName());
                break;
            case 6:
                SysDept deptParentId=sysDeptService.selectDeptById(Dept.getParentId());
                cDataManagement.setProvincialLevelName(sysDeptService.selectDeptById(deptParentId.getParentId()).getDeptName());
                cDataManagement.setCityLevelName( deptParentId.getDeptName());
                cDataManagement.setCountyLevelName( Dept.getDeptName());
                break;
            default:
                cDataManagement.setProvincialLevelName( Dept.getDeptName());
                break;
        }
        mmap.put("cDataManagement", cDataManagement);
        return prefix + "/add";
    }


/*
     * 新增保存防治组织信息管理*/


    @RequiresPermissions("prevention:management:add")
    @Log(title = "防治组织信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CDataManagement cDataManagement)
    {
        cDataManagement.setStatus("0");
        return toAjax(cDataManagementService.insertCDataManagement(cDataManagement));
    }

/*
     * 新增保存防治组织信息管理*/


    @RequiresPermissions("prevention:management:add1")
    @Log(title = "防治组织信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    @ResponseBody
    public AjaxResult addSave1(CDataManagement cDataManagement)
    {
        cDataManagement.setStatus("1");
        cDataManagement.setReportTime(DateUtils.getNowDate());
        return toAjax(cDataManagementService.insertCDataManagement(cDataManagement));
    }

/*
     * 修改防治组织信息管理*/


    @GetMapping("/edit/{id}")
    @RequiresPermissions("prevention:management:edit")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CDataManagement cDataManagement = cDataManagementService.selectCDataManagementById(id);
        mmap.put("cDataManagement", cDataManagement);
        return prefix + "/edit";
    }

/*
     * 修改保存防治组织信息管理*/


    @RequiresPermissions("prevention:management:edit")
    @Log(title = "防治组织信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CDataManagement cDataManagement)
    {
        cDataManagement.setStatus("0");
        return toAjax(cDataManagementService.updateCDataManagement(cDataManagement));
    }


    /**
     * 修改保存防治组织信息管理
     */
    @RequiresPermissions("prevention:management:edit1")
    @Log(title = "防治组织信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit1")
    @ResponseBody
    public AjaxResult editSave1(CDataManagement cDataManagement)
    {

        cDataManagement.setStatus("1");
        cDataManagement.setReportTime(DateUtils.getNowDate());
        return toAjax(cDataManagementService.updateCDataManagement(cDataManagement));
    }

/*
     * 删除防治组织信息管理*/
    @RequiresPermissions("prevention:management:remove")
    @Log(title = "防治组织信息管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cDataManagementService.deleteCDataManagementByIds(ids));
    }
}
