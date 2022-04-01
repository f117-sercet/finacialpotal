package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.bo.TransFlowBO;
import com.atguigu.srb.core.pojo.entity.TransFlow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface TransFlowService extends IService<TransFlow> {

    /**
     * 保存流水
     * @param transFlowBO
     */
    void saveTransFlow(TransFlowBO transFlowBO);

    /**
     * 确认信息
     * @param agentBillNo
     * @return
     */
    boolean isSaveTransFlow(String agentBillNo);

    /**
     * 通过id查询信息
     * @param userId
     * @return
     */
    List<TransFlow> selectByUserId(Long userId);
}
