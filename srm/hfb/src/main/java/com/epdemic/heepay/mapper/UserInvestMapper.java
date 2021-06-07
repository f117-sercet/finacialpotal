package com.epdemic.heepay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.epdemic.heepay.model.UserInvest;
import com.epdemic.heepay.model.UserInvestQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author:estic
 * @Date: 2021/6/7 15:18
 */
@Repository
public interface UserInvestMapper extends BaseMapper<UserInvest>{
    /**
     * 分页
     * @param page
     * @param userInvestQueryVo
     * @return
     */
    IPage<UserInvest> selectPage(Page<UserInvest> page, @Param("vo") UserInvestQueryVo userInvestQueryVo);

    /**
     * 根据总额选取
     * @param agentProjectCode
     * @return
     */
    String selectSumVoteAmtByAgentProjectCode(@Param("agentProjectCode") String agentProjectCode);

    /**
     * 根据代理代号选择
     * @param agentProjectCode
     * @return
     */
    UserInvest selectByAgentProjectCode(@Param("agentProjectCode") String agentProjectCode);

    /**
     * 根据工程代号选择
     * @param agentProjectCode
     * @return
     */
    Integer selectStatusByAgentProjectCode(@Param("agentProjectCode") String agentProjectCode);

}
