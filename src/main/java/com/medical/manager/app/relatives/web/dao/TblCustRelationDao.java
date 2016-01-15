
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.dao
 * TblCustRelationDao.java
 * 
 * 2015年12月15日-下午1:48:28
 * 2015
 * 
 */
 
package com.medical.manager.app.relatives.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.relatives.web.model.TblCustRelation;


/**
 * 
 * TblCustRelationDao
 * 
 * 2015年12月15日 下午1:48:28
 * 
 * @version 1.0.0
 * 
 */
@Repository("custRelationDao")
public interface TblCustRelationDao {
    /**
     * updateCustRelation 修改关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    public void updateCustRelation(TblCustRelation relation);
    /**
     * addNewCustRelation 新增关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    public void addNewCustRelation(TblCustRelation relation);
    /**
     * findCustRelation 条件查询
     * (这里描述这个方法适用条件 – 可选)
     * @param paramMap 条件查询
     * @return List<TblCustRelation>
     * @exception 
     * @since  1.0.0
     */
    public List<TblCustRelation> findCustRelation(Map paramMap);
    /**
     * findById 主键查询
     * (这里描述这个方法适用条件 – 可选)
     * @param id
     * @return TblCustRelation
     * @exception 
     * @since  1.0.0
     */
    public TblCustRelation findById(Long id);
    /**
     * deleteRelation 删除关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    public void deleteRelation(Long id);
}
