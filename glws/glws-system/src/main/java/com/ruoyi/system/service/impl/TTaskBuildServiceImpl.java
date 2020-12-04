package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.TTaskYear;
import com.ruoyi.system.mapper.TTaskBuildMapper;
import com.ruoyi.system.service.ITTaskBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.TTaskReport;

/**
 * 舍饲棚围建设任务分省报表Service业务层处理
 *
 * @author feiyanxu
 * @date 2019-12-23
 */
@Service
public class TTaskBuildServiceImpl implements ITTaskBuildService
{
    @Autowired
    private TTaskBuildMapper tTaskBuildMapper;

    /**
     * 饲棚围建设任务分省报表
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 退牧还草工程进度上报
     */
    @Override
    public TTaskReport selectTTaskReportById(Long reportId)
    {
        return tTaskBuildMapper.selectTTaskReportById(reportId);
    }

    /**
     *饲棚围建设任务分省报表
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 退牧还草工程进度上报
     */
    @Override
    public List<TTaskReport> selectTTaskReportList(TTaskReport tTaskReport)
    {
        return tTaskBuildMapper.selectTTaskReportList(tTaskReport);
    }

    @Override
    public List<TTaskYear> selectProvinceList() {
        return tTaskBuildMapper.selectProvinceList();
    }

    @Override
    public List<TTaskReport> selectTimeList(String province) {
        return tTaskBuildMapper.selecttimeList();
    }

   /* @Override
    public List<TTaskReport> selectTTaskBuildList(String sqlParam) {
        return tTaskBuildMapper.selectTTaskBuildList(sqlParam);
    }*/

    @Override
    public List<Map<String, Object>> selectTTaskBuildList(String sqlParam) {
        return tTaskBuildMapper.selectTTaskBuildList(sqlParam);
    }

}
