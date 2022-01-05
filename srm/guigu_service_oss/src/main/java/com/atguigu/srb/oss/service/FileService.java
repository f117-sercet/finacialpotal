package com.atguigu.srb.oss.service;

import java.io.InputStream;

/**
 * @author:estic
 * 文件上传服务类
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);


    /**
     * 根据路径删除文件
     * @param url
     */
    void removeFile(String url);


}

