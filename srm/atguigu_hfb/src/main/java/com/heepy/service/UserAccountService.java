package com.heepy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heepy.model.UserAccount;

import java.util.Map;

/**
 * 账户服务类
 * @author 段世超
 * @date 2022-3-5
 */
public interface UserAccountService extends IService<UserAccount> {

    void recharge(Map<String, Object> paramMap);

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

    UserAccount getByUserCode(String userCode);

    boolean dealAccount(String userCode, String amount, String freezeAmount);
}
