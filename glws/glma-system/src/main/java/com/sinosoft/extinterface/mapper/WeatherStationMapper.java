package com.sinosoft.extinterface.mapper;

import com.ruoyi.common.datasource.DataSource;
import com.sinosoft.extinterface.domain.WeatherStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunlei
 * @description 气象数据
 * @date 2020/09/07/15:18
 */
@DataSource("spider")
public interface WeatherStationMapper {
    /**
     * 查询气象数据
     * @param weatherStation
     * @return
     */
    WeatherStation selectWeatherStation(WeatherStation weatherStation);

    /**
     * 查询气象数据列表
     * @param weatherStation
     * @return
     */
    List<WeatherStation> selectWeatherStationList(WeatherStation weatherStation);


    /**
     * 修改气象数据
     */
    int updateWeatherStation(WeatherStation weatherStation);

    /**
     * 新增气象数据
     * @param weatherStation
     */
    int insertWeatherStation(WeatherStation weatherStation);

    /**
     * 删除
     * @param ids
     * @return
     */
    int deleteWeatherStationByIds(String[] ids);

    String getCityCode(@Param("cityName") String cityName);

    int updateaddFields(WeatherStation weatherStation);
}
