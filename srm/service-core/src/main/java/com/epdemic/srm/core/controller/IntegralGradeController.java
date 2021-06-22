package com.epdemic.srm.core.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Api(tags = "网站积分等级接口")
@RestController
@RequestMapping("/api/core/integralGrade")
public class IntegralGradeController {

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public void test(){
        return;
    }

}

