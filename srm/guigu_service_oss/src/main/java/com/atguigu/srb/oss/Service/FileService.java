package com.atguigu.srb.oss.Service;

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
}

