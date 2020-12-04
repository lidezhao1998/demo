package com.sinosoft.extinterface.service;

import com.sinosoft.extinterface.domain.WeatherStation;

import java.util.List;

/**
 * @author sunlei
 * @description 气象数据传输接口
 * @date 2020/09/07/15:16
 */
public interface WeatherStationService {
    public List<WeatherStation> selectWeatherStationList(WeatherStation weatherStation);

    String importWeatherStation(List<WeatherStation> weatherStationList, boolean updateSupport, String operName);

    String getCityCode(String cityName);
}