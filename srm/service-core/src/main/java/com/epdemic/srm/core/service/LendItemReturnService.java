package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.LendItemReturn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借回款记录表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface LendItemReturnService extends IService<LendItemReturn> {
    /**
     * 根据借款id查询
     * @param lendId
     * @param userId
     * @return
     */
    List<LendItemReturn> selectByLendId(Long lendId, Long userId);

    /**
     * 添加回款明细
     * @param lendReturnId
     * @return
     */
    List<Map<String, Object>> addReturnDetail(Long lendReturnId);

    /**
     * 根据还款记录的id查询对应的回款记录
     * @param lendReturnId
     * @return
     */
    List<LendItemReturn> selectLendItemReturnList(Long lendReturnId);

}
