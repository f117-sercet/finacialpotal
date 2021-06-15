package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.BorrowInfo;
import com.epdemic.srm.core.pojo.entity.Lend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.srm.core.pojo.vo.BorrowInfoApprovalVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface LendService extends IService<Lend> {
    /**
     *
     * @param borrowInfoApprovalVO
     * @param borrowInfo
     */
    void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo);

    /**
     * 选择列表
     * @return
     */
    List<Lend> selectList();

    /**
     * 获取标的细节
     * @param id
     * @return
     */
    Map<String, Object> getLendDetail(Long id);

    /**
     * 获取当前数额
     * @param invest
     * @param yearRate
     * @param totalmonth
     * @param returnMethod
     * @return
     */
    BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, Integer totalmonth, Integer returnMethod);

    /**
     * 产生贷款
     * @param id
     */
    void makeLoan(Long id);

}
