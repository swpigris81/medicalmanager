
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.service
 * ITblCusRelationService.java
 * 
 * 2015年12月19日-下午10:07:27
 * 2015版权所有
 * 
 */

package com.medical.manager.app.relatives.web.service;

import com.medical.manager.app.relatives.web.model.TblCustRelation;

/**
 * @description 
 * 
 * @time 2015年12月19日 下午10:07:27
 * @author Jason
 * @version 1.0.0
 * 
 */

public interface ITblCusRelationService {

	/**
	 * 同意他人添加我为关系人
	 * @description 
	 * @param cusId			我的id
	 * @param relationId	添加我为关系人的他人id
	 * @exception Exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void agreeRelationApply(String cusId, String relationId) throws Exception;
	
	/**
	 * 允许关系人访问我的足迹
	 * @description 
	 * @param cusId			我的id
	 * @param relationId	关系人id
	 * @exception Exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void allowVisitMe(String cusId, String relationId) throws Exception;
	
    /**
     * addNewCustRelation 新增关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    public void addNewCustRelation(TblCustRelation relation);
}
