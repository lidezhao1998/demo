package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import com.ruoyi.zaihai.caiji.service.ICTaskChfzService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 草原虫害发生与防治情况Controller
 * 
 * @author sudonngdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/caiji/chfz")
public class CTaskChfzController extends BaseController
{
    private String prefix = "caiji/chfz";

    @Autowired
    private ICTaskChfzService cTaskChfzService;
    @Autowired
    private ISysDictDataService iSysDictDataService;

    @RequiresPermissions("caiji:chfz:view")
    @GetMapping()
    public String chfz()
    {
        return prefix + "/chfz";
    }

    /**
     * 查询草原虫害发生与防治情况列表
     */
    @RequiresPermissions("caiji:chfz:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskChfz cTaskChfz)
    {
        startPage();
        List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);
        return getDataTable(list);
    }

    /**
     *展开详情信息
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskChfz cTaskChfz = cTaskChfzService.selectCTaskChfzById(id);
        mmap.put("cTaskChfz",cTaskChfz);
        return prefix + "/detail";
    }


    /**
     * 导出草原虫害发生与防治情况列表
     */
    @RequiresPermissions("caiji:chfz:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskChfz cTaskChfz)
    {
        List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);
        ExcelUtil<CTaskChfz> util = new ExcelUtil<CTaskChfz>(CTaskChfz.class);
        return util.exportExcel(list, "chfz");
    }

    /**
     * 新增草原虫害发生与防治情况
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存草原虫害发生与防治情况
     */
    @RequiresPermissions("caiji:chfz:add")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskChfz cTaskChfz)
    {
      /*  String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskChfz.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskChfz.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskChfz.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }*/
        if(cTaskChfz.getWeekTime()==null){
            cTaskChfz.setWeekTime(DateUtils.getNowDate());
        }
        cTaskChfz.setProvince("内蒙古");
        cTaskChfz.setCity("包头市");
        cTaskChfz.setCounty("青山区");
        cTaskChfz.setType("草原虫害发生与防治");
        cTaskChfz.setCode("150204");
        cTaskChfz.setStatus("0");
        return toAjax(cTaskChfzService.insertCTaskChfz(cTaskChfz));
    }

    /**
     * 新增保存草原虫害发生与防治情况
     */
    @RequiresPermissions("caiji:chfz:add1")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    @ResponseBody
    public AjaxResult addSave1(CTaskChfz cTaskChfz)
    {
        /*将字典省份市级县级code代码转化为中文名称*/
        String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskChfz.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskChfz.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskChfz.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }
        cTaskChfz.setProvince(addrProvinceLabel);
        cTaskChfz.setCity(addrCityLabel);
        cTaskChfz.setCounty(addrAreaLabel);
        cTaskChfz.setType("草原虫害发生与防治");
        cTaskChfz.setStatus("1");
        return toAjax(cTaskChfzService.insertCTaskChfz(cTaskChfz));
    }

    /**
     * 修改草原虫害发生与防治情况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskChfz cTaskChfz = cTaskChfzService.selectCTaskChfzById(id);
        //if(cTaskChfz.getProvince().equals())
        mmap.put("cTaskChfz", cTaskChfz);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原虫害发生与防治情况
     */
    @RequiresPermissions("caiji:chfz:edit")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskChfz cTaskChfz)
    {
        cTaskChfz.setType("草原虫害发生与防治");
        cTaskChfz.setStatus("0");
        return toAjax(cTaskChfzService.updateCTaskChfz(cTaskChfz));
    }


    /**
     * 修改保存草原虫害发生与防治情况
     */
    @RequiresPermissions("caiji:chfz:edit1")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit1")
    @ResponseBody
    public AjaxResult editSave1(CTaskChfz cTaskChfz)
    {
        cTaskChfz.setType("草原虫害发生与防治");
        cTaskChfz.setStatus("1");
        return toAjax(cTaskChfzService.updateCTaskChfz(cTaskChfz));
    }

    /**
     * 删除草原虫害发生与防治情况
     */
    @RequiresPermissions("caiji:chfz:remove")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cTaskChfzService.deleteCTaskChfzByIds(ids));
    }

    /**
     * 自动生成编码草原虫害发生与防治情况
     */
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.DELETE)
    @GetMapping ( "/CodeAutogain")
    @ResponseBody
    public String CodeAutogain()
    {
        String strCode="";
        String date=DateUtils.dateTimeNow();
        strCode="CHFZ"+date+strCode;
        return strCode;
    }

}
