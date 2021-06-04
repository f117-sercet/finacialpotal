package com.epdemic.srm.sms.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 常量读取工具类
 * @Author:estic
 * @Date: 2021/6/2 16:57
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties implements InitializingBean {

    //    region-id: cn-hangzhou
//    key-id: LTAI4G5Svnb2TWBMuKnNT6jY
//    key-secret: N7v6R4V3EJ1SGDZlsqtqo8QyVVMmtQ
//    template-code: SMS_96695065
//    sign-name: 谷粒
    private String regionId;
    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;

    public static String REGION_Id;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String TEMPLATE_CODE;
    public static String SIGN_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_Id = regionId;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        TEMPLATE_CODE = templateCode;
        SIGN_NAME = signName;
    }
}

