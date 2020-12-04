package com.ruoyi.web.properties;

import com.ruoyi.system.service.gis.EologicalAttributesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ApplicationRunnerImpl.class);
    @Autowired
    EologicalAttributesService ecologicalAttributesService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            ecologicalAttributesService.getCoefficient();
            log.info("通过实现ApplicationRunner接口，在项目启动后生态属性写入缓存完成");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}