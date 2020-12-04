package com.sinosoft.integration.mapper;

import com.ruoyi.common.datasource.DataSource;
import com.sinosoft.integration.domain.MeteorologyData;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 10:42
 * 气象数据
 */
@DataSource("spider")
//@DataSource(value=DataSourceType.SPIDER)
public interface MeteorologyDataMapper {
    /**
     * 查询气象数据
     * @param meteorologyData
     * @return
     */
    MeteorologyData selectMeteorologyData(MeteorologyData meteorologyData);

    /**
     * 查询气象数据列表
     * @param meteorologyData
     * @return
     */
    List<MeteorologyData> selectMeteorologyDataList(MeteorologyData meteorologyData);


    /**
     * 修改气象数据
     */
    int updateMeteorologyData(MeteorologyData meteorologyData);

    /**
     * 新增气象数据
     * @param meteorologyData
     */
    int insertMeteorologyData(MeteorologyData meteorologyData);

    /**
     * 删除
     * @param ids
     * @return
     */
    int deleteMeteorologyDataByIds(String[] ids);
}
