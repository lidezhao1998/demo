package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.CRatKnowledge;
import com.ruoyi.zaihai.caiji.service.ICRatKnowledgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 鼠害知识库信息Controller
 * 
 * @author ruoyi
 * @date 2020-09-04
 */
@Controller
@RequestMapping("/library/mouseknowledgeView")
public class CRatKnowledgeViewController extends BaseController
{
    private String prefix = "system/mouseknowledge";

    @Autowired
    private ICRatKnowledgeService cRatKnowledgeService;

    @Autowired
    private ServerConfig serverConfig;

    @RequiresPermissions("library:mouseknowledgeView:view")
    @GetMapping()
    public String knowledge()
    {
        return prefix + "/knowledgeView";
    }

    /**
     * 查询鼠害知识库信息列表
     */
    @RequiresPermissions("library:mouseknowledgeView:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CRatKnowledge cRatKnowledge)
    {
        startPage();
        List<CRatKnowledge> list = cRatKnowledgeService.selectCRatKnowledgeList(cRatKnowledge);
        return getDataTable(list);
    }

    /**
     * 导出鼠害知识库信息列表
     */
    @RequiresPermissions("library:mouseknowledgeView:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CRatKnowledge cRatKnowledge)
    {
        List<CRatKnowledge> list = cRatKnowledgeService.selectCRatKnowledgeList(cRatKnowledge);
        ExcelUtil<CRatKnowledge> util = new ExcelUtil<CRatKnowledge>(CRatKnowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 新增鼠害知识库信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }



    /**
     * 新增保存草原病害草知识库信息
     */
    @RequiresPermissions("library:mouseknowledgeView:add")
    @Log(title = "鼠害知识库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file,CRatKnowledge cRatKnowledge)
    {
        try
        {
            // library.out.println("上传的图片数："+file.length);
            // 上传文件路径
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                // AjaxResult ajax = AjaxResult.success();
                cRatKnowledge.setPicture(url);
            }

            return toAjax(cRatKnowledgeService.insertCRatKnowledge(cRatKnowledge));        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改鼠害知识库信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CRatKnowledge cRatKnowledge = cRatKnowledgeService.selectCRatKnowledgeById(id);
        mmap.put("cRatKnowledge", cRatKnowledge);
        return prefix + "/edit";
    }


    /**
     * 修改保存草原病害草知识库信息
     */
    @RequiresPermissions("library:mouseknowledgeView:edit")
    @Log(title = "鼠害知识库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("file") MultipartFile file,CRatKnowledge cRatKnowledge)
    {
        try
        {
            // library.out.println("上传的图片数："+file.length);
            // 上传文件路径

            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                // AjaxResult ajax = AjaxResult.success();
                cRatKnowledge.setPicture(url);

            }


            return toAjax(cRatKnowledgeService.updateCRatKnowledge(cRatKnowledge));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 删除鼠害知识库信息
     */
    @RequiresPermissions("library:mouseknowledgeView:remove")
    @Log(title = "鼠害知识库信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cRatKnowledgeService.deleteCRatKnowledgeByIds(ids));
    }


    /**
     * 删除草原鼠虫害知识库信息图片
     */
    @RequiresPermissions("library:mouseknowledgeView:remove")
    @Log(title = "草原病害知识库信息", businessType = BusinessType.DELETE)
    @PostMapping("/delPothon")
    @ResponseBody
    public String delPothon(String id,String type) {
        try {
            String url="";
            Long longId = Long.parseLong(id);
            CRatKnowledge cRatKnowledge = cRatKnowledgeService.selectCRatKnowledgeById(longId);

            if (type.equals("picture")) {
                url = cRatKnowledge.getPicture();

            }
            //获取本机访问地址
            String qz= serverConfig.getUrl();
            //截取访问路径前缀
            String str=url.substring(0, url.indexOf("u"));

            String bendiUrl=url.substring(str.length()+1, url.length());
            //拼接本地删除路径
            String defaultBaseDir = Global.getProfile()+"/u"+bendiUrl;
            FileUtils.deleteFile(defaultBaseDir);

            if (type.equals("picture")) {
                cRatKnowledgeService.updateCRatKnowledgePicture(id);

            }
            return "200";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }


    /**
     * 查看鼠害知识库
     */
    @RequiresPermissions("library:mouseknowledgeView:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap, Model model, CRatKnowledge cRatKnowledge) {
        //  List<TTaskResolve> page= taskResolveService.selectTTaskResolveByPublishId(publishId);
        CRatKnowledge cRatKnowled = cRatKnowledgeService.selectCRatKnowledgeById(id);
        mmap.put("cRatKnowledge", cRatKnowled);

        return prefix + "/look";
    }


}
