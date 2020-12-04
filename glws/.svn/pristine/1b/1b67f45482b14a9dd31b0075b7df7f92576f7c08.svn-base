package com.ruoyi.zaihai.workflow.mapper;

import com.ruoyi.zaihai.workflow.domain.FlowWrite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程Mapper接口
 * 
 * @author sudogndong
 * @date 2020-07-10
 */
public interface FlowWriteMapper 
{
    /**
     * 查询流程
     * 
     * @param id 流程ID
     * @return 流程
     */
        FlowWrite selectFlowWriteById(String id);

    /**
     * 查询流程列表
     * 
     * @param flowWrite 流程
     * @return 流程集合
     */
    List<FlowWrite> selectFlowWriteList(FlowWrite flowWrite);
    /**
     * 查询待审核流程列表
     *
     * @param userid 用户
     * @return 流程集合
     */
    List<FlowWrite> selectFlowWriteListdai(String userid);

    /**
     * 新增流程
     * 
     * @param flowWrite 流程
     * @return 结果
     */
    int insertFlowWrite(FlowWrite flowWrite);

    /**
     * 修改流程
     * 
     * @param flowWrite 流程
     * @return 结果
     */
    int updateFlowWrite(FlowWrite flowWrite);

    /**
     * 删除流程
     * 
     * @param id 流程ID
     * @return 结果
     */
    int deleteFlowWriteById(String id);

    /**
     * 批量删除流程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteFlowWriteByIds(String[] ids);
    /**
     * 删除流程
     *
     * @param id 流程ID
     * @return 结果
     */
    int deleteFlowWriteByreId(@Param("recordid") String recordid, @Param("filetypeid") String filetypeid);

    /**
     * 删除单条数据同级所有待审核信息
     *
     * @param id 流程ID
     * @return 结果
     */
    int deleteFlowWriteBylogId(String id);

}
