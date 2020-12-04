package com.ruoyi.zaihai.caiji.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.caiji.domain.CTaskTechnology;
import com.ruoyi.zaihai.caiji.service.ICTaskTechnologyService;
import com.ruoyi.zaihai.caiji.util.ResourceUntil;
import com.ruoyi.zaihai.common.domain.CResource;
import com.ruoyi.zaihai.common.service.ICResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 【科技成果Controller
 *
 * @author ruoyi
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/achievements/sselect")
public class CTaskTechnologySelectController extends BaseController
{
    private String prefix = "system/sselect";

    @Autowired
    private ICTaskTechnologyService cTaskTechnologyService;
    @Autowired
    private ICResourceService CResourceService;
    @RequiresPermissions("achievements:sselect:view")
    @GetMapping()
    public String technology()
    {
        return prefix + "/sselect";
    }

    /**
     * 查询科技成果信息列表
     */
    @RequiresPermissions("achievements:sselect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskTechnology cTaskTechnology)
    {
        startPage();

        List<CTaskTechnology> list = cTaskTechnologyService.selectCTaskTechnologyList1(cTaskTechnology);
        /*for (int i = 0; i < list.size(); i++) {
            CTaskTechnology taskTechnology =  list.get(i);
            if(taskTechnology.getStatus()=="0"){

            }

        }*/
        return getDataTable(list);
    }

    /*
     *展开详情信息*/
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        CTaskTechnology cTaskTechnology = cTaskTechnologyService.selectCTaskTechnologyById(id);
        cTaskTechnology.setReportTime(DateUtils.getNowDate());
        if(cTaskTechnology.getStatus().equals("1")){
            cTaskTechnology.setStatus("已上报");
        }else{
            cTaskTechnology.setStatus("未上报");

        }
        mmap.put("cTaskTechnology",cTaskTechnology);
        return prefix + "/detail";
    }

    /**
     * 导出科技成果信息列表
     */
    @RequiresPermissions("achievements:sselect:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CTaskTechnology cTaskTechnology)
    {
        List<CTaskTechnology> list = cTaskTechnologyService.selectCTaskTechnologyList(cTaskTechnology);
        ExcelUtil<CTaskTechnology> util = new ExcelUtil<CTaskTechnology>(CTaskTechnology.class);
        return util.exportExcel(list, "technology");
    }


    /**
     * 政策库附件下载
     */
    @RequiresPermissions("achievements:sselect:download")
    @GetMapping("/download/{fileId}")
    public void resourceDownload(@PathVariable("fileId") Long fileId, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CResource cResource = CResourceService.selectCResourceById(fileId);
        ResourceUntil.download(cResource,request,response);
    }




}
