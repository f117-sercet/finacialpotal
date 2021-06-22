package com.epdemic.srm.sms.controller.api;

import com.epdemic.srm.common.exception.Assert;
import com.epdemic.srm.common.result.R;
import com.epdemic.srm.common.result.ResponseEnum;
import com.epdemic.srm.common.util.RandomUtils;
import com.epdemic.srm.common.util.RegexValidateUtils;
import com.epdemic.srm.sms.service.SmsService;
import com.epdemic.srm.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信发送 Controller
 * @Author:estic
 * @Date: 2021/6/3 9:56
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
@Slf4j
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

@ApiOperation("获取验证码")
@GetMapping("/send/{mobile}")
public R send(@ApiParam(value = "手机号",required =true ) @PathVariable String mobile){

    // MOBILE_NULL_ERROR(-202,"手机号不能为空")
    Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);

    //MOBILE_ERROR(-203, "手机号不正确"),
    Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

     // 生成验证码
    String code = RandomUtils.getFourBitRandom();

    // 组装短信模板参数
    Map<String,Object> param = new HashMap<>();
    param.put(code,"code");

    // 发送短信
    smsService.send(mobile, SmsProperties.TEMPLATE_CODE, param);

    // 将验证码存入redis
    redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);

    return  R.ok().message("短信发送成功");
}



}
