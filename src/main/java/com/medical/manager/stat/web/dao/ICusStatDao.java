
/**
 * 系统项目名称
 * com.medical.manager.stat.web.dao
 * ICusStatDao.java
 * 
 * 2015年12月8日-下午1:01:28
 * 2015版权所有
 * 
 */
package com.medical.manager.stat.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.stat.web.model.CusGeneralStat;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月8日 下午1:01:28
 * @author Jason
 * @version 1.0.0
 */

@Repository("cusStatDao")
public interface ICusStatDao {

	/**
	 * @description 
	 * @param paramMap
	 * @return List<CusGeneralStat>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<CusGeneralStat> queryGeneralStatList(Map<String, String> paramMap);
	
	/**
	 * @description 更新 
	 * @param stat
	 * void
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateTblStatGeneral(CusGeneralStat stat);
	
	List queryStatAreaAlarmStat(Map<String, String> paramMap);
}
