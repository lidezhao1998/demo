package com.sinosoft.web.controller.analyze;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.ISysDictDataService;
import com.sinosoft.analyze.domain.ProvinceProductionAnalyze;
import com.sinosoft.analyze.service.ProvinceProductionAnalyzeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/16 16:39
 */
@Controller
@RequestMapping("/analyze/province")
public class ProvinceProductionAnalyzeController extends BaseController {
    private String prefix = "analyze/province";
    @Autowired
    ProvinceProductionAnalyzeService provinceProductionAnalyzeService;

    @Autowired
    ISysDictDataService iSysDictDataService;

    @GetMapping
    public String ProvinceProductionAnalyze(ModelMap mmap){
        List<String> years=provinceProductionAnalyzeService.selectYearGroup();
        List<String> provinces=provinceProductionAnalyzeService.selectProductionGroup();
        Map<String,List> mapValue=new HashMap<>();
        for (String province:provinces) {
            ProvinceProductionAnalyze provinceProductionAnalyze =  new ProvinceProductionAnalyze();
            provinceProductionAnalyze.setProvince(province);
            List<ProvinceProductionAnalyze> list = provinceProductionAnalyzeService.selectProvinceProductionAnalyzeList(provinceProductionAnalyze);
            mapValue.put(province,list);
        }

        mmap.put("provinces",provinces);
        mmap.put("years", years);
        mmap.put("mapValue",mapValue);
        return prefix+"/analyze";
    }

    @PostMapping("/indexShow")
    @ResponseBody
    public Map indexShow(){
        Map result=new HashMap();
        List<String> years=provinceProductionAnalyzeService.selectYearGroup();
        List<String> provinces=provinceProductionAnalyzeService.selectProductionGroup();
        Map<String,List> mapValue=new HashMap<>();
        for (String province:provinces) {
            ProvinceProductionAnalyze provinceProductionAnalyze =  new ProvinceProductionAnalyze();
            provinceProductionAnalyze.setProvince(province);
            List<ProvinceProductionAnalyze> list = provinceProductionAnalyzeService.selectProvinceProductionAnalyzeList(provinceProductionAnalyze);
            mapValue.put(province,list);
        }

        result.put("provinces",provinces);
        result.put("years", years);
        result.put("mapValue",mapValue);
        return result;
    }


    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProvinceProductionAnalyze provinceProductionAnalyze)
    {
        startPage();
        List<ProvinceProductionAnalyze> list = provinceProductionAnalyzeService.selectProvinceProductionAnalyzeList(provinceProductionAnalyze);
        return getDataTable(list);
    }

    @GetMapping("/map")
    public String map(ProvinceProductionAnalyze provinceProductionAnalyze,ModelMap mmp)
    {
        List<String> years=provinceProductionAnalyzeService.selectYearGroup();
        ProvinceProductionAnalyze entity=new ProvinceProductionAnalyze();
        if(provinceProductionAnalyze.getYear()==null||provinceProductionAnalyze.getYear().equals("")){
            entity.setYear(years.get(0));
        }else{
            entity.setYear(provinceProductionAnalyze.getYear());
        }
        List<ProvinceProductionAnalyze> ppas = provinceProductionAnalyzeService.selectProvinceProductionAnalyzeList(entity);
        mmp.put("ppas",ppas);
        mmp.put("years",years);
        mmp.put("year",provinceProductionAnalyze.getYear());
        return prefix+"/map";
    }

}
