package com.ruoyi.zaihai.ReserveManagement.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;
import com.ruoyi.zaihai.ReserveManagement.domain.KStock;
import com.ruoyi.zaihai.ReserveManagement.domain.Reserve;
import com.ruoyi.zaihai.ReserveManagement.mapper.KManufactorMapper;
import com.ruoyi.zaihai.ReserveManagement.mapper.KStockMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKStockService;
import com.ruoyi.zaihai.ReserveManagement.service.IReserveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存Controller
 * 
 * @author ruoyi
 * @date 2020-06-23
 */
@Controller
@RequestMapping("/reserves/stock")
public class KStockController extends BaseController
{
    private String prefix = "ReserveManagement/stock";

    @Autowired
    private IKStockService kStockService;
    @Autowired
    private IReserveService reserveService;

    @Autowired
    private KManufactorMapper kManufactorMapper;


    @Autowired
    private KStockMapper kStockMapper;

    @RequiresPermissions("reserves:stock:view")
    @GetMapping()
    public String stock()
    {
        return prefix + "/stock";
    }

    /**
     * 查询库存列表
     */
    @RequiresPermissions("reserves:stock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KStock kStock)
    {

        startPage();
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();

        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取登录用户的部门id
        long deptId = ShiroUtils.getSysUser().getDept().getDeptId();


        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        if(roles.size()==0){
            List<KStock> list = kStockService.selectKStockList(kStock);
            return getDataTable(list);
        }else{
            for (int i = 0; i < roles.size(); i++) {
                SysRole sysRole = roles.get(i);
                String roleName = sysRole.getRoleName();
                if (roleName.equals("国家级")) {
                    List<KStock> list = kStockService.selectKStockList(kStock);
                    return getDataTable(list);

                } else if (roleName.equals("省级")) {
                    Reserve reserve=new Reserve();
                    reserve.setDeptId(deptId);
                    List<Reserve> reseverId=reserveService.selectReserveList(reserve);
                    for (int j = 0; j < reseverId.size(); j++) {
                        Reserve reserve1 =  reseverId.get(j);
                        reserve1.getId();
                        kStock.setReserveId(reserve1.getId());
                    }
                    List<KStock> list = kStockService.selectKStockList(kStock);
                    for (int k = 0; k < list.size(); k++) {
                        KStock stock =  list.get(k);
                        KManufactor kManufactor = kManufactorMapper.selectKManufactorById(Long.parseLong(stock.getName()));
                        if(kManufactor==null){
                        }else{
                            stock.setName(kManufactor.getMaterialName());

                        }
                    }
                    return getDataTable(list);

                }else{

                    List<KStock> list = kStockService.selectKStockList(kStock);
                    return getDataTable(list);
                }
            }
        }
        List<KStock> list = kStockService.selectKStockList(kStock);
        return getDataTable(list);
    }

    /**
     * 导出库存列表
     */
    @RequiresPermissions("reserves:stock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KStock kStock)
    {
        List<KStock> list = kStockService.selectKStockList(kStock);
        ExcelUtil<KStock> util = new ExcelUtil<KStock>(KStock.class);
        return util.exportExcel(list, "stock");
    }

    /**
     * 新增库存
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存
     */
    @RequiresPermissions("reserves:stock:add")
    @Log(title = "库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KStock kStock)
    {
        return toAjax(kStockService.insertKStock(kStock));
    }

    /**
     * 修改库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KStock kStock = kStockService.selectKStockById(id);
        KManufactor kManufactor = kManufactorMapper.selectKManufactorById(Long.parseLong(kStock.getName()));
        kStock.setName(kManufactor.getMaterialName());

        mmap.put("kStock", kStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存
     */
    @RequiresPermissions("reserves:stock:edit")
    @Log(title = "库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KStock kStock)
    {
        return toAjax(kStockService.updateKStock(kStock));
    }

    /**
     * 删除库存
     */
    @RequiresPermissions("reserves:stock:remove")
    @Log(title = "库存", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kStockService.deleteKStockByIds(ids));
    }
}
