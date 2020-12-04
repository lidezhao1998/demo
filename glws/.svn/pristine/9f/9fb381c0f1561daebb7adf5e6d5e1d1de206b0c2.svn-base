package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.mapper.TTaskPublishMapper;
import com.ruoyi.system.service.ITTaskPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退牧还草工程任务发布Service业务层处理
 *
 * @author feiyanxu
 * @date 2019-11-28
 */
@Service
public class TTaskPublishServiceImpl implements ITTaskPublishService
{
    @Autowired
    private TTaskPublishMapper tTaskPublishMapper;

    /**
     * 查询退牧还草工程任务发布
     *
     * @param publishId 退牧还草工程任务发布ID
     * @return 退牧还草工程任务发布
     */
    @Override
    public TTaskPublish selectTTaskPublishById(Long publishId)
    {
        return tTaskPublishMapper.selectTTaskPublishById(publishId);
    }

    /**
     * 查询退牧还草工程任务发布列表
     *
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 退牧还草工程任务发布
     */
    @Override
    public List<TTaskPublish> selectTTaskPublishList(TTaskPublish tTaskPublish)
    {
        return tTaskPublishMapper.selectTTaskPublishList(tTaskPublish);
    }

    /**
     * 查询工程资金汇总表
     *
     * @return 查询工程资金汇总表集合
     */
    @Override
    public List<TTaskPublish> selectSummaryList(TTaskPublish tTaskPublish){
        return tTaskPublishMapper.selectSummaryList(tTaskPublish);
    }

    /**
     * 新增退牧还草工程任务发布
     *
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 结果
     */
    @Override
    public int insertTTaskPublish(TTaskPublish tTaskPublish)
    {
        tTaskPublish.setCreateTime(DateUtils.getNowDate());
        return tTaskPublishMapper.insertTTaskPublish(tTaskPublish);
    }

    /**
     * 修改退牧还草工程任务发布
     *
     * @param tTaskPublish 退牧还草工程任务发布
     * @return 结果
     */
    @Override
    public int updateTTaskPublish(TTaskPublish tTaskPublish)
    {
        tTaskPublish.setUpdateTime(DateUtils.getNowDate());
        return tTaskPublishMapper.updateTTaskPublish(tTaskPublish);
    }

    /**
     * 删除退牧还草工程任务发布对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTTaskPublishByIds(String ids)
    {
        return tTaskPublishMapper.deleteTTaskPublishByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退牧还草工程任务发布信息
     *
     * @param publishId 退牧还草工程任务发布ID
     * @return 结果
     */
    @Override
    public int deleteTTaskPublishById(Long publishId)
    {
        return tTaskPublishMapper.deleteTTaskPublishById(publishId);
    }



    @Override
    public int updateTTaskPublishchehui(TTaskPublish tTaskPublish) {
        return tTaskPublishMapper.updateTTaskPublishchehui(tTaskPublish);
    }


    //查询数据库中最大的id
    @Override
    public Object getByid() {
        return tTaskPublishMapper.getByid();
    }

    @Override
    public void updateTTaskResolveStatus(TTaskPublish tTaskPublish) {
        tTaskPublishMapper.updateTTaskResolveStatus(tTaskPublish);

    }

    /**
     * 修改退牧还草工程任务发布信息
     *
     * @param publishId 撤回退牧还草工程任务发布
     * @return 结果
     */
    @Override
    public TTaskPublish selectTTaskResolveById(Long publishId) {
        return tTaskPublishMapper.selectTTaskResolveById(publishId);
    }
    /**
     * 查询退牧还草工程任务领取分解修改状态
     *
     * @param publishId 退牧还草工程任务领取分解ID
     * @return 返回状态
     */
    @Override
    public void updateTTaskPublishStatus(long publishId) {
        tTaskPublishMapper.updateTTaskPublishStatus(publishId);
    }

    /**
     * 查询退牧还草工程任务领取分解修改状态
     *
     * @param publishId 退牧还草工程任务发布
     * @return 返回状态
     */
    @Override
    public int updateTTaskPublishRelease(long publishId) {
        return tTaskPublishMapper.updateTTaskPublishRelease(publishId);
    }



  /*  @Override
    public Object selectTTaskPublishLookById(int publishId) {
        return tTaskPublishMapper.selectTTaskPublishLookById(publishId);
    }*/
}
