/**
 * 系统项目名称
 * com.medical.manager.app.common.web.service
 * ITblFundWithdrawAccService.java
 * 
 * 2015年12月17日-下午2:05:39
 * 2015版权所有
 * 
 */
package com.medical.manager.app.common.web.service;

import java.util.List;

import com.medical.manager.app.common.web.model.TblFundWithdrawAcc;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月17日 下午2:05:39
 * @author Json
 * @version 1.0.0
 */
public interface ITblFundWithdrawAccService {
	/**
	 * 根据id查询
	 * 
	 * @description
	 * @param id
	 * @return TblFundWithdrawAcc
	 * @author Jason
	 * @since 1.0.0
	 */
	TblFundWithdrawAcc findById(Long id);

	/**
	 * 根据客户id查询客户所有收货地址
	 * 
	 * @description
	 * @param cusId
	 * @return List<TblFundWithdrawAcc>
	 * @author Jason
	 * @since 1.0.0
	 */
	List<TblFundWithdrawAcc> queryByCusId(Long cusId);

	/**
	 * @description
	 * @param withdrawAcc
	 * @author Jason
	 * @since 1.0.0
	 */
	void insert(TblFundWithdrawAcc withdrawAcc);

	/**
	 * 
	 * @description
	 * @param withdrawAcc
	 * @author Jason
	 * @since 1.0.0
	 */
	void update(TblFundWithdrawAcc withdrawAcc);

	/**
	 * @description
	 * @param id
	 * @exception
	 * @author Jason
	 * @since 1.0.0
	 */
	void delete(Long id);
}
