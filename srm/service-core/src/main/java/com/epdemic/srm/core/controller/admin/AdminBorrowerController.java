package com.epdemic.srm.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.epdemic.srm.common.result.R;
import com.epdemic.srm.core.controller.BorrowerController;
import com.epdemic.srm.core.pojo.entity.Borrower;
import com.epdemic.srm.core.service.BorrowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理员借款管理控制器
 * @Author:estic
 * @Date: 2021/6/10 16:47
 */
@Api(tags = "借款人管理")
@RestController
@RequestMapping("/admin/core/borrower")
@Slf4j
public class AdminBorrowerController {}
