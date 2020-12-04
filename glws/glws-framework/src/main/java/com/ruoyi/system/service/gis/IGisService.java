package com.ruoyi.system.service.gis;

import com.ruoyi.system.domain.gis.GisFeatures;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface IGisService {

    List<GisFeatures> getShpIds(String shpIds);
}