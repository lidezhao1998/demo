package com.ruoyi.system.service.gis;

import com.ruoyi.system.domain.gis.SysCity;

import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface ISysCityService {

    HashMap<String, Object> getCityById(Long id);

    List<SysCity> getDistrictSheng();

    HashMap<String, Object> getDistrictShi(Long id);

    HashMap<String, Object> getDistrictQu(Long id);
}