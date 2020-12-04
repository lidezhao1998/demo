package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.domain.TTaskResolve;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 退牧还草工程进度上报Mapper接口
 * 
 * @author LiuHongfei
 * @date 2019-11-29
 */
public interface TTaskReportMapper 
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
    List<TTaskReport> selectTTaskReportMonth(TTaskReport tTaskReport);

    /**
     * 查询省份
     *
     * @return 查询省份
     */
    List<TTaskReport> selectProvince(TTaskReport tTaskReport);

    /**
     * 查询人工种草地中央资金每年总和
     *
     * @return 查询人工种草地中央资金每年总和
     */
    TTaskReport selectZyMoney(String province);

    /**
     * 查询退化草原中央资金每年总和
     *
     * @return 查询退化草原中央资金每年总和
     */
    TTaskReport selectThcyglMoney(String province);

    /**
     * 查询围栏分省报表
     *
     * @return 查询工程资金汇总表集合
     */
    List<TTaskReport> selectEnclosureList(TTaskReport tTaskReport);

    /**
     * 查询区县数据
     *
     * @return 查询区县数据集合
     */
    List<TTaskReport> selectReportAreaList(TTaskReport tTaskReport);

    /**
     * 查询市上报列表
     *
     * @return 查询市上报列表
     */
    List<TTaskReport> selectCityList(TTaskReport tTaskReport);

    /**
     * 查询省上报列表
     *
     * @return 查询省上报列表
     */
    List<TTaskReport> selectProvinceList(TTaskReport tTaskReport);

    /**
     * 查询区县以通过数据列表
     *
     * @return 查询区县以通过数据列表
     */
    List<TTaskReport> selectAreaPassList(TTaskReport tTaskReport);

    /**
     * 查询市以通过数据列表
     *
     * @return 查询区县以通过数据列表
     */
    List<TTaskReport> selectCityPassList(TTaskReport tTaskReport);

    /**
     * 查询市数据
     *
     * @return 查询市数据集合
     */
    List<TTaskReport> selectReportCityList(TTaskReport tTaskReport);

    /**
     * 查询市数据
     *
     * @return 查询区县数据集合
     */
    List<TTaskReport> selectReportProvinceList(@Param("dictLabel")String dictLabel,@Param("year") String year,@Param("province") String province);

    /**
     * 查询市已完成建设
     *
     * @return 查询已完成建设
     */
    TTaskReport selectCityTotal(TTaskReport tTaskReport);

    /**
     * 查询省已完成建设
     *
     * @return 查询已完成建设
     */
    TTaskReport selectProvinceTotal(TTaskReport tTaskReport);

    /**
     * 查询已完成建设规模
     *
     * @return 查询已完成建设规模
     */
    TTaskReport selectReportCityTotalScale(TTaskReport tTaskReport);

    /**
     * 查询已完成建设规模
     *
     * @return 查询已完成建设规模
     */
    TTaskReport selectReportProvinceTotalScale(TTaskReport tTaskReport);

    /**
     * 查询市上报
     *
     * @return 查询市上报
     */
    TTaskReport selectCityReport(TTaskReport tTaskReport);

    /**
     * 查询市上报列表
     *
     * @return 查询市上报列表
     */
    List<TTaskReport> selectCityReportList(TTaskReport tTaskReport);

    /**
     * 查询区县上报
     *
     * @return 查询区县上报
     */
    List<TTaskReport> selectAreaReportList(TTaskReport tTaskReport);

    /**
     * 查询通过的区县总和
     *
     * @return 查询通过的区县总和
     */
    TTaskReport selectPassReport(TTaskReport tTaskReport);

    /**
     * 查询省级(市级通过总和)
     *
     * @return 查询省级(市级通过总和)
     */
    TTaskReport selectReportCitySum(TTaskReport tTaskReport);

    /**
     * 根据地区查找省市区上报列表
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectAnalysisList(TTaskReport tTaskReport);

    /**
     * 根据地区查找省市区上报列表
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectAnalysisListH(TTaskReport tTaskReport);

    /**
     * 根据年份全国上报列表
     *
     * @param tTaskReport 退牧还草工程进度上报
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectReportCountryZ(TTaskReport tTaskReport);

    /**
     * 横向比对上报列表
     *
     * @param tTaskReport 纵向比对上报列表
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectTransverseList(TTaskReport tTaskReport);

    /**
     * 纵向比对上报列表
     *
     * @param tTaskReport 纵向比对上报列表
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectPortraitList(TTaskReport tTaskReport);

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

    /**
     * 删除退牧还草工程进度上报
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 结果
     */
    int deleteTTaskReportById(Long reportId);

    /**
     * 批量删除退牧还草工程进度上报
     *
     * @param reportIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTTaskReportByIds(String[] reportIds);

    TTaskReport selectTTaskMapById(long id);

    /**
     * 查询当前用户未审核上报信息 （市级别 需要查区）
     */
    List<TTaskReport> selectUnreviewedReportAreaList(TTaskReport tTaskReport);

    /**
     * 查询当前用户未审核上报信息 （省级别 需要查市）
     */
    List<TTaskReport> selectUnreviewedReportCityList(TTaskReport tTaskReport);

    /**
     * 根据年份 和 省市区 查找对应的一条最新上报信息
     * */
    TTaskReport selectLatestTTaskReport(TTaskReport tTaskReport);

    /**
     * 根据专题 和 省市区 查找所属上报信息
     * */
    List<TTaskReport> selectGisListReport(TTaskReport tTaskReport);

    List<TTaskReport> reportId(@Param("year") String year,@Param("city") String city);
}
