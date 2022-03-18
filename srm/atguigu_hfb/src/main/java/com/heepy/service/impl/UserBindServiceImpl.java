package com.heepy.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heepy.mapper.UserAccountMapper;
import com.heepy.mapper.UserBindMapper;
import com.heepy.model.UserAccount;
import com.heepy.model.UserBind;
import com.heepy.service.UserBindService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * Description： 用户绑定服务
 *
 * @author: 段世超
 * @aate: Created in 2022/3/16 9:37
 */
public class UserBindServiceImpl extends ServiceImpl<UserBindMapper, UserBind> implements UserBindService {

   @Resource
   private UserAccountMapper userAccountMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserBind bind(Map<String, Object> paramMap) {
        UserBind userBind = JSON.parseObject(JSON.toJSONString(paramMap),UserBind.class);
        String  bindCode = UUID.randomUUID().toString().replaceAll("-", "");

        userBind.setBindCode(bindCode);
        baseMapper.insert(userBind);

        UserAccount userAccount = new UserAccount();
        userAccount.setUserCode(bindCode);
        userAccount.setAmount("0");
        userAccount.setFreezeAmount("0");
        userAccountMapper.insert(userAccount);
        return userBind;
    }

    @Override
    public boolean isBind(String idCard) {
        return false;
    }

    @Override
    public UserBind getByBindCode(String bindCode) {
        return null;
    }

    @Override
    public void checkPassword(String bindCode, String password) {

    }
}
