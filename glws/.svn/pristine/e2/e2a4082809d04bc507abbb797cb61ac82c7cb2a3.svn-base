package com.sinosoft.web.controller.common;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author sunlei
 * @description 省市联动
 * @date 2020/11/04/11:00
 */
@Controller
@RequestMapping("common/pcc")
public class ProvinceCityCounty extends BaseController {
    @Autowired
    SysDictDataMapper dictDataMapper;
    /**
     * 三级联动省级
     */

    @GetMapping("/getProvinces")
    @ResponseBody
    public List<SysDictData> getProvinces(ModelMap mmap)
    {
        String dictType="sys_province";
        List<SysDictData> list=dictDataMapper.selectDictDataByType(dictType);
        return list;
    }
    /**
     * 三级联动市级
     */

    @GetMapping("/getCities")
    @ResponseBody
    public List<SysDictData> getCities(ModelMap mmap, String provinceCode)
    {
        List<SysDictData> list=dictDataMapper.getCities(provinceCode);
        return list;
    }
    /**
     * 三级联动曲县级
     */
    @GetMapping("/getAreas")
    @ResponseBody
    public List<SysDictData> getAreas(ModelMap mmap, String cityCode)
    {
        List<SysDictData> list=dictDataMapper.getAreas(cityCode);

        return list;
    }

    /**
     * 四级联动曲县级
     */
    @GetMapping("/getdz")
    @ResponseBody
    public List<SysDictData> getdz( ModelMap mmap,String areasCode)
    {
        List<SysDictData> list=dictDataMapper.getAreas(areasCode);

        return list;
    }

}
