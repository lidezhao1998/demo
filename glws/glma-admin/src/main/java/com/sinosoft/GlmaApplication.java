package com.sinosoft;

import com.ruoyi.common.datasource.DynamicDataSourceAnnotationAdvisor;
import com.ruoyi.common.datasource.DynamicDataSourceAnnotationInterceptor;
import com.ruoyi.common.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/5/20 17:28
 */
/**
 * 启动程序
 */
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@Import(DynamicDataSourceRegister.class)
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class },scanBasePackages = {"com.sinosoft.*","com.ruoyi.*"})
public class GlmaApplication {
    @Bean
    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
    }
    public static void main(String[] args) {
        SpringApplication.run(GlmaApplication.class);
    }
}
