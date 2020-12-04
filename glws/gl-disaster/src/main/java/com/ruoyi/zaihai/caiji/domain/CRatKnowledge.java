package com.ruoyi.zaihai.caiji.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 鼠害知识库信息对象 c_rat_knowledge
 *
 * @author ruoyi
 * @date 2020-09-04
 */
public class CRatKnowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 中文名称 */
    @Excel(name = "中文名称")
    private String chineseName;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyin;

    /** 拼音简写 */
    @Excel(name = "拼音简写")
    private String pinyinjx;

    /** 代码 */
    @Excel(name = "代码")
    private String code;

    /** 旧代码 */
    @Excel(name = "旧代码")
    private String oldCode;

    /** 拉丁名称 */
    @Excel(name = "拉丁名称")
    private String latinName;

    /** 别名 */
    @Excel(name = "别名")
    private String alias;

    /** 等级 */
    @Excel(name = "等级")
    private String grade;

    /** 国标名称 */
    @Excel(name = "国标名称")
    private String gbName;

    /** 国标代码 */
    @Excel(name = "国标代码")
    private String gbCode;

    /** 危害部位 */
    @Excel(name = "危害部位")
    private String hazardousParts;

    /** 危害症状 */
    @Excel(name = "危害症状")
    private String  harmfulSymptoms;

    /** 发病环境 */
    @Excel(name = "发病环境")
    private String  pathogenicEnvironment;

    /** 寄生植物 */
    @Excel(name = "寄生植物")
    private String parasiticPlants;

    /** 调查方法 */
    @Excel(name = "调查方法")
    private String surveyMethods;

    /** 流行时间 */
    @Excel(name = "流行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fashionTime;

    /** 侵染循环 */
    @Excel(name = "侵染循环")
    private String rendering;

    /** 分布特点 */
    @Excel(name = "分布特点")
    private String distribution;

    /** 国内分布 */
    @Excel(name = "国内分布")
    private String domesticDistribution;

    /** 国外分布 */
    @Excel(name = "国外分布")
    private String distributionAbroad;

    /** 区域类型 */
    @Excel(name = "区域类型")
    private String areaType;

    /** 检疫措施 */
    @Excel(name = "检疫措施")
    private String quarantineMeasures;

    /** 防治措施 */
    @Excel(name = "防治措施")
    private String preventiveMeasures;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyinjx() {
        return pinyinjx;
    }

    public void setPinyinjx(String pinyinjx) {
        this.pinyinjx = pinyinjx;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGbName() {
        return gbName;
    }

    public void setGbName(String gbName) {
        this.gbName = gbName;
    }

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
    }

    public String getHazardousParts() {
        return hazardousParts;
    }

    public void setHazardousParts(String hazardousParts) {
        this.hazardousParts = hazardousParts;
    }

    public String getHarmfulSymptoms() {
        return harmfulSymptoms;
    }

    public void setHarmfulSymptoms(String harmfulSymptoms) {
        this.harmfulSymptoms = harmfulSymptoms;
    }

    public String getPathogenicEnvironment() {
        return pathogenicEnvironment;
    }

    public void setPathogenicEnvironment(String pathogenicEnvironment) {
        this.pathogenicEnvironment = pathogenicEnvironment;
    }

    public String getParasiticPlants() {
        return parasiticPlants;
    }

    public void setParasiticPlants(String parasiticPlants) {
        this.parasiticPlants = parasiticPlants;
    }

    public String getSurveyMethods() {
        return surveyMethods;
    }

    public void setSurveyMethods(String surveyMethods) {
        this.surveyMethods = surveyMethods;
    }

    public Date getFashionTime() {
        return fashionTime;
    }

    public void setFashionTime(Date fashionTime) {
        this.fashionTime = fashionTime;
    }

    public String getRendering() {
        return rendering;
    }

    public void setRendering(String rendering) {
        this.rendering = rendering;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getDomesticDistribution() {
        return domesticDistribution;
    }

    public void setDomesticDistribution(String domesticDistribution) {
        this.domesticDistribution = domesticDistribution;
    }

    public String getDistributionAbroad() {
        return distributionAbroad;
    }

    public void setDistributionAbroad(String distributionAbroad) {
        this.distributionAbroad = distributionAbroad;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getQuarantineMeasures() {
        return quarantineMeasures;
    }

    public void setQuarantineMeasures(String quarantineMeasures) {
        this.quarantineMeasures = quarantineMeasures;
    }

    public String getPreventiveMeasures() {
        return preventiveMeasures;
    }

    public void setPreventiveMeasures(String preventiveMeasures) {
        this.preventiveMeasures = preventiveMeasures;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chineseName", getChineseName())
            .append("pinyin", getPinyin())
            .append("pinyinjx", getPinyinjx())
            .append("code", getCode())
            .append("oldCode", getOldCode())
            .append("latinName", getLatinName())
            .append("alias", getAlias())
            .append("grade", getGrade())
            .append("gbName", getGbName())
            .append("gbCode", getGbCode())
            .append("hazardousParts", getHazardousParts())
            .append("harmfulSymptoms", getHarmfulSymptoms())
            .append("pathogenicEnvironment", getPathogenicEnvironment())
            .append("parasiticPlants", getParasiticPlants())
            .append("surveyMethods", getSurveyMethods())
            .append("fashionTime", getFashionTime())
            .append("rendering", getRendering())
            .append("distribution", getDistribution())
            .append("domesticDistribution", getDomesticDistribution())
            .append("distributionAbroad", getDistributionAbroad())
            .append("areaType", getAreaType())
            .append("quarantineMeasures", getQuarantineMeasures())
            .append("preventiveMeasures", getPreventiveMeasures())
            .append("picture", getPicture())
            .append("remark", getRemark())
            .toString();
    }
}
