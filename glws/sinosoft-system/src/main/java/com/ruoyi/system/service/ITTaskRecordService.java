package com.ruoyi.system.service;

import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskRecord;
import java.util.List;

/**
 * 退牧还草工程任务调整记录Service接口
 * 
 * @author liuhongfei
 * @date 2020-09-22
 */
public interface ITTaskRecordService 
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
     * @param tTaskPublish 退牧还草工程任务调整记录
     * @return 结果
     */
    int insertTTaskRecord(TTaskPublish tTaskPublish);

    /**
     * 修改退牧还草工程任务调整记录
     * 
     * @param tTaskRecord 退牧还草工程任务调整记录
     * @return 结果
     */
    int updateTTaskRecord(TTaskRecord tTaskRecord);

    /**
     * 批量删除退牧还草工程任务调整记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTTaskRecordByIds(String ids);

    /**
     * 删除退牧还草工程任务调整记录信息
     * 
     * @param recordId 退牧还草工程任务调整记录ID
     * @return 结果
     */
    int deleteTTaskRecordById(Long recordId);
}
