package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.mapper.TTaskPublishMapper;
import com.ruoyi.system.mapper.TTaskResolveMapper;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskResolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * 退牧还草工程任务统计Controller
 *
 * @author gk
 * @date 2020-9-16
 */
@Controller
@RequestMapping("/system/statisticsH")
public class TTaskStatisticsHController extends BaseController {
    private String prefix = "system/statistics";

    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private ITTaskResolveService taskResolveService;
    @Autowired
    private TTaskPublishMapper tTaskPublishMapper;
    @Autowired
    private TTaskResolveMapper tTaskResolveMapper;

    @GetMapping()
    public String statistics(ModelMap mmap) {
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        Long deptid = ShiroUtils.getSysUser().getDeptId();//部门id
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String roleNames = "";
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            roleNames += sysRole.getRoleName() + ",";
        }

        if (!roleNames.contains("超级管理员")) {
            if (roleNames.contains("国家级")) {
                mmap.put("user", "国家级");
            } else if (roleNames.contains("省级")) {
                String provinceCode = iSysDictDataService.selectDictCodeByDictLabel(deptName);
                mmap.put("user", "省级");
                mmap.put("provinceName", deptName);
                mmap.put("provinceCode", provinceCode);
            } else if (roleNames.contains("市级")) {
                SysDictData sysDictData = iSysDictDataService.selectProvinceByCity(deptName);
                String provinceName = sysDictData.getDictLabel();
                String provinceCode = sysDictData.getDictValue();
                String cityCode = iSysDictDataService.selectDictCodeByDictLabel(deptName);
                ;
                mmap.put("user", "市级");
                mmap.put("provinceName", provinceName);
                mmap.put("provinceCode", provinceCode);
                mmap.put("cityName", deptName);
                mmap.put("cityCode", cityCode);
            }
        }

