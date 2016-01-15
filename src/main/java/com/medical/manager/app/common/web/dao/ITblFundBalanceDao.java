/**
 * 系统项目名称
 * com.medical.manager.app.common.web.dao
 * ITblFundBalanceDao.java
 * 
 * 2015年12月15日-下午1:48:28
 * 2015
 * 
 */

package com.medical.manager.app.common.web.dao;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.common.web.model.TblFundBalance;

/**
 * 
 * ITblFundBalance
 * 
 * 2015年12月15日 下午1:48:28
 * 
 * @version 1.0.0
 * 
 */
@Repository("tblFundBalanceDao")
public interface ITblFundBalanceDao {
	/**
	 * findByCusId 主键查询 (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param id
	 * @return TblCusDevice
	 * @exception
	 * @since 1.0.0
	 */
	public TblFundBalance findByCusId(Long cusId);

	/**
	 * 新增 (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param balance
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	void insert(TblFundBalance balance);

	/**
	 * 修改 (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param balance
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	void update(TblFundBalance balance);

	void changeBalance(TblFundBalance balance);
}
