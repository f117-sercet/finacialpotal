package com.epdemic.srm.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
/**
 * @author:estic
 * @date
 * 还款方法枚举
 */
public enum ReturnMethodEnum {

    ONE(1, "等额本息"),
    TWO(2, "等额本金"),
    THREE(3, "每月还息一次还本"),
    FOUR(4, "一次还本还息"),
    ;

    private Integer method;
    private String msg;
}
