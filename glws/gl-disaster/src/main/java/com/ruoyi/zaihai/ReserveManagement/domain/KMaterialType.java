package com.ruoyi.zaihai.ReserveManagement.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物资类型对象 k_material_type
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public class KMaterialType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物资类别id */
    private Long id;

    /** 物资编号 */
    @Excel(name = "物资编号")
    private String code;

    /** 物资类别名称 */
    @Excel(name = "物资类别名称")
    private String materialType;

    /** 物资名称 */
    @Excel(name = "物资名称")
    private String materialName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setMaterialType(String materialType) 
    {
        this.materialType = materialType;
    }

    public String getMaterialType() 
    {
        return materialType;
    }

    @Override
    public String toString() {
        return "KMaterialType{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialName='" + materialName + '\'' +
                '}';
    }
}
