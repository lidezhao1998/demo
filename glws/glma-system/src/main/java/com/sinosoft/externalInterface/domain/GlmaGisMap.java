package com.sinosoft.externalInterface.domain;

import com.ruoyi.system.domain.gis.GisMap;

/**
 * @author sunlei
 * @description 画图数据
 * @date 2020/11/16/11:07
 */
public class GlmaGisMap extends GisMap {
    //来源（glws、sinosoft）
    private String source;
    //专题树id（病虫害、工程、评价项目的id)
    private String sourceId;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
