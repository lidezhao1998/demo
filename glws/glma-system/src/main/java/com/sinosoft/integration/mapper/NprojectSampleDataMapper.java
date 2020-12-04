package com.sinosoft.integration.mapper;

import com.sinosoft.integration.domain.NprojectSampleData;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 10:42
 * 非工程样地数据表
 */
public interface NprojectSampleDataMapper {
    /**
     * 查询非工程样地详细信息
     * @param nprojectSampleData
     * @return
     */
    NprojectSampleData selectNprojectSampleData(NprojectSampleData nprojectSampleData);

    /**
     * 查询非工程样地列表
     * @param nprojectSampleData
     * @return
     */
    List<NprojectSampleData> selectNprojectSampleDataList(NprojectSampleData nprojectSampleData);


    /**
     * 修改非工程样地
     */
    int updateNprojectSampleData(NprojectSampleData nprojectSampleData);

    /**
     * 新增返非工程样地
     * @param nprojectSampleData
     */
    int insertNprojectSampleData(NprojectSampleData nprojectSampleData);

    /**
     * 删除
     * @param ids
     * @return
     */
    int deleteNprojectSampleDataByIds(String[] ids);

    int updateStatusByIds(String[] ids);
}
