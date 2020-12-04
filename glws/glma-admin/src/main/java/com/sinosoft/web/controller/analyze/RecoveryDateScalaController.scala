package com.sinosoft.web.controller.analyze

import java.util

import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.ruoyi.common.core.controller.BaseController
import com.ruoyi.common.core.page.TableDataInfo
import com.sinosoft.integration.domain.RecoveryQuestion
import com.sinosoft.integration.service.RecoveryQuestionService
import org.apache.shiro.authz.annotation.RequiresPermissions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestMapping, ResponseBody}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
  * author lhf
  * date 2020/7/16 9:44
  * version 1.0
  * 返青期
  */
@Controller
@RequestMapping(Array("/analyze/recovdrydatescala"))
class RecoveryDateScalaController @Autowired()(var recoveryQuestionService: RecoveryQuestionService = null)  extends BaseController {

  var prefix : String = "analyze/recoverydate";

  @GetMapping
  def RecoveryDateAnalyze(recoveryQuestion:RecoveryQuestion,mmap: ModelMap) : String = {
    import scala.collection.JavaConversions._
    if (recoveryQuestion.getAddress()==null||recoveryQuestion.getAddress().equals("")){
      recoveryQuestion.setAddress("内蒙古")
    }else{
      mmap.put("address",recoveryQuestion.getAddress())
    }
    recoveryQuestion.setPhenology(1)
    var recoveryQuestions : util.List[RecoveryQuestion] = recoveryQuestionService.selectRecoveryQuestionListOrderBy(recoveryQuestion)
    val buf = new StringBuilder;
    for (rq <- recoveryQuestions){
      buf ++= rq.getAvgTemp.toString
      buf ++="@"
      buf ++= rq.getCompareDayss.toString
      buf ++="#"
    }
    var s=buf.toString()
    mmap.put("s", s)
    mmap.put("recoveryQuestions", recoveryQuestions)
    val arr= Array[Double](2.67, 3.13, 3.25, 3.36, 3.56, 3.72, 3.12, 3.33, 2.12)
    println(Analyze.gm(arr,1));
    return prefix + "/analyze"
  }

  @PostMapping(Array("/list"))
  @ResponseBody
  def list(recoveryQuestion: RecoveryQuestion): TableDataInfo = {
    if (recoveryQuestion.getAddress()==null||recoveryQuestion.getAddress().equals("")){
      recoveryQuestion.setAddress("内蒙古")
    }
    recoveryQuestion.setPhenology(1);
    startPage()
    var lists : util.List[RecoveryQuestion] = recoveryQuestionService.selectRecoveryQuestionList(recoveryQuestion)
    return getDataTable(lists)
  }
}


















