package com.epdemic.heepay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户账户
 * @Author:estic
 * @Date: 2021/6/7 15:01
 */
@Data
@ApiModel(description = "UserAccount")
@TableName("user_account")
public class UserAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户code")
    @TableField("user_code")
    private String userCode;

    @ApiModelProperty(value = "帐户可用余额")
    @TableField("amount")
    private String amount;

    @ApiModelProperty(value = "冻结金额")
    @TableField("freeze_amount")
    private String freezeAmount;
}
