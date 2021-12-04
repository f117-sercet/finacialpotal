package com.atguigu.srb.core.service;

import java.io.InputStream;

/**
 * @author:estic
 * 数据字典监听器
 */
public interface DcitSetvice {

    /**
     * 数据字典监听器
     * @param inputStream
     */

    void importData(InputStream inputStream);
}
