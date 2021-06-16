package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 用户账户 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface UserAccountService extends IService<UserAccount> {
    /**
     * 提交请求
     * @param chargeAmt
     * @param userId
     * @return
     */
    String commitCharge(BigDecimal chargeAmt, Long userId);

    /**
     * 注意事项
     * @param paramMap
     * @return
     */
    String notify(Map<String, Object> paramMap);

    /**
     * 获取账户
     * @param userId
     * @return
     */
    BigDecimal getAccount(Long userId);

    /**
     * 提交撤回申请
     * @param fetchAmt
     * @param userId
     * @return
     */
    String commitWithdraw(BigDecimal fetchAmt, Long userId);

    /**
     * 确认撤回
     * @param paramMap
     */
    void notifyWithdraw(Map<String, Object> paramMap);

}
