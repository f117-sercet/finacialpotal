package com.epdemic.srm.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author:estic
 * @Date: 2021/5/31 17:26
 */
@SpringBootApplication
@ComponentScan({"com.epdemic.srm"})
@EnableSwagger2
public class ServiceCoreApplication {

    public static void main (String[]args){
            SpringApplication.run(ServiceCoreApplication.class, args);

        }
    }

