package com.ruoyi.zaihai.ReserveManagement.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.zaihai.ReserveManagement.domain.KAllocation;
import com.ruoyi.zaihai.ReserveManagement.domain.KInstock;
import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;
import com.ruoyi.zaihai.ReserveManagement.domain.KMaterialType;
import com.ruoyi.zaihai.ReserveManagement.mapper.KAllocationMapper;
import com.ruoyi.zaihai.ReserveManagement.mapper.KMaterialTypeMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKAllocationService;
import com.ruoyi.zaihai.ReserveManagement.service.IKInstockService;
import com.ruoyi.zaihai.ReserveManagement.service.IKManufactorService;
import com.ruoyi.zaihai.ReserveManagement.service.IKMaterialTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调拨单Controller
 * 
 * @author ruoyi
 * @date 2020-06-30
 */
@Controller
@RequestMapping("/reserves/allocationSh")
public class KAllocationShController extends BaseController
{
    private String prefix = "ReserveManagement/allocation";

    @Autowired
    private IKAllocationService kAllocationService;

    @Autowired
    private KAllocationMapper kAllocationMapper;


    @Autowired
    private IKInstockService kInstockService;

    @Autowired
    private IKMaterialTypeService kMaterialTypeService;

    @Autowired
    private IKManufactorService kManufactorService;


    @Autowired
    private KMaterialTypeMapper kMaterialTypeMapper;

    @RequiresPermissions("reserves:allocation:view")
    @GetMapping()
    public String allocation()
    {
        return prefix + "/allocationSh";
    }


    /**
     * 查询调拨单列表
     */
    @RequiresPermissions("reserves:allocation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KAllocation kAllocation)
    {
        startPage();
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();

        //获取登录用户的部门id
        long deptId = ShiroUtils.getSysUser().getDeptId();

        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole =  roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("省级")) {
                kAllocation.setDeptId(deptId);
                List<KAllocation> list = kAllocationService.selectKAllocationShList(kAllocation);
                return getDataTable(list);
            }
        }
        List<KAllocation> list = kAllocationService.selectKAllocationShList(kAllocation);

