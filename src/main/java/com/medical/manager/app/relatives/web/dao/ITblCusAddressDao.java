
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.dao
 * ITblCusAddressDao.java
 * 
 * 2015年12月16日-下午9:48:01
 * 2015版权所有
 * 
 */

package com.medical.manager.app.relatives.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.relatives.web.model.TblCusAddress;

/**
 * @description 
 * 
 * @time 2015年12月16日 下午9:48:01
 * @author Jason
 * @version 1.0.0
 * 
 */

@Repository("tblCusAddressDao")
public interface ITblCusAddressDao {

	/**
	 * 根据id查询
	 * @description 
	 * @param id
	 * @return TblCusAddress
	 * @author Jason
	 * @since  1.0.0
	 */
	TblCusAddress findById(Long id);
	
	/**
	 * 根据客户id查询客户所有收货地址
	 * @description 
	 * @param cusId
	 * @return List<TblCusAddress>
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblCusAddress> queryByCusId(Long cusId);
	
	/**
	 * @description 
	 * @param address
	 * @author Jason
	 * @since  1.0.0
	 */
	void insert(TblCusAddress address); 
	
	/**
	 * 
	 * @description 
	 * @param address
	 * @author Jason
	 * @since  1.0.0
	 */
	void update(TblCusAddress address);

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