        return prefix + "/statisticsH";
    }

    /**
     * 实施分析数据列表查询
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskPublish tTaskPublish) {
//        //获取当前年份
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
        //判断值是否为空
        if (!"".equals(tTaskPublish.getProvince()) && tTaskPublish.getProvince() != null) {
            tTaskPublish.setProvince(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskPublish.getProvince())).getDictLabel());
        }
        if (!"".equals(tTaskPublish.getCity()) && tTaskPublish.getCity() != null) {
            tTaskPublish.setCity(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskPublish.getCity())).getDictLabel());
        }
        if (!"".equals(tTaskPublish.getAddress()) && tTaskPublish.getAddress() != null) {
            tTaskPublish.setAddress(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskPublish.getAddress())).getDictLabel());
        }
//        if ("".equals(tTaskPublish.getYear()) && tTaskPublish.getYear() == null) {
//            tTaskPublish.setYear(year+"");
//        }
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //保留两位小数
        DecimalFormat df = new DecimalFormat("0.00");

        TTaskResolve tTaskResolve = new TTaskResolve();
        TTaskResolve tTaskResolveH = new TTaskResolve();
        List<TTaskPublish> tTaskPublisha = new ArrayList<>();

        if (tTaskPublish.getProvince() == "" && tTaskPublish.getAddress() == "") {
            List<TTaskPublish> tTaskPublishs = new ArrayList<>();
            TTaskPublish countryH = tTaskPublishMapper.selectCountryH(tTaskPublish);
            startPage();
            List<TTaskPublish> list = tTaskPublishService.selectProvince(tTaskPublish);
            if (countryH != null) {
                countryH.setProvince("全国");
                countryH.setAddress("全国");
                list.add(0, countryH);
            }
            for (int i = 0; i < list.size(); i++) {
                TTaskPublish Publish = list.get(i);

                /** 围栏规模合计 */
                Double xmSize = Publish.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                Double jmSize = Publish.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double smhzlSizeSize = Publish.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                Double hqlxSizeSize = Publish.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                Double wlScale = Publish.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize + wlScale;
                Publish.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));


                /** 围栏投资合计 */
                Double jmMoney = Publish.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                Double xmMoney = Publish.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                Double hqlxMoney = Publish.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                Double smhzlMoney = Publish.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                Double wlInvestment = Publish.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney + wlInvestment;


                /** 其他规模合计 */
                Double thzyglSize = Publish.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                Double rgscdSize = Publish.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                Double hspjSize = Publish.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                Double httSize = Publish.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                Double dhcSize = Publish.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
                Publish.setQtmjCount(Double.parseDouble(df.format(qtCount)));

                /** 其金额合计 */
                Double thzyglMoney = Publish.getThzyglMoney();
                if (thzyglMoney == null) {
                    thzyglMoney = 0.0;
                }
                Double rgscdMoney = Publish.getRgscdMoney();
                if (rgscdMoney == null) {
                    rgscdMoney = 0.0;
                }
                Double hspjMoney = Publish.getHspjMoney();
                if (hspjMoney == null) {
                    hspjMoney = 0.0;
                }
                Double httMoney = Publish.getHttMoney();
                if (httMoney == null) {
                    httMoney = 0.0;
                }
                Double dhcMoney = Publish.getDhcMoney();
                if (dhcMoney == null) {
                    dhcMoney = 0.0;
                }
                Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
                Publish.setQtjeCount(Double.parseDouble(df.format(qtjeCount)));

                /** 总规模合计 */
                Double zgmCount = Publish.getQtmjCount() + Publish.getWlmjCount();
                Publish.setZgmCount(Double.parseDouble(df.format(zgmCount)));


                /** 总金额 */
                Double zjeCount = qtjeCount + wljeCount;
                Publish.setZjeCount(Double.parseDouble(df.format(zjeCount)));
                Publish.setAddress(Publish.getProvince());


            }
            return getDataTable(list);
        }

        if (tTaskPublish.getProvince() != "" && tTaskPublish.getAddress() == "") {
            List<TTaskPublish> tTaskPublishs = new ArrayList<>();

            TTaskPublish countryH = tTaskPublishMapper.selectProvinceH(tTaskPublish);
            startPage();
            List<TTaskPublish> list = tTaskPublishService.selectCityPublishList(tTaskPublish);
            if (countryH != null) {
                countryH.setProvince(tTaskPublish.getProvince());
                countryH.setAddress(tTaskPublish.getProvince());
                list.add(0, countryH);
            }
            for (int i = 0; i < list.size(); i++) {
                TTaskPublish Publish = list.get(i);

                /** 围栏规模合计 */
                Double xmSize = Publish.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                Double jmSize = Publish.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double smhzlSizeSize = Publish.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                Double hqlxSizeSize = Publish.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                Double wlScale = Publish.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize + wlScale;
                Publish.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));


                /** 围栏投资合计 */
                Double jmMoney = Publish.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                Double xmMoney = Publish.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                Double hqlxMoney = Publish.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                Double smhzlMoney = Publish.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                Double wlInvestment = Publish.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney + wlInvestment;


                /** 其他规模合计 */
                Double thzyglSize = Publish.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                Double rgscdSize = Publish.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                Double hspjSize = Publish.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                Double httSize = Publish.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                Double dhcSize = Publish.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
                Publish.setQtmjCount(Double.parseDouble(df.format(qtCount)));

                /** 其金额合计 */
                Double thzyglMoney = Publish.getThzyglMoney();
                if (thzyglMoney == null) {
                    thzyglMoney = 0.0;
                }
                Double rgscdMoney = Publish.getRgscdMoney();
                if (rgscdMoney == null) {
                    rgscdMoney = 0.0;
                }
                Double hspjMoney = Publish.getHspjMoney();
                if (hspjMoney == null) {
                    hspjMoney = 0.0;
                }
                Double httMoney = Publish.getHttMoney();
                if (httMoney == null) {
                    httMoney = 0.0;
                }
                Double dhcMoney = Publish.getDhcMoney();
                if (dhcMoney == null) {
                    dhcMoney = 0.0;
                }
                Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
                Publish.setQtjeCount(Double.parseDouble(df.format(qtjeCount)));

                /** 总规模合计 */
                Double zgmCount = Publish.getQtmjCount() + Publish.getWlmjCount();
                Publish.setZgmCount(Double.parseDouble(df.format(zgmCount)));


                /** 总金额 */
                Double zjeCount = qtjeCount + wljeCount;
                Publish.setZjeCount(Double.parseDouble(df.format(zjeCount)));
