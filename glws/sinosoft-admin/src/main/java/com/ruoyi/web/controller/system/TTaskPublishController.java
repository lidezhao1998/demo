package com.ruoyi.web.controller.system;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.PublishOperateModel;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 退牧还草工程任务发布Controller
 *
 * @author liuhongfei
 * @date 2019-11-28
 */
@Controller
@RequestMapping("/system/publish")
public class TTaskPublishController extends BaseController {
    private String prefix = "system/pulish";

    @Autowired
    private ITTaskPublishService tTaskPublishService;

    @Autowired
    private ITTaskResolveService taskResolveService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private ITTaskRecordService tTaskRecordService;

    @RequiresPermissions("system:publish:view")
    @GetMapping()
    public String publish() {
        return prefix + "/publish";
    }

    /**
     * 查询退牧还草工程任务发布列表
     */
    @RequiresPermissions("system:publish:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskPublish tTaskPublish) {
        startPage();
        //根据字典键值查询字典标签
        if (tTaskPublish.getProvince() != "") {
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
            tTaskPublish.setProvince(provinceLabel);
        }
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        if (deptName.equals("国家级")) {
            System.out.println("国家级部门");
        } else if (deptName.equals("国家级")) {
            tTaskPublish.setProvince(deptName);
        }
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        List<TTaskPublish> countryList = tTaskPublishService.selectTTaskPublishCountryList();
        List<TTaskPublish> allTTask = new ArrayList<>();
        for(int i = 0; i<countryList.size(); i++) {
            allTTask.add(countryList.get(i));
        }
        for(int i = 0; i<list.size(); i++) {
            allTTask.add(list.get(i));
        }
        for (int i = 0; i < allTTask.size(); i++) {
            TTaskPublish Publish = allTTask.get(i);
            if (deptName.equals("国家级")) {
                /** 拼接任务地区 */
                String province = Publish.getProvince();
                String year = Publish.getYear();

                if (Publish.getAddress() == null) {
                    String dz = year + "_" + province;
                    Publish.setDz(dz);
                } else {
                    String address = Publish.getAddress();
                    String dz = year + "_" + province + "" + address;
                    Publish.setDz(dz);
                }
                if(Publish.getStatus().equals("-1")){
                    Publish.setDz(year + "_国家级");
                }
            }

            /** 围栏规模合计 */
            Double wlmjCount = Publish.getWlScale();
            if (wlmjCount == null) {
                wlmjCount = 0.0;
            }
                Publish.setWlmjCount(Double.parseDouble(df.format(wlmjCount)));



            /** 围栏投资合计 */
            Double wljeCount = Publish.getWlInvestment();
            if (wljeCount == null) {
                wljeCount = 0.0;
            }
            Publish.setWljeCount(Double.parseDouble(df.format(wljeCount)));


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


        }

        return getDataTable(allTTask);
    }

    /**
     * 导出退牧还草工程任务发布列表
     */
    @RequiresPermissions("system:publish:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskPublish tTaskPublish) {
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        ExcelUtil<TTaskPublish> util = new ExcelUtil<TTaskPublish>(TTaskPublish.class);
        return util.exportExcel(list, "publish");
    }

    /**
     * 新增退牧还草工程任务发布
     */
    @GetMapping("/addLooks")
    public String addLook() {
        return prefix + "/look";
    }

    /**
     * 保存多条退牧还草工程任务发布
     */
    @GetMapping("/saveOne")
    public String saveOne(ModelMap mmap) {
        return prefix + "/saveOne";
    }

