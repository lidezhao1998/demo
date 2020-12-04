package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WaterEvaluateSummaryMapper;
import com.ruoyi.system.domain.WaterEvaluateSummary;
import com.ruoyi.system.service.IWaterEvaluateSummaryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 涵养水源功能评估数据汇总Service业务层处理
 * 
 * @author hdp
 * @date 2020-09-15
 */
@Service
public class WaterEvaluateSummaryServiceImpl implements IWaterEvaluateSummaryService 
{
    @Autowired
    private WaterEvaluateSummaryMapper waterEvaluateSummaryMapper;

    /**
     * 查询涵养水源功能评估数据汇总
     * 
     * @param id 涵养水源功能评估数据汇总ID
     * @return 涵养水源功能评估数据汇总
     */
    @Override
    public WaterEvaluateSummary selectWaterEvaluateSummaryById(Long id)
    {
        return waterEvaluateSummaryMapper.selectWaterEvaluateSummaryById(id);
    }

    /**
     * 查询涵养水源功能评估数据汇总列表
     * 
     * @param waterEvaluateSummary 涵养水源功能评估数据汇总
     * @return 涵养水源功能评估数据汇总
     */
    @Override
    public List<WaterEvaluateSummary> selectWaterEvaluateSummaryList(WaterEvaluateSummary waterEvaluateSummary)
    {
        return waterEvaluateSummaryMapper.selectWaterEvaluateSummaryList(waterEvaluateSummary);
    }

    /**
     * 新增涵养水源功能评估数据汇总
     * 
     * @param waterEvaluateSummary 涵养水源功能评估数据汇总
     * @return 结果
     */
    @Override
    public int insertWaterEvaluateSummary(WaterEvaluateSummary waterEvaluateSummary)
    {
        return waterEvaluateSummaryMapper.insertWaterEvaluateSummary(waterEvaluateSummary);
    }

    /**
     * 修改涵养水源功能评估数据汇总
     * 
     * @param waterEvaluateSummary 涵养水源功能评估数据汇总
     * @return 结果
     */
    @Override
    public int updateWaterEvaluateSummary(WaterEvaluateSummary waterEvaluateSummary)
    {
        return waterEvaluateSummaryMapper.updateWaterEvaluateSummary(waterEvaluateSummary);
    }

    /**
     * 删除涵养水源功能评估数据汇总对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWaterEvaluateSummaryByIds(String ids)
    {
        return waterEvaluateSummaryMapper.deleteWaterEvaluateSummaryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除涵养水源功能评估数据汇总信息
     * 
     * @param id 涵养水源功能评估数据汇总ID
     * @return 结果
     */
    @Override
    public int deleteWaterEvaluateSummaryById(Long id)
    {
        return waterEvaluateSummaryMapper.deleteWaterEvaluateSummaryById(id);
    }
}
