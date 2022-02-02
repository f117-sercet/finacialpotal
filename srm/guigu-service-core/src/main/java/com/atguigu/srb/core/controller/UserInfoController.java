package com.atguigu.srb.core.controller;


import com.atguigu.srb.core.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("校验手机号码是否注册")
    @GetMapping("/checkMobile/{moblie}")
    public boolean checkMobile(@PathVariable String mobile){

        return userInfoService.checkMobile(mobile);
    }

}

