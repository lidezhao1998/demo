package com.ruoyi;

import com.ruoyi.common.datasource.DynamicDataSourceAnnotationAdvisor;
import com.ruoyi.common.datasource.DynamicDataSourceAnnotationInterceptor;
import com.ruoyi.common.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 *
 */
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@Import(DynamicDataSourceRegister.class)
@EnableTransactionManagement
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    @Bean
    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
    }
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");

        SpringApplication.run(RuoYiApplication.class, args);
    }
}