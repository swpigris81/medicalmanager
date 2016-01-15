/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.dao
 * ITblHelpRecordDao.java
 * 
 * 2015年12月15日-下午1:48:28
 * 2015
 * 
 */

package com.medical.manager.app.relatives.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.relatives.web.model.TblHelpRecord;

/**
 * 
 * ITblCusDeviceDao
 * 
 * 2015年12月15日 下午1:48:28
 * 
 * @version 1.0.0
 * 
 */
@Repository("tblHelpRecordDao")
public interface ITblHelpRecordDao {
	/**
	 * findById 主键查询 (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param id
	 * @return TblCusDevice
	 * @exception
	 * @since 1.0.0
	 */
	public TblHelpRecord findById(Long id);

	/**
	 * 新增 (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param record
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	void insert(TblHelpRecord record);

	/**
	 * 修改 (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param record
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	void update(TblHelpRecord record);

	List<Map<String, String>> queryByMap(Map<String, String> paramMap);
}
