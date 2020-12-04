package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 代码生成业务字段表 gen_table_column
 *
 * @author ruoyi
 */
public class Areas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String city_code;

    private String area_name;

    private String area_code;


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

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    @Override
    public String toString() {
        return "Areas{" +
                "id='" + id + '\'' +
                ", city_code='" + city_code + '\'' +
                ", area_name='" + area_name + '\'' +
                ", area_code='" + area_code + '\'' +
                '}';
    }
}