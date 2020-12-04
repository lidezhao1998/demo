package com.ruoyi.zaihai.ReserveManagement.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.zaihai.ReserveManagement.domain.*;
import com.ruoyi.zaihai.ReserveManagement.mapper.KInstockMapper;
import com.ruoyi.zaihai.ReserveManagement.mapper.KManufactorMapper;
import com.ruoyi.zaihai.ReserveManagement.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库Controller
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
@Controller
@RequestMapping("/reserves/outbound")
public class KOutboundController extends BaseController
{
    private String prefix = "ReserveManagement/outbound";

    @Autowired
    private KInstockMapper kInstockMapper;

    @Autowired
    private IKInstockService kInstockService;
    @Autowired
    private IKMaterialTypeService kMaterialTypeService;
    @Autowired
    private KManufactorMapper kManufactorMapper;


    @Autowired
    private IReserveService reserveService;

    @Autowired
    private IKStockService kStockService;


    @Autowired
    private IKOutboundService OutboundService;


    @RequiresPermissions("reserves:outbound:view")
    @GetMapping()
    public String instock()
    {
        return prefix + "/outbound";
    }




    /**
     * 查询出库列表
     */
    @RequiresPermissions("reserves:outbound:list")
    @PostMapping("/listTwo")
    @ResponseBody
    public TableDataInfo listTwo(KOutbound kOutbound)
    {
        startPage();
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();

        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        long deptId = ShiroUtils.getSysUser().getDeptId();

        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        if(roles.size()==0){
            List<KOutbound> list = OutboundService.selectKOutboundList( kOutbound);
            for (int j = 0; j < list.size(); j++) {
                KOutbound instock =  list.get(j);
                Reserve reserve = reserveService.selectReserveById(instock.getReserveId());
                //instock.setWarehousingMode(reserve.getLibrayName());


            }
            return getDataTable(list);
        }else{
            for (int i = 0; i < roles.size(); i++) {
                SysRole sysRole = roles.get(i);
                String roleName = sysRole.getRoleName();
                if (roleName.equals("国家级")) {
                    List<KOutbound> list = OutboundService.selectKOutboundList( kOutbound);
                    for (int j = 0; j < list.size(); j++) {
                        KOutbound instock =  list.get(j);
                        Reserve reserve = reserveService.selectReserveById(instock.getReserveId());
                        //instock.setWarehousingMode(reserve.getLibrayName());


                    }
                    return getDataTable(list);

                } else if (roleName.equals("省级")) {
                    Reserve reserve=new Reserve();
                    reserve.setDeptId(deptId);
                    List<Reserve> reservelist = reserveService.selectReserveList(reserve);
                    long ReserveId=0;
                    for (int j = 0; j < reservelist.size(); j++) {
                        Reserve reserve1 =  reservelist.get(j);
                        ReserveId=reserve1.getId();
                    }
                    kOutbound.setReserveId(ReserveId);
                    List<KOutbound> list = OutboundService.selectKOutboundList(kOutbound);


                    return getDataTable(list);

                }else{

                    List<KOutbound> list = OutboundService.selectKOutboundList( kOutbound);
                    for (int j = 0; j < list.size(); j++) {
                        KOutbound instock =  list.get(j);
                        Reserve reserve = reserveService.selectReserveById(instock.getReserveId());
                        // instock.setWarehousingMode(reserve.getLibrayName());


                    }
                    return getDataTable(list);
                }
            }
        }
        return null;


    }

    /**
     * 导出出库列表
     */
    @RequiresPermissions("reserves:outbound:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KInstock kInstock)
    {
        List<KInstock> list = kInstockService.selectKInstockList(kInstock);
        ExcelUtil<KInstock> util = new ExcelUtil<KInstock>(KInstock.class);
        return util.exportExcel(list, "instock");
    }

    /**
     * 新增出库
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        int Inventory=0;
        /*  KInstock kInstock1=null;
        *//*查询得出库的数量*//*
        List<KInstock> list = kInstockMapper.selectKInstockListCount(kInstock1);
        for (int i = 0; i < list.size(); i++) {
            *//*每条数据*//*
            KInstock ins =  list.get(i);
            if(ins.getName().equals(kinstock.getName())){

            }

        }        mmap.put("kInstock", kInstock);*/
        mmap.put("kInstockSum", Inventory);
        return prefix + "/add";
    }

