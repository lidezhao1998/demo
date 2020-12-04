package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 涵养水源对象 water_conservation
 * 
 * @author hdp
 * @date 2020-06-22
 */
public class WaterConservation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 涵养水源ID */
    private Long conservationId;

    /** 年度 */
    @Excel(name = "年度")
    private String annual;

    /** 地区省份 */
    @Excel(name = "地区省份")
    private String area;

    /** 林分面积(单位：hm2) */
    @Excel(name = "林分面积(单位：hm2)")
    private Double standArea;

    /** 年降水量(单位：mm) */
    @Excel(name = "年降水量(单位：mm)")
    private Double annulPrecipitation;

    /** 林分年蒸散量(单位：mm) */
    @Excel(name = "林分年蒸散量(单位：mm)")
    private Double evapotranspiration;

    /** 年涵养水源量(单位：m3） */
    @Excel(name = "年涵养水源量(单位：m3）")
    private Double waterConservation;

    /** 林分调节水量价值(单位：元) */
    @Excel(name = "林分调节水量价值(单位：元)")
    private Double annualWaterConservation;

    /** 林分净化水质价值(单位：元) */
    @Excel(name = "林分净化水质价值(单位：元)")
    private Double waterQualityValue;

    /** 涵养水源总价值(单位：元) */
    @Excel(name = "涵养水源总价值(单位：元)")
    private Double conserveWaterValue;

    /** 单位面积涵养水源价值(单位：元) */
    @Excel(name = "单位面积涵养水源价值(单位：元)")
    private Double unilConserveWaterValue;

    /** 单位面积地表年径流量(单位：mm) */
    @Excel(name="单位面积地表年径流量")
    private Double surfaceAnnualRunoff;

    public void setConservationId(Long conservationId) 
    {
        this.conservationId = conservationId;
    }

    public Long getConservationId() 
    {
        return conservationId;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStandArea(Double standArea)
    {
        this.standArea = standArea;
    }

    public Double getStandArea()
    {
        return standArea;
    }
    public void setAnnulPrecipitation(Double annulPrecipitation) 
    {
        this.annulPrecipitation = annulPrecipitation;
    }

    public Double getAnnulPrecipitation() 
    {
        return annulPrecipitation;
    }
    public void setEvapotranspiration(Double evapotranspiration) 
    {
        this.evapotranspiration = evapotranspiration;
    }

    public Double getEvapotranspiration() 
    {
        return evapotranspiration;
    }
    public void setWaterConservation(Double waterConservation) 
    {
        this.waterConservation = waterConservation;
    }

    public Double getWaterConservation() 
    {
        return waterConservation;
    }
    public void setAnnualWaterConservation(Double annualWaterConservation) 
    {
        this.annualWaterConservation = annualWaterConservation;
    }

    public Double getAnnualWaterConservation() 
    {
        return annualWaterConservation;
    }
    public void setWaterQualityValue(Double waterQualityValue) 
    {
        this.waterQualityValue = waterQualityValue;
    }

    public Double getWaterQualityValue() 
    {
        return waterQualityValue;
    }
    public void setConserveWaterValue(Double conserveWaterValue) 
    {
        this.conserveWaterValue = conserveWaterValue;
    }

    public Double getConserveWaterValue() 
    {
        return conserveWaterValue;
    }
    public void setUnilConserveWaterValue(Double unilConserveWaterValue) 
    {
        this.unilConserveWaterValue = unilConserveWaterValue;
    }

    public Double getUnilConserveWaterValue() 
    {
        return unilConserveWaterValue;
    }

    public Double getSurfaceAnnualRunoff() {
        return surfaceAnnualRunoff;
    }

    public void setSurfaceAnnualRunoff(Double surfaceAnnualRunoff) {
        this.surfaceAnnualRunoff = surfaceAnnualRunoff;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("conservationId", getConservationId())
            .append("annual",getAnnual())
            .append("area",getArea())
            .append("standArea", getStandArea())
            .append("annulPrecipitation", getAnnulPrecipitation())
            .append("evapotranspiration", getEvapotranspiration())
            .append("waterConservation", getWaterConservation())
            .append("annualWaterConservation", getAnnualWaterConservation())
            .append("waterQualityValue", getWaterQualityValue())
            .append("conserveWaterValue", getConserveWaterValue())
            .append("unilConserveWaterValue", getUnilConserveWaterValue())
            .append("surfaceAnnualRunoff",getSurfaceAnnualRunoff())
            .toString();
    }
}
