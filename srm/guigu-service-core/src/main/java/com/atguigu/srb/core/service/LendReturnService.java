package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.entity.LendReturn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 还款记录表 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface LendReturnService extends IService<LendReturn> {

    /**
     * 通过借款id查询
     * @param lendId
     * @return
     */
    List<LendReturn> selectByLendId(Long lendId);

    /**
     * 提交返回
     * @param lendReturnId
     * @param userId
     * @return
     */
    String commitReturn(Long lendReturnId, Long userId);

    /**
     *消息确认
     * @param paramMap
     */
    void notify(Map<String, Object> paramMap);
}
