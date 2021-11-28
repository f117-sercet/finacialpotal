package com.atguigu.srb.core.controller.admin;

import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import com.atguigu.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dsc
 *
 */
@Api(tags = "积分等级管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;
   @ApiOperation("获取积分等级列表")
    @GetMapping("/list")
    public List<IntegralGrade> listAll(){
        return integralGradeService.list();
    }
    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除")
    @DeleteMapping("/remove/{id}")
    public boolean removeById(
            @ApiParam(value = "数据id", required = true, example = "1")  @PathVariable Long id){
        return integralGradeService.removeById(id);
    }
}