    /**
     * 单条保存退牧还草工程任务发布
     */
    @RequiresPermissions("system:publish:saveOne")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.INSERT)
    @PostMapping("/saveOne")
    @ResponseBody
    public AjaxResult baoCunOne(TTaskPublish tTaskPublish) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        if (list.size() >= 1) {
            return AjaxResult.error("本省本年度已发布，请修改省或年份");
        }
        tTaskPublish.setProvince(provinceLabel);
        tTaskPublish.setStatus("-1");
        return toAjax(tTaskPublishService.insertTTaskPublish(tTaskPublish));
    }

    /**
     * 新增多条退牧还草工程任务发布
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 新增保存并发布退牧还草工程任务发布
     */
    @RequiresPermissions("system:publish:publishList")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.INSERT)
    @PostMapping("/publishList")
    @ResponseBody
    public AjaxResult publishList(TTaskPublish tTaskPublish) {
        //根据字典键值查询字典标签
        //String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(tTaskPublish.getProvince());
        tTaskPublish.setStatus("0");
        tTaskRecordService.insertTTaskRecord(tTaskPublish);
        return toAjax(tTaskPublishService.insertTTaskPublish(tTaskPublish));
    }

    /**
     * 新增单条退牧还草工程任务发布
     */
    @GetMapping("/addOne")
    public String addOne(ModelMap mmap) {
        return prefix + "/addOne";
    }

    /**
     * 单条新增保存并发布退牧还草工程任务发布
     */
