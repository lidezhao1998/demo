package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.TTaskResolveMapper;
import com.ruoyi.system.service.*;
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
 * 退牧还草工程任务领取分解Controller
 *
 * @author feiyanxu
 * @date 2019-12-16
 */
@Controller
@RequestMapping("/system/resolve")
public class TTaskResolveController extends BaseController {
    private String prefix = "system/resolve";

    @Autowired
    private ITTaskResolveService tTaskResolveService;

    @Autowired
    private ITTaskResolveRecordService tTaskResolveRecordService;

    @Autowired
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private TTaskResolveMapper tTaskResolveMapper;
    @Autowired
    private ITTaskRecordService tTaskRecordService;

    @RequiresPermissions("system:resolve:view")
    @GetMapping()
    public String resolve() {
        return prefix + "/resolve";
    }

    /**
     * 查询退牧还草工程任务领取分解列表
     */
    @RequiresPermissions("system:resolve:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskPublish tTaskPublish) {

        //根据字典键值查询字典标签
        if (tTaskPublish.getProvince() != "") {
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
            tTaskPublish.setProvince(provinceLabel);
        }
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String roleNames="";
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            roleNames += sysRole.getRoleName()+",";
        }
        if(!roleNames.contains("超级管理员")){
            if (roleNames.contains("市级")) {
                tTaskPublish.setAddress(deptName);
            } else if (roleNames.contains("国家级")) {
                tTaskPublish.setProvince(deptName);
            } else {
                tTaskPublish.setProvince(deptName);
            }
        }

        startPage();
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        for (int i = 0; i < list.size(); i++) {
            TTaskPublish Publish = list.get(i);

            /** 拼接任务地区 */
            String province = Publish.getProvince();
            String year = Publish.getYear();

            if (Publish.getAddress() == null) {
                String dz = year + "_" + province;
                Publish.setDz(dz);
            } else {
                String address = Publish.getAddress();
                String dz = year + "_" + province + "_" + address;
                Publish.setDz(dz);

            }
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
            Double wlmiCount = xmSize + jmSize + smhzlSizeSize + hqlxSizeSize;
            if (wlmiCount.equals(0.0)) {
                Double wlScale = Publish.getWlScale();
                if (wlScale == null) {
                    wlScale = 0.0;
                }
                Publish.setWlmjCount(Double.parseDouble(df.format(wlScale)));
            } else {
                Publish.setWlmjCount(Double.parseDouble(df.format(wlmiCount)));
            }


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
            Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney;
            if (wljeCount.equals(0.0)) {
                Double wlInvestment = Publish.getWlInvestment();
                if (wlInvestment == null) {
                    wlInvestment = 0.0;
                }
                Publish.setWljeCount(Double.parseDouble(df.format(wlInvestment)));
            } else {
                Publish.setWljeCount(Double.parseDouble(df.format(wljeCount)));
            }


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
            Publish.setQtmjCount(qtCount);

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
            Publish.setQtjeCount(qtjeCount);

            /** 总规模合计 */
            Double zgmCount = Publish.getQtmjCount() + Publish.getWlmjCount();
            Publish.setZgmCount(zgmCount);


            /** 总金额 */
            Double zjeCount = Publish.getQtjeCount() + Publish.getWljeCount();
            Publish.setZjeCount(zjeCount);
        }

