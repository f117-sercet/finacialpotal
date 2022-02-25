package com.heepy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heepy.model.UserInvest;
import com.heepy.model.UserInvestQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInvestMapper extends BaseMapper<UserInvest> {

 IPage<UserInvest> selectPage(Page<UserInvest> page, @Param("vo")UserInvestQueryVo userInvestQueryVo);

 String selectSumVoteAmtByAgentProjectCode(@Param("agentProjectCode")String agentProjectCode);
}
