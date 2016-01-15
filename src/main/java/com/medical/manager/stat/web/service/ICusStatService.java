
/**
 * 系统项目名称
 * com.medical.manager.stat.web.service
 * ICusGeneralStatService.java
 * 
 * 2015年12月8日-上午9:51:04
 * 2015版权所有
 * 
 */
package com.medical.manager.stat.web.service;

import java.util.List;

import com.medical.manager.stat.web.dto.CusStatDto;
import com.medical.manager.stat.web.model.CusGeneralStat;
import com.medical.manager.stat.web.model.CusStatAreaAlarm;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月8日 上午9:51:04
 * @author Jason
 * @version 1.0.0
 */
public interface ICusStatService {

	/**
	 * 这里是方法描述
	 *
	 * @author Jason
	 * @date 2015年12月8日 上午9:53:23
	 * @param statDto
	 * @param page
	 * @param rows
	 * @return
	 * List<CusGeneralStat>
	 */
	List<CusGeneralStat> queryGeneralStatList(CusStatDto statDto, Integer page,
			Integer rows);
	
	/**
	 * 这里是方法描述
	 *
	 * @author Jason
	 * @date 2015年12月8日 上午10:17:04
	 * @param statDto
	 * @param page
	 * @param rows
	 * @return
	 * List<CusGeneralStat>
	 */
	List<CusGeneralStat> exportGeneralStat(CusStatDto statDto, Integer page,
			Integer rows);
	
	/**
	 * 这里是方法描述
	 *
	 * @author Jason
	 * @date 2015年12月8日 下午12:54:27
	 * @param statDto
	 * @param page
	 * @param rows
	 * @return
	 * List<StatAreaAlarm>
	 */
	List<CusStatAreaAlarm> queryStatAreaAlarmList(CusStatDto statDto, Integer page,
			Integer rows);
	
	/**
	 * @description 查询辖区数据统计
	 * @param statDto
	 * @return List
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List queryStatAreaAlarmStat(CusStatDto statDto);
	
	/**
	 * 这里是方法描述
	 *
	 * @author Jason
	 * @date 2015年12月8日 下午12:54:32
	 * @param statDto
	 * @param page
	 * @param rows
	 * @return
	 * List<StatAreaAlarm>
	 */
	List<CusStatAreaAlarm> exportStatAreaAlarm(CusStatDto statDto, Integer page,
			Integer rows);
}
