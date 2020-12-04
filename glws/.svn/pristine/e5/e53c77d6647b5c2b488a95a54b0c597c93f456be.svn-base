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
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskResolveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    private ITTaskPublishService tTaskPublishService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private TTaskResolveMapper tTaskResolveMapper;

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
        startPage();
        //根据字典键值查询字典标签
        if(tTaskPublish.getProvince()!=""){
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
            tTaskPublish.setProvince(provinceLabel);
        }

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
        for (int i = 0; i < list.size(); i++) {
            TTaskPublish Publish = list.get(i);

            /** 拼接任务地区 */
            String province=Publish.getProvince();
            String year=Publish.getYear();

            if(Publish.getAddress()==null){
                String dz=year+"_"+province;
                Publish.setDz(dz);
            }else{
                String address= Publish.getAddress();
                String dz=year+"_"+province+""+address;
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
            Publish.setWlmjCount(wlmiCount);


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
            Publish.setWljeCount(wljeCount);


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
            Double ykcyzlSize = Publish.getYkcyzlSize();
            if (ykcyzlSize == null) {
                ykcyzlSize = 0.0;
            }
            Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize + ykcyzlSize;
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
            Double ykcazlMoney = Publish.getYkcazlMoney();
            if (ykcazlMoney == null) {
                ykcazlMoney = 0.0;
            }
            Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney + ykcazlMoney;
            Publish.setQtjeCount(qtjeCount);

            /** 总规模合计 */
            Double zgmCount = Publish.getQtmjCount() + Publish.getWlmjCount();
            Publish.setZgmCount(zgmCount);


            /** 总金额 */
            Double zjeCount = qtjeCount + wljeCount;
            Publish.setZjeCount(zjeCount);

                  /*  BigDecimal qtjeCount = new BigDecimal(Double.toString(zjeCount));
                    Publish.setZjeCount(zjeCount);
                    qtjeCount.add(wljeCount).doubleValue();*/
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
     * 新增退牧还草工程任务领取分解
     *
     * @GetMapping("/add") public String add()
     * {
     * return prefix + "/add";
     * }
     * <p>
     * /**
     * 新增保存退牧还草工程任务领取分解
     */
    @RequiresPermissions("system:resolve:add")
    @Log(title = "退牧还草工程任务领取分解", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TTaskResolve tTaskResolve, String publishId) {

        //查询省份
        String province = tTaskResolveService.selectDictValue(tTaskResolve.getAddrCity());
        tTaskResolve.setProvince(province);
        String city= tTaskResolve.getProvince();
        //截取字符串判断从市级开始分解
        String cityString=city.substring(city.length()-1);


        //判断填写市级
        if ("-1".equals(tTaskResolve.getAddrArea()) && "市".equals(cityString)) {
            String address = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getAddrCity());
            tTaskResolve.setAddress(address);
            //添加分解等级编码
            tTaskResolve.setResolveLevel("2");

            //判断填写区县级
        } else if (tTaskResolve.getAddrArea() != null && tTaskResolve.getAddrArea().equals("-1")==false) {
            //根据字典键值查询字典标签
            String address = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getAddrArea());
            tTaskResolve.setAddress(address);
            //添加分解等级编码
            tTaskResolve.setResolveLevel("2");
        }else{
            String address = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getAddrCity());
            tTaskResolve.setAddress(address);
            //添加分解等级编码
            tTaskResolve.setResolveLevel("1");
        }
        //分解完成后修改状态
        tTaskPublishService.updateTTaskPublishStatus(Long.parseLong(publishId));

        return toAjax(tTaskResolveService.insertTTaskResolve(tTaskResolve));
    }


    /**
     * 新增保存退牧还草工程任务领取市区县分解
     */
    @RequiresPermissions("system:resolve:add")
    @Log(title = "退牧还草工程任务领取分解", businessType = BusinessType.INSERT)
    @PostMapping("/Subclass")
    @ResponseBody
    public void addSaveSubclass(TTaskResolve tTaskResolve) {
        tTaskResolveService.insertTTaskResolve(tTaskResolve);

    }

    /**
     * 修改退牧还草工程任务领取分解
     */
    @GetMapping("/edit/{resolveId}")
    public String edit(@PathVariable("resolveId") Long publishId, ModelMap mmap) {
        //查看后领取
        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskResolveById(publishId);
        if (tTaskPublish.getStatus() != "1") {
            tTaskPublishService.updateTTaskResolveStatus(tTaskPublish);
            TTaskPublish tTask = tTaskPublishService.selectTTaskPublishById(publishId);
            mmap.put("tTaskResolve", tTask);
        } else {
            mmap.put("tTaskResolve", tTaskPublish);
        }
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
     * 新增保存退牧还草工程任务领取市区县分解
     */
    // @RequiresPermissions("system:resolve:add")
    @Log(title = "退牧还草工程任务领取分解", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addFenJie", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String addSaveFenJie(@RequestBody List<TTaskResolve> tTaskResolves ) {
       // Double sum= tTaskResolves .stream().collect(Collectors.summingDouble(TTaskResolve::getJmSize));
        //定义分解属性值
        Double xm=0.0;
        Double jm=0.0;
        Double smhzl=0.0;
        Double hql=0.0;
        Double jmm=0.0;
        Double xmm=0.0;
        Double hqlxm=0.0;
        Double smhzlm=0.0;
        Double thzygl=0.0;
        Double rgscd=0.0;
        Double hspj=0.0;
        Double htt=0.0;
        Double dhc=0.0;
        Double ykcyzl=0.0;

        Double xm2=0.0;
        Double jm2=0.0;
        Double smhzl2=0.0;
        Double hql2=0.0;
        Double jmm2=0.0;
        Double xmm2=0.0;
        Double hqlxm2=0.0;
        Double smhzlm2=0.0;
        Double thzygl2=0.0;
        Double rgscd2=0.0;
        Double hspj2=0.0;
        Double htt2=0.0;
        Double dhc2=0.0;
        Double ykcyzl2=0.0;
        //定义变量发布id
        Long pid=0l;

        try {
            for(int i =3;i<tTaskResolves.size();i++){

                //获取分解数据集合
                TTaskResolve tTaskResolve= tTaskResolves.get(i);
                //判断库存
                /** 围栏规模合计 */
                Double xmSize = tTaskResolve.getXmSize();
                if (xmSize == null) {
                    xmSize = 0.0;
                }
                xm+=xmSize;
                Double jmSize = tTaskResolve.getJmSize();
                if (jmSize == null) {
                    jmSize = 0.0;
                }
                Double jmsizeSum = jmSize;
                jm+=jmSize;
                Double smhzlSizeSize = tTaskResolve.getSmhzlSize();
                if (smhzlSizeSize == null) {
                    smhzlSizeSize = 0.0;
                }
                smhzl+=smhzlSizeSize;

                Double hqlxSizeSize = tTaskResolve.getHqlxSize();
                if (hqlxSizeSize == null) {
                    hqlxSizeSize = 0.0;
                }
                hql+=hqlxSizeSize;



                /** 围栏投资合计 */
                Double jmMoney = tTaskResolve.getJmMoney();
                if (jmMoney == null) {
                    jmMoney = 0.0;
                }
                jmm+=jmMoney;

                Double xmMoney = tTaskResolve.getXmMoney();
                if (xmMoney == null) {
                    xmMoney = 0.0;
                }
                xmm+=xmMoney;

                Double hqlxMoney = tTaskResolve.getHqlxMoney();
                if (hqlxMoney == null) {
                    hqlxMoney = 0.0;
                }
                hqlxm+=hqlxMoney;

                Double smhzlMoney = tTaskResolve.getSmhzlMoney();
                if (smhzlMoney == null) {
                    smhzlMoney = 0.0;
                }
                smhzlm+=smhzlMoney;

                Double wljeCount = jmMoney + xmMoney + hqlxMoney + smhzlMoney;
                tTaskResolve.setWljeCount(wljeCount);


                /** 其他规模合计 */
                Double thzyglSize = tTaskResolve.getThzyglSize();
                if (thzyglSize == null) {
                    thzyglSize = 0.0;
                }
                thzygl+=thzyglSize;

                Double rgscdSize = tTaskResolve.getRgscdSize();
                if (rgscdSize == null) {
                    rgscdSize = 0.0;
                }
                rgscd+=rgscdSize;

                Double hspjSize = tTaskResolve.getHspjSize();
                if (hspjSize == null) {
                    hspjSize = 0.0;
                }
                hspj+=hspjSize;

                Double httSize = tTaskResolve.getHttSize();
                if (httSize == null) {
                    httSize = 0.0;
                }
                htt+=httSize;

                Double dhcSize = tTaskResolve.getDhcSize();
                if (dhcSize == null) {
                    dhcSize = 0.0;
                }
                dhc+=dhcSize;

                Double ykcyzlSize = tTaskResolve.getYkcyzlSize();
                if (ykcyzlSize == null) {
                    ykcyzlSize = 0.0;
                }
                ykcyzl+=ykcyzlSize;


                if(tTaskResolve.getPublishId()!=null){
                    Long publishId=tTaskResolve.getPublishId();
                    //查出所有分解任务
                    List<TTaskResolve> tTaskResolvelist=tTaskResolveService.selectTTaskResolve(publishId);

                    for (int j = 0; j < tTaskResolvelist.size(); j++) {
                        TTaskResolve taskResolve1 =  tTaskResolvelist.get(j);
                        /** 围栏规模合计 */
                        Double xmSize2 = taskResolve1.getXmSize();
                        if (xmSize2 == null) {
                            xmSize2 = 0.0;
                        }
                        xm2+=xmSize2;
                        Double jmSize2 = taskResolve1.getJmSize();
                        if (jmSize2 == null) {
                            jmSize2= 0.0;
                        }
                        jm2+=jmSize2;
                        Double smhzlSizeSize2 = taskResolve1.getSmhzlSize();
                        if (smhzlSizeSize2 == null) {
                            smhzlSizeSize2 = 0.0;
                        }
                        smhzl2+=smhzlSizeSize2;

                        Double hqlxSizeSize2 = taskResolve1.getHqlxSize();
                        if (hqlxSizeSize2 == null) {
                            hqlxSizeSize2 = 0.0;
                        }
                        hql2+=hqlxSizeSize2;



                        /** 围栏投资合计 */
                        Double jmMoney2 = taskResolve1.getJmMoney();
                        if (jmMoney2 == null) {
                            jmMoney2 = 0.0;
                        }
                        jmm2+=jmMoney2;

                        Double xmMoney2 = taskResolve1.getXmMoney();
                        if (xmMoney2 == null) {
                            xmMoney2 = 0.0;
                        }
                        xmm2+=xmMoney2;

                        Double hqlxMoney2 = taskResolve1.getHqlxMoney();
                        if (hqlxMoney2 == null) {
                            hqlxMoney2 = 0.0;
                        }
                        hqlxm2+=hqlxMoney2;

                        Double smhzlMoney2 = taskResolve1.getSmhzlMoney();
                        if (smhzlMoney2 == null) {
                            smhzlMoney2 = 0.0;
                        }
                        smhzlm2+=smhzlMoney2;


                        /** 其他规模合计 */
                        Double thzyglSize2 = taskResolve1.getThzyglSize();
                        if (thzyglSize2 == null) {
                            thzyglSize2 = 0.0;
                        }
                        thzygl2+=thzyglSize2;

                        Double rgscdSize2 = taskResolve1.getRgscdSize();
                        if (rgscdSize2 == null) {
                            rgscdSize2 = 0.0;
                        }
                        rgscd2+=rgscdSize2;

                        Double hspjSize2 = taskResolve1.getHspjSize();
                        if (hspjSize2 == null) {
                            hspjSize2 = 0.0;
                        }
                        hspj2+=hspjSize2;

                        Double httSize2 = taskResolve1.getHttSize();
                        if (httSize2 == null) {
                            httSize2 = 0.0;
                        }
                        htt2+=httSize2;

                        Double dhcSize2 = taskResolve1.getDhcSize();
                        if (dhcSize2 == null) {
                            dhcSize2 = 0.0;
                        }
                        dhc2+=dhcSize2;

                        Double ykcyzlSize2 = taskResolve1.getYkcyzlSize();
                        if (ykcyzlSize2 == null) {
                            ykcyzlSize2 = 0.0;
                        }
                        ykcyzl2+=ykcyzlSize2;

                    }
                }

                //查询省份
                String province = tTaskResolveService.selectDictValue(tTaskResolve.getAddrCity());
                tTaskResolve.setProvince(province);
                String city= tTaskResolve.getProvince();
                //截取字符串判断从市级开始分解
                String cityString=city.substring(city.length()-1);


                //判断填写市级
                if ("-1".equals(tTaskResolve.getAddrArea()) && "市".equals(cityString)) {
                    String address = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getAddrCity());
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("2");

                    //判断填写区县级
                } else if (tTaskResolve.getAddrArea() != null && tTaskResolve.getAddrArea().equals("-1")==false) {
                    //根据字典键值查询字典标签
                    String address = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getAddrArea());
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("2");
                }else{
                    String address = iSysDictDataService.selectDictValueToLabel(tTaskResolve.getAddrCity());
                    tTaskResolve.setAddress(address);
                    //添加分解等级编码
                    tTaskResolve.setResolveLevel("1");
                }
                //取出分解任务父id
                if(tTaskResolve.getPublishId()!=null){
                    Long publishId=tTaskResolve.getPublishId();
                     pid=publishId;

                }else{
                    Long pId= tTaskResolves.get(3).getPublishId();
                    tTaskResolve.setPublishId(pId);
                }
                TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(pid);
                /** 围栏规模合计 */
                Double xmSizeCount = tTaskPublish.getXmSize();
                if (xmSizeCount == null) {
                    xmSizeCount = 0.0;
                }
                Double jmSizeCount = tTaskPublish.getJmSize();
                if (jmSizeCount == null) {
                    jmSizeCount= 0.0;
                }
                Double smhzlSizeSizeCount = tTaskPublish.getSmhzlSize();
                if (smhzlSizeSizeCount == null) {
                    smhzlSizeSizeCount = 0.0;
                }

                Double hqlxSizeSizeCount = tTaskPublish.getHqlxSize();
                if (hqlxSizeSizeCount == null) {
                    hqlxSizeSizeCount = 0.0;
                }



                /** 围栏投资合计 */
                Double jmMoneyCount = tTaskPublish.getJmMoney();
                if (jmMoneyCount == null) {
                    jmMoneyCount = 0.0;
                }

                Double xmMoneyCount = tTaskPublish.getXmMoney();
                if (xmMoneyCount == null) {
                    xmMoneyCount = 0.0;
                }

                Double hqlxMoneyCount = tTaskPublish.getHqlxMoney();
                if (hqlxMoneyCount == null) {
                    hqlxMoneyCount = 0.0;
                }

                Double smhzlMoneyCount = tTaskPublish.getSmhzlMoney();
                if (smhzlMoneyCount == null) {
                    smhzlMoneyCount = 0.0;
                }


                /** 其他规模合计 */
                Double thzyglSizeCount = tTaskPublish.getThzyglSize();
                if (thzyglSizeCount == null) {
                    thzyglSizeCount = 0.0;
                }

                Double rgscdSizeCount = tTaskPublish.getRgscdSize();
                if (rgscdSizeCount == null) {
                    rgscdSizeCount = 0.0;
                }

                Double hspjSizeCount = tTaskPublish.getHspjSize();
                if (hspjSizeCount == null) {
                    hspjSizeCount = 0.0;
                }

                Double httSizeCount = tTaskPublish.getHttSize();
                if (httSizeCount == null) {
                    httSizeCount = 0.0;
                }

                Double dhcSizeCount = tTaskPublish.getDhcSize();
                if (dhcSizeCount == null) {
                    dhcSizeCount = 0.0;
                }

                Double ykcyzlSizeCount = tTaskPublish.getYkcyzlSize();
                if (ykcyzlSizeCount == null) {
                    ykcyzlSizeCount = 0.0;
                }
                if(jm+jm2>jmSizeCount){
                    return "404";
                }else if(jmm+jmm2>jmMoneyCount){
                    return "404";

                }else if(xm+xm2>xmSizeCount){
                    return "404";

                }else if(smhzl+smhzl2>smhzlSizeSizeCount){
                    return "404";

                }else if(hql+hql2>hqlxSizeSizeCount){
                    return "404";

                }else if(xmm+xmm2>xmMoneyCount){
                    return "404";

                }else if(hqlxm+hqlxm2>hqlxMoneyCount){
                    return "404";

                }else if(smhzlm+smhzlm2>smhzlMoneyCount){
                    return "404";

                }else if(thzygl+thzygl2>thzyglSizeCount){
                    return "404";

                }else if(rgscd+rgscd2>rgscdSizeCount){
                    return "404";

                }else if(hspj+hspj2>hspjSizeCount){
                    return "404";

                }else if(htt+htt2>httSizeCount){
                    return "404";

                }else if(dhc+dhc2>dhcSizeCount){
                    return "404";

                }else if(ykcyzl+ykcyzl2>ykcyzlSizeCount){
                    return "404";

                }
                else {
                    //分解完成后修改状态
                    tTaskPublishService.updateTTaskPublishStatus(pid);
                    //逐条添加每条分解任务
                    tTaskResolveMapper.insertTTaskResolve(tTaskResolve);
                    return "400";

                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
