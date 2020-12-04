package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 退牧还草工程资金汇总报表Controller
 *
 * @author LiuHongfei
 * @date 2019-12-19
 */
@Controller
@RequestMapping("/system/summary")
public class TTaskSummaryController extends BaseController
{
    private String prefix = "system/summary";

    @Autowired
    private ITTaskReportService tTaskReportService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private ITTaskPublishService tTaskPublishService;

    @RequiresPermissions("system:summary:view")
    @GetMapping()

    public String grass()
    {
        return prefix + "/summary";
    }


    /**
     * 查询退牧还草工程资金汇总报表列表
     */
    @RequiresPermissions("system:summary:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskPublish tTaskPublish)
    {
        startPage();
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);

        List<TTaskPublish> list = tTaskPublishService.selectSummaryList(tTaskPublish);
        List<TTaskPublish> newlist = new ArrayList<>();

        for(int i = 0;i<list.size();i++){
            TTaskPublish newTTaskPublish = list.get(i);
            /** 围栏规模合计 */
            Double jmgm = newTTaskPublish.getJmSize();
            Double xmgm = newTTaskPublish.getXmSize();
            Double hqmgm = newTTaskPublish.getHqlxSize();
            Double smhgm = newTTaskPublish.getSmhzlSize();
            if(jmgm==null){newTTaskPublish.setJmSize(0.0);jmgm=0.0;}
            if(xmgm==null){newTTaskPublish.setXmSize(0.0);xmgm=0.0;}
            if(hqmgm==null){newTTaskPublish.setHqlxSize(0.0);hqmgm=0.0;}
            if(smhgm==null){newTTaskPublish.setSmhzlSize(0.0);smhgm=0.0;}
            Double wlScale = jmgm+xmgm+hqmgm+smhgm;
            newTTaskPublish.setWlScale(wlScale);
            /** 其他规模合计 */
            Double thcgm = newTTaskPublish.getThzyglSize();
            Double rgsgm = newTTaskPublish.getRgscdSize();
            Double hspgm = newTTaskPublish.getHspjSize();
            Double httgm = newTTaskPublish.getHttSize();
            Double dhcgm = newTTaskPublish.getDhcSize();
            Double ykcgm = newTTaskPublish.getYkcyzlSize();
            if(thcgm==null){newTTaskPublish.setThzyglSize(0.0);thcgm=0.0;}
            if(rgsgm==null){newTTaskPublish.setRgscdSize(0.0);rgsgm=0.0;}
            if(hspgm==null){newTTaskPublish.setHspjSize(0.0);hspgm=0.0;}
            if(httgm==null){newTTaskPublish.setHttSize(0.0);httgm=0.0;}
            if(dhcgm==null){newTTaskPublish.setDhcSize(0.0);dhcgm=0.0;}
            if(ykcgm==null){newTTaskPublish.setYkcyzlSize(0.0);ykcgm=0.0;}
            Double totalScale = thcgm+rgsgm+hspgm+httgm+dhcgm+ykcgm+wlScale;
            newTTaskPublish.setTotalScale(totalScale);
            /** 资金合计 */
            Double zyzj = newTTaskPublish.getZyMoney();
            Double dfzj = newTTaskPublish.getDfMoney();
            if(zyzj==null){newTTaskPublish.setZyMoney(0.0);zyzj=0.0;}
            if(dfzj==null){newTTaskPublish.setDfMoney(0.0);dfzj=0.0;}
            Double totalInvestment = zyzj+dfzj;
            newTTaskPublish.setTotalInvestment(totalInvestment);
            newlist.add(newTTaskPublish);
        }
        return getDataTable(newlist);
    }

    /**
     * 导出退牧还草工程资金汇总报表列表
     */
    @RequiresPermissions("system:summary:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskReport tTaskReport)
    {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskReport.getProvince());
        tTaskReport.setProvince(provinceLabel);

        List<TTaskReport> list = tTaskReportService.selectSummaryList(tTaskReport);
        ExcelUtil<TTaskReport> util = new ExcelUtil<TTaskReport>(TTaskReport.class);
        return util.exportExcel(list, "summary");
    }

    @PostMapping("/getOption")
    @ResponseBody
    public List<TTaskReport> getOption(TTaskReport tTaskReport,String province) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(province);
        tTaskReport.setProvince(provinceLabel);

        List<TTaskReport> list = tTaskReportService.selectSummaryList(tTaskReport);
        List<TTaskReport> newlist = new ArrayList<>();

        for(int i = 0;i<list.size();i++) {
            TTaskReport newTTaskReport = list.get(i);
            /** 围栏规模合计 */
            Double jmgm = newTTaskReport.getJmSize();
            Double xmgm = newTTaskReport.getXmSize();
            Double hqmgm = newTTaskReport.getHqlxSize();
            Double smhgm = newTTaskReport.getSmhzlSize();
            if(jmgm==null){jmgm=0.0;}
            if(xmgm==null){xmgm=0.0;}
            if(hqmgm==null){hqmgm=0.0;}
            if(smhgm==null){smhgm=0.0;}
            Double wlScale = jmgm + xmgm + hqmgm + smhgm;
            newTTaskReport.setWlScale(wlScale);
            newlist.add(newTTaskReport);
        }
        return newlist;
    }

    /**
     * 跳转牧还草工程资金汇总报表折线图
     */
    @GetMapping(value = "/Echars")
    public String echarts4(String province, ModelMap mmap){
        mmap.put("province", province);
        return prefix + "/Echars";
    }

}
