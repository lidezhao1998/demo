package com.sinosoft.web.controller.integration;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.sinosoft.integration.domain.DataSync;
import com.sinosoft.integration.service.DataSyncService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @date 2020/6/11 17:05
 */
@Controller
@RequestMapping("/integration/dataSync")
public class DataSyncController extends BaseController {
    private String prefix = "integration/dataSync";
    @Autowired
    DataSyncService dataSyncService;
    @GetMapping
    public String DataSync(){
        return prefix+"/dataSyncList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DataSync dataSync)
    {
        startPage();
        List<DataSync> list = dataSyncService.selectDataSyncList(dataSync);
        return getDataTable(list);
    }
}
