package com.epdemic.srm.core.controller.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 * @Author:estic
 * @Date: 2021/6/1 9:27
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
   public Docket adminApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                /**只显示admin路径下的页面*/
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();

    }

    private ApiInfo adminApiInfo() {

        return new ApiInfoBuilder()
                .title("金融服务后台管理系统-API文档")
                .description("本文档描述金融系统后台管理系统接口")
                .version("1.0")
                .build();
    }
}
