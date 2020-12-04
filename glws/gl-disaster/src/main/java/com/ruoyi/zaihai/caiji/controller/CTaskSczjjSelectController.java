package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import com.ruoyi.zaihai.caiji.domain.CTaskSelectAll;
import com.ruoyi.zaihai.caiji.service.ICTaskSczjService;
import com.ruoyi.zaihai.caiji.service.ICTaskSelectAllService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 草原鼠害发生与防治情况Controller
 *
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/caiji/sczjjselect")
public class CTaskSczjjSelectController extends BaseController
{
    private String prefix = "caiji/fangzhi";


    @Autowired
    private ICTaskSczjService cTaskSczjService;

    @Autowired
    private ICTaskSelectAllService icTaskSelectAllService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @RequiresPermissions("caiji:sczjjselect:view")
    @GetMapping()
    public String sczj()
    {
        return prefix + "/sczjjselect";
    }

    /**
     * 查询草原鼠害发生与防治情况列表
     */
    /*@RequiresPermissions("system:sczjjselect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskSczj cTaskSczj)
    {
        startPage();
        *//*SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.getDept();
        SysDept Dept =currentUser.getDept();
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
        }*//*
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
        *//*List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList1(codeS);*//*
        return getDataTable(list);
    }*/

    /**
     * 查询草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("caiji:sczjjselect:list")

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskSelectAll cTaskSelectAll) {

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
        String roleName= roles.get(0).getRoleName();
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
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
        }
        cTaskSelectAll.setCode(codeS);
/*
        List<CTaskSelectAll> list = icTaskSelectAllService.selectCTaskSelectAllList(cTaskSelectAll);

*/
        startPage();
        if(StringUtils.isNotEmpty(cTaskSelectAll.getProvince())){
            SysDictData sysDictData= iSysDictDataService.selectDictDataById(Long.valueOf(cTaskSelectAll.getProvince()));
            cTaskSelectAll.setProvince(sysDictData.getDictLabel().substring(0,2));
        }
        if(StringUtils.isNotEmpty(cTaskSelectAll.getCity())){
            SysDictData sysDictData1= iSysDictDataService.selectDictDataById(Long.valueOf(cTaskSelectAll.getCity()));
            cTaskSelectAll.setCity(sysDictData1.getDictLabel().substring(0,2));
        }
        if(StringUtils.isNotEmpty(cTaskSelectAll.getCounty())){
            SysDictData sysDictData2= iSysDictDataService.selectDictDataById(Long.valueOf(cTaskSelectAll.getCounty()));
            cTaskSelectAll.setCounty(sysDictData2.getDictLabel());
        }
        if(StringUtils.isNotEmpty(cTaskSelectAll.getProvince())){
            SysDictData sysDictData= iSysDictDataService.selectDictDataById(Long.valueOf(cTaskSelectAll.getProvince()));
            cTaskSelectAll.setProvince(sysDictData.getDictLabel());
        }
        List<CTaskSelectAll> list = icTaskSelectAllService.selectCTaskSelectAllList1(cTaskSelectAll);
        if(list!=null&& list.size()>0){
            for (int i = 0; i < list.size(); i++) {

                CTaskSelectAll groundSurvey = list.get(i);
                HashMap<String, Object> map = new HashMap<>();
                map.put("codeAddess", codeAddess);
                map.put("roleName", roleName);
                groundSurvey.setParams(map);

            }
        }else{
            CTaskSelectAll groundSurvey =new CTaskSelectAll();
            HashMap<String, Object> map = new HashMap<>();
            map.put("codeAddess", codeAddess);
            map.put("roleName", roleName);
            groundSurvey.setParams(map);
            list.add(groundSurvey);
        }

        return getDataTable(list);
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskSczj cTaskSczj = cTaskSczjService.selectCTaskSczjById(id);
        mmap.put("cTaskSczj",cTaskSczj);
        return prefix + "/detail";
    }

    /**
     * 导出草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("caiji:sczjjselect:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskSczj cTaskSczj)
    {
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
        ExcelUtil<CTaskSczj> util = new ExcelUtil<CTaskSczj>(CTaskSczj.class);
        return util.exportExcel(list, "sczjjselect");
    }

    /**
     * 新增草原鼠害发生与防治情况
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczjjselect:add")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskSczj cTaskSczj)
    {
        return toAjax(cTaskSczjService.insertCTaskSczj(cTaskSczj));
    }

    /**
     * 修改草原鼠害发生与防治情况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskSczj cTaskSczj = cTaskSczjService.selectCTaskSczjById(id);
        mmap.put("cTaskSczj", cTaskSczj);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczjjselect:edit")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskSczj cTaskSczj)
    {
        return toAjax(cTaskSczjService.updateCTaskSczj(cTaskSczj));
    }

    /**
     * 删除草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczjjselect:remove")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cTaskSczjService.deleteCTaskSczjByIds(ids));
    }
}
