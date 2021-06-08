package com.epdemic.heepay.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义全局异常
 * @Author:estic
 * @Date: 2021/6/8 9:05
 */
@Data
@ApiModel(value = "自定义全局异常")
public class HfbException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param messgae
     * @param code
     */
    public HfbException(String messgae,Integer code){

        super(messgae);
        this.code=code;
    }

    public HfbException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
