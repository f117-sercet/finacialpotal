package com.epdemic.srm.core.controller.admin;

import com.epdemic.srm.common.exception.Assert;
import com.epdemic.srm.common.result.R;
import com.epdemic.srm.common.result.ResponseEnum;
import com.epdemic.srm.core.pojo.entity.IntegralGrade;
import com.epdemic.srm.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 积分等级积分管理,前端控制器
 * @author:estic
 * @Date: 2021/6/1 9:02
 */
@Api(tags = "积分等级管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
@Slf4j
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll(){
        List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list",list).message("获取列表成功");
    }
    @ApiOperation(value = "根据id删除数据记录", notes="逻辑删除数据记录")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id", example = "100", required = true)
            @PathVariable Long id){
        boolean result = integralGradeService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("删除失败");
        }
    }
    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){

        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);

        boolean result = integralGradeService.save(integralGrade);
        if (result){
            return R.ok().message("保存成功!");
        }else{
            return R.error().message("抱歉！保存失败");
        }
    }
    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if (integralGrade !=null){
            return R.ok().data("record",integralGrade);
        }else{
            return R.error().message("数据获取失败");
        }
    }
    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if(result){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }
    }
    }

