package com.ruoyi.system.service;

import com.ruoyi.system.domain.TTaskReport;

import java.util.List;

/**
 * 退牧还草工程实施分析Service接口
 *
 * @author gk
 * @date 2020-07-21
 */
public interface ITTaskAnalysisService {
    /**
     * 横向比对上报列表
     *
     * @param tTaskReport 横向比对上报列表
     * @return 退牧还草工程进度上报集合
     */
    List<TTaskReport> selectAnalysisList(TTaskReport tTaskReport);

    /**
     * 横向比对上报列表
     *
     * @param tTaskReport 横向比对上报列表
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
}
