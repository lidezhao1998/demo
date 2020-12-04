package com.ruoyi.zaihai.caiji.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 草原虫害发生与防治情况对象 c_task_chfz
 * 
 * @author sudonngdong
 * @date 2020-05-26
 */
public class CTaskChfz extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
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

    /** 县区 */
    @Excel(name = "县区")
    private String county;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 危害总面积 */
    @Excel(name = "危害总面积")
    private Double harmTotalarea =0.0;

    /** 工程区危害面积 */
    @Excel(name = "工程区危害面积")
    private Double engineeringhazard =0.0;

    /** 严重危害总面积 */
    @Excel(name = "严重危害总面积")
    private Double seriousArea =0.0;

    /** 工程区严重危害面积 */
    @Excel(name = "工程区严重危害面积")
    private Double engineeringSeriousArea =0.0;

    /** 合计 */
    @Excel(name = "合计")
    private Double totalPrevention =0.0;

    /** 化学防治 */
    @Excel(name = "化学防治")
    private Double chemistryControl =0.0;

    /** 小计 */
    @Excel(name = "小计")
    private Double subtotal=0.0;

    /** 招募椋鸟 */
    @Excel(name = "招募椋鸟")
    private Double attractStarlings=0.0;

    /** 牧鸡 */
    @Excel(name = "牧鸡")
    private Double pastureChicken=0.0;

    /** 牧鸭 */
    @Excel(name = "牧鸭")
    private Double pastureDuck=0.0;

    /** 苦参碱 */
    @Excel(name = "苦参碱")
    private Double matrine=0.0;

    /** 烟碱 */
    @Excel(name = "烟碱")
    private Double nicotine=0.0;

    /** 绿僵菌 */
    @Excel(name = "绿僵菌")
    private Double greenMuscardineFungus=0.0;

    /** 印楝素 */
    @Excel(name = "印楝素")
    private Double azadirachtin=0.0;

    /** 阿维菌素 */
    @Excel(name = "阿维菌素")
    private Double avermectin=0.0;

    /** 短稳杆菌 */
    @Excel(name = "短稳杆菌")
    private Double empedobacterBrevis=0.0;

    /** 蛇床子素 */
    @Excel(name = "蛇床子素")
    private Double osthole=0.0;

    /** 阿维苏 */
    @Excel(name = "阿维苏")
    private Double weiSu=0.0;

    /** 微孢子虫 */
    @Excel(name = "微孢子虫")
    private Double enterocytozoonBieneusi=0.0;

    /** 管理技术干部（人/天） */
    @Excel(name = "管理技术干部", readConverterExp = "人=/天")
    private Double expatriateManagers;

    /** 出工（人/天） */
    @Excel(name = "出工", readConverterExp = "人=/天")
    private Double work;

    /** 飞机（架/次） */
    @Excel(name = "飞机", readConverterExp = "架=/次")
    private Double plane;

    /** 大型喷雾器（台/套/天） */
    @Excel(name = "大型喷雾器", readConverterExp = "台=/套/天")
    private Double bigSprayer;

    /** 中小型喷雾器（台/套/天） */
    @Excel(name = "中小型喷雾器", readConverterExp = "台=/套/天")
    private Double smallSprayer;

    /** 车辆（辆/天） */
    @Excel(name = "车辆", readConverterExp = "辆=/天")
    private Double car;

    /** 牧鸡（万只） */
    @Excel(name = "牧鸡", readConverterExp = "万=只")
    private Double pastureChickenNum=0.0;

    /** 牧鸭（万只） */
    @Excel(name = "牧鸭", readConverterExp = "万=只")
    private Double pastureDuckNum=0.0;

    /** 椋鸟巢（立方米） */
    @Excel(name = "椋鸟巢", readConverterExp = "立=方米")
    private Double starlingNest=0.0;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createId;

    /** null */
    @Excel(name = "null")
    private String createName;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 状态 */
    @Excel(name = "角色id")
    private String roleName;

    /** 行政编码 */
    @Excel(name = "行政编码")
    private String code;
    /** 上报周具体日期 */
    // @Excel(name = "出工(人/天)")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date weekTime;

    public Date getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(Date weekTime) {
        this.weekTime = weekTime;
    }

    public CTaskChfz(Long id, String number, String year, String week, String province, String city, String county, String type, Double harmTotalarea, Double engineeringhazard, Double seriousArea, Double engineeringSeriousArea, Double totalPrevention, Double chemistryControl, Double subtotal, Double attractStarlings, Double pastureChicken, Double pastureDuck, Double matrine, Double nicotine, Double greenMuscardineFungus, Double azadirachtin, Double avermectin, Double empedobacterBrevis, Double osthole, Double weiSu, Double enterocytozoonBieneusi, Double expatriateManagers, Double work, Double plane, Double bigSprayer, Double smallSprayer, Double car, Double pastureChickenNum, Double pastureDuckNum, Double starlingNest, String remarks, Date creatTime, String createId, String createName, String status, String roleName, String code) {
        this.id = id;
        this.number = number;
        this.year = year;
        this.week = week;
        this.province = province;
        this.city = city;
        this.county = county;
        this.type = type;
        this.harmTotalarea = harmTotalarea;
        this.engineeringhazard = engineeringhazard;
        this.seriousArea = seriousArea;
        this.engineeringSeriousArea = engineeringSeriousArea;
        this.totalPrevention = totalPrevention;
        this.chemistryControl = chemistryControl;
        this.subtotal = subtotal;
        this.attractStarlings = attractStarlings;
        this.pastureChicken = pastureChicken;
        this.pastureDuck = pastureDuck;
        this.matrine = matrine;
        this.nicotine = nicotine;
        this.greenMuscardineFungus = greenMuscardineFungus;
        this.azadirachtin = azadirachtin;
        this.avermectin = avermectin;
        this.empedobacterBrevis = empedobacterBrevis;
        this.osthole = osthole;
        this.weiSu = weiSu;
        this.enterocytozoonBieneusi = enterocytozoonBieneusi;
        this.expatriateManagers = expatriateManagers;
        this.work = work;
        this.plane = plane;
        this.bigSprayer = bigSprayer;
        this.smallSprayer = smallSprayer;
        this.car = car;
        this.pastureChickenNum = pastureChickenNum;
        this.pastureDuckNum = pastureDuckNum;
        this.starlingNest = starlingNest;
        this.remarks = remarks;
        this.creatTime = creatTime;
        this.createId = createId;
        this.createName = createName;
        this.status = status;
        this.roleName = roleName;
        this.code = code;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getHarmTotalarea() {
        return harmTotalarea;
    }

    public void setHarmTotalarea(Double harmTotalarea) {
        this.harmTotalarea = harmTotalarea;
    }

    public Double getEngineeringhazard() {
        return engineeringhazard;
    }

    public void setEngineeringhazard(Double engineeringhazard) {
        this.engineeringhazard = engineeringhazard;
    }

    public Double getSeriousArea() {
        return seriousArea;
    }

    public void setSeriousArea(Double seriousArea) {
        this.seriousArea = seriousArea;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Double getEngineeringSeriousArea() {
        return engineeringSeriousArea;
    }

    public void setEngineeringSeriousArea(Double engineeringSeriousArea) {
        this.engineeringSeriousArea = engineeringSeriousArea;
    }

    public Double getTotalPrevention() {
        return totalPrevention;
    }

    public void setTotalPrevention(Double totalPrevention) {
        this.totalPrevention = totalPrevention;
    }

    public Double getChemistryControl() {
        return chemistryControl;
    }

    public void setChemistryControl(Double chemistryControl) {
        this.chemistryControl = chemistryControl;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getAttractStarlings() {
        return attractStarlings;
    }

    public void setAttractStarlings(Double attractStarlings) {
        this.attractStarlings = attractStarlings;
    }

    public Double getPastureChicken() {
        return pastureChicken;
    }

    public void setPastureChicken(Double pastureChicken) {
        this.pastureChicken = pastureChicken;
    }

    public Double getPastureDuck() {
        return pastureDuck;
    }

    public void setPastureDuck(Double pastureDuck) {
        this.pastureDuck = pastureDuck;
    }

    public Double getMatrine() {
        return matrine;
    }

    public void setMatrine(Double matrine) {
        this.matrine = matrine;
    }

    public Double getNicotine() {
        return nicotine;
    }

    public void setNicotine(Double nicotine) {
        this.nicotine = nicotine;
    }

    public Double getGreenMuscardineFungus() {
        return greenMuscardineFungus;
    }

    public void setGreenMuscardineFungus(Double greenMuscardineFungus) {
        this.greenMuscardineFungus = greenMuscardineFungus;
    }

    public Double getAzadirachtin() {
        return azadirachtin;
    }

    public void setAzadirachtin(Double azadirachtin) {
        this.azadirachtin = azadirachtin;
    }

    public Double getAvermectin() {
        return avermectin;
    }

    public void setAvermectin(Double avermectin) {
        this.avermectin = avermectin;
    }

    public Double getEmpedobacterBrevis() {
        return empedobacterBrevis;
    }

    public void setEmpedobacterBrevis(Double empedobacterBrevis) {
        this.empedobacterBrevis = empedobacterBrevis;
    }

    public Double getOsthole() {
        return osthole;
    }

    public void setOsthole(Double osthole) {
        this.osthole = osthole;
    }

    public Double getWeiSu() {
        return weiSu;
    }

    public void setWeiSu(Double weiSu) {
        this.weiSu = weiSu;
    }

    public Double getEnterocytozoonBieneusi() {
        return enterocytozoonBieneusi;
    }

    public void setEnterocytozoonBieneusi(Double enterocytozoonBieneusi) {
        this.enterocytozoonBieneusi = enterocytozoonBieneusi;
    }

    public Double getExpatriateManagers() {
        return expatriateManagers;
    }

    public void setExpatriateManagers(Double expatriateManagers) {
        this.expatriateManagers = expatriateManagers;
    }

    public Double getWork() {
        return work;
    }

    public void setWork(Double work) {
        this.work = work;
    }

    public Double getPlane() {
        return plane;
    }

    public void setPlane(Double plane) {
        this.plane = plane;
    }

    public Double getBigSprayer() {
        return bigSprayer;
    }

    public void setBigSprayer(Double bigSprayer) {
        this.bigSprayer = bigSprayer;
    }

    public Double getSmallSprayer() {
        return smallSprayer;
    }

    public void setSmallSprayer(Double smallSprayer) {
        this.smallSprayer = smallSprayer;
    }

    public Double getCar() {
        return car;
    }

    public void setCar(Double car) {
        this.car = car;
    }

    public Double getPastureChickenNum() {
        return pastureChickenNum;
    }

    public void setPastureChickenNum(Double pastureChickenNum) {
        this.pastureChickenNum = pastureChickenNum;
    }

    public Double getPastureDuckNum() {
        return pastureDuckNum;
    }

    public void setPastureDuckNum(Double pastureDuckNum) {
        this.pastureDuckNum = pastureDuckNum;
    }

    public Double getStarlingNest() {
        return starlingNest;
    }

    public void setStarlingNest(Double starlingNest) {
        this.starlingNest = starlingNest;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CTaskChfz{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", year='" + year + '\'' +
                ", week='" + week + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", type='" + type + '\'' +
                ", harmTotalarea=" + harmTotalarea +
                ", engineeringhazard=" + engineeringhazard +
                ", seriousArea=" + seriousArea +
                ", engineeringSeriousArea=" + engineeringSeriousArea +
                ", totalPrevention=" + totalPrevention +
                ", chemistryControl=" + chemistryControl +
                ", subtotal=" + subtotal +
                ", attractStarlings=" + attractStarlings +
                ", pastureChicken=" + pastureChicken +
                ", pastureDuck=" + pastureDuck +
                ", matrine=" + matrine +
                ", nicotine=" + nicotine +
                ", greenMuscardineFungus=" + greenMuscardineFungus +
                ", azadirachtin=" + azadirachtin +
                ", avermectin=" + avermectin +
                ", empedobacterBrevis=" + empedobacterBrevis +
                ", osthole=" + osthole +
                ", weiSu=" + weiSu +
                ", enterocytozoonBieneusi=" + enterocytozoonBieneusi +
                ", expatriateManagers=" + expatriateManagers +
                ", work=" + work +
                ", plane=" + plane +
                ", bigSprayer=" + bigSprayer +
                ", smallSprayer=" + smallSprayer +
                ", car=" + car +
                ", pastureChickenNum=" + pastureChickenNum +
                ", pastureDuckNum=" + pastureDuckNum +
                ", starlingNest=" + starlingNest +
                ", remarks='" + remarks + '\'' +
                ", creatTime=" + creatTime +
                ", createId='" + createId + '\'' +
                ", createName='" + createName + '\'' +
                ", status='" + status + '\'' +
                ", roleName='" + roleName + '\'' +
                ", code='" + code + '\'' +
                ", weekTime='" + weekTime + '\'' +

                '}';
    }

    public CTaskChfz() {
    }
}
