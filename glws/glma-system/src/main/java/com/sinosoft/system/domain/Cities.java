package com.sinosoft.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 代码生成业务字段表 gen_table_column
 * 
 * @author sinosoft
 */
public class Cities extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String city_code;

    private String city_name;

    private String province_code;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "id='" + id + '\'' +
                ", city_code='" + city_code + '\'' +
                ", city_name='" + city_name + '\'' +
                ", province_code='" + province_code + '\'' +
                '}';
    }
}