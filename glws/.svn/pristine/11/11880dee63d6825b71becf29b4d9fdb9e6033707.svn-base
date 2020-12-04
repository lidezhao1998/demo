package com.ruoyi.zaihai.workflow.mapper;

import com.ruoyi.zaihai.workflow.domain.FlowIdea;
import java.util.List;

/**
 * 流程意见Mapper接口
 * 
 * @author sudogndong
 * @date 2020-07-10
 */
public interface FlowIdeaMapper 
{
    /**
     * 查询流程意见
     * 
     * @param id 流程意见ID
     * @return 流程意见
     */
        FlowIdea selectFlowIdeaById(String id);

    /**
     * 查询流程意见列表
     * 
     * @param flowIdea 流程意见
     * @return 流程意见集合
     */
    List<FlowIdea> selectFlowIdeaList(FlowIdea flowIdea);

    /**
     * 新增流程意见
     * 
     * @param flowIdea 流程意见
     * @return 结果
     */
    int insertFlowIdea(FlowIdea flowIdea);

    /**
     * 修改流程意见
     * 
     * @param flowIdea 流程意见
     * @return 结果
     */
    int updateFlowIdea(FlowIdea flowIdea);

    /**
     * 删除流程意见
     * 
     * @param id 流程意见ID
     * @return 结果
     */
    int deleteFlowIdeaById(String id);

    /**
     * 批量删除流程意见
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteFlowIdeaByIds(String[] ids);
}
