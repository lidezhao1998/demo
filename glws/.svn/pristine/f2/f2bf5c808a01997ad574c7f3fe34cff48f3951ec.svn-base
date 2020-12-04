package com.ruoyi.zaihai.ReserveManagement.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 储备库对象 reserve
 *
 * @author ruoyi
 * @date 2020-06-02
 */
public class Reserve extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;


    /** 入库ID */
    private Long instockId;


    /** 出库ID */
    private Long outboundId;


    /** 库存ID */
    private Long stockId;


    /** 经度 */
    private String longitde;


    /** 纬度 */
    private String latitude;

    /*部门id*/
    private Long deptId;

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    /*拼接地址*/
    private String dz;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getInstockId() {
        return instockId;
    }

    public void setInstockId(Long instockId) {
        this.instockId = instockId;
    }

    public Long getOutboundId() {
        return outboundId;
    }

    public void setOutboundId(Long outboundId) {
        this.outboundId = outboundId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    /** 库名称 */
    @Excel(name = "库名称")
    private String librayName;

    /** 库管理员 */
    @Excel(name = "库管理员")
    private String librayAdmin;

    /** 地点 */
    @Excel(name = "地点")
    private String address;

    /** 覆盖范围 */
    @Excel(name = "覆盖范围")
    private String coverage;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    public String getLongitde() {
        return longitde;
    }

    public void setLongitde(String longitde) {
        this.longitde = longitde;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setLibrayName(String librayName)
    {
        this.librayName = librayName;
    }

    public String getLibrayName()
    {
        return librayName;
    }
    public void setLibrayAdmin(String librayAdmin)
    {
        this.librayAdmin = librayAdmin;
    }

    public String getLibrayAdmin()
    {
        return librayAdmin;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", instockId=" + instockId +
                ", outboundId=" + outboundId +
                ", stockId=" + stockId +
                ", longitde='" + longitde + '\'' +
                ", latitude='" + latitude + '\'' +
                ", dz='" + dz + '\'' +
                ", librayName='" + librayName + '\'' +
                ", librayAdmin='" + librayAdmin + '\'' +
                ", address='" + address + '\'' +
                ", coverage='" + coverage + '\'' +
                ", status='" + status + '\'' +
                ", deptId='" + deptId + '\'' +


                '}';
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

}
