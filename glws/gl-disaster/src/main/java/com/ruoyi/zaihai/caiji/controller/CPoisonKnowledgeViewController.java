package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.CPoisonKnowledge;
import com.ruoyi.zaihai.caiji.service.ICPoisonKnowledgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 毒害草知识库Controller
 * 
 * @author ruoyi
 * @date 2020-06-15
 */
@Controller
@RequestMapping("/library/poisonknowledgeView")
public class CPoisonKnowledgeViewController extends BaseController
{
    private String prefix = "system/poisonknowledge";

    @Autowired
    private ICPoisonKnowledgeService cPoisonKnowledgeService;

    @RequiresPermissions("library:poisonknowledgeView:view")
    @GetMapping()
    public String knowledge()
    {
        return prefix + "/poisonknowledgeView";
    }

    /**
     * 查询【毒害草知识库信息列表
     */
    @RequiresPermissions("library:poisonknowledgeView:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CPoisonKnowledge cPoisonKnowledge)
    {
        startPage();
        List<CPoisonKnowledge> list = cPoisonKnowledgeService.selectCPoisonKnowledgeList(cPoisonKnowledge);
        return getDataTable(list);
    }

    /**
     * 导出【毒害草知识库信息列表
     */
    @RequiresPermissions("library:poisonknowledgeView:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CPoisonKnowledge cPoisonKnowledge)
    {
        List<CPoisonKnowledge> list = cPoisonKnowledgeService.selectCPoisonKnowledgeList(cPoisonKnowledge);
        ExcelUtil<CPoisonKnowledge> util = new ExcelUtil<CPoisonKnowledge>(CPoisonKnowledge.class);
        return util.exportExcel(list, "knowledge");
    }




    /**
     * 查看病害知识库
     */
    @RequiresPermissions("library:poisonknowledgeView:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap,  CPoisonKnowledge cPoisonKnowledge)
    {
        CPoisonKnowledge cPoisonKnowledgeLook = cPoisonKnowledgeService.selectCPoisonKnowledgeById(id);
        mmap.put("cPoisonKnowledge", cPoisonKnowledgeLook);

        return prefix + "/look";
    }

}