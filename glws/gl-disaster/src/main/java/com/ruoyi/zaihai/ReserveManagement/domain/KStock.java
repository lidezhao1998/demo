package com.ruoyi.zaihai.ReserveManagement.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 库存对象 k_stock
 * 
 * @author ruoyi
 * @date 2020-06-23
 */
public class KStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 调拨单ID */
    @Excel(name = "调拨单ID")
    private Long allocation;

    /** 地区 */
    @Excel(name = "地区")
    private String address;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long itemno;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 规格 */
    @Excel(name = "规格")
    private String specifications;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 库存数 */
    @Excel(name = "库存数")
    private Double inventory;

    /** 补货数量 */
    @Excel(name = "补货数量")
    private Double replenishment;

    /** 储备地点 */
    @Excel(name = "储备地点")
    private String storageLocation;

    /** 应急货物种类 */
    @Excel(name = "应急货物种类")
    private String emergencyType;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createName;

    /** 修改人 */
    @Excel(name = "修改人", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateName;

    /** 储备库ID */
    @Excel(name = "储备库ID")
    private Long reserveId;

    /** 入库表id */
    @Excel(name = "入库表id")
    private Long instockId;

    /** 出库表id */
    @Excel(name = "出库表id")
    private String outbound;

    public KStock() {
    }

    @Override
    public String toString() {
        return "KStock{" +
                "id=" + id +
                ", allocation=" + allocation +
                ", address='" + address + '\'' +
                ", itemno=" + itemno +
                ", name='" + name + '\'' +
                ", specifications='" + specifications + '\'' +
                ", brand='" + brand + '\'' +
                ", inventory=" + inventory +
                ", replenishment=" + replenishment +
                ", storageLocation='" + storageLocation + '\'' +
                ", emergencyType='" + emergencyType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                ", createName='" + createName + '\'' +
                ", updateName=" + updateName +
                ", reserveId=" + reserveId +
                ", instockId=" + instockId +
                ", outbound='" + outbound + '\'' +
                '}';
    }

    public KStock(Long id, Long allocation, String address, Long itemno, String name, String specifications, String brand, Double inventory, Double replenishment, String storageLocation, String emergencyType, String remarks, String status, String createName, Date updateName, Long reserveId, Long instockId, String outbound) {
        this.id = id;
        this.allocation = allocation;
        this.address = address;
        this.itemno = itemno;
        this.name = name;
        this.specifications = specifications;
        this.brand = brand;
        this.inventory = inventory;
        this.replenishment = replenishment;
        this.storageLocation = storageLocation;
        this.emergencyType = emergencyType;
        this.remarks = remarks;
        this.status = status;
        this.createName = createName;
        this.updateName = updateName;
        this.reserveId = reserveId;
        this.instockId = instockId;
        this.outbound = outbound;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAllocation() {
        return allocation;
    }

    public void setAllocation(Long allocation) {
        this.allocation = allocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getItemno() {
        return itemno;
    }

    public void setItemno(Long itemno) {
        this.itemno = itemno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    public Double getReplenishment() {
        return replenishment;
    }

    public void setReplenishment(Double replenishment) {
        this.replenishment = replenishment;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateName() {
        return updateName;
    }

    public void setUpdateName(Date updateName) {
        this.updateName = updateName;
    }

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public Long getInstockId() {
        return instockId;
    }

    public void setInstockId(Long instockId) {
        this.instockId = instockId;
    }

    public String getOutbound() {
        return outbound;
    }

    public void setOutbound(String outbound) {
        this.outbound = outbound;
    }

}
