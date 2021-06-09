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
import java.util.List;
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

        if(Double.parseDouble(curVoteAmtTotal) > Double.parseDouble(userInvest.getProjectAmt())) {
            throw new HfbException(ResultCodeEnum.INVEST_AMMOUNT_MORE_ERROR);
        }
        UserInvest userInvest1 = userInvestMapper.selectByAgentProjectCode(userInvest.getAgentProjectCode());
        if(null != userInvest1 && Double.parseDouble(userInvest.getProjectAmt()) != Double.parseDouble(userInvest1.getProjectAmt())) {
            throw new HfbException(ResultCodeEnum.PROJECT_AMMOUNT_ERROR);
        }
        this.save(userInvest);
        userInvest.setResultCode("0001");
        userInvest.setResultMsg("成功");
        userAccountService.lockAccount(userInvest.getVoteBindCode(),userInvest.getVoteAmt());
        return userInvest;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String makeLoan(Map<String, Object> paramMap) {

        String agentProjectCode = (String) paramMap.get("agentProjectCode");

        // 项目投资的总金额
        String voteAmtTotal = userInvestMapper.selectSumVoteAmtByAgentProjectCode(agentProjectCode);

        //确保接口调用的幂等性。检验投资信息的状态，如果状态已经修改，则直接返回，不进行后续的转账操作
        Integer status = userInvestMapper.selectStatusByAgentProjectCode(agentProjectCode);
        if (status != null && status.intValue() == 1) {
            return voteAmtTotal;
        }

        // 借款人绑定协议账号
        String benefitBindCdoe = "";
        List<UserInvest> userInvestList = userInvestMapper.selectList(new QueryWrapper<UserInvest>().eq("agent_project_code", agentProjectCode));

        for (UserInvest userInvest : userInvestList) {
            // 投资人绑定协议号
            String voteBindCode = userInvest.getVoteBindCode();
            // 借款人绑定协议号
            benefitBindCdoe = userInvest.getBenefitBindCode();
            //投资金额
            String voteAmt = userInvest.getVoteAmt();
            //解锁投资人金额
            userAccountService.unLockAccount(voteBindCode, voteAmt);
        }

            //给借款人转账：
            //从paramMap中获取平台服务费，然后从转账金额中减去平台的服务费
            BigDecimal mchFee = new BigDecimal((String) paramMap.get("mchFee"));
            BigDecimal total = new BigDecimal(voteAmtTotal).subtract(mchFee);
            userAccountService.transferAccounts(benefitBindCdoe, total.toString());

            //更新状态
            UserInvest uptUserInvest = new UserInvest();
            uptUserInvest.setStatus(1);
            userInvestMapper.update(uptUserInvest, new QueryWrapper<UserInvest>().eq("agent_project_code", agentProjectCode));

            return total.toString();

        }


    @Override
    public String cancelLend(Map<String, Object> paramMap) {
        String agentProjectCode = (String) paramMap.get("agentProjectCode");

        // 项目投资的总金额
        String voteAmtTotal = userInvestMapper.
                selectSumVoteAmtByAgentProjectCode(agentProjectCode);
        Integer status = userInvestMapper.selectStatusByAgentProjectCode(agentProjectCode);

        if (null != status && status.intValue() == -1) {
            return voteAmtTotal;
        }
        // 借款人绑定协议账号
        String benefitBindCode = "";
        List<UserInvest> userInvestList = userInvestMapper
                .selectList(new QueryWrapper<UserInvest>().eq("agent_project_code", agentProjectCode));
        for(UserInvest userInvest : userInvestList) {
            //投资人绑定协议号
            String voteBindCode = userInvest.getVoteBindCode();
            //借款人绑定协议号
            benefitBindCode = userInvest.getBenefitBindCode();
            //投资金额
            String voteAmt = userInvest.getVoteAmt();

            //返回投资人金额
            userAccountService.dealAccount(voteBindCode, voteAmt, "-"+voteAmt);
        }

        return voteAmtTotal;
    }
}
