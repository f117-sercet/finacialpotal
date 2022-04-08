package com.atguigu.srb.core.controller.api;


import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.R;
import com.atguigu.common.result.ResponseEnum;
import com.atguigu.common.util.RegexValidateUtils;
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
    public R register(@RequestBody RegisterVO registerVO) {

       String mobile = registerVO.getMobile();
       String password = registerVO.getPassword();
       String code = registerVO.getCode();

       //MoBILE_NULL_ERROR(-202,"手机号不能为空")
       Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);

       //MOBILE_ERROR(-203,"手机号不正确")
       Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

       //PASSWORD_NULL_ERROR(-204,"密码不能为空")
       Assert.isNull(password, ResponseEnum.PASSWORD_NULL_ERROR);

       //CODE_NULL_ERROR(-205,"验证码不能为空")
       Assert.notEmpty(code, ResponseEnum.CODE_NULL_ERROR);

       //校验验证码
       String codeGen = (String) redisTemplate.opsForValue().get("srb:sms:code:" + mobile);
       //CODE_ERROR(-206, "验证码不正确"),
       Assert.equals(code, codeGen, ResponseEnum.CODE_ERROR);

       //注册
       userInfoService.register(registerVO);
       return R.ok().message("注册成功");
   }





    @ApiOperation("校验手机号码是否注册")
    @GetMapping("/checkMobile/{moblie}")
    public boolean checkMobile(@PathVariable String mobile){

        return userInfoService.checkMobile(mobile);
    }

}

