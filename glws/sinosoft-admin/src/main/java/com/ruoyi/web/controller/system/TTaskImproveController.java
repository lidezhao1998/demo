package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Improve;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangduo
 * @create 2020-01-07
 * @description  改良分省报表Controller
 */
@Controller
@RequestMapping("/system/improve")
public class TTaskImproveController extends BaseController {

    private String prefix = "/system/improve";

    @Autowired
    private ITTaskReportService tTaskReportService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @RequiresPermissions("system:improve:view")
    @GetMapping
    public String improve(){
        return prefix + "/improve";
    }

    /*
        查询改良分省报表列表
     */
    @RequiresPermissions("system:improve:list")
    @PostMapping("/list")
    @ResponseBody
    public Improve list(TTaskReport tTaskReport){
        startPage();
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskReport.getProvince());
        tTaskReport.setProvince(provinceLabel);
        //查询退牧还草工程任务领取分解表所有数据
        List<TTaskReport> listData = tTaskReportService.selectTTaskReportList(tTaskReport);
        //存储处理数据
        Improve improve = new Improve();
        /**
         * 从退牧还草工程任务领取分解表查询所有年份
         */
        int minYear = Integer.parseInt(listData.stream().min(Comparator.comparing(TTaskReport::getYear)).get().getYear());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearRange = new ArrayList<>();
        for (int i = minYear;i <= currentYear;i++ ){
            yearRange.add(i);
        }
        Collections.reverse(yearRange);
        improve.setYearCols(yearRange);
        /**
         * 查询出所有的省份
         */
        Set<String> provinceDistinct = new HashSet<>();
        listData.forEach(item -> provinceDistinct.add(item.getProvince()));
        improve.setProvinceRows(provinceDistinct);

        /**
         * 将省份、年份进行处理（省份：此年份中所有数值之和）
         */
        List<Map<String,String>> datas = new ArrayList<>();
        provinceDistinct.forEach(province -> {
            Map<String, String> map = new HashMap<>();
            yearRange.forEach(year ->{
                double sum = listData.stream()
                        .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getProvince().equals(province))
                        .mapToDouble(TTaskReport::getThcyglSize).sum();
                map.put(String.valueOf(year),String.valueOf(sum));
            });
            map.put("province",province);
            datas.add(map);
        });
        improve.setDatas(datas);
        return improve;
    }

    /**
     * 导出改良分省报表列表
     */
    @RequiresPermissions("system:improve:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskReport tTaskReport)
    {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskReport.getProvince());
        tTaskReport.setProvince(provinceLabel);
        //获取当前时间
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        //生成报表
        List<TTaskReport> list = tTaskReportService.selectEnclosureList(tTaskReport);
        ExcelUtil<TTaskReport> util = new ExcelUtil<TTaskReport>(TTaskReport.class);
        return util.exportExcel(list, dateFormat.format(date)+"-改良分省报表");
    }


   /* @RequiresPermissions("system:improve:year")
    @PostMapping("/year")
    @ResponseBody
    public List<TTaskResolve> selectTTaskResolveByYear(TTaskResolve tTaskResolve){
        System.out.println("<><>>>>>>>>>><><><><><>><<<>>><>><");
        System.out.println(itTaskResolveService.selectTTaskResolveByYear(tTaskResolve));
            return itTaskResolveService.selectTTaskResolveByYear(tTaskResolve);
    }*/







}
