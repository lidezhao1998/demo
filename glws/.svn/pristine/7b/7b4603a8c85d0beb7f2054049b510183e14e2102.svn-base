package com.ruoyi.web.controller.system;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskResolveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 退牧还草工程进度上报Controller
 *
 * @author LiuHongfei
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/system/taskList")
public class TTaskListController extends BaseController {
    private String prefix = "system/taskList";

    @Autowired
    private ITTaskResolveService tTaskResolveService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @RequiresPermissions("system:report:view")
    @GetMapping()
    public String report() {
        return prefix + "/taskList";
    }

    /**
     * 查询退牧还草工程任务列表
     */
    @RequiresPermissions("system:report:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskResolve tTaskResolve) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getProvince());
        tTaskResolve.setProvince(provinceLabel);
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        startPage();
        tTaskResolve.setAddress(deptName);
        List<TTaskResolve> list = tTaskResolveService.selectTTaskResolveList(tTaskResolve);
        List<TTaskResolve> newlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TTaskResolve newTTaskReport = list.get(i);
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
            Double thcgm = newTTaskReport.getThzyglSize();
            Double rgsgm = newTTaskReport.getRgscdSize();
            Double hspgm = newTTaskReport.getHspjSize();
            Double httgm = newTTaskReport.getHttSize();
            Double dhcgm = newTTaskReport.getDhcSize();
            Double ykcgm = newTTaskReport.getYkcyzlSize();
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
            if (ykcgm == null) {
                ykcgm = 0.0;
            }
            Double totalScale = thcgm + rgsgm + hspgm + httgm + dhcgm + ykcgm;
            newTTaskReport.setQtmjCount(Double.parseDouble(df.format(totalScale)));
            /** 其他投资合计 */
            Double thctz = newTTaskReport.getThzyglMoney();
            Double rgstz = newTTaskReport.getRgscdMoney();
            Double hsptz = newTTaskReport.getHspjMoney();
            Double htttz = newTTaskReport.getHttMoney();
            Double dhctz = newTTaskReport.getDhcMoney();
            Double ykctz = newTTaskReport.getYkcazlMoney();
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
            if (ykctz == null) {
                ykctz = 0.0;
            }
            Double totalInvestment = thctz + rgstz + hsptz + htttz + dhctz + ykctz;
            newTTaskReport.setQtjeCount(Double.parseDouble(df.format(totalInvestment)));
            /** 总合计 */
            newTTaskReport.setZgmCount(Double.parseDouble(df.format(wlmj+totalScale)));
            newTTaskReport.setZjeCount(Double.parseDouble(df.format(wlje+totalInvestment)));
            /** 拼接任务地区 */
            String province = newTTaskReport.getAddress();
            String year = newTTaskReport.getYear();
            String dz = year + "_" + province;
            newTTaskReport.setDz(dz);

            newlist.add(newTTaskReport);
        }
        return getDataTable(newlist);
    }

    /**
     * 查看退牧还草工程任务领取分解
     */
    @GetMapping("/detail/{resolveId}")
    public String edit(@PathVariable("resolveId") Long resolveId, ModelMap mmap) {
        TTaskResolve tTaskResolve = tTaskResolveService.selectTTaskResolveById(resolveId);
        mmap.put("tTaskResolve", tTaskResolve);
        return prefix + "/detail";
    }
}
