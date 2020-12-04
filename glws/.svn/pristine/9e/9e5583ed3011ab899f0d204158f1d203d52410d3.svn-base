package com.ruoyi.system.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TTaskReportMapper;
import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.service.ITTaskReportService;
import com.ruoyi.common.core.text.Convert;

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
    @Autowired
    private ISysDictDataService iSysDictDataService;

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
    public List<TTaskReport> selectTTaskReportMonth(TTaskReport tTaskReport)
    {
        return tTaskReportMapper.selectTTaskReportMonth(tTaskReport);
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
    public List<TTaskReport> selectReportAreaList(TTaskReport tTaskReport){
            return tTaskReportMapper.selectReportAreaList(tTaskReport);
        }

    /**
     * 查询市数据
     *
     * @return 查询市数据集合
     */
    @Override
    public List<TTaskReport> selectReportCityList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectReportCityList(tTaskReport);
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
    public List<TTaskReport> selectCityList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectCityList(tTaskReport);
    }

    /**
     * 查询省上报列表
     *
     * @return 查询省上报列表
     */
    public List<TTaskReport> selectProvinceList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectProvinceList(tTaskReport);
    }

    /**
     * 查询区县以通过数据列表
     *
     * @return 查询区县以通过数据列表
     */
    public List<TTaskReport> selectAreaPassList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectAreaPassList(tTaskReport);
    }

    /**
     * 查询市以通过数据列表
     *
     * @return 查询区县以通过数据列表
     */
    public List<TTaskReport> selectCityPassList(TTaskReport tTaskReport){
        return tTaskReportMapper.selectCityPassList(tTaskReport);
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
        tTaskReport.setUpdateTime(DateUtils.getNowDate());
//        tTaskReport.setConcreteTime(tTaskReport.getConcreteTime().trim().replace(",",""));
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
//        tTaskReport.setConcreteTime(tTaskReport.getConcreteTime().trim().replace(",",""));
        return tTaskReportMapper.updateTTaskReport(tTaskReport);
    }

    /**
     * 删除退牧还草工程进度上报对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTTaskReportByIds(String ids)
    {
        return tTaskReportMapper.deleteTTaskReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退牧还草工程进度上报信息
     *
     * @param reportId 退牧还草工程进度上报ID
     * @return 结果
     */
    @Override
    public int deleteTTaskReportById(Long reportId)
    {
        return tTaskReportMapper.deleteTTaskReportById(reportId);
    }

    @Override
    public TTaskReport selectTTaskMapById(long id) {
        return tTaskReportMapper.selectTTaskMapById(id);
    }

    /**
     * 查询当前用户未审核上报信息 （市级别 需要查区）
     */
    public List<TTaskReport> selectUnreviewedReportAreaList(TTaskReport tTaskReport){

        return tTaskReportMapper.selectUnreviewedReportAreaList(tTaskReport);
    }

    /**
     * 询当前用户未审核上报信息 （省级别 需要查市）
     */
    public List<TTaskReport> selectUnreviewedReportCityList(TTaskReport tTaskReport){

        return tTaskReportMapper.selectUnreviewedReportCityList(tTaskReport);
    }

    /**
     * 根据年份 和 省市区 查找对应的一条最新上报信息
     * */
    public TTaskReport selectLatestTTaskReport(TTaskReport tTaskReport){
        return tTaskReportMapper.selectLatestTTaskReport(tTaskReport);
    }

    /**
     * 根据专题 和 省市区 查找所属上报信息
     * */
    public List<TTaskReport> selectGisListReport(TTaskReport tTaskReport){
        //判断值是否为空
        if (!"".equals(tTaskReport.getProvince()) && tTaskReport.getProvince() != null) {
            tTaskReport.setProvince(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskReport.getProvince())).getDictLabel());
        }
        if (!"".equals(tTaskReport.getCity()) && tTaskReport.getCity() != null) {
            tTaskReport.setCity(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskReport.getCity())).getDictLabel());
        }
        if (!"".equals(tTaskReport.getAddress()) && tTaskReport.getAddress() != null) {
            tTaskReport.setAddress(iSysDictDataService.selectDictDataById(Long.parseLong(tTaskReport.getAddress())).getDictLabel());
        }
        List<TTaskReport> tTaskReports = tTaskReportMapper.selectGisListReport(tTaskReport);
        for (int i = 0; i < tTaskReports.size(); i++) {
            TTaskReport newTTaskReport = tTaskReports.get(i);
            newTTaskReport = getSumReport(newTTaskReport);
            //市级
            if (newTTaskReport.getReportLevel() == 1) {
                newTTaskReport.setAddress(newTTaskReport.getCity());
            }
            //省级
            if (newTTaskReport.getReportLevel() == 2) {
                newTTaskReport.setAddress(newTTaskReport.getProvince());
            }
        }
        return tTaskReports;
    }

    /**
     * 提取方法计算列表展示合计
     */
    private TTaskReport getSumReport(TTaskReport newTTaskReport) {
        DecimalFormat df = new DecimalFormat("#.00");
        /** 围栏规模合计 */
        Double jmgm = newTTaskReport.getJmSize();
        Double xmgm = newTTaskReport.getXmSize();
        Double hqmgm = newTTaskReport.getHqlxSize();
        Double smhgm = newTTaskReport.getSmhzlSize();
        Double wlscale = newTTaskReport.getWlScale();
        if (jmgm == null) {
            jmgm = 0.0;
        }
        if (xmgm == null) {
            xmgm = 0.0;
        }
        if (hqmgm == null) {
            hqmgm = 0.0;
        }
        if (smhgm == null) {
            smhgm = 0.0;
        }
        if (wlscale == null) {
            wlscale = 0.0;
        }
        Double wlmj = jmgm + xmgm + hqmgm + smhgm + wlscale;
        newTTaskReport.setWlmjCount(Double.parseDouble(df.format(wlmj)));
        /** 围栏投资合计 */
        Double jmtz = newTTaskReport.getJmMoney();
        Double xmtz = newTTaskReport.getXmMoney();
        Double hqmtz = newTTaskReport.getHqlxMoney();
        Double smhtz = newTTaskReport.getSmhzlMoney();
        Double wlinvestment = newTTaskReport.getWlInvestment();
        if (jmtz == null) {
            jmtz = 0.0;
        }
        if (xmtz == null) {
            xmtz = 0.0;
        }
        if (hqmtz == null) {
            hqmtz = 0.0;
        }
        if (smhtz == null) {
            smhtz = 0.0;
        }
        if (wlinvestment == null) {
            wlinvestment = 0.0;
        }
        Double wlje = jmtz + xmtz + hqmtz + smhtz + wlinvestment;
        newTTaskReport.setWljeCount(Double.parseDouble(df.format(wlje)));
        /** 其他规模合计 */
        Double thcgm = newTTaskReport.getThcyglSize();
        Double rgsgm = newTTaskReport.getRgscdSize();
        Double hspgm = newTTaskReport.getHspjSize();
        Double httgm = newTTaskReport.getHttSize();
        Double dhcgm = newTTaskReport.getDhcSize();
        if (thcgm == null) {
            thcgm = 0.0;
        }
        if (rgsgm == null) {
            rgsgm = 0.0;
        }
        if (hspgm == null) {
            hspgm = 0.0;
        }
        if (httgm == null) {
            httgm = 0.0;
        }
        if (dhcgm == null) {
            dhcgm = 0.0;
        }
        Double totalScale = thcgm + rgsgm + hspgm + httgm + dhcgm;
        newTTaskReport.setQtmjCount(Double.parseDouble(df.format(totalScale)));
        /** 其他投资合计 */
        Double thctz = newTTaskReport.getThcyglMoney();
        Double rgstz = newTTaskReport.getRgscdMoney();
        Double hsptz = newTTaskReport.getHspjMoney();
        Double htttz = newTTaskReport.getHttMoney();
        Double dhctz = newTTaskReport.getDhcMoney();
        if (thctz == null) {
            thctz = 0.0;
        }
        if (rgstz == null) {
            rgstz = 0.0;
        }
        if (hsptz == null) {
            hsptz = 0.0;
        }
        if (htttz == null) {
            htttz = 0.0;
        }
        if (dhctz == null) {
            dhctz = 0.0;
        }

        Double totalInvestment = thctz + rgstz + hsptz + htttz + dhctz;
        newTTaskReport.setQtjeCount(Double.parseDouble(df.format(totalInvestment)));
        /** 总合计 */
        newTTaskReport.setZgmCount(Double.parseDouble(df.format(wlmj + totalScale)));
        newTTaskReport.setZjeCount(Double.parseDouble(df.format(wlje + totalInvestment)));
        return newTTaskReport;
    }

    public List<TTaskReport> reportId(Long reportId) {
        TTaskReport tTaskReport = tTaskReportMapper.selectTTaskReportById(reportId);
        return tTaskReportMapper.reportId(tTaskReport.getYear(),tTaskReport.getCity());
    }
}
