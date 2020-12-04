package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskYear;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 舍饲棚围建设任务分省报表Mapper接口
 *
 * @author ruoyi
 * @date 2019-12-23
 */
public interface TaskGovernmentMapper
{
    /**
     * 饲棚围建设任务分省报表查询
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 饲棚围建设任务分省报表
     */
    TTaskReport selectTTaskReportById(Long reportId);

    /**
     * 饲棚围建设任务分省报表列表
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 饲棚围建设任务分省报表
     */
    List<TTaskReport> selectTTaskReportList(TTaskReport tTaskReport);

    @Select("select DISTINCT(year) year from t_task_report")
    List<TTaskYear> selectProvinceList();

    @Select("select year from t_task_report ")
    List<TTaskReport> selecttimeList();

    @Select("select PROVINCE,${sqlParam} from t_task_report GROUP BY PROVINCE")
    List<Map<String, Object>> selectTTaskBuildList(@Param("sqlParam") String sqlParam);

   // List<TTaskReport> selectTTaskBuildList(@Param("sqlParam")String sqlPasram);
}
