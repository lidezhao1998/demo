package com.sinosoft.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.gis.GrasslandEcology;
import com.ruoyi.system.service.gis.PostGisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/9/18
 */
@Controller
@RequestMapping("system/postGis")
public class PostGisController extends BaseController {
    private String prefix = "system/gis";
    @Autowired
    private PostGisService postGisService;

    @RequestMapping("/getGressTypeList")
    @ResponseBody
    public List<Ztree> getGressTypeList() {
        return postGisService.getSystemGressTypeList();
    }

    @RequestMapping("/getGressList")
    @ResponseBody
    public TableDataInfo getGressList(GrasslandEcology grasslandEcology) {
        startPage();
        return getDataTable(postGisService.getGressList(grasslandEcology));
    }

    @RequestMapping("/getSection")
    @ResponseBody
    public List<GrasslandEcology> getSection() {
        return postGisService.getSection();
    }

    @RequestMapping("/getGeneric")
    @ResponseBody
    public List<GrasslandEcology> getGeneric(String section) {
        return postGisService.getGeneric(section);
    }

    @RequestMapping("/getBundle")
    @ResponseBody
    public List<GrasslandEcology> getBundle(String generic) {
        return postGisService.getBundle(generic);
    }

    @RequestMapping("/getProvince")
    @ResponseBody
    public List<GrasslandEcology> getProvince() {
        return postGisService.getProvince();
    }

    @RequestMapping("/getGrassById")
    public String getGrassById(int id, ModelMap model){
        model.addAttribute("grasslandEcology",postGisService.getGarssById(id));
        return prefix+"/grassEdit";
    }


}
