package com.ruoyi.zaihai.analysis.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import com.ruoyi.zaihai.caiji.service.ICTaskChfzService;
import com.ruoyi.zaihai.caiji.service.ICTaskSczjService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020/6/15 17:57
 * 生产力分析
 */
@Controller
@RequestMapping("/analysis/earlyWarning")
public class earlyWarningController extends BaseController {

    @Autowired
    private ICTaskChfzService cTaskChfzService;

    @Autowired
    private ICTaskSczjService cTaskSczjService;

    private String prefix = "analysis/ealyWarning";

    @GetMapping
    public String ProductionAnalyze(CTaskSczj cTaskSczj,CTaskChfz cTaskChfz, String xs,String type, ModelMap mmap){


        return prefix+"/earlyWarning";
    }


}
