package com.epdemic.srm.sms.service.impl;

import com.epdemic.srm.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 短信服务实现类
 * @Author:estic
 * @Date: 2021/6/2 17:33
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Override
    public void send(String mobile, String templateCode, Map<String, Object> param) {

        //创建远程的连接参数

    }
}
