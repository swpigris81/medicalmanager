/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.service.impl
 * TblHelpRecordServiceImpl.java
 * 
 * 2015年12月18日-下午4:05:12
 * 2015版权所有
 * 
 */
package com.medical.manager.app.relatives.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.ambulance.web.dao.ITblEvaluateInfoDao;
import com.medical.manager.app.ambulance.web.model.TblEvaluateInfo;
import com.medical.manager.app.relatives.web.dao.ITblHelpRecordDao;
import com.medical.manager.app.relatives.web.model.TblHelpRecord;
import com.medical.manager.app.relatives.web.service.ITblHelpRecordService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.customer.web.service.ICusBasicInfoService;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月18日 下午4:05:12
 * @author wusj
 * @version 1.0.0
 */
@Transactional
@Service("tblHelpRecordService")
public class TblHelpRecordServiceImpl implements ITblHelpRecordService {

	@Resource
	private ITblHelpRecordDao tblHelpRecordDao;
	@Resource
	private ITblEvaluateInfoDao tblEvaluateInfoDao;
	@Resource
	private ICusBasicInfoService cusBasicInfoService;

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblHelpRecordService#findById(java.lang.Long)
	 */
	@Override
	public TblHelpRecord findById(Long id) {
		return tblHelpRecordDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblHelpRecordService#insert(com.medical.manager.app.relatives.web.model.TblHelpRecord)
	 */
	@Override
	public void insert(TblHelpRecord record) {
		tblHelpRecordDao.insert(record);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblHelpRecordService#update(com.medical.manager.app.relatives.web.model.TblHelpRecord)
	 */
	@Override
	public void update(TblHelpRecord record) {
		tblHelpRecordDao.update(record);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblHelpRecordService#queryByMap(java.util.Map)
	 */
	@Override
	public List<Map<String, String>> queryByMap(Map<String, String> paramMap) {
		return tblHelpRecordDao.queryByMap(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblHelpRecordService#evaluationHelp(java.lang.Long, java.lang.String)
	 */
	@Override
	public void evaluationHelp(Long helpId, String cusId,
			String evaluationType, String evaluateContent, String evaluateStar)
			throws Exception {
		if (!"0".equals(evaluationType) && !"1".equals(evaluationType)) {
			throw new Exception("无效的评价类型，0医院或120中心，1急救车");
		}
		TblHelpRecord record = findById(Long.valueOf(helpId));
		if (record == null) {
			throw new Exception("求救记录不存在");
		}
		TblEvaluateInfo eInfo = new TblEvaluateInfo();
		eInfo.setCustId(Long.valueOf(cusId));
		eInfo.setEvaluateContent(evaluateContent);
		eInfo.setEvaluatedId(helpId);
		eInfo.setEvaluateStar(evaluateStar);
		eInfo.setEvaluateTime(DateUtil.fmtDateToYMDHMS(new Date()));
		tblEvaluateInfoDao.insert(eInfo);
		
		// 更新信息
		if ("0".equals(evaluationType)) {
			record.setRescue_evaluation_id(eInfo.getId());
			record.setRescue_state(CommonEnums.YesOrNo.YES.id);
		} else {
			record.setAmbulance_evaluation_id(eInfo.getId());
			record.setAmbulance_state(CommonEnums.YesOrNo.YES.id);
		}
		tblHelpRecordDao.update(record);
	}
}
