package com.atguigu.srb.rabbitutil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description： MQService
 *
 * @author: 段世超
 * @aate: Created in 2022/4/27 17:07
 */
@Service
@Slf4j
public class MQServcie {

    @Resource
    private AmqpTemplate amqpTemplate;

    public boolean sendMessage(String exchange, String routingKey, Object message){
        log.info("发送消息。。。。。。");
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        return true;
    }

}