//                tTaskPublishs.add(Publish);

            }
            return getDataTable(list);
        }
        if (tTaskPublish.getProvince() != "" && tTaskPublish.getAddress() != "" && tTaskPublish.getCity() == "") {
            List<TTaskResolve> tTaskPublishs = new ArrayList<>();
            tTaskResolve.setYear(tTaskPublish.getYear());
            tTaskResolve.setProvince(tTaskPublish.getAddress());
            List<TTaskResolve> list = taskResolveService.selectAreaResolveListH(tTaskResolve);
            if (!tTaskPublish.getYear().equals("")) {
                tTaskResolveH.setYear(tTaskPublish.getYear());
            }
            tTaskResolveH.setProvince(tTaskPublish.getProvince());
            tTaskResolveH.setAddress(tTaskPublish.getAddress());
            TTaskResolve countryH = tTaskResolveMapper.selectProvinceH(tTaskResolveH);
            if (countryH != null) {
                countryH.setProvince(tTaskPublish.getAddress());
                countryH.setAddress(tTaskPublish.getAddress());
                list.add(0, countryH);
            }
            for (int i = 0; i < list.size(); i++) {
                TTaskResolve Resolve = list.get(i);

                /** 围栏规模合计 */
                Double xmSize = Resolve.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                Double jmSize = Resolve.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double smhzlSizeSize = Resolve.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                Double hqlxSizeSize = Resolve.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                Double wlScale = Resolve.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize + wlScale;
                Resolve.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));


                /** 围栏投资合计 */
                Double jmMoney = Resolve.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                Double xmMoney = Resolve.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                Double hqlxMoney = Resolve.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                Double smhzlMoney = Resolve.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                Double wlInvestment = Resolve.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney + wlInvestment;
                Resolve.setWljeCount(Double.parseDouble(df.format(wljeCount)));


                /** 其他规模合计 */
                Double thzyglSize = Resolve.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                Double rgscdSize = Resolve.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                Double hspjSize = Resolve.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                Double httSize = Resolve.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                Double dhcSize = Resolve.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
                Resolve.setQtmjCount(qtCount);

                /** 其金额合计 */
                Double thzyglMoney = Resolve.getThzyglMoney();
                if (thzyglMoney == null) {
                    thzyglMoney = 0.0;
                }
                Double rgscdMoney = Resolve.getRgscdMoney();
                if (rgscdMoney == null) {
                    rgscdMoney = 0.0;
                }
                Double hspjMoney = Resolve.getHspjMoney();
                if (hspjMoney == null) {
                    hspjMoney = 0.0;
                }
                Double httMoney = Resolve.getHttMoney();
                if (httMoney == null) {
                    httMoney = 0.0;
                }
                Double dhcMoney = Resolve.getDhcMoney();
                if (dhcMoney == null) {
                    dhcMoney = 0.0;
                }
                Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
                Resolve.setQtjeCount(qtjeCount);

                /** 总规模合计 */
                Double zgmCount = Resolve.getQtmjCount() + Resolve.getWlmjCount();
                Resolve.setZgmCount(zgmCount);


                /** 总金额 */
                Double zjeCount = Resolve.getQtjeCount() + Resolve.getWljeCount();
                Resolve.setZjeCount(zjeCount);