        return getDataTable(list);

    }


    /**
     * 导出退牧还草工程任务领取分解列表
     */
    @RequiresPermissions("system:resolve:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskPublish tTaskPublish) {

        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("市级")) {
                tTaskPublish.setAddress(deptName);
            } else if (deptName.equals("国家级")) {
                tTaskPublish.setProvince(deptName);
            } else {
                tTaskPublish.setProvince(deptName);
            }
        }
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        ExcelUtil<TTaskPublish> util = new ExcelUtil<TTaskPublish>(TTaskPublish.class);
        return util.exportExcel(list, "publish");
    }

    /**
     * 修改退牧还草工程任务领取分解
     */
    @GetMapping("/edit/{resolveId}")
    public String edit(@PathVariable("resolveId") Long publishId, ModelMap mmap) {
        //初始化对象
        TTaskResolve tTaskResolve = new TTaskResolve();
        //获取角色
        String roleName = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //查看后领取
        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskResolveById(publishId);
        if ("2".equals(tTaskPublish.getStatus())) {
            mmap.put("tTaskResolve", tTaskPublish);
        } else {
            tTaskPublishService.updateTTaskResolveStatus(tTaskPublish);
            TTaskPublish tTask = tTaskPublishService.selectTTaskPublishById(publishId);
            mmap.put("tTaskResolve", tTask);
        }
        if (roleName.equals("省级")) {
            //查询省级已分解任务
            List<TTaskPublish> provinceList = tTaskPublishService.selectCityPublishList(tTaskPublish);
            mmap.put("tTaskResolveList", provinceList);
        }
        if(roleName.equals("市级")) {
            tTaskResolve.setYear(tTaskPublish.getYear());
            tTaskResolve.setProvince(deptName);
            List<TTaskResolve> cityList = tTaskResolveService.selectAreaResolveListH(tTaskResolve);
            mmap.put("tTaskResolveList", cityList);
        }
        mmap.addAttribute("roleName", ShiroUtils.getSysUser().getRoles().get(0).getRoleName());
        return prefix + "/edit";


    }


    /**
     * 修改保存退牧还草工程任务领取分解
     */
    @RequiresPermissions("system:resolve:edit")
    @Log(title = "退牧还草工程任务领取分解", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TTaskResolve tTaskResolve) {
        return toAjax(tTaskResolveService.updateTTaskResolve(tTaskResolve));
    }

    /**
     * 删除退牧还草工程任务领取分解
     */
    @RequiresPermissions("system:resolve:remove")
    @Log(title = "退牧还草工程任务领取分解", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tTaskResolveService.deleteTTaskResolveByIds(ids));
    }

    /**
     * 删除退牧还草工程任务领取分解-逻辑删除
     */
    @RequiresPermissions("system:resolve:remove")
    @Log(title = "退牧还草工程任务领取分解-逻辑删除市级", businessType = BusinessType.DELETE)
    @PostMapping("/removeResolve")
    @ResponseBody
    public AjaxResult removeResolve(String ids) {
        return toAjax(tTaskResolveService.removeResolveTTaskResolveByIds(ids));
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
     * 修改省级审核状态
     */

    @PostMapping("/changeProvince")
    @ResponseBody
    public String changeProvince(@RequestParam Map map) {
        Long publishId = Long.parseLong((String) map.get("publishId"));
        String change = (String) map.get("change");

        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(publishId);
        tTaskPublish.setStatus(change);
        tTaskPublishService.updateTTaskPublish(tTaskPublish);

        return prefix + "/changeProvince";
    }

    /**
     * 修改市级审核状态
     */

    @PostMapping("/changeCity")
    @ResponseBody
    public String changeCity(@RequestParam Map map) {
        Long resolveId = Long.parseLong((String) map.get("resolveId"));
        String change = (String) map.get("change");

        TTaskResolve tTaskResolve = tTaskResolveService.selectTTaskResolveById(resolveId);
        tTaskResolve.setStatus(change);
        tTaskResolveService.updateTTaskResolve(tTaskResolve);

        return prefix + "/changeCity";
    }

    /**
     * 新增保存退牧还草工程任务领取市区县分解
     */
    // @RequiresPermissions("system:resolve:add")
    @Log(title = "退牧还草工程任务领取分解", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addFenJie", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String addSaveFenJie(@RequestBody List<TTaskResolve> tTaskResolves) {
        //定义变量发布id
        Long pid = 0l;
        //初始化对象
        TTaskPublish tTaskPublish= new TTaskPublish();
        try {
            for (int i = 5; i < tTaskResolves.size(); i++) {
                //获取分解数据集合
                TTaskResolve tTaskResolve = tTaskResolves.get(i);
                //查询省份
                String province = tTaskResolveService.selectDictValue(tTaskResolve.getAddrCity());
                tTaskResolve.setProvince(province);
                String city = tTaskResolve.getProvince();
                //截取字符串判断从市级开始分解
                String cityString = city.substring(city.length() - 1);
                //判断填写市级
                if ("市".equals(cityString) || "盟".equals(cityString)) {
                    String address = tTaskResolve.getAddrCity();
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("2");
                    //判断填写区县级
                }else {
                    String address = tTaskResolve.getAddrCity();
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("1");
                }
                //取出分解任务父id
                if (tTaskResolve.getPublishId() != null) {
                    Long publishId = tTaskResolve.getPublishId();
                    pid = publishId;

                } else {
                    Long pId = tTaskResolves.get(5).getPublishId();
                    tTaskResolve.setPublishId(pId);
                }

                if (("1").equals(tTaskResolve.getResolveLevel())) {
                    String address = tTaskResolve.getAddrCity();
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("2");
                    tTaskPublishService.updateTTaskPublishStatus(pid);
                    //查询以分解任务
                    tTaskPublish.setYear(tTaskResolve.getYear());
                    tTaskPublish.setProvince(tTaskResolve.getProvince());
                    tTaskPublish.setAddress(tTaskResolve.getAddress());
                    TTaskPublish tTaskPublishs = tTaskPublishService.selectProvinceResolve(tTaskPublish);
                    if(tTaskPublishs != null){
                        //更新已分解任务
                        tTaskResolve.setPublishId(tTaskPublishs.getPublishId());
                        tTaskResolve.setDelStatus("1");
                        tTaskPublishService.updateTTaskPublishCity(tTaskResolve);
                    }else{
                        //市级添加到发布表中
                        tTaskPublishService.insertTTaskPublishCity(tTaskResolve);
                    }
                } else {
                     tTaskPublishService.updateTTaskPublishStatus(pid);

                    TTaskResolve tTaskResolvec = tTaskResolveService.selectCityResolve(tTaskResolve);
                    if(tTaskResolvec != null){
                        //更新已分解任务
                        tTaskResolve.setResolveId(tTaskResolvec.getResolveId());
                        tTaskResolveService.updateTTaskResolve(tTaskResolve);
                    }else{
                        //逐条添加每条分解任务
                        tTaskResolveService.insertTTaskResolve(tTaskResolve);
                    }
                }
                //保存到分解记录表里
                tTaskResolveRecordService.insertTTaskRecord(tTaskResolve);
            }
            return "400";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询退牧还草工程任务分解 追加列表记录
     */
    @PostMapping("/recordList")
    @ResponseBody
    public TableDataInfo recordList(TTaskPublish tTaskPublish) {
        //获取角色
        String roleName = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        if (roleName.equals("省级")) {
            //查询省级任务追加列表
            TTaskRecord tTaskRecord = new TTaskRecord();
            tTaskRecord.setYear(tTaskPublish.getYear());
            tTaskRecord.setProvince(tTaskPublish.getProvince());
            List<TTaskRecord> list = tTaskRecordService.selectTTaskRecordList(tTaskRecord);
            return getDataTable(list);
        }else if(roleName.equals("市级")){
            //查询省级任务追加列表
            TTaskRecord tTaskRecord = new TTaskRecord();
            tTaskRecord.setYear(tTaskPublish.getYear());
            tTaskRecord.setProvince(tTaskPublish.getProvince());
            tTaskRecord.setAddress(tTaskPublish.getAddress());
            List<TTaskRecord> list = tTaskResolveRecordService.selectTTaskRecordList(tTaskRecord);
            return getDataTable(list);
        }else{
            return getDataTable(new ArrayList<>());
        }

    }

    /**
     * 保存全部分解记录 -- 再次进入时查看保存列表 并进行编辑修改
     */
    // @RequiresPermissions("system:resolve:add")
    @Log(title = "退牧还草工程任务领取分解", businessType = BusinessType.INSERT)
    @PostMapping(value = "/saveAllResolve", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String saveAllResolve(@RequestBody List<TTaskResolve> tTaskResolves) {
        /*
        * 1、获取保存列表
        * 2、遍历保存列表 获取单独分解保存任务
        * 3、遍历保存到 分解表里 状态为 -1
        * */

        //定义变量发布id
        Long pid = 0l;
        //初始化对象
        TTaskPublish tTaskPublish= new TTaskPublish();
        try {
            for (int i = 5; i < tTaskResolves.size(); i++) {
                //获取分解数据集合
                TTaskResolve tTaskResolve = tTaskResolves.get(i);
                //查询省份
                String province = tTaskResolveService.selectDictValue(tTaskResolve.getAddrCity());
                tTaskResolve.setProvince(province);
                String city = tTaskResolve.getProvince();
                //截取字符串判断从市级开始分解
                String cityString = city.substring(city.length() - 1);
                //判断填写市级
                if ("市".equals(cityString) || "盟".equals(cityString)) {
                    String address = tTaskResolve.getAddrCity();
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("2");
                    //判断填写区县级
                }else {
                    String address = tTaskResolve.getAddrCity();
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("1");
                }
                //取出分解任务父id
                if (tTaskResolve.getPublishId() != null) {
                    Long publishId = tTaskResolve.getPublishId();
                    pid = publishId;

                } else {
                    Long pId = tTaskResolves.get(5).getPublishId();
                    tTaskResolve.setPublishId(pId);
                }

                if (("1").equals(tTaskResolve.getResolveLevel())) {
                    String address = tTaskResolve.getAddrCity();
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("2");
                    tTaskPublishService.updateTTaskPublishStatus(pid);
                    //查询以分解任务
                    tTaskPublish.setYear(tTaskResolve.getYear());
                    tTaskPublish.setProvince(tTaskResolve.getProvince());
                    tTaskPublish.setAddress(tTaskResolve.getAddress());
                    TTaskPublish tTaskPublishs = tTaskPublishService.selectProvinceResolve(tTaskPublish);
                    if(tTaskPublishs != null){
                        //更新已分解任务
                        tTaskResolve.setPublishId(tTaskPublishs.getPublishId());
                        tTaskResolve.setDelStatus("1");
                        tTaskPublishService.updateTTaskPublishCity(tTaskResolve);
                    }else{
                        //市级添加到发布表中
                        tTaskPublishService.insertTTaskPublishCity(tTaskResolve);
                    }
                } else {
                    tTaskPublishService.updateTTaskPublishStatus(pid);

                    TTaskResolve tTaskResolvec = tTaskResolveService.selectCityResolve(tTaskResolve);
                    if(tTaskResolvec != null){
                        //更新已分解任务
                        tTaskResolve.setResolveId(tTaskResolvec.getResolveId());
                        tTaskResolveService.updateTTaskResolve(tTaskResolve);
                    }else{
                        //逐条添加每条分解任务
                        tTaskResolveService.insertTTaskResolve(tTaskResolve);
                    }
                }
                //保存到分解记录表里
                tTaskResolveRecordService.insertTTaskRecord(tTaskResolve);
            }
            return "400";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转分解记录页面
     */
    @GetMapping("/records")
    public String adjustment() {
        return prefix + "/records";
    }

    /**
     * 查询退牧还草工程任务领取分解列表
     */
    @PostMapping("/resolveRecords")
    @ResponseBody
    public TableDataInfo resolveRecords(TTaskRecord tTaskRecord) {

        startPage();
        List<TTaskRecord> list = tTaskResolveRecordService.selectTTaskRecordList(tTaskRecord);
        return getDataTable(list);
    }

    /**
     * 分解记录查看详情
     */
    @GetMapping("/decompositionRecordDetails/{recordId}")
    public String decompositionRecordDetails(@PathVariable("recordId") Long recordId, ModelMap mmap) {
        TTaskRecord tTaskRecord = tTaskResolveRecordService.selectTTaskRecordById(recordId);
        mmap.put("tTaskRecord", tTaskRecord);
        return "system/resolve/recordsDetail";
    }
}
