package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CPlot;

import java.util.List;

/**
 * 样地Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public interface CPlotMapper 
{
    /**
     * 查询样地
     * 
     * @param plotId 样地ID
     * @return 样地
     */
        CPlot selectCPlotById(Long plotId);

    /**
     * 查询样地
     *
     * @param groundId 样地ID
     * @return 样地
     */
    List<CPlot> selectCPlotGyId(Long groundId);
    /**
     * 查询样地按时间倒序
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
     * 删除样地
     * 
     * @param plotId 样地ID
     * @return 结果
     */
    int deleteCPlotById(Long plotId);

    /**
     * 批量删除样地
     * 
     * @param plotIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCPlotByIds(String[] plotIds);
/**
 * 批量删除样地
 *
 * @param gyIds 需要删除的样地ID
 * @return 结果
 */
        int deleteCPlotGyIds(String[] gyIds);


    int selectCPlotById1(Long plotId);

    int selectCPlotListById(Long plotId);
    /**
     *功能描述 展示
     * @author sunlei
     * @date 2020/10/17
     * @param
     * @return [plotId]
     */
    CPlot selectCPlotDetailById(Long plotId);
}
