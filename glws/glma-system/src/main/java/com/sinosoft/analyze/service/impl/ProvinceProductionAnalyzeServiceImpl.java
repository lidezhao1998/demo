package com.sinosoft.analyze.service.impl;

import com.sinosoft.analyze.domain.ProvinceProductionAnalyze;
import com.sinosoft.analyze.mapper.ProvinceProductionAnalyzeMapper;
import com.sinosoft.analyze.service.ProvinceProductionAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/16 16:31
 */
@Service
public class ProvinceProductionAnalyzeServiceImpl implements ProvinceProductionAnalyzeService {
    @Autowired
    ProvinceProductionAnalyzeMapper provinceProductionAnalyzeMapper;
    @Override
    public List<ProvinceProductionAnalyze> selectProvinceProductionAnalyzeList(ProvinceProductionAnalyze provinceProductionAnalyze) {
        return provinceProductionAnalyzeMapper.selectProvinceProductionAnalyzeList(provinceProductionAnalyze);
    }

    @Override
    public List<String> selectYearGroup() {
        return provinceProductionAnalyzeMapper.selectYearGroup();
    }

    @Override
    public List<String> selectProductionGroup() {
        return provinceProductionAnalyzeMapper.selectProductionGroup();
    }
}
