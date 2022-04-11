package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.pojo.Vo.InvestVO;
import com.atguigu.srb.core.pojo.entity.LendItem;
import com.atguigu.srb.core.mapper.LendItemMapper;
import com.atguigu.srb.core.service.LendItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class LendItemServiceImpl extends ServiceImpl<LendItemMapper, LendItem> implements LendItemService {

    @Override
    public String commitInvest(InvestVO investVO) {
        return null;
    }

    @Override
    public void notify(Map<String, Object> paramMap) {

    }

    @Override
    public List<LendItem> selectByLendId(Long lendId, Integer status) {
        return null;
    }

    @Override
    public List<LendItem> selectByLendId(Long lendId) {
        return null;
    }
}
