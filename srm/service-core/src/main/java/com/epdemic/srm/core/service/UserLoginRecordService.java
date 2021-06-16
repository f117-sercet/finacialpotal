package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.UserLoginRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户登录记录表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface UserLoginRecordService extends IService<UserLoginRecord> {
    /**
     * 列出前50位
     * @param userId
     * @return
     */
    List<UserLoginRecord> listTop50(Long userId);
}
