package com.epdemic.srm.core.service.impl;

import com.epdemic.srm.core.pojo.entity.UserAccount;
import com.epdemic.srm.core.mapper.UserAccountMapper;
import com.epdemic.srm.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