//                tTaskPublishs.add(Resolve);
            }
            return getDataTable(list);
        }

        if (tTaskPublish.getProvince() != "" && tTaskPublish.getAddress() != "" && tTaskPublish.getCity() != "") {
            List<TTaskResolve> tTaskPublishs = new ArrayList<>();
            tTaskResolve.setYear(tTaskPublish.getYear());
            tTaskResolve.setProvince(tTaskPublish.getAddress());
            if (!tTaskPublish.getYear().equals("")) {
                tTaskResolveH.setYear(tTaskPublish.getYear());
            }
            tTaskResolveH.setProvince(tTaskPublish.getAddress());
            tTaskResolveH.setAddress(tTaskPublish.getCity());
            TTaskResolve countryH = tTaskResolveMapper.selectAreaH(tTaskResolveH);
            if (countryH != null) {
                /** 围栏规模合计 */
                Double xmSize = countryH.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                Double jmSize = countryH.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double smhzlSizeSize = countryH.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                Double hqlxSizeSize = countryH.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                Double wlScale = countryH.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize + wlScale;
                countryH.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));


                /** 围栏投资合计 */
                Double jmMoney = countryH.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                Double xmMoney = countryH.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                Double hqlxMoney = countryH.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                Double smhzlMoney = countryH.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                Double wlInvestment = countryH.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney + wlInvestment;
                countryH.setWljeCount(Double.parseDouble(df.format(wljeCount)));


                /** 其他规模合计 */
                Double thzyglSize = countryH.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                Double rgscdSize = countryH.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                Double hspjSize = countryH.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                Double httSize = countryH.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                Double dhcSize = countryH.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
                countryH.setQtmjCount(qtCount);

                /** 其金额合计 */
                Double thzyglMoney = countryH.getThzyglMoney();
                if (thzyglMoney == null) {
                    thzyglMoney = 0.0;
                }
                Double rgscdMoney = countryH.getRgscdMoney();
                if (rgscdMoney == null) {
                    rgscdMoney = 0.0;
                }
                Double hspjMoney = countryH.getHspjMoney();
                if (hspjMoney == null) {
                    hspjMoney = 0.0;
                }
                Double httMoney = countryH.getHttMoney();
                if (httMoney == null) {
                    httMoney = 0.0;
                }
                Double dhcMoney = countryH.getDhcMoney();
                if (dhcMoney == null) {
                    dhcMoney = 0.0;
                }
                Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
                countryH.setQtjeCount(qtjeCount);

                /** 总规模合计 */
                Double zgmCount = countryH.getQtmjCount() + countryH.getWlmjCount();
                countryH.setZgmCount(zgmCount);


                /** 总金额 */
                Double zjeCount = countryH.getQtjeCount() + countryH.getWljeCount();
                countryH.setZjeCount(zjeCount);
                tTaskPublishs.add(0, countryH);
            }
            return getDataTable(tTaskPublishs);
        }
        return getDataTable(tTaskPublisha);
    }

    /**
     * 三级联动省级
     */
    @RequestMapping("/getProvinces")
    @ResponseBody
    public List<SysDictData> getProvinces() {
        return iSysDictDataService.selectDictDataByType("sys_province");
    }

    /**
     * 三级联动市级
     */
    @RequestMapping("/getCities")
    @ResponseBody
    public List<SysDictData> getCities(String provinceCode) {
        return iSysDictDataService.getCities(provinceCode);
    }

    /**
     * 三级联动曲县级
     */
    @RequestMapping("/getAreas")
    @ResponseBody
    public List<SysDictData> getAreas(String cityCode) {
        return iSysDictDataService.getAreas(cityCode);
    }

    /**
     * 任务统计-横向统计图表
     */
    @RequestMapping("/statisticsHHistogram")
    @ResponseBody
    public List statisticsHHistogram(TTaskPublish tTaskPublish) {
        //地区code转换为地区名称
        tTaskPublish = codeConversionName(tTaskPublish);
        //保留两位小数
        DecimalFormat df = new DecimalFormat("0.00");
        TTaskResolve tTaskResolve = new TTaskResolve();
        TTaskResolve tTaskResolveH = new TTaskResolve();
        List<TTaskPublish> tTaskPublisha = new ArrayList<>();
        if (tTaskPublish.getProvince() == "" && tTaskPublish.getAddress() == "") {
            List<TTaskPublish> tTaskPublishs = new ArrayList<>();
            List<TTaskPublish> list = tTaskPublishService.selectProvince(tTaskPublish);
            for (int i = 0; i < list.size(); i++) {
                TTaskPublish Publish = list.get(i);

                /** 围栏规模合计 */
                Double xmSize = Publish.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                Double jmSize = Publish.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double smhzlSizeSize = Publish.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                Double hqlxSizeSize = Publish.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                Double wlScale = Publish.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize + wlScale;
                Publish.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));


                /** 围栏投资合计 */
                Double jmMoney = Publish.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                Double xmMoney = Publish.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                Double hqlxMoney = Publish.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                Double smhzlMoney = Publish.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                Double wlInvestment = Publish.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney + wlInvestment;


                /** 其他规模合计 */
                Double thzyglSize = Publish.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                Double rgscdSize = Publish.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                Double hspjSize = Publish.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                Double httSize = Publish.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                Double dhcSize = Publish.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
                Publish.setQtmjCount(Double.parseDouble(df.format(qtCount)));

                /** 其金额合计 */
                Double thzyglMoney = Publish.getThzyglMoney();
                if (thzyglMoney == null) {
                    thzyglMoney = 0.0;
                }
                Double rgscdMoney = Publish.getRgscdMoney();
                if (rgscdMoney == null) {
                    rgscdMoney = 0.0;
                }
                Double hspjMoney = Publish.getHspjMoney();
                if (hspjMoney == null) {
                    hspjMoney = 0.0;
                }
                Double httMoney = Publish.getHttMoney();
                if (httMoney == null) {
                    httMoney = 0.0;
                }
                Double dhcMoney = Publish.getDhcMoney();
                if (dhcMoney == null) {
                    dhcMoney = 0.0;
                }
                Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
                Publish.setQtjeCount(Double.parseDouble(df.format(qtjeCount)));

                /** 总规模合计 */
                Double zgmCount = Publish.getQtmjCount() + Publish.getWlmjCount();
                Publish.setZgmCount(Double.parseDouble(df.format(zgmCount)));


                /** 总金额 */
                Double zjeCount = qtjeCount + wljeCount;
                Publish.setZjeCount(Double.parseDouble(df.format(zjeCount)));
                Publish.setAddress(Publish.getProvince());


            }
            return list;
        }
        if (tTaskPublish.getProvince() != "" && tTaskPublish.getAddress() == "") {
            List<TTaskPublish> tTaskPublishs = new ArrayList<>();
            List<TTaskPublish> list = tTaskPublishService.selectCityPublishList(tTaskPublish);
            for (int i = 0; i < list.size(); i++) {
                TTaskPublish Publish = list.get(i);

                /** 围栏规模合计 */
                Double xmSize = Publish.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                Double jmSize = Publish.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double smhzlSizeSize = Publish.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                Double hqlxSizeSize = Publish.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                Double wlScale = Publish.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize + wlScale;
                Publish.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));


                /** 围栏投资合计 */
                Double jmMoney = Publish.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                Double xmMoney = Publish.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                Double hqlxMoney = Publish.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                Double smhzlMoney = Publish.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                Double wlInvestment = Publish.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney + wlInvestment;


                /** 其他规模合计 */
                Double thzyglSize = Publish.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                Double rgscdSize = Publish.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                Double hspjSize = Publish.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                Double httSize = Publish.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                Double dhcSize = Publish.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
                Publish.setQtmjCount(Double.parseDouble(df.format(qtCount)));

                /** 其金额合计 */
                Double thzyglMoney = Publish.getThzyglMoney();
                if (thzyglMoney == null) {
                    thzyglMoney = 0.0;
                }
                Double rgscdMoney = Publish.getRgscdMoney();
                if (rgscdMoney == null) {
                    rgscdMoney = 0.0;
                }
                Double hspjMoney = Publish.getHspjMoney();
                if (hspjMoney == null) {
                    hspjMoney = 0.0;
                }
                Double httMoney = Publish.getHttMoney();
                if (httMoney == null) {
                    httMoney = 0.0;
                }
                Double dhcMoney = Publish.getDhcMoney();
                if (dhcMoney == null) {
                    dhcMoney = 0.0;
                }
                Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
                Publish.setQtjeCount(Double.parseDouble(df.format(qtjeCount)));

                /** 总规模合计 */
                Double zgmCount = Publish.getQtmjCount() + Publish.getWlmjCount();
                Publish.setZgmCount(Double.parseDouble(df.format(zgmCount)));


                /** 总金额 */
                Double zjeCount = qtjeCount + wljeCount;
                Publish.setZjeCount(Double.parseDouble(df.format(zjeCount)));
