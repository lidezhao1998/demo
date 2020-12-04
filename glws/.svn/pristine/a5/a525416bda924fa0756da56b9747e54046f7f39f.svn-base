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
import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import com.ruoyi.zaihai.caiji.mapper.CTaskSczjMapper;
import com.ruoyi.zaihai.caiji.service.ICTaskSczjService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 草原鼠害发生与防治情况Controller
 *
 * @author sudongdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/zaihai/sczjtj")
public class CTaskSczjCountController extends BaseController {
    private String prefix = "statistics/sczj";

    @Autowired
    private ICTaskSczjService cTaskSczjService;

    @Autowired
    private CTaskSczjMapper cTaskSczjMapper;
    @Autowired
    private ISysDictDataService iSysDictDataService;

    @RequiresPermissions("zaihai:sczj:view")
    @GetMapping()
    public String sczj(ModelMap mmap) {

        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            mmap.put("roleName",roleName);

        }

        return prefix + "/sczj";
    }

    /**
     * 查询草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("zaihai:sczj:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskSczj cTaskSczj) {
        startPage();
        //获取当前年份
       /* Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if(cTaskSczj.getYear()==""){
            cTaskSczj.setYear(year+"");
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
               /* String addrCityLabel = iSysDictDataService.selectDictValueToLabels(cTaskSczj.getCity());
                cTaskSczj.setCity(addrCityLabel);*/
                cTaskSczj.setStatus("2");
                cTaskSczj.setCode(code);
                cTaskSczj.setProvince(deptName);
                List<CTaskSczj> list = getcTaskSczjsSum(cTaskSczj);
            }else if(roleName.equals("市级")){
                cTaskSczj.setStatus("3");
                cTaskSczj.setCity(deptName);
                cTaskSczj.setCode(code);
                List<CTaskSczj> list = getcTaskSczjsSum(cTaskSczj);

            }else if(roleName.equals("区级")){
                cTaskSczj.setStatus("4");
                cTaskSczj.setCounty(deptName);
                List<CTaskSczj> list = getcTaskSczjsSum(cTaskSczj);

            }else if(roleName.equals("国家级") || roleName.equals("管理员")){
                cTaskSczj.setStatus("1");
                cTaskSczj.setCounty(deptName);
                List<CTaskSczj> list = getcTaskSczjsSum(cTaskSczj);

            }
        }
        List<CTaskSczj> list = getcTaskSczjsSum(cTaskSczj);
        getcTaskSczjsSum(cTaskSczj);
        return getDataTable(list);
    }

    /**
     *省级列表详情展开区县级列表信息
     */
   /* @GetMapping("/detailList/{province}")*/
    @RequestMapping("/detailList/{province}")
    public String detailList(@PathVariable("province") String province, ModelMap modelMap)
    {
        startPage();
        List<CTaskSczj> list = cTaskSczjMapper.selectCTaskSczjAreaListByProvince(province);
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
        //modelMap.put("sczjAreaList", list);
        return prefix + "/sczjAreaList";
    }

    /**
     *市级列表详情展开区县级列表信息
     */
    @RequestMapping("/cityList/{city}")
    public String cityList(@PathVariable("city") String city, ModelMap modelMap)
    {
        List<CTaskSczj> list = cTaskSczjMapper.selectCTaskSczjAreaListByCity(city);
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
       // modelMap.put("sczjAreaList", list);
        return prefix + "/sczjAreaList";
    }
    /**
     *市级列表详情展开区县级列表信息
     */
    @RequiresPermissions("zaihai:sczj:list")
    @PostMapping("/list2")
    @ResponseBody
    public TableDataInfo list2(CTaskSczj cTaskSczj,String province,String city) {
        if(province==null){
        }else{
            cTaskSczj.setProvince(province);
        }if(city==null){
        }else{
            cTaskSczj.setCity(city);

        }
        List<CTaskSczj> list= cTaskSczjMapper.selectCTaskSczjCityList(cTaskSczj);

        return getDataTable(list);
    }


    /**
     * 查询草原虫害发生与防治情况二三级列表
     */
    @RequiresPermissions("zaihai:sczj:list")
    @PostMapping("/ParentList")
    @ResponseBody
    public TableDataInfo ParentList(CTaskSczj cTaskSczj) {
        startPage();
        // 查询条件过滤
        if (StringUtils.isNotEmpty(cTaskSczj.getProvince())) {
            //省级查看市级
            List<CTaskSczj> list = cTaskSczjMapper.selectCTaskSczjCityList(cTaskSczj);
            return getDataTable(list);
        } else if (StringUtils.isNotEmpty(cTaskSczj.getCity())) {
            //市级查看县级
            List<CTaskSczj> list = cTaskSczjMapper.selectCTaskSczjRatcounty(cTaskSczj);
            return getDataTable(list);
        } else {
            List<CTaskSczj> list = getcTaskSczjsSum(cTaskSczj);
            getcTaskSczjsSum(cTaskSczj);
            return getDataTable(list);

        }

    }
    /**
     * 抽取sum方法将各省份数值进行合计
     */
    private List<CTaskSczj> getcTaskSczjsSum(CTaskSczj cTaskSczj) {
        List<CTaskSczj> list = new ArrayList<CTaskSczj>();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();

            /*省级用户进入统计页面*/
            if(cTaskSczj.getStatus().equals("2")){
                list = cTaskSczjMapper.selectCTaskSczjRatcity(cTaskSczj);
                for (int j = 0; j < list.size(); j++) {
                    CTaskSczj taskSczj =  list.get(j);
                    taskSczj.setRoleName(roleName);
                    taskSczj.setCode(cTaskSczj.getCode());
                    cTaskSczjMapper.updateCTaskSczjRoleName(taskSczj);

                }

                /*市级用户进入统计页面*/

            }else if(cTaskSczj.getStatus().equals("3")){
                list = cTaskSczjMapper.selectCTaskSczjCityList(cTaskSczj);
                for (int j = 0; j < list.size(); j++) {
                    CTaskSczj taskSczj =  list.get(j);
                    taskSczj.setRoleName(roleName);
                    taskSczj.setCode(cTaskSczj.getCode());
                    cTaskSczjMapper.updateCTaskSczjRoleName(taskSczj);

                }
                /*县级用户进入统计页面*/

            }else if(cTaskSczj.getStatus().equals("4")){
                list = cTaskSczjMapper.selectCTaskSczjAreaList(cTaskSczj);
                for (int j = 0; j < list.size(); j++) {
                    CTaskSczj taskSczj =  list.get(j);
                    taskSczj.setRoleName(roleName);
                    cTaskSczjMapper.updateCTaskSczjRoleName(taskSczj);
                }
            }else{
            /*国家级admin进入统计页面*/
                list = cTaskSczjService.selectCTaskSczjParentList(cTaskSczj);
                for (int j = 0; j< list.size(); j++) {
                    CTaskSczj taskSczj =  list.get(j);
                    taskSczj.setRoleName(roleName);
                    cTaskSczjMapper.updateCTaskSczjRoleName(taskSczj);
                }
            }
        }
        Double harmTotalareaCount = 0.0;
        Double engineeringhazardCount = 0.0;
        Double seriousAreaCount = 0.0;
        Double engineeringSeriousAreaCount = 0.0;
        Double chemistryControlCount = 0.0;
        Double cBotulinumtoxinCount = 0.0;
        Double dBotulinumtoxinCount = 0.0;
        Double tripterygiumCount = 0.0;
        Double curcumaeCount = 0.0;
        Double hawkMouseCount = 0.0;
        Double foxRatCount = 0.0;
        Double physicalControlCount = 0.0;
        Double defensiveAreaCount = 0.0;
        Double controlareaCount = 0.0;
        Double hawkDeratizationCount = 0.0;
        Double rodentControlCount = 0.0;
        Double artisanCount = 0.0;
        Double workCount = 0.0;
        Double planeCount = 0.0;
        Double controlApparatusCount = 0.0;
        Double carCount = 0.0;


        for (int i = 0; i < list.size(); i++) {
            CTaskSczj taskSczj = list.get(i);
            Double harmTotalarea = taskSczj.getHarmTotalarea();

            if (harmTotalarea == null) {
                taskSczj.setHarmTotalarea(0.0);
                harmTotalarea = 0.0;
            }

            Double engineeringhazard = taskSczj.getEngineeringhazard();
            if (engineeringhazard == null) {
                taskSczj.setEngineeringhazard(0.0);
                engineeringhazard = 0.0;
            }

            Double seriousArea = taskSczj.getSeriousArea();
            if (seriousArea == null) {
                taskSczj.setSeriousArea(0.0);
                seriousArea = 0.0;
            }

            Double engineeringSeriousArea = taskSczj.getEngineeringSeriousArea();
            if (engineeringSeriousArea == null) {
                taskSczj.setEngineeringSeriousArea(0.0);
                engineeringSeriousArea = 0.0;
            }

            Double cBotulinumtoxin = taskSczj.getCBotulinumtoxin();
            if (cBotulinumtoxin == null) {
                taskSczj.setCBotulinumtoxin(0.0);
                cBotulinumtoxin = 0.0;
            }

            Double dBotulinumtoxin = taskSczj.getDBotulinumtoxin();
            if (dBotulinumtoxin == null) {
                taskSczj.setDBotulinumtoxin(0.0);
                dBotulinumtoxin = 0.0;
            }

            Double tripterygium = taskSczj.getTripterygium();
            if (tripterygium == null) {
                taskSczj.setTripterygium(0.0);
                tripterygium = 0.0;
            }

            Double curcumae = taskSczj.getCurcumae();
            if (curcumae == null) {
                taskSczj.setCurcumae(0.0);
                curcumae = 0.0;
            }

            Double hawkMouse = taskSczj.getHawkMouse();
            if (hawkMouse == null) {
                taskSczj.setHawkMouse(0.0);
                hawkMouse = 0.0;
            }

            Double foxRat = taskSczj.getFoxRat();
            if (foxRat == null) {
                taskSczj.setFoxRat(0.0);
                foxRat = 0.0;
            }

            Double physicalControl = taskSczj.getPhysicalControl();
            if (physicalControl == null) {
                taskSczj.setPhysicalControl(0.0);
                physicalControl = 0.0;
            }

            Double defensiveArea = taskSczj.getDefensiveArea();
            if (defensiveArea == null) {
                taskSczj.setDefensiveArea(0.0);
                defensiveArea = 0.0;
            }

            Double controlarea = taskSczj.getControlarea();
            if (controlarea == null) {
                taskSczj.setControlarea(0.0);
                controlarea = 0.0;
            }

            Double hawkDeratization = taskSczj.getHawkDeratization();
            if (hawkDeratization == null) {
                taskSczj.setHawkDeratization(0.0);
                hawkDeratization = 0.0;
            }

            Double rodentControl = taskSczj.getRodentControl();
            if (rodentControl == null) {
                taskSczj.setRodentControl(0.0);
                rodentControl = 0.0;
            }

            Double artisan = taskSczj.getArtisan();
            if (artisan == null) {
                taskSczj.setHarmTotalarea(0.0);
                harmTotalarea = 0.0;
            }

            Double work = taskSczj.getWork();
            if (work == null) {
                taskSczj.setWork(0.0);
                work = 0.0;
            }

            Double plane = taskSczj.getPlane();
            if (plane == null) {
                taskSczj.setPlane(0.0);
                plane = 0.0;
            }

            Double controlApparatus = taskSczj.getControlApparatus();
            if (controlApparatus == null) {
                taskSczj.setControlApparatus(0.0);
                controlApparatus = 0.0;
            }

            Double car = taskSczj.getCar();
            if (car == null) {
                taskSczj.setCar(0.0);
                car = 0.0;
            }

            Double chemistryControl = taskSczj.getChemistryControl();
            if (chemistryControl == null) {
                taskSczj.setChemistryControl(0.0);
                chemistryControl = 0.0;
            }

            /** 合计行数据 */

            harmTotalareaCount = harmTotalareaCount + harmTotalarea;
            chemistryControlCount = chemistryControlCount + chemistryControl;
            engineeringhazardCount = engineeringhazardCount + engineeringhazard;
            seriousAreaCount = seriousAreaCount + seriousArea;
            engineeringSeriousAreaCount = engineeringSeriousAreaCount + engineeringSeriousArea;
            cBotulinumtoxinCount = cBotulinumtoxinCount + cBotulinumtoxin;
            dBotulinumtoxinCount = dBotulinumtoxinCount + dBotulinumtoxin;
            tripterygiumCount = tripterygiumCount + tripterygium;
            curcumaeCount = curcumaeCount + curcumae;
            hawkMouseCount = hawkMouseCount + hawkMouse;
            foxRatCount = foxRatCount + foxRat;
            physicalControlCount = physicalControlCount + physicalControl;
            defensiveAreaCount = defensiveAreaCount + defensiveArea;
            controlareaCount = controlareaCount + controlarea;
            hawkDeratizationCount = hawkDeratizationCount + hawkDeratization;
            rodentControlCount = rodentControlCount + rodentControl;
            artisanCount = artisanCount + artisan;
            workCount = workCount + work;
            planeCount = planeCount + plane;
            controlApparatusCount = controlApparatusCount + controlApparatus;
            carCount = carCount + car;


        }
        CTaskSczj chfz = new CTaskSczj();
        chfz.setProvince("合计");
        chfz.setHarmTotalarea(harmTotalareaCount);
        chfz.setEngineeringhazard(engineeringhazardCount);
        chfz.setSeriousArea(seriousAreaCount);
        chfz.setEngineeringSeriousArea(engineeringSeriousAreaCount);
        chfz.setCBotulinumtoxin(cBotulinumtoxinCount);
        chfz.setDBotulinumtoxin(dBotulinumtoxinCount);
        chfz.setTripterygium(tripterygiumCount);
        chfz.setCurcumae(curcumaeCount);
        chfz.setHawkMouse(hawkMouseCount);
        chfz.setRodentControl(rodentControlCount);
        chfz.setPhysicalControl(physicalControlCount);
        chfz.setDefensiveArea(defensiveAreaCount);
        chfz.setControlarea(controlareaCount);
        chfz.setHawkDeratization(hawkDeratizationCount);
        chfz.setArtisan(artisanCount);
        chfz.setWork(workCount);
        chfz.setPlane(planeCount);
        chfz.setControlApparatus(controlApparatusCount);
        chfz.setCar(carCount);
        chfz.setChemistryControl(chemistryControlCount);
        chfz.setChemistryControl(chemistryControlCount);
        chfz.setFoxRat(foxRatCount);
        chfz.setCode(cTaskSczj.getCode());
        list.add(chfz);
        return list;
    }

    /**
     * 导出草原鼠害发生与防治情况列表
     */
    @RequiresPermissions("zaihai:sczj:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskSczj cTaskSczj) {
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
        ExcelUtil<CTaskSczj> util = new ExcelUtil<CTaskSczj>(CTaskSczj.class);
        return util.exportExcel(list, "sczj");
    }

    /**
     * 新增草原鼠害发生与防治情况
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("zaihai:sczj:add")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskSczj cTaskSczj) {
        return toAjax(cTaskSczjService.insertCTaskSczj(cTaskSczj));
    }

    /**
     * 修改草原鼠害发生与防治情况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CTaskSczj cTaskSczj = cTaskSczjService.selectCTaskSczjById(id);
        mmap.put("cTaskSczj", cTaskSczj);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原鼠害发生与防治情况
     */
    @RequiresPermissions("zaihai:sczj:edit")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskSczj cTaskSczj) {
        return toAjax(cTaskSczjService.updateCTaskSczj(cTaskSczj));
    }

    /**
     * 删除草原鼠害发生与防治情况
     */
    @RequiresPermissions("zaihai:sczj:remove")
    @Log(title = "草原鼠害发生与防治情况", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cTaskSczjService.deleteCTaskSczjByIds(ids));
    }


    /**
     * 跳转鼠害发生情况报表图
     */
    @GetMapping(value = "/Echars")
    public String echarts(CTaskSczj cTaskSczj, ModelMap mmap) {

        //定义每周更新的数据合的变量
        double harmTotalareaWeek = 0.0;
        double engineeringhazardWeek = 0.0;
        double seriousAreaWeek = 0.0;
        double engineeringSeriousAreaWeek = 0.0;
        double defensiveAreaWeek = 0.0;
        double controlareaWeek = 0.0;
        //获取·到每条数据
        List<CTaskSczj> Originallist = cTaskSczjService.selectCTaskSczjList(cTaskSczj);

        //根据数据库查询最大的id，
        long maxId = cTaskSczjService.selectCTaskSczjMaxId(cTaskSczj);
        //获取最新数据
        CTaskSczj NewCTaskSczj = cTaskSczjService.selectCTaskSczjById(maxId);
        //获取到最新的周
        String week = NewCTaskSczj.getWeek();

        for (int i = 0; i < Originallist.size(); i++) {
            CTaskSczj taskSczj = Originallist.get(i);
            String wk = taskSczj.getWeek();
            if (week.equals(wk)) {
                double a = taskSczj.getHarmTotalarea();
                double b = taskSczj.getEngineeringhazard();
                double c = taskSczj.getEngineeringSeriousArea();
                double d = taskSczj.getSeriousArea();
                /*当年防治面积*/
                double e = taskSczj.getChemistryControl();
                double f = taskSczj.getcBotulinumtoxin();
                double g = taskSczj.getdBotulinumtoxin();
                double h = taskSczj.getTripterygium();
                double m = taskSczj.getCurcumae();
                double n = taskSczj.getHawkMouse();
                double o = taskSczj.getFoxRat();
                double fz = e + f + g + h + m + n + o;
                /*持续控制面积*/
                double p = taskSczj.getHawkDeratization();
                double q = taskSczj.getRodentControl();
                double cx = p + q;

                harmTotalareaWeek += a;
                engineeringhazardWeek += b;
                seriousAreaWeek += c;
                engineeringSeriousAreaWeek += d;
                defensiveAreaWeek += fz;
                controlareaWeek += cx;
            }
        }
        //创建存储数据总和的对象
        CTaskSczj cTaskSczWeek = new CTaskSczj();
        cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
        cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
        cTaskSczWeek.setSeriousArea(seriousAreaWeek);
        cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
        cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
        mmap.put("cTaskSczWeek", cTaskSczWeek);


        //根据数据库查询危害面积情况
        List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList(cTaskSczj);

        //定义统计数据合的变量
        double harmTotalarea = 0.0;
        double engineeringhazard = 0.0;
        double seriousArea = 0.0;
        double engineeringSeriousArea = 0.0;
        double defensiveArea = 0.0;
        double controlarea = 0.0;
        for (int i = 0; i < list.size(); i++) {
            CTaskSczj taskSczj = list.get(i);
            /** 危害总面积 */
            double a = taskSczj.getHarmTotalarea();
            /** 工程区危害面积 */
            double b = taskSczj.getEngineeringhazard();
            /** 严重危害总面积 */
            double c = taskSczj.getSeriousArea();
            /** 工程区严重危害面积 */
            double d = taskSczj.getEngineeringSeriousArea();
            /** 工程区严重危害面积 */
            double e = taskSczj.getDefensiveArea();
            /** 工程区严重危害面积 */
            double f = taskSczj.getControlarea();
            harmTotalarea += a;
            engineeringhazard += b;
            seriousArea += c;
            engineeringSeriousArea += d;
            defensiveArea += e;
            controlarea += f;
        }
        //创建存储数据总和的对象
        CTaskSczj cTaskSczjOb = new CTaskSczj();
        cTaskSczjOb.setHarmTotalarea(harmTotalarea);
        cTaskSczjOb.setEngineeringSeriousArea(engineeringSeriousArea);
        cTaskSczjOb.setSeriousArea(seriousArea);
        cTaskSczjOb.setEngineeringhazard(engineeringhazard);
        cTaskSczjOb.setDefensiveArea(defensiveArea);
        cTaskSczjOb.setControlarea(controlarea);

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
        mmap.put("cTaskSczj", cTaskSczjOb);
        //省份数据展示列表
        return prefix + "/Echars";
    }

    /**
     * @description 获取 地区危害情况地图统计 列表
     * @author
     * @date 2030/06/20 21:37
     */
    @ResponseBody
    @PostMapping("/getRequestCountSczj")
    public Map<String, Object> getRequestCount(HttpServletRequest request) {
        Map<String, Object> retMap = new HashMap<>();
        List<Map<String, String>> dataMap = new ArrayList<>();

        try {
            //危害总面积
            HashMap<String, String> objectObjectHashMap = new HashMap<>();

            dataMap.add(objectObjectHashMap);
            retMap.put("success", true);
            //危害总面积
            retMap.put("dataList", cTaskSczjService.getRequestCount());
            //工程区危害面积
            retMap.put("engineeringhazardList", cTaskSczjService.getEngineeringhazardCount());
            //严重危害总面积
            retMap.put("seriousAreaList", cTaskSczjService.getSeriousAreaCount());
            //工程区严重危害面积
            retMap.put("engineeringSeriousAreaList", cTaskSczjService.getEngineeringSeriousAreaCount());
            //当年防治面积
            retMap.put("defensiveArea", cTaskSczjService.getDefensiveAreaCount());
            //持续控制面积
            retMap.put("controlarea", cTaskSczjService.getControlareaCount());


        } catch (Exception e) {
            retMap.put("success", false);
            e.printStackTrace();
        }

        return retMap;
    }


    /**
     * @description 获取 地区危害情况地图统计 列表
     * @author
     * @date 2030/06/20 21:37
     */
    @ResponseBody
    @PostMapping("/getCityCount")
    public Map<String, Object> getCityCount(HttpServletRequest request) {
        Map<String, Object> retMap = new HashMap<>();
        List<Map<String, String>> dataMap = new ArrayList<>();

        try {
            //危害总面积
            HashMap<String, String> objectObjectHashMap = new HashMap<>();

            dataMap.add(objectObjectHashMap);
            retMap.put("success", true);
            //危害总面积
            retMap.put("dataList", cTaskSczjService.getCityCount());
            //工程区危害面积
            retMap.put("engineeringhazardList", cTaskSczjService.getEngineeringhazardCityCount());
            //严重危害总面积
            retMap.put("seriousAreaList", cTaskSczjService.getSeriousAreaCityCount());
            //工程区严重危害面积
            retMap.put("engineeringSeriousAreaList", cTaskSczjService.getEngineeringSeriousAreaCityCount());
            //当年防治面积
            retMap.put("defensiveArea", cTaskSczjService.getDefensiveAreaCityCount());
            //持续控制面积
            retMap.put("controlarea", cTaskSczjService.getControlareaCityCount());

        } catch (Exception e) {
            retMap.put("success", false);
            e.printStackTrace();
        }

        return retMap;
    }



    /**
     * @description 获取 地区危害情况地图统计 列表
     * @author
     * @date 2030/06/20 21:37
     */
    @ResponseBody
    @PostMapping("/getCityInsectCount")
    public Map<String, Object> getCityInsectCount(HttpServletRequest request) {
        Map<String, Object> retMap = new HashMap<>();
        List<Map<String, String>> dataMap = new ArrayList<>();

        try {
            //危害总面积
            HashMap<String, String> objectObjectHashMap = new HashMap<>();

            dataMap.add(objectObjectHashMap);
            retMap.put("success", true);
            //危害总面积
            retMap.put("dataList", cTaskSczjService.getCityCount());
            //工程区危害面积
            retMap.put("engineeringhazardList", cTaskSczjService.getEngineeringhazardCityCount());


        } catch (Exception e) {
            retMap.put("success", false);
            e.printStackTrace();
        }

        return retMap;
    }



    /**
     * @description 获取 地区上报统计折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOption")
    @ResponseBody
    public List<CTaskSczj> getOption(CTaskSczj cTaskSczj) {
        //根据数据库查询每周情况
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjLine(cTaskSczj);
        return list;
    }


    /**
     * @description 获取 详细地区危害情况面积列表
     * @author feiyanxu
     * @date 2019/12/6 21:37
     */

    /**
     * @description 获取 详细地区危害情况面积列表
     * @author feiyanxu
     * @date 2019/12/6 21:37
     */
    @RequiresPermissions("zaihai:chfz:list")
    @PostMapping("/addressList")
    @ResponseBody
    public TableDataInfo addressList(CTaskSczj cTaskSczj) {
        startPage();
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("省级")) {
                cTaskSczj.setProvince(deptName);
                List<CTaskSczj> list = cTaskSczjMapper.selectCTaskSczjCityList(cTaskSczj);
                return getDataTable(list);

            } else if (roleName.equals("国家级")) {
                cTaskSczj.setProvince(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList(cTaskSczj);
                return getDataTable(list);

            } else {
                cTaskSczj.setCounty(deptName);
                List<CTaskSczj> list = cTaskSczjMapper.selectCTaskChfzCountyList(cTaskSczj);
                return getDataTable(list);

            }
        }


        // List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList(cTaskSczj);

        //  return getDataTable(list);
        return null;
    }
          /**
     * @description 获取 地区上报统计柱状图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOptionHistogram")
    @ResponseBody
    public List<CTaskSczj> getOptionHistogram(CTaskSczj cTaskSczj) {
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjSumList(cTaskSczj);
        return list;

    }


    /**
     * @description 获取 地区上报统计柱状图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getPieOption")
    @ResponseBody
    public List<CTaskSczj> getPieOption(CTaskSczj cTaskSczj) {
        List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjSumList(cTaskSczj);
        return list;

    }

    /**
     * @description 获取 地区上报统计首页鼠害折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getRatOption")
    @ResponseBody
    public List<CTaskSczj> getRatOption(CTaskSczj cTaskSczj) {


        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();

        /*获取用户确认权限*/
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
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
                cTaskSczj.setProvince(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLineProvince(cTaskSczj);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskSczj.setCity(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLineProvince(cTaskSczj);
                return list;


            }else if(roleName.equals("县级")){
                cTaskSczj.setCounty(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLineProvince(cTaskSczj);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLine(cTaskSczj);
                return list;

            }
        }

        return null;
    }


    /**
     * @description 首页鼠害上报统计柱状图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOptionRatHistogram")
    @ResponseBody
    public List<CTaskSczj> getOptionRatHistogram(CTaskSczj cTaskSczj, ModelMap mmap) {
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();

        /*获取用户确认权限*/
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
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
                cTaskSczj.setProvince(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatcity(cTaskSczj);
                //将层级标识符放入list中
                for (int j = 0; j < list.size(); j++) {
                    CTaskSczj taskSczj =  list.get(j);
                    taskSczj.setRoleName("2");
                }
                return list;

            }else  if(roleName.equals("市级")){
                cTaskSczj.setCity(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatcounty(cTaskSczj);
                //将层级标识符放入list中
                for (int j = 0; j < list.size(); j++) {
                    CTaskSczj taskSczj =  list.get(j);
                    taskSczj.setRoleName("3");
                }
                return list;


            }else  if(roleName.equals("县级")){
                cTaskSczj.setCounty(deptName);

                List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList2(cTaskSczj);
                return list;

            }else{
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjSumList(cTaskSczj);
                return list;
            }
        }
        return null;

    }

    /**
     * @description 获取 数据统计模块 地区上报统计鼠害省级用户折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getProvinceRatOption")
    @ResponseBody
    public List<CTaskSczj> getProvinceRatOption(CTaskSczj cTaskSczj) {


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
                //截取去除最后一个字符串
                cTaskSczj.setProvince(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatProvince(cTaskSczj);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskSczj.setCity(deptName);
                //List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLineProvince(cTaskSczj);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLineCounty(cTaskSczj);
                return list;


            }else if(roleName.equals("县级")){
                cTaskSczj.setCounty(deptName);
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLineProvince(cTaskSczj);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjRatLine(cTaskSczj);
                return list;

            }
        }

        return null;
    }


}
