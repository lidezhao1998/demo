package com.sinosoft.integration.service.impl;

import com.sinosoft.integration.domain.DataSync;
import com.sinosoft.integration.mapper.DataSyncMapper;
import com.sinosoft.integration.service.DataSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/15 15:38
 */
@Service
public class DataSyncServiceImpl implements DataSyncService {

    @Autowired
    DataSyncMapper dataSyncMapper;

    @Override
    public List<DataSync> selectDataSyncList(DataSync dataSync) {
        return dataSyncMapper.selectDataSyncList(dataSync);
    }
}
