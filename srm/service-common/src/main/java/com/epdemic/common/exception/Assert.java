package com.epdemic.common.exception;

import com.epdemic.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义断言
 * @Author:estic
 * @Date: 2021/6/1 9:58
 */
@Slf4j
public class Assert {
    /**
     * 断言对象不为空
     * obj 为空则抛出异常
     * @param obj
     * @param responseEnum
     */
    public  static void notNull(Object obj, ResponseEnum responseEnum){

        if (obj == null){
            log.info("obj is null.......");
            throw new BusinessException(responseEnum);
        }
    }
}
