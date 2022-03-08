package com.heepy.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heepy.mapper.UserAccountMapper;
import com.heepy.model.UserAccount;
import com.heepy.service.UserAccountService;
import com.heepy.util.BigDemicalUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description： 用户账户服务类
 *
 * @author: 段世超
 * @aate: Created in 2022/3/8 15:44
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper,UserAccount> implements UserAccountService {

   @Resource
   private UserAccountMapper userAccountMapper;

   @Transactional(rollbackFor = Exception.class)
    @Override
    public void recharge(Map<String, Object> paramMap) {

       String bindCode = (String) paramMap.get("bindCode");
       String chargeAmt = (String) paramMap.get("chargeAmt");

       UserAccount userAccount = userAccountMapper.selectOne(new QueryWrapper<UserAccount> ().eq("user_code", bindCode));

     if(null == userAccount){

         userAccount = new UserAccount();
         userAccount.setUserCode(bindCode);
         userAccount.setFreezeAmount("0");
         userAccountMapper.insert(userAccount);
     }else{

         String amount = BigDemicalUtil.add(userAccount.getAmount(), chargeAmt);
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
        String amount = BigDemicalUtil.subtract(userAccount.getAmount(), fetchAmt);
        userAccount.setAmount(amount);
        userAccountMapper.updateById(userAccount);


    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockAccount(String userCode, String lockAmt) {

       UserAccount userAccount = this.getByUserCode(userCode);
       String amount = userAccount.getAmount();
       if (Double.parseDouble(amount) < Double.parseDouble(lockAmt)){

           return  false;
       }

       amount = BigDemicalUtil.subtract(amount,lockAmt);
       String freezeAmount = BigDemicalUtil.add(userAccount.getFreezeAmount(),lockAmt);
       userAccount.setAmount(amount);
       userAccount.setFreezeAmount(freezeAmount);
       this.updateById(userAccount);

       return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean unLockAccount(String userCode, String unLockAmt) {
        return false;
    }

    @Override
    public boolean transferAccounts(String userCode, String unLockAmt) {
        return false;
    }

    @Override
    public String getAmount(String userCode) {
        return null;
    }

    @Override
    public UserAccount getByUserCode(String userCode) {
        return null;
    }

    @Override
    public boolean dealAccount(String userCode, String amount, String freezeAmount) {
        return false;
    }
}
