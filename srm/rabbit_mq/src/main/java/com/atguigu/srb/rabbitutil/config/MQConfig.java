package com.atguigu.srb.rabbitutil.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description： rabbit_mq配置
 *
 * @author: 段世超
 * @aate: Created in 2022/4/27 17:03
 */
@Configuration
public class MQConfig {
    @Bean
    public MessageConverter messageConverter(){
        //json字符串转换器
        return new Jackson2JsonMessageConverter();
    }

}
