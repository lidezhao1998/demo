package com.ruoyi.web.controller.eceas;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.service.ISysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.EvalTianbaoConstruction;
import com.ruoyi.system.service.IEvalTianbaoConstructionService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 天然林资源保护工程建设情况统计Controller
 * 
 * @author hdp
 * @date 2020-06-20
 */
@Controller
@RequestMapping("/eceas/projects")
public class EvalTianbaoConstructionController extends BaseController
{
    private String prefix = "eceas/projects";

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private IEvalTianbaoConstructionService evalTianbaoConstructionService;

    @RequiresPermissions("eceas:projects:view")
    @GetMapping()
    public String projects()
    {
        return prefix + "/projects";
    }

    /**
     * 查询天然林资源保护工程建设情况统计列表
     */
    @RequiresPermissions("eceas:projects:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EvalTianbaoConstruction evalTianbaoConstruction)
    {
        startPage();
        String province = iSysDictDataService.selectDictValueToLabel(evalTianbaoConstruction.getArea());
        evalTianbaoConstruction.setArea(province);
        List<EvalTianbaoConstruction> list = evalTianbaoConstructionService.selectEvalTianbaoConstructionList(evalTianbaoConstruction);
        //算合计的时候对空值设置默认值。
        List<EvalTianbaoConstruction> tbList = new ArrayList<EvalTianbaoConstruction>();
        for(int i=0;i<list.size();i++){
            EvalTianbaoConstruction eval = list.get(i);
            if(eval.getAirSendAfforest()==null){
                eval.setAirSendAfforest(0.0);
            }
            if(eval.getNewAfforestTotal()==null){eval.setNewAfforestTotal(0.0);}
            if(eval.getNoNewAfforestArea()==null){eval.setNoNewAfforestArea(0.0);}
            if(eval.getHaveNewAfforestArea()==null){eval.setHaveNewAfforestArea(0.0);}
            if(eval.getDegraRepairArea()==null){eval.setDegraRepairArea(0.0);}
            if(eval.getForestTendArea()==null){eval.setForestTendArea(0.0);}
            if(eval.getYearendAfforestArea()==null){eval.setYearendAfforestArea(0.0);}
            if(eval.getManageAreaTotal()==null){eval.setManageAreaTotal(0.0);}
            if(eval.getNationalForest()==null){eval.setNationalForest(0.0);}
            if(eval.getNationalPublicForest()==null){eval.setNationalPublicForest(0.0);}
            if(eval.getLocalPublicForest()==null){eval.setLocalPublicForest(0.0);}
            tbList.add(eval);

        }

        return getDataTable(tbList);
    }

    /**
     * 导出天然林资源保护工程建设情况统计列表
     */
    @RequiresPermissions("eceas:projects:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EvalTianbaoConstruction evalTianbaoConstruction)
    {
        List<EvalTianbaoConstruction> list = evalTianbaoConstructionService.selectEvalTianbaoConstructionList(evalTianbaoConstruction);
        ExcelUtil<EvalTianbaoConstruction> util = new ExcelUtil<EvalTianbaoConstruction>(EvalTianbaoConstruction.class);
        return util.exportExcel(list, "projects");
    }

    /**
     * 新增天然林资源保护工程建设情况统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存天然林资源保护工程建设情况统计
     */
    @RequiresPermissions("eceas:projects:add")
    @Log(title = "天然林资源保护工程建设情况统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EvalTianbaoConstruction evalTianbaoConstruction)
    {
        String province = iSysDictDataService.selectDictValueToLabel(evalTianbaoConstruction.getArea());
        evalTianbaoConstruction.setArea(province);
        return toAjax(evalTianbaoConstructionService.insertEvalTianbaoConstruction(evalTianbaoConstruction));
    }

    /**
     * 修改天然林资源保护工程建设情况统计
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EvalTianbaoConstruction evalTianbaoConstruction = evalTianbaoConstructionService.selectEvalTianbaoConstructionById(id);
        mmap.put("evalTianbaoConstruction", evalTianbaoConstruction);
        return prefix + "/edit";
    }

    /**
     * 修改保存天然林资源保护工程建设情况统计
     */
    @RequiresPermissions("eceas:projects:edit")
    @Log(title = "天然林资源保护工程建设情况统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EvalTianbaoConstruction evalTianbaoConstruction)
    {
        return toAjax(evalTianbaoConstructionService.updateEvalTianbaoConstruction(evalTianbaoConstruction));
    }

    /**
     * 删除天然林资源保护工程建设情况统计
     */
    @RequiresPermissions("eceas:projects:remove")
    @Log(title = "天然林资源保护工程建设情况统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(evalTianbaoConstructionService.deleteEvalTianbaoConstructionByIds(ids));
    }


    /**
     * 进入生态评价界面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/eveal/{id}")
    public String eveal(@PathVariable("id") Long id, ModelMap mmap){
        EvalTianbaoConstruction evalTianbaoConstruction = evalTianbaoConstructionService.selectEvalTianbaoConstructionById(id);
        mmap.put("evalTianbaoConstruction", evalTianbaoConstruction);
        return prefix + "/eveal";
    }

}
