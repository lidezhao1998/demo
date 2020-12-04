package com.ruoyi.zaihai.caiji.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 样地对象 c_plot
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public class CPlot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long plotId;

    /** 样地ID */
    @Excel(name = "样地ID")
    private Long groundId;

    /** 取样编号 */
    @Excel(name = "取样编号")
    private String samplingNumber;

    /** 取样面积 */
    @Excel(name = "取样面积类型")
    private String samplingArea;

    /** 有害生物种类 */
    @Excel(name = "有害生物种类")
    private String harmfulSpeciesType;

    /** 具体种类 */
    @Excel(name = "具体种类")
    private String detailedType;

    /** 具体种类 */
    @Excel(name = "具体种类")
    private String sectiondType;


    /** 测定数量 */
    @Excel(name = "测定数量")
    private String quantityDetermination;

    /** 照片 */
    @Excel(name = "照片")
    private String photoUrl;



    /** 换算标准密度 */
    @Excel(name = "换算标准密度")
    private String standardDensity;

    /** 取样时间 */
    @Excel(name = "取样时间")

    private Date  samplingTime;

    /** 每株害虫数*/
    @Excel(name = "每株害虫数")
    private String  eachpestNumber;

    /** 每百平米植株数 */
    @Excel(name = "每百平米植株数")
    private Long  plantNumber;

    /** 每鼠丘平均面积 */
    @Excel(name = "每鼠丘平均面积")
    private Long  mouseSeare;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        this.status = status;
        return status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPlotId() {
        return plotId;
    }

    public Long setPlotId(Long plotId) {
        this.plotId = plotId;
        return plotId;
    }

    public Long getGroundId() {
        return groundId;
    }

    public void setGroundId(Long groundId) {
        this.groundId = groundId;
    }

    public String getSamplingNumber() {
        return samplingNumber;
    }

    public void setSamplingNumber(String samplingNumber) {
        this.samplingNumber = samplingNumber;
    }

    public String getSamplingArea() {
        return samplingArea;
    }

    public void setSamplingArea(String samplingArea) {
        this.samplingArea = samplingArea;
    }

    public String getHarmfulSpeciesType() {
        return harmfulSpeciesType;
    }

    public void setHarmfulSpeciesType(String harmfulSpeciesType) {
        this.harmfulSpeciesType = harmfulSpeciesType;
    }

    public String getDetailedType() {
        return detailedType;
    }

    public void setDetailedType(String detailedType) {
        this.detailedType = detailedType;
    }

    public String getQuantityDetermination() {
        return quantityDetermination;
    }

    public void setQuantityDetermination(String quantityDetermination) {
        this.quantityDetermination = quantityDetermination;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getStandardDensity() {
        return standardDensity;
    }

    public void setStandardDensity(String standardDensity) {
        this.standardDensity = standardDensity;
    }

    public Date getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    public String getEachpestNumber() {
        return eachpestNumber;
    }

    public void setEachpestNumber(String eachpestNumber) {
        this.eachpestNumber = eachpestNumber;
    }

    public Long getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(Long plantNumber) {
        this.plantNumber = plantNumber;
    }

    public Long getMouseSeare() {
        return mouseSeare;
    }

    public void setMouseSeare(Long mouseSeare) {
        this.mouseSeare = mouseSeare;
    }

    public String getSectiondType() {
        return sectiondType;
    }

    public void setSectiondType(String sectiondType) {
        this.sectiondType = sectiondType;
    }

    @Override
    public String toString() {
        return "CPlot{" +
                "plotId=" + plotId +
                ", groundId=" + groundId +
                ", samplingNumber='" + samplingNumber + '\'' +
                ", samplingArea='" + samplingArea + '\'' +
                ", harmfulSpeciesType='" + harmfulSpeciesType + '\'' +
                ", detailedType='" + detailedType + '\'' +
                ", sectiondType='" + sectiondType + '\'' +
                ", quantityDetermination='" + quantityDetermination + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", standardDensity='" + standardDensity + '\'' +
                ", samplingTime=" + samplingTime +
                ", eachpestNumber='" + eachpestNumber + '\'' +
                ", plantNumber=" + plantNumber +
                ", mouseSeare=" + mouseSeare +
                ", status=" + status +
                '}';
    }
}
