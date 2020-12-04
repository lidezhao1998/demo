package com.ruoyi.zaihai.ReserveManagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 入库对象 k_instock
 *
 * @author ruoyi
 * @date 2020-06-16
 */
public class KInstock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 物资名称 */
    @Excel(name = "物资名称")
    private String name;

    /** 入库时间 */
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date warehousingTime;

    /** 入库方式 */
    @Excel(name = "入库方式")
    private String warehousingMode;

    /** 入库地點*/
    @Excel(name = "入库地点")
    private String address;

    /** 货物类型 */
    @Excel(name = "货物类型")
    private String type;

    /** 接货人电话 */
    @Excel(name = "接货人电话")
    private Long receiverNumber;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 厂家信息 */
    @Excel(name = "厂家信息")
    private String manufactor;

    /** 送货人 */
    @Excel(name = "送货人")
    private String deliveryPerson;

    /** 送货人电话 */
    @Excel(name = "送货人电话")
    private Long reasonNumber;

    /** 货物数量 */
    @Excel(name = "货物数量")
    private Long goodsNumber;

    /** 货物金额 */
    @Excel(name = "货物金额")
    private Long goodsMoney;

    /** 原因 */
    @Excel(name = "原因")
    private String reason;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createName;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateName;

    /** 储备库id */
    @Excel(name = "储备库id")
    private Long reserveId;
    /** 储备库id */
    @Excel(name = "出入库基本信息id")
    private Long basicId;

    /** 储备库id */
    @Excel(name = "调拨单id")
    private Long allocationId;


    /** 物资名称 */
    @Excel(name = "物资类型名称")
    private String manufactorName;

    /** 物资类型 */
    @Excel(name = "物资类型类型")
    private String manufactorType;

    /** 申请地点 */
    @Excel(name = "申请地点")
    private String applicationArea;

    public String getApplicationArea() {
        return applicationArea;
    }

    public void setApplicationArea(String applicationArea) {
        this.applicationArea = applicationArea;
    }

    public String getOddnumber() {
        return oddnumber;
    }

    public void setOddnumber(String oddnumber) {
        this.oddnumber = oddnumber;
    }

    /** 调拨单单号 */
    @Excel(name = "调拨单单号")
    private String oddnumber;



    public Long getBasicId() {
        return basicId;
    }

    public void setBasicId(Long basicId) {
        this.basicId = basicId;
    }

    public String getManufactorType() {
        return manufactorType;
    }

    public void setManufactorType(String manufactorType) {
        this.manufactorType = manufactorType;
    }

    public Long getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Long allocationId) {
        this.allocationId = allocationId;
    }




    public String getManufactorName() {
        return manufactorName;
    }

    public void setManufactorName(String manufactorName) {
        this.manufactorName = manufactorName;
    }

    @Override
    public String toString() {
        return "KInstock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", warehousingTime=" + warehousingTime +
                ", warehousingMode='" + warehousingMode + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", receiverNumber=" + receiverNumber +
                ", deliveryPerson='" + deliveryPerson + '\'' +
                ", reasonNumber=" + reasonNumber +
                ", goodsNumber=" + goodsNumber +
                ", goodsMoney=" + goodsMoney +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", createName='" + createName + '\'' +
                ", updateName='" + updateName + '\'' +
                ", reserveId=" + reserveId +
                ", model=" + model +
                ", manufactor=" + manufactor +
                  ", manufactorName=" + manufactorName +
                ", manufactorType=" + manufactorType +
                ", allocationId=" + allocationId +
                ", basicId=" + basicId +
                ", oddnumber=" + oddnumber +
                ", applicationArea=" + applicationArea +



                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }



    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setWarehousingTime(Date warehousingTime)
    {
        this.warehousingTime = warehousingTime;
    }

    public Date getWarehousingTime()
    {
        return warehousingTime;
    }
    public void setWarehousingMode(String warehousingMode)
    {
        this.warehousingMode = warehousingMode;
    }

    public String getWarehousingMode()
    {
        return warehousingMode;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setReceiverNumber(Long receiverNumber)
    {
        this.receiverNumber = receiverNumber;
    }

    public Long getReceiverNumber()
    {
        return receiverNumber;
    }


    public void setReasonNumber(Long reasonNumber)
    {
        this.reasonNumber = reasonNumber;
    }

    public Long getReasonNumber()
    {
        return reasonNumber;
    }
    public void setGoodsNumber(Long goodsNumber)
    {
        this.goodsNumber = goodsNumber;
    }

    public Long getGoodsNumber()
    {
        return goodsNumber;
    }
    public void setGoodsMoney(Long goodsMoney)
    {
        this.goodsMoney = goodsMoney;
    }

    public Long getGoodsMoney()
    {
        return goodsMoney;
    }
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setCreateName(String createName)
    {
        this.createName = createName;
    }

    public String getCreateName()
    {
        return createName;
    }
    public void setUpdateName(String updateName)
    {
        this.updateName = updateName;
    }

    public String getUpdateName()
    {
        return updateName;
    }
    public void setReserveId(Long reserveId)
    {
        this.reserveId = reserveId;
    }

    public Long getReserveId()
    {
        return reserveId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }
}
