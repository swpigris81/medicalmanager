
/**
 * 系统项目名称
 * com.medical.manager.customer.web.dao
 * ITblCusReportDao.java
 * 
 * 2015年12月3日-下午8:14:26
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.dao;

import org.springframework.stereotype.Repository;

import com.medical.manager.customer.web.model.TblCusReport;


/**
 * @description 客户举报管理DAO
 * 
 * @time 2015年12月3日 下午8:14:26
 * @author Jason
 * @version 1.0.0
 * 
 */

@Repository("tblCusReportDao")
public interface ITblCusReportDao {

	/**
	 * @description 根据id查询
	 * @param id
	 * @return TblCusReport
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	TblCusReport queryById(String id);
	
	/**
	 * @description 更新
	 * @param report
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void update(TblCusReport report);
	
	/**
	 * @description 新增
	 * @param report
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void insert(TblCusReport report);
}
