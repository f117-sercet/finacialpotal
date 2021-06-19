package com.epdemic.srm.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.epdemic.srm.common.result.R;
import com.epdemic.srm.core.controller.BorrowerController;
import com.epdemic.srm.core.pojo.entity.Borrower;
import com.epdemic.srm.core.pojo.vo.BorrowerApprovalVO;
import com.epdemic.srm.core.pojo.vo.BorrowerDetailVO;
import com.epdemic.srm.core.service.BorrowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理员借款管理控制器
 * @author:estic
 * @Date: 2021/6/10 16:47
 */
@Api(tags = "借款人管理")
@RestController
@RequestMapping("/admin/core/borrower")
@Slf4j
public class AdminBorrowerController {

    @Resource
    private BorrowerService borrowerService;
    @ApiOperation("获取借款人列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(value = "查询关键字", required = false)
            @RequestParam String keyword) {

        Page<Borrower> pageParam = new Page<>(page, limit);
        IPage<Borrower> pageModel = borrowerService.listPage(pageParam, keyword);
        return R.ok().data("pageModel", pageModel);
    }
    @ApiOperation("获取借款人信息")
    @GetMapping("/show/{id}")
    public R show(
            @ApiParam(value = "借款人id", required = true)
            @PathVariable Long id){

        BorrowerDetailVO borrowerDetailVO = borrowerService.getBorrowerDetailVOById(id);
        return R.ok().data("borrowerDetailVO",borrowerDetailVO);
    }
    @ApiOperation("借款额度审批")
    @PostMapping("/approval")
    public R approval(@RequestBody BorrowerApprovalVO borrowerApprovalVO){

        borrowerService.approval(borrowerApprovalVO);
        return R.ok().message("审批完成");
    }
    }

