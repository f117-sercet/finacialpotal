package com.heepy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heepy.mapper.UserItemReturnMapper;
import com.heepy.mapper.UserReturnMapper;
import com.heepy.model.UserItemReturn;
import com.heepy.model.UserReturn;
import com.heepy.service.LendReturnService;
import com.heepy.service.UserAccountService;
import com.heepy.util.HfbException;
import com.heepy.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description： 贷款返还服务
 *
 * @author: 段世超
 * @aate: Created in 2022/3/7 11:16
 */
@Service
public class LendReturnServiceImpl implements LendReturnService {


    @Resource
    private UserItemReturnMapper userItemReturnMapper;

    @Resource
    private UserReturnMapper userReturnMapper;

    @Resource
    private UserAccountService userAccountService;

    @Override
    public Map<String, Object> returnCommit(Map<String, Object> paramMap) {

        //还款人绑定协议号

        String fromBindCode = (String) paramMap.get("fromBindCode");

        //还款额度
        BigDecimal totalAmt = new BigDecimal((String) paramMap.get("totalAmt"));
        BigDecimal voteFeeAmt = new BigDecimal((String) paramMap.get("voteFeeAmt"));

        BigDecimal transitAmtTotal = new BigDecimal("0");

        //还款明细
        String data = (String) paramMap.get("data");

        UserReturn userReturn = JSONObject.parseObject(JSONObject.toJSONString(paramMap), UserReturn.class);

        userReturnMapper.insert(userReturn);

        List<Map<String, Object>> LendItemReturnDetailList = JSONObject.parseObject(data, List.class);
        for (Map<String, Object> repayMap : LendItemReturnDetailList) {

            //投资编号
            String voteBillNo = (String) repayMap.get("voteBillNo");
            //收款人(投资人)
            String toBindCode = (String) repayMap.get("toBIndCode");
            //还款金额 (必须等于base_amt+benifit_amt), 最多支持小数点后2位
            BigDecimal transitAmt = (BigDecimal) repayMap.get("transitAmt");
            //还款利息
            BigDecimal benifitAmt = (BigDecimal) repayMap.get("benifitAmt");

            //商户手续费。最多小数点后2位
            //BigDecimal feeAmt = (BigDecimal)repayMap.get("feeAmt");

            UserItemReturn userItemReturn = JSONObject.parseObject(JSONObject.toJSONString(repayMap), UserItemReturn.class);
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
            userAccountService.transferAccounts(fromBindCode, "" + totalAmt);

            HashMap<String, Object> resultParam = new HashMap<>();
            resultParam.put("resultCode", "0001");
            resultParam.put("resultMsg", "还款成功");
            resultParam.put("agentBatchNo", paramMap.get("agentBatchNo"));
            resultParam.put("timestamp", System.currentTimeMillis());
            resultParam.put("resultMsg", "还款成功");
            resultParam.put("totalAmt", totalAmt);
            resultParam.put("voteFeeAmt", voteFeeAmt);
            resultParam.put("successNum", LendItemReturnDetailList.size());

            return resultParam;


        }

}
