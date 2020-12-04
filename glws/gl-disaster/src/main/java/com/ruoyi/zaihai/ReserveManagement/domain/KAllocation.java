package com.ruoyi.zaihai.ReserveManagement.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 调拨单对象 k_allocation
 * 
 * @author ruoyi
 * @date 2020-06-30
 */
public class KAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 调拨单号 */
    @Excel(name = "调拨单号")
    private String oddnumber;

    /** 物资名称 */
    @Excel(name = "物资名称")
    private String dbName;

    /** 申请地区 */
    @Excel(name = "申请地区")
    private String applicationArea;

    /** 调拨地区 */
    @Excel(name = "调拨地区")
    private String allocationArea;

    /** 调出时间 */
    @Excel(name = "调出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date allocationTime;

    /** 接货人 */
    @Excel(name = "接货人")
    private String receiver;

    /** 送货人 */
    @Excel(name = "送货人")
    private String deliveryman;

    /** 接货人联系方式 */
    @Excel(name = "接货人联系方式")
    private Long receiverNumber;

    /** 送货人联系方式 */
    @Excel(name = "送货人联系方式")
    private Long deliverymanNumber;

    /** 货物编码 */
    @Excel(name = "货物编码")
    private String cargoCode;

    /** 货物数量 */
    @Excel(name = "货物数量")
    private Long cargoNumber;

    /** 货物金额 */
    @Excel(name = "货物金额")
    private String cargoMoney;

    /** 货物名称 */
    @Excel(name = "货物名称")
    private String cargoName;


    /** Z调拨单状态 */
    @Excel(name = "Z调拨单状态")
    private String status;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public String getOddnumber() {
        return oddnumber;
    }

    public void setOddnumber(String oddnumber) {
        this.oddnumber = oddnumber;
    }


    public void setApplicationArea(String applicationArea) 
    {
        this.applicationArea = applicationArea;
    }

    public String getApplicationArea() 
    {
        return applicationArea;
    }
    public void setAllocationArea(String allocationArea) 
    {
        this.allocationArea = allocationArea;
    }

    public String getAllocationArea() 
    {
        return allocationArea;
    }
    public void setAllocationTime(Date allocationTime) 
    {
        this.allocationTime = allocationTime;
    }

    public Date getAllocationTime() 
    {
        return allocationTime;
    }
    public void setReceiver(String receiver) 
    {
        this.receiver = receiver;
    }

    public String getReceiver() 
    {
        return receiver;
    }
    public void setDeliveryman(String deliveryman) 
    {
        this.deliveryman = deliveryman;
    }

    public String getDeliveryman() 
    {
        return deliveryman;
    }
    public void setReceiverNumber(Long receiverNumber) 
    {
        this.receiverNumber = receiverNumber;
    }

    public Long getReceiverNumber() 
    {
        return receiverNumber;
    }
    public void setDeliverymanNumber(Long deliverymanNumber) 
    {
        this.deliverymanNumber = deliverymanNumber;
    }

    public Long getDeliverymanNumber() 
    {
        return deliverymanNumber;
    }
    public void setCargoCode(String cargoCode) 
    {
        this.cargoCode = cargoCode;
    }

    public String getCargoCode() 
    {
        return cargoCode;
    }
    public void setCargoNumber(Long cargoNumber) 
    {
        this.cargoNumber = cargoNumber;
    }

    public Long getCargoNumber() 
    {
        return cargoNumber;
    }
    public void setCargoMoney(String cargoMoney) 
    {
        this.cargoMoney = cargoMoney;
    }

    public String getCargoMoney() 
    {
        return cargoMoney;
    }
    public void setCargoName(String cargoName) 
    {
        this.cargoName = cargoName;
    }

    public String getCargoName() 
    {
        return cargoName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("oddnumber", getOddnumber())
            .append("dbName", getDbName())
            .append("applicationArea", getApplicationArea())
            .append("allocationArea", getAllocationArea())
            .append("allocationTime", getAllocationTime())
            .append("receiver", getReceiver())
            .append("deliveryman", getDeliveryman())
            .append("receiverNumber", getReceiverNumber())
            .append("deliverymanNumber", getDeliverymanNumber())
            .append("cargoCode", getCargoCode())
            .append("cargoNumber", getCargoNumber())
            .append("cargoMoney", getCargoMoney())
            .append("cargoName", getCargoName())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
                .append("deptId", getDeptId())

                .toString();
    }
}
