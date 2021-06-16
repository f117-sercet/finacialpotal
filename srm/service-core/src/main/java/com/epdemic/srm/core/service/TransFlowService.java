package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.bo.TransFlowBO;
import com.epdemic.srm.core.pojo.entity.TransFlow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface TransFlowService extends IService<TransFlow> {
    /**
     * 保存交易流水
     * @param transFlowBO
     */
    void saveTransFlow(TransFlowBO transFlowBO);

    /**
     * 判断是否保存交易流水
     * @param agentBillNo
     * @return
     */
    boolean isSaveTransFlow(String agentBillNo);

    /**
     * 通过用户id筛选
     * @param userId
     * @return
     */
    List<TransFlow> selectByUserId(Long userId);

}
