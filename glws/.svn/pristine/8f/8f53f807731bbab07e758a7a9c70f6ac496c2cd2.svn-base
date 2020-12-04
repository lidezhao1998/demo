package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 生态效益价值评价社会公共数据对象 public_data
 * 
 * @author hdp
 * @date 2020-06-28
 */
public class PublicData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公共数据ID */
    private Long dataid;

    /** 数据名称 */
    @Excel(name = "数据名称")
    private String dataName;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 数值 */
    @Excel(name = "数值")
    private Double value;

    /** 来源及依据 */
    @Excel(name = "来源及依据")
    private String source;

    public void setDataid(Long dataid) 
    {
        this.dataid = dataid;
    }

    public Long getDataid() 
    {
        return dataid;
    }
    public void setDataName(String dataName) 
    {
        this.dataName = dataName;
    }

    public String getDataName() 
    {
        return dataName;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setValue(Double value) 
    {
        this.value = value;
    }

    public Double getValue() 
    {
        return value;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("dataid", getDataid())
            .append("dataName", getDataName())
            .append("unit", getUnit())
            .append("value", getValue())
            .append("source", getSource())
            .toString();
    }
}
