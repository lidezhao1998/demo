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
import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import com.ruoyi.zaihai.caiji.mapper.CTaskChfzMapper;
import com.ruoyi.zaihai.caiji.service.ICTaskChfzService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 草原虫害发生与防治情况Controller
 *
 * @author sudonngdong
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/zaihai/chfztj")
public class CTaskChfzCountController extends BaseController {
    private String prefix = "statistics/chfz";

    @Autowired
    private ICTaskChfzService cTaskChfzService;

    @Autowired
    private CTaskChfzMapper cTaskChfzMapper;

    @Autowired
    private ISysDictDataService iSysDictDataService ;


    @RequiresPermissions("zaihai:chfz:view")
    @GetMapping()
    public String chfz(ModelMap mmap) {

        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            mmap.put("roleName",roleName);

        }
        return prefix + "/chfz";
    }


    /**
     * 查询草原虫害发生与防治情况列表
     */
    @RequiresPermissions("zaihai:chfz:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskChfz cTaskChfz) {

        //获取当前年份
       /* Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if(cTaskChfz.getYear()==""){
            cTaskChfz.setYear(year+"");
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
                cTaskChfz.setStatus("2");
                cTaskChfz.setCode(code);


                cTaskChfz.setProvince(deptName);


                List<CTaskChfz> list = getcTaskChfzsSum(cTaskChfz);
            }else if(roleName.equals("市级")){
                cTaskChfz.setStatus("3");
                cTaskChfz.setCity(deptName);
                cTaskChfz.setCode(code);
                List<CTaskChfz> list = getcTaskChfzsSum(cTaskChfz);

            }else if(roleName.equals("区级")){
                cTaskChfz.setStatus("4");
                cTaskChfz.setCounty(deptName);
                List<CTaskChfz> list = getcTaskChfzsSum(cTaskChfz);

            }else if(roleName.equals("国家级") || roleName.equals("管理员")){
                cTaskChfz.setStatus("1");
                cTaskChfz.setCounty(deptName);
                List<CTaskChfz> list = getcTaskChfzsSum(cTaskChfz);
            }
        }
        List<CTaskChfz> list = getcTaskChfzsSum(cTaskChfz);

        getcTaskChfzsSum(cTaskChfz);
        return getDataTable(list);
    }

    /**
     *省级列表详情展开区县级列表信息
     */
    @RequestMapping("/detailList/{province}")
    public String detailList(@PathVariable("province") String province, ModelMap modelMap)
    {
       // startPage();
       //List<CTaskChfz> list = cTaskChfzMapper.selectCTaskChfzAreaListByProvince(province);
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

        return prefix + "/chfzAreaList";
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

        return prefix + "/chfzAreaList";
    }
    /**
     *市级列表详情展开区县级列表信息
     */
    @RequiresPermissions("zaihai:sczj:list")
    @PostMapping("/list2")
    @ResponseBody
    public TableDataInfo list2(CTaskChfz cTaskChfz,String province,String city) {
        if(province==null){
        }else{
            cTaskChfz.setProvince(province);
        }if(city==null){
        }else{
            cTaskChfz.setCity(city);

        }
        List<CTaskChfz> list= cTaskChfzMapper.selectCTaskChfzCountyList(cTaskChfz);


        return getDataTable(list);
    }

    /**
     * 查询草原虫害发生与防治情况二三级列表
     */
    @RequiresPermissions("zaihai:chfz:list")
    @PostMapping("/ParentList")
    @ResponseBody
    public TableDataInfo ParentList(CTaskChfz cTaskChfz) {
        startPage();
        // 查询条件过滤
        if (StringUtils.isNotEmpty(cTaskChfz.getProvince())) {

            //省级查看市级
            List<CTaskChfz> list = cTaskChfzMapper.selectCTaskChfzCityList(cTaskChfz);
            return getDataTable(list);
        } else if (StringUtils.isNotEmpty(cTaskChfz.getCity())) {
            //市级查看县级不分组查看
            List<CTaskChfz> list = cTaskChfzMapper.selectCTaskChfzCountyList(cTaskChfz);
            return getDataTable(list);

        } else {
            List<CTaskChfz> list = getcTaskChfzsSum(cTaskChfz);
            return getDataTable(list);

        }

    }

    /**
     * 抽取草原虫害发生与防治情况合计
     */
    private List<CTaskChfz> getcTaskChfzsSum(CTaskChfz cTaskChfz) {
        //List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzParentList(cTaskChfz);


        List<CTaskChfz> list = new ArrayList<CTaskChfz>();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();

            /*省级用户进入统计页面*/
            if(cTaskChfz.getStatus().equals("2")){
                list = cTaskChfzMapper.selectCTaskChfzCityList(cTaskChfz);
                for (int j = 0; j < list.size(); j++) {
                    CTaskChfz taskChfz =  list.get(j);
                    taskChfz.setRoleName(roleName);
                    taskChfz.setCode(cTaskChfz.getCode());
                    cTaskChfzMapper.updateCTaskChfzRoleName(taskChfz);
                }

                /*市级用户进入统计页面*/

            }else if(cTaskChfz.getStatus().equals("3")){
                list = cTaskChfzMapper.selectCTaskChfzCityList(cTaskChfz);
                for (int j = 0; j < list.size(); j++) {
                    CTaskChfz taskChfz =  list.get(j);
                    taskChfz.setRoleName(roleName);
                    taskChfz.setCode(cTaskChfz.getCode());
                    cTaskChfzMapper.updateCTaskChfzRoleName(taskChfz);

                }
                /*县级用户进入统计页面*/

            }else if(cTaskChfz.getStatus().equals("4")){
                list = cTaskChfzMapper.selectCTaskChfzCountyList(cTaskChfz);
                for (int j = 0; j < list.size(); j++) {
                    CTaskChfz taskChfz =  list.get(j);
                    taskChfz.setRoleName(roleName);
                    cTaskChfzMapper.updateCTaskChfzRoleName(taskChfz);
                }
            }
            /*国家级admin进入统计页面*/
            else{
                list = cTaskChfzService.selectCTaskChfzParentList(cTaskChfz);
                for (int j = 0; j< list.size(); j++) {
                    CTaskChfz taskChfz =  list.get(j);
                    taskChfz.setRoleName(roleName);
                    cTaskChfzMapper.updateCTaskChfzRoleName(taskChfz);
                }
            }
        }

        /*定义求和变量*/
        Double harmTotalareaCount = 0.0;
        Double engineeringhazardCount = 0.0;
        Double seriousAreaCount = 0.0;
        Double engineeringSeriousAreaCount = 0.0;
        Double totalPreventionCount = 0.0;
        Double subtotalCount = 0.0;
        Double attractStarlingsCount = 0.0;
        Double pastureChickenCount = 0.0;
        Double pastureDuckCount = 0.0;
        Double matrineCount = 0.0;
        Double nicotineCount = 0.0;
        Double greenMuscardineFungusCount = 0.0;
        Double azadirachtinCount = 0.0;
        Double avermectinCount = 0.0;
        Double empedobacterBrevisCount = 0.0;
        Double ostholeCount = 0.0;
        Double weiSuCount = 0.0;
        Double enterocytozoonBieneusiCount = 0.0;
        Double workCount = 0.0;
        Double planeCount = 0.0;
        Double bigSprayerCount = 0.0;
        Double smallSprayerCount = 0.0;
        Double carCount = 0.0;
        Double pastureChickenNumCount = 0.0;
        Double pastureDuckNumCount = 0.0;
        Double starlingNestCount = 0.0;
        Double chemistryControlCount = 0.0;
        Double expatriateManagersCount = 0.0;

        for (int i = 0; i < list.size(); i++) {
            CTaskChfz taskChfz = list.get(i);
            Double harmTotalarea = taskChfz.getHarmTotalarea();

            if (harmTotalarea == null) {
                taskChfz.setHarmTotalarea(0.0);
                harmTotalarea = 0.0;
            }

            Double engineeringhazard = taskChfz.getEngineeringhazard();
            if (engineeringhazard == null) {
                taskChfz.setEngineeringhazard(0.0);
                engineeringhazard = 0.0;
            }

            Double seriousArea = taskChfz.getSeriousArea();
            if (seriousArea == null) {
                taskChfz.setSeriousArea(0.0);
                seriousArea = 0.0;
            }

            Double engineeringSeriousArea = taskChfz.getEngineeringSeriousArea();
            if (engineeringSeriousArea == null) {
                taskChfz.setEngineeringSeriousArea(0.0);
                engineeringSeriousArea = 0.0;
            }

            Double totalPrevention = taskChfz.getTotalPrevention();
            if (totalPrevention == null) {
                taskChfz.setTotalPrevention(0.0);
                totalPrevention = 0.0;
            }

            Double chemistryControl = taskChfz.getChemistryControl();
            if (chemistryControl == null) {
                taskChfz.setChemistryControl(0.0);
                chemistryControl = 0.0;
            }

            Double subtotal = taskChfz.getSubtotal();
            if (subtotal == null) {
                taskChfz.setSubtotal(0.0);
                subtotal = 0.0;
            }

            Double attractStarlings = taskChfz.getAttractStarlings();
            if (attractStarlings == null) {
                taskChfz.setAttractStarlings(0.0);
                attractStarlings = 0.0;
            }

            Double pastureChicken = taskChfz.getPastureChicken();
            if (pastureChicken == null) {
                taskChfz.setPastureChicken(0.0);
                pastureChicken = 0.0;
            }

            Double pastureDuck = taskChfz.getPastureDuck();
            if (pastureDuck == null) {
                taskChfz.setPastureDuck(0.0);
                pastureDuck = 0.0;
            }

            Double matrine = taskChfz.getMatrine();
            if (matrine == null) {
                taskChfz.setMatrine(0.0);
                matrine = 0.0;
            }

            Double nicotine = taskChfz.getNicotine();
            if (nicotine == null) {
                taskChfz.setNicotine(0.0);
                nicotine = 0.0;
            }

            Double greenMuscardineFungus = taskChfz.getGreenMuscardineFungus();
            if (greenMuscardineFungus == null) {
                taskChfz.setGreenMuscardineFungus(0.0);
                greenMuscardineFungus = 0.0;
            }

            Double azadirachtin = taskChfz.getAzadirachtin();
            if (azadirachtin == null) {
                taskChfz.setAzadirachtin(0.0);
                azadirachtin = 0.0;
            }

            Double avermectin = taskChfz.getAvermectin();
            if (avermectin == null) {
                taskChfz.setAvermectin(0.0);
                avermectin = 0.0;
            }

            Double empedobacterBrevis = taskChfz.getEmpedobacterBrevis();
            if (empedobacterBrevis == null) {
                taskChfz.setEmpedobacterBrevis(0.0);
                empedobacterBrevis = 0.0;
            }

            Double osthole = taskChfz.getOsthole();
            if (harmTotalarea == null) {
                taskChfz.setHarmTotalarea(0.0);
                harmTotalarea = 0.0;
            }

            Double weiSu = taskChfz.getWeiSu();
            if (weiSu == null) {
                taskChfz.setWeiSu(0.0);
                weiSu = 0.0;
            }

            Double enterocytozoonBieneusi = taskChfz.getEnterocytozoonBieneusi();
            if (enterocytozoonBieneusi == null) {
                taskChfz.setEnterocytozoonBieneusi(0.0);
                enterocytozoonBieneusi = 0.0;
            }

            Double expatriateManagers = taskChfz.getExpatriateManagers();
            if (expatriateManagers == null) {
                taskChfz.setExpatriateManagers(0.0);
                expatriateManagers = 0.0;
            }

            Double work = taskChfz.getWork();
            if (work == null) {
                taskChfz.setWork(0.0);
                work = 0.0;
            }

            Double plane = taskChfz.getPlane();
            if (plane == null) {
                taskChfz.setPlane(0.0);
                plane = 0.0;
            }

            Double bigSprayer = taskChfz.getBigSprayer();
            if (bigSprayer == null) {
                taskChfz.setBigSprayer(0.0);
                bigSprayer = 0.0;
            }

            Double smallSprayer = taskChfz.getSmallSprayer();
            if (smallSprayer == null) {
                taskChfz.setSmallSprayer(0.0);
                smallSprayer = 0.0;
            }

            Double car = taskChfz.getCar();
            if (car == null) {
                taskChfz.setCar(0.0);
                car = 0.0;
            }

            Double pastureChickenNum = taskChfz.getPastureChickenNum();
            if (pastureChickenNum == null) {
                taskChfz.setPastureChickenNum(0.0);
                pastureChickenNum = 0.0;
            }

            Double pastureDuckNum = taskChfz.getPastureDuckNum();
            if (pastureDuckNum == null) {
                taskChfz.setPastureDuckNum(0.0);
                pastureDuckNum = 0.0;
            }

            Double starlingNest = taskChfz.getStarlingNest();
            if (starlingNest == null) {
                taskChfz.setStarlingNest(0.0);
                starlingNest = 0.0;
            }


            /** 合计行数据 */

            harmTotalareaCount = harmTotalareaCount + harmTotalarea;
            /*保留两位小数*/
            BigDecimal bg = new BigDecimal(harmTotalareaCount);
            harmTotalareaCount = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            engineeringhazardCount = engineeringhazardCount + engineeringhazard;
            seriousAreaCount = seriousAreaCount + seriousArea;
            engineeringSeriousAreaCount = engineeringSeriousAreaCount + engineeringSeriousArea;
            totalPreventionCount = totalPreventionCount + totalPrevention;
            subtotalCount = subtotalCount + subtotal;
            attractStarlingsCount = attractStarlingsCount + attractStarlings;
            pastureChickenCount = pastureChickenCount + pastureChicken;
            pastureDuckCount = pastureDuckCount + pastureDuck;
            matrineCount = matrineCount + matrine;
            nicotineCount = nicotineCount + nicotine;
            greenMuscardineFungusCount = greenMuscardineFungusCount + greenMuscardineFungus;
            azadirachtinCount = azadirachtinCount + azadirachtin;
            avermectinCount = avermectinCount + avermectin;
            empedobacterBrevisCount = empedobacterBrevisCount + empedobacterBrevis;
            ostholeCount = ostholeCount + osthole;
            weiSuCount = weiSuCount + weiSu;
            enterocytozoonBieneusiCount = enterocytozoonBieneusiCount + enterocytozoonBieneusi;
            workCount = workCount + work;
            planeCount = planeCount + plane;
            bigSprayerCount = bigSprayerCount + bigSprayer;
            smallSprayerCount = smallSprayerCount + smallSprayer;
            carCount = carCount + car;
            pastureChickenNumCount = pastureChickenNumCount + pastureChickenNum;
            pastureDuckNumCount = pastureDuckNumCount + pastureDuckNum;
            starlingNestCount = starlingNestCount + starlingNest;
            chemistryControlCount = chemistryControlCount + chemistryControl;
            expatriateManagersCount = expatriateManagersCount + expatriateManagers;


        }
        /** 将合计数据，添加进set进最后一行 */
        CTaskChfz chfz = new CTaskChfz();
        chfz.setProvince("合计");
        chfz.setHarmTotalarea(harmTotalareaCount);
        chfz.setEngineeringhazard(engineeringhazardCount);
        chfz.setSeriousArea(seriousAreaCount);
        chfz.setEngineeringSeriousArea(engineeringSeriousAreaCount);
        chfz.setTotalPrevention(totalPreventionCount);
        chfz.setSubtotal(subtotalCount);
        chfz.setAttractStarlings(attractStarlingsCount);
        chfz.setPastureChicken(pastureChickenCount);
        chfz.setPastureDuck(pastureDuckCount);
        chfz.setMatrine(matrineCount);
        chfz.setNicotine(nicotineCount);
        chfz.setGreenMuscardineFungus(greenMuscardineFungusCount);
        chfz.setAzadirachtin(azadirachtinCount);
        chfz.setAvermectin(avermectinCount);
        chfz.setEmpedobacterBrevis(empedobacterBrevisCount);
        chfz.setOsthole(ostholeCount);
        chfz.setWeiSu(weiSuCount);
        chfz.setEnterocytozoonBieneusi(enterocytozoonBieneusiCount);
        chfz.setWork(workCount);
        chfz.setPlane(planeCount);
        chfz.setBigSprayer(bigSprayerCount);
        chfz.setSmallSprayer(smallSprayerCount);
        chfz.setCar(carCount);
        chfz.setPastureChickenNum(pastureChickenNumCount);
        chfz.setPastureDuckNum(pastureDuckNumCount);
        chfz.setStarlingNest(starlingNestCount);
        chfz.setChemistryControl(chemistryControlCount);
        chfz.setExpatriateManagers(expatriateManagersCount);
        chfz.setCode(cTaskChfz.getCode());
        list.add(chfz);
        return list;
    }

    /**
     * 导出草原虫害发生与防治情况列表
     */
    @RequiresPermissions("zaihai:chfz:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskChfz cTaskChfz) {
        List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);
        ExcelUtil<CTaskChfz> util = new ExcelUtil<CTaskChfz>(CTaskChfz.class);
        return util.exportExcel(list, "chfz");
    }

    /**
     * 新增草原虫害发生与防治情况
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存草原虫害发生与防治情况
     */
    @RequiresPermissions("zaihai:chfz:add")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CTaskChfz cTaskChfz) {
        return toAjax(cTaskChfzService.insertCTaskChfz(cTaskChfz));
    }

    /**
     * 修改草原虫害发生与防治情况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CTaskChfz cTaskChfz = cTaskChfzService.selectCTaskChfzById(id);
        mmap.put("cTaskChfz", cTaskChfz);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原虫害发生与防治情况
     */
    @RequiresPermissions("zaihai:chfz:edit")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CTaskChfz cTaskChfz) {
        return toAjax(cTaskChfzService.updateCTaskChfz(cTaskChfz));
    }

    /**
     * 删除草原虫害发生与防治情况
     */
    @RequiresPermissions("zaihai:chfz:remove")
    @Log(title = "草原虫害发生与防治情况", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cTaskChfzService.deleteCTaskChfzByIds(ids));
    }

    /**
     * 跳转虫害发生情况报表图
     */
    @GetMapping(value = "/Echars")
    public String echarts(CTaskChfz cTaskChfz, ModelMap mmap) {
        //根据数据库查询危害面积情况
        List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);
        double harmTotalarea = 0.0;
        double engineeringhazard = 0.0;
        double seriousArea = 0.0;
        double engineeringSeriousArea = 0.0;

        for (int i = 0; i < list.size(); i++) {
            CTaskChfz taskChfz = list.get(i);
            /** 危害总面积 */
            double a = taskChfz.getHarmTotalarea();
            /** 工程区危害面积 */
            double b = taskChfz.getEngineeringhazard();
            /** 严重危害总面积 */
            double c = taskChfz.getSeriousArea();
            /** 工程区严重危害面积 */
            double d = taskChfz.getEngineeringSeriousArea();
            harmTotalarea += a;
            engineeringhazard += b;
            seriousArea += c;
            engineeringSeriousArea += d;
        }
        //创建存储数据总和的对象
        CTaskChfz cTaskChf1 = new CTaskChfz();
        cTaskChf1.setHarmTotalarea(harmTotalarea);
        cTaskChf1.setEngineeringSeriousArea(engineeringSeriousArea);
        cTaskChf1.setSeriousArea(seriousArea);
        cTaskChf1.setEngineeringhazard(engineeringhazard);
        mmap.put("cTaskChfz", cTaskChf1);
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
        //省份数据展示列表
        return prefix + "/Echars2";
    }

    /**
     * @description 获取 地区危害情况地图统计 列表
     * @author
     * @date 2030/06/20 21:37
     */
    @ResponseBody
    @PostMapping("/getRequestCount")
    public Map<String, Object> getRequestCount(HttpServletRequest request, ModelMap mmap) {

        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if(roleName.equals("省级")){
                mmap.put("grade", "省级");
                Map<String, Object> retMap = new HashMap<>();
                List<Map<String, String>> dataMap = new ArrayList<>();

                try {
                    //危害总面积
                    HashMap<String, String> objectObjectHashMap = new HashMap<>();

                    dataMap.add(objectObjectHashMap);
                    retMap.put("success", true);
                    //危害总面积
                    retMap.put("dataList", cTaskChfzService.getRequestCount());
                    //工程区危害面积
                    retMap.put("engineeringhazardList", cTaskChfzMapper.getEngineeringhazardCount());
                    //严重危害总面积
                    retMap.put("seriousAreaList", cTaskChfzMapper.getSeriousAreaCount());
                    //工程区严重危害面积
                    retMap.put("engineeringSeriousAreaList", cTaskChfzMapper.getEngineeringSeriousAreaCount());


                } catch (Exception e) {
                    retMap.put("success", false);
                    e.printStackTrace();
                }

                return retMap;

            }else if(roleName.equals("市级")){
                mmap.put("grade", "市级");

            }else if(roleName.equals("区级")){
                mmap.put("grade", "市级");


            }else if(roleName.equals("国家级") || roleName.equals("管理员")){
                mmap.put("grade", "国家级");
                Map<String, Object> retMap = new HashMap<>();
                List<Map<String, String>> dataMap = new ArrayList<>();

                try {
                    //危害总面积
                    HashMap<String, String> objectObjectHashMap = new HashMap<>();

                    dataMap.add(objectObjectHashMap);
                    retMap.put("success", true);
                    //危害总面积
                    retMap.put("dataList", cTaskChfzService.getRequestCount());
                    //工程区危害面积
                    retMap.put("engineeringhazardList", cTaskChfzMapper.getEngineeringhazardCount());
                    //严重危害总面积
                    retMap.put("seriousAreaList", cTaskChfzMapper.getSeriousAreaCount());
                    //工程区严重危害面积
                    retMap.put("engineeringSeriousAreaList", cTaskChfzMapper.getEngineeringSeriousAreaCount());


                } catch (Exception e) {
                    retMap.put("success", false);
                    e.printStackTrace();
                }

                return retMap;

            }
        }
        return null;

    }

    /**
     * @description 获取 地区上报统计折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getOption")
    @ResponseBody
    public List<CTaskChfz> getOption(CTaskChfz cTaskChfz) {


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
                cTaskChfz.setProvince(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLine(cTaskChfz);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskChfz.setCity(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLine(cTaskChfz);
                return list;

            }else if(roleName.equals("县级")){
                cTaskChfz.setCounty(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLine(cTaskChfz);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLine(cTaskChfz);
                return list;

            }
        }

        return null;


    }



    /**
     * @description 获取 详细地区危害情况面积
     * @author feiyanxu
     * @date 2019/12/6 21:37
     */

    @RequiresPermissions("zaihai:chfz:list")
    @PostMapping("/addressList")
    @ResponseBody
    public TableDataInfo addressList(CTaskChfz cTaskChfz) {
        startPage();
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);
        return getDataTable(list);
    }



    /**
     * @description 首页鼠害折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getPageOption")
    @ResponseBody
    public List<CTaskChfz> getPageOption(CTaskChfz cTaskChfz) {

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
                cTaskChfz.setProvince(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLinePage(cTaskChfz);
                return list;

            }else  if(roleName.equals("市级")){
                cTaskChfz.setCity(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLinePage(cTaskChfz);
                return list;

            }else if(roleName.equals("县级")){
                cTaskChfz.setCounty(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLinePage(cTaskChfz);
                return list;

            }else{
                //根据数据库查询每周情况
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLinePage(cTaskChfz);
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
    public List<CTaskChfz> getOptionHistogram(CTaskChfz cTaskChfz) {

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
                cTaskChfz.setProvince(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzCityList(cTaskChfz);
                //将层级标识符放入list中
                for (int j = 0; j < list.size(); j++) {
                    CTaskChfz taskChfz =  list.get(j);
                    taskChfz.setRoleName("2");
                }
                return list;

            }else  if(roleName.equals("市级")){
                cTaskChfz.setCity(deptName);
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzCountyList(cTaskChfz);
                //将层级标识符放入list中
                for (int j = 0; j < list.size(); j++) {
                    CTaskChfz taskChfz =  list.get(j);
                    taskChfz.setRoleName("3");
                }
                return list;

            }else  if(roleName.equals("县级")){
                cTaskChfz.setCounty(deptName);

                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListColumnar(cTaskChfz);
                return list;

            }else{
                List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListColumnar(cTaskChfz);
                return list;
            }
        }
        return null;
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
            retMap.put("dataList", cTaskChfzService.getCityCount());
            //工程区危害面积
            retMap.put("engineeringhazardList", cTaskChfzService.getEngineeringhazardCityCount());

            //严重危害总面积
            retMap.put("seriousAreaList", cTaskChfzMapper.getSeriousAreaCityCount());
            //工程区严重危害面积
            retMap.put("engineeringSeriousAreaList", cTaskChfzMapper.getEngineeringSeriousAreaCityCount());

        } catch (Exception e) {
            retMap.put("success", false);
            e.printStackTrace();
        }

        return retMap;
    }


    /**
     * @description 数据统计 省级用户地区查看市级上报统计折线图
     * @author feiyanxu
     * @date
     */
    @PostMapping("/getPrvonceOption")
    @ResponseBody
    public List<CTaskChfz> getPrvonceOption(CTaskChfz cTaskChfz) {
        //根据数据库查询每周情况
        List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzListLine(cTaskChfz);
        return list;
    }


}