package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskReportService;
import com.ruoyi.system.service.ITTaskResolveService;
import com.ruoyi.system.service.gis.IGisMapService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 退牧还草工程进度上报Controller
 *
 * @author LiuHongfei
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/system/report")
public class TTaskReportController extends BaseController {
    private String prefix = "system/report";

    @Autowired
    private ITTaskReportService tTaskReportService;

    @Autowired
    private ITTaskResolveService tTaskResolveService;

    @Autowired
    private ITTaskPublishService tTaskPublishService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private IGisMapService iGisMapService;


    @RequiresPermissions("system:report:view")
    @GetMapping()
    public String report() {
        return prefix + "/report";
    }

    /**
     * 提取方法计算列表展示合计
     */
    private TTaskReport getSumReport(TTaskReport newTTaskReport) {
        DecimalFormat df = new DecimalFormat("#.00");
        /** 围栏规模合计 */
        Double jmgm = newTTaskReport.getJmSize();
        Double xmgm = newTTaskReport.getXmSize();
        Double hqmgm = newTTaskReport.getHqlxSize();
        Double smhgm = newTTaskReport.getSmhzlSize();
        Double wlscale = newTTaskReport.getWlScale();
        if (jmgm == null) {
            jmgm = 0.0;
        }
        if (xmgm == null) {
            xmgm = 0.0;
        }
        if (hqmgm == null) {
            hqmgm = 0.0;
        }
        if (smhgm == null) {
            smhgm = 0.0;
        }
        if (wlscale == null) {
            wlscale = 0.0;
        }
        Double wlmj = jmgm + xmgm + hqmgm + smhgm + wlscale;
        newTTaskReport.setWlmjCount(Double.parseDouble(df.format(wlmj)));
        /** 围栏投资合计 */
        Double jmtz = newTTaskReport.getJmMoney();
        Double xmtz = newTTaskReport.getXmMoney();
        Double hqmtz = newTTaskReport.getHqlxMoney();
        Double smhtz = newTTaskReport.getSmhzlMoney();
        Double wlinvestment = newTTaskReport.getWlInvestment();
        if (jmtz == null) {
            jmtz = 0.0;
        }
        if (xmtz == null) {
            xmtz = 0.0;
        }
        if (hqmtz == null) {
            hqmtz = 0.0;
        }
        if (smhtz == null) {
            smhtz = 0.0;
        }
        if (wlinvestment == null) {
            wlinvestment = 0.0;
        }
        Double wlje = jmtz + xmtz + hqmtz + smhtz + wlinvestment;
        newTTaskReport.setWljeCount(Double.parseDouble(df.format(wlje)));
        /** 其他规模合计 */
        Double thcgm = newTTaskReport.getThcyglSize();
        Double rgsgm = newTTaskReport.getRgscdSize();
        Double hspgm = newTTaskReport.getHspjSize();
        Double httgm = newTTaskReport.getHttSize();
        Double dhcgm = newTTaskReport.getDhcSize();
        if (thcgm == null) {
            thcgm = 0.0;
        }
        if (rgsgm == null) {
            rgsgm = 0.0;
        }
        if (hspgm == null) {
            hspgm = 0.0;
        }
        if (httgm == null) {
            httgm = 0.0;
        }
        if (dhcgm == null) {
            dhcgm = 0.0;
        }
        Double totalScale = thcgm + rgsgm + hspgm + httgm + dhcgm;
        newTTaskReport.setQtmjCount(Double.parseDouble(df.format(totalScale)));
        /** 其他投资合计 */
        Double thctz = newTTaskReport.getThcyglMoney();
        Double rgstz = newTTaskReport.getRgscdMoney();
        Double hsptz = newTTaskReport.getHspjMoney();
        Double htttz = newTTaskReport.getHttMoney();
        Double dhctz = newTTaskReport.getDhcMoney();
        if (thctz == null) {
            thctz = 0.0;
        }
        if (rgstz == null) {
            rgstz = 0.0;
        }
        if (hsptz == null) {
            hsptz = 0.0;
        }
        if (htttz == null) {
            htttz = 0.0;
        }
        if (dhctz == null) {
            dhctz = 0.0;
        }

        Double totalInvestment = thctz + rgstz + hsptz + htttz + dhctz;
        newTTaskReport.setQtjeCount(Double.parseDouble(df.format(totalInvestment)));
        /** 总合计 */
        newTTaskReport.setZgmCount(Double.parseDouble(df.format(wlmj + totalScale)));
        newTTaskReport.setZjeCount(Double.parseDouble(df.format(wlje + totalInvestment)));
        return newTTaskReport;
    }

