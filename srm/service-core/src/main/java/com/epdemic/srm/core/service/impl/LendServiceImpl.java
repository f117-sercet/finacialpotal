package com.epdemic.srm.core.service.impl;

import com.epdemic.srm.core.pojo.entity.BorrowInfo;
import com.epdemic.srm.core.pojo.entity.Lend;
import com.epdemic.srm.core.mapper.LendMapper;
import com.epdemic.srm.core.pojo.vo.BorrowInfoApprovalVO;
import com.epdemic.srm.core.service.LendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
@Slf4j
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
