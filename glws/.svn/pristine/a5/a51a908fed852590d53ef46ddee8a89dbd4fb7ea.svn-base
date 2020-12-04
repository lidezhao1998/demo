package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.CDiseaseKnowledge;
import com.ruoyi.zaihai.caiji.service.ICDiseaseKnowledgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 草原病害草知识库信息Controller
 *
 * @author ruoyi
 * @date 2020-04-29
 */
@Controller
@RequestMapping("/library/knowledgeView")
public class CDiseaseKnowledgeViewController extends BaseController
{
    private String prefix = "system/knowledge";

    @Autowired
    private ICDiseaseKnowledgeService cDiseaseKnowledgeService;

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 跳转管理页面
     */
    @RequiresPermissions("library:knowledgeView:view")
    @GetMapping()
    public String knowledge()
    {
        return prefix + "/knowledgeView";
    }


    /**
     * 查询草原病害草知识库信息列表
     */
    @RequiresPermissions("library:knowledgeView:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CDiseaseKnowledge cDiseaseKnowledge)
    {
        startPage();
        List<CDiseaseKnowledge> list = cDiseaseKnowledgeService.selectCDiseaseKnowledgeList(cDiseaseKnowledge);
        return getDataTable(list);
    }

    /**
     * 导出草原病害草知识库信息列表
     */
    @RequiresPermissions("library:knowledgeView:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CDiseaseKnowledge cDiseaseKnowledge)
    {
        List<CDiseaseKnowledge> list = cDiseaseKnowledgeService.selectCDiseaseKnowledgeList(cDiseaseKnowledge);
        ExcelUtil<CDiseaseKnowledge> util = new ExcelUtil<CDiseaseKnowledge>(CDiseaseKnowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 新增草原病害草知识库信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存草原病害草知识库信息
     */
    @RequiresPermissions("library:knowledgeView:add")
    @Log(title = "草原病害草知识库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2,CDiseaseKnowledge cDiseaseKnowledge)
    {
        try
        {
            // library.out.println("上传的图片数："+file.length);
            // 上传文件路径

            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            // AjaxResult ajax = AjaxResult.success();
            cDiseaseKnowledge.setByMorphology(url);

            String filePath2 = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName2 = FileUploadUtils.upload(filePath2, file2);
            String url2 = serverConfig.getUrl() + fileName2;
            // AjaxResult ajax = AjaxResult.success();
            cDiseaseKnowledge.setSymptomMap(url2);

            return toAjax(cDiseaseKnowledgeService.insertCDiseaseKnowledge(cDiseaseKnowledge));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改草原病害草知识库信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CDiseaseKnowledge cDiseaseKnowledge = cDiseaseKnowledgeService.selectCDiseaseKnowledgeById(id);
        mmap.put("cDiseaseKnowledge", cDiseaseKnowledge);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原病害草知识库信息
     */
    @RequiresPermissions("library:knowledgeView:edit")
    @Log(title = "草原病害草知识库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2,CDiseaseKnowledge cDiseaseKnowledge)
    {
        try
        {
            // library.out.println("上传的图片数："+file.length);
            // 上传文件路径

            if(null != file){
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                // AjaxResult ajax = AjaxResult.success();
                cDiseaseKnowledge.setByMorphology(url);

            }
            if(file2 !=null){
                String filePath2 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName2 = FileUploadUtils.upload(filePath2, file2);
                String url2 = serverConfig.getUrl() + fileName2;
                // AjaxResult ajax = AjaxResult.success();
                cDiseaseKnowledge.setSymptomMap(url2);
            }

            return toAjax(cDiseaseKnowledgeService.updateCDiseaseKnowledge(cDiseaseKnowledge));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
    /**
     * 删除草原病害草知识库信息
     */
    @RequiresPermissions("library:knowledgeView:remove")
    @Log(title = "草原病害草知识库信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cDiseaseKnowledgeService.deleteCDiseaseKnowledgeByIds(ids));
    }

    /**
     * 查看病害知识库
     */
    @RequiresPermissions("library:knowledgeView:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap, Model model,CDiseaseKnowledge cDiseaseKnowledge)
    {
        //  List<TTaskResolve> page= taskResolveService.selectTTaskResolveByPublishId(publishId);
        CDiseaseKnowledge cDiseaseKnowledg = cDiseaseKnowledgeService.selectCDiseaseKnowledgeById(id);
        mmap.put("cDiseaseKnowledge", cDiseaseKnowledg);

        return prefix + "/look";
    }


}
