package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 表单相关
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/regional")
public class RegionalController extends BaseController
{
    private String prefix = "system/regional";

    @Autowired
    private ISysDictDataService iSysDictDataService;

    /**
     * 左右互选组件
     */
    @GetMapping()
    public String regional()
    {
        return prefix + "/regional";
    }

    /**
     * 修改审核状态
     */
    @GetMapping("/change")
    public String change(ModelMap mmap) {
        return prefix + "/change";
    }

    @PostMapping(value = "/change", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public AjaxResult change(@RequestBody Map<String,List<Long>> map) {
        List<Long> selected = map.get("selected");
        List<Long> nonselected = map.get("nonselected");
        SysDictData sysDictData = new SysDictData();
        for (int i = 0; i <selected.size() ; i++) {
            Long dictCode = selected.get(i);
            sysDictData.setDictCode(dictCode);
            sysDictData.setStatus("1");
            iSysDictDataService.updateDictData(sysDictData);
        }
        for (int i = 0; i <nonselected.size() ; i++) {
            Long dictCode = nonselected.get(i);
            sysDictData.setDictCode(dictCode);
            sysDictData.setStatus("0");
            iSysDictDataService.updateDictData(sysDictData);
        }
        return toAjax(iSysDictDataService.updateDictData(sysDictData));
    }

}

