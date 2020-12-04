/*
package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.zaihai.caiji.domain.CDataManagement;
import com.ruoyi.zaihai.caiji.service.ICDataManagementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 防治组织信息查询Controller
 *
 * @author ruoyi
 * @date 2020-05-20
 *//*

@Controller
@RequestMapping("/system/select")
public class CDataManagementSelectController extends BaseController
{
    private String prefix = "caiji/select";

    @Autowired
    private ICDataManagementService cDataManagementService;

    @RequiresPermissions("system:select:view")
    @GetMapping()
    public String select()
    {
        return prefix + "/select";
    }

    */
/**
     * 查询防治组织信息查询列表
     *//*

    @RequiresPermissions("system:select:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CDataManagement cDataManagement)
    {
        startPage();
        List<CDataManagement> list = cDataManagementService.selectCDataManagementList(cDataManagement);
        return getDataTable(list);
    }

    */
/**
     * 导出防治组织信息查询列表
     *//*

    @RequiresPermissions("system:select:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CDataManagement cDataManagement)
    {
        List<CDataManagement> list = cDataManagementService.selectCDataManagementList(cDataManagement);
        ExcelUtil<CDataManagement> util = new ExcelUtil<CDataManagement>(CDataManagement.class);
        return util.exportExcel(list, "select");
    }


}
*/
