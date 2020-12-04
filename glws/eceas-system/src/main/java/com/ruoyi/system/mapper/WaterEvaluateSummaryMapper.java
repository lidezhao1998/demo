package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.WaterEvaluateSummary;
import java.util.List;

/**
 * 涵养水源功能评估数据汇总Mapper接口
 * 
 * @author hdp
 * @date 2020-09-15
 */
public interface WaterEvaluateSummaryMapper 
{
    /**
     * 查询涵养水源功能评估数据汇总
     * 
     * @param id 涵养水源功能评估数据汇总ID
     * @return 涵养水源功能评估数据汇总
     */
        WaterEvaluateSummary selectWaterEvaluateSummaryById(Long id);

    /**
     * 查询涵养水源功能评估数据汇总列表
     * 
     * @param waterEvaluateSummary 涵养水源功能评估数据汇总
     * @return 涵养水源功能评估数据汇总集合
     */
    List<WaterEvaluateSummary> selectWaterEvaluateSummaryList(WaterEvaluateSummary waterEvaluateSummary);

    /**
     * 新增涵养水源功能评估数据汇总
     * 
     * @param waterEvaluateSummary 涵养水源功能评估数据汇总
     * @return 结果
     */
    int insertWaterEvaluateSummary(WaterEvaluateSummary waterEvaluateSummary);

    /**
     * 修改涵养水源功能评估数据汇总
     * 
     * @param waterEvaluateSummary 涵养水源功能评估数据汇总
     * @return 结果
     */
    int updateWaterEvaluateSummary(WaterEvaluateSummary waterEvaluateSummary);

    /**
     * 删除涵养水源功能评估数据汇总
     * 
     * @param id 涵养水源功能评估数据汇总ID
     * @return 结果
     */
    int deleteWaterEvaluateSummaryById(Long id);

    /**
     * 批量删除涵养水源功能评估数据汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWaterEvaluateSummaryByIds(String[] ids);
}
