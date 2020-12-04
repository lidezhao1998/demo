package com.ruoyi.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskReportService;
import com.ruoyi.system.service.ITTaskResolveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 退牧还草工程进度上报Controller
 * 
 * @author LiuHongfei
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/system/report")
public class TTaskReportController extends BaseController
{
    private String prefix = "system/report";

    @Autowired
    private ITTaskReportService tTaskReportService;

    @Autowired
    private ITTaskResolveService tTaskResolveService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @RequiresPermissions("system:report:view")
    @GetMapping()
    public String report()
    {
        return prefix + "/report";
    }

    /**
     * 查询退牧还草工程任务列表
     */
    @RequiresPermissions("system:report:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskReport TTaskReport, TTaskResolve tTaskResolve, Long resolveId)
    {

        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getProvince());
        tTaskResolve.setProvince(provinceLabel);

        DecimalFormat df = new DecimalFormat("#.00");
        if(resolveId!=null){
            TTaskReport.setResolveId(resolveId);
            List<TTaskReport> list = tTaskReportService.selectTTaskReportListById(TTaskReport);
            return getDataTable(list);
        }
        startPage();
        List<TTaskResolve> list = tTaskResolveService.selectTTaskResolveList(tTaskResolve);
        List<TTaskResolve> newlist = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            TTaskResolve newTTaskReport = list.get(i);
            /** 围栏规模合计 */
            Double jmgm = newTTaskReport.getJmSize();
            Double xmgm = newTTaskReport.getXmSize();
            Double hqmgm = newTTaskReport.getHqlxSize();
            Double smhgm = newTTaskReport.getSmhzlSize();
            if(jmgm==null){jmgm=0.0;}
            if(xmgm==null){xmgm=0.0;}
            if(hqmgm==null){hqmgm=0.0;}
            if(smhgm==null){smhgm=0.0;}
            Double wlScale = jmgm+xmgm+hqmgm+smhgm;
            newTTaskReport.setWlScale(Double.parseDouble(df.format(wlScale)));
            /** 围栏投资合计 */
            Double jmtz = newTTaskReport.getJmMoney();
            Double xmtz = newTTaskReport.getXmMoney();
            Double hqmtz = newTTaskReport.getHqlxMoney();
            Double smhtz = newTTaskReport.getSmhzlMoney();
            if(jmtz==null){jmtz=0.0;}
            if(xmtz==null){xmtz=0.0;}
            if(hqmtz==null){hqmtz=0.0;}
            if(smhtz==null){smhtz=0.0;}
            Double wlInvestment = jmtz+xmtz+hqmtz+smhtz;
            newTTaskReport.setWlInvestment(Double.parseDouble(df.format(wlInvestment)));
            /** 其他规模合计 */
            Double thcgm = newTTaskReport.getThzyglSize();
            Double rgsgm = newTTaskReport.getRgscdSize();
            Double hspgm = newTTaskReport.getHspjSize();
            Double httgm = newTTaskReport.getHttSize();
            Double dhcgm = newTTaskReport.getDhcSize();
            Double ykcgm = newTTaskReport.getYkcyzlSize();
            if(thcgm==null){thcgm=0.0;}
            if(rgsgm==null){rgsgm=0.0;}
            if(hspgm==null){hspgm=0.0;}
            if(httgm==null){httgm=0.0;}
            if(dhcgm==null){dhcgm=0.0;}
            if(ykcgm==null){ykcgm=0.0;}
            Double totalScale = thcgm+rgsgm+hspgm+httgm+dhcgm+ykcgm+wlScale;
            newTTaskReport.setTotalScale(Double.parseDouble(df.format(totalScale)));
            /** 总投资合计 */
            Double thctz = newTTaskReport.getThzyglMoney();
            Double rgstz = newTTaskReport.getRgscdMoney();
            Double hsptz = newTTaskReport.getHspjMoney();
            Double htttz = newTTaskReport.getHttMoney();
            Double dhctz = newTTaskReport.getDhcMoney();
            Double ykctz = newTTaskReport.getYkcazlMoney();
            if(thctz==null){thctz=0.0;}
            if(rgstz==null){rgstz=0.0;}
            if(hsptz==null){hsptz=0.0;}
            if(htttz==null){htttz=0.0;}
            if(dhctz==null){dhctz=0.0;}
            if(ykctz==null){ykctz=0.0;}
            Double totalInvestment = thctz+rgstz+hsptz+htttz+dhctz+ykctz+wlScale;
            newTTaskReport.setTotalInvestment(Double.parseDouble(df.format(totalInvestment)));
            newlist.add(newTTaskReport);
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(newlist);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 查看新增退牧还草工程进度
     */
    @RequestMapping("/add/{resolveId}")
    public String add(@PathVariable("resolveId") Long resolveId, ModelMap mmap)
    {
        TTaskResolve tTaskResolve = tTaskResolveService.selectTTaskResolveById(resolveId);
        mmap.put("tTaskResolve", tTaskResolve);
        return prefix + "/add";
    }

    /**
     * 新增退牧还草工程进度
     */
    @RequiresPermissions("system:report:view")
    @Log(title = "退牧还草工程进度上报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TTaskReport tTaskReport,Long resolveId) {
        //关联父子关系
        tTaskReport.setResolveId(resolveId);
        //获取当前年份
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        tTaskReport.setYear(year+"");
        //变更状态为0，待审核
        tTaskReport.setStatus("0");
        //判断时间刻度冲突
        List<TTaskReport> list = new ArrayList<>();
        if(tTaskReport.getTimeScale().equals("年")){
            list =  tTaskReportService.selectTimeScaleFromYearAndAddress(tTaskReport);
        }
        if(list.size()<1){
            return toAjax(tTaskReportService.insertTTaskReport(tTaskReport));
        }else{
            return AjaxResult.error("年刻度已存在，请检查日期和时间刻度");
        }
    }

    /**
     * 查看退牧还草工程进度
     */
    @GetMapping("/edit/{resolveId}")
    public String edit(@PathVariable("resolveId") Long resolveId, ModelMap mmap)
    {
        TTaskResolve tTaskResolve = tTaskResolveService.selectTTaskResolveById(resolveId);
        mmap.put("tTaskResolve", tTaskResolve);
        return prefix + "/edit";
    }

    /**
     *
     */
    @RequiresPermissions("system:report:view")
    @Log(title = "退牧还草工程进度上报", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TTaskReport tTaskReport)
    {
//        Long resolveId = tTaskReport.getReportId();
//        tTaskReport.setResolveId(resolveId);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        tTaskReport.setYear(year+"");
        return toAjax(1);
    }
}
