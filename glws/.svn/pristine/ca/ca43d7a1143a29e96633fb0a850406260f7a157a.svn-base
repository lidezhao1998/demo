package com.ruoyi.web.controller.system;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskPublishService;
import com.ruoyi.system.service.ITTaskResolveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退牧还草工程任务发布Controller
 *
 * @author feiyanxu
 * @date 2019-11-28
 */
@Controller
@RequestMapping("/system/publish")
public class TTaskPublishController extends BaseController
{
    private String prefix = "system/pulish";

    @Autowired
    private ITTaskPublishService tTaskPublishService;

   @Autowired
   private ITTaskResolveService taskResolveService;

    @Autowired
    private ISysDictDataService iSysDictDataService;



    @RequiresPermissions("system:publish:view")
    @GetMapping()
    public String publish()
    {
        return prefix + "/publish";
    }

    /**
     * 查询退牧还草工程任务发布列表
     */
    @RequiresPermissions("system:publish:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TTaskPublish tTaskPublish)
    {
        startPage();
        //根据字典键值查询字典标签
        if(tTaskPublish.getProvince()!=""){
            String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
            tTaskPublish.setProvince(provinceLabel);
        }
        //查询登录人的用户信息(登录名)
        String operName = ShiroUtils.getSysUser().getLoginName();

        //获取登录用户的部门
        String deptName=ShiroUtils.getSysUser().getDept().getDeptName();
        if(deptName.equals("国家级")){
            System.out.println("国家级部门");
        }else if(deptName.equals("国家级")){
            tTaskPublish.setProvince(deptName);
        }
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        for (int i = 0; i < list.size(); i++) {
            TTaskPublish Publish =  list.get(i);
            if(deptName.equals("国家级")){
                System.out.println("国家级部门");
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
            }

            /** 围栏规模合计 */
            Double xmSize=Publish.getXmSize();
            if(xmSize==null){
                 xmSize=0.0;
            }
            Double jmSize=Publish.getJmSize();
            if(jmSize==null){
                jmSize=0.0;
            }
            Double smhzlSizeSize=Publish.getSmhzlSize();
            if(smhzlSizeSize==null){
                smhzlSizeSize=0.0;
            }
            Double hqlxSizeSize=Publish.getHqlxSize();
            if(hqlxSizeSize==null){
                hqlxSizeSize=0.0;
            }
            Double wlmiCount = xmSize+jmSize+smhzlSizeSize+hqlxSizeSize;
            Publish.setWlmjCount(wlmiCount);



            /** 围栏投资合计 */
            Double jmMoney=Publish.getJmMoney();
            if(jmMoney==null){
                jmMoney=0.0;
            }
            Double xmMoney= Publish.getXmMoney();
            if(xmMoney==null){
                xmMoney=0.0;
            }
            Double hqlxMoney=Publish.getHqlxMoney();
            if(hqlxMoney==null){
                hqlxMoney=0.0;
            }
            Double smhzlMoney=Publish.getSmhzlMoney();
            if(smhzlMoney==null){
                smhzlMoney=0.0;
            }
            Double wljeCount = jmMoney+xmMoney+hqlxMoney+smhzlMoney;
            Publish.setWljeCount(wljeCount);


            /** 其他规模合计 */
            Double thzyglSize=Publish.getThzyglSize();
            if(thzyglSize==null){
                thzyglSize=0.0;
            }
            Double rgscdSize= Publish.getRgscdSize();
            if(rgscdSize==null){
                rgscdSize=0.0;
            }
            Double hspjSize=Publish.getHspjSize();
            if(hspjSize==null){
                hspjSize=0.0;
            }
            Double httSize=Publish.getHttSize();
            if(httSize==null){
                httSize=0.0;
            }
            Double dhcSize=Publish.getDhcSize();
            if(dhcSize==null){
                dhcSize=0.0;
            }
            Double ykcyzlSize=Publish.getYkcyzlSize();
            if(ykcyzlSize==null){
                ykcyzlSize=0.0;
            }
            Double  qtCount= thzyglSize+rgscdSize+hspjSize+httSize+dhcSize+ykcyzlSize;
            Publish.setQtmjCount(qtCount);

            /** 其金额合计 */
            Double thzyglMoney=Publish.getThzyglMoney();
            if(thzyglMoney==null){
                thzyglMoney=0.0;
            }
            Double rgscdMoney=Publish.getRgscdMoney();
            if(rgscdMoney==null){
                rgscdMoney=0.0;
            }
            Double hspjMoney=Publish.getHspjMoney();
            if(hspjMoney==null){
                hspjMoney=0.0;
            }
            Double httMoney=Publish.getHttMoney();
            if(httMoney==null){
                httMoney=0.0;
            }
            Double dhcMoney=Publish.getDhcMoney();
            if(dhcMoney==null){
                dhcMoney=0.0;
            }
            Double ykcazlMoney=Publish.getYkcazlMoney();
            if(ykcazlMoney==null){
                ykcazlMoney=0.0;
            }
            Double  qtjeCount= thzyglMoney+rgscdMoney+hspjMoney+httMoney+dhcMoney+ykcazlMoney;
            Publish.setQtjeCount(qtjeCount);

            /** 总规模合计 */
            Double zgmCount=Publish.getQtmjCount()+Publish.getWlmjCount();
            Publish.setZgmCount(zgmCount);


            /** 总金额 */
            Double zjeCount=qtjeCount+wljeCount;
            Publish.setZjeCount(zjeCount);


        }

        return getDataTable(list);
    }

    /**
     * 导出退牧还草工程任务发布列表
     */
    @RequiresPermissions("system:publish:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TTaskPublish tTaskPublish)
    {
        List<TTaskPublish> list = tTaskPublishService.selectTTaskPublishList(tTaskPublish);
        ExcelUtil<TTaskPublish> util = new ExcelUtil<TTaskPublish>(TTaskPublish.class);
        return util.exportExcel(list, "publish");
    }

    /**
     * 新增退牧还草工程任务发布
     */
    @GetMapping("/addLooks")
    public String addLook()
    {
        return prefix + "/look";
    }

    /**
     * 新增退牧还草工程任务发布
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        /*int maxId= (int) tTaskPublishService.getByid();
        int insertId= maxId+1;
        mmap.put("insertId",insertId);*/
        return prefix + "/add";
    }

    /**
     * 新增保存退牧还草工程任务发布
     */
    @RequiresPermissions("system:publish:add")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TTaskPublish tTaskPublish)
    {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);
        tTaskPublish.setStatus("0");

