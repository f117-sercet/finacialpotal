package com.heepy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heepy.mapper.UserInvestMapper;
import com.heepy.model.UserInvest;
import com.heepy.model.UserInvestQueryVo;
import com.heepy.service.UserAccountService;
import com.heepy.service.UserInvestService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description： 用户投资服务类
 *
 * @author: 段世超
 * @aate: Created in 2022/3/13 18:47
 */
public class UserInvestServiceImpl extends ServiceImpl<UserInvestMapper, UserInvest> implements UserInvestService {

    @Resource
    private UserInvestMapper userInvestMapper;

    @Resource
    private UserAccountService userAccountService;


    @Override
    public IPage<UserInvest> selectPage(Page<UserInvest> pageParam, UserInvestQueryVo userInvestQueryVo) {
        return userInvestMapper.selectPage(pageParam,userInvestQueryVo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInvest invest(Map<String, Object> paramMap) {

        UserInvest userInvest = JSONObject.parseObject(JSONObject.toJSONString(paramMap),UserInvest.class);

        return null;
    }

    @Override
    public String makeLoan(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public String cancelLend(Map<String, Object> paramMap) {
        return null;
    }
}
