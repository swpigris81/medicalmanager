/**
 * 系统项目名称
 * com.medical.manager.app.center.web.dao
 * ITblCusMedicalRecordsVisitDao.java
 * 
 * 2015年12月15日-上午11:26:13
 * 2015
 * 
 */

package com.medical.manager.app.center.web.dao;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.center.web.model.TblCusMedicalRecordsVisit;

/**
 * 
 * ITblCusMedicalRecordsVisitDao
 * 
 * 2015年12月15日 上午11:26:13
 * 
 * @version 1.0.0
 * 
 */
@Repository("tblCusMedicalRecordsVisitDao")
public interface ITblCusMedicalRecordsVisitDao {

	/**
	 * 
	 * 这里是方法描述
	 *
	 * @date 2015年12月21日 下午5:11:08
	 * @param recordsVisit
	 * void
	 */
	void insert(TblCusMedicalRecordsVisit recordsVisit);
}
