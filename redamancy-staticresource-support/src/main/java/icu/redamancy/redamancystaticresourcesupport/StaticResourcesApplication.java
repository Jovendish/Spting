package icu.redamancy.redamancystaticresourcesupport;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author redamancy
 * @Date 2022/5/15 21:07
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"icu.redamancy.redamancystaticresourcesupport", "icu.redamancy.common"})
@EnableDubbo
@EnableDiscoveryClient
@DubboComponentScan(basePackages = {"icu.redamancy.redamancystaticresourcesupport.core.impl"})
public class StaticResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticResourcesApplication.class, args);
    }
}
