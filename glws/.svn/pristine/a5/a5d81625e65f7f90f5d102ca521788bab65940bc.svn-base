package com.sinosoft.web.controller.system;

import com.ruoyi.system.domain.gis.Region;
import com.ruoyi.system.service.gis.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/11/5
 */
@RestController
@RequestMapping("system/region")
public class RegionController {
    @Autowired
    RegionService regionService;

    private final int [] CHANGJIANGARRAYS= {510000,530000,520000,500000,320000,360000,340000,420000,430000,330000,310000};


    @RequestMapping("/getRegionById")
    public Region getRegionById(int id){
        return  regionService.getRegionById(id);
    }

    @RequestMapping("/updateAll")
    public void updateAll(){
        regionService.updateAll();
    }
    @RequestMapping("/province")
    public List<Region> provinceList(){
        return  regionService.provinceList();
    }
    @RequestMapping("/city")
    public List<Region> cityList(int provinceCode){
        List<Region> regions = new ArrayList<>();
        if(provinceCode==99999){
            Arrays.stream(CHANGJIANGARRAYS).forEach(proCode ->{
                regionService.cityList(proCode).stream().forEach(region -> {
                    regions.add(region);
                });
            });
            return regions;
        }
        return  regionService.cityList(provinceCode);
    }
    @RequestMapping("/county")
    public List<Region> countyList(int cityCode){
        return  regionService.countyList(cityCode);
    }
}
