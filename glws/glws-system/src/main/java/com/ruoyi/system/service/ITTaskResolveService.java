package com.ruoyi.system.service;

import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;

import java.util.List;

/**
 * 退牧还草工程任务领取分解Service接口
 * 
 * @author ruoyi
 * @date 2019-12-16
 */
public interface ITTaskResolveService 
{



    /**
     * 查询退牧还草工程任务领取或进行中的详细内容
     *
     * @param  publishId 退牧还草工程任务领取分解ID
     * @return 退牧还草工程任务领取分解
     */
/*
    List<TTaskResolve> selectTTaskResolveByPublishId(Long publishId);
*/


    /**
     * 查询退牧还草工程任务领取分解列表
     * 
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 退牧还草工程任务领取分解集合
     */
    List<TTaskResolve> selectTTaskResolveList(TTaskResolve tTaskResolve);

    /**
     * 新增退牧还草工程任务领取分解
     * 
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 结果
     */
    int insertTTaskResolve(TTaskResolve tTaskResolve);

    /**
     * 修改退牧还草工程任务领取分解
     * 
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 结果
     */
    int updateTTaskResolve(TTaskResolve tTaskResolve);

    /**
     * 批量删除退牧还草工程任务领取分解
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTTaskResolveByIds(String ids);

    /**
     * 删除退牧还草工程任务领取分解信息
     * 
     * @param resolveId 退牧还草工程任务领取分解ID
     * @return 结果
     */
    int deleteTTaskResolveById(Long resolveId);

    List<TTaskResolve> selectTTaskResolveByPublishId(TTaskPublish tTaskPublish);

    void updateTTaskResolveStatus(TTaskResolve tTaskResolve);

    //void addSaveFenJie(ArrayList<TTaskResolve> obj);
    /**
     * 获取省份
     *
     * @param addrCity 根据市级id获取省份
     * @return 结果
     */
    String selectDictValue(String addrCity);

    /**
     * 查询退牧还草工程任务领取分解
     *
     * @param resolveId 退牧还草工程任务领取分解ID
     * @return 退牧还草工程任务领取分解
     */
    TTaskResolve selectTTaskResolveById(Long resolveId);

    /**
     * 获取所有分解完成的所有任务任务
     *
     * @param publishId 根据发布总任务id查询出分解的id
     * @return 结果
     */
    List<TTaskResolve> selectTTaskResolve(Long publishId);
}
