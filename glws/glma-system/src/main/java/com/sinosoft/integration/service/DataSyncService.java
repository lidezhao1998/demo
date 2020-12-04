package com.sinosoft.integration.service;

import com.sinosoft.integration.domain.DataSync;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/15 15:37
 */
public interface DataSyncService {
    List<DataSync> selectDataSyncList(DataSync dataSync);
}
