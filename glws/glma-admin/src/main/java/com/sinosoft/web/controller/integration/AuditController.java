package com.sinosoft.web.controller.integration;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.sinosoft.integration.domain.Audit;
import com.sinosoft.integration.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 14:45
 */
@Controller
@RequestMapping("/audit")
public class AuditController extends BaseController {
    @Autowired
    AuditService auditService;
    private String prefix = "integration/audit";
    @GetMapping
    public String audit(){
        return  prefix+"/auditList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Audit audit)
    {
        startPage();
        List<Audit> list = auditService.selectAuditList(audit);
        return getDataTable(list);
    }

}
