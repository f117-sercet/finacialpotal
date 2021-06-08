package com.epdemic.heepay.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.heepay.model.UserBind;

import java.util.Map;

/**
 * 账户绑定
 */
public interface UserBindService extends IService<UserBind> {
	/**
	 * 绑定
	 * @param paramMap
	 * @return
	 */
	UserBind bind(Map<String, Object> paramMap);

	/**
	 * 已绑定
	 * @param idCard
	 * @return
	 */
	boolean isBind(String idCard);

	/**
	 * 获取绑定码
	 * @param bindCode
	 * @return
	 */
	UserBind getByBindCode(String bindCode);

	/**
	 * 检查
	 * @param bindCode
	 * @param password
	 */
	void checkPassword(String bindCode, String password);
}
