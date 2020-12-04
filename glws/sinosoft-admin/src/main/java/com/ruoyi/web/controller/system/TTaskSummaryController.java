package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.Improve;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.mapper.TTaskResolveMapper;
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

import java.text.DecimalFormat;
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
    @Autowired
    private TTaskResolveMapper tTaskResolveMapper;
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
        TTaskResolve tTaskResolve = new TTaskResolve();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("市级")) {
                tTaskResolve.setYear(tTaskPublish.getYear());
                //根据字典键值查询字典标签
                List<TTaskResolve> newlist = gettTaskResolves(tTaskResolve);
                return getDataTable(newlist);
            }
            List<TTaskPublish> newlist = gettTaskPublishes(tTaskPublish);
            return getDataTable(newlist);
        }
        return getDataTable(null);
    }
    private List<TTaskPublish> gettTaskPublishes(TTaskPublish tTaskPublish) {
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("省级")) {
                tTaskPublish.setProvince(deptName);
            }
        }

        List<TTaskPublish> list = tTaskPublishService.selectSummaryList(tTaskPublish);
        List<TTaskPublish> newlist = new ArrayList<>();
        TTaskPublish ttaskPublishnew= new TTaskPublish();
        Double jmgmcount=0.00;
        /** 休牧 */
        Double xmgmcount=0.00;
        /** 划区轮牧 */
        Double hqmgmcount=0.00;
        /** 石漠化治理 */
        Double smhgmcount=0.00;
        /** 围栏任务合计 */
        Double wlScalecount=0.00;
        /** 退化草原改良*/
        Double thcgmcount=0.00;
        /** 人工种草地 */
        Double rgsgmcount=0.00;
        /** 含饲棚圈 */
        Double hspgmcount=0.00;
        /** 黑土滩 */
        Double httgmcount=0.00;
        /** 毒害草 */
        Double dhcgmcount=0.00;
        /** 资金合计(万元) */
        Double totalScalecount=0.00;
        /** 资金合计(万元) */
        Double totalInvestmentcount=0.00;
        /** 中央资金(万元) */
        Double zyzjcount=0.00;
        /** 地方资金(万元) */
        Double dfzjcount=0.00;
        for(int i = 0;i<list.size();i++){
            TTaskPublish newTTaskPublish = list.get(i);
            /** 围栏规模合计 */
            Double jmgm = newTTaskPublish.getJmSize();
            Double xmgm = newTTaskPublish.getXmSize();
            Double hqmgm = newTTaskPublish.getHqlxSize();
            Double smhgm = newTTaskPublish.getSmhzlSize();
            Double wlScale =newTTaskPublish.getWlScale();
            if(jmgm==null){newTTaskPublish.setJmSize(0.0);jmgm=0.0;}
            if(xmgm==null){newTTaskPublish.setXmSize(0.0);xmgm=0.0;}
            if(hqmgm==null){newTTaskPublish.setHqlxSize(0.0);hqmgm=0.0;}
            if(smhgm==null){newTTaskPublish.setSmhzlSize(0.0);smhgm=0.0;}
            if(wlScale==null){newTTaskPublish.setWlScale(0.0);wlScale=0.0;}
//            Double wlScales = jmgm+xmgm+hqmgm+smhgm+wlScale;
            /** 其他规模合计 */
            Double thcgm = newTTaskPublish.getThzyglSize();
            Double rgsgm = newTTaskPublish.getRgscdSize();
            Double hspgm = newTTaskPublish.getHspjSize();
            Double httgm = newTTaskPublish.getHttSize();
            Double dhcgm = newTTaskPublish.getDhcSize();
            if(thcgm==null){newTTaskPublish.setThzyglSize(0.0);thcgm=0.0;}
            if(rgsgm==null){newTTaskPublish.setRgscdSize(0.0);rgsgm=0.0;}
            if(hspgm==null){newTTaskPublish.setHspjSize(0.0);hspgm=0.0;}
            if(httgm==null){newTTaskPublish.setHttSize(0.0);httgm=0.0;}
            if(dhcgm==null){newTTaskPublish.setDhcSize(0.0);dhcgm=0.0;}
//            Double totalScale = thcgm+rgsgm+hspgm+httgm+dhcgm;
//            newTTaskPublish.setTotalScale(Double.parseDouble(df.format(totalScale)));
            /** 资金合计 */
            Double zyzj = newTTaskPublish.getZyMoney();
            Double dfzj = newTTaskPublish.getDfMoney();
            if(zyzj==null){newTTaskPublish.setZyMoney(0.0);zyzj=0.0;}
            if(dfzj==null){newTTaskPublish.setDfMoney(0.0);dfzj=0.0;}
            Double totalInvestment = zyzj+dfzj;
            newTTaskPublish.setTotalInvestment(Double.parseDouble(df.format(totalInvestment)));
            newlist.add(newTTaskPublish);
            /** 合计行数据 */
            jmgmcount=jmgmcount+jmgm;
            xmgmcount=xmgmcount+xmgm;
            hqmgmcount=hqmgmcount+hqmgm;
            smhgmcount=smhgmcount+smhgm;
            wlScalecount=wlScalecount+wlScale;
            thcgmcount=thcgmcount+thcgm;
            rgsgmcount=rgsgmcount+rgsgm;
            hspgmcount=hspgmcount+hspgm;
            httgmcount=httgmcount+httgm;
            dhcgmcount=dhcgmcount+dhcgm;
//            totalScalecount=totalScalecount+totalScale;
            totalInvestmentcount=totalInvestmentcount+totalInvestment;
            zyzjcount=zyzjcount+zyzj;
            dfzjcount=dfzjcount+dfzj;
        }
        /** 添加合计行对象数据*/
        ttaskPublishnew.setYear("合计");
        ttaskPublishnew.setJmSize(Double.parseDouble(df.format(jmgmcount)));
        ttaskPublishnew.setXmSize(Double.parseDouble(df.format(xmgmcount)));
        ttaskPublishnew.setHqlxSize(Double.parseDouble(df.format(hqmgmcount)));
        ttaskPublishnew.setSmhzlSize(Double.parseDouble(df.format(smhgmcount)));
        ttaskPublishnew.setWlScale(Double.parseDouble(df.format(wlScalecount)));
        ttaskPublishnew.setThzyglSize(Double.parseDouble(df.format(thcgmcount)));
        ttaskPublishnew.setRgscdSize(Double.parseDouble(df.format(rgsgmcount)));
        ttaskPublishnew.setHspjSize(Double.parseDouble(df.format(hspgmcount)));
        ttaskPublishnew.setHttSize(Double.parseDouble(df.format(httgmcount)));
        ttaskPublishnew.setDhcSize(Double.parseDouble(df.format(dhcgmcount)));
