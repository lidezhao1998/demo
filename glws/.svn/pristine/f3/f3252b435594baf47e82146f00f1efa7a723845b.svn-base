package com.ruoyi.system.service;

import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskYear;

import java.util.List;
import java.util.Map;

/**
 * 黑土滩、毒害草、已垦草原治理报表接口
 *
 * @author feiyanxu
 * @date 2019-01-08
 */
public interface ITaskGovernmentService
{
    /**
     * 黑土滩、毒害草、已垦草原治理报表报表
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 退牧还草工程进度上报
     */
    TTaskReport selectTTaskReportById(Long reportId);

    /**
     * 黑土滩、毒害草、已垦草原治理报表
     *
     * @param tTaskReport 退牧还草工程进度上报实体
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectTTaskReportList(TTaskReport tTaskReport);

    List<TTaskYear> selectProvinceList();

    List<TTaskReport> selectTimeList(String province);

    //List<TTaskReport> selectTTaskBuildList(String sqlParam);

     List<Map<String, Object>> selectTTaskBuildList(String sqlParam);


    //List<Province> selectProvinceList();


}
