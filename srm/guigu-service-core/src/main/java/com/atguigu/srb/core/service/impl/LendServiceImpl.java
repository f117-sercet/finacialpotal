package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.pojo.Vo.BorrowInfoApprovalVO;
import com.atguigu.srb.core.pojo.entity.BorrowInfo;
import com.atguigu.srb.core.pojo.entity.Lend;
import com.atguigu.srb.core.mapper.LendMapper;
import com.atguigu.srb.core.service.LendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class LendServiceImpl extends ServiceImpl<LendMapper, Lend> implements LendService {

    @Override
    public void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo) {

    }

    @Override
    public List<Lend> selectList() {
        return null;
    }

    @Override
    public Map<String, Object> getLendDetail(Long id) {
        return null;
    }

    @Override
    public BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, Integer totalmonth, Integer returnMethod) {
        return null;
    }

    @Override
    public void makeLoan(Long id) {

    }
}
