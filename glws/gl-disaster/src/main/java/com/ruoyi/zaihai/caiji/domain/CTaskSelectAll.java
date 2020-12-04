package com.ruoyi.zaihai.caiji.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class CTaskSelectAll extends BaseEntity {

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

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatBy;

    /** 行政编码 */
    @Excel(name = "行政编码")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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

    public Date getCreatTime() {
        return creatTime;
    }


    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("number", getNumber())
                .append("year", getYear())
                .append("week", getWeek())
                .append("province", getProvince())
                .append("city", getCity())
                .append("county", getCounty())
                .append("type", getType())
                .append("creatTime", getCreatTime())
                .append("status", getStatus())
                .append("code", getCode())
                .toString();
    }
}
