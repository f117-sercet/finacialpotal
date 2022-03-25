package com.heepy.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heepy.mapper.UserAccountMapper;
import com.heepy.mapper.UserBindMapper;
import com.heepy.model.UserAccount;
import com.heepy.model.UserBind;
import com.heepy.service.UserBindService;
import com.heepy.util.HfbException;
import com.heepy.util.ResultCodeEnum;
import org.springframework.stereotype.Service;
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
@Service
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

        Integer count = baseMapper.selectCount(new QueryWrapper<UserBind>().eq("id_card", idCard));
        if(count.intValue() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public UserBind getByBindCode(String bindCode) {


        return baseMapper.selectOne(new QueryWrapper<UserBind>().eq("bind_code",bindCode));
    }

    @Override
    public void checkPassword(String bindCode, String password) {
        if (StringUtils.isEmpty(password)){

            throw  new HfbException(ResultCodeEnum.PAY_PASSWORD_ERROR);
        }
        UserBind userBind = this.getByBindCode(bindCode);
        if (!password.equals(userBind.getPayPasswd())){

            throw new HfbException(ResultCodeEnum.PAY_PASSWORD_ERROR);
        }

    }
}
