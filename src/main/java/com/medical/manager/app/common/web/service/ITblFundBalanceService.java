/**
 * 系统项目名称
 * com.medical.manager.app.common.web.service
 * ITblFundBalanceService.java
 * 
 * 2015年12月23日-上午11:58:45
 * 2015版权所有
 * 
 */
package com.medical.manager.app.common.web.service;

import java.math.BigDecimal;

import com.medical.manager.app.common.web.model.TblFundBalance;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月23日 上午11:58:45
 * @author Json
 * @version 1.0.0
 */
public interface ITblFundBalanceService {
	
	/**
	 * 改变余额
	 *
	 * @date 2015年12月23日 下午12:00:04
	 * @param cusId		客户id
	 * @param amount	变动资金
	 * @param inOrOut	变动类型，收入或支出，0收入，1支出
	 * @param changeDes 变动描述，如：充值、返现、提现、购物等
	 * @param recommendFlag	是否推荐费标识，Y是，N否，默认N
	 * @return void
	 */
	void changeBalance(final Long cusId, final BigDecimal amount,
			final String inOrOut, final String changeDes, final String recommendFlag);

	/**
	 * 这里是方法描述
	 *
	 * @date 2015年12月23日 下午1:43:55
	 * @param cusId
	 * @return TblFundBalance
	 */
	TblFundBalance findByCusId(Long cusId);
}
