package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 水源涵养模型对象 model_management
 * 
 * @author hdp
 * @date 2020-08-11
 */
public class ModelManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long modelId;

    /** 总水源涵养量 */
    @Excel(name = "总水源涵养量")
    private Double tq;

    /** 降雨量 */
    @Excel(name = "降雨量")
    private Double p;

    /** 地表径流量 */
    @Excel(name = "地表径流量")
    private Double r;

    /** 蒸散发量 */
    @Excel(name = "蒸散发量")
    private Double et;

    /** 面积 */
    @Excel(name = "面积")
    private Double a;

    /** 生态系统类型 */
    @Excel(name = "生态系统类型")
    private Integer type;

    /** 生态类型数 */
    @Excel(name = "生态类型数")
    private Long typeNum;

    public void setModelId(Long modelId) 
    {
        this.modelId = modelId;
    }

    public Long getModelId() 
    {
        return modelId;
    }
    public void setTq(Double tq) 
    {
        this.tq = tq;
    }

    public Double getTq() 
    {
        return tq;
    }
    public void setP(Double p) 
    {
        this.p = p;
    }

    public Double getP() 
    {
        return p;
    }
    public void setR(Double r) 
    {
        this.r = r;
    }

    public Double getR() 
    {
        return r;
    }
    public void setEt(Double et) 
    {
        this.et = et;
    }

    public Double getEt() 
    {
        return et;
    }
    public void setA(Double a) 
    {
        this.a = a;
    }

    public Double getA() 
    {
        return a;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setTypeNum(Long typeNum) 
    {
        this.typeNum = typeNum;
    }

    public Long getTypeNum() 
    {
        return typeNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("modelId", getModelId())
            .append("tq", getTq())
            .append("p", getP())
            .append("r", getR())
            .append("et", getEt())
            .append("a", getA())
            .append("type", getType())
            .append("typeNum", getTypeNum())
            .toString();
    }
}
