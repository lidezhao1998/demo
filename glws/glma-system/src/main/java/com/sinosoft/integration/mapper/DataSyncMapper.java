package com.sinosoft.integration.mapper;

import com.sinosoft.integration.domain.DataSync;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/15 15:36
 * 数据同步记录
 */
public interface DataSyncMapper {
    List<DataSync> selectDataSyncList(DataSync dataSync);
}
