package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Improve;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 改良分省报表
 */

@Controller
@RequestMapping("system/division")
public class TTaskDivisionController extends BaseController
{
    private String prefix = "system/division";

    @Autowired
    private ITTaskPublishService itTaskPublishService;
    @Autowired
    private ISysDictDataService iSysDictDataService1;

    @RequiresPermissions("system:division:view")

    @GetMapping()
    public String gailiang() {
        return prefix + "/division";
    }

    /**
     * 查询
     */
    @RequiresPermissions("system:division:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskPublish tTaskpublish,String provinc, String yea)
    {
        startPage();
        if(provinc != null || yea !=null){
            tTaskpublish.setProvince(provinc);
            tTaskpublish.setYear(yea);
        }else if(provinc != null && yea==null){
            tTaskpublish.setProvince(provinc);

        }else if(yea !=null && provinc==null){
            tTaskpublish.setYear(yea);

        }
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService1.selectDictValueToLabel(tTaskpublish.getProvince());
        tTaskpublish.setProvince(provinceLabel);

        DecimalFormat df = new DecimalFormat("#.00");

        List<TTaskPublish> list = itTaskPublishService.selectTTaskPublishList(tTaskpublish);

        //判断库中是否有空值
        for (int i = 0; i < list.size(); i++) {
            TTaskPublish tTaskPublish =  list.get(i);

            if(tTaskPublish.getThzyglSize()==null){
                tTaskPublish.setThzyglSize(0.0);
            }
        }
        //存储处理数据
        Improve improve = new Improve();
        /**
         * 从退牧还草工程任务领取分解表查询所有年份
         */
        int minYear = Integer.parseInt(list.stream().min(Comparator.comparing(TTaskPublish::getYear)).get().getYear());
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
        list.forEach(item -> provinceDistinct.add(item.getProvince()));
        improve.setProvinceRows(provinceDistinct);
        /**
         * 将省份、年份进行处理（省份：此年份中所有数值之和）
         */
        List<Map<String,String>> datas = new ArrayList<>();
        provinceDistinct.forEach(province -> {
            Map<String, String> map = new HashMap<>();
            yearRange.forEach(year ->{
                double sum = list.stream()
                        .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getProvince().equals(province))
                        .mapToDouble(TTaskPublish::getThzyglSize).sum();
                map.put(String.valueOf(year),String.valueOf(sum));
            });
            map.put("province",province);
            datas.add(map);
        });


        for(int i = 0;i<list.size();i++){
            TTaskPublish newTTaskPublish = list.get(i);
            /** 合计亩数 */
            Long publishid = newTTaskPublish.getPublishId();
            String province = newTTaskPublish.getProvince();
            String year = newTTaskPublish.getYear();
            Double thz = newTTaskPublish.getThzyglSize();

            newTTaskPublish.setPublishId(publishid);
            newTTaskPublish.setProvince(province);
            newTTaskPublish.setYear(year);
            if(thz==null){thz=0.0;}
            newTTaskPublish.setThzyglSize(Double.parseDouble(df.format(thz)));
        }
        improve.setDatas(datas);
        return improve;

    }


    /**
     * 导出改良分省报表列表
     */
    @RequiresPermissions("system:division:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskPublish tTaskPublish)
    {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService1.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);

        List<TTaskPublish> list = itTaskPublishService.selectTTaskPublishList(tTaskPublish);
        ExcelUtil<TTaskPublish> util = new ExcelUtil<TTaskPublish>(TTaskPublish.class);
        return util.exportExcel(list, "division");
    }

    @PostMapping("/getOption")
    @ResponseBody
    public List<TTaskPublish> getOption(TTaskPublish tTaskPublish,String province,Long publishid,String year) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService1.selectDictValueToLabel(province);
        tTaskPublish.setProvince(provinceLabel);

        DecimalFormat df = new DecimalFormat("#.00");

        List<TTaskPublish> list = itTaskPublishService.selectTTaskPublishList(tTaskPublish);
        List<TTaskPublish> newlist = new ArrayList<>();

        for(int i = 0;i<list.size();i++) {
            TTaskPublish newTTaskPublish = list.get(i);
            /** 合计投资 */
            Double thz = newTTaskPublish.getThzyglSize();
            newTTaskPublish.setPublishId(publishid);
            newTTaskPublish.setProvince(province);
            newTTaskPublish.setYear(year);
            if(thz==null){thz=0.0;}
            newTTaskPublish.setThzyglSize(Double.parseDouble(df.format(thz)));
        }
        return newlist;
    }


    /**
     * 跳转改良分省报表折线图
     */
    @GetMapping(value = "/Echars")
    public String echarts4(String province, ModelMap mmap){
        mmap.put("province", province);
        return prefix + "/Echars";
    }

}