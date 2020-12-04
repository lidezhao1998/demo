package com.ruoyi.zaihai.workflow.service;

import com.ruoyi.zaihai.workflow.domain.FlowRead;
import java.util.List;

/**
 * 流程Service接口
 * 
 * @author sudongdong
 * @date 2020-07-10
 */
public interface IFlowReadService 
{
    /**
     * 查询流程
     * 
     * @param id 流程ID
     * @return 流程
     */
        FlowRead selectFlowReadById(String id);
    /**
     * 查询流程
     *
     * @param id 流程ID
     * @return 流程
     */
    List<FlowRead> selectFlowReadListRe(String id);


    /**
     * 查询流程列表
     * 
     * @param flowRead 流程
     * @return 流程集合
     */
    List<FlowRead> selectFlowReadList(FlowRead flowRead);

    /**
     * 新增流程
     * 
     * @param flowRead 流程
     * @return 结果
     */
    int insertFlowRead(FlowRead flowRead);

    /**
     * 修改流程
     * 
     * @param flowRead 流程
     * @return 结果
     */
    int updateFlowRead(FlowRead flowRead);

    /**
     * 批量删除流程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteFlowReadByIds(String ids);

    /**
     * 删除流程信息
     * 
     * @param id 流程ID
     * @return 结果
     */
    int deleteFlowReadById(String id);
}
