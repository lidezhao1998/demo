package com.sinosoft.web.controller.integration;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.RecoveryQuestion;
import com.sinosoft.integration.service.RecoveryQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 14:45
 * 返青期调查
 */
@Controller
@RequestMapping("/integration/recoveryQuestion")
public class RecoveryQuestionController extends BaseController {
    @Autowired
    RecoveryQuestionService recoveryQuestionService;
    private String prefix = "integration/recoveryQuestion";
    @GetMapping
    public String recoveryQuestion(){
        return "integration/integrationList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RecoveryQuestion recoveryQuestion)
    {
        startPage();
        List<RecoveryQuestion> list = recoveryQuestionService.selectRecoveryQuestionList(recoveryQuestion);
        return getDataTable(list);
    }


    /**
     * 新增返青期调查
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存返青期调查
     */
    @Log(title = "返青期调查", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated RecoveryQuestion recoveryQuestion)
    {
        if (recoveryQuestion.getCompareWay().equals("推迟")){
            recoveryQuestion.setCompareDayss(-recoveryQuestion.getCompareDayss());
        }
        recoveryQuestion.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(recoveryQuestionService.insertRecoveryQuestion(recoveryQuestion));
    }



    /**
     * 跳转修改返青期调查数据页面
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, ModelMap mmap)
    {
        RecoveryQuestion recoveryQuestion=new RecoveryQuestion();
        recoveryQuestion.setId(id);
        mmap.put("recoveryQuestion", recoveryQuestionService.selectRecoveryQuestion(recoveryQuestion));
        return prefix + "/edit";
    }

/**
     * 跳转修改返青期调查数据页面
     */
    @GetMapping("/details/{id}")
    public String detail(@PathVariable("id") int id, ModelMap mmap)
    {
        RecoveryQuestion recoveryQuestion=new RecoveryQuestion();
        recoveryQuestion.setId(id);
        mmap.put("recoveryQuestion", recoveryQuestionService.selectRecoveryQuestion(recoveryQuestion));
        return prefix + "/details";
    }


    /**
     * 修改返青期调查数据
     */
    @RequiresPermissions("integration:recoveryquestion:edit")
    @Log(title = "返青期调查", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated RecoveryQuestion recoveryQuestion)
    {
        try {
            if (recoveryQuestion.getCompareWay().equals("推迟")){
                recoveryQuestion.setCompareDayss(-recoveryQuestion.getCompareDayss());
            }
            recoveryQuestion.setUpdateBy(ShiroUtils.getLoginName());
            recoveryQuestionService.updateRecoveryQuestion(recoveryQuestion);
        } catch (Exception e) {
            e.printStackTrace();
            return error("修改失败");
        }
        return success("修改成功");
    }

    /**
     * 删除参数配置
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(recoveryQuestionService.deleteRecoveryQuestions(ids));
    }


    @Log(title = "物候期数据审核", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(RecoveryQuestion recoveryQuestion,int id){
        recoveryQuestion.setId(id);
        recoveryQuestion.setStatus(1);
        return toAjax(recoveryQuestionService.updateRecoveryQuestion(recoveryQuestion));
    }


    @Log(title = "物候期数据审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditAll")
    @ResponseBody
    public AjaxResult auditAll(String ids){
        String[] id=ids.split(",");
        return toAjax(recoveryQuestionService.updateStatusByIds(id));
    }

    @Log(title = "物候期数据取消审核", businessType = BusinessType.UPDATE)
    @PostMapping("/unaudit")
    @ResponseBody
    public AjaxResult unaudit(RecoveryQuestion recoveryQuestion,int id){
        recoveryQuestion.setId(id);
        recoveryQuestion.setStatus(0);
        return toAjax(recoveryQuestionService.updateRecoveryQuestion(recoveryQuestion));
    }




}
