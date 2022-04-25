package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.enums.LendStatusEnum;
import com.atguigu.srb.core.pojo.Vo.BorrowInfoApprovalVO;
import com.atguigu.srb.core.pojo.entity.BorrowInfo;
import com.atguigu.srb.core.pojo.entity.Lend;
import com.atguigu.srb.core.mapper.LendMapper;
import com.atguigu.srb.core.service.LendService;
import com.atguigu.srb.core.utils.LendNoUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        Lend lend = new Lend();
        lend.setUserId(borrowInfo.getUserId());
        lend.setBorrowInfoId(borrowInfo.getId());
        lend.setLendNo(LendNoUtils.getLendNo());
        lend.setTitle(borrowInfoApprovalVO.getTitle());
        lend.setAmount(borrowInfo.getAmount()); //标的金额
        lend.setPeriod(borrowInfo.getPeriod());
        lend.setLendYearRate(borrowInfoApprovalVO.getLendYearRate().divide(new BigDecimal(100)));
        lend.setServiceRate(borrowInfoApprovalVO.getServiceRate().divide(new BigDecimal(100)));
        lend.setReturnMethod(borrowInfo.getReturnMethod());
        lend.setLowestAmount(new BigDecimal(100)); //最低投资金额
        lend.setInvestAmount(new BigDecimal(0)); //已投金额
        lend.setInvestNum(0); //已投人数
        lend.setPublishDate(LocalDateTime.now());

        //起息日期
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lendStartDate = LocalDate.parse(borrowInfoApprovalVO.getLendStartDate(), dateTimeFormatter);
        lend.setLendStartDate(lendStartDate);

        //结束日期
        //结束日期
        LocalDate lendEndDate = lendStartDate.plusMonths(borrowInfo.getPeriod());
        lend.setLendEndDate(lendEndDate);

        //描述标的
        lend.setLendInfo(borrowInfoApprovalVO.getLendInfo());

        //平台预期收益 = 标的金额*(年化/12*期数)
        BigDecimal monthRate = lend.getServiceRate().divide(new BigDecimal(12), 8, BigDecimal.ROUND_DOWN);

        BigDecimal expectAmount = lend.getAmount().multiply(monthRate.multiply(new BigDecimal(lend.getPeriod())));

        //实际收益
        lend.setRealAmount(new BigDecimal(0));
        //标的状态
        lend.setStatus(LendStatusEnum.INVEST_RUN.getStatus());
        //审核时间
        lend.setCheckTime(LocalDateTime.now());
        //审核人
        lend.setCheckAdminId(1L);

        //存入数据库
        baseMapper.insert(lend);


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
