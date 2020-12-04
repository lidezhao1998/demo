package com.sinosoft.externalInterface.service.impl;

import com.sinosoft.externalInterface.domain.GlmaGisMap;
import com.sinosoft.externalInterface.mapper.GlmaGisMapMapper;
import com.sinosoft.externalInterface.service.GlmaGisMapServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunlei
 * @description
 * @date 2020/11/16/11:12
 */
@Service
public class GlmaGisMapServicImpl implements GlmaGisMapServic {
    @Autowired
    GlmaGisMapMapper glmaGisMapMapper;


    @Override
    public String addSharedGisMap(GlmaGisMap gisMap) {
        return glmaGisMapMapper.addSharedGisMap(gisMap);
    }

    @Override
    public String getGisMapByID(String sharedDataId) {
        GlmaGisMap gisMap= glmaGisMapMapper.getGisMapByID(sharedDataId);
        return null;
    }
}
