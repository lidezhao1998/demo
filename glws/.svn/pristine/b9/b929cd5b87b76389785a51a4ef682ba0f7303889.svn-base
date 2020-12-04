package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.CPlot;

import java.util.List;

/**
 * 样地Service接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public interface ICPlotService 
{
    /**
     * 查询样地
     * 
     * @param plotId 样地ID
     * @return 样地
     */
        CPlot selectCPlotById(Long plotId);
    /**
     * 查询取样
     *
     * @param groundId 样地ID
     * @return 样地
     */
    List<CPlot> selectCPlotGyId(Long groundId);
    /**
     * 查询取样按时间倒序
     *
     * @param groundId 样地ID
     * @return 样地
     */
    List<CPlot> selectCPlotGyIdCount(Long groundId);
    /**
     * 查询样地列表
     * 
     * @param cPlot 样地
     * @return 样地集合
     */
    List<CPlot> selectCPlotList(CPlot cPlot);

    List<CPlot> selectCPlotList1(CPlot cPlot);

    List<CPlot> selectCPlotList2(CPlot cPlot);

    /**
     * 查询样地列表Id
     *
     * @param plotId 样地
     * @return 样地集合
     */
    int selectCPlotListById(Long plotId);



    /**
     * 新增样地
     * 
     * @param cPlot 样地
     * @return 结果
     */
    int insertCPlot(CPlot cPlot);

    /**
     * 修改样地
     * 
     * @param cPlot 样地
     * @return 结果
     */
    int updateCPlot(CPlot cPlot);

    int updateCPlot1(CPlot cPlot);

    /**
     * 批量删除样地
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCPlotByIds(String ids);

    /**
     * 删除样地信息
     * 
     * @param plotId 样地ID
     * @return 结果
     */
    int deleteCPlotById(Long plotId);

    int selectCPlotById1(Long ids);

    CPlot selectCPlotDetailById(Long id);
}
