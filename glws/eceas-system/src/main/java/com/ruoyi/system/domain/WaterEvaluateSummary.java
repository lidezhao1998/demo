package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 涵养水源功能评估数据汇总对象 water_evaluate_summary
 * 
 * @author hdp
 * @date 2020-09-15
 */
public class WaterEvaluateSummary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 地区 */
    @Excel(name = "地区")
    private String address;

    /** 面积(单位：万km²） */
    @Excel(name = "面积(单位：万km²）")
    private Double area;

    /** 年涵养水源量(单位：m3) */
    @Excel(name = "年涵养水源量(单位：m3)")
    private Double waterconservation;

    /** 涵养水源价值(单位：元) */
    @Excel(name = "涵养水源价值(单位：元)")
    private Double conservewatervalue;

    /** 水库建设单位库容投资(单位：元/m3) */
    @Excel(name = "水库建设单位库容投资(单位：元/m3)")
    private Double rcdci;

    /** 生态类型 */
    @Excel(name = "生态类型")
    private String ecologyType;

    /** 年降水量(单位：mm) */
    @Excel(name = "年降水量(单位：mm)")
    private Double annulprecipitation;

    /** 年蒸散量(单位：mm) */
    @Excel(name = "年蒸散量(单位：mm)")
    private Double evapotranspiration;

    /** 单位面积地表径流量(单位：mm) */
    @Excel(name = "单位面积地表径流量(单位：mm)")
    private Double surfaceannualrunoff;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setArea(Double area)
    {
        this.area = area;
    }

    public Double getArea()
    {
        return area;
    }
    public void setWaterconservation(Double waterconservation)
    {
        this.waterconservation = waterconservation;
    }

    public Double getWaterconservation()
    {
        return waterconservation;
    }
    public void setConservewatervalue(Double conservewatervalue)
    {
        this.conservewatervalue = conservewatervalue;
    }

    public Double getConservewatervalue()
    {
        return conservewatervalue;
    }
    public void setRcdci(Double rcdci)
    {
        this.rcdci = rcdci;
    }

    public Double getRcdci()
    {
        return rcdci;
    }
    public void setEcologyType(String ecologyType) 
    {
        this.ecologyType = ecologyType;
    }

    public String getEcologyType() 
    {
        return ecologyType;
    }
    public void setAnnulprecipitation(Double annulprecipitation)
    {
        this.annulprecipitation = annulprecipitation;
    }

    public Double getAnnulprecipitation()
    {
        return annulprecipitation;
    }
    public void setEvapotranspiration(Double evapotranspiration)
    {
        this.evapotranspiration = evapotranspiration;
    }

    public Double getEvapotranspiration()
    {
        return evapotranspiration;
    }
    public void setSurfaceannualrunoff(Double surfaceannualrunoff)
    {
        this.surfaceannualrunoff = surfaceannualrunoff;
    }

    public Double getSurfaceannualrunoff()
    {
        return surfaceannualrunoff;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("address", getAddress())
            .append("area", getArea())
            .append("waterconservation", getWaterconservation())
            .append("conservewatervalue", getConservewatervalue())
            .append("rcdci", getRcdci())
            .append("ecologyType", getEcologyType())
            .append("annulprecipitation", getAnnulprecipitation())
            .append("evapotranspiration", getEvapotranspiration())
            .append("surfaceannualrunoff", getSurfaceannualrunoff())
            .toString();
    }
}