//    @RequiresPermissions("system:publish:publishOne")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.INSERT)
    @PostMapping("/publishOne")
    @ResponseBody
    public AjaxResult publishOne(TTaskPublish tTaskPublish) {
        //根据字典键值查询字典标签
        //String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        if (list.size() >= 1) {
            return AjaxResult.error("本省本年度已发布，请修改省或年份");
        }
        tTaskPublish.setProvince(tTaskPublish.getProvince());
        tTaskPublish.setStatus("0");
        tTaskRecordService.insertTTaskRecord(tTaskPublish);
        return toAjax(tTaskPublishService.insertTTaskPublish(tTaskPublish));
    }

    @RequiresPermissions("system:publish:a")
    @GetMapping("/a/{publishId}")
    public String a(@PathVariable("publishId") Long publishId, ModelMap mmap) {
        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(publishId);
        mmap.put("tTaskPublish", tTaskPublish);
        return prefix + "/edit";
    }

    /**
     * 添加回显数据
     */
    @GetMapping("/selectDept/a")
    public String selectDept(TTaskPublish tTaskPublish, ModelMap mmap) {
        return prefix + "/look";
    }

    @GetMapping("/form")
    public String form() {
        return prefix + "/look";
    }

    /**
     * 修改退牧还草工程任务发布
     */
    @GetMapping("/edit/{publishId}")
    public String edit(@PathVariable("publishId") Long publishId, ModelMap mmap) {
        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(publishId);
        //根据省份名称查询dictcode
        String province = iSysDictDataService.selectDictValueCode(tTaskPublish.getProvince());
        tTaskPublish.setProvince(province);
        tTaskRecordService.insertTTaskRecord(tTaskPublish);
        mmap.put("tTaskPublish", tTaskPublish);
        return prefix + "/edit";
    }

    /**
     * 查看退牧还草工程任务发布
     */
    //@RequiresPermissions("system:publish:detail")
    @GetMapping("/detail/{publishId}")
    public String detail(@PathVariable("publishId") Long publishId, ModelMap mmap, Model model) {
        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(publishId);
        mmap.put("tTaskPublish", tTaskPublish);
        return prefix + "/detail";
    }


    /**
     * 修改保存退牧还草工程任务发布
     */
    @RequiresPermissions("system:publish:edit")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TTaskPublish tTaskPublish) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);
        tTaskPublish.setDelStatus("1");
        tTaskRecordService.insertTTaskRecord(tTaskPublish);
        return toAjax(tTaskPublishService.updateTTaskPublish(tTaskPublish));
    }

    /**
     * 删除退牧还草工程任务发布
     */
    @RequiresPermissions("system:publish:remove")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String publishId) {

        return toAjax(tTaskPublishService.deleteTTaskPublishById(Long.parseLong(publishId)));
    }


    /**
     * 三级联动省级
     */

    @GetMapping("/getProvinces")
    @ResponseBody
    public List<String> getProvinces(ModelMap mmap) {
        String dictType = "sys_province";
        List<SysDictData> list = iSysDictDataService.selectDictDataByType(dictType);
        List<String> dictLabel = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SysDictData sysDictData = list.get(i);
            String label = sysDictData.getDictLabel();
            dictLabel.add(label);
        }
        return dictLabel;
    }

    /**
     * 三级联动市级
     */

    @GetMapping("/getCities")
    @ResponseBody
    public List<SysDictData> getCities(ModelMap mmap, String provinceCode) {
        List<SysDictData> list = iSysDictDataService.getCities(provinceCode);
        return list;
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
     * 四级联动曲县级
     */
    @GetMapping("/getdz")
    @ResponseBody
    public List<SysDictData> getdz(ModelMap mmap, String areasCode) {
        List<SysDictData> list = iSysDictDataService.getAreas(areasCode);

        return list;
    }


    /**
     * 撤回操作
     */
    @Log(title = "撤回操作", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult Withdraw(TTaskPublish tTaskPublish) {
        //判断状态为未领取
        if (tTaskPublish.getStatus() == "0") {
            return toAjax(tTaskPublishService.updateTTaskPublishchehui(tTaskPublish));
        } else {
            Long publishId = tTaskPublish.getPublishId();
            taskResolveService.deleteTTaskResolveById(publishId);
            return toAjax(tTaskPublishService.updateTTaskPublishchehui(tTaskPublish));

        }
    }

    /**
     * 发布操作
     */
    @Log(title = "撤回操作", businessType = BusinessType.UPDATE)
    @PostMapping("/Release")
    @ResponseBody
    public AjaxResult Release(Long publishId) {
        return toAjax(tTaskPublishService.updateTTaskPublishRelease(publishId));
    }

    /**
     * 展开详情信息
     */
    @GetMapping("/authDataScope/{resolveId}")
    public String authDataScope(@PathVariable("resolveId") Long resolveId, ModelMap mmap) {
        TTaskResolve tTaskResolve = taskResolveService.selectTTaskResolveById(resolveId);
        mmap.put("tTaskPublish", tTaskResolve);
        return prefix + "/fenjieLook";
    }

    // queren
    @GetMapping("/switchSkin")
    public String switchSkin(ModelMap mmap, TTaskPublish tTaskPublish) {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);
        mmap.put("tTaskPublish", tTaskPublish);
        return prefix + "/look";
    }

    /**
     * 删除退牧还草工程任务领取分解-逻辑删除
     */
    @Log(title = "退牧还草工程任务领取分解-逻辑删除省级", businessType = BusinessType.DELETE)
    @PostMapping("/removePublish")
    @ResponseBody
    public AjaxResult removePublish(String ids) {
        return toAjax(tTaskPublishService.removePublishTTaskResolveByIds(ids));
    }


    /**
     * 统一发布-保存并发布
     * @return
     */
    @Log(title = "统一发布-保存并发布", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addFaBu", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public AjaxResult addSaveFaBu(@RequestBody List<TTaskPublish> tTaskPublishList) {
        //定义分解属性值

        try {
            for (int i = 3; i < tTaskPublishList.size(); i++) {
                TTaskPublish tTaskPublish = tTaskPublishList.get(i);
                if (tTaskPublish.getWlScale() != null
                        || tTaskPublish.getWlInvestment() != null
                        || tTaskPublish.getThzyglSize() != null
                        || tTaskPublish.getThzyglMoney() != null
                        || tTaskPublish.getRgscdSize() != null
                        || tTaskPublish.getRgscdMoney() != null
                        || tTaskPublish.getHspjSize() != null
                        || tTaskPublish.getHspjMoney() != null
                        || tTaskPublish.getHttSize() != null
                        || tTaskPublish.getHttMoney() != null
                        || tTaskPublish.getDhcSize() != null
                        || tTaskPublish.getDhcMoney() != null
                        || tTaskPublish.getZyMoney() != null
                        || tTaskPublish.getDfMoney() != null
                ) {
                    tTaskPublish.setStatus("0");
                    List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
                    if (list.size() >= 1) {
                        return AjaxResult.error("表格中已有发布省，请进行逐条发布");
                    }
                    tTaskRecordService.insertTTaskRecord(tTaskPublish);
                    tTaskPublishService.insertTTaskPublish(tTaskPublish);
                }
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }

    /**
     * 统一发布-保存
     */
    @Log(title = "统一发布-保存", businessType = BusinessType.INSERT)
    @PostMapping(value = "/publishSave", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String publishSave(@RequestBody List<TTaskPublish> tTaskPublishList) {
        //定义分解属性值

        try {
            for (int i = 3; i < tTaskPublishList.size(); i++) {
                TTaskPublish tTaskPublish = tTaskPublishList.get(i);
                if (tTaskPublish.getWlScale() != null
                        || tTaskPublish.getWlInvestment() != null
                        || tTaskPublish.getThzyglSize() != null
                        || tTaskPublish.getThzyglMoney() != null
                        || tTaskPublish.getRgscdSize() != null
                        || tTaskPublish.getRgscdMoney() != null
                        || tTaskPublish.getHspjSize() != null
                        || tTaskPublish.getHspjMoney() != null
                        || tTaskPublish.getHttSize() != null
                        || tTaskPublish.getHttMoney() != null
                        || tTaskPublish.getDhcSize() != null
                        || tTaskPublish.getDhcMoney() != null
                        || tTaskPublish.getZyMoney() != null
                        || tTaskPublish.getDfMoney() != null
                ) {
                    tTaskPublish.setStatus("-1");
                    tTaskPublishService.insertTTaskPublish(tTaskPublish);
                }
            }
            return "400";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转战略调整页面
     */
    @GetMapping("/adjust")
    public String adjust() {
        return prefix + "/adjust";
    }

    /**
     * 跳转调整记录页面
     */
    @GetMapping("/adjustment")
    public String adjustment() {
        return prefix + "/adjustment";
    }


    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public List<PublishOperateModel> importData(@RequestParam("file") MultipartFile file) throws Exception {
        ExcelUtil<PublishOperateModel> util = new ExcelUtil<PublishOperateModel>(PublishOperateModel.class);
        List<PublishOperateModel> publishList = util.importExcel(file.getInputStream());
        return publishList;
    }

    /**
     * 下载模板
     */
    @RequiresPermissions("system:publish:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<PublishOperateModel> util = new ExcelUtil<PublishOperateModel>(PublishOperateModel.class);
        //将省份添加到表格内
        List<PublishOperateModel> list = new ArrayList<PublishOperateModel>();
        List<String> provinces = getProvinces(null);
        for (int i = 0; i < provinces.size(); i++) {
            PublishOperateModel publishOperateModel = new PublishOperateModel();
            publishOperateModel.setProvince(provinces.get(i));
            list.add(publishOperateModel);
        }

        return util.exportExcel(list, "发布表格");
    }

    /**
     * 查询已发布列表
     */
    @RequiresPermissions("system:publish:list")
    @PostMapping("/releaseList")
    @ResponseBody
    public List<TTaskPublish> releaseList(TTaskPublish tTaskPublish) {
        List<TTaskPublish> releaseList = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        return releaseList;
    }

    /**
     * 修改已发布任务(战略调整)
     */
    @RequiresPermissions("system:publish:publishList")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.UPDATE)
    @PostMapping("/updatePublishList")
    @ResponseBody
    public String updatePublishList(@RequestBody List<TTaskPublish> tTaskPublishList) {
        try {
            for (int i = 3; i < tTaskPublishList.size(); i++) {
                TTaskPublish tTaskPublish = tTaskPublishList.get(i);
                tTaskPublish.setStatus("0");
                tTaskPublish.setDelStatus("1");

                tTaskRecordService.insertTTaskRecord(tTaskPublish);
                tTaskPublishService.updateTTaskPublish(tTaskPublish);
            }
            return "400";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转国家级保存修改页面
     */

    @GetMapping("/countryPublish/{year}")
    public String countryPublish(@PathVariable("year") String year,ModelMap mmap) {
        List<TTaskPublish> tTaskPublishes = tTaskPublishService.queryCountryList(year);
        mmap.put("TTaskPublishList",tTaskPublishes);
        return prefix + "/add";
    }

    /**
     * 退牧还草任务完成情况
     */
    @PostMapping("/getMissionCompletion")
    @ResponseBody
    public AjaxResult missionCompletion() {
        return AjaxResult.success(tTaskPublishService.getMissionCompletion());
    }
}
