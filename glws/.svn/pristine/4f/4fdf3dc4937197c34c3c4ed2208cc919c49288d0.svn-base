package com.xhz.spider.jobHandler;

import com.xhz.spider.service.CommonsService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 抓取天气详情
 *
 * @创建人 LIRENDONG
 * @创建时间 2018-12-24
 */
@JobHandler(value = "SpiderWeatherDayJobHandler")
@Component
public class SpiderWeatherDayJobHandler extends IJobHandler {

    @Autowired
    private CommonsService commonService;

    /**
     * 抓取天气详情
     *
     * @param param 定时任务参数
     * @return
     */
    @Override
    public ReturnT<String> execute(String param) {

        return (commonService.SpiderWeatherDayService(param)) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }
}