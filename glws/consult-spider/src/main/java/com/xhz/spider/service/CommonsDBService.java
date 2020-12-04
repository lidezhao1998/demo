package com.xhz.spider.service;

import com.xhz.spider.mapper.SpiderMapper;
import com.xhz.spider.pojo.BasePromotion;
import com.xhz.spider.pojo.BaseWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/3
 */
@Service
public class CommonsDBService{
    @Autowired
    private SpiderMapper spiderMapper;

    @Async
    @Transactional
    public void operationDB(BaseWeather baseArticle,String table) {
        /**
         * 查询是否垃圾数据
         */
        BaseWeather baseArt = spiderMapper.findIsNull(table, baseArticle.getDistrict(), baseArticle.getConcrete());
        /**
         * 查询是否重复数据
         */
        BaseWeather isRepetitionBaseArt = spiderMapper.findByDistrictAndConcrete(table, baseArticle.getDistrict(), baseArticle.getConcrete(), baseArticle.getWeather());

        if (baseArt != null) {
            System.out.println("删除:" + baseArt);
            spiderMapper.deleteById(table, baseArt.getId());
        }
        if (isRepetitionBaseArt == null) {
            spiderMapper.addDetails(table, baseArticle);
        }
    }
}
