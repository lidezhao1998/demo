package com.sinosoft.analyze.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/16 16:25
 * 各省生产力分析
 */
@Getter
@Setter
public class ProvinceProductionAnalyze extends BaseEntity {
    private int id;
    private String province;
    private String year;
    private double dryWeight;//干重
    private double wetWeight;//鲜重
    private double sum;//总数
}
