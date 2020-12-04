package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Objects;

/**
 * 退牧还草工程任务调整记录对象 t_task_record
 * 
 * @author ruoyi
 * @date 2020-09-22
 */
public class TTaskRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务发布id */
    private Long recordId;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 地址 */
    @Excel(name = "地址")
    private String address;


    /** 围栏规模合计 */
    @Excel(name = "围栏规模合计")
    private Double wlScale = 0.0;

    /** 围栏面积合计 */
    @Excel(name = "围栏面积合计")
    private Double wlInvestment = 0.0;

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

    /** 人工饲草地建设规模 */
    @Excel(name = "人工饲草地建设规模")
    private Double rgscdSize = 0.0;

    /** 人工饲草地建设投资 */
    @Excel(name = "人工饲草地建设投资")
    private Double rgscdMoney = 0.0;

    /** 含饲棚圈建设规模 */
    @Excel(name = "含饲棚圈建设规模")
    private Double hspjSize = 0.0;

    /** 含饲棚圈建设投资 */
    @Excel(name = "含饲棚圈建设投资")
    private Double hspjMoney = 0.0;

    /** 石漠化治理建设规模 */
    @Excel(name = "石漠化治理建设规模")
    private Double smhzlSize = 0.0;

    /** 石漠化治理建设投资 */
    @Excel(name = "石漠化治理建设投资")
    private Double smhzlMoney = 0.0;

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

    /** 害草建设规模 */
    @Excel(name = "害草建设规模")
    private Double dhcSize = 0.0;

    /** 毒害草建设投资 */
    @Excel(name = "毒害草建设投资")
    private Double dhcMoney = 0.0;

    /** 中央投资 */
    @Excel(name = "中央投资")
    private Double zyMoney = 0.0;

    /** 地方投资 */
    @Excel(name = "地方投资")
    private Double dfMoney = 0.0;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 是否删除0 删除，1正常 */
    private String delStatus;

    /** 其他规模合计 */
    @Excel(name = "其他规模合计")
    private Double qtmjCount = 0.0;

    /** 其金额合计 */
    @Excel(name = "其金额合计")
    private Double qtjeCount = 0.0;

    /** 总规模合计 */
    @Excel(name = "总规模合计")
    private Double zgmCount = 0.0;

    /** 总金额 */
    @Excel(name = "总金额")
    private Double zjeCount = 0.0;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public Double getWlScale() {
        return wlScale;
    }

    public void setWlScale(Double wlScale) {
        this.wlScale = wlScale;
    }

    public Double getWlInvestment() {
        return wlInvestment;
    }

    public void setWlInvestment(Double wlInvestment) {
        this.wlInvestment = wlInvestment;
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

    public Double getQtmjCount() {
        return qtmjCount;
    }

    public void setQtmjCount(Double qtmjCount) {
        this.qtmjCount = qtmjCount;
    }

    public Double getQtjeCount() {
        return qtjeCount;
    }

    public void setQtjeCount(Double qtjeCount) {
        this.qtjeCount = qtjeCount;
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
