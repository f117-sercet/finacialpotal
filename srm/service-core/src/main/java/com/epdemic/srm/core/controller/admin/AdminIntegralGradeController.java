package com.epdemic.srm.core.controller.admin;

import com.epdemic.srm.common.result.R;
import com.epdemic.srm.core.pojo.entity.IntegralGrade;
import com.epdemic.srm.core.service.IntegralGradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 积分等级积分管理
 * @Author:estic
 * @Date: 2021/6/1 9:02
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
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
}
