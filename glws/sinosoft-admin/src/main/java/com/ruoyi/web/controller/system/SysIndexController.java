package com.ruoyi.web.controller.system;

import java.text.DecimalFormat;
import java.util.*;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController {
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private ITTaskResolveService tTaskResolveService;
    @Autowired
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private ITTaskReportService tTaskReportService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        if (!user.getRemark().equals("管理员")) {
            //获取角色
            List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
            SysRole sysRole = roles.get(0);
            String roleName = sysRole.getRoleName();
            mmap.put("roleName", roleName);
        }
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap) {
        return "skin";
    }


    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        DecimalFormat df = new DecimalFormat("#.00");
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        SysRole sysRole = new SysRole();
        if (roles.size() != 0) {
            sysRole = roles.get(0);
        } else {
            sysRole.setRoleName("国家级");
        }
        String roleName = sysRole.getRoleName();
        TTaskPublish tTaskPublish = new TTaskPublish();
        mmap.put("countryFlag", "false");
        if (roleName.equals("国家级")) {
            mmap.put("countryFlag", "true");
            tTaskPublish.setYear(DateUtils.getDate().substring(0, 4));
            tTaskPublish.setProvince(null);
            tTaskPublish = tTaskPublishService.selectCountry(tTaskPublish);
            tTaskPublish.setProvince("全国");
        }
        if (roleName.equals("省级")) {
            mmap.put("countryFlag", "false");
            tTaskPublish.setYear(DateUtils.getDate().substring(0, 4));
            tTaskPublish.setProvince(deptName);
            tTaskPublish = tTaskPublishService.selectCountry(tTaskPublish);
        }
        if (roleName.equals("市级")) {
            mmap.put("countryFlag", "false");
            tTaskPublish.setYear(DateUtils.getDate().substring(0, 4));
            tTaskPublish.setAddress(deptName);
            tTaskPublish = tTaskPublishService.selectCountry(tTaskPublish);
            tTaskPublish.setProvince(deptName);
        }
        if (tTaskPublish == null) {
            tTaskPublish = new TTaskPublish();
            tTaskPublish.setJmSize(0.0);
            tTaskPublish.setXmSize(0.0);
            tTaskPublish.setHqlxSize(0.0);
            tTaskPublish.setSmhzlSize(0.0);
            tTaskPublish.setJmMoney(0.0);
            tTaskPublish.setXmMoney(0.0);
            tTaskPublish.setHqlxMoney(0.0);
            tTaskPublish.setSmhzlMoney(0.0);
            tTaskPublish.setThzyglSize(0.0);
            tTaskPublish.setRgscdSize(0.0);
            tTaskPublish.setHspjSize(0.0);
            tTaskPublish.setHttSize(0.0);
            tTaskPublish.setDhcSize(0.0);
            tTaskPublish.setYkcyzlSize(0.0);
            tTaskPublish.setThzyglMoney(0.0);
            tTaskPublish.setRgscdMoney(0.0);
            tTaskPublish.setHspjMoney(0.0);
            tTaskPublish.setHttMoney(0.0);
            tTaskPublish.setDhcMoney(0.0);
            tTaskPublish.setYkcazlMoney(0.0);
            tTaskPublish.setZyMoney(0.0);
            tTaskPublish.setDfMoney(0.0);
            tTaskPublish.setYear(DateUtils.getDate().substring(0, 4));
            tTaskPublish.setProvince(deptName);
        }

        /** 围栏规模合计 */
        Double jmgm = tTaskPublish.getJmSize();
        Double xmgm = tTaskPublish.getXmSize();
        Double hqmgm = tTaskPublish.getHqlxSize();
        Double smhgm = tTaskPublish.getSmhzlSize();
        Double wlScale = tTaskPublish.getWlScale();
        if (jmgm == null) {
            jmgm = 0.0;
            tTaskPublish.setJmSize(0.0);
        }
        if (xmgm == null) {
            xmgm = 0.0;
            tTaskPublish.setXmSize(0.0);
        }
        if (hqmgm == null) {
            hqmgm = 0.0;
            tTaskPublish.setHqlxSize(0.0);
        }
        if (smhgm == null) {
            smhgm = 0.0;
            tTaskPublish.setSmhzlSize(0.0);
        }
        if (wlScale == null) {
            wlScale = 0.0;
            tTaskPublish.setWlScale(0.0);
        }
        Double wlScales = jmgm + xmgm + hqmgm + smhgm + wlScale;

        tTaskPublish.setWlmjCount(Double.parseDouble(df.format(wlScales)));

        /** 围栏投资合计 */
        Double jmtz = tTaskPublish.getJmMoney();
        Double xmtz = tTaskPublish.getXmMoney();
        Double hqmtz = tTaskPublish.getHqlxMoney();
        Double smhtz = tTaskPublish.getSmhzlMoney();
        Double wlInvestment = tTaskPublish.getWlInvestment();
        if (jmtz == null) {
            jmtz = 0.0;
            tTaskPublish.setJmMoney(0.0);
        }
        if (xmtz == null) {
            xmtz = 0.0;
            tTaskPublish.setXmMoney(0.0);
        }
        if (hqmtz == null) {
            hqmtz = 0.0;
            tTaskPublish.setHqlxMoney(0.0);
        }
        if (smhtz == null) {
            smhtz = 0.0;
            tTaskPublish.setSmhzlMoney(0.0);
        }
        if (wlInvestment == null) {
            wlInvestment = 0.0;
            tTaskPublish.setWlInvestment(0.0);
        }
        Double wlInvestments = jmtz + xmtz + hqmtz + smhtz + wlInvestment;
        tTaskPublish.setWljeCount(Double.parseDouble(df.format(wlInvestments)));
        /** 总面积合计 */
        Double thcmj = tTaskPublish.getThzyglSize();
        Double rgsmj = tTaskPublish.getRgscdSize();
        Double hspmj = tTaskPublish.getHspjSize();
        Double httmj = tTaskPublish.getHttSize();
        Double dhcmj = tTaskPublish.getDhcSize();
        if (thcmj == null) {
            thcmj = 0.0;
            tTaskPublish.setThzyglSize(0.0);
        }
        if (rgsmj == null) {
            rgsmj = 0.0;
            tTaskPublish.setRgscdSize(0.0);
        }
        if (hspmj == null) {
            hspmj = 0.0;
            tTaskPublish.setHspjSize(0.0);
        }
        if (httmj == null) {
            httmj = 0.0;
            tTaskPublish.setHttSize(0.0);
        }
        if (dhcmj == null) {
            dhcmj = 0.0;
            tTaskPublish.setDhcSize(0.0);
        }

        Double zgmCount = thcmj + rgsmj + hspmj + httmj + dhcmj + wlScales;
        tTaskPublish.setZgmCount(Double.parseDouble(df.format(zgmCount)));
        /** 总投资合计 */
        Double thctz = tTaskPublish.getThzyglMoney();
        Double rgstz = tTaskPublish.getRgscdMoney();
        Double hsptz = tTaskPublish.getHspjMoney();
        Double htttz = tTaskPublish.getHttMoney();
        Double dhctz = tTaskPublish.getDhcMoney();
        if (thctz == null) {
            thctz = 0.0;
            tTaskPublish.setThzyglMoney(0.0);
        }
        if (rgstz == null) {
            rgstz = 0.0;
            tTaskPublish.setRgscdMoney(0.0);
        }
        if (hsptz == null) {
            hsptz = 0.0;
            tTaskPublish.setHspjMoney(0.0);
        }
        if (htttz == null) {
            htttz = 0.0;
            tTaskPublish.setHttMoney(0.0);
        }
        if (dhctz == null) {
            dhctz = 0.0;
            tTaskPublish.setDhcMoney(0.0);
        }
        Double zjeCount = thctz + rgstz + hsptz + htttz + dhctz + wlInvestments;
        tTaskPublish.setZjeCount(Double.parseDouble(df.format(zjeCount)));
        /** 中央地方资金 */
        Double zyzj = tTaskPublish.getZyMoney();
        Double dfzj = tTaskPublish.getDfMoney();
        if (zyzj == null) {
            tTaskPublish.setZyMoney(0.0);
        }
        if (dfzj == null) {
            tTaskPublish.setDfMoney(0.0);
        }
        //获取职级
        String auditName = roleName;

        tTaskPublish.setAuditName(auditName);
        mmap.put("tTaskResolve", tTaskPublish);
        return "main";
    }

    // 系统介绍
    @PostMapping(value = "/system/ajaxmain", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public AjaxResult ajaxmain() {
        DecimalFormat df = new DecimalFormat("#.00");
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        SysRole sysRole = new SysRole();
        if (roles.size() != 0) {
            sysRole = roles.get(0);
        } else {
            sysRole.setRoleName("国家级");
        }
        String roleName = sysRole.getRoleName();
        TTaskPublish tTaskPublish = new TTaskPublish();
        if (roleName.equals("国家级")) {
            tTaskPublish.setYear(DateUtils.getDate().substring(0, 4));
            tTaskPublish = tTaskPublishService.selectYangtseRiver(tTaskPublish);
            if (tTaskPublish == null) {
                tTaskPublish = new TTaskPublish();
                tTaskPublish.setJmSize(0.0);
                tTaskPublish.setXmSize(0.0);
                tTaskPublish.setHqlxSize(0.0);
                tTaskPublish.setSmhzlSize(0.0);
                tTaskPublish.setJmMoney(0.0);
                tTaskPublish.setXmMoney(0.0);
                tTaskPublish.setHqlxMoney(0.0);
                tTaskPublish.setSmhzlMoney(0.0);
                tTaskPublish.setThzyglSize(0.0);
                tTaskPublish.setRgscdSize(0.0);
                tTaskPublish.setHspjSize(0.0);
                tTaskPublish.setHttSize(0.0);
                tTaskPublish.setDhcSize(0.0);
                tTaskPublish.setYkcyzlSize(0.0);
                tTaskPublish.setThzyglMoney(0.0);
                tTaskPublish.setRgscdMoney(0.0);
                tTaskPublish.setHspjMoney(0.0);
                tTaskPublish.setHttMoney(0.0);
                tTaskPublish.setDhcMoney(0.0);
                tTaskPublish.setYkcazlMoney(0.0);
                tTaskPublish.setZyMoney(0.0);
                tTaskPublish.setDfMoney(0.0);
                tTaskPublish.setYear(DateUtils.getDate().substring(0, 4));
            }
            tTaskPublish.setYear(DateUtils.getDate().substring(0, 4));
            tTaskPublish.setProvince("长江经济带");
        }

        /** 围栏规模合计 */
        Double jmgm = tTaskPublish.getJmSize();
        Double xmgm = tTaskPublish.getXmSize();
        Double hqmgm = tTaskPublish.getHqlxSize();
        Double smhgm = tTaskPublish.getSmhzlSize();
        Double wlScale = tTaskPublish.getWlScale();
        if (jmgm == null) {
            jmgm = 0.0;
            tTaskPublish.setJmSize(0.0);
        }
        if (xmgm == null) {
            xmgm = 0.0;
            tTaskPublish.setXmSize(0.0);
        }
        if (hqmgm == null) {
            hqmgm = 0.0;
            tTaskPublish.setHqlxSize(0.0);
        }
        if (smhgm == null) {
            smhgm = 0.0;
            tTaskPublish.setSmhzlSize(0.0);
        }
        if (wlScale == null) {
            wlScale = 0.0;
            tTaskPublish.setWlScale(0.0);
        }
        Double wlScales = jmgm + xmgm + hqmgm + smhgm + wlScale;

        tTaskPublish.setWlmjCount(Double.parseDouble(df.format(wlScales)));

        /** 围栏投资合计 */
        Double jmtz = tTaskPublish.getJmMoney();
        Double xmtz = tTaskPublish.getXmMoney();
        Double hqmtz = tTaskPublish.getHqlxMoney();
        Double smhtz = tTaskPublish.getSmhzlMoney();
        Double wlInvestment = tTaskPublish.getWlInvestment();
        if (jmtz == null) {
            jmtz = 0.0;
            tTaskPublish.setJmMoney(0.0);
        }
        if (xmtz == null) {
            xmtz = 0.0;
            tTaskPublish.setXmMoney(0.0);
        }
        if (hqmtz == null) {
            hqmtz = 0.0;
            tTaskPublish.setHqlxMoney(0.0);
        }
        if (smhtz == null) {
            smhtz = 0.0;
            tTaskPublish.setSmhzlMoney(0.0);
        }
        if (wlInvestment == null) {
            wlInvestment = 0.0;
            tTaskPublish.setWlInvestment(0.0);
        }
        Double wlInvestments = jmtz + xmtz + hqmtz + smhtz + wlInvestment;
        tTaskPublish.setWljeCount(Double.parseDouble(df.format(wlInvestments)));
        /** 总面积合计 */
        Double thcmj = tTaskPublish.getThzyglSize();
        Double rgsmj = tTaskPublish.getRgscdSize();
        Double hspmj = tTaskPublish.getHspjSize();
        Double httmj = tTaskPublish.getHttSize();
        Double dhcmj = tTaskPublish.getDhcSize();
        if (thcmj == null) {
            thcmj = 0.0;
            tTaskPublish.setThzyglSize(0.0);
        }
        if (rgsmj == null) {
            rgsmj = 0.0;
            tTaskPublish.setRgscdSize(0.0);
        }
        if (hspmj == null) {
            hspmj = 0.0;
            tTaskPublish.setHspjSize(0.0);
        }
        if (httmj == null) {
            httmj = 0.0;
            tTaskPublish.setHttSize(0.0);
        }
        if (dhcmj == null) {
            dhcmj = 0.0;
            tTaskPublish.setDhcSize(0.0);
        }

        Double zgmCount = thcmj + rgsmj + hspmj + httmj + dhcmj + wlScales;
        tTaskPublish.setZgmCount(Double.parseDouble(df.format(zgmCount)));
        /** 总投资合计 */
        Double thctz = tTaskPublish.getThzyglMoney();
        Double rgstz = tTaskPublish.getRgscdMoney();
        Double hsptz = tTaskPublish.getHspjMoney();
        Double htttz = tTaskPublish.getHttMoney();
        Double dhctz = tTaskPublish.getDhcMoney();
        if (thctz == null) {
            thctz = 0.0;
            tTaskPublish.setThzyglMoney(0.0);
        }
        if (rgstz == null) {
            rgstz = 0.0;
            tTaskPublish.setRgscdMoney(0.0);
        }
        if (hsptz == null) {
            hsptz = 0.0;
            tTaskPublish.setHspjMoney(0.0);
        }
        if (htttz == null) {
            htttz = 0.0;
            tTaskPublish.setHttMoney(0.0);
        }
        if (dhctz == null) {
            dhctz = 0.0;
            tTaskPublish.setDhcMoney(0.0);
        }
        Double zjeCount = thctz + rgstz + hsptz + htttz + dhctz + wlInvestments;
        tTaskPublish.setZjeCount(Double.parseDouble(df.format(zjeCount)));
        /** 中央地方资金 */
        Double zyzj = tTaskPublish.getZyMoney();
        Double dfzj = tTaskPublish.getDfMoney();
        if (zyzj == null) {
            tTaskPublish.setZyMoney(0.0);
        }
        if (dfzj == null) {
            tTaskPublish.setDfMoney(0.0);
        }
        //获取职级
        String auditName = roleName;
        tTaskPublish.setAuditName(auditName);
        return AjaxResult.success(tTaskPublish);
    }

    private Improve getImprove() {
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        //获取角色
        String roleName = "";
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            roleName += sysRole.getRoleName() + ",";
        }
        //获取当前年份
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        TTaskPublish tTaskPublish = new TTaskPublish();
        TTaskResolve tTaskResolve = new TTaskResolve();
        tTaskPublish.setYear(year + "");
        tTaskResolve.setYear(year + "");
        if (roleName.contains("市级")) {
            tTaskResolve.setProvince(deptName);
            List<TTaskResolve> listAreaData = tTaskResolveService.selectAreaResolveListH(tTaskResolve);
            //存储处理数据
            Improve improve = new Improve();
            /**
             * 从退牧还草工程任务领取分解表查询所有年份
             */
            List<Integer> yearRange = new ArrayList<>();
            for (int a = year; a <= year; a++) {
                yearRange.add(a);
            }
            improve.setYearCols(yearRange);
            /**
             * 查询出所有的区县
             */
            Set<String> areaDistinct = new HashSet<>();
            listAreaData.forEach(item -> areaDistinct.add(item.getAddress()));
            improve.setAreaRows(areaDistinct);
            improve.setProvinceRows(areaDistinct);
            /**
             * 将省份、年份进行处理（省份：此年份中所有数值之和）
             */
            List<Map<String, String>> datas = new ArrayList<>();
            areaDistinct.forEach(address -> {
                Map<String, String> map = new HashMap<>();
                yearRange.forEach(years -> {
                    double sum = Double.parseDouble(df.format(listAreaData.stream()
                            .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getAddress().equals(address))
                            .mapToDouble(TTaskResolve::getTotalScale).sum()));
                    map.put(String.valueOf(year), String.valueOf(sum));
                });
                String province = address;
                map.put("province", province);
                datas.add(map);
            });
            improve.setDatas(datas);
            return improve;
        }
        if (roleName.contains("省级")) {
            tTaskPublish.setProvince(deptName);
            List<TTaskPublish> listCityData = tTaskPublishService.selectCityPublishList(tTaskPublish);
            //存储处理数据
            Improve improve = new Improve();
            /**
             * 从退牧还草工程任务领取分解表查询所有年份
             */
            List<Integer> yearRange = new ArrayList<>();
            for (int a = year; a <= year; a++) {
                yearRange.add(a);
            }
            improve.setYearCols(yearRange);
            /**
             * 查询出所有的城市
             */
            Set<String> cityDistinct = new HashSet<>();
            listCityData.forEach(item -> cityDistinct.add(item.getAddress()));
            improve.setCityRows(cityDistinct);
            improve.setProvinceRows(cityDistinct);
            /**
             * 将省份、年份进行处理（省份：此年份中所有数值之和）
             */
            List<Map<String, String>> datas = new ArrayList<>();
            cityDistinct.forEach(address -> {
                Map<String, String> map = new HashMap<>();
                yearRange.forEach(years -> {
                    double sum = Double.parseDouble(df.format(listCityData.stream()
                            .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getAddress().equals(address))
                            .mapToDouble(TTaskPublish::getTotalScale).sum()));
                    map.put(String.valueOf(year), String.valueOf(sum));
                });
                String province = address;
                map.put("province", province);
                datas.add(map);
            });
            improve.setDatas(datas);
            return improve;
        }
        if (roleName.contains("国家级")) {
            List<TTaskPublish> listData = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
            //存储处理数据
            Improve improve = new Improve();
            /**
             * 从退牧还草工程任务领取分解表查询所有年份
             */
            List<Integer> yearRange = new ArrayList<>();
            for (int a = year; a <= year; a++) {
                yearRange.add(a);
            }
            improve.setYearCols(yearRange);
            /**
             * 查询出所有的省份
             */
            Set<String> provinceDistinct = new HashSet<>();
            listData.forEach(item -> provinceDistinct.add(item.getProvince()));
            improve.setProvinceRows(provinceDistinct);
            /**
             * 将省份、年份进行处理（省份：此年份中所有数值之和）
             */
            List<Map<String, String>> datas = new ArrayList<>();
            provinceDistinct.forEach(province -> {
                Map<String, String> map = new HashMap<>();
                yearRange.forEach(years -> {
                    double sum = Double.parseDouble(df.format(listData.stream()
                            .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getProvince().equals(province))
                            .mapToDouble(TTaskPublish::getTotalScale).sum()));
                    map.put(String.valueOf(year), String.valueOf(sum));
                });
                map.put("province", province);
                datas.add(map);
            });
            improve.setDatas(datas);
            return improve;
        }
        return null;
    }

    @PostMapping("/getOptionHistogram")
    @ResponseBody
    public Improve getOption() {
        Improve improve = getImprove();
        return improve;
    }
}
