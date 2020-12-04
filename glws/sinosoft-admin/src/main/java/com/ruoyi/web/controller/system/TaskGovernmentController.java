package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.TTaskResolveMapper;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskReportService;
import com.ruoyi.system.service.ITaskGovernmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 黑土滩报表
 *
 * @author feiyanxu
 * @date 2019-01-08
 */
@Controller
@RequestMapping("/system/government")
public class TaskGovernmentController extends BaseController {
    private String prefix = "system/government";

    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private TTaskResolveMapper tTaskResolveMapper;

    @RequiresPermissions("system:government:view")
    @GetMapping()
    public String report() {
        return prefix + "/government";
    }

    /**
     * 查询黑土滩、毒害草、已垦草原治理报表
     */
    @RequiresPermissions("system:government:list")
    //@GetMapping("/list")
    @PostMapping("/list")
    @ResponseBody
    public Improve list(TTaskPublish tTaskPublish, String provinc, String yea) {
        startPage();
        TTaskResolve tTaskResolve = new TTaskResolve();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("市级")) {
                tTaskResolve.setYear(tTaskPublish.getYear());
                Improve improve = getAreaImprove(tTaskResolve, provinc, yea);
                return improve;
            }
        }
        Improve improve = getImprove(tTaskPublish, provinc, yea);
        return improve;
    }

    private Improve getImprove(TTaskPublish tTaskPublish, String provinc, String yea) {
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
                provinc = deptName;
            }
        }
        if (provinc != null || yea != null) {
            tTaskPublish.setProvince(provinc);
            tTaskPublish.setYear(yea);
        } else if (provinc != null && yea == null) {
            tTaskPublish.setProvince(provinc);

        } else if (yea != null && provinc == null) {
            tTaskPublish.setYear(yea);

        }

        //存储处理数据
        Improve improve = new Improve();
        //国家级查询退牧还草工程任务领取分解表所有数据
        List<TTaskPublish> listData = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        //判断库中是否有空值
        for (int i = 0; i < listData.size(); i++) {
            TTaskPublish tTaskPublisha = listData.get(i);

            if (tTaskPublisha.getZyMoney() == null) {
                tTaskPublisha.setZyMoney(0.0);
            }
        }
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("省级")) {
                //省级查询退牧还草工程任务领取分解表所有数据
                List<TTaskPublish> listCityData = tTaskPublishService.selectCityPublishList(tTaskPublish);
                /**
                 * 从退牧还草工程任务领取分解表查询所有年份
                 */
                int minYear = Integer.parseInt(listCityData.stream().min(Comparator.comparing(TTaskPublish::getYear)).get().getYear());
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                List<Integer> yearRange = new ArrayList<>();
                for (int a = minYear; a <= currentYear; a++) {
                    yearRange.add(a);
                }
                Collections.reverse(yearRange);
                improve.setYearCols(yearRange);
                /**
                 * 查询出所有的城市
                 */
                Set<String> cityDistinct = new HashSet<>();
                listCityData.forEach(item -> cityDistinct.add(item.getAddress()));
                improve.setCityRows(cityDistinct);
                /**
                 * 将城市、年份进行处理（省份：此年份中所有数值之和）
                 */
                List<Map<String, String>> datas = new ArrayList<>();
                cityDistinct.forEach(address -> {
                    Map<String, String> map = new HashMap<>();
                    yearRange.forEach(year -> {
                        double sum = Double.parseDouble(df.format(listCityData.stream()
                                .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getAddress().equals(address))
                                .mapToDouble(TTaskPublish::getHttSize).sum()));
                        map.put(String.valueOf(year), String.valueOf(sum));
                    });
                    String province = address;
                    map.put("province", province);
                    datas.add(map);
                });
                improve.setDatas(datas);
                return improve;
            }
        }


        /**
         * 从退牧还草工程任务领取分解表查询所有年份
         */
        int minYear = Integer.parseInt(listData.stream().min(Comparator.comparing(TTaskPublish::getYear)).get().getYear());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearRange = new ArrayList<>();
        for (int i = minYear; i <= currentYear; i++) {
            yearRange.add(i);
        }
        Collections.reverse(yearRange);
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
            yearRange.forEach(year -> {
                double sum = Double.parseDouble(df.format(listData.stream()
                        .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getProvince().equals(province))
                        .mapToDouble(TTaskPublish::getHttSize).sum()));
                map.put(String.valueOf(year), String.valueOf(sum));
            });
            map.put("province", province);
            datas.add(map);
        });
        improve.setDatas(datas);
        return improve;
    }

    private Improve getAreaImprove(TTaskResolve tTaskResolve, String provinc, String yea) {
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        provinc = deptName;
        tTaskResolve.setProvince(provinc);
        //存储处理数据
        Improve improve = new Improve();

        //省级查询退牧还草工程任务领取分解表所有数据
        List<TTaskResolve> listAreaData = tTaskResolveMapper.selectAreaPublishList(tTaskResolve);
        /**
         * 从退牧还草工程任务领取分解表查询所有年份
         */
        int minYear = Integer.parseInt(listAreaData.stream().min(Comparator.comparing(TTaskResolve::getYear)).get().getYear());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearRange = new ArrayList<>();
        for (int a = minYear; a <= currentYear; a++) {
            yearRange.add(a);
        }
        Collections.reverse(yearRange);
        improve.setYearCols(yearRange);
        /**
         * 查询出所有的区
         */
        Set<String> areaDistinct = new HashSet<>();
        listAreaData.forEach(item -> areaDistinct.add(item.getAddress()));
        improve.setAreaRows(areaDistinct);
        /**
         * 将城市、年份进行处理（省份：此年份中所有数值之和）
         */
        List<Map<String, String>> datas = new ArrayList<>();
        areaDistinct.forEach(address -> {
            Map<String, String> map = new HashMap<>();
            yearRange.forEach(year -> {
                double sum = Double.parseDouble(df.format(listAreaData.stream()
                        .filter(item -> item.getYear().equals(String.valueOf(year)) && item.getAddress().equals(address))
                        .mapToDouble(TTaskResolve::getHttSize).sum()));
                map.put(String.valueOf(year), String.valueOf(sum));
            });
            String province = address;
            map.put("province", province);
            datas.add(map);
        });
        improve.setDatas(datas);
        return improve;

    }

