package com.heepy.controller;

import com.heepy.model.NotifyVo;
import com.heepy.model.UserBind;
import com.heepy.service.UserBindService;
import com.heepy.task.ScheduledTask;
import com.heepy.util.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description： 用户账户绑定
 *
 * @author: 段世超
 * @aate: Created in 2022/3/21 11:13
 */
@CrossOrigin
@Controller
@RequestMapping("/userBind")
public class UserBindController {

    @Resource
    private UserBindService userBindService;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;


    @GetMapping("/BindAgreeUserV2")
    public String bind(Model model, HttpServletResponse response, HttpServletRequest request) {

        Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
        SignUtil.isSignEquals(paramMap);

        boolean isBind = userBindService.isBind((String) paramMap.get("idCard"));

        if (isBind) {
            model.addAttribute("returnUrl", paramMap.get("retunrUrl"));

            return "user/success";
        }
        model.addAttribute("paramMap",paramMap);
        return "user/bindUser";
        }

        @PostMapping("/save")
        public String save(Model model,HttpServletRequest request,HttpServletResponse response) {

            Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

            //账户绑定
            UserBind userBind = userBindService.bind(paramMap);

            //异步通知
            //异步通知
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("resultCode", "0001");
            resultMap.put("resultMsg", "成功");
            resultMap.put("bindCode", userBind.getBindCode());
            resultMap.put("agentUserId", userBind.getAgentUserId());
            resultMap.put("timestamp", new Date().getTime());
            resultMap.put("sign", SignUtil.getSign(resultMap));
            ScheduledTask.queue.offer(new NotifyVo(userBind.getNotifyUrl(), resultMap));

            //同步跳转
            //response.sendRedirect(userBind.getReturnUrl());
            model.addAttribute("returnUrl", paramMap.get("returnUrl"));
            return "user/success";

        }


        }