    /**
     * 查询退牧还草工程任务上报列表
     */
    @RequiresPermissions("system:report:view")
    @PostMapping("/listReport")
    @ResponseBody
    public TableDataInfo listReport(TTaskReport tTaskReport) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskReport.getProvince());
        tTaskReport.setProvince(provinceLabel);
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        tTaskReport.setAddress(deptName);
        //获取角色
        String roleName = "";
        if(ShiroUtils.getSysUser().getRoles().size() != 0){
            roleName = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        }
        startPage();
        List<TTaskReport> newlist = new ArrayList<>();
        if (roleName.equals("区级")) {
            List<TTaskReport> list = tTaskReportService.selectTTaskReportList(tTaskReport);
            for (int i = 0; i < list.size(); i++) {
                TTaskReport newTTaskReport = list.get(i);
                newTTaskReport = getSumReport(newTTaskReport);
                newlist.add(newTTaskReport);
            }
            return getDataTable(newlist);
        }
        if (roleName.equals("市级")) {
            tTaskReport.setCity(deptName);
            List<TTaskReport> list = tTaskReportService.selectCityList(tTaskReport);
            for (int i = 0; i < list.size(); i++) {
                TTaskReport newTTaskReport = list.get(i);
                newTTaskReport = getSumReport(newTTaskReport);
                newTTaskReport.setAddress(newTTaskReport.getCity());
                newlist.add(newTTaskReport);
            }
            return getDataTable(newlist);
        }
        if (roleName.equals("省级")) {
            tTaskReport.setProvince(deptName);
            List<TTaskReport> list = tTaskReportService.selectProvinceList(tTaskReport);
            for (int i = 0; i < list.size(); i++) {
                TTaskReport newTTaskReport = list.get(i);
                newTTaskReport = getSumReport(newTTaskReport);
                newTTaskReport.setAddress(newTTaskReport.getProvince());
                newlist.add(newTTaskReport);
            }
            return getDataTable(newlist);
        }
        return getDataTable(new ArrayList<>());
    }

    /**
     * 查看新增退牧还草工程进度
     */
    @RequestMapping("/add")
    public String add(ModelMap mmap) {
        //获取角色
        String roleName = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        if (roleName.equals("区级")) {
            return prefix + "/areaAdd";
        } else {
            return prefix + "/cityAdd";
        }
    }

    /**
     * 查询退牧还草工程任务上报列表
     */
    @RequiresPermissions("system:report:view")
    @PostMapping("/managerListReport")
    @ResponseBody
    public TableDataInfo managerListReport(TTaskReport tTaskReport) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskReport.getProvince());
        tTaskReport.setProvince(provinceLabel);
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        String roleName = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        startPage();
        List<TTaskReport> newlist = new ArrayList<>();
        TTaskResolve tTaskResolve = new TTaskResolve();
        TTaskPublish tTaskPublish = new TTaskPublish();
        if (roleName.equals("市级")) {
            tTaskReport.setCity(deptName);
            List<TTaskReport> list = tTaskReportService.selectAreaPassList(tTaskReport);
            for (int i = 0; i < list.size(); i++) {
                TTaskReport newTTaskReport = list.get(i);
                newTTaskReport = getSumReport(newTTaskReport);
                tTaskPublish.setYear(newTTaskReport.getYear());
                tTaskPublish.setAddress(deptName);
                TTaskPublish tTaskPublisha = tTaskPublishService.selectCityPublish(tTaskPublish);
                newTTaskReport.setTotalScale(tTaskPublisha.getTotalScale());
                newlist.add(newTTaskReport);
            }
            return getDataTable(newlist);
        }
        if (roleName.equals("省级")) {
            tTaskReport.setProvince(deptName);
            List<TTaskReport> list = tTaskReportService.selectCityPassList(tTaskReport);
            for (int i = 0; i < list.size(); i++) {
                TTaskReport newTTaskReport = list.get(i);
                newTTaskReport = getSumReport(newTTaskReport);
                tTaskPublish.setYear(newTTaskReport.getYear());
                tTaskPublish.setAddress(deptName);
                List<TTaskPublish> tTaskPublisha = tTaskPublishService.selectProvince(tTaskPublish);
                newTTaskReport.setTotalScale(tTaskPublisha.get(0).getTotalScale());
                newTTaskReport.setAddress(newTTaskReport.getCity());
                newlist.add(newTTaskReport);
            }
            return getDataTable(newlist);
        }
        return getDataTable(new ArrayList<>());
    }


    /**
     * 新增退牧还草工程进度
     */
    @RequiresPermissions("system:report:view")
    @Log(title = "退牧还草工程进度上报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TTaskReport tTaskReport) {
        //获取登录角色地区名
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        tTaskReport.setAddress(deptName);
        //根据区查询对应市
        SysDictData sysDataCity = iSysDictDataService.selectProvinceByCity(deptName);
        //将查询到的城市赋值给城市字段
        if (sysDataCity != null) {
            tTaskReport.setCity(sysDataCity.getDictLabel());
        }
        //根据市查询对应省份
        SysDictData sysDataProvince = iSysDictDataService.selectProvinceByCity(tTaskReport.getCity());
        //变更状态为0，待审核
        tTaskReport.setStatus("0");
        //根据上报信息查询任务id
        TTaskResolve tTaskResolve = tTaskResolveService.selectResolveId(tTaskReport);
        if(tTaskResolve==null){
            return AjaxResult.error("当前选择年份无任务!");
        }
        tTaskReport.setResolveId(tTaskResolve.getResolveId());
        if (sysDataProvince != null) {
            //将查询到的省份赋值给省份字段
            tTaskReport.setProvince(sysDataProvince.getDictLabel());
        }
        //获取当前月份
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        tTaskReport.setConcreteTime(month);

        tTaskReportService.insertTTaskReport(tTaskReport);
        return toAjax(1);
    }

    /**
     * 新增退牧还草工程进度
     */
    @RequiresPermissions("system:report:view")
    @Log(title = "退牧还草工程进度上报", businessType = BusinessType.INSERT)
    @PostMapping("/cityAdd")
    @ResponseBody
    public AjaxResult cityAddSave(TTaskReport tTaskReport) {
        //获取角色
        String roleName = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        if (roleName.equals("市级")) {
            //变更状态为0，待审核
            tTaskReport.setStatus("0");
            //市级编码1
            tTaskReport.setReportLevel(Long.parseLong("1"));
            //获取当前月份
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            tTaskReport.setConcreteTime(month);
            //新增市级记录
            tTaskReportService.insertTTaskReport(tTaskReport);
            return toAjax(1);
        } else {
            //省级上报即通过
            tTaskReport.setStatus("1");
            //省级编码1
            tTaskReport.setReportLevel(Long.parseLong("2"));
            //去除市字段
            tTaskReport.setCity(null);
            //获取当前月份
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            tTaskReport.setConcreteTime(month);
            //新增省级记录
            tTaskReportService.insertTTaskReport(tTaskReport);
            return toAjax(1);
        }

    }

    /**
     * 查看退牧还草工程进度
     */
    @GetMapping("/edit/{resolveId}")
    public String edit(@PathVariable("resolveId") Long resolveId, ModelMap mmap) {
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
    public AjaxResult editSave(TTaskReport tTaskReport) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        tTaskReport.setYear(year + "");
        return toAjax(1);
    }

    /**
     * 修改审核状态
     */

    @PostMapping("/change")
    @ResponseBody
    public String change(@RequestParam Map map) {
        Long reportId = Long.parseLong((String) map.get("id"));
        String change = (String) map.get("change");

        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        tTaskReport.setStatus(change);
        tTaskReportService.updateTTaskReport(tTaskReport);
        return prefix + "/change";
    }

    /**
     * 查看退牧还草工程进度
     */
    @GetMapping("/detail/{reportId}")
    public String detail(@PathVariable("reportId") Long reportId, ModelMap mmap) {
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        mmap.put("tTaskReport", tTaskReport);
        return prefix + "/detail";
    }

    /**
     * 修改退牧还草工程进度
     */
    @GetMapping("/again/{reportId}")
    public String again(@PathVariable("reportId") Long reportId, ModelMap mmap) {
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        mmap.put("tTaskReport", tTaskReport);
        return prefix + "/again";
    }

    /**
     *
     */
    @RequiresPermissions("system:report:detail")
    @Log(title = "退牧还草工程任务修改", businessType = BusinessType.UPDATE)
    @PostMapping("/again")
    @ResponseBody
    public AjaxResult againSave(TTaskReport tTaskReport) {
        return toAjax(tTaskReportService.updateTTaskReport(tTaskReport));
    }

    /**
     * 跳转绘图
     */

    @GetMapping("/ht/{publishId}")
    public String ht(@PathVariable("publishId") Long publishId, ModelMap mmap) {
        return "/system/gis/gis1";
    }

    /**
     * 删除退牧还草工程进度上报
     */
    @RequiresPermissions("system:report:remove")
    @Log(title = "退牧还草工程进度上报", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String reportId) {
        return toAjax(tTaskReportService.deleteTTaskReportByIds(reportId));
    }

    /**
     * 空间分析展示-条件查询-专题查询
     */
    @PostMapping("/selectGisListReport")
    @ResponseBody
    public TableDataInfo gislistReport(TTaskReport tTaskReport) {
        startPage();
        return getDataTable(tTaskReportService.selectGisListReport(tTaskReport));
    }
}
