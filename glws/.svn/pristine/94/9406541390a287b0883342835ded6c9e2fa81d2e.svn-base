package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;

/**
 * 天然林资源保护工程建设情况统计对象 eval_tianbao_construction
 *
 * @author hdp
 * @date 2020-06-20
 */
public class EvalTianbaoConstruction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 天保生态工程ID */
    private Long id;

    /** 涵养水源ID */
    private Long conservationId;

    /** 年度 */
    @Excel(name = "年度")
    private String annual;

    /** 地区省份 */
    @Excel(name = "地区省份")
    private String area;

    /** 造林面积总计（公顷） */
    @Excel(name = "造林面积总计", readConverterExp = "公=顷")
    private Double afforestionAreaTotal;

    /** 人工造林合计 */
    @Excel(name = "人工造林合计", defaultValue = "0")
    private Double artificialAfforestionTotal;

    /** 灌木林面积 */
    @Excel(name = "灌木林面积")
    private Double shrubArea;

    /** 飞播造林 */
    @Excel(name = "飞播造林")
    private Double airSendAfforest;

    /** 当年新封山（沙）育林合计 */
    @Excel(name = "当年新封山")
    private Double newAfforestTotal;

    /** 无林地和疏林地新封山育林面积 */
    @Excel(name = "无林地和疏林地新封山育林面积")
    private Double noNewAfforestArea;

    /** 有林地和灌木林地新封山育林面积 */
    @Excel(name = "有林地和灌木林地新封山育林面积")
    private Double haveNewAfforestArea;

    /** 退化林修复面积 */
    @Excel(name = "退化林修复面积")
    private Double degraRepairArea;

    /** 森林抚育面积（公顷） */
    @Excel(name = "森林抚育面积")
    private Double forestTendArea;

    /** 年末实有封山育林面积 */
    @Excel(name = "年末实有封山育林面积")
    private Double yearendAfforestArea;

    /** 年末实有森林管护面积合计（公顷） */
    @Excel(name = "年末实有森林管护面积合计")
    @NumberFormat(pattern="###.##")
    private Double manageAreaTotal;

    /** 国有林 */
    @Excel(name = "国有林")
    private Double nationalForest;

    /** 集体和个人所有的国家级公益林（公顷） */
    @Excel(name = "集体和个人所有的国家级公益林", readConverterExp = "公顷")
    private Double nationalPublicForest;

    /** 集体和个人所有的地方公益林（公顷） */
    @Excel(name = "集体和个人所有的地方公益林", readConverterExp = "公顷")
    private Double localPublicForest;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public Long getConservationId() {
        return conservationId;
    }

    public void setConservationId(Long conservationId) {
        this.conservationId = conservationId;
    }

    public void setAnnual(String annual)
    {
        this.annual = annual;
    }

    public String getAnnual()
    {
        return annual;
    }
    public void setArea(String area)
    {
        this.area = area;
    }

    public String getArea()
    {
        return area;
    }
    public void setAfforestionAreaTotal(Double afforestionAreaTotal)
    {
        this.afforestionAreaTotal = afforestionAreaTotal;
    }

    public Double getAfforestionAreaTotal()
    {
        return afforestionAreaTotal;
    }
    public void setArtificialAfforestionTotal(Double artificialAfforestionTotal)
    {
        this.artificialAfforestionTotal = artificialAfforestionTotal;
    }

    public Double getArtificialAfforestionTotal()
    {
        return artificialAfforestionTotal;
    }
    public void setShrubArea(Double shrubArea)
    {
        this.shrubArea = shrubArea;
    }

    public Double getShrubArea()
    {
        return shrubArea;
    }
    public void setAirSendAfforest(Double airSendAfforest)
    {
        this.airSendAfforest = airSendAfforest;
    }

    public Double getAirSendAfforest()
    {
        return airSendAfforest;
    }
    public void setNewAfforestTotal(Double newAfforestTotal)
    {
        this.newAfforestTotal = newAfforestTotal;
    }

    public Double getNewAfforestTotal() {
        return newAfforestTotal;
    }
    public void setNoNewAfforestArea(Double noNewAfforestArea)
    {
        this.noNewAfforestArea = noNewAfforestArea;
    }

    public Double getNoNewAfforestArea()
    {
        return noNewAfforestArea;
    }
    public void setHaveNewAfforestArea(Double haveNewAfforestArea)
    {
        this.haveNewAfforestArea = haveNewAfforestArea;
    }

    public Double getHaveNewAfforestArea()
    {
        return haveNewAfforestArea;
    }
    public void setDegraRepairArea(Double degraRepairArea)
    {
        this.degraRepairArea = degraRepairArea;
    }

    public Double getDegraRepairArea()
    {
        return degraRepairArea;
    }
    public void setForestTendArea(Double forestTendArea)
    {
        this.forestTendArea = forestTendArea;
    }

    public Double getForestTendArea()
    {
        return forestTendArea;
    }
    public void setYearendAfforestArea(Double yearendAfforestArea)
    {
        this.yearendAfforestArea = yearendAfforestArea;
    }

    public Double getYearendAfforestArea()
    {
        return yearendAfforestArea;
    }
    public void setManageAreaTotal(Double manageAreaTotal)
    {
        this.manageAreaTotal = manageAreaTotal;
    }

    public Double getManageAreaTotal()
    {
        return manageAreaTotal;
    }
    public void setNationalForest(Double nationalForest)
    {
        this.nationalForest = nationalForest;
    }

    public Double getNationalForest()
    {
        return nationalForest;
    }
    public void setNationalPublicForest(Double nationalPublicForest)
    {
        this.nationalPublicForest = nationalPublicForest;
    }

    public Double getNationalPublicForest()
    {
        return nationalPublicForest;
    }
    public void setLocalPublicForest(Double localPublicForest)
    {
        this.localPublicForest = localPublicForest;
    }

    public Double getLocalPublicForest()
    {
        return localPublicForest;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("conservationId",getConservationId())
                .append("annual", getAnnual())
                .append("area", getArea())
                .append("afforestionAreaTotal", getAfforestionAreaTotal())
                .append("artificialAfforestionTotal", getArtificialAfforestionTotal())
                .append("shrubArea", getShrubArea())
                .append("airSendAfforest", getAirSendAfforest())
                .append("newAfforestTotal", getNewAfforestTotal())
                .append("noNewAfforestArea", getNoNewAfforestArea())
                .append("haveNewAfforestArea", getHaveNewAfforestArea())
                .append("degraRepairArea", getDegraRepairArea())
                .append("forestTendArea", getForestTendArea())
                .append("yearendAfforestArea", getYearendAfforestArea())
                .append("manageAreaTotal", getManageAreaTotal())
                .append("nationalForest", getNationalForest())
                .append("nationalPublicForest", getNationalPublicForest())
                .append("localPublicForest", getLocalPublicForest())
                .toString();
    }
}
