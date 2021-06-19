package com.epdemic.srm.core.controller.admin;

import com.epdemic.srm.common.result.R;
import com.epdemic.srm.core.pojo.entity.BorrowInfo;
import com.epdemic.srm.core.pojo.vo.BorrowInfoApprovalVO;
import com.epdemic.srm.core.service.BorrowInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 借款管理
 * @author:estic
 * @Date: 2021/6/19 9:18
 */
@Api(tags = "借款管理")
@RestController
@RequestMapping("/admin/core/borrowInfo")
@Slf4j
public class AdminBorrowInfoController {

    @Resource
    private BorrowInfoService borrowInfoService;

    @ApiOperation("借款信息")
    @GetMapping("/list")
    public R list(){
        List<BorrowInfo> borrowInfoList = borrowInfoService.selectList();
        return R.ok().data("list",borrowInfoList);
    }
    @ApiOperation("借款信息详情")
    @GetMapping("/show/{id}")
    public R show(
            @ApiParam(value = "借款信息id", required = true)
            @PathVariable Long id){

        Map<String, Object> borrowInfoDetail = borrowInfoService.getBorrowInfoDetail(id);
        return R.ok().data("borrowInfoDetail", borrowInfoDetail);
    }

    @ApiOperation("审批借款信息")
    @PostMapping("/approval")
    public R approval(@RequestBody BorrowInfoApprovalVO borrowInfoApprovalVO) {

        borrowInfoService.approval(borrowInfoApprovalVO);
        return R.ok().message("审批完成");
    }
}
