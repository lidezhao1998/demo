package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CTaskFznb;
import com.ruoyi.zaihai.caiji.service.ICTaskFznbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 发生防治年报Controller
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/caiji/fznb")
public class CTaskFznbController extends BaseController
{
    private String prefix = "caiji/fznb";

    @Autowired
    private ICTaskFznbService cTaskFznbService;
    @Autowired
    private ISysDictDataService iSysDictDataService;

    @RequiresPermissions("caiji:fznb:view")
    @GetMapping()
    public String fznb()
    {
        return prefix + "/fznb";
    }

    /**
     * 查询发生防治年报列表
     */
    @RequiresPermissions("caiji:fznb:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskFznb cTaskFznb)
    {
        startPage();
        List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbList(cTaskFznb);
        return getDataTable(list);
    }

    /**
     *展开详情信息
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskFznb cTaskFznb = cTaskFznbService.selectCTaskFznbById(id);
        mmap.put("cTaskFznb",cTaskFznb);
        return prefix + "/detail";
    }


    /**
     * 导出发生防治年报列表
     */
    @RequiresPermissions("caiji:fznb:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskFznb cTaskFznb)
    {
        List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbList(cTaskFznb);
        ExcelUtil<CTaskFznb> util = new ExcelUtil<CTaskFznb>(CTaskFznb.class);
        return util.exportExcel(list, "fznb");
    }

    /**
     * 新增发生防治年报
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存发生防治年报
     */
    @RequiresPermissions("caiji:fznb:add")
    @Log(title = "发生防治年报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskFznb cTaskFznb)
    {

        /*String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskFznb.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskFznb.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskFznb.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }*/
        cTaskFznb.setProvince("内蒙古");
        cTaskFznb.setCity("包头市");
        cTaskFznb.setCounty("青山区");
        cTaskFznb.setStatus("0");
        cTaskFznb.setType("发生防治年报");
        cTaskFznb.setCode("150204");
        return toAjax(cTaskFznbService.insertCTaskFznb(cTaskFznb));
    }

    /**
     * 新增保存发生防治年报
     */
    @RequiresPermissions("caiji:fznb:add1")
    @Log(title = "发生防治年报", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    @ResponseBody
    public AjaxResult addSave1(CTaskFznb cTaskFznb)
    {
        String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskFznb.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskFznb.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskFznb.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }
        cTaskFznb.setProvince(addrProvinceLabel);
        cTaskFznb.setCity(addrCityLabel);
        cTaskFznb.setCounty(addrAreaLabel);
        cTaskFznb.setStatus("1");
        cTaskFznb.setType("发生防治年报");
        return toAjax(cTaskFznbService.insertCTaskFznb(cTaskFznb));
    }

    /**
     * 修改发生防治年报
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskFznb cTaskFznb = cTaskFznbService.selectCTaskFznbById(id);
        mmap.put("cTaskFznb", cTaskFznb);
        return prefix + "/edit";
    }

    /**
     * 修改保存发生防治年报
     */
    @RequiresPermissions("caiji:fznb:edit")
    @Log(title = "发生防治年报", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskFznb cTaskFznb)
    {
        cTaskFznb.setType("发生防治年报");
        cTaskFznb.setStatus("0");
        return toAjax(cTaskFznbService.updateCTaskFznb(cTaskFznb));
    }

    /**
     * 修改保存发生防治年报
     */
    @RequiresPermissions("caiji:fznb:edit1")
    @Log(title = "发生防治年报", businessType = BusinessType.UPDATE)
    @PostMapping("/edit1")
    @ResponseBody
    public AjaxResult editSave1(CTaskFznb cTaskFznb)
    {
        cTaskFznb.setType("发生防治年报");
        cTaskFznb.setStatus("1");
        return toAjax(cTaskFznbService.updateCTaskFznb(cTaskFznb));
    }

    /**
     * 删除发生防治年报
     */
    @RequiresPermissions("caiji:fznb:remove")
    @Log(title = "发生防治年报", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cTaskFznbService.deleteCTaskFznbByIds(ids));
    }

    /**
     * 自动生成编码防治年报
     */
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.DELETE)
    @GetMapping ( "/CodeAutogain")
    @ResponseBody
    public String CodeAutogain()
    {
        String strCode="";
        String date= DateUtils.dateTimeNow();
        strCode="FZNB"+date+strCode;
        return strCode;
    }
}