        return getDataTable(list);
    }

    /**
     * 导出调拨单列表
     */
    @RequiresPermissions("reserves:allocation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KAllocation kAllocation)
    {
        List<KAllocation> list = kAllocationService.selectKAllocationList(kAllocation);
        ExcelUtil<KAllocation> util = new ExcelUtil<KAllocation>(KAllocation.class);
        return util.exportExcel(list, "allocation");
    }

    /**
     * 新增调拨单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存调拨单
     */
    @RequiresPermissions("reserves:allocation:add")
    @Log(title = "调拨单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KAllocation kAllocation)
    {
        kAllocation.setStatus("1");
        return toAjax(kAllocationService.insertKAllocation(kAllocation));
    }

    /**
     * 修改调拨单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KAllocation kAllocation = kAllocationService.selectKAllocationById(id);
        mmap.put("kAllocation", kAllocation);
        return prefix + "/edit";
    }

    /**
     * 修改保存调拨单
     */
    @RequiresPermissions("reserves:allocation:edit")
    @Log(title = "调拨单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KAllocation kAllocation)
    {
        return toAjax(kAllocationService.updateKAllocation(kAllocation));
    }

    /**
     * 删除调拨单
     */
    @RequiresPermissions("reserves:allocation:remove")
    @Log(title = "调拨单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kAllocationService.deleteKAllocationByIds(ids));
    }

    /**
     * 上报调拨单
     */
    @Log(title = "上报操作", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult updateAllocation  (long id)
    {
        //判断状态为待上报
        return toAjax(kAllocationService.updateAllocation(id));
    }

    /**
 * 查看库详细信息
 */
@RequiresPermissions("reserves:allocation:detail")
@GetMapping("/detail/{id}")
public String detail(@PathVariable("id") Long id, ModelMap mmap, KAllocation kAllocation)
{
    KAllocation KAllocation = kAllocationService.selectKAllocationById(id);
    mmap.put("kAllocation", KAllocation);
    return prefix + "/look";
}


    /**
     * 审核调拨单详细信息
     */
    @GetMapping("/shReport/{id}")
    public String shReport(@PathVariable("id") Long id, ModelMap mmap, KInstock kInstock)
    {
        kInstock.setAllocationId(id);
        List<KInstock> list = kInstockService.selectKInstockAllocationList(kInstock);
        for (int i = 0; i < list.size(); i++) {
            KInstock instock =  list.get(i);
            KMaterialType kMaterialType = kMaterialTypeService.selectKMaterialTypeByType(instock.getType());
            KManufactor kManufactor = kManufactorService.selectKManufactorById(Long.parseLong(instock.getName()));
            instock.setType(kMaterialType.getMaterialName());
            instock.setName(kManufactor.getMaterialName());
        }
        KAllocation KAllocation = kAllocationService.selectKAllocationById(id);
        if(KAllocation.getStatus().equals("1")){
            KAllocation.setStatus("待上报");
        }else if(KAllocation.getStatus().equals("2")){
            KAllocation.setStatus("审核中");
        }else if(KAllocation.getStatus().equals("3")){
            KAllocation.setStatus("已上报");
        }else{
            KAllocation.setStatus("驳回");
        }
        mmap.put("KInstocklist", list);
        mmap.put("KAllocation", KAllocation);
        return prefix + "/shReport";
    }



    /**
     * 审核调拨单
     */
    @Log(title = "驳回操作", businessType = BusinessType.UPDATE)
    @PostMapping("/sh")
    @ResponseBody
    public AjaxResult updateBhAllocation  (long id)
    {
        //判断状态为驳回
        return toAjax(kAllocationMapper.updateBhAllocation(id));
    }

    /**
     * 驳回调拨单
     */
    @Log(title = "审核操作", businessType = BusinessType.UPDATE)
    @PostMapping("/bh")
    @ResponseBody
    public AjaxResult updateShAllocation  (KAllocation kAllocation,long id,String remark)
    {
        kAllocation.setId(id);
        kAllocation.setRemark(remark);
        kAllocation.setStatus("4");
        //判断状态为已上报
        return toAjax(kAllocationMapper.updateShAllocation(kAllocation));
    }


    /**
     * 查看审核调拨单详细信息
     */
    @GetMapping("/detailSh/{id}")
    public String detailSh(@PathVariable("id") Long id, ModelMap mmap, KInstock kInstock)
    {
        kInstock.setAllocationId(id);
        List<KInstock> list = kInstockService.selectKInstockAllocationList(kInstock);
        for (int i = 0; i < list.size(); i++) {
            KInstock instock =  list.get(i);
            KMaterialType kMaterialType = kMaterialTypeService.selectKMaterialTypeByType(instock.getType());
            KManufactor kManufactor = kManufactorService.selectKManufactorById(Long.parseLong(instock.getName()));
            instock.setType(kMaterialType.getMaterialName());
            instock.setName(kManufactor.getMaterialName());
        }
        KAllocation KAllocation = kAllocationService.selectKAllocationById(id);
        if(KAllocation.getStatus().equals("1")){
            KAllocation.setStatus("待上报");
        }else if(KAllocation.getStatus().equals("2")){
            KAllocation.setStatus("审核中");
        }else if(KAllocation.getStatus().equals("3")){
            KAllocation.setStatus("已上报");
        }else{
            KAllocation.setStatus("驳回");
        }
        mmap.put("KInstocklist", list);
        mmap.put("KAllocation", KAllocation);
        return prefix + "/ShLook";
    }


    /**
     * 添加审核记录操作
     */
    @Log(title = "储备库物资入库", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addFenJie", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String addSaveFenJie(@RequestBody List<KInstock> kInstock) {

        try {
            double ckCount;

            int status=0;
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            KInstock Instock=kInstock.get(0);
           /* KAllocation allocation=new KAllocation();
            allocation.setOddnumber(Instock.getOddnumber());
            allocation.setDbName(Instock.getName());
            allocation.setAllocationTime(DateUtils.getNowDate());
            allocation.setAllocationArea(Instock.getApplicationArea());
            allocation.setStatus("1");
            int sum=kAllocationService.insertKAllocation(allocation);*/
            long id=Instock.getAllocationId();
            kAllocationMapper.updateBhAllocation(id);
            for (int j = 1; j < kInstock.size(); j++) {

                //新增进入库表
                KInstock tTaskResolve = kInstock.get(j);
                KMaterialType type=kMaterialTypeMapper.selectKMaterialTypeByCode(tTaskResolve.getManufactorType());
                tTaskResolve.setType(type.getMaterialName());
                tTaskResolve.setAllocationId(id);
                tTaskResolve.setAddress(deptName);
                //调拨审核的状态
                tTaskResolve.setStatus("4");
                tTaskResolve.setName(tTaskResolve.getManufactorName());
                kInstockService.insertKInstock(tTaskResolve);


            }

            return "400";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
