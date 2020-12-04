package com.ruoyi.zaihai.caiji.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 草原鼠虫害防治农药使用况对象 c_task_scny
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public class CTaskScny extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
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

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String projectType;

    /** 农药名称 */
    @Excel(name = "农药名称")
    private String pesticide;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String manufacturer;


    /** 最后登录人角色 */
    @Excel(name = "登录人角色")
    private String roleName;


    /** 农药价格（万元/吨） */
    @Excel(name = "农药价格", readConverterExp = "万=元/吨")
    private Double pesticidePrice;

    /** 订购数量（吨） */
    @Excel(name = "订购数量", readConverterExp = "吨=")
    private Double orderQuantity;

    /** 所用资金（万元） */
    @Excel(name = "所用资金", readConverterExp = "万=元")
    private Double fundsUsed;

    /** 当年防治用量（吨） */
    @Excel(name = "当年防治用量", readConverterExp = "吨=")
    private Double controlDosage;

    /** 亩用量（毫升） */
    @Excel(name = "亩用量", readConverterExp = "毫=升")
    private Double muDosage;

    /** 化学防治 */
    @Excel(name = "化学防治")
    private Double chemistryControl;

    /** 合计 */
    @Excel(name = "合计")
    private Double total;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 行政编码 */
    @Excel(name = "行政编码")
    private String code;
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

    public Double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Double orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getFundsUsed() {
        return fundsUsed;
    }

    public void setFundsUsed(Double fundsUsed) {
        this.fundsUsed = fundsUsed;
    }

    public Double getControlDosage() {
        return controlDosage;
    }

    public void setControlDosage(Double controlDosage) {
        this.controlDosage = controlDosage;
    }

    public Date getWeekTime() {
        return weekTime;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    public void setWeekTime(Date weekTime) {
        this.weekTime = weekTime;
    }

    public Double getMuDosage() {
        return muDosage;
    }

    public void setMuDosage(Double muDosage) {
        this.muDosage = muDosage;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Double getChemistryControl() {
        return chemistryControl;
    }

    public void setChemistryControl(Double chemistryControl) {
        this.chemistryControl = chemistryControl;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
    public void setProjectType(String projectType) 
    {
        this.projectType = projectType;
    }

    public String getProjectType() 
    {
        return projectType;
    }
    public void setPesticide(String pesticide) 
    {
        this.pesticide = pesticide;
    }

    public String getPesticide() 
    {
        return pesticide;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public Double getPesticidePrice() {
        return pesticidePrice;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public void setPesticidePrice(Double pesticidePrice) {
        this.pesticidePrice = pesticidePrice;
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
            .append("projectType", getProjectType())
            .append("pesticide", getPesticide())
            .append("manufacturer", getManufacturer())
            .append("pesticidePrice", getPesticidePrice())
            .append("orderQuantity", getOrderQuantity())
            .append("fundsUsed", getFundsUsed())
            .append("controlDosage", getControlDosage())
            .append("muDosage", getMuDosage())
            .append("chemistryControl", getChemistryControl())
            .append("total", getTotal())
            .append("remarks", getRemarks())
            .append("creatTime", getCreatTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("status", getStatus())
                .append("roleName", getRoleName())
                .append("code", getCode())
                .append("weekTime", getWeekTime())

                .toString();
    }

    public CTaskScny() {
    }

    public CTaskScny(Long id, String number, String year, String week, String province, String city, String county, String type, String projectType, String pesticide, String manufacturer, String roleName, Double pesticidePrice, Double orderQuantity, Double fundsUsed, Double controlDosage, Double muDosage, Double chemistryControl, Double total, String remarks, Date creatTime, String status,String code) {
        this.id = id;
        this.number = number;
        this.year = year;
        this.week = week;
        this.province = province;
        this.city = city;
        this.county = county;
        this.type = type;
        this.projectType = projectType;
        this.pesticide = pesticide;
        this.manufacturer = manufacturer;
        this.roleName = roleName;
        this.pesticidePrice = pesticidePrice;
        this.orderQuantity = orderQuantity;
        this.fundsUsed = fundsUsed;
        this.controlDosage = controlDosage;
        this.muDosage = muDosage;
        this.chemistryControl = chemistryControl;
        this.total = total;
        this.remarks = remarks;
        this.creatTime = creatTime;
        this.status = status;
        this.code = code;
    }
}
