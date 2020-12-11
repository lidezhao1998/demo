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
import com.ruoyi.zaihai.caiji.domain.CTaskFznb;
import com.ruoyi.zaihai.caiji.mapper.CTaskFznbMapper;
import com.ruoyi.zaihai.caiji.service.ICTaskFznbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 发生防治年报Controller
 *
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/zaihai/fznbtj")
public class CTaskFznbCountController extends BaseController {
    private String prefix = "statistics/fznb";

    @Autowired
    private ICTaskFznbService cTaskFznbService;

    @Autowired
    private CTaskFznbMapper cTaskFznbMapper ;

    @Autowired
    private ISysDictDataService iSysDictDataService ;



    @RequiresPermissions("zaihai:fznb:view")
    @GetMapping()
    public String fznb(ModelMap mmap) {

        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            mmap.put("roleName",roleName);

        }
        return prefix + "/fznb";
    }

    /**
     * 查询发生防治年报列表
     */
    @RequiresPermissions("zaihai:fznb:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskFznb cTaskFznb) {
        startPage();
        //获取当前年份
       /* Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if(cTaskFznb.getYear()==""){
            cTaskFznb.setYear(year+"");
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
                cTaskFznb.setStatus("2");
                cTaskFznb.setProvince(deptName);
                cTaskFznb.setCode(code);
                List<CTaskFznb> list = getcTaskFznbsSum(cTaskFznb);
                return getDataTable(list);
            }else if(roleName.equals("市级")){
                cTaskFznb.setStatus("3");
                cTaskFznb.setCity(deptName);
                cTaskFznb.setCode(code);
                List<CTaskFznb> list = getcTaskFznbsSum(cTaskFznb);
                return getDataTable(list);
            }else if(roleName.equals("区级")){
                cTaskFznb.setStatus("4");
                cTaskFznb.setCounty(deptName);
                List<CTaskFznb> list = getcTaskFznbsSum(cTaskFznb);
                return getDataTable(list);
            }else if(roleName.equals("国家级") || roleName.equals("管理员")){
                cTaskFznb.setStatus("1");
                List<CTaskFznb> list = getcTaskFznbsSum(cTaskFznb);
                return getDataTable(list);
            }
        }
        List<CTaskFznb> list = getcTaskFznbsSum(cTaskFznb);
        getcTaskFznbsSum(cTaskFznb);
        return getDataTable(list);
    }

    /**
     *省级列表详情展开区县级列表信息
     */
    @RequestMapping("/detailList/{province}")
    public String detailList(@PathVariable("province") String province, ModelMap modelMap)
    {
        startPage();
        List<CTaskFznb> list = cTaskFznbMapper.selectCTaskFznbAreaListByProvince(province);
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
        return prefix + "/fznbAreaList";
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
        return prefix + "/fznbAreaList";
    }

    /**
     *市级列表详情展开区县级列表信息
     */
    @RequiresPermissions("zaihai:sczj:list")
    @PostMapping("/list2")
    @ResponseBody
    public TableDataInfo list2(CTaskFznb cTaskFznb,String province,String city) {
        if(province ==null){
        }else{
            cTaskFznb.setProvince(province);
        }if(city ==null){
        }else{
            cTaskFznb.setCity(city);

        }
        List<CTaskFznb> list= cTaskFznbMapper.selectCTaskFznbCountyList(cTaskFznb);

        return getDataTable(list);
    }

    /**
     * 查询草原虫害发生与防治情况二三级列表
     */
    @RequiresPermissions("zaihai:fznb:list")
    @PostMapping("/ParentList")
    @ResponseBody
    public TableDataInfo ParentList(CTaskFznb cTaskFznb) {
        startPage();
        // 查询条件过滤
        if (StringUtils.isNotEmpty(cTaskFznb.getProvince())) {
            //省级查看市级
            List<CTaskFznb> list = cTaskFznbMapper.selectCTaskFznbCityList(cTaskFznb);
            return getDataTable(list);
        } else if (StringUtils.isNotEmpty(cTaskFznb.getCity())) {
            //市级查看县级=
            List<CTaskFznb> list = cTaskFznbMapper.selectCTaskFznbList(cTaskFznb);
            return getDataTable(list);
        } else {
            List<CTaskFznb> list = getcTaskFznbsSum(cTaskFznb);
            getcTaskFznbsSum(cTaskFznb);
            return getDataTable(list);
        }

    }

    /**
     * 抽取sum方法将各省份数值进行合计
     */
    private List<CTaskFznb> getcTaskFznbsSum(CTaskFznb cTaskFznb) {
        //List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbParentList(cTaskFznb);
        String province = cTaskFznb.getProvince();
        if(province != null && province !=""){
            if(province.equals("内蒙古自治区")){
                cTaskFznb.setProvince(province.substring(0,province.length()-3));
            }else if(province.equals("西藏自治区")){
                cTaskFznb.setProvince(province.substring(0,province.length()-3));
            }else if(province.equals("宁夏回族自治区")){
                cTaskFznb.setProvince(province.substring(0,province.length()-5));
            }else if(province.equals("广西壮族自治区")){
                cTaskFznb.setProvince(province.substring(0,province.length()-5));
            }else if(province.equals("新疆维吾尔自治区")){
                cTaskFznb.setProvince(province.substring(0,province.length()-6));
            }else{
                cTaskFznb.setProvince(province.substring(0,province.length()-1));
            }
        }
        List<CTaskFznb> list = new ArrayList<CTaskFznb>();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();

            /*省级用户进入统计页面*/
            if(cTaskFznb.getStatus().equals("2")){
                list = cTaskFznbMapper.selectCTaskFznbCityList(cTaskFznb);
                for (int j = 0; j < list.size(); j++) {
                    CTaskFznb taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    taskScny.setCode(cTaskFznb.getCode());
                    cTaskFznbMapper.updateCTaskSczjRoleName(taskScny);
                }
                /*市级用户进入统计页面*/
            }else if(cTaskFznb.getStatus().equals("3")){
                list = cTaskFznbMapper.selectCTaskFznbCityList(cTaskFznb);
                for (int j = 0; j < list.size(); j++) {
                    CTaskFznb taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    taskScny.setCode(cTaskFznb.getCode());
                    cTaskFznbMapper.updateCTaskSczjRoleName(taskScny);
                }
                /*县级用户进入统计页面*/

            }else if(cTaskFznb.getStatus().equals("4")){
                list = cTaskFznbMapper.selectCTaskFznbAreaList(cTaskFznb);
                for (int j = 0; j < list.size(); j++) {
                    CTaskFznb taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    cTaskFznbMapper.updateCTaskSczjRoleName(taskScny);
                }
            }
            /*国家级admin进入统计页面*/
            else{
                list = cTaskFznbService.selectCTaskFznbParentList(cTaskFznb);
                for (int j = 0; j< list.size(); j++) {
                    CTaskFznb taskScny =  list.get(j);
                    taskScny.setRoleName(roleName);
                    cTaskFznbMapper.updateCTaskSczjRoleName(taskScny);
                }
            }
        }

        /*定义累计变量*/
        Double ratharmCenterCount = 0.0;
        Double ratharmProvinceCount = 0.0;
        Double ratharmCityCount = 0.0;
        Double ratharmCountyCount = 0.0;
        Double insectharmCenterCount = 0.0;
        Double insectharmProvinceCount = 0.0;
        Double insectharmCityCount = 0.0;
        Double insectharmCountyCount = 0.0;
        Double economicLossCount = 0.0;
        Double livestockDeathCount = 0.0;
        Double hazardAreaCount = 0.0;
        Double hazardAllAreaCount = 0.0;
        Double controlDosageCount = 0.0;
        Double muDosageCount = 0.0;



        for (int i = 0; i < list.size(); i++) {
            CTaskFznb taskFznb = list.get(i);
            Double ratharmCenter = taskFznb.getRatharmCenter();

            if (ratharmCenter == null) {
                taskFznb.setRatharmCenter(0.0);
                ratharmCenter = 0.0;
            }

            Double ratharmProvince = taskFznb.getRatharmProvince();
            if (ratharmProvince == null) {
                taskFznb.setRatharmProvince(0.0);
                ratharmProvince = 0.0;
            }

            Double ratharmCity = taskFznb.getRatharmCity();
            if (ratharmCity == null) {
                taskFznb.setRatharmCity(0.0);
                ratharmCity = 0.0;
            }

            Double ratharmCounty = taskFznb.getRatharmCounty();
            if (ratharmCounty == null) {
                taskFznb.setRatharmCounty(0.0);
                ratharmCounty = 0.0;
            }

            Double insectharmCenter = taskFznb.getInsectharmCenter();
            if (insectharmCenter == null) {
                taskFznb.setInsectharmCenter(0.0);
                insectharmCenter = 0.0;
            }
            Double insectharmProvince = taskFznb.getInsectharmProvince();
            if (insectharmProvince == null) {
                taskFznb.setInsectharmProvince(0.0);
                insectharmProvince = 0.0;
            }
            Double insectharmCity = taskFznb.getInsectharmCity();
            if (insectharmCity == null) {
                taskFznb.setInsectharmCity(0.0);
                insectharmCity = 0.0;
            }

            Double insectharmCounty = taskFznb.getInsectharmCounty();
            if (insectharmCounty == null) {
                taskFznb.setInsectharmCounty(0.0);
                insectharmCounty = 0.0;
            }
            Double economicLoss = taskFznb.getEconomicLoss();
            if (economicLoss == null) {
                taskFznb.setEconomicLoss(0.0);
                economicLoss = 0.0;
            }
            Double livestockDeath = taskFznb.getLivestockDeath();
            if (livestockDeath == null) {
                taskFznb.setLivestockDeath(0.0);
                livestockDeath = 0.0;
            }
            Double hazardArea = taskFznb.getHazardArea();
            if (hazardArea == null) {
                taskFznb.setHazardArea(0.0);
                hazardArea = 0.0;
            }
            Double hazardAllArea = taskFznb.getHazardAllArea();
            if (hazardAllArea == null) {
                taskFznb.setHazardAllArea(0.0);
                hazardAllArea = 0.0;
            }
            Double controlDosage = taskFznb.getControlDosage();
            if (controlDosage == null) {
                taskFznb.setControlDosage(0.0);
                controlDosage = 0.0;
            }
            Double muDosage = taskFznb.getMuDosage();
            if (muDosage == null) {
                taskFznb.setMuDosage(0.0);
                muDosage = 0.0;
            }





            /** 合计行数据 */

            ratharmCenterCount = ratharmCenterCount + ratharmCenter;
            ratharmProvinceCount = ratharmProvinceCount + ratharmProvince;
            ratharmCityCount = ratharmCityCount + ratharmCity;
            controlDosageCount = controlDosageCount + controlDosage;
            muDosageCount = muDosageCount + muDosage;
            ratharmCountyCount = ratharmCountyCount + ratharmCounty;
            insectharmCenterCount = insectharmCenterCount + insectharmCenter;
            insectharmProvinceCount = insectharmProvinceCount + insectharmProvince;
            insectharmCityCount = insectharmCityCount + insectharmCity;
            insectharmCountyCount = insectharmCountyCount + insectharmCounty;
            economicLossCount = economicLossCount + economicLoss;
            livestockDeathCount = livestockDeathCount + livestockDeath;
            hazardAreaCount = hazardAreaCount + hazardArea;
            hazardAllAreaCount = hazardAllAreaCount + hazardAllArea;




        }
        CTaskFznb fznb = new CTaskFznb();
        fznb.setProvince("合计");
        fznb.setRatharmCenter(ratharmCenterCount);

        fznb.setRatharmProvince(ratharmProvinceCount);

        fznb.setControlDosage(controlDosageCount);

        fznb.setMuDosage(muDosageCount);

        fznb.setRatharmCity(ratharmCityCount);

        fznb.setRatharmCounty(ratharmCountyCount);

        fznb.setInsectharmCenter(insectharmCenterCount);

        fznb.setInsectharmProvince(insectharmProvinceCount);

        fznb.setInsectharmCity(insectharmCityCount);

        fznb.setInsectharmCounty(insectharmCountyCount);

        fznb.setEconomicLoss(economicLossCount);
        fznb.setLivestockDeath(livestockDeathCount);
        fznb.setHazardArea(hazardAreaCount);
        fznb.setHazardAllArea(hazardAllAreaCount);
        fznb.setCode(cTaskFznb.getCode());
        list.add(fznb);
        return list;
    }


    /**
     * 导出发生防治年报列表
     */
    @RequiresPermissions("zaihai:fznb:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskFznb cTaskFznb) {
        List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbList(cTaskFznb);
        ExcelUtil<CTaskFznb> util = new ExcelUtil<CTaskFznb>(CTaskFznb.class);
        return util.exportExcel(list, "fznb");
    }

    /**
     * 新增发生防治年报
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存发生防治年报
     */
    @RequiresPermissions("zaihai:fznb:add")
    @Log(title = "发生防治年报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskFznb cTaskFznb) {
        return toAjax(cTaskFznbService.insertCTaskFznb(cTaskFznb));
    }

    /**
     * 修改发生防治年报
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CTaskFznb cTaskFznb = cTaskFznbService.selectCTaskFznbById(id);
        mmap.put("cTaskFznb", cTaskFznb);
        return prefix + "/edit";
    }

    /**
     * 修改保存发生防治年报
     */
    @RequiresPermissions("zaihai:fznb:edit")
    @Log(title = "发生防治年报", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskFznb cTaskFznb) {
        return toAjax(cTaskFznbService.updateCTaskFznb(cTaskFznb));
    }

    /**
     * 删除发生防治年报
     */
    @RequiresPermissions("zaihai:fznb:remove")
    @Log(title = "发生防治年报", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cTaskFznbService.deleteCTaskFznbByIds(ids));
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
    public List<CTaskFznb> getOption(CTaskFznb cTaskFznb) {

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
                cTaskFznb.setProvince(deptName);
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbList(cTaskFznb);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskFznb.setCity(deptName);
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbList(cTaskFznb);
                return list;

            }else if(roleName.equals("县级")){
                cTaskFznb.setCounty(deptName);
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbList(cTaskFznb);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbList(cTaskFznb);
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
    public List<CTaskFznb> getOptionHistogram(CTaskFznb cTaskFznb) {
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
                cTaskFznb.setProvince(deptName);
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbCityList(cTaskFznb);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskFznb.setCity(deptName);
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbCityList(cTaskFznb);
                return list;

            }else if(roleName.equals("县级")){
                cTaskFznb.setCounty(deptName);
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbCityList(cTaskFznb);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskFznb> list = cTaskFznbService.selectCTaskFznbParentList(cTaskFznb);
                return list;

            }
        }

        return null;




    }
}
