package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import com.ruoyi.zaihai.caiji.domain.CTaskSelectAll;
import com.ruoyi.zaihai.caiji.service.ICTaskSczjService;
import com.ruoyi.zaihai.caiji.service.ICTaskSelectAllService;
import com.ruoyi.zaihai.workflow.domain.FlowWrite;
import com.ruoyi.zaihai.workflow.service.IFlowWriteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 草原鼠害发生与防治情况Controller
 *
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/caiji/sczjjassess")
public class CTaskSczjjAssessController extends BaseController {
    private String prefix = "caiji/fangzhi";

    @Autowired
    private ICTaskSczjService cTaskSczjService;

    @Autowired
    private ICTaskSelectAllService icTaskSelectAllService;
    @Autowired
    private IFlowWriteService iFlowWriteService;

    @RequiresPermissions("caiji:sczjjassess:view")
    @GetMapping()
    public String sczjj() {
        return prefix + "/sczjjassess";
    }

    /**
     * 查询草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("caiji:sczjjassess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskSczj cTaskSczj) {
        startPage();
        //List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
        SysUser currentUser = ShiroUtils.getSysUser();
        List<FlowWrite> list = iFlowWriteService.selectFlowWriteListdai(String.valueOf(currentUser.getUserId()));
        return getDataTable(list);
    }

    @GetMapping("/detail1/{id}")
    public String detail1(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskSczj cTaskSczj = cTaskSczjService.selectCTaskSczjById(id);
        mmap.put("cTaskSczj",cTaskSczj);
        return prefix + "/detail1";
    }

    /**
     * 导出草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("caiji:sczjjassess:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskSczj cTaskSczj) {
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
        ExcelUtil<CTaskSczj> util = new ExcelUtil<CTaskSczj>(CTaskSczj.class);
        return util.exportExcel(list, "sczjjassess");
    }


    /**
     * 修改草原鼠害发生与防治情况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CTaskSelectAll cTaskSelectAll = icTaskSelectAllService.selectCTaskSelectAllById(id);
        mmap.put("cTaskSelectAll", cTaskSelectAll);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczjj:edit")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskSelectAll cTaskSelectAll) {


        cTaskSelectAll.setStatus("1");
        return toAjax(icTaskSelectAllService.updateCTaskSelectAll(cTaskSelectAll));
    }

    /**
     * 修改保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczjj:edit1")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit1")
    @ResponseBody
    public AjaxResult editSave1(CTaskSelectAll cTaskSelectAll) {


        cTaskSelectAll.setStatus("1");
        return toAjax(icTaskSelectAllService.updateCTaskSelectAll(cTaskSelectAll));
    }



}