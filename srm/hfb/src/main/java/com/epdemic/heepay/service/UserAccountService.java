package com.epdemic.heepay.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.heepay.model.UserAccount;

import java.util.Map;

/**
 * 用户账户
 * @author:estic
 * @date
 */
public interface UserAccountService extends IService<UserAccount> {
    /**
     * 重新确认
     * @param paramMap
     */
    void recharge(Map<String, Object> paramMap);

    /**
     * 账户平衡
     * @param paramMap
     */
    void withdraw(Map<String, Object> paramMap);

    /**
     * 锁定账户
     * @param userCode
     * @param lockAmt
     * @return
     */
    boolean lockAccount(String userCode, String lockAmt);

    /**
     * 解锁账户
     * @param userCode
     * @param unLockAmt
     * @return
     */
    boolean unLockAccount(String userCode, String unLockAmt);

    /**
     * 转账
     * @param userCode
     * @param unLockAmt
     * @return
     */
    boolean transferAccounts(String userCode, String unLockAmt);

    /**
     * 查询账户余额
     * @param userCode
     * @return
     */
    String getAmount(String userCode);

    /**
     * 获取用户状态码
     * @param userCode
     * @return
     */
    UserAccount getByUserCode(String userCode);

    /**
     * 完成账户
     * @param userCode
     * @param amount
     * @param freezeAmount
     * @return
     */
    boolean dealAccount(String userCode, String amount, String freezeAmount);
}
