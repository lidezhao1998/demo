package com.ruoyi.zaihai.caiji.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 草原鼠害发生与防治情况对象 c_task_sczj
 *
 * @author sudongdong
 * @date 2020-05-26
 */
public class CTaskSczj extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String number;

    /** 年度 */
    @Excel(name = "年度")
    private String year;

    /** 周 */
    @Excel(name = "周")
    private String week;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 县 */
    @Excel(name = "县")
    private String county;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 危害总面积 */
    @Excel(name = "危害总面积")
    private Double harmTotalarea=0.0;

    /** 其中工程区危害面积 */
    @Excel(name = "其中工程区危害面积")
    private Double engineeringhazard=0.0;

    /** 严重危害总面积 */
    @Excel(name = "严重危害总面积")
    private Double seriousArea =0.0;

    /** 其中区工程区严重危害面积 */
    @Excel(name = "其中区工程区严重危害面积")
    private Double engineeringSeriousArea=0.0;

    /** 化学防治 */
    @Excel(name = "化学防治")
    private Double chemistryControl=0.0;

    /** C型肉毒素 */
    @Excel(name = "C型肉毒素")
    private Double cBotulinumtoxin=0.0;

    /** D型肉毒素 */
    @Excel(name = "D型肉毒素")
    private Double dBotulinumtoxin=0.0;

    /** 雷公藤 */
    @Excel(name = "雷公藤")
    private Double tripterygium=0.0;

    /** 莪术醇 */
    @Excel(name = "莪术醇")
    private Double curcumae=0.0;

    /** 招鹰控鼠 */
    @Excel(name = "招鹰控鼠")
    private Double hawkMouse=0.0;

    /** 野化狐狸控鼠 */
    @Excel(name = "野化狐狸控鼠")
    private Double foxRat=0.0;

    /** 物理防治 */
    @Excel(name = "物理防治")
    private Double physicalControl=0.0;

    /** 防治面积 */
    @Excel(name = "防治面积")
    private Double defensiveArea=0.0;

    /** 控制面积 */
    @Excel(name = "控制面积")
    private Double controlarea=0.0;

    /** 招鹰灭鼠 */
    @Excel(name = "招鹰灭鼠")
    private Double hawkDeratization=0.0;

    /** 野化狐狸灭鼠 */
    @Excel(name = "野化狐狸灭鼠")
    private Double rodentControl=0.0;

    /** 技术人员(人/天) */
    @Excel(name = "技术人员(人/天)")
    private Double artisan=0.0;

    /** 出工(人/天) */
    @Excel(name = "出工(人/天)")
    private Double work=0.0;

    /** 飞机(架/次) */
    @Excel(name = "飞机(架/次)")
    private  Double plane=0.0;

    /** 防治器械(台/套/天) */
    @Excel(name = "防治器械(台/套/天)")
    private Double controlApparatus=0.0;

    /** 车辆(辆/天) */
    @Excel(name = "车辆(辆/天)")
    private Double car=0.0;

    /** 合计 */
    @Excel(name = "合计")
    private Double total;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private String createId;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createName;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /**级别 */
    @Excel(name = "状态")
    private String roleName;

    /**行政编码 */
    @Excel(name = "行政编码")
    private String code;

    /**国家 */
    @Excel(name = "国家")
    private String COUNTRYY;

    /** 上报周具体日期 */
   // @Excel(name = "出工(人/天)")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date weekTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCOUNTRYY() {
        return COUNTRYY;
    }

    public void setCOUNTRYY(String COUNTRYY) {
        this.COUNTRYY = COUNTRYY;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
    public void setYear(String year)
    {
        this.year = year;
    }

    public String getYear()
    {
        return year;
    }
    public void setWeek(String week)
    {
        this.week = week;
    }

    public String getWeek()
    {
        return week;
    }

     @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    public Date getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(Date weekTime) {
        this.weekTime = weekTime;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return province;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getCounty()
    {
        return county;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setHarmTotalarea(Double harmTotalarea)
    {
        this.harmTotalarea = harmTotalarea;
    }

    public Double getHarmTotalarea()
    {
        return harmTotalarea;
    }
    public void setEngineeringhazard(Double engineeringhazard)
    {
        this.engineeringhazard = engineeringhazard;
    }

    public Double getEngineeringhazard()
    {
        return engineeringhazard;
    }
    public void setSeriousArea(Double seriousArea)
    {
        this.seriousArea = seriousArea;
    }

    public Double getSeriousArea()
    {
        return seriousArea;
    }
    public void setEngineeringSeriousArea(Double engineeringSeriousArea)
    {
        this.engineeringSeriousArea = engineeringSeriousArea;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Double getEngineeringSeriousArea()
    {
        return engineeringSeriousArea;
    }
    public void setChemistryControl(Double chemistryControl)
    {
        this.chemistryControl = chemistryControl;
    }

    public Double getChemistryControl()
    {
        return chemistryControl;
    }
    public void setCBotulinumtoxin(Double cBotulinumtoxin)
    {
        this.cBotulinumtoxin = cBotulinumtoxin;
    }

    public Double getCBotulinumtoxin()
    {
        return cBotulinumtoxin;
    }
    public void setDBotulinumtoxin(Double dBotulinumtoxin)
    {
        this.dBotulinumtoxin = dBotulinumtoxin;
    }

    public Double getDBotulinumtoxin()
    {
        return dBotulinumtoxin;
    }
    public void setTripterygium(Double tripterygium)
    {
        this.tripterygium = tripterygium;
    }

    public Double getTripterygium()
    {
        return tripterygium;
    }
    public void setCurcumae(Double curcumae)
    {
        this.curcumae = curcumae;
    }

    public Double getCurcumae()
    {
        return curcumae;
    }
    public void setHawkMouse(Double hawkMouse)
    {
        this.hawkMouse = hawkMouse;
    }

    public Double getHawkMouse()
    {
        return hawkMouse;
    }
    public void setFoxRat(Double foxRat)
    {
        this.foxRat = foxRat;
    }

    public Double getFoxRat()
    {
        return foxRat;
    }
    public void setPhysicalControl(Double physicalControl)
    {
        this.physicalControl = physicalControl;
    }

    public Double getPhysicalControl()
    {
        return physicalControl;
    }
    public void setDefensiveArea(Double defensiveArea)
    {
        this.defensiveArea = defensiveArea;
    }

    public Double getDefensiveArea()
    {
        return defensiveArea;
    }
    public void setControlarea(Double controlarea)
    {
        this.controlarea = controlarea;
    }

    public Double getControlarea()
    {
        return controlarea;
    }
    public void setHawkDeratization(Double hawkDeratization)
    {
        this.hawkDeratization = hawkDeratization;
    }

    public Double getHawkDeratization()
    {
        return hawkDeratization;
    }
    public void setRodentControl(Double rodentControl)
    {
        this.rodentControl = rodentControl;
    }

    public Double getRodentControl()
    {
        return rodentControl;
    }
    public void setArtisan(Double artisan)
    {
        this.artisan = artisan;
    }

    public Double getArtisan()
    {
        return artisan;
    }
    public void setWork(Double work)
    {
        this.work = work;
    }

    public Double getWork()
    {
        return work;
    }

    public void setControlApparatus(Double controlApparatus)
    {
        this.controlApparatus = controlApparatus;
    }

    public Double getControlApparatus()
    {
        return controlApparatus;
    }
    public void setCar(Double car)
    {
        this.car = car;
    }

    public Double getCar()
    {
        return car;
    }
    public void setTotal(Double total)
    {
        this.total = total;
    }

    public Double getTotal()
    {
        return total;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }
    public void setCreatTime(Date creatTime)
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime()
    {
        return creatTime;
    }
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public String getCreateId()
    {
        return createId;
    }
    public void setCreateName(String createName)
    {
        this.createName = createName;
    }

    public String getCreateName()
    {
        return createName;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("year", getYear())
            .append("week", getWeek())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("type", getType())
            .append("harmTotalarea", getHarmTotalarea())
            .append("engineeringhazard", getEngineeringhazard())
            .append("seriousArea", getSeriousArea())
            .append("engineeringSeriousArea", getEngineeringSeriousArea())
            .append("chemistryControl", getChemistryControl())
            .append("cBotulinumtoxin", getCBotulinumtoxin())
            .append("dBotulinumtoxin", getDBotulinumtoxin())
            .append("tripterygium", getTripterygium())
            .append("curcumae", getCurcumae())
            .append("hawkMouse", getHawkMouse())
            .append("foxRat", getFoxRat())
            .append("physicalControl", getPhysicalControl())
            .append("defensiveArea", getDefensiveArea())
            .append("controlarea", getControlarea())
            .append("hawkDeratization", getHawkDeratization())
            .append("rodentControl", getRodentControl())
            .append("artisan", getArtisan())
            .append("work", getWork())
            .append("plane", getPlane())
            .append("controlApparatus", getControlApparatus())
            .append("car", getCar())
            .append("total", getTotal())
            .append("remarks", getRemarks())
            .append("creatTime", getCreatTime())
            .append("updateTime", getUpdateTime())
            .append("createId", getCreateId())
            .append("createName", getCreateName())
            .append("status", getStatus())
                .append("roleName", getRoleName())
                .append("code", getCode())
                .append("weekTime", getWeekTime())

                .toString();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getcBotulinumtoxin() {
        return cBotulinumtoxin;
    }

    public void setcBotulinumtoxin(Double cBotulinumtoxin) {
        this.cBotulinumtoxin = cBotulinumtoxin;
    }

    public Double getdBotulinumtoxin() {
        return dBotulinumtoxin;
    }

    public void setdBotulinumtoxin(Double dBotulinumtoxin) {
        this.dBotulinumtoxin = dBotulinumtoxin;
    }

    public Double getPlane() {
        return plane;
    }

    public void setPlane(Double plane) {
        this.plane = plane;
    }
}
