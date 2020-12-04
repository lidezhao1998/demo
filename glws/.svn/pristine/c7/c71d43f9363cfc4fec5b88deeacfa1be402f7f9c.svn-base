package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.CDiseaseKnowledge;
import com.ruoyi.zaihai.caiji.domain.CPoisonKnowledge;
import com.ruoyi.zaihai.caiji.domain.CRatKnowledge;
import com.ruoyi.zaihai.caiji.domain.CRatpestKnowledge;
import com.ruoyi.zaihai.caiji.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 草原病害草知识库信息Controller
 *
 * @author ruoyi
 * @date 2020-04-29
 */
@Controller
@RequestMapping("/library/knowledgeBase")
public class KnowledgeBaseController extends BaseController
{
    private String prefix = "system/knowledgeBase";

    @Autowired
    private KnowledgeBaseService knowledgeBaseService; 

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ICDiseaseKnowledgeService cDiseaseKnowledgeService;


    @Autowired
    private ICRatpestKnowledgeService cRatpestKnowledgeService;

    @Autowired
    private ICPoisonKnowledgeService cPoisonKnowledgeService;

    @Autowired
    private ICRatKnowledgeService cRatKnowledgeService;
    /**
     * 跳转管理页面
     */
    @RequiresPermissions("library:knowledgeBase:view")
    @GetMapping()
    public String knowledge()
    {
        return prefix + "/knowledgeBase";
    }


//    /**
//     * 查询草原病害草知识库信息列表
//     */
//    @RequiresPermissions("library:knowledgeBase:list")
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(CDiseaseKnowledge cDiseaseKnowledge)
//    {
//        startPage();
//        List<CDiseaseKnowledge> list = knowledgeBaseService.selectCDiseaseKnowledgeList(cDiseaseKnowledge);
//        return getDataTable(list);
//    }

//    /**
//     * 导出草原病害草知识库信息列表
//     */
//    @RequiresPermissions("library:knowledgeBase:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(CDiseaseKnowledge cDiseaseKnowledge)
//    {
//        List<CDiseaseKnowledge> list = knowledgeBaseService.selectCDiseaseKnowledgeList(cDiseaseKnowledge);
//        ExcelUtil<CDiseaseKnowledge> util = new ExcelUtil<CDiseaseKnowledge>(CDiseaseKnowledge.class);
//        return util.exportExcel(list, "knowledge");
//    }
  

//    /**
//     * 查看病害知识库
//     */
//    @RequiresPermissions("library:knowledgeBase:detail")
//    @GetMapping("/detail/{id}")
//    public String detail(@PathVariable("id") Long id, ModelMap mmap, Model model,CDiseaseKnowledge cDiseaseKnowledge)
//    {
//        //  List<TTaskResolve> page= taskResolveService.selectTTaskResolveByPublishId(publishId);
//        CDiseaseKnowledge cDiseaseKnowledg = knowledgeBaseService.selectCDiseaseKnowledgeById(id);
//        mmap.put("cDiseaseKnowledge", cDiseaseKnowledg);
//
//        return prefix + "/look";
//    }



    /**
     * 查询毒害草知识库信息列表
     */
    @RequiresPermissions("library:knowledgeBase:list")
    @PostMapping("/dhList")
    @ResponseBody
    public TableDataInfo list(CPoisonKnowledge cPoisonKnowledge,@RequestParam("names") String names,@RequestParam("fenbu") String fenbu)
    {
        startPage();
        cPoisonKnowledge.setDistributionDomestic(fenbu);
        cPoisonKnowledge.setChineseName(names);
        List<CPoisonKnowledge> list = cPoisonKnowledgeService.selectCPoisonKnowledgeList(cPoisonKnowledge);
        return getDataTable(list);
    }


    /**
     * 查询虫害知识库信息列表
     */
    @RequiresPermissions("library:knowledgeBase:list")
    @PostMapping("/chList")
    @ResponseBody
    public TableDataInfo chList(CRatpestKnowledge cRatpestKnowledge,@RequestParam("names") String names,@RequestParam("fenbu") String fenbu) {
        startPage();
        cRatpestKnowledge.setMiddleName(names);
        cRatpestKnowledge.setGnDistribution(fenbu);
        List<CRatpestKnowledge> list = cRatpestKnowledgeService.selectCRatpestKnowledgeList(cRatpestKnowledge);
        return getDataTable(list);
    }

    /**
     * 查询草原病知识库信息列表
     */
    @RequiresPermissions("library:knowledgeBase:list")
    @PostMapping("/bhList")
    @ResponseBody
    public TableDataInfo bhList(CDiseaseKnowledge cDiseaseKnowledge,@RequestParam("names") String names,@RequestParam("fenbu") String fenbu)
    {
        startPage();
        cDiseaseKnowledge.setMiddleName(names);
        cDiseaseKnowledge.setDistribution(fenbu);
        List<CDiseaseKnowledge> list = cDiseaseKnowledgeService.selectCDiseaseKnowledgeList(cDiseaseKnowledge);
        return getDataTable(list);
    }

    /**
     * 查询鼠害知识库信息列表
     */
    @RequiresPermissions("library:mouseknowledgeView:list")
    @PostMapping("/shlist")
    @ResponseBody
    public TableDataInfo shlist(CRatKnowledge cRatKnowledge,@RequestParam("names") String names,@RequestParam("fenbu") String fenbu)
    {
        startPage();
        cRatKnowledge.setChineseName(names);
        cRatKnowledge.setDomesticDistribution(fenbu);
        List<CRatKnowledge> list = cRatKnowledgeService.selectCRatKnowledgeList(cRatKnowledge);
        return getDataTable(list);
    }
}
