package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.srm.core.pojo.vo.UserBindVO;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface UserBindService extends IService<UserBind> {
    /**
     * 提交绑定用户
     * @param userBindVO
     * @param userId
     * @return
     */
    String commitBindUser(UserBindVO userBindVO, Long userId);

    /**
     * 声明事项
     * @param paramMap
     */
    void notify(Map<String, Object> paramMap);

    /**
     * 通过id获取绑定账号
     * @param userId
     * @return
     */
    String getBindCodeByUserId(Long userId);
}
