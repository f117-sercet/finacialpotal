package com.heepy.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description： 自定义全局类
 *
 * @author: 段世超
 * @aate: Created in 2022/3/7 10:26
 */
@Data
@ApiModel(value = "自定义全局异常类")
public class HfbException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public HfbException(String message, Integer code) {
        super(message);
        this.code = code;
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