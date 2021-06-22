package com.epdemic.srm.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.epdemic.srm.common.exception.Assert;
import com.epdemic.srm.common.result.ResponseEnum;
import com.epdemic.srm.core.enums.LendStatusEnum;
import com.epdemic.srm.core.enums.TransTypeEnum;
import com.epdemic.srm.core.hfb.FormHelper;
import com.epdemic.srm.core.hfb.HfbConst;
import com.epdemic.srm.core.hfb.RequestHelper;
import com.epdemic.srm.core.mapper.LendMapper;
import com.epdemic.srm.core.mapper.UserAccountMapper;
import com.epdemic.srm.core.pojo.bo.TransFlowBO;
import com.epdemic.srm.core.pojo.entity.Lend;
import com.epdemic.srm.core.pojo.entity.LendItem;
import com.epdemic.srm.core.mapper.LendItemMapper;
import com.epdemic.srm.core.pojo.vo.InvestVO;
import com.epdemic.srm.core.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epdemic.srm.core.util.LendNoUtils;
import lombok.extern.slf4j.Slf4j;
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
 * @author combine
 * @since 2021-05-31
 */
@Service
@Slf4j
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
        ////投资人id
        lendItem.setInvestUserId(investUserId);
        ////投资人名字
        lendItem.setInvestName(investVO.getInvestName());
        String lendItemNo = LendNoUtils.getLendItemNo();
        //投资条目编号（一个Lend对应一个或多个LendItem
        lendItem.setLendItemNo(lendItemNo);
        //对应的标的id
        lendItem.setLendId(investVO.getLendId());
        //此笔投资金额
        lendItem.setInvestAmount(new BigDecimal(investVO.getInvestAmount()));
        //年化
        lendItem.setLendYearRate(lend.getLendYearRate());
        //投资时间
        lendItem.setInvestTime(LocalDateTime.now());
        //开始时间
        lendItem.setLendStartDate(lend.getLendStartDate());
        //结束时间
        lendItem.setLendEndDate(lend.getLendEndDate());

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
        lendItem.setStatus(0);
        baseMapper.insert(lendItem);

        //获取投资人的bindCode
        String bindCode = userBindService.getBindCodeByUserId(investUserId);
        //获取借款人的bindCode
        String benefitBindCode = userBindService.getBindCodeByUserId(lend.getUserId());

        //封装提交至汇付宝的参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("agentId", HfbConst.AGENT_ID);
        paramMap.put("voteBindCode", bindCode);
        paramMap.put("benefitBindCode",benefitBindCode);
        //项目标号
        paramMap.put("agentProjectCode", lend.getLendNo());
        paramMap.put("agentProjectName", lend.getTitle());

        //在资金托管平台上的投资订单的唯一编号，可以独立生成，不一定非要和lendItemNo保持一致，但是可以一致。
        paramMap.put("agentBillNo", lendItemNo);
        paramMap.put("voteAmt", investVO.getInvestAmount());
        paramMap.put("votePrizeAmt", "0");
        paramMap.put("voteFeeAmt", "0");
        paramMap.put("projectAmt", lend.getAmount());
        paramMap.put("note", "");
        paramMap.put("notifyUrl", HfbConst.INVEST_NOTIFY_URL);
        paramMap.put("returnUrl", HfbConst.INVEST_RETURN_URL);
        paramMap.put("timestamp", RequestHelper.getTimestamp());
        String sign = RequestHelper.getSign(paramMap);
        paramMap.put("sign", sign);

        //构建充值自动提交表单
        String formStr = FormHelper.buildForm(HfbConst.INVEST_URL, paramMap);
        return formStr;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void notify(Map<String, Object> paramMap) {

        //幂等性返回
        String agentBillNo = (String)paramMap.get("agentBillNo");
        boolean result = transFlowService.isSaveTransFlow(agentBillNo);
        if(result){
            log.warn("幂等性返回");
            return;
        }

        //修改账户金额：从余额中减去投资金额，在冻结金额中增加投资进入
        String voteBindCode = (String)paramMap.get("voteBindCode");
        String voteAmt = (String)paramMap.get("voteAmt");

        userAccountMapper.updateAccount(
                voteBindCode,
                new BigDecimal("-" + voteAmt),
                new BigDecimal(voteAmt));

        //修改投资记录的状态
        LendItem lendItem = this.getByLenItemNo(agentBillNo);
        lendItem.setStatus(1);
        baseMapper.updateById(lendItem);

        //修改标的记录：投资人数、已投金额
        Long lendId = lendItem.getLendId();
        Lend lend = lendMapper.selectById(lendId);
        lend.setInvestNum(lend.getInvestNum() + 1);
        lend.setInvestAmount(lend.getInvestAmount().add(lendItem.getInvestAmount()));
        lendMapper.updateById(lend);

        //记录账户流水
        TransFlowBO transFlowBO = new TransFlowBO(
                agentBillNo,
                voteBindCode,
                new BigDecimal(voteAmt),
                TransTypeEnum.INVEST_LOCK,
                "项目编号：" + lend.getLendNo() + "，项目名称" + lend.getTitle());
        transFlowService.saveTransFlow(transFlowBO);

    }

    @Override
    public List<LendItem> selectByLendId(Long lendId, Integer status) {
        QueryWrapper<LendItem> lendItemQueryWrapper = new QueryWrapper<>();
        lendItemQueryWrapper
                .eq("lend_id", lendId)
                .eq("status", status);
        return baseMapper.selectList(lendItemQueryWrapper);
    }

    @Override
    public List<LendItem> selectByLendId(Long lendId) {
        QueryWrapper<LendItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("lend_id", lendId);
        List<LendItem> lendItemList = baseMapper.selectList(queryWrapper);
        return lendItemList;
    }

    /**
     * 根据流水号获取投资记录
     * @param lendItemNo
     * @return
     */
    private LendItem getByLenItemNo(String lendItemNo){
        QueryWrapper<LendItem> lendItemQueryWrapper = new QueryWrapper<>();
        lendItemQueryWrapper.eq("lend_item_no", lendItemNo);
        return baseMapper.selectOne(lendItemQueryWrapper);
    }
}
