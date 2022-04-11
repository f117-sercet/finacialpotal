package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.pojo.bo.TransFlowBO;
import com.atguigu.srb.core.pojo.entity.TransFlow;
import com.atguigu.srb.core.mapper.TransFlowMapper;
import com.atguigu.srb.core.service.TransFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 交易流水表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class TransFlowServiceImpl extends ServiceImpl<TransFlowMapper, TransFlow> implements TransFlowService {

    @Override
    public void saveTransFlow(TransFlowBO transFlowBO) {

    }

    @Override
    public boolean isSaveTransFlow(String agentBillNo) {
        return false;
    }

    @Override
    public List<TransFlow> selectByUserId(Long userId) {
        return null;
    }
}
