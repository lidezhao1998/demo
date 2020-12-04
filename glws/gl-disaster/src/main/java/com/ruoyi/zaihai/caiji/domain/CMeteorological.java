package com.ruoyi.zaihai.caiji.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 气象管理信息对象 c_meteorological
 * 
 * @author ruoyi
 * @date 2020-07-15
 */
public class CMeteorological extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 地点 */
    @Excel(name = "地点")
    private String address;

    /** 气温 */
    @Excel(name = "气温")
    private Double airTemperature;

    /** 积温 */
    @Excel(name = "积温")
    private Double accumulatedTemperature;

    /** 地温 */
    @Excel(name = "地温")
    private Double groundTemperature;

    /** 降水 */
    @Excel(name = "降水")
    private String precipitation;

    /** 自然灾害 */
    @Excel(name = "自然灾害")
    private String naturalDisaster;

    /** 风向 */
    @Excel(name = "风向")
    private String windDirection;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 发布时间 */
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAirTemperature(Double airTemperature) 
    {
        this.airTemperature = airTemperature;
    }

    public Double getAirTemperature() 
    {
        return airTemperature;
    }
    public void setAccumulatedTemperature(Double accumulatedTemperature) 
    {
        this.accumulatedTemperature = accumulatedTemperature;
    }

    public Double getAccumulatedTemperature() 
    {
        return accumulatedTemperature;
    }
    public void setGroundTemperature(Double groundTemperature) 
    {
        this.groundTemperature = groundTemperature;
    }

    public Double getGroundTemperature() 
    {
        return groundTemperature;
    }
    public void setPrecipitation(String precipitation) 
    {
        this.precipitation = precipitation;
    }

    public String getPrecipitation() 
    {
        return precipitation;
    }
    public void setNaturalDisaster(String naturalDisaster) 
    {
        this.naturalDisaster = naturalDisaster;
    }

    public String getNaturalDisaster() 
    {
        return naturalDisaster;
    }
    public void setWindDirection(String windDirection) 
    {
        this.windDirection = windDirection;
    }

    public String getWindDirection() 
    {
        return windDirection;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setPublishTime(Date publishTime) 
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime() 
    {
        return publishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("address", getAddress())
            .append("airTemperature", getAirTemperature())
            .append("accumulatedTemperature", getAccumulatedTemperature())
            .append("groundTemperature", getGroundTemperature())
            .append("precipitation", getPrecipitation())
            .append("naturalDisaster", getNaturalDisaster())
            .append("windDirection", getWindDirection())
            .append("status", getStatus())
            .append("source", getSource())
            .append("publishTime", getPublishTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
