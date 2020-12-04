package com.sinosoft.web.controller.analyze;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.sinosoft.analyze.domain.ProductionAnalyze;
import com.sinosoft.integration.domain.RecoveryQuestion;
import com.sinosoft.integration.service.RecoveryQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/7/6 10:52
 * 返青期分析
 */
@Controller
@RequestMapping("/analyze/recovdrydate")
public class RecoveryDateAnalyzeController extends BaseController {
    private String prefix = "analyze/recoverydate";
    @Autowired
    RecoveryQuestionService recoveryQuestionService;

    @GetMapping
    public String RecoveryDateAnalyze(RecoveryQuestion recoveryQuestion,ModelMap mmap){
        if (recoveryQuestion.getAddress()==null||recoveryQuestion.getAddress().equals("")){
            recoveryQuestion.setAddress("内蒙古自治区");
        }
        mmap.put("address",recoveryQuestion.getAddress());
        recoveryQuestion.setPhenology(1);
        List<RecoveryQuestion> recoveryQuestions = recoveryQuestionService.selectRecoveryQuestionListOrderBy(recoveryQuestion);

        Double[] lineAna = new Double[2];
        List lineAnas=new ArrayList<>();
        recoveryQuestions.forEach(rq -> {
            lineAna[0]=(double) rq.getCompareDayss();
            lineAna[1]= rq.getAvgTemp();
            lineAnas.add(lineAna);
        });
        mmap.put("lineAnas",lineAnas);
        mmap.put("recoveryQuestions",recoveryQuestions);
        return  prefix+"/analyze";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RecoveryQuestion recoveryQuestion)
    {
        recoveryQuestion.setPhenology(1);
        if (recoveryQuestion.getAddress()==null||recoveryQuestion.getAddress().equals("")){
            recoveryQuestion.setAddress("内蒙古自治区");
        }
        startPage();
        List<RecoveryQuestion> list = recoveryQuestionService.selectRecoveryQuestionList(recoveryQuestion);
        return getDataTable(list);
    }

}
