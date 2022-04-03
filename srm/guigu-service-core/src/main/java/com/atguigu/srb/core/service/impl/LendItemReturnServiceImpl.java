package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.mapper.LendItemMapper;
import com.atguigu.srb.core.mapper.LendMapper;
import com.atguigu.srb.core.mapper.LendReturnMapper;
import com.atguigu.srb.core.pojo.entity.LendItemReturn;
import com.atguigu.srb.core.mapper.LendItemReturnMapper;
import com.atguigu.srb.core.service.LendItemReturnService;
import com.atguigu.srb.core.service.UserBindService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借回款记录表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class LendItemReturnServiceImpl extends ServiceImpl<LendItemReturnMapper, LendItemReturn> implements LendItemReturnService {

    @Resource
    private LendMapper lendMapper;

    @Resource
    private LendReturnMapper lendReturnMapper;

    @Resource
    private LendItemMapper lendItemMapper;

    @Resource
    private UserBindService userBindService;




    @Override
    public List<LendItemReturn> selectByLendId(Long lendId, Long userId) {

        QueryWrapper<LendItemReturn> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("lend_id",lendId)
                .eq("invest_user_id",userId)
                .orderByAsc("current_period");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> addReturnDetail(Long lendReturnId) {
        return null;
    }

    @Override
    public List<LendItemReturn> selectLendItemReturnList(Long lendReturnId) {
        return null;
    }
}
