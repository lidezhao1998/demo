package com.ruoyi.zaihai.caiji.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.zaihai.workflow.domain.FlowIdea;

import java.util.Date;
import java.util.List;

/**
 * 地面调查数据对象 c_ground_survey
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public class CGroundSurvey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long groundId;

    /** 编码 */
    @Excel(name = "编码")
    private String codeId;

    /** 样地编号 */
    @Excel(name = "样地编号")
    private Long sampleNumber;

    /** 省级名称 */
    @Excel(name = "省级名称")
    private String provincialLevelName;

    /** 地级名称 */

    @Excel(name = "地级名称")
    private String cityLevelName;

    /** 县级名称 */
    @Excel(name = "县级名称")
    private String countyLevelName;

    /** 调查时间 */
    @Excel(name = "调查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date surveyTime;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 高程 */
    @Excel(name = "高程")
    private String elevation;

    /** 地形 */
    @Excel(name = "地形")
    private String topographyType;

    /** 坡位 */
    @Excel(name = "坡位")
    private String slopePositionType;

    /** 坡向 */
    @Excel(name = "坡向")
    private String asceptType;

    /** 坡度 */
    @Excel(name = "坡度")
    private String fallingGradient;

    /** 土壤类型 */
    @Excel(name = "土壤类型")
    private String soilType;

    /** 草地类型 */
    @Excel(name = "草地类型")
    private String grasslandType;

    /** 植被覆盖度 */
    @Excel(name = "植被覆盖度")
    private String vegetationCoverage;

    /** 地上生物量 */
    @Excel(name = "地上生物量")
    private String aboveGroundBiomass;

    /** 地表特征 */
    @Excel(name = "地表特征")
    private String surfaceFeaturesType;

    /** $column.columnComment */
    @Excel(name = "地表特征")
    private String mianBotanyType;

    /** 天敌种类 */
    @Excel(name = "天敌种类")
    private String naturalEnemyType;

    /** 填报人 */
    @Excel(name = "填报人")
    private String name;

    /** 照片 */
    @Excel(name = "照片")
    private String photoUrl;

    /** 状态 */
    @Excel(name = "状态")
    private String staute;

    /** 天地图id */
    @Excel(name = "天地图id")
    private Long mapId;

    /** 部门 */
    @Excel(name = "部门")
    private String dept;

    /** 代号 */
    @Excel(name = "代号")
    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private List<CPlot> cPlotList;

    private FlowIdea flowIdea;

    public FlowIdea getFlowIdea() {
        return flowIdea;
    }

    public void setFlowIdea(FlowIdea flowIdea) {
        this.flowIdea = flowIdea;
    }


    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setCPlotList(List<CPlot> cPlotList)
    {
        this.cPlotList = cPlotList;
    }

    public List<CPlot> getCPlotList()
    {
        return cPlotList;
    }
    public void setGroundId(Long groundId) 
    {
        this.groundId = groundId;
    }

    public Long getGroundId() 
    {
        return groundId;
    }
    public void setCodeId(String codeId) 
    {
        this.codeId = codeId;
    }

    public String getCodeId() 
    {
        return codeId;
    }
    public void setSampleNumber(Long sampleNumber) 
    {
        this.sampleNumber = sampleNumber;
    }

    public Long getSampleNumber() 
    {
        return sampleNumber;
    }
    public void setProvincialLevelName(String provincialLevelName) 
    {
        this.provincialLevelName = provincialLevelName;
    }

    public String getProvincialLevelName() 
    {
        return provincialLevelName;
    }
    public void setCityLevelName(String cityLevelName) 
    {
        this.cityLevelName = cityLevelName;
    }

    public String getCityLevelName() 
    {
        return cityLevelName;
    }
    public void setCountyLevelName(String countyLevelName) 
    {
        this.countyLevelName = countyLevelName;
    }

    public String getCountyLevelName() 
    {
        return countyLevelName;
    }
    public void setSurveyTime(Date surveyTime) 
    {
        this.surveyTime = surveyTime;
    }

    public Date getSurveyTime() 
    {
        return surveyTime;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setElevation(String elevation) 
    {
        this.elevation = elevation;
    }

    public String getElevation() 
    {
        return elevation;
    }
    public void setTopographyType(String topographyType) 
    {
        this.topographyType = topographyType;
    }

    public String getTopographyType() 
    {
        return topographyType;
    }
    public void setSlopePositionType(String slopePositionType) 
    {
        this.slopePositionType = slopePositionType;
    }

    public String getSlopePositionType() 
    {
        return slopePositionType;
    }
    public void setAsceptType(String asceptType) 
    {
        this.asceptType = asceptType;
    }

    public String getAsceptType() 
    {
        return asceptType;
    }
    public void setFallingGradient(String fallingGradient) 
    {
        this.fallingGradient = fallingGradient;
    }

    public String getFallingGradient() 
    {
        return fallingGradient;
    }
    public void setSoilType(String soilType) 
    {
        this.soilType = soilType;
    }

    public String getSoilType() 
    {
        return soilType;
    }
    public void setGrasslandType(String grasslandType) 
    {
        this.grasslandType = grasslandType;
    }

    public String getGrasslandType() 
    {
        return grasslandType;
    }
    public void setVegetationCoverage(String vegetationCoverage) 
    {
        this.vegetationCoverage = vegetationCoverage;
    }

    public String getVegetationCoverage() 
    {
        return vegetationCoverage;
    }
    public void setAboveGroundBiomass(String aboveGroundBiomass) 
    {
        this.aboveGroundBiomass = aboveGroundBiomass;
    }

    public String getAboveGroundBiomass() 
    {
        return aboveGroundBiomass;
    }
    public void setSurfaceFeaturesType(String surfaceFeaturesType) 
    {
        this.surfaceFeaturesType = surfaceFeaturesType;
    }

    public String getSurfaceFeaturesType() 
    {
        return surfaceFeaturesType;
    }
    public void setMianBotanyType(String mianBotanyType) 
    {
        this.mianBotanyType = mianBotanyType;
    }

    public String getMianBotanyType() 
    {
        return mianBotanyType;
    }
    public void setNaturalEnemyType(String naturalEnemyType) 
    {
        this.naturalEnemyType = naturalEnemyType;
    }

    public String getNaturalEnemyType() 
    {
        return naturalEnemyType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhotoUrl(String photoUrl) 
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() 
    {
        return photoUrl;
    }
    public void setStaute(String staute) 
    {
        this.staute = staute;
    }

    public String getStaute() 
    {
        return staute;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    @Override
    public String toString() {
        return "CGroundSurvey{" +
                "groundId=" + groundId +
                ", codeId='" + codeId + '\'' +
                ", sampleNumber=" + sampleNumber +
                ", provincialLevelName='" + provincialLevelName + '\'' +
                ", cityLevelName='" + cityLevelName + '\'' +
                ", countyLevelName='" + countyLevelName + '\'' +
                ", surveyTime=" + surveyTime +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", elevation='" + elevation + '\'' +
                ", topographyType='" + topographyType + '\'' +
                ", slopePositionType='" + slopePositionType + '\'' +
                ", asceptType='" + asceptType + '\'' +
                ", fallingGradient='" + fallingGradient + '\'' +
                ", soilType='" + soilType + '\'' +
                ", grasslandType='" + grasslandType + '\'' +
                ", vegetationCoverage='" + vegetationCoverage + '\'' +
                ", aboveGroundBiomass='" + aboveGroundBiomass + '\'' +
                ", surfaceFeaturesType='" + surfaceFeaturesType + '\'' +
                ", mianBotanyType='" + mianBotanyType + '\'' +
                ", naturalEnemyType='" + naturalEnemyType + '\'' +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", staute='" + staute + '\'' +
                ", mapId='" + mapId + '\'' +
                ", cPlotList=" + cPlotList +
                ", flowIdea=" + flowIdea +
                ", code=" + code +
                '}';
    }
}
