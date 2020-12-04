package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 保育土壤对象 soil
 * 
 * @author hdp
 * @date 2020-06-22
 */
public class Soil extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 土壤 ID */
    private Long soilId;

    /** 林分面积(单位：hm2) */
    @Excel(name = "林分面积(单位：hm2)")
    private Double standArea;

    /** 林地土壤侵蚀模数(单位：t/hm2) */
    @Excel(name = "林地土壤侵蚀模数(单位：t/hm2)")
    private Double woodlandSoil;

    /** 无林地土壤侵蚀模数(单位：t/hm2) */
    @Excel(name = "无林地土壤侵蚀模数(单位：t/hm2)")
    private Double woodlandNotsoil;

    /** 林地土壤容重(单位：t/m3) */
    @Excel(name = "林地土壤容重(单位：t/m3)")
    private Double soilDensity;

    /** 林地土壤含氮量(单位：%) */
    @Excel(name = "林地土壤含氮量(单位：%)")
    private Double soilNitrogenContent;

    /** 林地土壤含磷量(单位：%) */
    @Excel(name = "林地土壤含磷量(单位：%)")
    private Double soilPhosphorusContent;

    /** 林地土壤含钾量(单位：%) */
    @Excel(name = "林地土壤含钾量(单位：%)")
    private Double potassiumContentSoil;

    /** 林地土壤有机质含量(单位：%) */
    @Excel(name = "林地土壤有机质含量(单位：%)")
    private Double soilOrganicMatter;

    /** 林分年固土量(单位：t) */
    @Excel(name = "林分年固土量(单位：t)")
    private Double soilConsolidation;

    /** 林分年固土价值(单位：元) */
    @Excel(name = "林分年固土价值(单位：元)")
    private Double soilConsolidationValue;

    /** 林分年保持氮量(单位：t) */
    @Excel(name = "林分年保持氮量(单位：t)")
    private Double maintainingNitrogen;

    /** 林分年保持磷量(单位：t) */
    @Excel(name = "林分年保持磷量(单位：t)")
    private Double maintainingPhosphorus;

    /** 林分年保持钾量(单位：t) */
    @Excel(name = "林分年保持钾量(单位：t)")
    private Double maintainingPotassium;

    /** 林分年保持有机质量(单位：t) */
    @Excel(name = "林分年保持有机质量(单位：t)")
    private Double organicQuality;

    /** 林分年保肥价值(单位：元) */
    @Excel(name = "林分年保肥价值(单位：元)")
    private Double protectFertilizerValue;

    public void setSoilId(Long soilId) 
    {
        this.soilId = soilId;
    }

    public Long getSoilId() 
    {
        return soilId;
    }
    public void setStandArea(Double standArea) 
    {
        this.standArea = standArea;
    }

    public Double getStandArea() 
    {
        return standArea;
    }
    public void setWoodlandSoil(Double woodlandSoil) 
    {
        this.woodlandSoil = woodlandSoil;
    }

    public Double getWoodlandSoil() 
    {
        return woodlandSoil;
    }
    public void setWoodlandNotsoil(Double woodlandNotsoil) 
    {
        this.woodlandNotsoil = woodlandNotsoil;
    }

    public Double getWoodlandNotsoil() 
    {
        return woodlandNotsoil;
    }
    public void setSoilDensity(Double soilDensity) 
    {
        this.soilDensity = soilDensity;
    }

    public Double getSoilDensity() 
    {
        return soilDensity;
    }
    public void setSoilNitrogenContent(Double soilNitrogenContent) 
    {
        this.soilNitrogenContent = soilNitrogenContent;
    }

    public Double getSoilNitrogenContent() 
    {
        return soilNitrogenContent;
    }
    public void setSoilPhosphorusContent(Double soilPhosphorusContent) 
    {
        this.soilPhosphorusContent = soilPhosphorusContent;
    }

    public Double getSoilPhosphorusContent() 
    {
        return soilPhosphorusContent;
    }
    public void setPotassiumContentSoil(Double potassiumContentSoil) 
    {
        this.potassiumContentSoil = potassiumContentSoil;
    }

    public Double getPotassiumContentSoil() 
    {
        return potassiumContentSoil;
    }
    public void setSoilOrganicMatter(Double soilOrganicMatter) 
    {
        this.soilOrganicMatter = soilOrganicMatter;
    }

    public Double getSoilOrganicMatter() 
    {
        return soilOrganicMatter;
    }
    public void setSoilConsolidation(Double soilConsolidation) 
    {
        this.soilConsolidation = soilConsolidation;
    }

    public Double getSoilConsolidation() 
    {
        return soilConsolidation;
    }
    public void setSoilConsolidationValue(Double soilConsolidationValue) 
    {
        this.soilConsolidationValue = soilConsolidationValue;
    }

    public Double getSoilConsolidationValue() 
    {
        return soilConsolidationValue;
    }
    public void setMaintainingNitrogen(Double maintainingNitrogen) 
    {
        this.maintainingNitrogen = maintainingNitrogen;
    }

    public Double getMaintainingNitrogen() 
    {
        return maintainingNitrogen;
    }
    public void setMaintainingPhosphorus(Double maintainingPhosphorus) 
    {
        this.maintainingPhosphorus = maintainingPhosphorus;
    }

    public Double getMaintainingPhosphorus() 
    {
        return maintainingPhosphorus;
    }
    public void setMaintainingPotassium(Double maintainingPotassium) 
    {
        this.maintainingPotassium = maintainingPotassium;
    }

    public Double getMaintainingPotassium() 
    {
        return maintainingPotassium;
    }
    public void setOrganicQuality(Double organicQuality) 
    {
        this.organicQuality = organicQuality;
    }

    public Double getOrganicQuality() 
    {
        return organicQuality;
    }
    public void setProtectFertilizerValue(Double protectFertilizerValue) 
    {
        this.protectFertilizerValue = protectFertilizerValue;
    }

    public Double getProtectFertilizerValue() 
    {
        return protectFertilizerValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("soilId", getSoilId())
            .append("standArea", getStandArea())
            .append("woodlandSoil", getWoodlandSoil())
            .append("woodlandNotsoil", getWoodlandNotsoil())
            .append("soilDensity", getSoilDensity())
            .append("soilNitrogenContent", getSoilNitrogenContent())
            .append("soilPhosphorusContent", getSoilPhosphorusContent())
            .append("potassiumContentSoil", getPotassiumContentSoil())
            .append("soilOrganicMatter", getSoilOrganicMatter())
            .append("soilConsolidation", getSoilConsolidation())
            .append("soilConsolidationValue", getSoilConsolidationValue())
            .append("maintainingNitrogen", getMaintainingNitrogen())
            .append("maintainingPhosphorus", getMaintainingPhosphorus())
            .append("maintainingPotassium", getMaintainingPotassium())
            .append("organicQuality", getOrganicQuality())
            .append("protectFertilizerValue", getProtectFertilizerValue())
            .toString();
    }
}
