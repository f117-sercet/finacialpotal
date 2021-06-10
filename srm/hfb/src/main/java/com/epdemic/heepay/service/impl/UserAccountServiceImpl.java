package com.epdemic.heepay.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epdemic.heepay.mapper.UserAccountMapper;
import com.epdemic.heepay.model.UserAccount;
import com.epdemic.heepay.service.UserAccountService;
import com.epdemic.heepay.util.BigDecimalUtil;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * 用户账户Service
 * @Author:estic
 * @Date: 2021/6/8 15:02
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recharge(Map<String, Object> paramMap) {

        String bindCode = (String) paramMap.get("bindCdoe");
        String chargeAmt = (String)paramMap.get("chargeAmt");

        UserAccount userAccount = userAccountMapper.selectOne(new QueryWrapper<UserAccount>().eq("user_code", bindCode));

        if (userAccount == null){
            userAccount = new UserAccount();
            userAccount.setUserCode(bindCode);
            userAccount.setAmount(chargeAmt);
            userAccount.setFreezeAmount("0");
            userAccountMapper.insert(userAccount);
        }else{
            String amount = BigDecimalUtil.add(userAccount.getAmount(),chargeAmt);
            userAccount.setAmount(amount);
            userAccountMapper.updateById(userAccount);
        }


    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void withdraw(Map<String, Object> paramMap) {
        String bindCode = (String) paramMap.get("bindCode");
        String fetchAmt = (String)paramMap.get("fetchAmt");

        UserAccount userAccount = userAccountMapper.selectOne(new QueryWrapper<UserAccount>().eq("user_code", bindCode));
        String amount = BigDecimalUtil.subtract(userAccount.getAmount(), fetchAmt);
        userAccount.setAmount(amount);
        userAccountMapper.updateById(userAccount);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean lockAccount(String userCode, String lockAmt) {

     UserAccount userAccount = this.getByUserCode(userCode);
     String amount = userAccount.getAmount();
     if (Double.parseDouble(amount) < Double.parseDouble(lockAmt)){
         return false;
     }
      amount = BigDecimalUtil.subtract(amount,lockAmt);
     String freezAmount = BigDecimalUtil.add(userAccount.getFreezeAmount(),lockAmt);
    userAccount.setAmount(amount);
    userAccount.setFreezeAmount(freezAmount);
    this.updateById(userAccount);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unLockAccount(String userCode, String unLockAmt) {
        UserAccount userAccount = this.getByUserCode(userCode);
        String freezeAmount = userAccount.getFreezeAmount();
        if(Double.parseDouble(freezeAmount) < Double.parseDouble(unLockAmt)) {
            return false;
        }

        freezeAmount = BigDecimalUtil.subtract(userAccount.getFreezeAmount(), unLockAmt);
        userAccount.setFreezeAmount(freezeAmount);
        this.updateById(userAccount);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean transferAccounts(String userCode, String unLockAmt) {

        UserAccount userAccount = this.getByUserCode(userCode);
        String amount = userAccount.getAmount();

        amount = BigDecimalUtil.add(amount, unLockAmt);
        userAccount.setAmount(amount);
        this.updateById(userAccount);
        return false;
    }

    @Override
    public String getAmount(String userCode) {
        UserAccount userAccount = this.getByUserCode(userCode);
        String amount = userAccount.getAmount();
        return amount;
    }

    @Override
    public UserAccount getByUserCode(String userCode) {
        return userAccountMapper.selectOne(new QueryWrapper<UserAccount>().eq("user_bind",userCode));
    }

    @Override
    public boolean dealAccount(String userCode, String amount, String freezeAmount) {
        UserAccount userAccount = this.getByUserCode(userCode);
        String amount1 = BigDecimalUtil.add(userAccount.getAmount(), amount);
        String freezeAmount1 = BigDecimalUtil.add(userAccount.getFreezeAmount(), freezeAmount);
        userAccount.setAmount(amount1);
        userAccount.setFreezeAmount(freezeAmount1);
        this.updateById(userAccount);
        return false;
    }


}
