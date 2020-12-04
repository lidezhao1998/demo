package com.ruoyi.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 字典数据表 sys_dict_data
 *
 * @author ruoyi
 */
public class SysDictData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典编码 */
    @Excel(name = "字典编码", cellType = ColumnType.NUMERIC)
    private Long dictCode;


    /** 父id */
    @Excel(name = "编码父ID")
    private String dictParent;

    /** 字典排序 */
    @Excel(name = "字典排序", cellType = ColumnType.NUMERIC)
    private Long dictSort;

    /** 字典标签 */
    @Excel(name = "字典标签")
    private String dictLabel;

    /** 字典键值 */
    @Excel(name = "字典键值")
    private String dictValue;

    /** 字典等级 */
    @Excel(name = "字典等级")
    private String dictLevel;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictType;

    /** 地区属性 */
    @Excel(name = "地区属性")
    private String dictAttribute;

    /** 样式属性（其他样式扩展） */
    @Excel(name = "字典样式")
    private String cssClass;

    /** 表格字典样式 */
    private String listClass;

    /** 是否默认（Y是 N否） */
    @Excel(name = "是否默认", readConverterExp = "Y=是,N=否")
    private String isDefault;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    @Override
    public String toString() {
        return "SysDictData{" +
                "dictCode=" + dictCode +
                ", dictParent='" + dictParent + '\'' +
                ", dictSort=" + dictSort +
                ", dictLabel='" + dictLabel + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictLevel='" + dictLevel + '\'' +
                ", dictType='" + dictType + '\'' +
                ", dictAttribute='" + dictAttribute + '\'' +
                ", cssClass='" + cssClass + '\'' +
                ", listClass='" + listClass + '\'' +
                ", isDefault='" + isDefault + '\'' +
                ", status='" + status + '\'' +
                ", addrProvince='" + addrProvince + '\'' +
                ", addrCity='" + addrCity + '\'' +
                ", addrArea='" + addrArea + '\'' +
                '}';
    }

    /** 市 */
    private String addrProvince;
    /** 市 */
    private String addrCity;
    /** 区县 */
    private String addrArea;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Long getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(Long dictCode)
    {
        this.dictCode = dictCode;
    }

    public Long getDictSort()
    {
        return dictSort;
    }

    public void setDictSort(Long dictSort)
    {
        this.dictSort = dictSort;
    }

    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
    public String getDictLabel()
    {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel)
    {
        this.dictLabel = dictLabel;
    }

    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
    public String getDictValue()
    {
        return dictValue;
    }

    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
    }

    public String getDictLevel()
    {
        return dictLevel;
    }

    public void setDictLevel(String dictLevel)
    {
        this.dictLevel = dictLevel;
    }
    public String getDictParent()
    {
        return dictLevel;
    }

    public void setDictParent(String dictParent)
    {
        this.dictParent = dictParent;
    }

    public String getDictAttribute() {
        return dictAttribute;
    }

    public void setDictAttribute(String dictAttribute) {
        this.dictAttribute = dictAttribute;
    }

    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    @Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
    public String getCssClass()
    {
        return cssClass;
    }

    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }

    public String getListClass()
    {
        return listClass;
    }

    public void setListClass(String listClass)
    {
        this.listClass = listClass;
    }

    public boolean getDefault()
    {
        return UserConstants.YES.equals(this.isDefault);
    }

    public String getIsDefault()
    {
        return isDefault;
    }

    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }


}
