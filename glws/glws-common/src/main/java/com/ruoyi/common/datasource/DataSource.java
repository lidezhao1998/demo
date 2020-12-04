package com.ruoyi.common.datasource;

import java.lang.annotation.*;

/**
 * 自定义多数据源切换注解
 *
 * @author ruoyi
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default "master"; //该值即key值
}
