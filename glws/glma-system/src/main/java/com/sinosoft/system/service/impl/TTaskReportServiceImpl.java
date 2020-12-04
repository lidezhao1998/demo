package com.sinosoft.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.sinosoft.system.domain.TTaskReport;
import com.sinosoft.system.mapper.TTaskReportMapper;
import com.sinosoft.system.service.ITTaskReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

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
     * 查询人工种草地规模每年总和
     *
     * @return 查询人工种草地规模每年总和
     */
    @Override
    public TTaskReport selectSumRgscdSize(String province){
        return tTaskReportMapper.selectSumRgscdSize(province);
    }

    /**
     * 查询人工种草地中央资金每年总和
     *
     * @return 查询人工种草地中央资金每年总和
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
     * 查询围栏分省报表
     *
     * @return 查询围栏分省报表集合
     */
    @Override
    public List<TTaskReport> selectEnclosureList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectEnclosureList(tTaskReport);
    }

    /**
     * 查询区县数据
     *
     * @return 查询区县数据集合
     */
    @Override
    public List<TTaskReport> selectReportAreaList(String deptName, String year, String province, String status, String addrCity){
//            if(StringUtils.isNotEmpty(addrCity) && Long.parseLong(addrCity)>1){
//                SysDictData sysDictData = sysDictDataMapper.selectDictDataById(Long.parseLong(addrCity));
//                addrCity=sysDictData.getDictLabel();
//            }else{
//                addrCity="-1";
//            }
            return tTaskReportMapper.selectReportAreaList(deptName,year,province,status,addrCity);
        }

    /**
     * 查询市数据
     *
     * @return 查询市数据集合
     */
    @Override
    public List<TTaskReport> selectReportProvinceList(String dictLabel,String year,String province){
        return tTaskReportMapper.selectReportProvinceList(dictLabel,year,province);
    }

    /**
     * 查询市已完成建设
     *
     * @return 查询已完成建设
     */
    @Override
    public TTaskReport selectCityTotal(TTaskReport tTaskReport){
        return tTaskReportMapper.selectCityTotal(tTaskReport);
    }

    /**
     * 查询省已完成建设
     *
     * @return 查询已完成建设
     */
    @Override
    public TTaskReport selectProvinceTotal(TTaskReport tTaskReport){
        return tTaskReportMapper.selectProvinceTotal(tTaskReport);
    }

    /**
     * 查询已完成建设规模
     *
     * @return 查询已完成建设规模
     */
    @Override
    public TTaskReport selectReportCityTotalScale(TTaskReport tTaskReport){
        return tTaskReportMapper.selectReportCityTotalScale(tTaskReport);
    }

    /**
     * 查询已完成建设规模
     *
     * @return 查询已完成建设规模
     */
    @Override
    public TTaskReport selectReportProvinceTotalScale(TTaskReport tTaskReport){
        return tTaskReportMapper.selectReportProvinceTotalScale(tTaskReport);
    }

    /**
     * 查询市上报
     *
     * @return 查询市上报
     */
    public TTaskReport selectCityReport(TTaskReport tTaskReport){
        return tTaskReportMapper.selectCityReport(tTaskReport);
    }

    /**
     * 查询市上报列表
     *
     * @return 查询市上报列表
     */
    public List<TTaskReport> selectCityReportList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectCityReportList(tTaskReport);
    }

    /**
     * 查询区县上报
     *
     * @return 查询区县上报
     */
    public List<TTaskReport> selectAreaReportList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectAreaReportList(tTaskReport);
    }

    /**
     * 查询通过的区县总和
     *
     * @return 查询通过的区县总和
     */
    public TTaskReport selectPassReport(TTaskReport tTaskReport){
        return tTaskReportMapper.selectPassReport(tTaskReport);
    }

    /**
     * 查询省级(市级通过总和)
     *
     * @return 查询省级(市级通过总和)
     */
    public TTaskReport selectReportCitySum(TTaskReport tTaskReport){
        return tTaskReportMapper.selectReportCitySum(tTaskReport);
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
        tTaskReport.setConcreteTime(tTaskReport.getConcreteTime().trim().replace(",",""));
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
        tTaskReport.setConcreteTime(tTaskReport.getConcreteTime().trim().replace(",",""));
        return tTaskReportMapper.updateTTaskReport(tTaskReport);
    }

    @Override
    public TTaskReport selectTTaskMapById(long id) {
        return tTaskReportMapper.selectTTaskMapById(id);
    }
}
