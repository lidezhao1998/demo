package com.ruoyi.web.controller.system;

import java.util.*;

import com.ruoyi.system.domain.Improve;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskBuildService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 舍饲棚围建设任务分省报表
 *
 * @author feiyanxu
 * @date 2019-12-23
 */
@Controller
@RequestMapping("/system/build")
public class TTaskBuildController extends BaseController
{
    private String prefix = "system/build";

    @Autowired
    private ITTaskBuildService taskBuildServiceService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private ITTaskReportService ItaskReportService;

    @Autowired
    private ITTaskPublishService tTaskPublishService;


    @RequiresPermissions("system:build:view")
    @GetMapping()
    public String report(String province,String year,ModelMap mmap)
    {
        System.err.println("========开始");
        mmap.put("province", province);
        mmap.put("year", year);
        return prefix + "/build";
    }

    /**
     * 查询饲棚围建设任务分省报表
     */
    @RequiresPermissions("system:build:list")
    //@GetMapping("/list")
    @PostMapping("/list")
    @ResponseBody
    public Improve list(TTaskPublish TTaskpblish, String provinc, String yea){

            if(provinc != null || yea !=null){
                TTaskpblish.setProvince(provinc);
                TTaskpblish.setYear(yea);
            }else if(provinc != null && yea==null){
                TTaskpblish.setProvince(provinc);

            }else if(yea !=null && provinc==null){
                TTaskpblish.setYear(yea);

            }
            //tTaskReport.setProvince("320000");
            //根据字典键值查询字典标签
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(TTaskpblish.getProvince());
            TTaskpblish.setProvince(provinceLabel);
            //查询退牧还草工程任务领取分解表所有数据
            List<TTaskPublish> listData = tTaskPublishService.selectTTaskPublishList(TTaskpblish);
            //判断库中是否有空值
            for (int i = 0; i < listData.size(); i++) {
                TTaskPublish tTaskPublish =  listData.get(i);

                if(tTaskPublish.getRgscdSize()==null){
                    tTaskPublish.setRgscdSize(0.0);
                }
            }
            //存储处理数据
            Improve improve = new Improve();
            /**
             * 从退牧还草工程任务领取分解表查询所有年份
             */
            int minYear = Integer.parseInt(listData.stream().min(Comparator.comparing(TTaskPublish::getYear)).get().getYear());
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            List<Integer> yearRange = new ArrayList<>();
            for (int i = minYear;i <= currentYear;i++ ){
                yearRange.add(i);
            }
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
                            .mapToDouble(TTaskPublish::getRgscdSize).sum();
                    map.put(String.valueOf(year),String.valueOf(sum));
                });
                map.put("province",province);
                datas.add(map);
            });
            improve.setDatas(datas);
            return improve;
        }



    /**
     * 导出饲棚围建设任务分省报表
     */
    @RequiresPermissions("system:build:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskReport tTaskReport)
    {
        List<TTaskReport> list = taskBuildServiceService.selectTTaskReportList(tTaskReport);
        ExcelUtil<TTaskReport> util = new ExcelUtil<TTaskReport>(TTaskReport.class);
        return util.exportExcel(list, "build");
    }

    /**
     * 饲棚围建设任务分省报表柱状图
     */
    @PostMapping("/getOption")
    @ResponseBody
    public List<TTaskPublish>  getOption(TTaskPublish TTaskpblish,String province,String year)
    {
        if(province.equals("") && year.equals("")){
            List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(TTaskpblish);
            return list;
        }else if(province!=null && province!="" || year!=null && year!=""){
            //根据字典键值查询字典标签
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(province);
            TTaskpblish.setProvince(provinceLabel);
            TTaskpblish.setYear(year);
            List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(TTaskpblish);
            return list;
        }
        return null;

    }
    /**
     * 跳转舍饲棚围建设任务分省报表折线图
     */
    @GetMapping(value = "/Echars")
    public String echarts4(String year, String province, ModelMap mmap){
        mmap.put("province", province);
        mmap.put("year", year);
        return prefix + "/Echars";
    }

}
