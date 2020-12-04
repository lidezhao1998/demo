package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 退牧还草工程任务领取分解对象 t_task_resolve
 * 
 * @author ruoyi
 * @date 2019-12-16
 */
public class TTaskResolve extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分解id */
    private Long resolveId;

    /** 任务发布id */
    @Excel(name = "任务发布id")
    private Long publishId;

    /** 父分解id */
    @Excel(name = "父分解id")
    private Long parentId;

    /** 分解编码 */
    @Excel(name = "分解编码")
    private String resolveCode;

    /** 分解等级 */
    @Excel(name = "分解等级")
    private String resolveLevel;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 地址id */
    @Excel(name = "地址id")
    private String addressId;

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

    /** 补播建设规模 */
    @Excel(name = "补播建设规模")
    private Double bpSize;

    /** 补播建设投资 */
    @Excel(name = "补播建设投资")
    private Double bpMoney;

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

    /** 石漠化治理建设规模 */
    @Excel(name = "石漠化治理建设规模")
    private Double smhzlSize;

    /** 石漠化治理建设投资 */
    @Excel(name = "石漠化治理建设投资")
    private Double smhzlMoney;

    /** 退化草原改良建设规模 */
    @Excel(name = "退化草原改良建设规模")
    private Double thzyglSize;

    /** 退化草原改良建设投资 */
    @Excel(name = "退化草原改良建设投资")
    private Double thzyglMoney;

    /** 黑土滩建设规模 */
    @Excel(name = "黑土滩建设规模")
    private Double httSize;

    /** 黑土滩建设投资 */
    @Excel(name = "黑土滩建设投资")
    private Double httMoney;

    /** 已垦草原治理建设规模 */
    @Excel(name = "已垦草原治理建设规模")
    private Double ykcyzlSize;

    /** 已垦草原治理建设投资 */
    @Excel(name = "已垦草原治理建设投资")
    private Double ykcazlMoney;

    /** 害草建设规模 */
    @Excel(name = "害草建设规模")
    private Double dhcSize;

    /** 毒害草建设投资 */
    @Excel(name = "毒害草建设投资")
    private Double dhcMoney;

    /** 中央投资 */
    @Excel(name = "中央投资")
    private Double zyMoney;

    /** 地方投资 */
    @Excel(name = "地方投资")
    private Double dfMoney;

    /** 状态 0 未分解 1 已领取 2 进行中 3已分解 */
    @Excel(name = "状态 0 未分解 1 已领取 2 进行中 3已分解")
    private String status;

    /** 是否删除0 删除，1正常 */
    @Excel(name = "是否删除0 删除，1正常")
    private String delStatus;


    /** 围栏合计亩数 */
    private Double wlmjCount;

    /** 围栏合计投资金额 */
    private Double wljeCount;

    /** 其他合计投资金额 */
    private Double qtjeCount;

    /** 其他合计亩数 */
    private Double qtmjCount;

    /** 总规模 */
    private Double zgmCount;

    /** 总投资 */
    private Double zjeCount;
    /** 市 */
    private String addrCity;
    /** 区县 */
    private String addrArea;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getResolveId() {
        return resolveId;
    }

    public void setResolveId(Long resolveId) {
        this.resolveId = resolveId;
    }

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResolveCode() {
        return resolveCode;
    }

    public void setResolveCode(String resolveCode) {
        this.resolveCode = resolveCode;
    }

    public String getResolveLevel() {
        return resolveLevel;
    }

    public void setResolveLevel(String resolveLevel) {
        this.resolveLevel = resolveLevel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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

    public Double getBpSize() {
        return bpSize;
    }

    public void setBpSize(Double bpSize) {
        this.bpSize = bpSize;
    }

    public Double getBpMoney() {
        return bpMoney;
    }

    public void setBpMoney(Double bpMoney) {
        this.bpMoney = bpMoney;
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

    public Double getThzyglSize() {
        return thzyglSize;
    }

    public void setThzyglSize(Double thzyglSize) {
        this.thzyglSize = thzyglSize;
    }

    public Double getThzyglMoney() {
        return thzyglMoney;
    }

    public void setThzyglMoney(Double thzyglMoney) {
        this.thzyglMoney = thzyglMoney;
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

    public Double getYkcyzlSize() {
        return ykcyzlSize;
    }

    public void setYkcyzlSize(Double ykcyzlSize) {
        this.ykcyzlSize = ykcyzlSize;
    }

    public Double getYkcazlMoney() {
        return ykcazlMoney;
    }

    public void setYkcazlMoney(Double ykcazlMoney) {
        this.ykcazlMoney = ykcazlMoney;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    public Double getWlmjCount() {
        return wlmjCount;
    }

    public void setWlmjCount(Double wlmjCount) {
        this.wlmjCount = wlmjCount;
    }

    public Double getWljeCount() {
        return wljeCount;
    }

    public void setWljeCount(Double wljeCount) {
        this.wljeCount = wljeCount;
    }

    public Double getQtjeCount() {
        return qtjeCount;
    }

    public void setQtjeCount(Double qtjeCount) {
        this.qtjeCount = qtjeCount;
    }

    public Double getQtmjCount() {
        return qtmjCount;
    }

    public void setQtmjCount(Double qtmjCount) {
        this.qtmjCount = qtmjCount;
    }

    public Double getZgmCount() {
        return zgmCount;
    }

    public void setZgmCount(Double zgmCount) {
        this.zgmCount = zgmCount;
    }

    public Double getZjeCount() {
        return zjeCount;
    }

    public void setZjeCount(Double zjeCount) {
        this.zjeCount = zjeCount;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrArea() {
        return addrArea;
    }

    public void setAddrArea(String addrArea) {
        this.addrArea = addrArea;
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
        return "TTaskResolve{" +
                "resolveId=" + resolveId +
                ", publishId=" + publishId +
                ", parentId=" + parentId +
                ", resolveCode='" + resolveCode + '\'' +
                ", resolveLevel='" + resolveLevel + '\'' +
                ", year='" + year + '\'' +
                ", province='" + province + '\'' +
                ", address='" + address + '\'' +
                ", addressId='" + addressId + '\'' +
                ", jmSize=" + jmSize +
                ", jmMoney=" + jmMoney +
                ", xmSize=" + xmSize +
                ", xmMoney=" + xmMoney +
                ", hqlxSize=" + hqlxSize +
                ", hqlxMoney=" + hqlxMoney +
                ", bpSize=" + bpSize +
                ", bpMoney=" + bpMoney +
                ", rgscdSize=" + rgscdSize +
                ", rgscdMoney=" + rgscdMoney +
                ", hspjSize=" + hspjSize +
                ", hspjMoney=" + hspjMoney +
                ", smhzlSize=" + smhzlSize +
                ", smhzlMoney=" + smhzlMoney +
                ", thzyglSize=" + thzyglSize +
                ", thzyglMoney=" + thzyglMoney +
                ", httSize=" + httSize +
                ", httMoney=" + httMoney +
                ", ykcyzlSize=" + ykcyzlSize +
                ", ykcazlMoney=" + ykcazlMoney +
                ", dhcSize=" + dhcSize +
                ", dhcMoney=" + dhcMoney +
                ", zyMoney=" + zyMoney +
                ", dfMoney=" + dfMoney +
                ", status='" + status + '\'' +
                ", delStatus='" + delStatus + '\'' +
                ", wlmjCount=" + wlmjCount +
                ", wljeCount=" + wljeCount +
                ", qtjeCount=" + qtjeCount +
                ", qtmjCount=" + qtmjCount +
                ", zgmCount=" + zgmCount +
                ", zjeCount=" + zjeCount +
                ", addrCity='" + addrCity + '\'' +
                ", addrArea='" + addrArea + '\'' +
                '}';
    }
}
