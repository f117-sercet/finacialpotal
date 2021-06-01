package com.epdemic.srm.core.controller.admin;

import com.epdemic.srm.core.pojo.entity.IntegralGrade;
import com.epdemic.srm.core.service.IntegralGradeService;
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

    @GetMapping("/list")
    public List<IntegralGrade> listAll(){
        return integralGradeService.list();
    }
    @DeleteMapping("/remove/{id}")
    public boolean removeById(@PathVariable Long id){

        return integralGradeService.removeById(id);
    }
}
