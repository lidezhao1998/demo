package com.sinosoft.analyze.mapper;

import com.ruoyi.system.domain.SysDictData;
import com.sinosoft.analyze.domain.ProductionAnalyze;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/15 17:52
 * 生产力分析
 */
public interface ProductionAnalyzeMapper {
    List<ProductionAnalyze> selectProductionAnalyzeList(ProductionAnalyze productionAnalyze);

    List<SysDictData> selectDictDataByType(String dictType);
    String getProvincesCode(String provinces);

    String getCityCode(@Param("provincesCode") String provincesCode, @Param("city")String city);


}
