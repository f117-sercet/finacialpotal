package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.Vo.InvestVO;
import com.atguigu.srb.core.pojo.entity.LendItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface LendItemService extends IService<LendItem> {

    /***
     * 提交投资信息
     * @param investVO
     * @return
     */
    String commitInvest(InvestVO investVO);

    /***
     * 通知信息
     * @param paramMap
     */
    void notify(Map<String, Object> paramMap);

    /**
     * 通过借款id查询
     * @param lendId
     * @param status
     * @return
     */
    List<LendItem> selectByLendId(Long lendId, Integer status);

    /**
     * 通过借款id查询
     * @param lendId
     * @return
     */
    List<LendItem> selectByLendId(Long lendId);
}
