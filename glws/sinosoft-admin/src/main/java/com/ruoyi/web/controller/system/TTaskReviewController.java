package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskReportService;
import com.ruoyi.system.service.ITTaskResolveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 退牧还草工程任务审核Controller
 *
 * @author liuhongfei
 * @date 2019-3-24
 */
@Controller
@RequestMapping("/system/review")
public class TTaskReviewController extends BaseController {
    private String prefix = "system/review";

    @Autowired
    private ITTaskResolveService tTaskResolveService;

    @Autowired
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private ITTaskReportService tTaskReportService;

    /**
     * 进度审核页面
     */
    @RequiresPermissions("system:review:view")
    @GetMapping()
    public String reviewList(ModelMap mmap) {
        mmap.put("tTaskResolve", new TTaskPublish());
        return prefix + "/review";
    }

    /**
     * 提取方法计算列表展示合计
     */
    private TTaskResolve getSumResolve(TTaskResolve total) {
        DecimalFormat df = new DecimalFormat("#.00");
        /** 围栏规模合计 */
        Double jmgm = total.getJmSize();
        Double xmgm = total.getXmSize();
        Double hqmgm = total.getHqlxSize();
        Double smhgm = total.getSmhzlSize();
        Double wlScale = total.getWlScale();
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
        if (wlScale == null) {
            wlScale = 0.0;
        }
        Double wlgm = jmgm + xmgm + hqmgm + smhgm +wlScale;
        total.setWlmjCount(Double.parseDouble(df.format(wlgm)));
        /** 围栏投资合计 */
        Double jmtz = total.getJmMoney();
        Double xmtz = total.getXmMoney();
        Double hqmtz = total.getHqlxMoney();
        Double smhtz = total.getSmhzlMoney();
        Double wlInvestment = total.getWlInvestment();
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
        if (wlInvestment == null) {
            wlInvestment = 0.0;
        }
        Double wlje = jmtz + xmtz + hqmtz + smhtz +wlInvestment;
        total.setWljeCount(Double.parseDouble(df.format(wlInvestment)));
        /** 总面积合计 */
        Double thcmj = total.getThzyglSize();
        Double rgsmj = total.getRgscdSize();
        Double hspmj = total.getHspjSize();
        Double httmj = total.getHttSize();
        Double dhcmj = total.getDhcSize();
        if (thcmj == null) {
            thcmj = 0.0;
        }
        if (rgsmj == null) {
            rgsmj = 0.0;
        }
        if (hspmj == null) {
            hspmj = 0.0;
        }
        if (httmj == null) {
            httmj = 0.0;
        }
        if (dhcmj == null) {
            dhcmj = 0.0;
        }

        Double zgmCount = thcmj + rgsmj + hspmj + httmj + dhcmj + wlgm;
        total.setZgmCount(Double.parseDouble(df.format(zgmCount)));
        /** 总投资合计 */
        Double thctz = total.getThzyglMoney();
        Double rgstz = total.getRgscdMoney();
        Double hsptz = total.getHspjMoney();
        Double htttz = total.getHttMoney();
        Double dhctz = total.getDhcMoney();
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

        Double zjeCount = thctz + rgstz + hsptz + htttz + dhctz + wlje;
        total.setZjeCount(Double.parseDouble(df.format(zjeCount)));
        return total;
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
     * 查询退牧还草工程任务列表
     */
//    @RequiresPermissions("system:review:listReview")
    @PostMapping("/listReview")
    @ResponseBody
    public TableDataInfo listReview(TTaskReport tTaskReport) {
        startPage();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            List<TTaskReport> tTaskReportList = new ArrayList<>();
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("市级")) {
                tTaskReport.setCity(deptName);
                List<TTaskReport> ReportList = tTaskReportService.selectReportAreaList(tTaskReport);
                for (int j = 0; j < ReportList.size(); j++) {
                    TTaskReport newTTaskReport = ReportList.get(j);
                    if (newTTaskReport != null) {
                        //计算规模合计、投资合计、其他规模合计、其他投资合计、总规模(万亩)、总投资(万元)
                        newTTaskReport = getSumReport(newTTaskReport);
                        newTTaskReport.setAuditName(roleName);
                        tTaskReportList.add(newTTaskReport);
                    }
                }
                return getDataTable(tTaskReportList);
            } else {
                tTaskReport.setProvince(deptName);
                List<TTaskReport> ReportList = tTaskReportService.selectReportCityList(tTaskReport);
                for (int j = 0; j < ReportList.size(); j++) {
                    TTaskReport newTTaskReport = ReportList.get(j);
                    if (newTTaskReport != null) {
                        //计算规模合计、投资合计、其他规模合计、其他投资合计、总规模(万亩)、总投资(万元)
                        newTTaskReport = getSumReport(newTTaskReport);
                        newTTaskReport.setAuditName(roleName);
                        String address = newTTaskReport.getCity();
                        newTTaskReport.setAddress(address);

                        tTaskReportList.add(newTTaskReport);
                    }
                }
                return getDataTable(tTaskReportList);
            }

        }
        return getDataTable(new ArrayList<>());
    }




    /**
     * 修改审核状态
     */

    @PostMapping("/change")
    @ResponseBody
    public String change(@RequestParam Map map) {
        Long reportId = Long.parseLong((String) map.get("reportId"));
        String status = (String) map.get("status");
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        tTaskReport.setStatus(status);
        tTaskReportService.updateTTaskReport(tTaskReport);
        return prefix + "/change";
    }


    /**
     * 查看退牧还草工程进度
     */
    @GetMapping("/detail/{reportId}")
    public String add(@PathVariable("reportId") Long reportId, ModelMap mmap) {
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        mmap.put("tTaskReport", tTaskReport);
        mmap.put("sonList", getSonListTableDataInfo(reportId).getRows().get(0));
        mmap.put("taskList", getTableDataInfo(reportId).getRows().get(0));
        return prefix + "/detail";
    }

    /**
     * 查询退牧还草工程任务上报列表
     */
    @RequiresPermissions("system:review:sonList")
    @PostMapping("/sonList")
    @ResponseBody
    public TableDataInfo sonList(Long reportId) {
        startPage();
        return getSonListTableDataInfo(reportId);
    }

    private TableDataInfo getSonListTableDataInfo(Long reportId) {
        List<TTaskReport> list = new ArrayList();
        DecimalFormat df = new DecimalFormat("#.00");
        //查询上报信息
        TTaskReport newTTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        SysRole sysRole = roles.get(0);
        if (sysRole.getRoleName().equals("省级")) {
            newTTaskReport.setAuditName("省级");
            tTaskReportService.updateTTaskReport(newTTaskReport);
        } else {
            newTTaskReport.setAuditName("市级");
            tTaskReportService.updateTTaskReport(newTTaskReport);
        }
        //查询总规模
        TTaskResolve tTaskResolveTotal = tTaskResolveService.selectTTaskResolveById(newTTaskReport.getResolveId());
        TTaskResolve total = tTaskResolveService.selectResolveTotalScale(tTaskResolveTotal);
        //查询上报审核通过的总规模
        Double reportTotal = total.getBpSize();
        newTTaskReport = getSumReport(newTTaskReport);
        //已完成任务量
        newTTaskReport.setJindu(df.format((newTTaskReport.getZgmCount() / reportTotal) * 100));
        list.add(newTTaskReport);
        return getDataTable(list);
    }

    /**
     * 查询退牧还草工程任务列表
     */
    @RequiresPermissions("system:review:taskList")
    @PostMapping("/taskList")
    @ResponseBody
    public TableDataInfo taskList(Long reportId) {
        startPage();
        return getTableDataInfo(reportId);

    }

    private TableDataInfo getTableDataInfo(Long reportId) {
        List<TTaskResolve> list = new ArrayList();
        List<TTaskPublish> publishList = new ArrayList();
        DecimalFormat df = new DecimalFormat("#.00");
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        //获取登录角色信息
        SysRole sysRole = roles.get(0);
        //查询上报信息
        TTaskReport tTaskReport = tTaskReportService.selectTTaskReportById(reportId);
        //查询总规模
        TTaskResolve tTaskResolveTotal = new TTaskResolve();
        if (sysRole.getRoleName().equals("市级")) {
            tTaskResolveTotal.setResolveId(tTaskReport.getResolveId());
            TTaskResolve total = tTaskResolveService.selectResolveTotalScale(tTaskResolveTotal);
            total = getSumResolve(total);
            list.add(total);
            return getDataTable(list);
        } else {
            TTaskPublish tTaskPublish = new TTaskPublish();
            tTaskPublish.setYear(tTaskReport.getYear());
            tTaskPublish.setProvince(tTaskReport.getProvince());
            TTaskPublish total = tTaskPublishService.selectPublishTotalScale(tTaskReport.getResolveId());
            Double totalScale = 0.0;
            /** 围栏规模合计 */
            Double jmgm = total.getJmSize();
            Double xmgm = total.getXmSize();
            Double hqmgm = total.getHqlxSize();
            Double smhgm = total.getSmhzlSize();
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
            Double wlScale = jmgm + xmgm + hqmgm + smhgm;
            total.setWlScale(Double.parseDouble(df.format(wlScale)));
            /** 围栏投资合计 */
            Double jmtz = total.getJmMoney();
            Double xmtz = total.getXmMoney();
            Double hqmtz = total.getHqlxMoney();
            Double smhtz = total.getSmhzlMoney();
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
            Double wlInvestment = jmtz + xmtz + hqmtz + smhtz;
            total.setWlInvestment(Double.parseDouble(df.format(wlInvestment)));
            /** 总面积合计 */
            Double thcmj = total.getThzyglSize();
            Double rgsmj = total.getRgscdSize();
            Double hspmj = total.getHspjSize();
            Double httmj = total.getHttSize();
            Double dhcmj = total.getDhcSize();
            if (thcmj == null) {
                thcmj = 0.0;
            }
            if (rgsmj == null) {
                rgsmj = 0.0;
            }
            if (hspmj == null) {
                hspmj = 0.0;
            }
            if (httmj == null) {
                httmj = 0.0;
            }
            if (dhcmj == null) {
                dhcmj = 0.0;
            }
            Double zgmCount = thcmj + rgsmj + hspmj + httmj + dhcmj + wlScale;
            total.setTotalScale(Double.parseDouble(df.format(zgmCount)));
            /** 总投资合计 */
            Double thctz = total.getThzyglMoney();
            Double rgstz = total.getRgscdMoney();
            Double hsptz = total.getHspjMoney();
            Double htttz = total.getHttMoney();
            Double dhctz = total.getDhcMoney();
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
            Double zjeCount = thctz + rgstz + hsptz + htttz + dhctz + wlInvestment;
            total.setTotalInvestment(Double.parseDouble(df.format(zjeCount)));

            publishList.add(total);
            return getDataTable(publishList);
        }
    }


    /**
     * 三级联动市级
     */

    @GetMapping("/getCities")
    @ResponseBody
    public List<SysDictData> getCities(ModelMap mmap, String provinceCode, String addressCode) {
        //判断分解任务为市级
        if (addressCode != "" && addressCode != provinceCode) {
            String address = iSysDictDataService.selectDictValueCode(addressCode);
            List<SysDictData> list = iSysDictDataService.getCities(address);
            getAreas(mmap, address);
            return list;
            //判断分解任务为省级查询
        } else {
            String province = iSysDictDataService.selectDictValueCode(provinceCode);
            List<SysDictData> list = iSysDictDataService.getCities(province);
            return list;

        }
        //根据字典键值查询字典标签

    }

    /**
     * 三级联动曲县级
     */
    @GetMapping("/getAreas")
    @ResponseBody
    public List<SysDictData> getAreas(ModelMap mmap, String cityCode) {

        List<SysDictData> list = iSysDictDataService.getAreas(cityCode);

        return list;
    }

    /**
     * 新增意见
     */
    @GetMapping("/add/{id}/{status}")
    public String add(@PathVariable("id") Integer id,@PathVariable("status") Integer status,ModelMap mmap)
    {
        mmap.addAttribute("taskId",id);
        mmap.addAttribute("status",status);
        mmap.addAttribute("ideaCreateUser",ShiroUtils.getLoginName());
        return prefix + "/add";
    }

    /**
     * 审核时获取当前用户角色下所属任务地区
     */
    @GetMapping("/getAddress")
    @ResponseBody
    public List<SysDictData> getAddress() {
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        List<SysDictData> list = iSysDictDataService.selectBelongAddressByDeptName(deptName);
        return list;
    }


    /**
     * 首页查询待审核列表
     * */
    @PostMapping("/UnrevieweRepostList")
    @ResponseBody
    public TableDataInfo UnrevieweRepostList() {
        TTaskReport tTaskReport = new TTaskReport();
        startPage();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            List<TTaskReport> tTaskReportList = new ArrayList<>();
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("市级")) {
                tTaskReport.setCity(deptName);
                tTaskReport.setStatus("0");
                //查询当前用户未审核上报信息
                List<TTaskReport> ReportList = tTaskReportService.selectUnreviewedReportAreaList(tTaskReport);
                for (int j = 0; j < ReportList.size(); j++) {
                    TTaskReport newTTaskReport = ReportList.get(j);
                    if (newTTaskReport != null) {
                        //计算规模合计、投资合计、其他规模合计、其他投资合计、总规模(万亩)、总投资(万元)
                        newTTaskReport = getSumReport(newTTaskReport);
                        newTTaskReport.setAuditName(roleName);
                        tTaskReportList.add(newTTaskReport);
                    }
                }
                return getDataTable(tTaskReportList);
            } else {
                tTaskReport.setProvince(deptName);
                tTaskReport.setStatus("0");
                //查询当前用户未审核上报信息
                List<TTaskReport> ReportList = tTaskReportService.selectUnreviewedReportCityList(tTaskReport);
                for (int j = 0; j < ReportList.size(); j++) {
                    TTaskReport newTTaskReport = ReportList.get(j);
                    if (newTTaskReport != null) {
                        //计算规模合计、投资合计、其他规模合计、其他投资合计、总规模(万亩)、总投资(万元)
                        newTTaskReport = getSumReport(newTTaskReport);
                        newTTaskReport.setAuditName(roleName);
                        String address = newTTaskReport.getCity();
                        newTTaskReport.setAddress(address);
                        tTaskReportList.add(newTTaskReport);
                    }
                }
                return getDataTable(tTaskReportList);
            }
        }
        return getDataTable(new ArrayList<>());
    }
}
