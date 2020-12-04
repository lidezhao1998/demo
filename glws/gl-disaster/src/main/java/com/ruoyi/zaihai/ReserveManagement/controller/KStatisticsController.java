package com.ruoyi.zaihai.ReserveManagement.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.zaihai.ReserveManagement.domain.KStock;
import com.ruoyi.zaihai.ReserveManagement.service.IKStockService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统计Controller
 * 
 * @author ruoyi
 * @date 2020-06-23
 */
@Controller
@RequestMapping("/reserves/statistics")
public class KStatisticsController extends BaseController
{
    private String prefix = "ReserveManagement/statistics";

    @Autowired
    private IKStockService kStockService;

    @RequiresPermissions("reserves:statistics:view")
    @GetMapping()
    public String stock()
    {
        return prefix + "/statistics";
    }

    /**
     * 查询库存列表
     */
    @RequiresPermissions("reserves:statistics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KStock kStock) {

        startPage();
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();

        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("省级")) {

                kStock.setAddress(deptName);
                List<KStock> list = kStockService.selectKStockList(kStock);
                return getDataTable(list);

            }else if(roleName.equals("国家级")){
                List<KStock> list = kStockService.selectKStockList(kStock);
                return getDataTable(list);
            }
            else{
                kStock.setAddress(deptName);
                List<KStock> list = kStockService.selectKStockList(kStock);
                return getDataTable(list);

            }

        }
        return null;

    }

    /**
     * 导出库存列表
     */
    @RequiresPermissions("reserves:statistics:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KStock kStock)
    {
        List<KStock> list = kStockService.selectKStockList(kStock);
        ExcelUtil<KStock> util = new ExcelUtil<KStock>(KStock.class);
        return util.exportExcel(list, "stock");
    }

    /**
     * @description 获取 储备库统计折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOption")
    @ResponseBody
    public List<KStock> getOption(KStock stock) {
        //根据数据库查询每周情况
        List<KStock> list = kStockService.getOption(stock);
        return list;
    }

    /**
     * @description 获取 地区上报统计柱状图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOptionHistogram")
    @ResponseBody
    public List<KStock> getOptionHistogram(KStock stock) {
        List<KStock> list = kStockService.getOptionHistogram(stock);
        return list;

    }

    /**
     * 跳转牧还草工程人工种草报表折线图
     */
    @GetMapping(value = "/Echars")
    public String echarts4(String province, ModelMap mmap){
        mmap.put("province", province);
        return prefix + "/Echars";
    }



}
