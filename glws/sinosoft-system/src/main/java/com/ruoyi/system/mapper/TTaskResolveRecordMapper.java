package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskRecord;
import com.ruoyi.system.domain.TTaskResolve;

import java.util.List;

/**
 * 退牧还草工程任务调整记录Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-22
 */
public interface TTaskResolveRecordMapper
{
    /**
     * 查询退牧还草工程任务调整记录
     * 
     * @param recordId 退牧还草工程任务调整记录ID
     * @return 退牧还草工程任务调整记录
     */
        TTaskRecord selectTTaskRecordById(Long recordId);

    /**
     * 查询退牧还草工程任务调整记录列表
     * 
     * @param tTaskRecord 退牧还草工程任务调整记录
     * @return 退牧还草工程任务调整记录集合
     */
    List<TTaskRecord> selectTTaskRecordList(TTaskRecord tTaskRecord);

    /**
     * 新增退牧还草工程任务调整记录
     * 
     * @param tTaskResolve 退牧还草工程任务调整记录
     * @return 结果
     */
    int insertTTaskRecord(TTaskResolve tTaskResolve);

    /**
     * 修改退牧还草工程任务调整记录
     * 
     * @param tTaskRecord 退牧还草工程任务调整记录
     * @return 结果
     */
    int updateTTaskRecord(TTaskRecord tTaskRecord);

    /**
     * 删除退牧还草工程任务调整记录
     * 
     * @param recordId 退牧还草工程任务调整记录ID
     * @return 结果
     */
    int deleteTTaskRecordById(Long recordId);

    /**
     * 批量删除退牧还草工程任务调整记录
     * 
     * @param recordIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTTaskRecordByIds(String[] recordIds);
}
