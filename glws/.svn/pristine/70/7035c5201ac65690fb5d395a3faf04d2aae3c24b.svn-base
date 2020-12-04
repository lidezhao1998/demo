package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WaterConservationMapper;
import com.ruoyi.system.domain.WaterConservation;
import com.ruoyi.system.service.IWaterConservationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 涵养水源Service业务层处理
 * 
 * @author hdp
 * @date 2020-06-22
 */
@Service
public class WaterConservationServiceImpl implements IWaterConservationService 
{
    @Autowired
    private WaterConservationMapper waterConservationMapper;

    /**
     * 查询涵养水源
     * 
     * @param conservationId 涵养水源ID
     * @return 涵养水源
     */
    @Override
    public WaterConservation selectWaterConservationById(Long conservationId)
    {
        return waterConservationMapper.selectWaterConservationById(conservationId);
    }

    /**
     * 查询涵养水源列表
     * 
     * @param waterConservation 涵养水源
     * @return 涵养水源
     */
    @Override
    public List<WaterConservation> selectWaterConservationList(WaterConservation waterConservation)
    {
        return waterConservationMapper.selectWaterConservationList(waterConservation);
    }

    /**
     * 新增涵养水源
     * 
     * @param waterConservation 涵养水源
     * @return 结果
     */
    @Override
    public int insertWaterConservation(WaterConservation waterConservation)
    {
        return waterConservationMapper.insertWaterConservation(waterConservation);
    }

    /**
     * 修改涵养水源
     * 
     * @param waterConservation 涵养水源
     * @return 结果
     */
    @Override
    public int updateWaterConservation(WaterConservation waterConservation)
    {
        return waterConservationMapper.updateWaterConservation(waterConservation);
    }

    /**
     * 删除涵养水源对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWaterConservationByIds(String ids)
    {
        return waterConservationMapper.deleteWaterConservationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除涵养水源信息
     * 
     * @param conservationId 涵养水源ID
     * @return 结果
     */
    @Override
    public int deleteWaterConservationById(Long conservationId)
    {
        return waterConservationMapper.deleteWaterConservationById(conservationId);
    }
}
