package com.epdemic.heepay.service;

import java.util.Map;

/**
 *
 * @author dsc
 * @date
 * 借款还贷
 */
public interface LendReturnService {

	/**
	 * 返还确认
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> returnCommit(Map<String, Object> paramMap);

}
