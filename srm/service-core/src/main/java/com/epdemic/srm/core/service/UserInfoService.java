package com.epdemic.srm.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.epdemic.srm.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.srm.core.pojo.query.UserInfoQuery;
import com.epdemic.srm.core.pojo.vo.LoginVO;
import com.epdemic.srm.core.pojo.vo.RegisterVO;
import com.epdemic.srm.core.pojo.vo.UserIndexVO;
import com.epdemic.srm.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 注册
     * @param registerVO
     */
    void register(RegisterVO registerVO);

    /**
     * 登录
     * @param loginVO
     * @param ip
     * @return
     */
    UserInfoVO login(LoginVO loginVO, String ip);

    /**
     * 用户信息列表
     * @param pageParam
     * @param userInfoQuery
     * @return
     */
    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    /**
     * 锁定
     * @param id
     * @param status
     */
    void lock(Long id, Integer status);

    /**
     * 检查手机号
     * @param mobile
     * @return
     */
    boolean checkMobile(String mobile);

    /**
     * 获取检索的用户信息
     * @param userId
     * @return
     */
    UserIndexVO getIndexUserInfo(Long userId);

    /**
     * 通过绑定账号获取移动电话
     * @param bindCode
     * @return
     */
    String getMobileByBindCode(String bindCode);
}
