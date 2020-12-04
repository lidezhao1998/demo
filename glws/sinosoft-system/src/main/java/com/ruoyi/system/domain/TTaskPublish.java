package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 退牧还草工程任务发布对象 t_task_publish
 *
 * @author ruoyi
 * @date 2019-12-03
 */
public class TTaskPublish extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务发布id */
    private Long publishId;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 地址id */
    @Excel(name = "地址id")
    private String addressId;

    /** 禁牧建设规模 */
    @Excel(name = "禁牧建设规模")
    private Double jmSize = 0.0;

    /** 禁牧建设投资 */
    @Excel(name = "禁牧建设投资")
    private Double jmMoney = 0.0;

    /** 休牧建设规模 */
    @Excel(name = "休牧建设规模")
    private Double xmSize = 0.0;

    /** 休牧建设投资 */
    @Excel(name = "休牧建设投资")
    private Double xmMoney = 0.0;

    /** 划区轮休建设规模 */
    @Excel(name = "划区轮休建设规模")
    private Double hqlxSize = 0.0;

    /** 划区轮休建设投资 */
    @Excel(name = "划区轮休建设投资")
    private Double hqlxMoney = 0.0;

    /** 补播建设规模 */
    @Excel(name = "补播建设规模")
    private Double bpSize = 0.0;

    /** 补播建设投资 */
    @Excel(name = "补播建设投资")
    private Double bpMoney = 0.0;

    /** 人工种草地建设规模 */
    @Excel(name = "人工种草地建设规模")
    private Double rgscdSize = 0.0;

    /** 人工种草地建设投资 */
    @Excel(name = "人工种草地建设投资")
    private Double rgscdMoney = 0.0;

    /** 含饲棚圈建设规模 */
    @Excel(name = "含饲棚圈建设规模")
    private Double hspjSize = 0.0;

    /** 含饲棚圈建设投资 */
    @Excel(name = "含饲棚圈建设投资")
    private Double hspjMoney = 0.0;

    /** 中央投资 */
    @Excel(name = "中央投资")
    private Double zyMoney = 0.0;

    /** 地方投资 */
    @Excel(name = "地方投资")
    private Double dfMoney = 0.0;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 石漠化治理建设规模 */
    @Excel(name = "石漠化治理建设规模")
    private Double smhzlSize = 0.0;

    /** 石漠化治理建设投资 */
    @Excel(name = "石漠化治理建设投资")
    private Double smhzlMoney = 0.0;

    /** 是否删除0 删除，1正常 */
    @Excel(name = "是否删除0 删除，1正常")
    private String delStatus;

    /** 退化草原改良建设规模 */
    @Excel(name = "退化草原改良建设规模")
    private Double thzyglSize = 0.0;

    /** 退化草原改良建设投资 */
    @Excel(name = "退化草原改良建设投资")
    private Double thzyglMoney = 0.0;

    /** 黑土滩建设规模 */
    @Excel(name = "黑土滩建设规模")
    private Double httSize = 0.0;

    /** 黑土滩建设投资 */
    @Excel(name = "黑土滩建设投资")
    private Double httMoney = 0.0;

    /** 已垦草原治理建设规模 */
    @Excel(name = "已垦草原治理建设规模")
    private Double ykcyzlSize;

    /** 已垦草原治理建设投资 */
    @Excel(name = "已垦草原治理建设投资")
    private Double ykcazlMoney;

    /** 害草建设规模 */
    @Excel(name = "害草建设规模")
    private Double dhcSize = 0.0;

    /** 毒害草建设投资 */
    @Excel(name = "毒害草建设投资")
    private Double dhcMoney = 0.0;

    /** 围栏合计亩数 */
    private Double wlmjCount;

    /** 围栏合计投资金额 */
    private Double wljeCount;

    /** 其他合计投资金额 */
    private Double qtjeCount;

    /** 其他合计亩数 */
    private Double qtmjCount;

    /** 石漠化和改良草地合计亩数*/
    private Double JSScale;

    /** 石漠化和改良草地合计金额*/
    private Double JSScales;

    /** 总规模 */
    private Double zgmCount;

    /** 总投资 */
    private Double zjeCount;

    /** 市 */
    private String addrProvince;
    /** 市 */
    private String addrCity;
    /** 区县 */
    private String addrArea;
    /**
     * 围栏投资合计
     */
    @Excel(name = "围栏投资合计")
    private Double wlInvestment = 0.0;

    /**
     * 围栏规模合计
     */
    @Excel(name = "围栏规模合计")
    private Double wlScale = 0.0;

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
     * 已完成规模合计
     */
    @Excel(name = "已完成规模合计")
    private Double ywcTotalScale;

    /**
     * 已完成投资合计
     */
    @Excel(name = "已完成投资合计")
    private Double ywcTotalInvestment;

    /**
     * 省规模合计
     */
    @Excel(name = "省规模合计")
    private Double provinceScale;

    /** 字典标签 */
    @Excel(name = "字典标签")
    private String dictLabel;

    /** 当前完成进度 */
    private String jindu;

    /**
     * 审核人名称
     */
    @Excel(name = "审核人名称")
    private String auditName;

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getJindu() {
        return jindu;
    }

    public void setJindu(String jindu) {
        this.jindu = jindu;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public Double getYwcTotalScale() {
        return ywcTotalScale;
    }

    public void setYwcTotalScale(Double ywcTotalScale) {
        this.ywcTotalScale = ywcTotalScale;
    }

    public Double getYwcTotalInvestment() {
        return ywcTotalInvestment;
    }

    public void setYwcTotalInvestment(Double ywcTotalInvestment) {
        this.ywcTotalInvestment = ywcTotalInvestment;
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
        return "TTaskPublish{" +
                "publishId=" + publishId +
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
                ", zyMoney=" + zyMoney +
                ", dfMoney=" + dfMoney +
                ", status='" + status + '\'' +
                ", smhzlSize=" + smhzlSize +
                ", smhzlMoney=" + smhzlMoney +
                ", delStatus='" + delStatus + '\'' +
                ", thcyglSize=" + thzyglSize +
                ", thcyglMoney=" + thzyglMoney +
                ", httSize=" + httSize +
                ", httMoney=" + httMoney +
                ", ykcyzlSize=" + ykcyzlSize +
                ", ykcazlMoney=" + ykcazlMoney +
                ", dhcSize=" + dhcSize +
                ", dhcMoney=" + dhcMoney +
                ", wlmjCount=" + wlmjCount +
                ", wljeCount=" + wljeCount +
                ", qtjeCount=" + qtjeCount +
                ", qtmjCount=" + qtmjCount +
                ", zgmCount=" + zgmCount +
                ", zjeCount=" + zjeCount +
                ", addrProvince='" + addrProvince + '\'' +
                ", addrCity='" + addrCity + '\'' +
                ", addrArea='" + addrArea + '\'' +
                ", dz='" + dz + '\'' +
                '}';
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    /** 拼接的详细地址 */
    private  String dz;

    public String getAddrProvince() {
        return addrProvince;
    }

    public void setAddrProvince(String addrProvince) {
        this.addrProvince = addrProvince;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
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




}
