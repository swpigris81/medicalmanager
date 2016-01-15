
/**
 * 系统项目名称
 * com.medical.manager.customer.web.service.impl
 * ITblCusReportService.java
 * 
 * 2015年12月7日 上午11:56:19
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.service;

import java.util.List;

import com.medical.manager.customer.web.dto.TblCusReportDto;
import com.medical.manager.customer.web.model.TblCusReport;

/**
 * @description 客户举报管理
 * 
 * @time 2015年12月3日 下午8:26:05
 * @author Jason
 * @version 1.0.0
 * 
 */

public interface ITblCusReportService {
	/**
	 * @description 根据id查询客户基本信息
	 * @param id
	 * @return TblCusBasicInfo
	 * @author Jason
	 * @since  1.0.0
	 */
	TblCusReport queryById(String id);
	
	/**
	 * @description 更新客户基本信息
	 * @param report
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void update(TblCusReport report);
	
	/**
	 * @description 批量更新客户基本信息
	 * @param reports
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateBatch(List<TblCusReport> reports);
	
	/**
	 * @description 新增客户基本信息
	 * @param report
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void insert(TblCusReport report);
	
	/**
	 * @description 分页查询，查询条件保存在info中
	 * @param reportDto 查询条件
	 * @param page 		当前页码
	 * @param rows 		每页显示行数
	 * @return List<TblCusReport>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblCusReport> queryTblCusReportList(TblCusReportDto reportDto, Integer page, Integer rows);
}
