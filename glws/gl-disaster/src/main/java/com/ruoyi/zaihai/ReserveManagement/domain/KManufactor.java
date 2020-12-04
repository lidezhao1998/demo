package com.ruoyi.zaihai.ReserveManagement.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物资对象 k_manufactor
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public class KManufactor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物资id */
    private Long materialId;

    /** 物资类型id */
    @Excel(name = "物资类型id")
    private Long materialTypeId;

    /** 物资编号 */
    @Excel(name = "物资编号")
    private String code;

    /** 物资名称 */
    @Excel(name = "物资名称")
    private String materialName;

    /** 物资类型 */
    @Excel(name = "物资类型")
    private String materialType;

    public String getMaterialType() {
        return materialType;
    }

    public KManufactor(Long materialId, Long materialTypeId, String code, String materialName, String materialType, String manufactor) {
        this.materialId = materialId;
        this.materialTypeId = materialTypeId;
        this.code = code;
        this.materialName = materialName;
        this.materialType = materialType;
        this.manufactor = manufactor;
    }

    public KManufactor() {
    }

    @Override
    public String toString() {
        return "KManufactor{" +
                "materialId=" + materialId +
                ", materialTypeId=" + materialTypeId +
                ", code='" + code + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialType='" + materialType + '\'' +
                ", manufactor='" + manufactor + '\'' +
                '}';
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    /** 厂家 */
    @Excel(name = "厂家")
    private String manufactor;

    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setMaterialTypeId(Long materialTypeId) 
    {
        this.materialTypeId = materialTypeId;
    }

    public Long getMaterialTypeId() 
    {
        return materialTypeId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setManufactor(String manufactor) 
    {
        this.manufactor = manufactor;
    }

    public String getManufactor() 
    {
        return manufactor;
    }

}
