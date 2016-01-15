
/**
 * 系统项目名称
 * com.medical.manager.app.common.web.dao
 * ITblFundWithdrawAccDao.java
 * 
 * 2015年12月16日-下午9:48:01
 * 2015版权所有
 * 
 */

package com.medical.manager.app.common.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.common.web.model.TblFundWithdrawAcc;

/**
 * @description 
 * 
 * @time 2015年12月16日 下午9:48:01
 * @author Jason
 * @version 1.0.0
 * 
 */

@Repository("tblFundWithdrawAccDao")
public interface ITblFundWithdrawAccDao {

	/**
	 * 根据id查询
	 * @description 
	 * @param id
	 * @return TblFundWithdrawAcc
	 * @author Jason
	 * @since  1.0.0
	 */
	TblFundWithdrawAcc findById(Long id);
	
	/**
	 * 根据客户id查询客户所有收货地址
	 * @description 
	 * @param cusId
	 * @return List<TblFundWithdrawAcc>
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblFundWithdrawAcc> queryByCusId(Long cusId);
	
	/**
	 * @description 
	 * @param withdrawAcc
	 * @author Jason
	 * @since  1.0.0
	 */
	void insert(TblFundWithdrawAcc withdrawAcc); 
	
	/**
	 * 
	 * @description 
	 * @param withdrawAcc
	 * @author Jason
	 * @since  1.0.0
	 */
	void update(TblFundWithdrawAcc withdrawAcc);

	/**
	 * @description 
	 * @param id
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void delete(Long id);
	
	/**
	 * @description 设置某个用户的默认收货地址为非默认收货地址
	 * @param cusId
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateDefaultToNonDefault(Long cusId);
}
