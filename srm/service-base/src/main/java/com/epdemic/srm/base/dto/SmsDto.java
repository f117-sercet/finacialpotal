package com.epdemic.srm.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Detainted;

/**
 * 短信服务
 * @Author:estic
 * @Date: 2021/6/18 10:16
 */
@Data
@ApiModel(description = "短信")
public class SmsDto {


    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "消息内容")
    private String message;
}
