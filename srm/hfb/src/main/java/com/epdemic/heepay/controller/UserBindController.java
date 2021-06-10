package com.epdemic.heepay.controller;

import com.epdemic.heepay.model.NotifyVo;
import com.epdemic.heepay.model.UserBind;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 用户绑定controller
 * @Author:estic
 * @Date: 2021/6/10 9:19
 */
@CrossOrigin
@Controller
@RequestMapping("/userBind")
@Slf4j
public class UserBindController {

    @Resource
    private UserBindService userBindService;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 绑定用户
     * @param request
     * @return
     */
    @PostMapping("/BindAgreeUserV2")
    public String  bind(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());
        SignUtil.isSignEquals(paramMap);

        boolean isBind = userBindService.isBind((String)paramMap.get("idCard"));
        if(isBind) {
            model.addAttribute("returnUrl", paramMap.get("returnUrl"));
            return "user/success";
        }

        model.addAttribute("paramMap", paramMap);
        return "user/bindUser";
    }

    @PostMapping("/save")
    public String save(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

        //账户绑定
        UserBind userBind = userBindService.bind(paramMap);

        //异步通知
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCode","0001");
        resultMap.put("resultMsg","成功");
        resultMap.put("bindCode",userBind.getBindCode());
        resultMap.put("agentUserId",userBind.getAgentUserId());
        resultMap.put("timestamp",System.currentTimeMillis());
        resultMap.put("sign",SignUtil.getSign(resultMap));
        ScheduledTask.queue.offer(new NotifyVo(userBind.getNotifyUrl(), resultMap));

        //同步跳转
        model.addAttribute("returnUrl", paramMap.get("returnUrl"));
        return "user/success";
    }


}
