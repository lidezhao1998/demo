package com.sinosoft.web.controller.analyze;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
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

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/7/10 10:33
 * 枯黄期分析
 */
@Controller
@RequestMapping("/analyze/withered")
public class WitheredAnalyzeController extends BaseController {
    private String prefix = "analyze/withered";
    @Autowired
    RecoveryQuestionService recoveryQuestionService;

    @GetMapping
    public String RecoveryDateAnalyze(RecoveryQuestion recoveryQuestion, ModelMap mmap){
        if (recoveryQuestion.getAddress()==null||recoveryQuestion.getAddress().equals("")){
            recoveryQuestion.setAddress("内蒙古自治区");
        }
        mmap.put("address",recoveryQuestion.getAddress());
        recoveryQuestion.setPhenology(3);
        List<RecoveryQuestion> recoveryQuestions = recoveryQuestionService.selectRecoveryQuestionListOrderBy(recoveryQuestion);
        double[] d=new double[recoveryQuestions.size()];
        for(int i=0;i<recoveryQuestions.size();i++){
            d[i]=Double.parseDouble(recoveryQuestions.get(i).getCompareDayss()+"");
        }
        //System.out.println(Analyze.gm(d,1));
        mmap.put("recoveryQuestions",recoveryQuestions);
        return  prefix+"/analyze";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RecoveryQuestion recoveryQuestion)
    {
        recoveryQuestion.setPhenology(3);
        if (recoveryQuestion.getAddress()==null||recoveryQuestion.getAddress().equals("")){
            recoveryQuestion.setAddress("内蒙古自治区");
        }
        startPage();
        List<RecoveryQuestion> list = recoveryQuestionService.selectRecoveryQuestionList(recoveryQuestion);
        return getDataTable(list);
    }

}
