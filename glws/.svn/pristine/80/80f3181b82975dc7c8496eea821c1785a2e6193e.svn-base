package com.sinosoft.web.controller.integration;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.GeoServerUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.UpFile;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.RemoteSensing;
import com.sinosoft.integration.service.RemoteSensingService;
import com.sinosoft.web.properties.GeoServerProperties;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/28 16:28
 * 草原生态数据
 */
@Controller
@RequestMapping("/integration/remoteSensing")
public class RemoteSensingController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(RemoteSensingController.class);
    @Autowired
    GeoServerProperties geoServerProperties;

    @Autowired
    private RemoteSensingService remoteSensingService;
//    @Autowired
//    private ISysShpFileService shpFileService;

    private String prefix = "integration/remoteSensing";

    @GetMapping
    public String remoteSensing() {
        return "integration/remoteSensingList";
    }

    @GetMapping("rsimport")
    public String remoteSensingImp() {
        return "integration/remoteSensingImp";
    }

//    @RequiresPermissions("integration:remoteSensing:view")
//    @GetMapping()
//    public String shpFile() {
//        return prefix + "/shpFile";
//    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RemoteSensing remoteSensing) {
        startPage();
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String xzcode = ShiroUtils.getSysUser().getDept().getXzcode();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("国家级")) {
                remoteSensing.setStatus(1);

            }
        }
        List<RemoteSensing> list = remoteSensingService.selectRemoteSensingList(remoteSensing);
        return getDataTable(list);
    }

    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(RemoteSensing remoteSensing, int id) {
        remoteSensing.setId(id);
        remoteSensing.setStatus(1);
        return toAjax(remoteSensingService.updateRemoteSensing(remoteSensing));
    }

    @Log(title = "栅格数据审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditAll")
    @ResponseBody
    public AjaxResult auditAll(String ids) {
        String[] id = ids.split(",");
        return toAjax(remoteSensingService.updateRemoteSensingByIds(id));
    }

    @Log(title = "栅格数据取消审核", businessType = BusinessType.UPDATE)
    @PostMapping("/unaudit")
    @ResponseBody
    public AjaxResult unaudit(RemoteSensing remoteSensing, int id) {
        remoteSensing.setId(id);
        remoteSensing.setStatus(0);
        return toAjax(remoteSensingService.updateRemoteSensing(remoteSensing));
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") int id, ModelMap mmap) {
        RemoteSensing entity = new RemoteSensing();
        entity.setId(id);
        RemoteSensing result = remoteSensingService.selectRemoteSensing(entity);
        mmap.put("entity", result);
        mmap.put("imgUrl", result.getImgUrl());
        System.out.println("imgurl------" + result.getImgUrl());
        return prefix + "/details";
    }

    @PostMapping("/detail")
    @ResponseBody
    public RemoteSensing showImg(int id) {
        RemoteSensing entity = new RemoteSensing();
        entity.setId(id);
        RemoteSensing result = remoteSensingService.selectRemoteSensing(entity);
        return result;
    }


    /**
     * 新增保存shpFile
     */
    @Log(title = "shpFile管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam(value = "files") MultipartFile[] files, @RequestParam("color") String color) {
        //上传文件
        Map<String, String> map = UpFile.uploadFile(files);
        //返回上传后的路径
        String filePath = map.get("filePath");
        //入库
        int i = remoteSensingService.insertShpFile(filePath, color);

        return toAjax(i);
    }

    /**
     * 新增shpFile
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * @Describe 根据shp id 发布geoServer 服务
     * @Params [id]
     * @Return boolean
     * @DATE 2020/7/21
     * @Author LiRenDong
     */
    public boolean releaseShp(String path, String fileName) {
        boolean b = false;
        try {
            b = GeoServerUtils.GeoserverPublishShapefileData(
                    geoServerProperties.getUrlYml(),
                    geoServerProperties.getUsernameYml(),
                    geoServerProperties.getPasswordYml(),
                    geoServerProperties.getWorkspaceYml(),
                    geoServerProperties.getSrsYml(),
                    path,
                    fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        RemoteSensing remoteSensing = remoteSensingService.selectRemoteSensingById(id);
        mmap.put("shpFile", remoteSensing);
        return prefix + "/edit";
    }
//   /**
//     * 保存修改
//     */
//    @GetMapping("/edit/")
//    public AjaxResult saveEdit(RemoteSensing remoteSensing, ModelMap mmap) {
//        return toAjax(remoteSensingService.saveEditRemoteSensing(remoteSensing));
//    }

    /**
     * 审核通过
     */

    @PostMapping("/examine")
    @ResponseBody
    public AjaxResult examine(Long id, ModelMap mmap) {
        return toAjax(remoteSensingService.examineRemoteSensing(id));
    }

    /**
     * 未审核
     */

    @PostMapping("/unexamine")
    @ResponseBody
    public AjaxResult unexamine(Long id, ModelMap mmap) {
        return toAjax(remoteSensingService.unexamineRemoteSensing(id));
    }

    /**
     * 删除
     */
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id, ModelMap mmap) {
        return toAjax(remoteSensingService.deleteRemoteSensing(id));
    }

    /**
     * 保存
     */
    @Log(title = "shpFile管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("integration:remoteSensing:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated RemoteSensing remoteSensing) {
        return toAjax(remoteSensingService.saveEditRemoteSensing(remoteSensing));
    }


    /**
     * 下载
     */
    @GetMapping("/download/{id}")
    public void fileDownload(@PathVariable("id") int id, HttpServletResponse response, HttpServletRequest request) {
        try {
            String fileName = "";
            RemoteSensing entity = new RemoteSensing();
            entity.setId(id);
            RemoteSensing result = remoteSensingService.selectRemoteSensing(entity);
            fileName = result.getName();
            String imgUrl = result.getImgUrl().replace("/profile", "");
            String realFileName = System.currentTimeMillis() + fileName + imgUrl.substring(imgUrl.lastIndexOf("/"), imgUrl.length());
            String filePath = Global.getProfile() + imgUrl;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    @PostMapping("/gisShow")
    @ResponseBody
    public List<HashMap> gisShow(String url) {
        return remoteSensingService.gisShow(url);
    }

}
