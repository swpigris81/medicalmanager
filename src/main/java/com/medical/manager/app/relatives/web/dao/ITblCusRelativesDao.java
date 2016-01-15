
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.dao
 * TblCusRelativesDao.java
 * 
 * 2015年12月15日-下午1:48:28
 * 2015
 * 
 */
 
package com.medical.manager.app.relatives.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.relatives.web.model.TblCusRelatives;


/**
 * 
 * TblCustRelationDao
 * 
 * 2015年12月15日 下午1:48:28
 * 
 * @version 1.0.0
 * 
 */
@Repository("tblCusRelativesDao")
public interface ITblCusRelativesDao {
    /**
     * findById 主键查询
     * (这里描述这个方法适用条件 – 可选)
     * @param id
     * @return TblCustRelation
     * @exception 
     * @since  1.0.0
     */
    public TblCusRelatives queryRelativesDetail(Long id);
    
    /**
     * @description 查询客户亲属列表
     * @param cusId
     * @return List<TblCusRelatives>
     * @author Jason
     * @since  1.0.0
     */
    public List<TblCusRelatives> queryRelativesList(Long cusId);
}
