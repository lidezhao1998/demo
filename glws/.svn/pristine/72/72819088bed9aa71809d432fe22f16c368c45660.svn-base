package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.CDataManagement;
import com.ruoyi.zaihai.caiji.service.ICDataManagementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 防治组织信息管理Controller
 *
 * @author ruoyi
 * @date 2020-05-29*/


@Controller
@RequestMapping("/prevention/select")
public class CDataManagementSelectController extends BaseController
{
    private String prefix = "caiji/select";

    @Autowired
    private ICDataManagementService cDataManagementService;

    @RequiresPermissions("prevention:select:view")
    @GetMapping()
    public String management()
    {
        return prefix + "/select";
    }

    /*
     * 查询防治组织信息管理列表*/


    @RequiresPermissions("prevention:select:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CDataManagement cDataManagement)
    {
        startPage();
        cDataManagement.setStatus("1");
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
        mmap.put("cDataManagement",cDataManagement);
        return prefix + "/detail";
    }


    /**
     * 地图
     */
    @GetMapping("/map")
    public String map()
    {
        CDataManagement cDataManagement=new CDataManagement();
        cDataManagement.getRegisteredAddress();
        return prefix + "/map";
    }




    /*
     * 导出防治组织信息管理列表*/


    @RequiresPermissions("prevention:select:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CDataManagement cDataManagement)
    {
        List<CDataManagement> list = cDataManagementService.selectCDataManagementList(cDataManagement);
        ExcelUtil<CDataManagement> util = new ExcelUtil<CDataManagement>(CDataManagement.class);
        return util.exportExcel(list, "select");
    }






}
