package com.ruoyi.system.service;

import com.ruoyi.system.domain.WaterConservation;
import java.util.List;

/**
 * 涵养水源Service接口
 * 
 * @author hdp
 * @date 2020-06-22
 */
public interface IWaterConservationService 
{
    /**
     * 查询涵养水源
     * 
     * @param conservationId 涵养水源ID
     * @return 涵养水源
     */
        WaterConservation selectWaterConservationById(Long conservationId);

    /**
     * 查询涵养水源列表
     * 
     * @param waterConservation 涵养水源
     * @return 涵养水源集合
     */
    List<WaterConservation> selectWaterConservationList(WaterConservation waterConservation);

    /**
     * 新增涵养水源
     * 
     * @param waterConservation 涵养水源
     * @return 结果
     */
    int insertWaterConservation(WaterConservation waterConservation);

    /**
     * 修改涵养水源
     * 
     * @param waterConservation 涵养水源
     * @return 结果
     */
    int updateWaterConservation(WaterConservation waterConservation);

    /**
     * 批量删除涵养水源
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWaterConservationByIds(String ids);

    /**
     * 删除涵养水源信息
     * 
     * @param conservationId 涵养水源ID
     * @return 结果
     */
    int deleteWaterConservationById(Long conservationId);
}
