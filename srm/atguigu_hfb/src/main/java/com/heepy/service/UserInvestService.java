package com.heepy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heepy.model.UserInvest;
import com.heepy.model.UserInvestQueryVo;

import java.util.Map;

/**
 * Description： 用户投资服务
 * @author: 段世超
 * @aate: Created in 2022/3/5 11:34
 */
public interface UserInvestService extends IService<UserInvest> {

    /**
     * 讲师分页列表
     * @param userInvestQueryVo
     * @return
     */
    IPage<UserInvest> selectPage(Page<UserInvest> pageParam, UserInvestQueryVo userInvestQueryVo);

    /**
     * 用户投资
     * @param paramMap
     * @return
     */
    UserInvest invest(Map<String, Object> paramMap);

    /**
     * 借贷
     * @param paramMap
     * @return
     */
    String makeLoan(Map<String, Object> paramMap);

    /**
     * 取消借贷
     * @param paramMap
     * @return
     */
    String cancelLend(Map<String, Object> paramMap);
}
