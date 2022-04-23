package com.atguigu.srb.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.ResponseEnum;
import com.atguigu.srb.core.hfb.HfbConst;
import com.atguigu.srb.core.mapper.*;
import com.atguigu.srb.core.pojo.entity.Lend;
import com.atguigu.srb.core.pojo.entity.LendReturn;
import com.atguigu.srb.core.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 还款记录表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
@Slf4j
public class LendReturnServiceImpl extends ServiceImpl<LendReturnMapper, LendReturn> implements LendReturnService {

    @Resource
    private LendMapper lendMapper;

    @Resource
    private UserBindService userBindService;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private LendItemReturnService lendItemReturnService;

    @Resource
    private TransFlowService transFlowService;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private LendItemReturnMapper lendItemReturnMapper;

    @Resource
    private LendItemMapper lendItemMapper;


    @Override
    public List<LendReturn> selectByLendId(Long lendId) {

        QueryWrapper<LendReturn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lend_id",lendId);
        List<LendReturn> lendReturnList = baseMapper.selectList(queryWrapper);
        return lendReturnList;
    }

    @Override
    public String commitReturn(Long lendReturnId, Long userId) {


        //还款记录
        LendReturn lendReturn = baseMapper.selectById(lendReturnId);

        //获取用户余额
        BigDecimal amount = userAccountService.getAccount(userId);

        Assert.isTrue(amount.doubleValue() >= lendReturn.getTotal().doubleValue(),
                ResponseEnum.NOT_SUFFICIENT_FUNDS_ERROR);

        //标的记录
        Lend lend = lendMapper.selectById(lendReturn.getLendId());

        //获取还款人的绑定协议号
        String bindCode = userBindService.getBindCodeByUserId(userId);

        //组装参数
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("agentId", HfbConst.AGENT_ID);

        //商户商品名称
        paramMap.put("agentGoodsName",lend.getTitle());

        //批次号
        paramMap.put("agentBatchNo",lend.getReturnMethod());

        //还款人绑定协议号
        paramMap.put("fromBindCode",bindCode);

        //还款总额
        paramMap.put("totalAmt",lendReturn.getTotal());
        paramMap.put("note","");

        //还款明细
        List<Map<String, Object>> lendItemReturnDetailList = lendItemReturnService.addReturnDetail(lendReturnId);
        paramMap.put("data", JSONObject.toJSONString(lendItemReturnDetailList));

        return null;
    }

    @Override
    public void notify(Map<String, Object> paramMap) {

    }
}
