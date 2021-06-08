package com.epdemic.heepay.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epdemic.heepay.mapper.UserAccountMapper;
import com.epdemic.heepay.model.UserAccount;
import com.epdemic.heepay.service.UserAccountService;
import com.epdemic.heepay.util.BigDecimalUtil;
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

        return false;
    }

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

    @Override
    public boolean saveBatch(Collection<UserAccount> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UserAccount> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UserAccount> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(UserAccount entity) {
        return false;
    }

    @Override
    public UserAccount getOne(Wrapper<UserAccount> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<UserAccount> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<UserAccount> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }
}
