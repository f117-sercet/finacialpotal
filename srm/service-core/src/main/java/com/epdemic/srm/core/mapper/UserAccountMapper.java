package com.epdemic.srm.core.mapper;

import com.epdemic.srm.core.pojo.entity.UserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户 Mapper 接口
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    /**
     * 更新账户
     * @param bindCode
     * @param amount
     * @param freezeAmount
     */
    void updateAccount(
            @Param("bindCode")String bindCode,
            @Param("amount") BigDecimal amount,
            @Param("freezeAmount")BigDecimal freezeAmount);
}
