package com.ruoyi.web.controller.eceas;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.FileResources;
import com.ruoyi.system.service.IFileResourcesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  水源涵养导入文件审核Controller
 *  Created by hdp on 2020/9/25.
 */
@Controller
@RequestMapping("/eceas/waterFile")
public class WaterFileAuditController extends BaseController{

    private String prefix = "eceas/waterFile";

    @Autowired
    private IFileResourcesService fileResourcesService;

    @RequiresPermissions("eceas:waterFile:view")
    @GetMapping()
    public String waterFile(){
        return prefix + "/waterFileAuditList";
    }

    @RequiresPermissions("eceas:waterFile:waterFileAuditList")
    @PostMapping("/waterFileAuditList")
    @ResponseBody
    public TableDataInfo waterFileAuditList(FileResources fileResources){
        startPage();
        List<FileResources> waterFileAuditList = fileResourcesService.selectFileResourcesList(fileResources);
        return getDataTable(waterFileAuditList);
    }

    /**
     * 审核数据
     * @param fileResources
     * @return
     */
    @RequiresPermissions("eceas:waterFile:edit")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(FileResources fileResources){
        fileResources.setAuditStatus("1");
        return toAjax(fileResourcesService.updateFileResources(fileResources));
    }

    /**
     * 取现审核
     * @param fileResources
     * @return
     */
    @RequiresPermissions("eceas:waterFile:edit")
    @Log(title = "取消审核", businessType = BusinessType.UPDATE)
    @PostMapping("/cancelAudit")
    @ResponseBody
    public AjaxResult cancelAudit(FileResources fileResources){
        fileResources.setAuditStatus("0");
        return toAjax(fileResourcesService.updateFileResources(fileResources));
    }

    /**
     * 批量审核
     * @param fileIds
     * @return
     */
    @Log(title = "批量审核", businessType = BusinessType.UPDATE)
    @PostMapping("/batchAudit")
    @ResponseBody
    public AjaxResult batchAudit(String fileIds){
        String[] fileId = fileIds.split(",");
        return toAjax(fileResourcesService.updateFileResourcesByIds(fileId));
    }

}
