package com.atguigu.sms.service;

import java.util.Map;

/**
 * @author dsc
 * 短信服务类
 */
public interface SmsService {

    void send(String mobile, String templateCode, Map<String,Object> param);
}
