package com.ruoyi.system.service;


import com.ruoyi.system.domain.TTaskPublish;

import java.util.List;

/**
 * 退牧还草工程任务发布Service接口
 * 
 * @author feiyanxu
 * @date 2019-11-28
 */
public interface ITTaskPublishService 
{
    /**
     * 查询退牧还草工程任务发布
     * 
     * @param publishId 退牧还草工程任务发布ID
     * @return 退牧还草工程任务发布
     */
    public TTaskPublish selectTTaskPublishById(Long publishId);

    /**
     * 查询退牧还草工程任务发布列表
     * 
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 退牧还草工程任务发布集合
     */
    public List<TTaskPublish> selectTTaskPublishList(TTaskPublish tTaskPublish);

    /**
     * 查询工程资金汇总表
     *
     * @return 查询工程资金汇总表集合
     */
    List<TTaskPublish> selectSummaryList(TTaskPublish tTaskPublish);

    /**
     * 新增退牧还草工程任务发布
     * 
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 结果
     */
    public int insertTTaskPublish(TTaskPublish tTaskPublish);

    /**
     * 修改退牧还草工程任务发布
     * 
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 结果
     */
    public int updateTTaskPublish(TTaskPublish tTaskPublish);

    /**
     * 批量删除退牧还草工程任务发布
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTaskPublishByIds(String ids);

    /**
     * 删除退牧还草工程任务发布信息
     * 
     * @param publishId 退牧还草工程任务发布ID
     * @return 结果
     */
    public int deleteTTaskPublishById(Long publishId);

    /**
     * 撤回发布信息
     *
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 结果
     */
    int updateTTaskPublishchehui(TTaskPublish tTaskPublish);


    Object getByid();


    //修改状态
    void updateTTaskResolveStatus(TTaskPublish tTaskPublish);

    /**
     * 查询退牧还草工程任务领取分解
     *
     * @param publishId 退牧还草工程任务领取分解ID
     * @return 退牧还草工程任务领取分解
     */
    TTaskPublish selectTTaskResolveById(Long publishId);

    /**
     * 查询退牧还草工程任务领取分解修改状态
     *
     * @param publishId 退牧还草工程任务领取分解ID
     * @return 返回状态
     */
    void updateTTaskPublishStatus(long publishId);

    /**
     * 查询退牧还草工程任务领取分解修改状态
     *
     * @param publishId 发布任务
     * @return 返回状态
     */
    int updateTTaskPublishRelease(long publishId);
}
