package com.atguigu.srb.core.controller.api;


import com.atguigu.common.result.R;
import com.atguigu.srb.core.pojo.Vo.RegisterVO;
import com.atguigu.srb.core.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("api/core/userInfo")
@Slf4j
@CrossOrigin
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisTemplate redisTemplate;


   @ApiOperation("会员注册")
   @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO){

       String mobile = registerVO.getMobile();
       String password = registerVO.getPassword();
       String code = registerVO.getCode();

       //


   }



    @ApiOperation("校验手机号码是否注册")
    @GetMapping("/checkMobile/{moblie}")
    public boolean checkMobile(@PathVariable String mobile){

        return userInfoService.checkMobile(mobile);
    }

}

