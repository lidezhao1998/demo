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
import com.ruoyi.zaihai.ReserveManagement.domain.*;
import com.ruoyi.zaihai.ReserveManagement.mapper.KManufactorMapper;
import com.ruoyi.zaihai.ReserveManagement.mapper.KMaterialTypeMapper;
import com.ruoyi.zaihai.ReserveManagement.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库Controller
 *
 * @author ruoyi
 * @date 2020-06-16
 */
@Controller
@RequestMapping("/reserves/instock")
public class KInstockController extends BaseController {
    private String prefix = "ReserveManagement/instock";

    @Autowired
    private IKMaterialTypeService kMaterialTypeService;

    @Autowired
    private IKInstockService kInstockService;

    @Autowired
    private IKStockService kStockService;

    @Autowired
    private KManufactorMapper kManufactorMapper;

    @Autowired
    private IReserveService reserveService;

    @Autowired
    private IKOutboundService OutboundService;

    @Autowired
    private KMaterialTypeMapper kMaterialTypeMapper;




    @RequiresPermissions("reserves:instock:view")
    @GetMapping()
    public String instock() {
        return prefix + "/instock";
    }

    /**
     * 查询入库列表
     */
    @RequiresPermissions("reserves:instock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KOutbound kOutbound) {
            startPage();

        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();

        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取登录用户的部门id
        long deptId = ShiroUtils.getSysUser().getDeptId();


        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        if(roles.size()==0){
            List<KOutbound> list = OutboundService.selectKOutboundListTwo(kOutbound);
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
                    List<KOutbound> list = OutboundService.selectKOutboundListTwo( kOutbound);
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
                    List<KOutbound> list = OutboundService.selectKOutboundListTwo(kOutbound);
                    for (int j = 0; j < list.size(); j++) {
                        KOutbound instock =  list.get(j);
                        Reserve reserve2 = reserveService.selectReserveById(instock.getReserveId());
                        //instock.setWarehousingMode(reserve.getLibrayName());
                    }
                    return getDataTable(list);

                }else{

                    List<KOutbound> list = OutboundService.selectKOutboundListTwo( kOutbound);
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
     * 导出入库列表
     */
    @RequiresPermissions("reserves:instock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KInstock kInstock) {
        List<KInstock> list = kInstockService.selectKInstockList(kInstock);
        ExcelUtil<KInstock> util = new ExcelUtil<KInstock>(KInstock.class);
        return util.exportExcel(list, "instock");
    }

    /**
     * 新增入库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存入库
     */
    @RequiresPermissions("reserves:instock:add")
    @Log(title = "入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KInstock kInstock) {
        double ckCount;
        long ReserveId=0;
        String name="";
        KStock kStock = null;
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
                    ReserveId=reserve1.getId();
                }
            }
            //添加储备库id至库存表中
            kInstock.setReserveId(ReserveId);

            //添加入库数据到库存表
            kStockService.insertKInstock(kInstock);
        } else if (name.equals(kInstock.getName())){
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
                    ckCount = kInstock.getGoodsNumber() + stock.getInventory();
                    stock.setInventory(ckCount);
                    //修改库存数量
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
                    ReserveId=reserve1.getId();
                }
            }
            //添加储备库id至库存表
            kInstock.setReserveId(ReserveId);
            //添加入库数据到库存表
            kStockService.insertKInstock(kInstock);
        }

        kInstock.setStatus("2");
        return toAjax(kInstockService.insertKInstock(kInstock));
    }

    /**
     * 修改入库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        KInstock kInstock = kInstockService.selectKInstockById(id);
        mmap.put("kInstock", kInstock);
        return prefix + "/edit";
    }

    /**
     * 修改保存入库
     */
    @RequiresPermissions("reserves:instock:edit")
    @Log(title = "入库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KInstock kInstock) {
        return toAjax(kInstockService.updateKInstock(kInstock));
    }

    /**
     * 删除物资表数据
     */
    @Log(title = "入库", businessType = BusinessType.DELETE)
    @GetMapping("/del/{ids}")
    @ResponseBody
    public String del(@PathVariable("ids") String ids) {

        kInstockService.deleteKInstockByIds(ids);
        String code="200";
        return code;

    }


    /**
     * 删除入库
     */
    @RequiresPermissions("reserves:instock:remove")
    @Log(title = "入库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(kInstockService.deleteKInstockByIds(ids));
    }

    /**
     * 确认入库操作
     */
    @Log(title = "确认入库操作", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult updateInstock(long instockId) {
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
                    ckCount =stock.getInventory() + kInstock.getGoodsNumber();
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
        kInstock.setStatus("2");

        //判断状态为待出库
        return toAjax(kInstockService.updateInstock(instockId));
    }


    /**
     * 查看库详细信息
     */
    @RequiresPermissions("reserves:instock:detail")
    @GetMapping("/detail/{id}")
    public String detail(
            Long id, ModelMap mmap, KInstock kInstock) {
        kInstock.setBasicId(kInstock.getId());
        List<KInstock> list = kInstockService.selectKInstockList(kInstock);
        for (int i = 0; i < list.size(); i++) {
            KInstock instock =  list.get(i);
            KMaterialType kMaterialType = kMaterialTypeService.selectKMaterialTypeByType(instock.getType());
            KManufactor kManufactor = kManufactorMapper.selectKManufactorById(Long.parseLong(instock.getName()));
            instock.setType(kMaterialType.getMaterialName());
            instock.setName(kManufactor.getMaterialName());
        }
        KOutbound outbound= OutboundService.selectKOutboundById(kInstock.getId());

        mmap.put("outbound", outbound);
        mmap.put("KInstocklist", list);

        return prefix + "/look";
    }

    /**
     * 确认入库详细信息
     */
    @RequiresPermissions("reserves:instock:report")
    @GetMapping("/report/{id}")
    public String report(@PathVariable("id") Long id, ModelMap mmap, KInstock kInstock) {
        KInstock kInstockLook = kInstockService.selectKInstockById(id);
        mmap.put("kInstock", kInstockLook);

        return prefix + "/report";
    }

    /**
     * 三级联动类型
     */

    @GetMapping("/getType")
    @ResponseBody
    public List<KMaterialType> getType(ModelMap mmap) {
        List<KMaterialType> list = kMaterialTypeService.selectDictTypeAll();
        return list;
    }

    /**
     * 三级联动物资名称
     */

    @GetMapping("/getGoods")
    @ResponseBody
    public List<KManufactor> getCities(ModelMap mmap, String provinceCode) {
        List<KManufactor> list = kManufactorMapper.getGoods(provinceCode);
        return list;
    }

    /**
     * 三级联动数量
     */

    @GetMapping("/getNumber")
    @ResponseBody
    public KStock getNumber(ModelMap mmap, String provinceCode) {
        KStock kStock = kStockService.getNumber(provinceCode);
        if(kStock!=null){
            mmap.put("kStock",kStock.getInventory());
            return kStock;

        }else{
            KStock kStock1=new KStock();
            kStock1.setStatus("404");
            return kStock1;
        }
    }


    /**
     * 保存出库操作不进入库存
     */
    @RequiresPermissions("reserves:instock:add")
    @Log(title = "保存出库", businessType = BusinessType.INSERT)
    @PostMapping("/Saveck")
    @ResponseBody
    public AjaxResult Saveck(KInstock kInstock) {
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        kInstock.setAddress(deptName);
        kInstock.setStatus("3");

        return toAjax(kInstockService.insertKInstock(kInstock));

    }

    /**
     * 保存入库操作不进入库存
     */
    @RequiresPermissions("reserves:instock:add")
    @Log(title = "保存入库", businessType = BusinessType.INSERT)
    @PostMapping("/addrk")
    @ResponseBody
    public AjaxResult addrk(KInstock kInstock) {
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        kInstock.setAddress(deptName);
        kInstock.setStatus("1");

        return toAjax(kInstockService.insertKInstock(kInstock));

    }




    /**
     * 直接出库操作
     */
    @RequiresPermissions("reserves:instock:add")
    @Log(title = "出库", businessType = BusinessType.INSERT)
    @PostMapping("/addck")
    @ResponseBody
    public AjaxResult addCkSave(KInstock kInstock) {
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
        return toAjax(kInstockService.insertKInstock(kInstock));
    }


    /**
     * 全部入库操作
     */
    @Log(title = "储备库物资入库", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addFenJie", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String addSaveFenJie(@RequestBody List<KInstock> kInstock) {

        try {
            double ckCount;
            long ReserveId=0;
            String name="";
            KStock kStock = null;
            //获取登录用户的部门
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();

            String operName = ShiroUtils.getSysUser().getUserName();
            List<KStock> list = kStockService.selectKStockList(kStock);
            KOutbound KOutbound=new KOutbound();
            KInstock sock=kInstock.get(0);
            KOutbound.setReserveId(sock.getReserveId());
            Reserve reserve = reserveService.selectReserveById(KOutbound.getReserveId());
            KOutbound.setWarehousingMode(reserve.getLibrayName());
            //查询储备库
            KOutbound.setWarehousingTime(DateUtils.getNowDate());
            KOutbound.setCreateName(ShiroUtils.getSysUser().getCreateBy());
            KOutbound.setCreateTime(DateUtils.getNowDate());
            KOutbound.setCreateName(operName);
            KOutbound.setStatus("1");
            int sum=OutboundService.insertKOutbound(KOutbound);
            long id=KOutbound.getId();
            for (int j = 1; j < kInstock.size(); j++) {
                int status=0;

                //新增进入库表
                KInstock tTaskResolve = kInstock.get(j);
               // KMaterialType type=kMaterialTypeMapper.selectKMaterialTypeByCode(tTaskResolve.getManufactorType());
                tTaskResolve.setType(tTaskResolve.getManufactorType());
                tTaskResolve.setBasicId(id);
                tTaskResolve.setAddress(reserve.getLibrayName());
                tTaskResolve.setStatus("1");
                tTaskResolve.setName(tTaskResolve.getManufactorName());
                kInstockService.insertKInstock(tTaskResolve);
                //判断库存表中是否已存在,存在累加数值不存在新增数据
                for (int i = 0; i < list.size(); i++) {
                    KStock rk= list.get(i);
                if (rk.getName().equals(tTaskResolve.getManufactorName()) && rk.getReserveId() ==tTaskResolve.getReserveId()) {
                    ckCount = tTaskResolve.getGoodsNumber() + rk.getInventory();
                    rk.setInventory(ckCount);
                    //修改库存数量
                    status++;
                    kStockService.updateKInstock(rk);
                    System.out.println("-----------------xiangtongle");
                    break;//跳出k循环，回到j循环
                }

                }
                if(status==0){
                    System.out.println("不相同添加");
                    //添加入库数据到库存表
                    kStockService.insertKInstock(tTaskResolve);
                }
            }

            return "400";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 全部出库操作
     */
    @Log(title = "储备库物资出库", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addSaveCk", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String addSaveCk(@RequestBody List<KInstock> kInstock) {

        try {
            double ckCount;
            long ReserveId=0;
            String name="";
            KStock kStock = null;
            //获取登录用户的部门
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            String operName = ShiroUtils.getSysUser().getUserName();
            List<KStock> list = kStockService.selectKStockList(kStock);
            KOutbound KOutbound=new KOutbound();
            KInstock sock=kInstock.get(0);
            KOutbound.setReserveId(sock.getReserveId());
            Reserve reserve = reserveService.selectReserveById(KOutbound.getReserveId());
            KOutbound.setWarehousingMode(reserve.getLibrayName());
            //查询储备库
            KOutbound.setWarehousingTime(DateUtils.getNowDate());
            KOutbound.setCreateName(ShiroUtils.getSysUser().getCreateBy());
            KOutbound.setCreateTime(DateUtils.getNowDate());
            KOutbound.setCreateBy(ShiroUtils.getSysUser().getCreateBy());
            KOutbound.setStatus("2");
            KOutbound.setCreateName(operName);
            int sum=OutboundService.insertKOutbound(KOutbound);
            long id=KOutbound.getId();
            for (int j = 1; j < kInstock.size(); j++) {
                int status=0;
                //新增进入库表
                KInstock tTaskResolve = kInstock.get(j);
                tTaskResolve.setType(tTaskResolve.getManufactorType());
                tTaskResolve.setAddress(deptName);
                tTaskResolve.setBasicId(id);
                tTaskResolve.setStatus("2");
                tTaskResolve.setName(tTaskResolve.getManufactorName());
                kInstockService.insertKInstock(tTaskResolve);
                //判断库存表中是否已存在,存在累加数值不存在新增数据
                for (int i = 0; i < list.size(); i++) {
                    KStock rk= list.get(i);

                    if (rk.getName().equals(tTaskResolve.getManufactorName()) && rk.getReserveId() ==tTaskResolve.getReserveId()) {
                        ckCount =  rk.getInventory()-tTaskResolve.getGoodsNumber();
                        if(ckCount<0){
                            return "404";
                        }
                        rk.setInventory(ckCount);
                        //修改库存数量
                        status++;
                        kStockService.updateKInstock(rk);
                        System.out.println("-----------------xiangtongle");
                        break;//跳出k循环，回到j循环
                    }
                    if(status==0){
                        return "404";
                    }

                }

            }

            return "400";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
