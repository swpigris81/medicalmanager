
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.service
 * ITblCusAddressService.java
 * 
 * 2015年12月17日-上午11:47:48
 * 2015版权所有
 * 
 */
package com.medical.manager.app.relatives.web.service;

import java.util.List;

import com.medical.manager.app.relatives.web.model.TblCusAddress;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月17日 上午11:47:48
 * @author Json
 * @version 1.0.0
 */
public interface ITblCusAddressService {
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
}
