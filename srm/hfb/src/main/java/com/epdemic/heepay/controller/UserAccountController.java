package com.epdemic.heepay.controller;

import com.epdemic.heepay.model.NotifyVo;
import com.epdemic.heepay.service.UserAccountService;
import com.epdemic.heepay.service.UserBindService;
import com.epdemic.heepay.task.ScheduledTask;
import com.epdemic.heepay.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 用户账户
 * @Author:estic
 * @Date: 2021/6/10 9:12
 */
@CrossOrigin
@Controller
@RequestMapping("/userAccount")
@Slf4j
public class UserAccountController {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserBindService userBindService;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 充值
     * @param request
     * @return
     */
    @PostMapping("/AgreeBankCharge")
    public String  bind(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = SignUtil.
                switchMap(request.getParameterMap());
        //验签
        SignUtil.isSignEquals(paramMap);

        model.addAttribute("paramMap", paramMap);
        model.addAttribute("userBind", userBindService.getByBindCode((String)paramMap.get("bindCode")));
        return "userAccount/index";
    }

    @PostMapping("/recharge")
    public String recharge(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());


        //校验支付密码
        userBindService.checkPassword((String)paramMap.get("bindCode"), request.getParameter("payPasswd"));

        //汇付宝账户充值
        userAccountService.recharge(paramMap);

        /**组装回调请求参数**/
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCode","0001");
        resultMap.put("resultMsg","充值成功");
        resultMap.put("agentBillNo",paramMap.get("agentBillNo"));
        resultMap.put("bindCode",paramMap.get("bindCode"));
        resultMap.put("chargeAmt", new BigDecimal((String)paramMap.get("chargeAmt")));
        resultMap.put("mchFee",new BigDecimal("0"));
        resultMap.put("hyFee",new BigDecimal("0"));
        resultMap.put("timestamp",System.currentTimeMillis());
        resultMap.put("sign",SignUtil.getSign(resultMap));

        /**异步通知**/
        /**threadPoolExecutor.submit(new NotifyThread((String)paramMap.get("notifyUrl"), paramMap));**/
        ScheduledTask.queue.offer(new NotifyVo((String)paramMap.get("notifyUrl"), resultMap));

        /**同步跳转**/
        /**response.sendRedirect(userBind.getReturnUrl());**/
        model.addAttribute("returnUrl", paramMap.get("returnUrl"));
        return "userAccount/success";
    }

    /**
     * 提现
     * @param request
     * @return
     */
    @PostMapping("/CashBankManager")
    public String  CashBankManager(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
        SignUtil.isSignEquals(paramMap);

        model.addAttribute("paramMap", paramMap);
        model.addAttribute("userBind", userBindService.getByBindCode((String)paramMap.get("bindCode")));
        return "withdraw/index";
    }

    @PostMapping("/withdraw")
    public String withdraw(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

        userBindService.checkPassword((String)paramMap.get("bindCode"), request.getParameter("payPasswd"));

        userAccountService.withdraw(paramMap);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCode","0001");
        resultMap.put("resultMsg","充值成功");
        resultMap.put("agentBillNo",paramMap.get("agentBillNo"));
        resultMap.put("bindCode",paramMap.get("bindCode"));
        resultMap.put("fetchAmt", new BigDecimal((String)paramMap.get("fetchAmt")));
        resultMap.put("mchFee",new BigDecimal("0"));
        resultMap.put("timestamp", System.currentTimeMillis());
        resultMap.put("sign",SignUtil.getSign(resultMap));

        //异步通知
        ScheduledTask.queue.offer(new NotifyVo((String)paramMap.get("notifyUrl"), resultMap));

        //同步跳转
        model.addAttribute("returnUrl", paramMap.get("returnUrl"));
        return "withdraw/success";
    }
}

