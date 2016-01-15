
/**
 * 系统项目名称
 * com.medical.manager.app.ambulance.web.dao
 * IAmbulanceInfoVoDao.java
 * 
 * 2015年12月21日-下午4:46:29
 * 2015版权所有
 * 
 */
package com.medical.manager.app.ambulance.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.ambulance.web.vo.AmbulanceInfoVo;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月21日 下午4:46:29
 * @author Json
 * @version 1.0.0
 */
@Repository("ambulanceInfoVoDao")
public interface IAmbulanceInfoVoDao {

	/**
	 * 这里是方法描述
	 *
	 * @date 2015年12月21日 下午4:49:45
	 * @param paramMap
	 * @return List<AmbulanceInfoVo>
	 */
	List<AmbulanceInfoVo> queryNearAmbulance(Map<String, Object> paramMap);
}
