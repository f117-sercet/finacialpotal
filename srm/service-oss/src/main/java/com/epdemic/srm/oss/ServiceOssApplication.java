package com.epdemic.srm.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 文件服务启动类
 * @Author:estic
 * @Date: 2021/6/3 15:06
 */
@SpringBootApplication
@ComponentScan({"com.epdemic.srm","com.epdemic.srm.common"})
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }
}
