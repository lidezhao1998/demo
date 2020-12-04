package com.ruoyi.zaihai.workflow.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.zaihai.common.utils.GenerateUUID;
import com.ruoyi.zaihai.workflow.domain.FlowWrite;
import com.ruoyi.zaihai.workflow.mapper.FlowWriteMapper;
import com.ruoyi.zaihai.workflow.service.IFlowWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程Service业务层处理
 * 
 * @author sudogndong
 * @date 2020-07-10
 */
@Service
public class FlowWriteServiceImpl implements IFlowWriteService 
{
    @Autowired
    private FlowWriteMapper flowWriteMapper;

    /**
     * 查询流程
     * 
     * @param id 流程ID
     * @return 流程
     */
    @Override
    public FlowWrite selectFlowWriteById(String id)
    {
        return flowWriteMapper.selectFlowWriteById(id);
    }

    /**
     * 查询待审核流程列表
     *
     * @param userid 用户
     * @return 流程集合
     */
    @Override
    public List<FlowWrite> selectFlowWriteListdai(String userid){
        return flowWriteMapper.selectFlowWriteListdai(userid);
    };

    /**
     * 查询流程列表
     * 
     * @param flowWrite 流程
     * @return 流程
     */
    @Override
    public List<FlowWrite> selectFlowWriteList(FlowWrite flowWrite)
    {
        return flowWriteMapper.selectFlowWriteList(flowWrite);
    }

    /**
     * 新增流程
     * 
     * @param flowWrite 流程
     * @return 结果
     */
    @Override
    public int insertFlowWrite(FlowWrite flowWrite)
    {
        flowWrite.setId(GenerateUUID.crUid());
        return flowWriteMapper.insertFlowWrite(flowWrite);
    }

    /**
     * 修改流程
     * 
     * @param flowWrite 流程
     * @return 结果
     */
    @Override
    public int updateFlowWrite(FlowWrite flowWrite)
    {
        return flowWriteMapper.updateFlowWrite(flowWrite);
    }

    /**
     * 删除流程对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFlowWriteByIds(String ids)
    {
        return flowWriteMapper.deleteFlowWriteByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程信息
     * 
     * @param id 流程ID
     * @return 结果
     */
    @Override
    public int deleteFlowWriteById(String id)
    {
        return flowWriteMapper.deleteFlowWriteById(id);
    }

    /**
     * 删除流程待办信息
     *
     * @param recordid 数据ID
     * @param filetypeid 流程ID
     * @return 结果
     */
    @Override
    public  int deleteFlowWriteByreId(String recordid,String filetypeid){
        return flowWriteMapper.deleteFlowWriteByreId(recordid,filetypeid);
    }
    /**
     *  删除单条数据同级所有待审核信息
     *
     * @param id 流程ID
     * @return 结果
     */
    @Override
    public int deleteFlowWriteBylogId(String id)
    {
        return flowWriteMapper.deleteFlowWriteBylogId(id);
    }

}
