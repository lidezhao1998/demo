package com.xhz.spider.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseTArea {
    protected Integer areaid;

    protected String areacode;

    protected String areaname;

    protected Byte level;

    protected String citycode;

    protected String center;

    protected Integer parentid;

    protected String pinyin;


}