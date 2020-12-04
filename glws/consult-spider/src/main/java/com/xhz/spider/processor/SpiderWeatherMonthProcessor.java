package com.xhz.spider.processor;

import com.alibaba.fastjson.JSONObject;
import com.xhz.spider.pojo.BasePromotion;
import com.xhz.spider.pojo.BaseWeather;
import com.xhz.spider.service.CommonsDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
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
public class SpiderWeatherMonthProcessor extends BasePromotion implements PageProcessor {

    @Autowired
    private CommonsDBService commonsDBService;

    private Site site = Site
            .me()
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
            .setTimeOut(30000)
            .setRetryTimes(5)
            .setSleepTime(5000);

    @Override
    public void process(Page page) {
        try {
            /**
             * 获取配置信息
             */
            LinkedHashMap<String, String> linkedHashMapDetails = JSONObject.parseObject(BaseConsult.getDetail_xpath(), LinkedHashMap.class);
            List<BaseWeather> baseArticles = new ArrayList<>();
            List<String> concreteList = page.getHtml().xpath(linkedHashMapDetails.get("concrete")).all().stream().map(con -> con.trim()).collect(Collectors.toList());
            List<String> createDateList = page.getHtml().xpath(linkedHashMapDetails.get("weather_date")).all().stream().map(cre -> {
                String replaceCre = cre.replace("年", ".").replace("月", ".").replace("日", "");
                return replaceCre.trim();
            }).collect(Collectors.toList());
            List<String> weatherList = page.getHtml().xpath(linkedHashMapDetails.get("weather")).all().stream().filter(wea -> !wea.equals(" ")).map(wea -> wea.trim()).collect(Collectors.toList());
            List<String> temperatureList = page.getHtml().xpath(linkedHashMapDetails.get("temperature")).all().stream().filter(tem -> !tem.equals(" ")).map(tem -> tem.trim()).collect(Collectors.toList());
            List<String> wind_directionList = page.getHtml().xpath(linkedHashMapDetails.get("wind_direction")).all().stream().filter(wind -> !wind.equals(" ")).map(wind -> wind.trim()).collect(Collectors.toList());
            /**
             * 把数据封装到实体集合中
             */
            for (int i = 0; i < concreteList.size(); i++) {
                BaseWeather baseArticle = new BaseWeather();
                baseArticle.setDistrict(cityName);
                baseArticle.setConcrete(concreteList.get(i));
                baseArticle.setWeather(weatherList.get(i));
                baseArticle.setTemperature(temperatureList.get(i));
                baseArticle.setWind_direction(wind_directionList.get(i));
                baseArticle.setWeather_date(createDateList.get(i));
                baseArticles.add(baseArticle);
            }
            /**
             * 遍历集合操作数据库
             */
            for (BaseWeather baseArticle : baseArticles) {
                commonsDBService.operationDB(baseArticle,BaseConsult.getTable());
            }
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
