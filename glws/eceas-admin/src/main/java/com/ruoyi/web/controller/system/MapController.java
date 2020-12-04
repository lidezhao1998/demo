package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("system/gis")
public class MapController extends BaseController {
    private String prefix = "system/gis";


    @GetMapping()
    public String gis() {
        return prefix + "/g2";
    }

    }














