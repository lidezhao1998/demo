package com.xhz.spider.service;


import com.xhz.spider.config.PinYinUtils;
import com.xhz.spider.mapper.SpiderMapper;
import com.xhz.spider.pojo.BaseChannel;
import com.xhz.spider.pojo.BaseTArea;
import com.xhz.spider.processor.SpiderWeatherDayProcessor;
import com.xhz.spider.processor.SpiderWeatherMonthProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LIRENDONG
 * @create 2018-12-25 17:59
 **/
@Service
public class CommonsService {

    @Autowired
    private SpiderMapper spiderMapper;
    @Autowired
    private SpiderWeatherMonthProcessor spiderDetailsMonthProcessor;
    @Autowired
    private SpiderWeatherDayProcessor spiderDetailsDayProcessor;


    /**
     * 根据url抓取详情数据
     *
     * @param param 按月爬取信息
     * @return
     */
    public boolean SpiderWeatherMonthService(String param) {
        String[] split = getParams(param);
        //根据id查询出对应的xpath
        BaseChannel consult = spiderMapper.queryById(split[0]);
        //查询所有的市集合
        List<BaseTArea> areaList = spiderMapper.getAreaNameBylevel(2);
        spiderDetailsMonthProcessor.setBaseConsult(consult);
        if (consult == null) {
            return false;
        } else {
            // IP代理池
            HttpClientDownloader ip = getIP();
            for (BaseTArea baseTArea : areaList) {
                spiderDetailsMonthProcessor.setCityName(baseTArea.getAreaname());
                String url = String.format(consult.getUrl(), baseTArea.getPinyin()).replace("*", split[1]);
                OOSpider.create(spiderDetailsMonthProcessor)
                        .addUrl(url)
                        .thread(20)
                        //.setDownloader(ip)
                        .run();
            }
            return true;
        }
    }

    /**
     * 根据url抓取详情数据
     *
     * @param param 按天爬取信息
     * @return
     */
    public boolean SpiderWeatherDayService(String param) {
        String[] split = getParams(param);
        //根据id查询出对应的xpath
        BaseChannel consult = spiderMapper.queryById(split[0]);
        //查询所有的市集合
        List<BaseTArea> areaList = spiderMapper.getAreaNameBylevel(2);
        spiderDetailsDayProcessor.setBaseConsult(consult);
        if (consult == null) {
            return false;
        } else {
            String newDate="";
            if (split.length==1) {
                LocalDate now = LocalDate.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                newDate = now.format(dtf);
            }
            // IP代理池
            HttpClientDownloader ip = getIP();
            for (BaseTArea baseTArea : areaList) {
                spiderDetailsDayProcessor.setCityName(baseTArea.getAreaname());
                String url = String.format(consult.getUrl(), baseTArea.getPinyin()).replace("*", newDate);
                OOSpider.create(spiderDetailsDayProcessor)
                        .addUrl(url)
                        .thread(10)
                        //.setDownloader(ip)
                        .run();
            }
            return true;
        }
    }

    private String[] getParams(String param) {
        String[] split = param.split(",");
        if (StringUtils.isEmpty(param)) {
            split[0] = split[0] == "" ? "1" : split[0];
        }
        return split;
    }

    /**
     * ip代理池
     */
    private HttpClientDownloader getIP() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        try {
            List<Proxy> proxies = buildProxyIP();
            System.out.println("请求代理IP： " + proxies);
            httpClientDownloader.setProxyProvider(new SimpleProxyProvider(proxies));
            return httpClientDownloader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 免费代理IP站点
     *
     * @return
     */
    private static List<Proxy> buildProxyIP() throws IOException {
        Document parse = Jsoup.parse(new URL("http://www.89ip.cn/tqdl.html?api=1&num=100&port=&address=%E5%8C%97%E4%BA%AC&isp="), 5000);
        String pattern = "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+):(\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(parse.toString());
        List<Proxy> proxies = new ArrayList<Proxy>();
        while (m.find()) {
            String[] group = m.group().split(":");
            int prot = Integer.parseInt(group[1]);
            proxies.add(new Proxy(group[0], prot));
        }
        return proxies;
    }
}
