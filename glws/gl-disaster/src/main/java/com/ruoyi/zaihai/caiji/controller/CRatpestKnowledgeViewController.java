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
import com.ruoyi.zaihai.caiji.domain.CRatpestKnowledge;
import com.ruoyi.zaihai.caiji.service.ICRatpestKnowledgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 草原鼠虫害知识库信息Controller
 *
 * @author ruoyi
 * @date 2020-05-07
 */
@Controller
@RequestMapping("/library/RatpestknowledgeView")
public class CRatpestKnowledgeViewController extends BaseController
{
    private String prefix = "system/Ratpestknowledge";


    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ICRatpestKnowledgeService cRatpestKnowledgeService;

    @RequiresPermissions("library:RatpestknowledgeView:view")
    @GetMapping()
    public String knowledge()
    {
        return prefix + "/RatpestknowledgeView";
    }

    /**
     * 查询草原鼠虫害知识库信息列表
     */
    @RequiresPermissions("library:RatpestknowledgeView:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CRatpestKnowledge cRatpestKnowledge)
    {
        startPage();
        List<CRatpestKnowledge> list = cRatpestKnowledgeService.selectCRatpestKnowledgeList(cRatpestKnowledge);
        return getDataTable(list);
    }

    /**
     * 导出草原鼠虫害知识库信息列表
     */
    @RequiresPermissions("library:RatpestknowledgeView:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CRatpestKnowledge cRatpestKnowledge)
    {
        List<CRatpestKnowledge> list = cRatpestKnowledgeService.selectCRatpestKnowledgeList(cRatpestKnowledge);
        ExcelUtil<CRatpestKnowledge> util = new ExcelUtil<CRatpestKnowledge>(CRatpestKnowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 新增草原鼠虫害知识库信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存草原鼠虫害知识库信息
     */
    @RequiresPermissions("library:RatpestknowledgeView:add")
    @Log(title = "草原鼠虫害知识库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3,@RequestParam("file4") MultipartFile file4,@RequestParam("file5") MultipartFile file5,@RequestParam("file6") MultipartFile file6,CRatpestKnowledge cRatpestKnowledge)
    {
        try
        {
            if(file!=null){
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setSixPhotos(url);
            }
            if(file2!=null){
                //成虫模式图
                String filePath2 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName2 = FileUploadUtils.upload(filePath2, file2);
                String url2 = serverConfig.getUrl() + fileName2;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setAdultPhotos(url2);
            }


            if(file3!=null){
                //蛹的模式图及照片
                String filePath3 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName3 = FileUploadUtils.upload(filePath3, file3);
                String url3 = serverConfig.getUrl() + fileName3;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setPupaPhotos(url3);
            }


            if(file4!=null) {
                //危害照片
                String filePath4 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName4 = FileUploadUtils.upload(filePath4, file4);
                String url4 = serverConfig.getUrl() + fileName4;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setHazardPhotos(url4);
            }

            //全国图
            String filePath5 = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName5 = FileUploadUtils.upload(filePath5, file5);
            String url5 = serverConfig.getUrl() + fileName5;
            // AjaxResult ajax = AjaxResult.success();
            cRatpestKnowledge.setNationalMap(url5);

            //分省图
            String filePath6 = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName6 = FileUploadUtils.upload(filePath6, file6);
            String url6 = serverConfig.getUrl() + fileName6;
            // AjaxResult ajax = AjaxResult.success();
            cRatpestKnowledge.setProvincialMap(url6);

            return toAjax(cRatpestKnowledgeService.insertCRatpestKnowledge(cRatpestKnowledge));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改草原鼠虫害知识库信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CRatpestKnowledge cRatpestKnowledge = cRatpestKnowledgeService.selectCRatpestKnowledgeById(id);
        mmap.put("cRatpestKnowledge", cRatpestKnowledge);
        return prefix + "/edit";
    }

    /**
     * 修改保存草原鼠虫害知识库信息
     */
    @RequiresPermissions("library:RatpestknowledgeView:edit")
    @Log(title = "草原鼠虫害知识库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3,@RequestParam("file4") MultipartFile file4,@RequestParam("file5") MultipartFile file5,@RequestParam("file6") MultipartFile file6,CRatpestKnowledge cRatpestKnowledge)
    {
        try
        {
            if(file!=null){
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setSixPhotos(url);
            }
            if(file2!=null){
                //成虫模式图
                String filePath2 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName2 = FileUploadUtils.upload(filePath2, file2);
                String url2 = serverConfig.getUrl() + fileName2;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setAdultPhotos(url2);
            }


            if(file3!=null){
                //蛹的模式图及照片
                String filePath3 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName3 = FileUploadUtils.upload(filePath3, file3);
                String url3 = serverConfig.getUrl() + fileName3;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setPupaPhotos(url3);
            }


            if(file4!=null) {
                //危害照片
                String filePath4 = Global.getUploadPath();
                // 上传并返回新文件名称
                String fileName4 = FileUploadUtils.upload(filePath4, file4);
                String url4 = serverConfig.getUrl() + fileName4;
                // AjaxResult ajax = AjaxResult.success();
                cRatpestKnowledge.setHazardPhotos(url4);
            }

            //全国图
            String filePath5 = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName5 = FileUploadUtils.upload(filePath5, file5);
            String url5 = serverConfig.getUrl() + fileName5;
            // AjaxResult ajax = AjaxResult.success();
            cRatpestKnowledge.setNationalMap(url5);

            //分省图
            String filePath6 = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName6 = FileUploadUtils.upload(filePath6, file6);
            String url6 = serverConfig.getUrl() + fileName6;
            // AjaxResult ajax = AjaxResult.success();
            cRatpestKnowledge.setProvincialMap(url6);

            return toAjax(cRatpestKnowledgeService.updateCRatpestKnowledge(cRatpestKnowledge));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 删除草原鼠虫害知识库信息
     */
    @RequiresPermissions("library:RatpestknowledgeView:remove")
    @Log(title = "草原鼠虫害知识库信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cRatpestKnowledgeService.deleteCRatpestKnowledgeByIds(ids));
    }


    /**
     * 图片上传测试
     */
    @ResponseBody
    @RequestMapping("upload")
    public Map upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\mycode\\LayUiTest\\src\\main\\resources\\static\\images\\" + dateStr+"\\"+uuid+"." + prefix;


                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }


    /**
     * 查看鼠害知识库
     */
    @RequiresPermissions("library:RatpestknowledgeView:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap, Model model, CRatpestKnowledge cRatpestKnowledge)
    {
        //  List<TTaskResolve> page= taskResolveService.selectTTaskResolveByPublishId(publishId);
        CRatpestKnowledge cDiseaseKnowled = cRatpestKnowledgeService.selectCRatpestKnowledgeById(id);
        mmap.put("cRatpestKnowledge", cDiseaseKnowled);

        return prefix + "/look";
    }
}
