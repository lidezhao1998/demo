package com.ruoyi.zaihai.workflow.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.zaihai.workflow.mapper.FlowWflogMapper;
import com.ruoyi.zaihai.workflow.domain.FlowWflog;
import com.ruoyi.zaihai.workflow.service.IFlowWflogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 审核记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-23
 */
@Service
public class FlowWflogServiceImpl implements IFlowWflogService 
{
    @Autowired
    private FlowWflogMapper flowWflogMapper;

    /**
     * 查询审核记录
     * 
     * @param login 审核记录ID
     * @return 审核记录
     */
    @Override
    public FlowWflog selectFlowWflogById(String login)
    {
        return flowWflogMapper.selectFlowWflogById(login);
    }

    /**
     * 查询审核记录列表
     * 
     * @param flowWflog 审核记录
     * @return 审核记录
     */
    @Override
    public List<FlowWflog> selectFlowWflogList(FlowWflog flowWflog)
    {
        return flowWflogMapper.selectFlowWflogList(flowWflog);
    }

    /**
     * 新增审核记录
     * 
     * @param flowWflog 审核记录
     * @return 结果
     */
    @Override
    public int insertFlowWflog(FlowWflog flowWflog)
    {
        return flowWflogMapper.insertFlowWflog(flowWflog);
    }

    /**
     * 修改审核记录
     * 
     * @param flowWflog 审核记录
     * @return 结果
     */
    @Override
    public int updateFlowWflog(FlowWflog flowWflog)
    {
        return flowWflogMapper.updateFlowWflog(flowWflog);
    }

    /**
     * 删除审核记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFlowWflogByIds(String ids)
    {
        return flowWflogMapper.deleteFlowWflogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除审核记录信息
     * 
     * @param login 审核记录ID
     * @return 结果
     */
    @Override
    public int deleteFlowWflogById(String login)
    {
        return flowWflogMapper.deleteFlowWflogById(login);
    }
}
