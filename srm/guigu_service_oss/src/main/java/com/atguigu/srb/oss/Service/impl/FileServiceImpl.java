package com.atguigu.srb.oss.Service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.srb.oss.Service.FileService;
import com.atguigu.srb.oss.Util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传
 * @author:estic
 */
@Service
public class FileServiceImpl implements FileService {


        /**
         * 文件上传至阿里云
         */
        @Override
        public String upload(InputStream inputStream, String module, String fileName) {

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(
                    OssProperties.ENDPOINT,
                    OssProperties.KEY_ID,
                    OssProperties.KEY_SECRET);
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            if(!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)){
                //创建bucket
                ossClient.createBucket(OssProperties.BUCKET_NAME);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
            }

            //构建日期路径：avatar/2019/02/26/文件名
            String folder = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            //文件根路径
            String key = module + "/" + folder + "/" + fileName;

            //文件上传至阿里云
            ossClient.putObject(OssProperties.BUCKET_NAME, key, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //阿里云文件绝对路径
            return "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/" + key;
        }
    }