       /* if(tTaskPublish.getAddrArea().equals("-1") && tTaskPublish.getAddrCity()!=null){
            String address = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getAddrCity());
            tTaskPublish.setAddress(address);

        }else if(tTaskPublish.getAddrArea()!=null && tTaskPublish.getAddrArea()!="-1"){
            //根据字典键值查询字典标签
            String address = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getAddrArea());
            tTaskPublish.setAddress(address);
        }*/
        return toAjax( tTaskPublishService.insertTTaskPublish(tTaskPublish));
    }

    @RequiresPermissions("system:publish:a")
    @GetMapping("/a/{publishId}")
    public String a(@PathVariable("publishId") Long publishId, ModelMap mmap)

    {
        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(publishId);
        mmap.put("tTaskPublish", tTaskPublish);
        return prefix + "/edit";
    }

    /**
     * 添加回显数据
     */
    @GetMapping("/selectDept/a")
    public String selectDept(TTaskPublish tTaskPublish, ModelMap mmap)
    {
        return prefix + "/look";
    }

    @GetMapping("/form")
    public String form()
    {
        return prefix + "/look";
    }

    /**
     * 修改退牧还草工程任务发布
     */
    @GetMapping("/edit/{publishId}")
    public String edit(@PathVariable("publishId") Long publishId, ModelMap mmap)
    {

        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(publishId);
        //根据省份名称查询dictcode
        String province = iSysDictDataService.selectDictValueCode(tTaskPublish.getProvince());
        tTaskPublish.setProvince(province);

        mmap.put("tTaskPublish", tTaskPublish);
        return prefix + "/edit";
    }

    /**
     * 查看退牧还草工程任务发布
     */
    //@RequiresPermissions("system:publish:detail")
    @GetMapping("/detail/{publishId}")
    public String detail(@PathVariable("publishId") Long publishId, ModelMap mmap, Model model)
    {
      //  List<TTaskResolve> page= taskResolveService.selectTTaskResolveByPublishId(publishId);
        TTaskPublish tTaskPublish = tTaskPublishService.selectTTaskPublishById(publishId);
        mmap.put("tTaskPublish", tTaskPublish);

        return prefix + "/editLook";
    }


    /**
     * 修改保存退牧还草工程任务发布
     */
    @RequiresPermissions("system:publish:edit")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TTaskPublish tTaskPublish)
    {
        //根据字典键值查询字典标签
        String provinceLabel = iSysDictDataService.selectDictValueToLabel(tTaskPublish.getProvince());
        tTaskPublish.setProvince(provinceLabel);
        return toAjax(tTaskPublishService.updateTTaskPublish(tTaskPublish));
    }
    /**
     * 删除退牧还草工程任务发布
     */
    @RequiresPermissions("system:publish:remove")
    @Log(title = "退牧还草工程任务发布", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String publishId)
    {

        return toAjax(tTaskPublishService.deleteTTaskPublishById(Long.parseLong(publishId)));
    }


    /**
     * 三级联动省级
     */

    @GetMapping("/getProvinces")
    @ResponseBody
    public List<SysDictData> getProvinces(ModelMap mmap)
    {
        String dictType="sys_province";
        List<SysDictData> list=iSysDictDataService.selectDictDataByType(dictType);
        return list;
    }
    /**
     * 三级联动市级
     */

    @GetMapping("/getCities")
    @ResponseBody
    public List<SysDictData> getCities(ModelMap mmap, String provinceCode)
    {
        List<SysDictData> list=iSysDictDataService.getCities(provinceCode);
        return list;
    }
    /**
     * 三级联动曲县级
     */
    @GetMapping("/getAreas")
    @ResponseBody
    public List<SysDictData> getAreas( ModelMap mmap,String cityCode)
    {
        List<SysDictData> list=iSysDictDataService.getAreas(cityCode);

        return list;
    }

    /**
     * 四级联动曲县级
     */
    @GetMapping("/getdz")
    @ResponseBody
    public List<SysDictData> getdz( ModelMap mmap,String areasCode)
    {
        List<SysDictData> list=iSysDictDataService.getAreas(areasCode);

        return list;
    }


    /**
     * 撤回操作
     */
    @Log(title = "撤回操作", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult Withdraw  (TTaskPublish tTaskPublish)
    {
        //判断状态为未领取
        if(tTaskPublish.getStatus()=="0"){
            return toAjax(tTaskPublishService.updateTTaskPublishchehui(tTaskPublish));
        }else{
            Long publishId=tTaskPublish.getPublishId();
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
    public AjaxResult Release  (Long publishId)
    {
        return toAjax(tTaskPublishService.updateTTaskPublishRelease(publishId));
    }

    /**
     *查看
     */
    @RequiresPermissions("system:publish:list")
    @PostMapping("/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(TTaskPublish tTaskPublish)
    {
        startPage();
         List<TTaskResolve> list= taskResolveService.selectTTaskResolveByPublishId(tTaskPublish);
        return getDataTable(list);
    }
    /**
     *展开详情信息
     */
    @GetMapping("/authDataScope/{resolveId}")
    public String authDataScope(@PathVariable("resolveId") Long resolveId, ModelMap mmap)
    {
        TTaskResolve tTaskResolve = taskResolveService.selectTTaskResolveById(resolveId);
        mmap.put("tTaskPublish",tTaskResolve);
        return prefix + "/fenjieLook";
    }

    // queren
    @GetMapping("/switchSkin")
    public String switchSkin(ModelMap mmap, TTaskPublish tTaskPublish)
    {
        mmap.put("tTaskPublish",tTaskPublish);
        return prefix+"/look";
    }

}
