package com.ruoyi.zaihai.workflow.mapper;

import com.ruoyi.zaihai.workflow.domain.FlowWflog;
import java.util.List;

/**
 * 审核记录Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-23
 */
public interface FlowWflogMapper 
{
    /**
     * 查询审核记录
     * 
     * @param login 审核记录ID
     * @return 审核记录
     */
        FlowWflog selectFlowWflogById(String login);

    /**
     * 查询审核记录列表
     * 
     * @param flowWflog 审核记录
     * @return 审核记录集合
     */
    List<FlowWflog> selectFlowWflogList(FlowWflog flowWflog);

    /**
     * 新增审核记录
     * 
     * @param flowWflog 审核记录
     * @return 结果
     */
    int insertFlowWflog(FlowWflog flowWflog);

    /**
     * 修改审核记录
     * 
     * @param flowWflog 审核记录
     * @return 结果
     */
    int updateFlowWflog(FlowWflog flowWflog);

    /**
     * 删除审核记录
     * 
     * @param login 审核记录ID
     * @return 结果
     */
    int deleteFlowWflogById(String login);

    /**
     * 批量删除审核记录
     * 
     * @param logins 需要删除的数据ID
     * @return 结果
     */
    int deleteFlowWflogByIds(String[] logins);
}
