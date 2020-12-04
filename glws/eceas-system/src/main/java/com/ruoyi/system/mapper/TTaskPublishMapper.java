package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.TTaskPublish;
import org.apache.ibatis.annotations.Select;

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

    @Select("select dict_code,dict_label from sys_dict_data where dict_type='sys_province'")
    List<SysDictData> getProvinces();





    //@Update("update t_task_publish set Status='1' where PUBLISH_ID = #{publishId}")
    int updateTTaskPublishchehui(TTaskPublish tTaskPublish);



    @Select("select MAX(PUBLISH_ID) from t_task_publish")
    Object getByid();

    TTaskPublish selectTTaskResolveById(Long publishId);

    void updateTTaskResolveStatus(TTaskPublish tTaskPublish);

    void updateTTaskPublishStatus(long publishId);

    int updateTTaskPublishRelease(long publishId);
}
