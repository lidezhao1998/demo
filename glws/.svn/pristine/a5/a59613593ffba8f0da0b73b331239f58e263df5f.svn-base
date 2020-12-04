package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CTaskScny;
import com.ruoyi.zaihai.caiji.service.ICTaskScnyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 草原鼠虫害防治农药使用况Controller
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/caiji/scny")
public class CTaskScnyController extends BaseController
{
    private String prefix = "caiji/scny";

    @Autowired
    private ICTaskScnyService cTaskScnyService;
    @Autowired
    private ISysDictDataService iSysDictDataService;

    @RequiresPermissions("caiji:scny:view")
    @GetMapping()
    public String scny()
    {
        return prefix + "/scny";
    }

    /**
     * 查询草原鼠虫害防治农药使用况列表
     */
    @RequiresPermissions("caiji:scny:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskScny cTaskScny)
    {
        startPage();
        List<CTaskScny> list = cTaskScnyService.selectCTaskScnyList(cTaskScny);
        return getDataTable(list);
    }
    /**
     *展开详情信息
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskScny cTaskScny = cTaskScnyService.selectCTaskScnyById(id);
        mmap.put("cTaskScny",cTaskScny);
        return prefix + "/detail";
    }

    /**
     * 导出草原鼠虫害防治农药使用况列表
     */
    @RequiresPermissions("caiji:scny:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskScny cTaskScny)
    {
        List<CTaskScny> list = cTaskScnyService.selectCTaskScnyList(cTaskScny);
        ExcelUtil<CTaskScny> util = new ExcelUtil<CTaskScny>(CTaskScny.class);
        return util.exportExcel(list, "scny");
    }

    /**
     * 新增草原鼠虫害防治农药使用况
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("caiji:scny:add")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskScny cTaskScny)
    {
       /* String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskScny.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskScny.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskScny.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }*/

        if(cTaskScny.getWeekTime()==null){
            cTaskScny.setWeekTime(DateUtils.getNowDate());
        }
        cTaskScny.setProvince("内蒙古");
        cTaskScny.setCity("包头市");
        cTaskScny.setCounty("青山区");
        cTaskScny.setStatus("0");
        cTaskScny.setCode("150204");
        cTaskScny.setProjectType(cTaskScny.getType());
        cTaskScny.setType("草原鼠虫害防治农药使用");
        return toAjax(cTaskScnyService.insertCTaskScny(cTaskScny));
    }

    /**
     * 新增保存草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("caiji:scny:add1")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    @ResponseBody
    public AjaxResult addSave1(CTaskScny cTaskScny)
    {
        String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskScny.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskScny.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskScny.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }
        cTaskScny.setProvince(addrProvinceLabel);
        cTaskScny.setCity(addrCityLabel);
        cTaskScny.setCounty(addrAreaLabel);
        cTaskScny.setStatus("1");
        cTaskScny.setType("草原鼠虫害防治农药使用");
        return toAjax(cTaskScnyService.insertCTaskScny(cTaskScny));
    }

    /**
     * 修改草原鼠虫害防治农药使用况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskScny cTaskScny = cTaskScnyService.selectCTaskScnyById(id);
        mmap.put("cTaskScny", cTaskScny);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("caiji:scny:edit")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskScny cTaskScny)
    {
        cTaskScny.setStatus("0");
        cTaskScny.setType("草原鼠虫害防治农药使用");
        return toAjax(cTaskScnyService.updateCTaskScny(cTaskScny));
    }

    /**
     * 修改保存草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("caiji:scny:edit1")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit1")
    @ResponseBody
    public AjaxResult editSave1(CTaskScny cTaskScny)
    {
        cTaskScny.setStatus("1");
        cTaskScny.setType("草原鼠虫害防治农药使用");
        return toAjax(cTaskScnyService.updateCTaskScny(cTaskScny));
    }

    /**
     * 删除草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("caiji:scny:remove")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cTaskScnyService.deleteCTaskScnyByIds(ids));
    }


    /**
     * 自动生成编码农药情况
     */
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.DELETE)
    @GetMapping ( "/CodeAutogain")
    @ResponseBody
    public String CodeAutogain()
    {
        String strCode="";
        String date= DateUtils.dateTimeNow();
        strCode="SCNY"+date+strCode;
        return strCode;
    }
}
