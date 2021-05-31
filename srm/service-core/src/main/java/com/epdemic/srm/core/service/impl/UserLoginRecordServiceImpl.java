package com.epdemic.srm.core.service.impl;

import com.epdemic.srm.core.pojo.entity.UserLoginRecord;
import com.epdemic.srm.core.mapper.UserLoginRecordMapper;
import com.epdemic.srm.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
