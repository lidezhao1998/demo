package com.xhz.spider.mapper;

import com.xhz.spider.pojo.BaseWeather;
import com.xhz.spider.pojo.BaseChannel;
import com.xhz.spider.pojo.BaseTArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LIRENDONG
 * @create 2018-12-24 12:33
 **/
@Mapper
@Component
public interface SpiderMapper {

    List<BaseTArea> getAreaNameBylevel(int level);

    BaseChannel queryById(@Param("id") String id);

    BaseWeather findByDistrictAndConcrete(@Param("table") String table, @Param("district") String district, @Param("concrete") String concrete, @Param("weather") String weather);

    void addDetails(@Param("table") String table, @Param("baseArticle") BaseWeather baseArticle);

    void deleteById(@Param("table") String table, @Param("id") int id);

    BaseWeather findIsNull(@Param("table") String table, @Param("district") String district, @Param("concrete") String concrete);

    @Update("update t_area set pinyin = #{pinyin} where areaid =#{id}")
    void updateTarea(@Param("pinyin") String pinyin,@Param("id") int id);
}
