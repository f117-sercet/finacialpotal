package com.epdemic.srm.oss.service;

import java.io.InputStream;

/**
 * 文件上传
 * @Author:estic
 * @Date: 2021/6/3 15:39
 */
public interface FileService {

    /**
     * 文件上传到阿里云
     * @param inputStream
     * @param module
     * @param fileName
     * @return
     */

    String upload(InputStream inputStream, String module, String fileName);

    /**
     * 删除文件
     * @param url
     */
    void removeFile(String url);
}