//    /**
//     * 导出人围栏分省报表列表
//     */
//    @RequiresPermissions("system:enclosure:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(TTaskReport tTaskReport) {
//        //根据字典键值查询字典标签
//        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskReport.getProvince());
//        tTaskReport.setProvince(provinceLabel);
//        //将查询数据存入
//        List<TTaskReport> list = tTaskReportService.selectProvince(tTaskReport);
//        ExcelUtil<TTaskReport> util = new ExcelUtil<TTaskReport>(TTaskReport.class);
//        //下载后文件名
//        return util.exportExcel(list, "enclosure");
//    }

    @PostMapping("/getOptionHistogram")
    @ResponseBody
    public Improve getOption(TTaskPublish tTaskPublish, String provinc, String year) {
        TTaskResolve tTaskResolve = new TTaskResolve();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();

        if (StringUtils.isEmpty(provinc) && StringUtils.isEmpty(year)) {
            tTaskPublish.setProvince(null);
            for (int i = 0; i < roles.size(); i++) {
                SysRole sysRole = roles.get(i);
                String roleName = sysRole.getRoleName();
                if (roleName.equals("市级")) {
                    tTaskResolve.setProvince(tTaskPublish.getProvince());
                    tTaskResolve.setYear(tTaskPublish.getYear());
                    Improve improve = getAreaImprove(tTaskResolve, provinc, year);
                    return improve;
                }
            }
            Improve improve = getImprove(tTaskPublish, provinc, year);
            return improve;
        } else if (provinc != null && provinc != "" || year != null && year != "") {
            //根据字典键值查询字典标签
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(provinc);
            tTaskPublish.setProvince(provinceLabel);
            for (int i = 0; i < roles.size(); i++) {
                SysRole sysRole = roles.get(i);
                String roleName = sysRole.getRoleName();
                if (roleName.equals("市级")) {
                    tTaskResolve.setProvince(tTaskPublish.getProvince());
                    tTaskResolve.setYear(tTaskPublish.getYear());
                    Improve improve = getAreaImprove(tTaskResolve, provinc, year);
                    return improve;
                }
            }
            Improve improve = getImprove(tTaskPublish, provinc, year);
            return improve;
        }
        return null;

    }

    @GetMapping(value = "/EcharsLineChart")
    public String EcharsLineChart(String province, ModelMap mmap) {
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

