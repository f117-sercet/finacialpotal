package com.epdemic.srm.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 启动类
 *
 * @author:estic
 * @Date: 2021/6/2 16:44
 */
@SpringBootApplication
@ComponentScan({"com.epdemic.srm","com.epdemic.srm.common"})
public class ServcieSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServcieSmsApplication.class, args);
    }
}

