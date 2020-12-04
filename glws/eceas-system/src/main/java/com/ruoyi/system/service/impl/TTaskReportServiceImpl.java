package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TTaskReportMapper;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.service.ITTaskReportService;

/**
 * 退牧还草工程进度上报Service业务层处理
 *
 * @author LiuHongfei
 * @date 2019-11-29
 */
@Service
public class TTaskReportServiceImpl implements ITTaskReportService
{
    @Autowired
    private TTaskReportMapper tTaskReportMapper;

    /**
     * 查询退牧还草工程进度上报
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 退牧还草工程进度上报
     */
    @Override
    public TTaskReport selectTTaskReportById(Long reportId)
    {
        return tTaskReportMapper.selectTTaskReportById(reportId);
    }

    /**
     * 查询退牧还草工程进度上报列表
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 退牧还草工程进度上报
     */
    @Override
    public List<TTaskReport> selectTTaskReportList(TTaskReport tTaskReport)
    {
        return tTaskReportMapper.selectTTaskReportList(tTaskReport);
    }

    /**
     * 查询退牧还草工程详细进度
     *
     * @param tTaskReport 退牧还草工程详细进度
     * @return 退牧还草工程进度上报
     */
    @Override
    public List<TTaskReport> selectTTaskReportListById(TTaskReport tTaskReport)
    {
        return tTaskReportMapper.selectTTaskReportListById(tTaskReport);
    }

    /**
     * 查询当前地区当前年份时间刻度是否冲突
     *
     * @param tTaskReport
     * @return 查询当前地区当前年份时间刻度是否冲突
     */
    @Override
    public List<TTaskReport> selectTimeScaleFromYearAndAddress(TTaskReport tTaskReport)
    {
        return tTaskReportMapper.selectTimeScaleFromYearAndAddress(tTaskReport);
    }

    /**
     * 查询省份
     *
     * @return 查询省份
     */
    @Override
    public List<TTaskReport> selectProvince(TTaskReport tTaskReport) {
        return tTaskReportMapper.selectProvince(tTaskReport);
    }

    /**
     * 查询人工饲草地规模每年总和
     *
     * @return 查询人工饲草地规模每年总和
     */
    @Override
    public TTaskReport selectSumRgscdSize(String province){
        return tTaskReportMapper.selectSumRgscdSize(province);
    }

    /**
     * 查询人工饲草地中央资金每年总和
     *
     * @return 查询人工饲草地中央资金每年总和
     */
    @Override
    public TTaskReport selectZyMoney(String province){
        return tTaskReportMapper.selectZyMoney(province);
    }

    /**
     * 查询退化草原中央资金每年总和
     *
     * @return 查询退化草原中央资金每年总和
     */
    @Override
    public TTaskReport selectThcyglMoney(String province){
        return tTaskReportMapper.selectThcyglMoney(province);
    }


    /**
     * 查询工程资金汇总表
     *
     * @return 查询工程资金汇总表集合
     */
    @Override
    public List<TTaskReport> selectSummaryList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectSummaryList(tTaskReport);
    }

    /**
     * 查询围栏分省报表
     *
     * @return 查询围栏分省报表集合
     */
    @Override
    public List<TTaskReport> selectEnclosureList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectEnclosureList(tTaskReport);
    }

    /**
     * 新增退牧还草工程进度上报
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 结果
     */
    @Override
    public int insertTTaskReport(TTaskReport tTaskReport)
    {
        tTaskReport.setCreateTime(DateUtils.getNowDate());
        return tTaskReportMapper.insertTTaskReport(tTaskReport);
    }

    /**
     * 修改退牧还草工程进度上报
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 结果
     */
    @Override
    public int updateTTaskReport(TTaskReport tTaskReport)
    {
        tTaskReport.setUpdateTime(DateUtils.getNowDate());
        return tTaskReportMapper.updateTTaskReport(tTaskReport);
    }
}
