package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.pojo.Vo.UserBindVO;
import com.atguigu.srb.core.pojo.entity.UserBind;
import com.atguigu.srb.core.mapper.UserBindMapper;
import com.atguigu.srb.core.service.UserBindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class UserBindServiceImpl extends ServiceImpl<UserBindMapper, UserBind> implements UserBindService {

    @Override
    public String commitBindUser(UserBindVO userBindVO, Long userId) {
        return null;
    }

    @Override
    public void notify(Map<String, Object> paramMap) {

    }

    @Override
    public String getBindCodeByUserId(Long userId) {
        return null;
    }
}
