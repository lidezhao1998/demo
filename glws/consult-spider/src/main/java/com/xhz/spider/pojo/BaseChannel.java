package com.xhz.spider.pojo;

/**
 * 主实体
 *
 * @author LIRENDONG
 * @create 2018-12-24 15:18
 **/
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class BaseChannel {
    private long id;
    private String name;
    private String url;
    private String detail_xpath;
    private String table;


}
