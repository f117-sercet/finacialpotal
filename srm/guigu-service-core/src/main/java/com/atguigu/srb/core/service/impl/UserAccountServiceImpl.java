package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.pojo.entity.UserAccount;
import com.atguigu.srb.core.mapper.UserAccountMapper;
import com.atguigu.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Override
    public String commitCharge(BigDecimal chargeAmt, Long userId) {
        return null;
    }

    @Override
    public String notify(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public BigDecimal getAccount(Long userId) {
        return null;
    }

    @Override
    public String commitWithdraw(BigDecimal fetchAmt, Long userId) {
        return null;
    }

    @Override
    public void notifyWithdraw(Map<String, Object> paramMap) {

    }
}
