
/**
 * 系统项目名称
 * com.medical.manager.app.center.web.dao
 * TblMedicalRecordsDao.java
 * 
 * 2015年12月15日-下午12:42:10
 * 2015
 * 
 */
 
package com.medical.manager.app.center.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.center.web.model.TblCustMedicalRecords;


/**
 * 
 * TblMedicalRecordsDao
 * 
 * 2015年12月15日 下午12:42:10
 * 
 * @version 1.0.0
 * 
 */
@Repository("medicalRecordsDao")
public interface TblMedicalRecordsDao {
    /**
     * findMedicalRecords 查询病历
     * (这里描述这个方法适用条件 – 可选)
     * @param paramMap 查询条件
     * @return List<TblCustMedicalRecords>
     * @exception 
     * @since  1.0.0
     */
    public List<TblCustMedicalRecords> findMedicalRecords(Map paramMap);
    
    /**
     * 新增病历
     * 这里是方法描述
     *
     * @date 2015年12月21日 下午2:17:49
     * @param records
     * void
     */
    void insert(TblCustMedicalRecords records);
}
