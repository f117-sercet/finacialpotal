package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.BorrowInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.srm.core.pojo.vo.BorrowInfoApprovalVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 借款信息表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface BorrowInfoService extends IService<BorrowInfo> {

    /**
     * 获取借款数额
     * @param userId
     * @return
     */
    BigDecimal getBorrowAmount(Long userId);

    /**
     * 保存借款信息
     * @param borrowInfo
     * @param userId
     */
    void saveBorrowInfo(BorrowInfo borrowInfo, Long userId);

    /**
     * 保存借款信息
     * @param userId
     * @return
     */

    Integer getStatusByUserId(Long userId);

    /**
     * 选择
     * @return
     */
    List<BorrowInfo> selectList();

    /**
     * 获取借款信息明细
     * @param id
     * @return
     */

    Map<String, Object> getBorrowInfoDetail(Long id);

    /**
     * 准许借款
     * @param borrowInfoApprovalVO
     */

    void approval(BorrowInfoApprovalVO borrowInfoApprovalVO);

}
