package com.epdemic.srm.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:estic
 * @Date: 2021/5/31 17:26
 */
@SpringBootApplication
@ComponentScan({"com.epdemic.srm"})
public class ServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class,args);
    }
}