//                tTaskPublishs.add(Publish);

            }
            return list;
        }
        if (tTaskPublish.getProvince() != "" && tTaskPublish.getAddress() != "" && tTaskPublish.getCity() == "") {
            tTaskResolve.setYear(tTaskPublish.getYear());
            tTaskResolve.setProvince(tTaskPublish.getAddress());
            List<TTaskResolve> list = taskResolveService.selectAreaResolveListH(tTaskResolve);
            if (!tTaskPublish.getYear().equals("")) {
                tTaskResolveH.setYear(tTaskPublish.getYear());
            }
            tTaskResolveH.setProvince(tTaskPublish.getProvince());
            tTaskResolveH.setAddress(tTaskPublish.getAddress());
            for (int i = 0; i < list.size(); i++) {
                TTaskResolve Resolve = list.get(i);

                /** 围栏规模合计 */
                Double xmSize = Resolve.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                Double jmSize = Resolve.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double smhzlSizeSize = Resolve.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                Double hqlxSizeSize = Resolve.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                Double wlScale = Resolve.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize + wlScale;
                Resolve.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));


                /** 围栏投资合计 */
                Double jmMoney = Resolve.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                Double xmMoney = Resolve.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                Double hqlxMoney = Resolve.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                Double smhzlMoney = Resolve.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                Double wlInvestment = Resolve.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney + wlInvestment;
                Resolve.setWljeCount(Double.parseDouble(df.format(wljeCount)));


                /** 其他规模合计 */
                Double thzyglSize = Resolve.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                Double rgscdSize = Resolve.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                Double hspjSize = Resolve.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                Double httSize = Resolve.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                Double dhcSize = Resolve.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
                Resolve.setQtmjCount(qtCount);

                /** 其金额合计 */
                Double thzyglMoney = Resolve.getThzyglMoney();
                if (thzyglMoney == null) {
                    thzyglMoney = 0.0;
                }
                Double rgscdMoney = Resolve.getRgscdMoney();
                if (rgscdMoney == null) {
                    rgscdMoney = 0.0;
                }
                Double hspjMoney = Resolve.getHspjMoney();
                if (hspjMoney == null) {
                    hspjMoney = 0.0;
                }
                Double httMoney = Resolve.getHttMoney();
                if (httMoney == null) {
                    httMoney = 0.0;
                }
                Double dhcMoney = Resolve.getDhcMoney();
                if (dhcMoney == null) {
                    dhcMoney = 0.0;
                }
                Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
                Resolve.setQtjeCount(qtjeCount);

                /** 总规模合计 */
                Double zgmCount = Resolve.getQtmjCount() + Resolve.getWlmjCount();
                Resolve.setZgmCount(zgmCount);


                /** 总金额 */
                Double zjeCount = Resolve.getQtjeCount() + Resolve.getWljeCount();
                Resolve.setZjeCount(zjeCount);
