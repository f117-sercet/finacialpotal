package com.heepy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heepy.model.UserBind;

import java.util.Map;

/**
 * Description： 用户绑定服务
 * <p>
 * @author: 段世超
 * <p>
 * @Date: Created in 2022/3/5 11:31
 */
public interface UserBindService extends IService<UserBind> {

    UserBind bind(Map<String, Object> paramMap);

    boolean isBind(String idCard);

    UserBind getByBindCode(String bindCode);

    void checkPassword(String bindCode, String password);
}
