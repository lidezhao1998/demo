package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.WaterConservation;
import java.util.List;

/**
 * 涵养水源Mapper接口
 * 
 * @author hdp
 * @date 2020-06-22
 */
public interface WaterConservationMapper 
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
     * 删除涵养水源
     * 
     * @param conservationId 涵养水源ID
     * @return 结果
     */
    int deleteWaterConservationById(Long conservationId);

    /**
     * 批量删除涵养水源
     * 
     * @param conservationIds 需要删除的数据ID
     * @return 结果
     */
    int deleteWaterConservationByIds(String[] conservationIds);
}
