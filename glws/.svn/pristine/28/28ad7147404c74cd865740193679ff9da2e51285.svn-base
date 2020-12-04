package com.sinosoft.web.controller.integration;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.sinosoft.integration.domain.MeteorologyData;
import com.sinosoft.integration.service.MeteorologyDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 14:45
 * 气象数据
 */

@Controller
@RequestMapping("/integration/meteorologyData")
public class MeteorologyDataController extends BaseController {
    @Autowired
    MeteorologyDataService meteorologyDataService;

//    @Autowired
//    private transient JavaSparkContext sc;
//
    private String prefix = "integration/meteorologyData";
    @GetMapping
    public String MeteorologyData(){
        return prefix+"/meteorologyDataList";
    }

    @RequiresPermissions("integration:meteorologydata:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MeteorologyData meteorologyData)
    {
        startPage();
        List<MeteorologyData> list = meteorologyDataService.selectMeteorologyDataList(meteorologyData);
        return getDataTable(list);
    }

//    public void max(){
//        MeteorologyData meteorologyData = new MeteorologyData();
//        List<M
// eteorologyData> list = meteorologyDataService.selectMeteorologyDataList(meteorologyData);
//        //JavaRDD每次对RDD的操作，都视之为一次job
//        JavaRDD<MeteorologyData> rdd01 = sc.parallelize(list);
//        JavaRDD<MeteorologyData> data=rdd01.filter(new FlatMapFunction<MeteorologyData>(){
//            @Override
//            public Iterator call(MeteorologyData md) throws Exception {
//                List<String> list1 = Arrays.asList(md);
//                return null;
//            }
//        });
//    }


//    /**
//     * 新增非工程样地
//     */
//    @GetMapping("/add")
//    public String add()
//    {
//        return prefix + "/add";
//    }
//
//    /**
//     * 新增保存非工程样地
//     */
//    @RequiresPermissions("integration:nprojectsampledata:add")
//    @Log(title = "非工程样地", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(@Validated NprojectSampleData nprojectSampleData)
//    {
//        nprojectSampleData.setCreateBy(ShiroUtils.getLoginName());
//        return toAjax(nprojectSampleDataService.insertNprojectSampleData(nprojectSampleData));
//    }
//
//    /**
//     * 跳转修改非工程样地数据表页面
//     */
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") int id, ModelMap mmap)
//    {
//        NprojectSampleData nprojectSampleData=new NprojectSampleData();
//        nprojectSampleData.setId(id);
//        mmap.put("nprojectSampleData", nprojectSampleDataService.selectNprojectSampleData(nprojectSampleData));
//        return prefix + "/edit";
//    }
//    /**
//     * 修改非工程样地数据表
//     */
//    @RequiresPermissions("integration:nprojectsampledata:edit")
//    @Log(title = "非工程样地数据表", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(@Validated NprojectSampleData nprojectSampleData)
//    {
//        nprojectSampleData.setUpdateBy(ShiroUtils.getLoginName());
//        return toAjax(nprojectSampleDataService.updateNprojectSampleData(nprojectSampleData));
//    }

    /**
     * 删除
     */
    @RequiresPermissions("integration:meteorologydata:remove")
    @Log(title = "气象数据", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(meteorologyDataService.deleteMeteorologyDataByIds(ids));
    }


}
