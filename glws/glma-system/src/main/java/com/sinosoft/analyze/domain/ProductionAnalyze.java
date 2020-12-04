package com.sinosoft.analyze.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/15 17:14
 * 生产力分析
 */
@Getter
@Setter
public class ProductionAnalyze extends BaseEntity {
    private int id;
    private String year;
    private double value;

}
