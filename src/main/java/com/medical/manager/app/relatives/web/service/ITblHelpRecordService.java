/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.service
 * ITblHelpRecordService.java
 * 
 * 2015年12月18日-下午4:04:36
 * 2015版权所有
 * 
 */
package com.medical.manager.app.relatives.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.app.relatives.web.model.TblHelpRecord;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月18日 下午4:04:36
 * @author Json
 * @version 1.0.0
 */
public interface ITblHelpRecordService {
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
	
	void evaluationHelp(Long helpId, String cusId, String evaluationType,
			String evaluateContent, String evaluateStar) throws Exception;
}
