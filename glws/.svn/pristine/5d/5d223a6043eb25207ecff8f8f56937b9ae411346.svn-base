package com.xhz.spider.processor;

import com.alibaba.fastjson.JSONObject;
import com.xhz.spider.pojo.BasePromotion;
import com.xhz.spider.pojo.BaseWeather;
import com.xhz.spider.service.CommonsDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抓取详情数据
 *
 * @author LIRENDONG
 * @create 2018-12-24 15:42
 **/
@Component
public class SpiderWeatherDayProcessor extends BasePromotion implements PageProcessor {

    @Autowired
    private CommonsDBService commonsDBService;

    private Site site = Site
            .me()
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
            .setTimeOut(30000)
            .setRetryTimes(5)
            .setSleepTime(5000);

    @Transactional
    @Override
    public void process(Page page) {
        try {
            /**
             * 获取配置信息
             */
            LinkedHashMap<String, String> linkedHashMapDetails = JSONObject.parseObject(BaseConsult.getDetail_xpath(), LinkedHashMap.class);
            String newCityName = cityName.replace("市", "");
            String concrete = page.getHtml().xpath(linkedHashMapDetails.get("concrete")).replace(newCityName + "天气预报查询", "").toString();
            String createDate = concrete.replace("年", ".").replace("月", ".").replace("日", "");
            String weatherMorning = page.getHtml().xpath(linkedHashMapDetails.get("weather_morning")).toString().trim();
            String weatherNight = page.getHtml().xpath(linkedHashMapDetails.get("weather_night")).toString().trim();
            List<String> temperatureList = page.getHtml().xpath(linkedHashMapDetails.get("temperature")).all();
            List<String> wind_directionList = page.getHtml().xpath(linkedHashMapDetails.get("wind_direction")).all();

            BaseWeather baseArticle = new BaseWeather();
            baseArticle.setDistrict(cityName);
            baseArticle.setConcrete(concrete);
            baseArticle.setWeather(weatherMorning + " / " + weatherNight);
            baseArticle.setTemperature(temperatureList.get(1) + " / " + temperatureList.get(2));
            baseArticle.setWind_direction(wind_directionList.get(1) + " / " + wind_directionList.get(2));
            baseArticle.setWeather_date(createDate);

            commonsDBService.operationDB(baseArticle,BaseConsult.getTable());
            System.out.println(cityName + ":操作结束!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {

        return site;
    }
}
