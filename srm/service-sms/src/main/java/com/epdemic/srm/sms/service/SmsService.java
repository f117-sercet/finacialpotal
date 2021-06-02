package com.epdemic.srm.sms.service;

import java.util.Map;

/**
 * 短信服务
 * @Author:estic
 * @Date: 2021/6/2 17:32
 */
public interface SmsService {
    /**
     * 发送短信
     * @param mobile
     * @param templateCode
     * @param param
     */
    void send(String mobile, String templateCode, Map<String,Object> param);

}
