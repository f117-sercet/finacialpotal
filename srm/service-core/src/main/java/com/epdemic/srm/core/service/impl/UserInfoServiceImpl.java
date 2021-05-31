package com.epdemic.srm.core.service.impl;

import com.epdemic.srm.core.pojo.entity.UserInfo;
import com.epdemic.srm.core.mapper.UserInfoMapper;
import com.epdemic.srm.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
