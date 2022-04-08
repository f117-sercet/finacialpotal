package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.Vo.LoginVO;
import com.atguigu.srb.core.pojo.Vo.RegisterVO;
import com.atguigu.srb.core.pojo.Vo.UserIndexVO;
import com.atguigu.srb.core.pojo.Vo.UserInfoVO;
import com.atguigu.srb.core.pojo.entity.UserInfo;
import com.atguigu.srb.core.pojo.query.UserInfoQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface UserInfoService extends IService<UserInfo> {


    UserInfoVO login(LoginVO loginVO, String ip);


    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id, Integer status);

    boolean checkMobile(String mobile);

    UserIndexVO getIndexUserInfo(Long userId);

    String getMobileByBindCode(String bindCode);

    void register(RegisterVO registerVO);



}
