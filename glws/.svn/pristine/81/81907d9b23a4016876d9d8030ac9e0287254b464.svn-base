package com.ruoyi.system.service;

import com.ruoyi.system.domain.TTaskReport;
import java.util.List;

/**
 * 退牧还草工程进度上报Service接口
 * 
 * @author LiuHongfei
 * @date 2019-11-29
 */
public interface ITTaskReportService 
{
    /**
     * 查询退牧还草工程进度上报
     * 
     * @param reportId 退牧还草工程进度上报ID
     * @return 退牧还草工程进度上报
     */
    TTaskReport selectTTaskReportById(Long reportId);

    /**
     * 查询退牧还草工程进度上报列表
     * 
     * @param tTaskReport 退牧还草工程进度上报
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectTTaskReportList(TTaskReport tTaskReport);

    /**
     * 查询退牧还草工程详细进度
     *
     * @param tTaskReport 退牧还草工程详细进度
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectTTaskReportListById(TTaskReport tTaskReport);

    /**
     * 查询当前地区当前年份时间刻度是否冲突
     *
     * @param tTaskReport
     * @return 查询当前地区当前年份时间刻度是否冲突
     */
    List<TTaskReport> selectTimeScaleFromYearAndAddress(TTaskReport tTaskReport);

    /**
     * 查询省份
     *
     * @return 查询省份
     */
    List<TTaskReport> selectProvince(TTaskReport tTaskReport);

    /**
     * 查询人工饲草地规模每年总和
     *
     * @return 查询人工饲草地规模每年总和
     */
    TTaskReport selectSumRgscdSize(String province);

    /**
     * 查询人工饲草地中央资金每年总和
     *
     * @return 查询人工饲草地中央资金每年总和
     */
    TTaskReport selectZyMoney(String province);

    /**
     * 查询退化草原中央资金每年总和
     *
     * @return 查询退化草原中央资金每年总和
     */
    TTaskReport selectThcyglMoney(String province);

    /**
     * 查询工程资金汇总表
     *
     * @return 查询工程资金汇总表集合
     */
    List<TTaskReport> selectSummaryList(TTaskReport tTaskReport);

    /**
     * 查询围栏分省报表
     *
     * @return 查询围栏分省报表集合
     */
    List<TTaskReport> selectEnclosureList(TTaskReport tTaskReport);

    /**
     * 新增退牧还草工程进度上报
     * 
     * @param tTaskReport 退牧还草工程进度上报
     * @return 结果
     */
    int insertTTaskReport(TTaskReport tTaskReport);

    /**
     * 修改退牧还草工程进度上报
     * 
     * @param tTaskReport 退牧还草工程进度上报
     * @return 结果
     */
    int updateTTaskReport(TTaskReport tTaskReport);
}
