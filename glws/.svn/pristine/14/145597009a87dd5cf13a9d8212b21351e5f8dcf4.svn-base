package com.ruoyi.zaihai.workflow.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.zaihai.workflow.domain.FlowRead;
import com.ruoyi.zaihai.workflow.mapper.FlowReadMapper;
import com.ruoyi.zaihai.workflow.service.IFlowReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程Service业务层处理
 * 
 * @author sudongdong
 * @date 2020-07-10
 */
@Service
public class FlowReadServiceImpl implements IFlowReadService 
{
    @Autowired
    private FlowReadMapper flowReadMapper;

    /**
     * 查询流程
     * 
     * @param id 流程ID
     * @return 流程
     */
    @Override
    public FlowRead selectFlowReadById(String id)
    {
        return flowReadMapper.selectFlowReadById(id);
    }

    /**
     * 查询流程列表
     * 
     * @param flowRead 流程
     * @return 流程
     */
    @Override
    public List<FlowRead> selectFlowReadList(FlowRead flowRead)
    {
        return flowReadMapper.selectFlowReadList(flowRead);
    }
    /**
     * 查询已审核数据列表
     *
     * @param useid 用户ID
     * @return 流程
     */
    @Override
    public List<FlowRead> selectFlowReadListRe(String useid)
    {
        return flowReadMapper.selectFlowReadByIdRe( useid);
    }

    /**
     * 新增流程
     * 
     * @param flowRead 流程
     * @return 结果
     */
    @Override
    public int insertFlowRead(FlowRead flowRead)
    {
        return flowReadMapper.insertFlowRead(flowRead);
    }

    /**
     * 修改流程
     * 
     * @param flowRead 流程
     * @return 结果
     */
    @Override
    public int updateFlowRead(FlowRead flowRead)
    {
        return flowReadMapper.updateFlowRead(flowRead);
    }

    /**
     * 删除流程对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFlowReadByIds(String ids)
    {
        return flowReadMapper.deleteFlowReadByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程信息
     * 
     * @param id 流程ID
     * @return 结果
     */
    @Override
    public int deleteFlowReadById(String id)
    {
        return flowReadMapper.deleteFlowReadById(id);
    }
}
