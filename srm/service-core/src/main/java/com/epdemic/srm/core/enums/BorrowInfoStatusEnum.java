package com.epdemic.srm.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dsc
 * @date
 * 借款消息状态枚举类
 */

@AllArgsConstructor
@Getter
public enum BorrowInfoStatusEnum {

    NO_AUTH(0, "未认证"),
    CHECK_RUN(1, "审核中"),
    CHECK_OK(2, "审核通过"),
    CHECK_FAIL(-1, "审核不通过"),
    ;

    private Integer status;
    private String msg;

    public static String getMsgByStatus(int status) {
        BorrowInfoStatusEnum arrObj[] = BorrowInfoStatusEnum.values();
        for (BorrowInfoStatusEnum obj : arrObj) {
            if (status == obj.getStatus().intValue()) {
                return obj.getMsg();
            }
        }
        return "";
    }
}
