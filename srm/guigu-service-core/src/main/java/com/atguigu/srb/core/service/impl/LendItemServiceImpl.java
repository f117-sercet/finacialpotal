package com.atguigu.srb.core.service.impl;

import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.ResponseEnum;
import com.atguigu.srb.core.enums.LendStatusEnum;
import com.atguigu.srb.core.hfb.FormHelper;
import com.atguigu.srb.core.hfb.HfbConst;
import com.atguigu.srb.core.hfb.RequestHelper;
import com.atguigu.srb.core.mapper.LendMapper;
import com.atguigu.srb.core.mapper.UserAccountMapper;
import com.atguigu.srb.core.pojo.Vo.InvestVO;
import com.atguigu.srb.core.pojo.entity.Lend;
import com.atguigu.srb.core.pojo.entity.LendItem;
import com.atguigu.srb.core.mapper.LendItemMapper;
import com.atguigu.srb.core.service.*;
import com.atguigu.srb.core.utils.LendNoUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class LendItemServiceImpl extends ServiceImpl<LendItemMapper, LendItem> implements LendItemService {

    @Resource
    private LendMapper lendMapper;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserBindService userBindService;

    @Resource
    private LendService lendService;

    @Resource
    private TransFlowService transFlowService;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public String commitInvest(InvestVO investVO) {

        //健壮性的校验
        Long lendId = investVO.getLendId();
        Lend lend = lendMapper.selectById(lendId);

        //判断标的状态为募资中
        Assert.isTrue(
                lend.getStatus().intValue() == LendStatusEnum.INVEST_RUN.getStatus().intValue(),
                ResponseEnum.LEND_INVEST_ERROR);

        //超卖：已投金额 + 当前投资金额 <= 标的金额（正常）
        BigDecimal sum = lend.getInvestAmount().add(new BigDecimal(investVO.getInvestAmount()));
        Assert.isTrue(
                sum.doubleValue() <= lend.getAmount().doubleValue(),
                ResponseEnum.LEND_FULL_SCALE_ERROR);

        //用户余额：当前用户的余额 >= 当前投资金额
        Long investUserId = investVO.getInvestUserId();
        BigDecimal amount = userAccountService.getAccount(investUserId);
        Assert.isTrue(
                amount.doubleValue() >= Double.parseDouble(investVO.getInvestAmount()),
                ResponseEnum.NOT_SUFFICIENT_FUNDS_ERROR);

        //获取paramMap中需要的参数
        //生成标的下的投资记录
        LendItem lendItem = new LendItem();
        lendItem.setInvestUserId(investUserId);//投资人id
        lendItem.setInvestName(investVO.getInvestName());//投资人名字
        String lendItemNo = LendNoUtils.getLendItemNo();
        lendItem.setLendItemNo(lendItemNo); //投资条目编号（一个Lend对应一个或多个LendItem）
        lendItem.setLendId(investVO.getLendId());//对应的标的id
        lendItem.setInvestAmount(new BigDecimal(investVO.getInvestAmount())); //此笔投资金额
        lendItem.setLendYearRate(lend.getLendYearRate());//年化
        lendItem.setInvestTime(LocalDateTime.now()); //投资时间
        lendItem.setLendStartDate(lend.getLendStartDate()); //开始时间
        lendItem.setLendEndDate(lend.getLendEndDate()); //结束时间


        //预期收益
        BigDecimal expectAmount = lendService.getInterestCount(
             lendItem.getInvestAmount(),
             lendItem.getLendYearRate(),
             lend.getPeriod(),
             lend.getReturnMethod());
        lendItem.setExpectAmount(expectAmount);

        //实际收益
        lendItem.setRealAmount(new BigDecimal(0));

        //投资记录的状态
        //刚刚创建投资记录
        lendItem.setStatus(0);
        //存入数据库
        baseMapper.insert(lendItem);

        //获取投资人的bindCode
        String bindCode = userBindService.getBindCodeByUserId(investUserId);
        //获取借款人的bindCode
        String benefitBindCode = userBindService.getBindCodeByUserId(lend.getUserId());


        //封装提交至汇款宝
        Map<String,Object>paramMap = new HashMap<>();
        paramMap.put("agentId", HfbConst.AGENT_ID);
        paramMap.put("voteBindCode",bindCode);
        paramMap.put("benefitBindCode",benefitBindCode);
        paramMap.put("agentProjectCode",lend.getLendNo());
        paramMap.put("agentProjectName",lend.getTitle());

        //在资金托管平台上的投资订单的唯一编号，可以独立生成，不一定非要和lendItemNo保持一致，但是可以一致。
        paramMap.put("agentBillNo",lendItemNo);
        paramMap.put("voteAmt",investVO.getInvestAmount());
        paramMap.put("votePrizeAmt","0");
        paramMap.put("voteFeeAmt","0");
        paramMap.put("projectAmt",lend.getAmount());
        paramMap.put("note","");
        paramMap.put("notifyUrl",HfbConst.INVEST_NOTIFY_URL);
        paramMap.put("returnUrl",HfbConst.INVEST_RETURN_URL);
        paramMap.put("timestamp", RequestHelper.getTimestamp());
        String sign = RequestHelper.getSign(paramMap);
        paramMap.put("sign",sign);

        //构建充值自动提交表单
        String formStr = FormHelper.buildForm(HfbConst.INVEST_URL, paramMap);

        return formStr;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void notify(Map<String, Object> paramMap) {
   // 幂等性
    }

    @Override
    public List<LendItem> selectByLendId(Long lendId, Integer status) {
        return null;
    }

    @Override
    public List<LendItem> selectByLendId(Long lendId) {
        return null;
    }
}
