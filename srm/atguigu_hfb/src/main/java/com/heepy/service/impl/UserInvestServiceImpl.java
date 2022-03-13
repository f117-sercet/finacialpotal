package com.heepy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heepy.mapper.UserInvestMapper;
import com.heepy.model.UserInvest;
import com.heepy.model.UserInvestQueryVo;
import com.heepy.service.UserInvestService;

import java.util.Map;

/**
 * Description： TODO
 *
 * @author: 段世超
 * @aate: Created in 2022/3/13 18:47
 */
public class UserInvestServiceImpl extends ServiceImpl<UserInvestMapper, UserInvest> implements UserInvestService {
    @Override
    public IPage<UserInvest> selectPage(Page<UserInvest> pageParam, UserInvestQueryVo userInvestQueryVo) {
        return null;
    }

    @Override
    public UserInvest invest(Map<String, Object> paramMap) {
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
