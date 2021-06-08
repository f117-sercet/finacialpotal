package com.epdemic.heepay.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.heepay.model.UserInvest;
import com.epdemic.heepay.model.UserInvestQueryVo;
import java.util.Map;

/**
 * @author estic
 */
public interface UserInvestService extends IService<UserInvest> {

	/**
	 * 讲师分页列表
	 * @param pageParam
	 * @param userInvestQueryVo
	 * @return
	 */
	IPage<UserInvest> selectPage(Page<UserInvest> pageParam, UserInvestQueryVo userInvestQueryVo);

	/**
	 * 投资
	 * @param paramMap
	 * @return
	 */
	UserInvest invest(Map<String, Object> paramMap);

	/**
	 * 借贷
	 * @param paramMap
	 * @return
	 */
	String makeLoan(Map<String, Object> paramMap);

	/**
	 * 取消借贷
	 * @param paramMap
	 * @return
	 */
	String cancelLend(Map<String, Object> paramMap);

}
