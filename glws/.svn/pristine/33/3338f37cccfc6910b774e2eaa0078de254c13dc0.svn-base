package com.ruoyi.zaihai.caiji.controller;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.zaihai.caiji.domain.CPoisonKnowledge;
import com.ruoyi.zaihai.caiji.domain.CRatpestKnowledge;
import com.ruoyi.zaihai.caiji.service.ICPoisonKnowledgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 毒害草知识库Controller
 * 
 * @author ruoyi
 * @date 2020-06-15
 */
@Controller
@RequestMapping("/library/poisonknowledge")
public class CPoisonKnowledgeController extends BaseController
{
    private String prefix = "system/poisonknowledge";
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ICPoisonKnowledgeService cPoisonKnowledgeService;

    @RequiresPermissions("library:poisonknowledge:view")
    @GetMapping()
    public String knowledge()
    {
        return prefix + "/knowledge";
    }

    /**
     * 查询【毒害草知识库信息列表
     */
    @RequiresPermissions("library:poisonknowledge:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CPoisonKnowledge cPoisonKnowledge)
    {
        startPage();
        List<CPoisonKnowledge> list = cPoisonKnowledgeService.selectCPoisonKnowledgeList(cPoisonKnowledge);
        return getDataTable(list);
    }

    /**
     * 导出【毒害草知识库信息列表
     */
    @RequiresPermissions("library:poisonknowledge:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CPoisonKnowledge cPoisonKnowledge)
    {
        List<CPoisonKnowledge> list = cPoisonKnowledgeService.selectCPoisonKnowledgeList(cPoisonKnowledge);
        ExcelUtil<CPoisonKnowledge> util = new ExcelUtil<CPoisonKnowledge>(CPoisonKnowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 新增【毒害草知识库信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【毒害草知识库信息
     */
    @RequiresPermissions("library:poisonknowledge:add")
    @Log(title = "【毒害草知识库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, CPoisonKnowledge cPoisonKnowledge)
    {
        try {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                //形态图
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                // AjaxResult ajax = AjaxResult.success();
                cPoisonKnowledge.setMorphologyName(url);

            }
            if (StringUtils.isNotBlank(file2.getOriginalFilename())) {
                //生态图
                String filePath2 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName2 = FileUploadUtils.upload(filePath2, file2);
                String url2 = serverConfig.getUrl() + fileName2;
                // AjaxResult ajax = AjaxResult.success();
                cPoisonKnowledge.setEcologicalMapName(url2);

            }


            if (StringUtils.isNotBlank(file3.getOriginalFilename())) {
                //分布图
                String filePath3 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName3 = FileUploadUtils.upload(filePath3, file3);
                String url3 = serverConfig.getUrl() + fileName3;
                // AjaxResult ajax = AjaxResult.success();
                cPoisonKnowledge.setDistributionmapName(url3);
            }

            return toAjax(cPoisonKnowledgeService.insertCPoisonKnowledge(cPoisonKnowledge));
        } catch (Exception e) {
            System.out.println("-------------------");
            return AjaxResult.error(e.getMessage());
        }

    }

    /**
     * 修改【毒害草知识库信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CPoisonKnowledge cPoisonKnowledge = cPoisonKnowledgeService.selectCPoisonKnowledgeById(id);
        mmap.put("cPoisonKnowledge", cPoisonKnowledge);
        return prefix + "/edit";
    }

    /**
     * 修改保存【毒害草知识库信息
     */
    @RequiresPermissions("library:poisonknowledge:edit")
    @Log(title = "【毒害草知识库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3,CPoisonKnowledge cPoisonKnowledge)
    {
        try {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                //形态图
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                cPoisonKnowledge.setMorphologyName(url);

            }
            if (StringUtils.isNotBlank(file2.getOriginalFilename())) {
                //生态图
                String filePath2 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName2 = FileUploadUtils.upload(filePath2, file2);
                String url2 = serverConfig.getUrl() + fileName2;
                // AjaxResult ajax = AjaxResult.success();
                cPoisonKnowledge.setEcologicalMapName(url2);

            }


            if (StringUtils.isNotBlank(file3.getOriginalFilename())) {
                //分布图
                String filePath3 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName3 = FileUploadUtils.upload(filePath3, file3);
                String url3 = serverConfig.getUrl() + fileName3;
                // AjaxResult ajax = AjaxResult.success();
                cPoisonKnowledge.setDistributionmapName(url3);
            }
            return toAjax(cPoisonKnowledgeService.updateCPoisonKnowledge(cPoisonKnowledge));
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }

    /**
     * 删除【毒害草知识库信息
     */
    @RequiresPermissions("library:poisonknowledge:remove")
    @Log(title = "【毒害草知识库信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cPoisonKnowledgeService.deleteCPoisonKnowledgeByIds(ids));
    }

    @PostMapping("/checkCode")
    @ResponseBody
    public boolean checkCode(String code, ModelMap mmap) {
        boolean falg = cPoisonKnowledgeService.checkCode(code);
        return !falg;
    }

    /**
     * 查看病害知识库
     */
    @RequiresPermissions("library:knowledge:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap,  CPoisonKnowledge cPoisonKnowledge)
    {
        CPoisonKnowledge cPoisonKnowledgeLook = cPoisonKnowledgeService.selectCPoisonKnowledgeById(id);
        mmap.put("cPoisonKnowledge", cPoisonKnowledgeLook);

        return prefix + "/look";
    }


    /**
     * 删除毒害草知识库信息图片
     */
    @RequiresPermissions("system:Ratpestknowledge:remove")
    @Log(title = "毒害草知识库信息", businessType = BusinessType.DELETE)
    @PostMapping("/delPothon")
    @ResponseBody
    public String delPothon(String id, String type) {
        try {
            String url = "";
            Long longId = Long.parseLong(id);
            CPoisonKnowledge cPoisonKnowledge = cPoisonKnowledgeService.selectCPoisonKnowledgeById(longId);

            if (type.equals("morphologyName")) {
                url = cPoisonKnowledge.getMorphologyName();

            } else if (type.equals("ecologicalMapName")) {
                url = cPoisonKnowledge.getEcologicalMapName();

            } else if (type.equals("distributionmapName")) {
                url = cPoisonKnowledge.getDistributionmapName();

            }
            //获取本机访问地址
            String qz = serverConfig.getUrl();
            //截取访问路径前缀
            String str = url.substring(0, url.indexOf("u"));

            String bendiUrl = url.substring(str.length() + 1, url.length());
            //拼接本地删除路径
            String defaultBaseDir = Global.getProfile() + "/u" + bendiUrl;
            FileUtils.deleteFile(defaultBaseDir);

            if (type.equals("morphologyName")) {
                cPoisonKnowledgeService.CPoisonKnowledgeUpdateMorphPhotos(id);

            } else if (type.equals("ecologicalMapName")) {
                cPoisonKnowledgeService.CPoisonKnowledgeUpdateEcoloPhotos(id);

            } else if (type.equals("distributionmapName")) {
                cPoisonKnowledgeService.CPoisonKnowledgeUpdateDistrPhotos(id);

            }
            return "200";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }
}