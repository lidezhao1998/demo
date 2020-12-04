package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 退牧还草工程进度上报对象 t_task_report
 *
 * @author LiuHongfei
 * @date 2019-11-29
 */
public class TTaskReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 上报id
     */
    private Long reportId;

    /**
     * 分解id
     */
    @Excel(name = "分解id")
    private Long resolveId;

    /**
     * 年份
     */
    @Excel(name = "年份")
    private String year;

    /**
     * 省份
     */
    @Excel(name = "省份")
    private String province;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 地址id
     */
    @Excel(name = "地址id")
    private String addressId;

    /**
     * 时间刻度
     */
    @Excel(name = "时间刻度")
    private String timeScale;

    /** 禁牧建设规模 */
    @Excel(name = "禁牧建设规模")
    private Double jmSize;

    /** 禁牧建设投资 */
    @Excel(name = "禁牧建设投资")
    private Double jmMoney;

    /** 休牧建设规模 */
    @Excel(name = "休牧建设规模")
    private Double xmSize;

    /** 休牧建设投资 */
    @Excel(name = "休牧建设投资")
    private Double xmMoney;

    /** 划区轮休建设规模 */
    @Excel(name = "划区轮休建设规模")
    private Double hqlxSize;

    /** 划区轮休建设投资 */
    @Excel(name = "划区轮休建设投资")
    private Double hqlxMoney;

    /** 石漠化治理建设规模 */
    @Excel(name = "石漠化治理建设规模")
    private Double smhzlSize;

    /** 石漠化治理建设投资 */
    @Excel(name = "石漠化治理建设投资")
    private Double smhzlMoney;

    /** 退化草原改良建设规模 */
    @Excel(name = "退化草原改良建设规模")
    private Double thcyglSize;

    /** 退化草原改良建设投资 */
    @Excel(name = "退化草原改良建设投资")
    private Double thcyglMoney;

    /** 人工饲草地建设规模 */
    @Excel(name = "人工饲草地建设规模")
    private Double rgscdSize;

    /** 人工饲草地建设投资 */
    @Excel(name = "人工饲草地建设投资")
    private Double rgscdMoney;

    /** 含饲棚圈建设规模 */
    @Excel(name = "含饲棚圈建设规模")
    private Double hspjSize;

    /** 含饲棚圈建设投资 */
    @Excel(name = "含饲棚圈建设投资")
    private Double hspjMoney;

    /** 黑土滩建设规模 */
    @Excel(name = "黑土滩建设规模")
    private Double httSize;

    /** 黑土滩建设投资 */
    @Excel(name = "黑土滩建设投资")
    private Double httMoney;

    /** 害草建设规模 */
    @Excel(name = "害草建设规模")
    private Double dhcSize;

    /** 毒害草建设投资 */
    @Excel(name = "毒害草建设投资")
    private Double dhcMoney;

    /** 已垦草原治理建设规模 */
    @Excel(name = "已垦草原治理建设规模")
    private Double ykcyzlSize;

    /** 已垦草原治理建设投资 */
    @Excel(name = "已垦草原治理建设投资")
    private Double ykcyzlMoney;

    /** 中央投资 */
    @Excel(name = "中央投资")
    private Double zyMoney;

    /** 地方投资 */
    @Excel(name = "地方投资")
    private Double dfMoney;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 状态：0 待审核，1驳回，2已通过
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 是否删除0 删除，1正常
     */
    @Excel(name = "是否删除0 删除，1正常")
    private String delStatus;

    /**
     * 审核人id
     */
    @Excel(name = "审核人id")
    private Double auditId;

    /**
     * 审核人名称
     */
    @Excel(name = "审核人名称")
    private String auditName;

    /**
     * 审核时间
     */
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /**
     * 审核状态
     */
    @Excel(name = "审核状态")
    private String auditStatus;

    /**
     * 围栏投资合计
     */
    @Excel(name = "围栏投资合计")
    private Double wlInvestment;

    /**
     * 围栏规模合计
     */
    @Excel(name = "围栏规模合计")
    private Double wlScale;

    /**
     * 总规模合计
     */
    @Excel(name = "总规模合计")
    private Double totalScale;

    /**
     * 总投资合计
     */
    @Excel(name = "总投资合计")
    private Double totalInvestment;

    /**
     * 省规模合计
     */
    @Excel(name = "省规模合计")
    private Double provinceScale;

    //表格合并的名字
    private String tmhcname="退牧还草";

    private String hmhname;

    public String getHmhname() {
        return hmhname;
    }

    public void setHmhname(String hmhname) {
        this.hmhname = hmhname;
    }

    public String getTmhcname() {
        return tmhcname;
    }

    public void setTmhcname(String tmhcname) {
        this.tmhcname = tmhcname;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setResolveId(Long resolveId) {
        this.resolveId = resolveId;
    }

    public Long getResolveId() {
        return resolveId;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public Double getJmSize() {
        return jmSize;
    }

    public void setJmSize(Double jmSize) {
        this.jmSize = jmSize;
    }

    public Double getJmMoney() {
        return jmMoney;
    }

    public void setJmMoney(Double jmMoney) {
        this.jmMoney = jmMoney;
    }

    public Double getXmSize() {
        return xmSize;
    }

    public void setXmSize(Double xmSize) {
        this.xmSize = xmSize;
    }

    public Double getXmMoney() {
        return xmMoney;
    }

    public void setXmMoney(Double xmMoney) {
        this.xmMoney = xmMoney;
    }

    public Double getHqlxSize() {
        return hqlxSize;
    }

    public void setHqlxSize(Double hqlxSize) {
        this.hqlxSize = hqlxSize;
    }

    public Double getHqlxMoney() {
        return hqlxMoney;
    }

    public void setHqlxMoney(Double hqlxMoney) {
        this.hqlxMoney = hqlxMoney;
    }

    public Double getSmhzlSize() {
        return smhzlSize;
    }

    public void setSmhzlSize(Double smhzlSize) {
        this.smhzlSize = smhzlSize;
    }

    public Double getSmhzlMoney() {
        return smhzlMoney;
    }

    public void setSmhzlMoney(Double smhzlMoney) {
        this.smhzlMoney = smhzlMoney;
    }

    public Double getThcyglSize() {
        return thcyglSize;
    }

    public void setThcyglSize(Double thcyglSize) {
        this.thcyglSize = thcyglSize;
    }

    public Double getThcyglMoney() {
        return thcyglMoney;
    }

    public void setThcyglMoney(Double thcyglMoney) {
        this.thcyglMoney = thcyglMoney;
    }

    public Double getRgscdSize() {
        return rgscdSize;
    }

    public void setRgscdSize(Double rgscdSize) {
        this.rgscdSize = rgscdSize;
    }

    public Double getRgscdMoney() {
        return rgscdMoney;
    }

    public void setRgscdMoney(Double rgscdMoney) {
        this.rgscdMoney = rgscdMoney;
    }

    public Double getHspjSize() {
        return hspjSize;
    }

    public void setHspjSize(Double hspjSize) {
        this.hspjSize = hspjSize;
    }

    public Double getHspjMoney() {
        return hspjMoney;
    }

    public void setHspjMoney(Double hspjMoney) {
        this.hspjMoney = hspjMoney;
    }

    public Double getHttSize() {
        return httSize;
    }

    public void setHttSize(Double httSize) {
        this.httSize = httSize;
    }

    public Double getHttMoney() {
        return httMoney;
    }

    public void setHttMoney(Double httMoney) {
        this.httMoney = httMoney;
    }

    public Double getDhcSize() {
        return dhcSize;
    }

    public void setDhcSize(Double dhcSize) {
        this.dhcSize = dhcSize;
    }

    public Double getDhcMoney() {
        return dhcMoney;
    }

    public void setDhcMoney(Double dhcMoney) {
        this.dhcMoney = dhcMoney;
    }

    public Double getYkcyzlSize() {
        return ykcyzlSize;
    }

    public void setYkcyzlSize(Double ykcyzlSize) {
        this.ykcyzlSize = ykcyzlSize;
    }

    public Double getYkcyzlMoney() {
        return ykcyzlMoney;
    }

    public void setYkcyzlMoney(Double ykcyzlMoney) {
        this.ykcyzlMoney = ykcyzlMoney;
    }

    public Double getZyMoney() {
        return zyMoney;
    }

    public void setZyMoney(Double zyMoney) {
        this.zyMoney = zyMoney;
    }

    public Double getDfMoney() {
        return dfMoney;
    }

    public void setDfMoney(Double dfMoney) {
        this.dfMoney = dfMoney;
    }

    public void setTimeScale(String timeScale) {
        this.timeScale = timeScale;
    }

    public String getTimeScale() {
        return timeScale;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setAuditId(Double auditId) {
        this.auditId = auditId;
    }

    public Double getAuditId() {
        return auditId;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public Double getWlInvestment() {
        return wlInvestment;
    }

    public void setWlInvestment(Double wlInvestment) {
        this.wlInvestment = wlInvestment;
    }

    public Double getWlScale() {
        return wlScale;
    }

    public void setWlScale(Double wlScale) {
        this.wlScale = wlScale;
    }

    public Double getTotalScale() {
        return totalScale;
    }

    public void setTotalScale(Double totalScale) {
        this.totalScale = totalScale;
    }

    public Double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Double getProvinceScale() {
        return provinceScale;
    }

    public void setProvinceScale(Double provinceScale) {
        this.provinceScale = provinceScale;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("reportId", getReportId())
                .append("resolveId", getResolveId())
                .append("year", getYear())
                .append("province", getProvince())
                .append("address", getAddress())
                .append("addressId", getAddressId())
                .append("timeScale", getTimeScale())
                .append("jmSize", getJmSize())
                .append("jmMoney", getJmMoney())
                .append("xmSize", getXmSize())
                .append("xmMoney", getXmMoney())
                .append("timeScale", getTimeScale())
                .append("hqlxSize", getHqlxSize())
                .append("hqlxMoney", getHqlxMoney())
                .append("smhzlSize", getSmhzlSize())
                .append("smhzlMoney", getSmhzlMoney())
                .append("thcyglSize", getThcyglSize())
                .append("thcyglMoney", getThcyglMoney())
                .append("rgscdSize", getRgscdSize())
                .append("rgscdMoney", getRgscdMoney())
                .append("hspjSize", getHspjSize())
                .append("hspjMoney", getHspjMoney())
                .append("httSize", getHttSize())
                .append("httMoney", getHttMoney())
                .append("dhcSize", getDhcSize())
                .append("dhcMoney", getDhcMoney())
                .append("ykcyzlSize", getYkcyzlSize())
                .append("ykcyzlMoney", getYkcyzlMoney())
                .append("zyMoney", getZyMoney())
                .append("dfMoney", getDfMoney())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("status", getStatus())
                .append("delStatus", getDelStatus())
                .append("auditId", getAuditId())
                .append("auditName", getAuditName())
                .append("auditTime", getAuditTime())
                .append("auditStatus", getAuditStatus())
                .toString();
    }
}
