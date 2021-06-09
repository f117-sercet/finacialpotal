package com.epdemic.heepay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.epdemic.heepay.mapper.UserItemReturnMapper;
import com.epdemic.heepay.mapper.UserReturnMapper;
import com.epdemic.heepay.model.UserItemReturn;
import com.epdemic.heepay.model.UserReturn;
import com.epdemic.heepay.service.LendReturnService;
import com.epdemic.heepay.service.UserAccountService;
import com.epdemic.heepay.util.HfbException;
import com.epdemic.heepay.util.ResultCodeEnum;
import com.mysql.cj.protocol.a.result.ResultsetRowsCursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接待反还
 * @author:estic
 * @date : 2021/6/8 10:37
 */
@Service
public class LendReturnServiceImpl implements LendReturnService {

    @Resource
    private UserItemReturnMapper userItemReturnMapper;

    @Resource
    private UserReturnMapper userReturnMapper;

    @Resource
    private UserAccountService userAccountService;

   @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> returnCommit(Map<String, Object> paramMap) {

       // 还款人绑定账号
       String fromBindCode = (String) paramMap.get("fromBindCode");

       // 还款总额
       BigDecimal totalAmt = new BigDecimal((String) paramMap.get("totalAmt"));
       BigDecimal voteFeeAmt = new BigDecimal((String)paramMap.get("voteFeeAmt"));

       BigDecimal transitAmtTotal = new BigDecimal("0");
       //还款明细
       String data = (String) paramMap.get("data");

       UserReturn userReturn = JSONObject.parseObject(JSONObject.toJSONString(paramMap),UserReturn.class);
       userReturnMapper.insert(userReturn);
       List<Map<String,Object>> lendItemReturnDetailList = JSONObject.parseObject(data,List.class);

       for (Map<String,Object>repayMap:lendItemReturnDetailList){
           //投资编号
           String voteBillNo = (String)repayMap.get("voteBillNo");
           //收款人（投资人）
           String toBindCode = (String)repayMap.get("toBindCode");
           //还款金额 (必须等于base_amt+benifit_amt), 最多支持小数点后2位
           BigDecimal transitAmt = (BigDecimal)repayMap.get("transitAmt");
           //还款本金。最多小数点后2位
           BigDecimal baseAmt = (BigDecimal)repayMap.get("baseAmt");
           //还款利息
           BigDecimal benifitAmt = (BigDecimal)repayMap.get("benifitAmt");

           UserItemReturn userItemReturn = JSONObject.parseObject(JSONObject.toJSONString(repayMap),UserItemReturn.class);
           userItemReturn.setUserReturnId(userReturn.getId());
           userItemReturnMapper.insert(userItemReturn);

           transitAmtTotal = transitAmtTotal.add(transitAmt);
           userAccountService.transferAccounts(toBindCode, transitAmt.toString());

       }
       double t1 = voteFeeAmt.add(transitAmtTotal).doubleValue();
       double t2 = totalAmt.doubleValue();
       if (t1 != t2) {

           throw new HfbException(ResultCodeEnum.RETURN_AMMOUNT_MORE_ERROR);

       }

       userAccountService.transferAccounts(fromBindCode,"-"+totalAmt);

       Map<String,Object> resultParam = new HashMap<>();

       resultParam.put("resultCode", "0001");
       resultParam.put("resultMsg", "还款成功");
       resultParam.put("agentBatchNo", paramMap.get("agentBatchNo"));
       resultParam.put("timestamp", System.currentTimeMillis());
       resultParam.put("resultMsg", "还款成功");
       resultParam.put("totalAmt", totalAmt);
       resultParam.put("voteFeeAmt", voteFeeAmt);
       resultParam.put("successNum", lendItemReturnDetailList.size());
       return resultParam;

    }
}