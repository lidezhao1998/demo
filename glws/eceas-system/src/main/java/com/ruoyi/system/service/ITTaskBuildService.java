package com.ruoyi.system.service;

import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskYear;

import java.util.List;
import java.util.Map;

/**
 * 舍饲棚围建设任务分省报表接口
 *
 * @author feiyanxu
 * @date 2019-12-23
 */
public interface ITTaskBuildService
{
    /**
     * 饲棚围建设任务分省报表
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 退牧还草工程进度上报
     */
    TTaskReport selectTTaskReportById(Long reportId);

    /**
     * 饲棚围建设任务分省报表
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectTTaskReportList(TTaskReport tTaskReport);

    List<TTaskYear> selectProvinceList();

    List<TTaskReport> selectTimeList(String province);

    //List<TTaskReport> selectTTaskBuildList(String sqlParam);

     List<Map<String, Object>> selectTTaskBuildList(String sqlParam);


    //List<Province> selectProvinceList();


}