    /**
     * 新增保存出库
     */
    @RequiresPermissions("reserves:outbound:add")
    @Log(title = "出库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KInstock kinstock, ModelMap mmap)
    {
        kinstock.setStatus("3");
        return toAjax(kInstockService.insertKInstock(kinstock));
    }

    /**
     * 修改出库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KInstock kInstock = kInstockService.selectKInstockById(id);
        mmap.put("kInstock", kInstock);
        return prefix + "/edit";
    }

    /**
     * 修改保存出库
     */
    @RequiresPermissions("reserves:outbound:edit")
    @Log(title = "出库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KInstock kInstock)
    {
        return toAjax(kInstockService.updateKInstock(kInstock));
    }

    /**
     * 删除出库
     */
    @RequiresPermissions("reserves:outbound:remove")
    @Log(title = "出库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kInstockService.deleteKInstockByIds(ids));
    }


    /**
     * 确认出库操作
     */
    @Log(title = "撤回操作", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult updateInstock  (long instockId)
    {
        //根据id查询出对应的入库数据啊
        KInstock kInstock = kInstockService.selectKInstockById(instockId);

        double ckCount;
        KStock kStock = null;
        String name="";

        List<KStock> list = kStockService.selectKStockList(kStock);
        for (int i = 0; i < list.size(); i++) {
            KStock stock = list.get(i);
            if (kInstock.getName().equals(stock.getName())) {
                name=stock.getName();
            }

        }


        if (list.size() == 0) {
            //获取登录用户的部门
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            kInstock.setAddress(deptName);
            Reserve reserve = null;
            //查出对应的储备库id
            List<Reserve> list1 = reserveService.selectReserveList(reserve);
            for (int i = 0; i < list1.size(); i++) {
                Reserve reserve1 = list1.get(i);
                if (reserve1.getAddress().equals(deptName)) {
                    kInstock.setReserveId(reserve1.getId());
                }
            }
            //添加入库数据到库存表
            kStockService.insertKInstock(kInstock);
        } else if (list.size() != 0) {
            //获取登录用户的部门
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            kInstock.setAddress(deptName);
            Reserve reserve = null;
            //查出对应的储备库id
            List<Reserve> list1 = reserveService.selectReserveList(reserve);
            for (int i = 0; i < list1.size(); i++) {
                Reserve reserve1 = list1.get(i);
                if (reserve1.getAddress().equals(deptName)) {
                    kInstock.setReserveId(reserve1.getId());
                }
            }
            for (int i = 0; i < list.size(); i++) {
                KStock stock = list.get(i);
                if (kInstock.getName().equals(stock.getName())) {
                    ckCount =stock.getInventory() - kInstock.getGoodsNumber();
                    stock.setInventory(ckCount);
                    kStockService.updateKInstock(stock);
                }
            }

        } else {
            //获取登录用户的部门
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            kInstock.setAddress(deptName);
            Reserve reserve = null;
            //查出对应的储备库id
            List<Reserve> list1 = reserveService.selectReserveList(reserve);
            for (int i = 0; i < list1.size(); i++) {
                Reserve reserve1 = list1.get(i);
                if (reserve1.getAddress().equals(deptName)) {
                    kInstock.setReserveId(reserve1.getId());
                }
            }
            //添加入库数据到库存表
            kStockService.insertKInstock(kInstock);
        }
        kInstock.setStatus("4");
       /* return toAjax(kInstockService.insertKInstock(kInstock));*/


        //判断状态为待出库修改为出库
            return toAjax(kInstockMapper.updateOutbound(instockId));
    }


    /**
     * 查看出库信息详情
     */
    @RequiresPermissions("reserves:outbound:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap, KInstock kInstock)
    {
        kInstock.setBasicId(id);
        List<KInstock> list = kInstockService.selectKInstockListTwo(kInstock);
        for (int i = 0; i < list.size(); i++) {
            KInstock instock =  list.get(i);
            KMaterialType kMaterialType = kMaterialTypeService.selectKMaterialTypeByType(instock.getType());
            KManufactor kManufactor = kManufactorMapper.selectKManufactorById(Long.parseLong(instock.getName()));
            instock.setType(kMaterialType.getMaterialName());
            instock.setName(kManufactor.getMaterialName());
        }
        KOutbound outbound= OutboundService.selectKOutboundById(id);

        mmap.put("outbound", outbound);
        mmap.put("KInstocklist", list);

        return prefix + "/look";
    }

    /**
     * 确认出库信息详情
     */
    @RequiresPermissions("reserves:outbound:report")
    @GetMapping("/report/{id}")
    public String report(@PathVariable("id") Long id, ModelMap mmap, KInstock kInstock)
    {
        KInstock kInstockLook = kInstockService.selectKInstockById(id);
        mmap.put("kInstock", kInstockLook);

        return prefix + "/report";
    }


    @Log(title = "验证", businessType = BusinessType.UPDATE)
    @PostMapping("/addTest")
    @ResponseBody
    public String addTest  (String name, Double number)
    {
        //判断状态为待出库修改为出库
        System.out.println("----------cehsi----------");
        return "404";
    }
}
