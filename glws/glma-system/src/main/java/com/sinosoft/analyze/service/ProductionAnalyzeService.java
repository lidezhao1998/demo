package com.sinosoft.analyze.service;

import com.ruoyi.system.domain.SysDictData;
import com.sinosoft.analyze.domain.ProductionAnalyze;

import java.util.List;
import java.util.Map;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/15 17:54
 * 生产力分析
 */
public interface ProductionAnalyzeService {
    List<ProductionAnalyze> selectProductionAnalyzeList(ProductionAnalyze productionAnalyze);

    public Map getProvincesCityCountyCode(String provinces, String  city, String county);
}
