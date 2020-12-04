package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TTaskReport;
import com.ruoyi.system.mapper.TTaskReportMapper;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ITTaskAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退牧还草工程实施分析业务处理
 *
 * @author gk
 * @date 2020-07-21
 */
@Service
public class ITTaskAnalysisServiceImpl implements ITTaskAnalysisService {

    @Autowired
    private TTaskReportMapper tTaskReportMapper;
    
    @Autowired
    private ISysDictDataService iSysDictDataService;

    /**
     * 横向比对上报列表
     *
     * @param tTaskReport 横向比对上报列表
     * @return 退牧还草工程进度上报集合
     */
    public List<TTaskReport> selectAnalysisList(TTaskReport tTaskReport) {
        return tTaskReportMapper.selectAnalysisList(tTaskReport);
    }


    /**
     * 横向比对上报列表
     *
     * @param tTaskReport 横向比对上报列表
     * @return 退牧还草工程进度上报集合
     */
    public List<TTaskReport> selectTransverseList(TTaskReport tTaskReport) {
        return tTaskReportMapper.selectTransverseList(tTaskReport);
    }


    /**
     * 纵向比对上报列表
     *
     * @param tTaskReport 纵向比对上报列表
     * @return 退牧还草工程进度上报集合
     */
    public List<TTaskReport> selectPortraitList(TTaskReport tTaskReport) {
        return tTaskReportMapper.selectPortraitList(tTaskReport);
    }
}
