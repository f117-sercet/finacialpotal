package com.heepy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heepy.model.UserInvest;

import java.util.Map;

/**
 * @author 段世超
 */
public interface LendReturnService extends IService<UserInvest> {

    Map<String,Object> returnCommit(Map<String,Object> paramMap);
}
