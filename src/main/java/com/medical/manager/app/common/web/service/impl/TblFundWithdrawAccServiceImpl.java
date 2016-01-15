/**
 * 系统项目名称
 * com.medical.manager.app.common.web.service.impl
 * TblFundWithdrawAccServiceImpl.java
 * 
 * 2015年12月17日-下午2:06:30
 * 2015版权所有
 * 
 */
package com.medical.manager.app.common.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.common.web.dao.ITblFundWithdrawAccDao;
import com.medical.manager.app.common.web.model.TblFundWithdrawAcc;
import com.medical.manager.app.common.web.service.ITblFundWithdrawAccService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.StringUtil;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月17日 下午2:06:30
 * @author Json
 * @version 1.0.0
 */

@Transactional
@Service("tblFundWithdrawAccService")
public class TblFundWithdrawAccServiceImpl implements
		ITblFundWithdrawAccService {

	@Resource
	private ITblFundWithdrawAccDao tblFundWithdrawAccDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.app.common.web.service.ITblFundWithdrawAccDaoService
	 * #findById(java.lang.Long)
	 */
	@Override
	public TblFundWithdrawAcc findById(Long id) {
		return tblFundWithdrawAccDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.app.common.web.service.ITblFundWithdrawAccDaoService
	 * #queryByCusId(java.lang.Long)
	 */
	@Override
	public List<TblFundWithdrawAcc> queryByCusId(Long cusId) {
		return tblFundWithdrawAccDao.queryByCusId(cusId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.app.common.web.service.ITblFundWithdrawAccDaoService
	 * #insert(com.medical.manager.app.common.web.model.TblFundWithdrawAcc)
	 */
	@Override
	public void insert(TblFundWithdrawAcc withdrawAcc) {
		if (withdrawAcc != null) {
			withdrawAcc.setCreate_time(DateUtil.fmtDateToYMDHMS(new Date()));
			preSaveOrUpdate(withdrawAcc);
			tblFundWithdrawAccDao.insert(withdrawAcc);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.app.common.web.service.ITblFundWithdrawAccDaoService
	 * #update(com.medical.manager.app.common.web.model.TblFundWithdrawAcc)
	 */
	@Override
	public void update(TblFundWithdrawAcc withdrawAcc) {
		if (withdrawAcc != null) {
			TblFundWithdrawAcc _acc = findById(withdrawAcc.getId());
			if (_acc != null) {
				withdrawAcc.setCus_id(_acc.getCus_id());
				preSaveOrUpdate(withdrawAcc);
				tblFundWithdrawAccDao.update(withdrawAcc);
			}
		}		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.app.common.web.service.ITblFundWithdrawAccDaoService
	 * #delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		tblFundWithdrawAccDao.delete(id);
	}

	private void preSaveOrUpdate(TblFundWithdrawAcc withdrawAcc) {
		if (withdrawAcc != null) {
			// 默认设置为默认提现账户
			if (StringUtil.isEmpty(withdrawAcc.getDefault_flag())) {
				withdrawAcc.setDefault_flag(CommonEnums.YesOrNo.NO.id);
			}
			// 默认地址，则需要修改之前的所有地址为非默认地址
			if (CommonEnums.YesOrNo.YES.id.equals(withdrawAcc.getDefault_flag())) {
				tblFundWithdrawAccDao.updateDefaultToNonDefault(withdrawAcc.getCus_id());
			}
		}
	}
}
