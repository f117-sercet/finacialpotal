package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.Vo.BorrowerApprovalVO;
import com.atguigu.srb.core.pojo.Vo.BorrowerDetailVO;
import com.atguigu.srb.core.pojo.Vo.BorrowerVO;
import com.atguigu.srb.core.pojo.entity.Borrower;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface BorrowerService extends IService<Borrower> {

    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(Long userId);

    IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword);

    BorrowerDetailVO getBorrowerDetailVOById(Long id);

    void approval(BorrowerApprovalVO borrowerApprovalVO);


}
