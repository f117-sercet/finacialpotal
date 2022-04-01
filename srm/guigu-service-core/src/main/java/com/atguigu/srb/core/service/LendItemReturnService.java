package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.entity.LendItemReturn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借回款记录表 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface LendItemReturnService extends IService<LendItemReturn> {

    /**
     * 根据lendId选择对象
     * @param lendId
     * @param userId
     * @return
     */
    List<LendItemReturn> selectByLendId(Long lendId,Long userId);

    /**
     * 添加汇款列表
     * @param lendReturnId
     * @return
     */
    List<Map<String,Object>> addReturnDetail(Long lendReturnId);

    /**
     * 根据还款记录的id查询对应的汇款记录
     * @param lendReturnId
     * @return
     */
    List<LendItemReturn> selectLendItemReturnList(Long lendReturnId);
}
