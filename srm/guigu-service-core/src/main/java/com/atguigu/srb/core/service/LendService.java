package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.Vo.BorrowInfoApprovalVO;
import com.atguigu.srb.core.pojo.entity.BorrowInfo;
import com.atguigu.srb.core.pojo.entity.Lend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface LendService extends IService<Lend> {

    /**
     * 创建准备表
     * @param borrowInfoApprovalVO
     * @param borrowInfo
     */
    void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo);

    /**
     * 获取标的表信息
     * @return
     */
    List<Lend> selectList();
    /**
     * 获取标的表信息
     * @return
     */
    Map<String, Object> getLendDetail(Long id);

    /**
     * 获取投资总额
     * @param invest
     * @param yearRate
     * @param totalmonth
     * @param returnMethod
     * @return
     */
    BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, Integer totalmonth, Integer returnMethod);

    /**
     * 借贷
     * @param id
     */
    void makeLoan(Long id);
}
