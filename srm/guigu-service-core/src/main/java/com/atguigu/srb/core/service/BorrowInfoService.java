package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.Vo.BorrowInfoApprovalVO;
import com.atguigu.srb.core.pojo.entity.BorrowInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 借款信息表 服务类
 * </p>
 *
 * @author 段世超
 * @since 2021-11-25
 */
public interface BorrowInfoService extends IService<BorrowInfo> {

    BigDecimal getBorrowAmount(Long userId);

    void saveBorrowInfo(BorrowInfo borrowInfo,Long userId);

    /**
     * 通过id获取状态
     * @param userId
     * @return
     */
    Integer getStatusByUserId(Long userId);

    /**
     * 筛选
     * @return
     */
    List<BorrowInfo> selectList();

    Map<String, Object> getBorrowInfoDetail(Long id);

    void approval(BorrowInfoApprovalVO borrowInfoApprovalVO);

}
