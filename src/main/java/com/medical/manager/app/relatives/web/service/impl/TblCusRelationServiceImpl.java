
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.service.impl
 * TblCusRelationServiceImpl.java
 * 
 * 2015年12月19日-下午10:09:12
 * 2015版权所有
 * 
 */

package com.medical.manager.app.relatives.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.relatives.web.dao.TblCustRelationDao;
import com.medical.manager.app.relatives.web.model.TblCustRelation;
import com.medical.manager.app.relatives.web.service.ITblCusRelationService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.StringUtil;

/**
 * @description
 * 
 * @time 2015年12月19日 下午10:09:12
 * @author Jason
 * @version 1.0.0
 * 
 */
@Transactional
@Service("tblCusRelationService")
public class TblCusRelationServiceImpl implements ITblCusRelationService {

	@Resource
	private TblCustRelationDao custRelationDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.app.relatives.web.service.ITblCusRelationService#
	 * agreeRelationApply(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void agreeRelationApply(String cusId, String relationId) throws Exception {
		// 验证relationId和cusId当前关系，因为是验证，所有要先查询是否relationId申请添加了cusId为关系人
		Map<String, String> paramMap = new HashMap<String, String>(2);
		paramMap.put("custId", relationId);
		paramMap.put("relationId", cusId);
		List<TblCustRelation> relations = custRelationDao.findCustRelation(paramMap );
		if (relations == null || relations.size() == 0) {
			throw new Exception("不存在的申请！");
		}
		
		// 修改relationId和cusId当前关系为通过
		TblCustRelation relation = relations.get(0);
		relation.setRelationStatus(CommonEnums.YesOrNo.YES.id);
		custRelationDao.updateCustRelation(relation);
		
		// 新增cusId和relationId关系，并设置为通过
		TblCustRelation addRelation = new TblCustRelation();
		addRelation.setAgreeFlag(CommonEnums.YesOrNo.YES.id);
		addRelation.setCustId(relation.getRelationId());
		addRelation.setCustName(relation.getRelationName());
		addRelation.setCustType(relation.getRelationType());
		addRelation.setMobileNo(relation.getRelationMobile());
		addRelation.setRelationId(relation.getCustId());
		addRelation.setRelationMobile(relation.getMobileNo());
		addRelation.setRelationName(relation.getCustName());
		addRelation.setRelationType(relation.getCustType());
		addNewCustRelation(relation);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblCusRelationService#allowVisitMe(java.lang.String, java.lang.String)
	 */
	@Override
	public void allowVisitMe(String cusId, String relationId) throws Exception {
		// 查询cusId和relationId关系
		Map<String, String> paramMap = new HashMap<String, String>(2);
		paramMap.put("custId", cusId);
		paramMap.put("relationId", relationId);
		List<TblCustRelation> relations = custRelationDao.findCustRelation(paramMap );
		if (relations == null || relations.size() == 0) {
			throw new Exception("关系人不存在！");
		}
		// 允许关系人查看我的足迹
		TblCustRelation relation = relations.get(0);
		relation.setAllowFlag(CommonEnums.YesOrNo.YES.id);
		custRelationDao.updateCustRelation(relation);
		
		// 关系人被允许查看我的足迹
		paramMap = new HashMap<String, String>(2);
		paramMap.put("custId", relationId);
		paramMap.put("relationId", cusId);
		List<TblCustRelation> relationeds = custRelationDao.findCustRelation(paramMap );
		TblCustRelation relationed = relationeds.get(0);
		relationed.setAllowedFlag(CommonEnums.YesOrNo.YES.id);
		custRelationDao.updateCustRelation(relationed);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblCusRelationService#addNewCustRelation(com.medical.manager.app.relatives.web.model.TblCustRelation)
	 */
	@Override
	public void addNewCustRelation(TblCustRelation relation) {
		if (StringUtil.isEmpty(relation.getAgreeFlag()))
			relation.setAgreeFlag(CommonEnums.YesOrNo.NO.id);
		if (StringUtil.isEmpty(relation.getAllowFlag()))
			relation.setAllowFlag(CommonEnums.YesOrNo.NO.id);
		if (StringUtil.isEmpty(relation.getAllowedFlag()))
			relation.setAllowedFlag(CommonEnums.YesOrNop.NO.id);
		if (StringUtil.isEmpty(relation.getRelationStatus()))
			relation.setRelationStatus(CommonEnums.YesOrNop.NO.id);
		custRelationDao.addNewCustRelation(relation);
	}
}
