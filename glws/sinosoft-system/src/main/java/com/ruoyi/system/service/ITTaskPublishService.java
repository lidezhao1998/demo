package com.ruoyi.system.service;


import com.ruoyi.system.domain.PublishOperateModel;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;

import java.util.HashMap;
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
     * 查询退牧还草工程任务发布
     *
     * @param publishId 退牧还草工程任务发布ID
     * @return 退牧还草工程任务发布
     */
    List<TTaskPublish> selectCityPublishList(TTaskPublish tTaskPublish);

    /**
     * 查询当前登录省份的任务量总和
     *
     * @param tTaskPublish 查询当前登录省份的任务量总和
     * @return 查询当前登录省份的任务量总和
     */
    TTaskPublish selectCityPublish(TTaskPublish tTaskPublish);

    /**
     * 查询省级以分解任务
     *
     * @param tTaskPublish 查询省级以分解任务
     * @return 查询省级以分解任务
     */
    TTaskPublish selectProvinceResolve(TTaskPublish tTaskPublish);

    /**
     * 查询退牧还草工程任务发布列表
     * 
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 退牧还草工程任务发布集合
     */
    List<TTaskPublish> selectTTaskPublishList(TTaskPublish tTaskPublish);

    /**
     * 查询工程资金汇总表
     *
     * @return 查询工程资金汇总表集合
     */
    List<TTaskPublish> selectSummaryList(TTaskPublish tTaskPublish);

    /**
     * 查询全国发布任务总和
     *
     * @return 查询全国发布任务总和
     */
    TTaskPublish selectCountry(TTaskPublish tTaskPublish);

    /**
     * 查询发布任务信息(省级)
     *
     * @return 查询发布任务信息(省级)
     */
    TTaskPublish selectPublishTotalScale(Long resolveId);

    /**
     * 查询长江经济带发布量
     *
     * @return 查询长江经济带发布量
     */
    TTaskPublish selectYangtseRiver(TTaskPublish tTaskPublish);

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
     * 修改省已分解任务
     *
     * @param tTaskResolve 修改省已分解任务
     * @return 结果
     */
    public int updateTTaskPublishCity(TTaskResolve tTaskResolve);

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
    /**
     * 添加分解的市级信息到发布表中
     *
     * @param tTaskResolve 发布任务
     * @return
     */
    void insertTTaskPublishCity(TTaskResolve tTaskResolve);

    /**
     * 省市级任务查询
     *
     * @param tTaskPublish 根据年份，省，市查询
     * @return 结果
     */
    List<TTaskPublish> selectProvince(TTaskPublish tTaskPublish);

    int removePublishTTaskResolveByIds(String ids);

    /**
     * 导入发布数据
     *
     * @param publishList 发布数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    String importPublish(List<PublishOperateModel> publishList, Boolean isUpdateSupport, String operName);

    /**
     * 查询国家级未发布列表
     */
    List<TTaskPublish> selectTTaskPublishCountryList();

    /**
     * 查询国家级未发布列表
     */
    List<TTaskPublish> queryCountryList(String year);

    /**
     * 退牧还草任务完成情况
     */
    List<HashMap> getMissionCompletion();

    /**
     * 查找任务是否已保存或发布
     */
    TTaskPublish publishIsExist(TTaskPublish tTaskPublish);

}
