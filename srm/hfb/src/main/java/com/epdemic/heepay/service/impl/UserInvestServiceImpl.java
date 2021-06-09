package com.epdemic.heepay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epdemic.heepay.mapper.UserInvestMapper;
import com.epdemic.heepay.model.UserInvest;
import com.epdemic.heepay.model.UserInvestQueryVo;
import com.epdemic.heepay.service.UserAccountService;
import com.epdemic.heepay.service.UserInvestService;
import com.epdemic.heepay.util.BigDecimalUtil;
import com.epdemic.heepay.util.HfbException;
import com.epdemic.heepay.util.ResultCodeEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 用户投资账户实现类
 * @Author:estic
 * @Date: 2021/6/9 11:00
 */
@Service
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
    public synchronized UserInvest invest(Map<String, Object> paramMap) {

        UserInvest userInvest = JSONObject.parseObject(JSONObject.toJSONString(paramMap),UserInvest.class);

        Integer count = userInvestMapper.selectCount(new QueryWrapper<UserInvest>().eq("agent_bill_no", userInvest.getAgentBillNo()));
        if(count.intValue() > 0) {
            throw new HfbException(ResultCodeEnum.REPEAT_ERROR);
        }

        /**项目投资的总金额**/
        String voteAmTotal = userInvestMapper.selectSumVoteAmtByAgentProjectCode(userInvest.getAgentProjectCode());

        voteAmTotal = StringUtils.isEmpty(voteAmTotal)?"0":voteAmTotal;
        String curVoteAmtTotal = new BigDecimal(voteAmTotal).add(new BigDecimal(userInvest.getVoteAmt())).toString();


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
