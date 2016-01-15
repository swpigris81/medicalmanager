
/**
 * 系统项目名称
 * com.medical.manager.app.common.web.dao
 * ITblFundBalanceSeqDao.java
 * 
 * 2015年12月16日-下午8:46:15
 * 2015版权所有
 * 
 */

package com.medical.manager.app.common.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.common.web.model.TblFundBalanceSeq;

/**
 * @description 
 * 
 * @time 2015年12月16日 下午8:46:15
 * @author Jason
 * @version 1.0.0
 * 
 */

@Repository("tblFundBalanceSeqDao")
public interface ITblFundBalanceSeqDao {

	/**
	 * 
	 * @description 
	 * @param id
	 * @return TblFundBalanceSeq
	 * @author Jason
	 * @since  1.0.0
	 */
	TblFundBalanceSeq findById(Long id);
	
	/**
	 * 
	 * @description 新增 
	 * @param balanceSeq
	 * @author Jason
	 * @since  1.0.0
	 */
	void insert(TblFundBalanceSeq balanceSeq);
	
	/**
	 * 
	 * @description 更新
	 * @param balanceSeq
	 * @author Jason
	 * @since  1.0.0
	 */
	void update(TblFundBalanceSeq balanceSeq);
	
	/**
	 * 
	 * @description 根据客户id查询客户收支记录 
	 * @param cusId
	 * @return List<TblFundBalanceSeq>
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblFundBalanceSeq> queryBalanceSeqByCusId(Long cusId);
}
