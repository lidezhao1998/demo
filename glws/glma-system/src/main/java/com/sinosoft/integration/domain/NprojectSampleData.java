package com.sinosoft.integration.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 9:48
 * 非工程样地数据表
 */
public class NprojectSampleData extends BaseEntity {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date surveyTime;//调查时间
    private String surveyArea;//调查地区
    private String sampleNumber;//样地编号
    private String shrubTallPlants;//灌木或高大草木
    private String grasslandClass;//草地类
    private String grasslandType;//草地型
    private String photoName;//照片名称
    private String photoUrl;//照片路径
    private String photoRealName;//照片真实名称
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
    }

    public String getSurveyArea() {
        return surveyArea;
    }

    public void setSurveyArea(String surveyArea) {
        this.surveyArea = surveyArea;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getShrubTallPlants() {
        return shrubTallPlants;
    }

    public void setShrubTallPlants(String shrubTallPlants) {
        this.shrubTallPlants = shrubTallPlants;
    }

    public String getGrasslandClass() {
        return grasslandClass;
    }

    public void setGrasslandClass(String grasslandClass) {
        this.grasslandClass = grasslandClass;
    }

    public String getGrasslandType() {
        return grasslandType;
    }

    public void setGrasslandType(String grasslandType) {
        this.grasslandType = grasslandType;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoRealName() {
        return photoRealName;
    }

    public void setPhotoRealName(String photoRealName) {
        this.photoRealName = photoRealName;
    }
}
