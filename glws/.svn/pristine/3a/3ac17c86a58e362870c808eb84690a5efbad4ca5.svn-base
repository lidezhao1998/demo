package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.mapper.TTaskResolveMapper;
import com.ruoyi.system.service.ITTaskResolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退牧还草工程任务领取分解Service业务层处理
 *
 * @author ruoyi
 * @date 2019-12-16
 */
@Service
public class TTaskResolveServiceImpl implements ITTaskResolveService
{
    @Autowired
    private TTaskResolveMapper tTaskResolveMapper;




    /**
     * 查询退牧还草工程任务领取分解
     *
     * @param resolveId 退牧还草工程任务领取分解ID
     * @return 退牧还草工程任务领取分解
     */
    @Override
    public TTaskResolve selectTTaskResolveById(Long resolveId)
    {
        return tTaskResolveMapper.selectTTaskResolveById(resolveId);
    }
    /**
     * 查询退牧还草工程任务领取分解得所有任务
     *
     * @param publishId 退牧还草工程任务领取分解ID
     * @return 退牧还草工程任务领取分解
     */
    @Override
    public List<TTaskResolve> selectTTaskResolve(Long publishId) {
        return tTaskResolveMapper.selectTTaskResolve(publishId);
    }


    /**
     * 查询退牧还草工程任务领取分解列表
     *
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 退牧还草工程任务领取分解
     */
    @Override
    public List<TTaskResolve> selectTTaskResolveList(TTaskResolve tTaskResolve)
    {
        return tTaskResolveMapper.selectTTaskResolveList(tTaskResolve);
    }

    /**
     * 查询市级以分解任务
     *
     * @param tTaskResolve 查询市级以分解任务
     * @return 查询市级以分解任务
     */
    @Override
    public TTaskResolve selectCityResolve(TTaskResolve tTaskResolve){
        return tTaskResolveMapper.selectCityResolve(tTaskResolve);
    }

    /**
     * 新增退牧还草工程任务领取分解
     *
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 结果
     */
    @Override
    public int insertTTaskResolve(TTaskResolve tTaskResolve)
    {
        tTaskResolve.setCreateTime(DateUtils.getNowDate());
        return tTaskResolveMapper.insertTTaskResolve(tTaskResolve);
    }

    /**
     * 修改退牧还草工程任务领取分解
     *
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 结果
     */
    @Override
    public int updateTTaskResolve(TTaskResolve tTaskResolve)
    {
        tTaskResolve.setUpdateTime(DateUtils.getNowDate());
        return tTaskResolveMapper.updateTTaskResolve(tTaskResolve);
    }

    /**
     * 删除退牧还草工程任务领取分解对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTTaskResolveByIds(String ids)
    {
        return tTaskResolveMapper.deleteTTaskResolveByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退牧还草工程任务领取分解信息
     *
     * @param resolveId 退牧还草工程任务领取分解ID
     * @return 结果
     */
    @Override
    public int deleteTTaskResolveById(Long resolveId)
    {
        return tTaskResolveMapper.deleteTTaskResolveById(resolveId);
    }

    @Override
    public int removeResolveTTaskResolveByIds(String ids)
    {
        return tTaskResolveMapper.removeResolveTTaskResolveByIds(Convert.toStrArray(ids));
    }



    public List<TTaskResolve> selectTTaskResolveByPublishId(TTaskPublish tTaskPublish) {
        return tTaskResolveMapper.selectTTaskResolveByPublishId(tTaskPublish);
    }

    @Override
    public void updateTTaskResolveStatus(TTaskResolve tTaskResolve) {
        tTaskResolveMapper.updateTTaskResolveStatus(tTaskResolve);
    }

    @Override
    public String selectDictValue(String addrCity) {
        return tTaskResolveMapper.selectDictValue(addrCity);
    }

    /**
     * 查询已完成建设规模
     *
     * @return 查询已完成建设规模
     */
    public TTaskResolve selectResolveTotalScale(TTaskResolve tTaskResolve){
        return tTaskResolveMapper.selectResolveTotalScale(tTaskResolve);
    }

    /**
     * 查询区县发布任务量
     *
     * @return 查询区县发布任务量
     */
    @Override
    public List<TTaskResolve> selectAreaResolveListH(TTaskResolve tTaskResolve){
        return tTaskResolveMapper.selectAreaResolveListH(tTaskResolve);
    }

    @Override
    public TTaskResolve getIdByDate(String date) {
        return tTaskResolveMapper.getIdByDate(date);
    }

    /**
     * 根据上报信息查询任务ID
     *
     * @return 根据上报信息查询任务ID
     */
    @Override
    public TTaskResolve selectResolveId(TTaskReport tTaskReport){
        tTaskReport.setProvince(tTaskReport.getCity());
        return tTaskResolveMapper.selectResolveId(tTaskReport);
    }
}
