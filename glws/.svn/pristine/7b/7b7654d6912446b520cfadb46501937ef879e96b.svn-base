
package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
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
@RequestMapping("system/division2")
public class TTaskStonyController extends BaseController {
    private String prefix1 = "system/division2";

    @Autowired
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private ISysDictDataService iSysDictDataService;


    @RequiresPermissions("system:division2:view")

    @GetMapping()
    public String gailiang(String province, String year, ModelMap mmap) {
        mmap.put("province", province);
        mmap.put("year", year);
        return prefix1 + "/division2";
    }


    /**
     * 查询
     */

    @RequiresPermissions("system:division2:list2")
    @PostMapping("/list2")
    @ResponseBody
    public Improve list2(TTaskPublish tTaskpublish, String provinc, String yea) {
        startPage();
        if (provinc != null || yea != null) {
            tTaskpublish.setProvince(provinc);
            tTaskpublish.setYear(yea);
        } else if (provinc != null && yea == null) {
            tTaskpublish.setProvince(provinc);

        } else if (yea != null && provinc == null) {
            tTaskpublish.setYear(yea);

        }
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskpublish.getProvince());
        tTaskpublish.setProvince(provinceLabel);
        List<TTaskPublish> list2 = tTaskPublishService.selectTTaskPublishList(tTaskpublish);

        //判断库中是否有空值
        for (int i = 0; i < list2.size(); i++) {
            TTaskPublish tTaskPublish = list2.get(i);


            if (tTaskPublish.getSmhzlSize() == null) {
                tTaskPublish.setSmhzlSize(0.0);
            }
        }

        //存储处理数据
        Improve improve = new Improve();


/**
 * 从退牧还草工程任务领取分解表查询所有年份
 */

        int minYear = Integer.parseInt(list2.stream().min(Comparator.comparing(TTaskPublish::getYear)).get().getYear());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearRange = new ArrayList<>();
        for (int i = minYear; i <= currentYear; i++) {
            yearRange.add(i);
        }
        Collections.reverse(yearRange);
        improve.setYearCols(yearRange);


/**
 * 查询出所有的省份
 */

        Set<String> provinceDistinct = new HashSet<>();
        list2.forEach(item -> provinceDistinct.add(item.getProvince()));
        improve.setProvinceRows(provinceDistinct);


/**
 * 将省份、年份进行处理（省份：此年份中所有数值之和）
 */

        List<Map<String, String>> datas= new ArrayList<>();
        provinceDistinct.forEach(province -> {
            Map<String, String> map = new HashMap<>();
            yearRange.forEach(year -> {
                double sum1 = list2.stream()
                        .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getProvince().equals(province))
                        .mapToDouble(TTaskPublish::getSmhzlSize).sum();
                map.put(String.valueOf(year), String.valueOf(sum1));
            });
            map.put("province", province);
            datas.add(map);
        });
        improve.setDatas(datas);
        return improve;
    }


    /**
     * 导出改良分省报表列表
     */

    @RequiresPermissions("system:division2:export2")
    @PostMapping("/export2")
    @ResponseBody
    public AjaxResult export2(TTaskPublish tTaskPublish) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);

        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        ExcelUtil<TTaskPublish> util = new ExcelUtil<TTaskPublish>(TTaskPublish.class);
        return util.exportExcel(list, "division2");
    }

    @PostMapping("/getOption2")
    @ResponseBody
    public List<TTaskPublish> getOption2(TTaskPublish tTaskPublish, String province, Long publishid, String year) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(province);
        tTaskPublish.setProvince(provinceLabel);

        DecimalFormat df = new DecimalFormat("#.00");

        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        List<TTaskPublish> newlist = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            TTaskPublish newTTaskPublish = list.get(i);

/** 合计投资 */

            Double smh = newTTaskPublish.getSmhzlSize();
            newTTaskPublish.setPublishId(publishid);
            newTTaskPublish.setProvince(province);
            newTTaskPublish.setYear(year);
            if (smh == null) {
                smh = 0.0;
            }
            newTTaskPublish.setSmhzlSize(Double.parseDouble(df.format(smh)));
        }
        return newlist;
    }


    /**
     * 跳转改良分省报表折线图
     */

    @GetMapping(value = "/Echars2")
    public String echarts2(String province, ModelMap mmap) {
        mmap.put("province", province);
        return prefix1 + "/Echars2";
    }


}






