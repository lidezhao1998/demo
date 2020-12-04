package com.sinosoft.externalInterface.service.impl;

import com.sinosoft.externalInterface.domain.GlmaGisFeatures;
import com.sinosoft.externalInterface.mapper.GlmaGisFeaturesMapper;
import com.sinosoft.externalInterface.service.GlmaGisFeaturesServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunlei
 * @description 专题树
 * @date 2020/11/16/10:33
 */
@Service
public class GlmaGisFeaturesServicImpl implements GlmaGisFeaturesServic {
    @Autowired
    GlmaGisFeaturesMapper glmaGisFeaturesMapper;
    @Override
    public String addSharedProjectTree(GlmaGisFeatures glmaGisFeatures) {
        return glmaGisFeaturesMapper.addSharedProjectTree(glmaGisFeatures);
    }
}
