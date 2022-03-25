package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.pojo.Vo.BorrowerApprovalVO;
import com.atguigu.srb.core.pojo.Vo.BorrowerDetailVO;
import com.atguigu.srb.core.pojo.Vo.BorrowerVO;
import com.atguigu.srb.core.pojo.entity.Borrower;
import com.atguigu.srb.core.mapper.BorrowerMapper;
import com.atguigu.srb.core.service.BorrowerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 借款人 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, Borrower> implements BorrowerService {

    @Override
    public void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId) {

    }

    @Override
    public Integer getStatusByUserId(Long userId) {
        return null;
    }

    @Override
    public IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword) {
        return null;
    }

    @Override
    public BorrowerDetailVO getBorrowerDetailVOById(Long id) {
        return null;
    }

    @Override
    public void approval(BorrowerApprovalVO borrowerApprovalVO) {

    }
}
