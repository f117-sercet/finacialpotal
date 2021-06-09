package com.epdemic.heepay.controller;

import com.epdemic.heepay.model.NotifyVo;
import com.epdemic.heepay.service.LendReturnService;
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
import java.util.Map;

/**
 * 借贷回款controller
 * @author:estic
 * @Date: 2021/6/9 16:31
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/lendReturn")
@Slf4j
public class LendReturnController {
    @Resource
    private LendReturnService borrowReturnService;

    @Resource
    private UserBindService userBindService;

    @Resource
    private UserAccountService userAccountService;

    /**
     * 还款
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/AgreeUserRepayment")
    public String  AgreeUserVoteProject(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
     Map<String,Object> paramMap = SignUtil.switchMap(request.getParameterMap());
     SignUtil.isSignEquals(paramMap);
        model.addAttribute("paramMap", paramMap);
        model.addAttribute("userBind", userBindService.getByBindCode((String)paramMap.get("fromBindCode")));
        model.addAttribute("userAccount", userAccountService.getByUserCode((String)paramMap.get("fromBindCode")));
        return "lendReturn/index";

    }
    /**
     * 还款
	 * @param request
	 * @return
     */
    @PostMapping("/returnCommit")
    public String returnCommit(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = SignUtil.switchMap(request.getParameterMap());

        userBindService.checkPassword((String)paramMap.get("fromBindCode"), request.getParameter("payPasswd"));

        Map<String, Object> resultParam = borrowReturnService.returnCommit(paramMap);

        //异步通知
        /**threadPoolExecutor.submit(new NotifyThread((String)paramMap.get("notifyUrl"), resultParam));**/
        ScheduledTask.queue.offer(new NotifyVo((String)paramMap.get("notifyUrl"), resultParam));

        /**同步跳转**/
        /**response.sendRedirect(userBind.getReturnUrl());**/
        model.addAttribute("returnUrl", paramMap.get("returnUrl"));
        return "lendReturn/success";
    }
}
