package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.Vo.UserBindVO;
import com.atguigu.srb.core.pojo.entity.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface UserBindService extends IService<UserBind> {

    String commitBindUser(UserBindVO userBindVO, Long userId);

    void notify(Map<String, Object> paramMap);

    String getBindCodeByUserId(Long userId);
}
