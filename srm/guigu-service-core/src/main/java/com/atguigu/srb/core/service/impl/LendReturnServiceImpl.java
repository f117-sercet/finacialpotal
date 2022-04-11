package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.pojo.entity.LendReturn;
import com.atguigu.srb.core.mapper.LendReturnMapper;
import com.atguigu.srb.core.service.LendReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class LendReturnServiceImpl extends ServiceImpl<LendReturnMapper, LendReturn> implements LendReturnService {

    @Override
    public List<LendReturn> selectByLendId(Long lendId) {
        return null;
    }

    @Override
    public String commitReturn(Long lendReturnId, Long userId) {
        return null;
    }

    @Override
    public void notify(Map<String, Object> paramMap) {

    }
}
