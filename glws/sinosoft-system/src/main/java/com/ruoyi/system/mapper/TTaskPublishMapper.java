package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * 退牧还草工程任务发布Mapper接口
 * 
 * @author feiyanxu
 * @date 2019-11-28
 */
public interface TTaskPublishMapper 
{
    /**
     * 查询退牧还草工程任务发布
     * 
     * @param publishId 退牧还草工程任务发布ID
     * @return 退牧还草工程任务发布
     */
    TTaskPublish selectTTaskPublishById(Long publishId);

    /**
     * 查询退牧还草工程任务发布列表
     * 
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 退牧还草工程任务发布集合
     */
    List<TTaskPublish> selectTTaskPublishList(TTaskPublish tTaskPublish);

    /**
     * 查询退牧还草工程任务发布列表
     *
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 退牧还草工程任务发布集合
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
     * 删除退牧还草工程任务发布
     * 
     * @param publishId 退牧还草工程任务发布ID
     * @return 结果
     */
    public int deleteTTaskPublishById(Long publishId);

    /**
     * 批量删除退牧还草工程任务发布
     * 
     * @param publishIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTaskPublishByIds(String[] publishIds);

    /**
     * 根据编码查出省名称
     *
     * @param
     * @return 结果
     */
    @Select("select dict_code,dict_label from sys_dict_data where dict_type='sys_province'")
    List<SysDictData> getProvinces();

    /**
     * 撤回发布信息操作
     *
     * @param tTaskPublish
     * @return 结果
     */
    int updateTTaskPublishchehui(TTaskPublish tTaskPublish);



    @Select("select MAX(PUBLISH_ID) from t_task_publish")
    Object getByid();

    /**
     * 修改退牧还草工程任务发布信息
     *
     * @param publishId 撤回退牧还草工程任务发布
     * @return 结果
     */
    TTaskPublish selectTTaskResolveById(Long publishId);

    void updateTTaskResolveStatus(TTaskPublish tTaskPublish);


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
     * @param publishId 退牧还草工程任务发布
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

    /**
     * 横向全国任务查询
     *
     * @param tTaskPublish 根据年份查询
     * @return 结果
     */
    TTaskPublish selectCountryH(TTaskPublish tTaskPublish);

    /**
     * 纵向全国任务查询
     *
     * @param tTaskPublish 根据年份查询
     * @return 结果
     */
    List<TTaskPublish> selectCountryZ(TTaskPublish tTaskPublish);

    /**
     * 全省任务查询
     *
     * @param tTaskPublish 根据年份，省，市查询
     * @return 结果
     */
    TTaskPublish selectProvinceH(TTaskPublish tTaskPublish);

    int removePublishTTaskResolveByIds(String[] toStrArray);

    /**
     * 查询国家级未发布列表
     */
    List<TTaskPublish> selectTTaskPublishCountryList();

    /**
     * 查询国家级未发布列表
     */
    List<TTaskPublish> queryCountryList(String year);

    /**
     * 查找任务是否已保存或发布
     */
    TTaskPublish publishIsExist(TTaskPublish tTaskPublish);

}