//        ttaskPublishnew.setTotalScale(Double.parseDouble(df.format(totalScalecount)));
        ttaskPublishnew.setTotalInvestment(Double.parseDouble(df.format(totalInvestmentcount)));
        ttaskPublishnew.setZyMoney(Double.parseDouble(df.format(zyzjcount)));
        ttaskPublishnew.setDfMoney(Double.parseDouble(df.format(dfzjcount)));
        newlist.add(ttaskPublishnew);
        return newlist;
    }

    private List<TTaskResolve> gettTaskResolves(TTaskResolve tTaskResolve) {
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getProvince());
        tTaskResolve.setProvince(provinceLabel);
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("市级")) {
                tTaskResolve.setProvince(deptName);
            }
        }
        List<TTaskResolve> list = tTaskResolveMapper.selectSummaryAreaList(tTaskResolve);
        List<TTaskResolve> newlist = new ArrayList<>();
        TTaskResolve TTaskResolvenew= new TTaskResolve();
        Double jmgmcount=0.00;
        /** 休牧 */
        Double xmgmcount=0.00;
        /** 划区轮牧 */
        Double hqmgmcount=0.00;
        /** 石漠化治理 */
        Double smhgmcount=0.00;
        /** 围栏任务合计 */
        Double wlScalecount=0.00;
        /** 退化草原改良*/
        Double thcgmcount=0.00;
        /** 人工种草地 */
        Double rgsgmcount=0.00;
        /** 含饲棚圈 */
        Double hspgmcount=0.00;
        /** 黑土滩 */
        Double httgmcount=0.00;
        /** 毒害草 */
        Double dhcgmcount=0.00;
        /** 资金合计(万元) */
        Double totalScalecount=0.00;
        /** 资金合计(万元) */
        Double totalInvestmentcount=0.00;
        /** 中央资金(万元) */
        Double zyzjcount=0.00;
        /** 地方资金(万元) */
        Double dfzjcount=0.00;
        for(int i = 0;i<list.size();i++){
            TTaskResolve newTTaskResolve = list.get(i);
            /** 围栏规模合计 */
            Double jmgm = newTTaskResolve.getJmSize();
            Double xmgm = newTTaskResolve.getXmSize();
            Double hqmgm = newTTaskResolve.getHqlxSize();
            Double smhgm = newTTaskResolve.getSmhzlSize();
            Double wlScale =newTTaskResolve.getWlScale();
            if(jmgm==null){newTTaskResolve.setJmSize(0.0);jmgm=0.0;}
            if(xmgm==null){newTTaskResolve.setXmSize(0.0);xmgm=0.0;}
            if(hqmgm==null){newTTaskResolve.setHqlxSize(0.0);hqmgm=0.0;}
            if(smhgm==null){newTTaskResolve.setSmhzlSize(0.0);smhgm=0.0;}
            if(wlScale==null){newTTaskResolve.setWlScale(0.0);wlScale=0.0;}
//            Double wlScales = jmgm+xmgm+hqmgm+smhgm+wlScale;
            /** 其他规模合计 */
            Double thcgm = newTTaskResolve.getThzyglSize();
            Double rgsgm = newTTaskResolve.getRgscdSize();
            Double hspgm = newTTaskResolve.getHspjSize();
            Double httgm = newTTaskResolve.getHttSize();
            Double dhcgm = newTTaskResolve.getDhcSize();
            if(thcgm==null){newTTaskResolve.setThzyglSize(0.0);thcgm=0.0;}
            if(rgsgm==null){newTTaskResolve.setRgscdSize(0.0);rgsgm=0.0;}
            if(hspgm==null){newTTaskResolve.setHspjSize(0.0);hspgm=0.0;}
            if(httgm==null){newTTaskResolve.setHttSize(0.0);httgm=0.0;}
            if(dhcgm==null){newTTaskResolve.setDhcSize(0.0);dhcgm=0.0;}
//            Double totalScale = thcgm+rgsgm+hspgm+httgm+dhcgm;
//            newTTaskPublish.setTotalScale(Double.parseDouble(df.format(totalScale)));
            /** 资金合计 */
            Double zyzj = newTTaskResolve.getZyMoney();
            Double dfzj = newTTaskResolve.getDfMoney();
            if(zyzj==null){newTTaskResolve.setZyMoney(0.0);zyzj=0.0;}
            if(dfzj==null){newTTaskResolve.setDfMoney(0.0);dfzj=0.0;}
            Double totalInvestment = zyzj+dfzj;
            newTTaskResolve.setTotalInvestment(Double.parseDouble(df.format(totalInvestment)));
            newlist.add(newTTaskResolve);
            /** 合计行数据 */
            jmgmcount=jmgmcount+jmgm;
            xmgmcount=xmgmcount+xmgm;
            hqmgmcount=hqmgmcount+hqmgm;
            smhgmcount=smhgmcount+smhgm;
            wlScalecount=wlScalecount+wlScale;
            thcgmcount=thcgmcount+thcgm;
            rgsgmcount=rgsgmcount+rgsgm;
            hspgmcount=hspgmcount+hspgm;
            httgmcount=httgmcount+httgm;
            dhcgmcount=dhcgmcount+dhcgm;
//            totalScalecount=totalScalecount+totalScale;
            totalInvestmentcount=totalInvestmentcount+totalInvestment;
            zyzjcount=zyzjcount+zyzj;
            dfzjcount=dfzjcount+dfzj;
        }
        /** 添加合计行对象数据*/
        TTaskResolvenew.setYear("合计");
        TTaskResolvenew.setJmSize(Double.parseDouble(df.format(jmgmcount)));
        TTaskResolvenew.setXmSize(Double.parseDouble(df.format(xmgmcount)));
        TTaskResolvenew.setHqlxSize(Double.parseDouble(df.format(hqmgmcount)));
        TTaskResolvenew.setSmhzlSize(Double.parseDouble(df.format(smhgmcount)));
        TTaskResolvenew.setWlScale(Double.parseDouble(df.format(wlScalecount)));
        TTaskResolvenew.setThzyglSize(Double.parseDouble(df.format(thcgmcount)));
        TTaskResolvenew.setRgscdSize(Double.parseDouble(df.format(rgsgmcount)));
        TTaskResolvenew.setHspjSize(Double.parseDouble(df.format(hspgmcount)));
        TTaskResolvenew.setHttSize(Double.parseDouble(df.format(httgmcount)));
        TTaskResolvenew.setDhcSize(Double.parseDouble(df.format(dhcgmcount)));
