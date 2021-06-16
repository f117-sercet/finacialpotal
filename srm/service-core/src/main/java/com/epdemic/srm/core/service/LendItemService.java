package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.LendItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.srm.core.pojo.entity.LendItemReturn;
import com.epdemic.srm.core.pojo.vo.InvestVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */

public interface LendItemService extends IService<LendItem> {
    /**
     * 呈递投资
     * @param investVO
     * @return
     */
    String commitInvest(InvestVO investVO);

    /**
     * notify
     * @param paramMap
     */
    void notify(Map<String, Object> paramMap);

    /**
     * 借款id选择
     * @param lendId
     * @param status
     * @return
     */
    List<LendItem> selectByLendId(Long lendId, Integer status);

    /**
     * 通过借款id选择
     * @param lendId
     * @return
     */

    List<LendItem> selectByLendId(Long lendId);
}
