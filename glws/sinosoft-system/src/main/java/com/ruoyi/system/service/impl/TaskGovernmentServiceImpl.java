package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskYear;
import com.ruoyi.system.mapper.TaskGovernmentMapper;
import com.ruoyi.system.service.ITaskGovernmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 舍饲棚围建设任务分省报表Service业务层处理
 *
 * @author feiyanxu
 * @date 2019-01-08
 */
@Service
public class TaskGovernmentServiceImpl implements ITaskGovernmentService
{
    @Autowired
    private TaskGovernmentMapper taskGovernmentMapper;

    /**
     * 饲棚围建设任务分省报表
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 退牧还草工程进度上报
     */
    @Override
    public TTaskReport selectTTaskReportById(Long reportId)
    {
        return taskGovernmentMapper.selectTTaskReportById(reportId);
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
        return taskGovernmentMapper.selectTTaskReportList(tTaskReport);
    }

    @Override
    public List<TTaskYear> selectProvinceList() {
        return taskGovernmentMapper.selectProvinceList();
    }

    @Override
    public List<TTaskReport> selectTimeList(String province) {
        return taskGovernmentMapper.selecttimeList();
    }

   /* @Override
    public List<TTaskReport> selectTTaskBuildList(String sqlParam) {
        return tTaskBuildMapper.selectTTaskBuildList(sqlParam);
    }*/

    @Override
    public List<Map<String, Object>> selectTTaskBuildList(String sqlParam) {
        return taskGovernmentMapper.selectTTaskBuildList(sqlParam);
    }

}
