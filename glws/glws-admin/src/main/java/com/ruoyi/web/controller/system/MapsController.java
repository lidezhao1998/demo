package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("system/gis2")
public class MapsController extends BaseController {
    private String prefix = "system/gis2";


    @GetMapping()
    public String gis() {
        return prefix + "/g1";
    }

}