//                tTaskPublishs.add(Resolve);
            }
            return list;
        }

        if (tTaskPublish.getProvince() != "" && tTaskPublish.getAddress() != "" && tTaskPublish.getCity() != "") {
            List<TTaskResolve> tTaskPublishs = new ArrayList<>();
            tTaskResolve.setYear(tTaskPublish.getYear());
            tTaskResolve.setProvince(tTaskPublish.getAddress());
            if (!tTaskPublish.getYear().equals("")) {
                tTaskResolveH.setYear(tTaskPublish.getYear());
            }
            tTaskResolveH.setProvince(tTaskPublish.getAddress());
            tTaskResolveH.setAddress(tTaskPublish.getCity());
            return tTaskPublishs;
        }
        return new ArrayList();
    }

    /**
     *  地区code转换为地区名称
     */
    private TTaskPublish codeConversionName(TTaskPublish tTaskPublish){
        //判断值是否为空
        if (!"".equals(tTaskPublish.getProvince()) && tTaskPublish.getProvince() != null) {
            tTaskPublish.setProvince(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskPublish.getProvince())).getDictLabel());
        }
        if (!"".equals(tTaskPublish.getCity()) && tTaskPublish.getCity() != null) {
            tTaskPublish.setCity(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskPublish.getCity())).getDictLabel());
        }
        if (!"".equals(tTaskPublish.getAddress()) && tTaskPublish.getAddress() != null) {
            tTaskPublish.setAddress(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskPublish.getAddress())).getDictLabel());
        }
        return tTaskPublish;
    }
}
