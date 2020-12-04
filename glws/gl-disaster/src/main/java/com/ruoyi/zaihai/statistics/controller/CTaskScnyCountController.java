package com.ruoyi.zaihai.statistics.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.zaihai.caiji.domain.CTaskScny;
import com.ruoyi.zaihai.caiji.mapper.CTaskScnyMapper;
import com.ruoyi.zaihai.caiji.service.ICTaskScnyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 草原鼠虫害防治农药使用况Controller
 *
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/zaihai/scnytj")
public class CTaskScnyCountController extends BaseController {
    private String prefix = "statistics/scny";

    @Autowired
    private ICTaskScnyService cTaskScnyService;

    @Autowired
    private CTaskScnyMapper cTaskScnyMapper;

    @Autowired
    private ISysDictDataService iSysDictDataService ;



    @RequiresPermissions("zaihai:scny:view")
    @GetMapping()
    public String scny(ModelMap mmap) {
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            mmap.put("roleName",roleName);

        }
        return prefix + "/scny";
    }

    /**
     * 查询草原鼠虫害防治农药使用况列表
     */
    @RequiresPermissions("zaihai:scny:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskScny cTaskScny) {
        startPage();
        //获取当前年份
      /*  Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if(cTaskScny.getYear()==""){
            cTaskScny.setYear(year+"");
        }*/
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        if(deptName.equals("内蒙古自治区")){
            deptName = deptName.substring(0, deptName.length() - 3);

        }else if(deptName.equals("西藏自治区")){
            deptName = deptName.substring(0, deptName.length() - 3);

        }else if(deptName.equals("宁夏回族自治区")){
            deptName = deptName.substring(0, deptName.length() - 5);

        }else if(deptName.equals("新疆维吾尔自治区")){
            deptName = deptName.substring(0, deptName.length() - 6);
        }else if(deptName.equals("广西壮族自治区")){
            deptName = deptName.substring(0, deptName.length() - 5);
        }else{
            //截取去除最后一个字符串
            deptName = deptName.substring(0, deptName.length() - 1);
        }
        String code = iSysDictDataService.selectDictValueCode(deptName);
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
                cTaskScny.setStatus("2");
                cTaskScny.setProvince(deptName);
                cTaskScny.setCode(code);
                List<CTaskScny> list = getcTaskScnysSum(cTaskScny);
            }else if(roleName.equals("市级")){
                cTaskScny.setStatus("3");
                cTaskScny.setCity(deptName);
                cTaskScny.setCode(code);
                List<CTaskScny> list = getcTaskScnysSum(cTaskScny);

            }else if(roleName.equals("区级")){
                cTaskScny.setStatus("4");
                cTaskScny.setCounty(deptName);
                List<CTaskScny> list = getcTaskScnysSum(cTaskScny);

            }else if(roleName.equals("国家级") || roleName.equals("管理员")){
                cTaskScny.setStatus("1");
                List<CTaskScny> list = getcTaskScnysSum(cTaskScny);

            }
        }
        List<CTaskScny> list = getcTaskScnysSum(cTaskScny);
        getcTaskScnysSum(cTaskScny);
        return getDataTable(list);
    }

    /**
     *省级列表详情展开区县级列表信息
     */
    @RequestMapping("/detailList/{province}")
    public String detailList(@PathVariable("province") String province, ModelMap modelMap)
    {
        startPage();
        List<CTaskScny> list = cTaskScnyMapper.selectCTaskScnyAreaListByProvince(province);
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();

        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("国家级")){
                modelMap.put("roleName", "gj");
            }

        }
        /*根据市名称查询code*/
        String code=iSysDictDataService.selectDictValueCode(province);
        modelMap.put("code", code);
        modelMap.put("province", province);

        return prefix + "/scnyAreaList";
    }

    /**
     * 市级列表详情展示区县级信息
     * @param city
     * @param modelMap
     * @return
     */
    @RequestMapping("/cityList/{city}")
    public String cityList(@PathVariable("city") String city, ModelMap modelMap){
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
                modelMap.put("roleName", "sj");
            }

        }
        /*根据市名称查询code*/
        String code=iSysDictDataService.selectDictValueCode(city);
        modelMap.put("code", code);
        modelMap.put("city", city);

        return prefix + "/scnyAreaList";
    }
    /**
     *市级列表详情展开区县级列表信息
     */
    @RequiresPermissions("zaihai:sczj:list")
    @PostMapping("/list2")
    @ResponseBody
    public TableDataInfo list2(CTaskScny cTaskScny,String province,String city) {
        if(province==null){
        }else{
            cTaskScny.setProvince(province);
        }if(city==null){
        }else{
            cTaskScny.setCity(city);

        }
        List<CTaskScny>   list = cTaskScnyService.selectCTaskScnyCityList(cTaskScny);


        return getDataTable(list);
    }

    /**
     * 查询草原虫害发生与防治情况二三级列表
     */
    @RequiresPermissions("zaihai:scny:list")
    @PostMapping("/ParentList")
    @ResponseBody
    public TableDataInfo ParentList(CTaskScny cTaskScny) {
        startPage();
        // 查询条件过滤
        if (StringUtils.isNotEmpty(cTaskScny.getProvince())) {
            //省级查看市级
            System.out.println(cTaskScny);
            List<CTaskScny> list = cTaskScnyService.selectCTaskScnyList(cTaskScny);
            return getDataTable(list);
        } else if (StringUtils.isNotEmpty(cTaskScny.getCity())) {
            //市级查看县级
            List<CTaskScny> list = cTaskScnyService.selectCTaskScnyList(cTaskScny);
            return getDataTable(list);
        } else {
            List<CTaskScny> list = getcTaskScnysSum(cTaskScny);
            getcTaskScnysSum(cTaskScny);
            return getDataTable(list);

        }

    }

    /**
     * 抽取sum方法将各省份数值进行合计
     */
    private List<CTaskScny> getcTaskScnysSum(CTaskScny cTaskScny) {
        //List<CTaskScny> list = cTaskScnyService.selectCTaskScnyParentList(cTaskScny);

        List<CTaskScny> list = new ArrayList<CTaskScny>();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();

            /*省级用户进入统计页面*/
            if(cTaskScny.getStatus().equals("2")){
                list = cTaskScnyService.selectCTaskScnyCityList(cTaskScny);
                for (int j = 0; j < list.size(); j++) {
                    CTaskScny taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    taskScny.setCode(cTaskScny.getCode());
                    cTaskScnyService.updateCTaskSczjRoleName(taskScny);

                }
                /*市级用户进入统计页面*/
            }else if(cTaskScny.getStatus().equals("3")){
                list = cTaskScnyService.selectCTaskScnyCityList(cTaskScny);
                for (int j = 0; j < list.size(); j++) {
                    CTaskScny taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    taskScny.setCode(cTaskScny.getCode());
                    cTaskScnyService.updateCTaskSczjRoleName(taskScny);

                }
                /*县级用户进入统计页面*/

            }else if(cTaskScny.getStatus().equals("4")){
                list = cTaskScnyService.selectCTaskScnyAreaList(cTaskScny);
                for (int j = 0; j < list.size(); j++) {
                    CTaskScny taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    cTaskScnyService.updateCTaskSczjRoleName(taskScny);
                }
            }

            /*国家级admin进入统计页面*/
            else{
                list = cTaskScnyService.selectCTaskScnyParentList(cTaskScny);
                for (int j = 0; j< list.size(); j++) {
                    CTaskScny taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    cTaskScnyService.updateCTaskSczjRoleName(taskScny);
                }
            }
        }
        Double pesticidePriceCount = 0.0;
        Double orderQuantityCount = 0.0;
        Double fundsUsedCount = 0.0;
        Double controlDosageCount = 0.0;
        Double muDosageCount = 0.0;
        Double chemistryControlCount = 0.0;
        Double totalCount = 0.0;

        for (int i = 0; i < list.size(); i++) {
            CTaskScny taskScny = list.get(i);
            Double pesticidePrice = taskScny.getPesticidePrice();

            if (pesticidePrice == null) {
                taskScny.setPesticidePrice(0.0);
                pesticidePrice = 0.0;
            }

            Double orderQuantity = taskScny.getOrderQuantity();
            if (orderQuantity == null) {
                taskScny.setOrderQuantity(0.0);
                orderQuantity = 0.0;
            }

            Double fundsUsed = taskScny.getFundsUsed();
            if (fundsUsed == null) {
                taskScny.setOrderQuantity(0.0);
                fundsUsed = 0.0;
            }

            Double controlDosage = taskScny.getControlDosage();
            if (controlDosage == null) {
                taskScny.setControlDosage(0.0);
                controlDosage = 0.0;
            }

            Double muDosage = taskScny.getMuDosage();
            if (muDosage == null) {
                taskScny.setMuDosage(0.0);
                muDosage = 0.0;
            }
            Double chemistryControl = taskScny.getChemistryControl();
            if (chemistryControl == null) {
                taskScny.setChemistryControl(0.0);
                chemistryControl = 0.0;
            }
            Double total = taskScny.getTotal();
            if (total == null) {
                taskScny.setTotal(0.0);
                total = 0.0;
            }



            /** 合计行数据 */

            pesticidePriceCount = pesticidePriceCount + pesticidePrice;
            orderQuantityCount = orderQuantityCount + orderQuantity;
            fundsUsedCount = fundsUsedCount + fundsUsed;
            controlDosageCount = controlDosageCount + controlDosage;
            muDosageCount = muDosageCount + muDosage;
            chemistryControlCount = chemistryControlCount + chemistryControl;
            totalCount = totalCount + total;

        }
        CTaskScny scny = new CTaskScny();
        scny.setProvince("合计");
        scny.setPesticidePrice(pesticidePriceCount);
        scny.setOrderQuantity(orderQuantityCount);
        scny.setFundsUsed(fundsUsedCount);
        scny.setControlDosage(controlDosageCount);
        scny.setMuDosage(muDosageCount);
        scny.setChemistryControl(chemistryControlCount);
        scny.setTotal(totalCount);
        scny.setCode(cTaskScny.getCode());
        list.add(scny);
        return list;
    }

    /**
     * 导出草原鼠虫害防治农药使用况列表
     */
    @RequiresPermissions("zaihai:scny:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskScny cTaskScny) {
        List<CTaskScny> list = cTaskScnyService.selectCTaskScnyList(cTaskScny);
        ExcelUtil<CTaskScny> util = new ExcelUtil<CTaskScny>(CTaskScny.class);
        return util.exportExcel(list, "scny");
    }

    /**
     * 新增草原鼠虫害防治农药使用况
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("zaihai:scny:add")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskScny cTaskScny) {
        return toAjax(cTaskScnyService.insertCTaskScny(cTaskScny));
    }

    /**
     * 修改草原鼠虫害防治农药使用况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CTaskScny cTaskScny = cTaskScnyService.selectCTaskScnyById(id);
        mmap.put("cTaskScny", cTaskScny);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("zaihai:scny:edit")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskScny cTaskScny) {
        return toAjax(cTaskScnyService.updateCTaskScny(cTaskScny));
    }

    /**
     * 删除草原鼠虫害防治农药使用况
     */
    @RequiresPermissions("zaihai:scny:remove")
    @Log(title = "草原鼠虫害防治农药使用况", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cTaskScnyService.deleteCTaskScnyByIds(ids));
    }


    /**
     * 跳转虫害发生情况报表图
     */
    @GetMapping(value = "/Echars")
    public String echarts( ModelMap mmap) {
        /*获取用户角色*/
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            long roleName = sysRole.getRoleId();
            //国家级角色编码为6省级为3
            if(roleName==6){
                mmap.put("grand", "6");
            }else if(roleName==3){
                mmap.put("grand", "3");
            }else if(roleName==4){
                mmap.put("grand", "4");
            }
        }
        return prefix + "/Echars";
    }


    /**
     * @description 获取 地区上报统计折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOption")
    @ResponseBody
    public List<CTaskScny> getOption(CTaskScny cTaskScny) {

        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        if(deptName.equals("内蒙古自治区")){
            deptName = deptName.substring(0, deptName.length() - 3);

        }else if(deptName.equals("西藏自治区")){
            deptName = deptName.substring(0, deptName.length() - 3);

        }else if(deptName.equals("宁夏回族自治区")){
            deptName = deptName.substring(0, deptName.length() - 5);

        }else if(deptName.equals("新疆维吾尔自治区")){
            deptName = deptName.substring(0, deptName.length() - 6);
        }else if(deptName.equals("广西壮族自治区")){
            deptName = deptName.substring(0, deptName.length() - 5);
        }else{
            //截取去除最后一个字符串
            deptName = deptName.substring(0, deptName.length() - 1);
        }
        /*获取用户确认权限*/
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
                cTaskScny.setProvince(deptName);
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyAreaList(cTaskScny);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskScny.setCity(deptName);
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyAreaList(cTaskScny);
                return list;

            }else if(roleName.equals("县级")){
                cTaskScny.setCounty(deptName);
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyAreaList(cTaskScny);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyAreaList(cTaskScny);
                return list;

            }
        }

        return null;
    }



    /**
     * @description 获取 地区上报统计柱状图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOptionHistogram")
    @ResponseBody
    public List<CTaskScny> getOptionHistogram(CTaskScny cTaskScny) {


        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        if(deptName.equals("内蒙古自治区")){
            deptName = deptName.substring(0, deptName.length() - 3);

        }else if(deptName.equals("西藏自治区")){
            deptName = deptName.substring(0, deptName.length() - 3);

        }else if(deptName.equals("宁夏回族自治区")){
            deptName = deptName.substring(0, deptName.length() - 5);

        }else if(deptName.equals("新疆维吾尔自治区")){
            deptName = deptName.substring(0, deptName.length() - 6);
        }else if(deptName.equals("广西壮族自治区")){
            deptName = deptName.substring(0, deptName.length() - 5);
        }else{
            //截取去除最后一个字符串
            deptName = deptName.substring(0, deptName.length() - 1);
        }
        /*获取用户确认权限*/
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
                cTaskScny.setProvince(deptName);
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyCityList(cTaskScny);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskScny.setCity(deptName);
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyList(cTaskScny);
                return list;

            }else if(roleName.equals("县级")){
                cTaskScny.setCounty(deptName);
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyList(cTaskScny);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskScny> list = cTaskScnyService.selectCTaskScnyParentList(cTaskScny);
                return list;

            }
        }

        return null;

    }
}
