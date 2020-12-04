package com.sinosoft.integration.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.sinosoft.integration.domain.MeteorologyData;
import com.sinosoft.integration.mapper.MeteorologyDataMapper;
import com.sinosoft.integration.service.MeteorologyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/11 10:12
 */
@Service
public class MeteorologyDataServiceImpl implements MeteorologyDataService {
    @Autowired
    MeteorologyDataMapper meteorologyDataMapper;
    @Override
    public MeteorologyData selectMeteorologyData(MeteorologyData meteorologyData) {
        return meteorologyDataMapper.selectMeteorologyData(meteorologyData);
    }

    @Override
    public List<MeteorologyData> selectMeteorologyDataList(MeteorologyData meteorologyData) {
        return meteorologyDataMapper.selectMeteorologyDataList(meteorologyData);
    }

    @Override
    public int deleteMeteorologyDataByIds(String ids) {
        return meteorologyDataMapper.deleteMeteorologyDataByIds(Convert.toStrArray(ids));
    }
}
