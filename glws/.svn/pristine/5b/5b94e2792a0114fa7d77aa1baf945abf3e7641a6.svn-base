package com.sinosoft.extinterface.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.analyze.mapper.ProductionAnalyzeMapper;
import com.sinosoft.extinterface.domain.SpatialPlanning;
import com.sinosoft.extinterface.domain.WeatherStation;
import com.sinosoft.extinterface.mapper.WeatherStationMapper;
import com.sinosoft.extinterface.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunlei
 * @description 气象数据实现类
 * @date 2020/09/07/15:16
 */
@Service
public class WeatherStationServiceImpl implements WeatherStationService {
    @Autowired
    WeatherStationMapper weatherStationMapper;
    @Autowired
    ProductionAnalyzeMapper productionAnalyzeMapper;


    @Override
    public List<WeatherStation> selectWeatherStationList(WeatherStation newWeatherStation) {
        List<WeatherStation> weatherStations = weatherStationMapper.selectWeatherStationList(newWeatherStation);


//        for (WeatherStation station : weatherStations) {
//            WeatherStation weatherStation = new WeatherStation();
//            JSONObject jsonObject = LandSurveyServiceImpl.reverseGeocode(station.getLongitude(), station.getLatitude());
//            String province = jsonObject.getString("province");
//            String city = jsonObject.getString("city");
//            String district = jsonObject.getString("district");
//
//            weatherStation.setProvince(jsonObject.getString("province"));
//            weatherStation.setCity(jsonObject.getString("city"));
//            weatherStation.setCounty(jsonObject.getString("district"));
//            weatherStation.setId(station.getId());
//            String provincesCode= productionAnalyzeMapper.getProvincesCode(province);
//            String cityCode=productionAnalyzeMapper.getCityCode(provincesCode,city);
//            if (cityCode==null){
//                String cityNew= city.replaceAll("市", "地区");
//                cityCode=productionAnalyzeMapper.getCityCode(provincesCode,cityNew);
//            }
//            if (city.equals(province)){
//                weatherStation.setCityCode(provincesCode);
//                String countyCode=productionAnalyzeMapper.getCityCode(provincesCode ,district);
//                weatherStation.setCountyCode(countyCode);
//            }else {
//                weatherStation.setCityCode(cityCode);
//                String countyCode=productionAnalyzeMapper.getCityCode(cityCode ,district);
//                weatherStation.setCountyCode(countyCode);
//            }
//
//            weatherStation.setProvinceCode(provincesCode);
//
//            int upCount=   weatherStationMapper.updateaddFields(weatherStation);
//
//        }

        return weatherStations;
    }

    @Override
    public String importWeatherStation(List<WeatherStation> weatherStationList, boolean updateSupport, String operName) {
        return null;
    }

    @Override
    public String getCityCode(String cityName) {
        return weatherStationMapper.getCityCode(cityName);
    }
}
