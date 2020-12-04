package com.ruoyi.web.controller.system;

import java.text.DecimalFormat;
import java.util.List;

import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.service.ITTaskPublishService;
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
import com.ruoyi.system.domain.TTaskRecord;
import com.ruoyi.system.service.ITTaskRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 退牧还草工程任务调整记录Controller
 * 
 * @author liuhongfei
 * @date 2020-09-22
 */
@Controller
@RequestMapping("/system/record")
public class TTaskRecordController extends BaseController
{
    private String prefix = "system/record";

    @Autowired
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private ITTaskRecordService tTaskRecordService;

    @RequiresPermissions("system:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询退牧还草工程任务调整记录列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskRecord tTaskRecord)
    {
        startPage();
        List<TTaskRecord> list = tTaskRecordService.selectTTaskRecordList(tTaskRecord);
        return getDataTable(list);
    }

    /**
     * 查看详情
     */
    @GetMapping("/adjustmentDetail/{recordId}")
    public String adjustmentDetail(@PathVariable("recordId") Long recordId, ModelMap mmap) {
        TTaskRecord tTaskRecord = tTaskRecordService.selectTTaskRecordById(recordId);
        mmap.put("tTaskRecord", tTaskRecord);
        return "system/pulish/adjustmentDetail";
    }

    /**
     * 导出退牧还草工程任务调整记录列表
     */
    @RequiresPermissions("system:record:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskRecord tTaskRecord)
    {
        List<TTaskRecord> list = tTaskRecordService.selectTTaskRecordList(tTaskRecord);
        ExcelUtil<TTaskRecord> util = new ExcelUtil<TTaskRecord>(TTaskRecord.class);
        return util.exportExcel(list, "record");
    }


}