//        ttaskPublishnew.setTotalScale(Double.parseDouble(df.format(totalScalecount)));
        TTaskResolvenew.setTotalInvestment(Double.parseDouble(df.format(totalInvestmentcount)));
        TTaskResolvenew.setZyMoney(Double.parseDouble(df.format(zyzjcount)));
        TTaskResolvenew.setDfMoney(Double.parseDouble(df.format(dfzjcount)));
        newlist.add(TTaskResolvenew);
        return newlist;
    }


    /**
     * 导出退牧还草工程资金汇总报表列表
     */
    @RequiresPermissions("system:summary:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskPublish tTaskPublish)
    {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);

        List<TTaskPublish> list = tTaskPublishService.selectSummaryList(tTaskPublish);
        ExcelUtil<TTaskPublish> util = new ExcelUtil<TTaskPublish>(TTaskPublish.class);
        return util.exportExcel(list, "summary");
    }


    @PostMapping("/getOptionHistogram")
    @ResponseBody
    public List<?> getOption(TTaskPublish tTaskPublish, String province, String year) {
        TTaskResolve tTaskResolve = new TTaskResolve();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        if (StringUtils.isEmpty(province) && StringUtils.isEmpty(year)) {
            tTaskPublish.setProvince(null);
            for (int i = 0; i < roles.size(); i++) {
                SysRole sysRole = roles.get(i);
                String roleName = sysRole.getRoleName();
                if (roleName.equals("市级")) {
                    tTaskResolve.setProvince(tTaskPublish.getProvince());
                    tTaskResolve.setYear(tTaskPublish.getYear());
                    List<TTaskResolve> newlist = gettTaskResolves(tTaskResolve);
                    newlist.remove(newlist.size() - 1);
                    return newlist;
                }
            }
            List<TTaskPublish> newlist = gettTaskPublishes(tTaskPublish);
            return newlist;
        } else if (province != null && province != "" || year != null && year != "") {
            //根据字典键值查询字典标签
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(province);
            tTaskPublish.setProvince(provinceLabel);
            tTaskPublish.setYear(year);
            for (int i = 0; i < roles.size(); i++) {
                SysRole sysRole = roles.get(i);
                String roleName = sysRole.getRoleName();
                if (roleName.equals("市级")) {
                    tTaskResolve.setProvince(tTaskPublish.getProvince());
                    tTaskResolve.setYear(tTaskPublish.getYear());
                    List<TTaskResolve> newlist = gettTaskResolves(tTaskResolve);
                    newlist.remove(newlist.size() - 1);
                    return newlist;
                }
            }
            List<TTaskPublish> newlist = gettTaskPublishes(tTaskPublish);
            return newlist;
        }
        return null;

    }
    @GetMapping(value = "/EcharsLineChart")
    public String EcharsLineChart(String province, ModelMap mmap){
        mmap.put("province", province);
        return prefix + "/EcharsLineChart";
    }

    @GetMapping(value = "/EcharsHistogram")
    public String EcharsHistogram(String year, String province, ModelMap mmap) {
        mmap.put("province", province);
        mmap.put("year", year);
        return prefix + "/EcharsHistogram";
    }

}
