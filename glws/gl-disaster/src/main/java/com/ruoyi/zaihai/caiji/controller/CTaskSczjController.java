package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import com.ruoyi.zaihai.caiji.service.ICTaskSczjService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 草原鼠害发生与防治情况Controller
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/caiji/sczj")
public class CTaskSczjController extends BaseController
{
    private String prefix = "caiji/sczj";

    @Autowired
    private ICTaskSczjService cTaskSczjService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private ISysDeptService sysDeptService;

    @RequiresPermissions("caiji:sczj:view")
    @GetMapping()
    public String sczj()
    {
        return prefix + "/sczj";
    }

    /**
     * 查询草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("caiji:sczj:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskSczj cTaskSczj)
    {
        startPage();
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
        for (int i = 0; i < list.size(); i++) {
            CTaskSczj taskSczj =  list.get(i);
            /*根据省市区编码显示省市区数据*/
            String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(taskSczj.getProvince());
            String addr = addrProvinceLabel.substring(addrProvinceLabel.length()-1,addrProvinceLabel.length());
            if(addr.equals("市")){
                String addrCityLabel = iSysDictDataService.selectDictValueToLabels(taskSczj.getCity());
                String addrAreaLabel = iSysDictDataService.selectDictValueToLabels(taskSczj.getCounty());
                taskSczj.setProvince(addrProvinceLabel);
                taskSczj.setCity(addrCityLabel);
                taskSczj.setCounty(addrAreaLabel);
            }else{
                String addrCityLabel = iSysDictDataService.selectDictValueToLabel(taskSczj.getCity());
                String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(taskSczj.getCounty());
                taskSczj.setProvince(addrProvinceLabel);
                taskSczj.setCity(addrCityLabel);
                taskSczj.setCounty(addrAreaLabel);
            }
        }
        return getDataTable(list);
    }

    /**
     *展开详情信息
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskSczj cTaskSczj = cTaskSczjService.selectCTaskSczjById(id);
        mmap.put("cTaskSczj",cTaskSczj);
        return prefix + "/detail";
    }



    /**
     * 导出草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("caiji:sczj:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskSczj cTaskSczj)
    {
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
        ExcelUtil<CTaskSczj> util = new ExcelUtil<CTaskSczj>(CTaskSczj.class);
        return util.exportExcel(list, "sczj");
    }

    /**
     * 新增草原鼠害发生与防治情况
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {

        return prefix + "/add";
    }

    /**
     * 新增保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczj:add")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskSczj cTaskSczj)
    {
        /*将字典省份市级县级code代码转化为中文名称*/
      /*  String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskSczj.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskSczj.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskSczj.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }*/
      if(cTaskSczj.getWeekTime()==null){
          cTaskSczj.setWeekTime(DateUtils.getNowDate());
      }
        cTaskSczj.setProvince("内蒙古");
        cTaskSczj.setCity("包头市");
        cTaskSczj.setCounty("青山区");
        cTaskSczj.setCode("150204");
        cTaskSczj.setStatus("0");
        cTaskSczj.setType("草原鼠害发生与防治");
        return toAjax(cTaskSczjService.insertCTaskSczj(cTaskSczj));
    }

    /**
     * 新增保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczj:add1")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    @ResponseBody
    public AjaxResult addSave1(CTaskSczj cTaskSczj)
    {
        String addrProvinceLabel = iSysDictDataService.selectDictValueToLabel(cTaskSczj.getProvince());
        String addrCityLabel = iSysDictDataService.selectDictValueToLabel(cTaskSczj.getCity());
        String addrAreaLabel = iSysDictDataService.selectDictValueToLabel(cTaskSczj.getCounty());
        if(!addrProvinceLabel.equals("内蒙古自治区")&&!addrProvinceLabel.equals("黑龙江省")){
            addrProvinceLabel = addrProvinceLabel.substring(0,2);
        }
        else{
            addrProvinceLabel = addrProvinceLabel.substring(0,3);
        }
        cTaskSczj.setProvince(addrProvinceLabel);
        cTaskSczj.setCity(addrCityLabel);
        cTaskSczj.setCounty(addrAreaLabel);
        cTaskSczj.setStatus("1");
        cTaskSczj.setType("草原鼠害发生与防治");
        return toAjax(cTaskSczjService.insertCTaskSczj(cTaskSczj));
    }

    /**
     * 修改草原鼠害发生与防治情况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskSczj cTaskSczj = cTaskSczjService.selectCTaskSczjById(id);

        mmap.put("cTaskSczj", cTaskSczj);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczj:edit")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskSczj cTaskSczj)
    {
        cTaskSczj.setStatus("0");
        cTaskSczj.setType("草原鼠害发生与防治");
        return toAjax(cTaskSczjService.updateCTaskSczj(cTaskSczj));
    }

    /**
     * 修改保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczj:edit1")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit1")
    @ResponseBody
    public AjaxResult editSave1(CTaskSczj cTaskSczj)
    {
        cTaskSczj.setStatus("1");
        cTaskSczj.setType("草原鼠害发生与防治");
        return toAjax(cTaskSczjService.updateCTaskSczj(cTaskSczj));
    }

    /**
     * 删除草原鼠害发生与防治情况
     */
    @RequiresPermissions("caiji:sczj:remove")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cTaskSczjService.deleteCTaskSczjByIds(ids));
    }


    /**
     * 上报草原鼠害发生与防治情况任务
     */
    @RequiresPermissions("caiji:sczj:report")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping( "/report")
    @ResponseBody
    public AjaxResult report(CTaskSczj cTaskSczj,String ids)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        String ancestors=sysDeptService.selectDeptById(Dept.getDeptId()).getAncestors();
        String[] ayyyancestors=ancestors.split(",");
        long id =currentUser.getUserId();
        if(id!=0){
            cTaskSczj.setCreateBy(String.valueOf(id));
        }

        return toAjax(cTaskSczjService.updateCTaskSczjStatus(Long.parseLong(ids)));


    }


    /**
     * 自动生成编码草原鼠害发生与防治情况
     */
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.DELETE)
    @GetMapping ( "/CodeAutogain")
    @ResponseBody
    public String CodeAutogain()
    {
        String strCode="";
        String date= DateUtils.dateTimeNow();
        strCode="SCZJ"+date+strCode;
        return strCode;
    }
}
