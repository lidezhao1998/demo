package com.sinosoft.common.mapper;

import com.ruoyi.system.domain.gis.GisFeatures;

import java.util.List;

/**
 * @author sunlei
 * @description glma 公共类方法
 * @date 2020/11/10/18:03
 */
public interface CommonMapper {


    List<GisFeatures> selectFeaturesTree();

}
