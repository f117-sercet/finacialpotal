package com.epdemic.heepay.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 通告类型
 * @Author:estic
 * @Date: 2021/6/7 14:54
 */
public class NotifyVo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String notifyUrl;

    private Map<String, Object> paramMap;

    public NotifyVo() {}

    public NotifyVo(String notifyUrl, Map<String, Object> paramMap) {
        this.notifyUrl = notifyUrl;
        this.paramMap = paramMap;
    }
}
