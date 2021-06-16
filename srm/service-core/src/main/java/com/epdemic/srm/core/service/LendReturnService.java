package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.LendReturn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 还款记录表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface LendReturnService extends IService<LendReturn> {
    /**
     * 通过借款id查询
     * @param lendId
     * @return
     */
    List<LendReturn> selectByLendId(Long lendId);

    /**
     * 提交回款请求
     * @param lendReturnId
     * @param userId
     * @return
     */
    String commitReturn(Long lendReturnId, Long userId);

    /**
     * 通知
     * @param paramMap
     */
    void notify(Map<String, Object> paramMap);
}
