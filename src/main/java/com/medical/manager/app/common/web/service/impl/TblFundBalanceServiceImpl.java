
/**
 * 系统项目名称
 * com.medical.manager.app.common.web.service.impl
 * TblFundBalanceServiceImpl.java
 * 
 * 2015年12月23日-下午12:02:21
 * 2015版权所有
 * 
 */
package com.medical.manager.app.common.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.common.web.dao.ITblFundBalanceDao;
import com.medical.manager.app.common.web.dao.ITblFundBalanceSeqDao;
import com.medical.manager.app.common.web.model.TblFundBalance;
import com.medical.manager.app.common.web.model.TblFundBalanceSeq;
import com.medical.manager.app.common.web.service.ITblFundBalanceService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.StringUtil;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月23日 下午12:02:21
 * @author wusj
 * @version 1.0.0
 */
@Transactional
@Service("tblFundBalanceService")
public class TblFundBalanceServiceImpl implements ITblFundBalanceService {

	@Resource
	private ITblFundBalanceDao tblFundBalanceDao;
	@Resource
	private ITblFundBalanceSeqDao tblFundBalanceSeqDao;
	
	
	/* (non-Javadoc)
	 * @see com.medical.manager.app.common.web.service.ITblFundBalanceService#changeBalance(java.lang.Long, java.math.BigDecimal)
	 */
	@Override
	public void changeBalance(final Long cusId, final BigDecimal amount,
			final String inOrOut, final String changeDes,
			final String recommendFlag) {

		String _changeTime = DateUtil.fmtDateToYMDHMS(new Date());
		String _recommendFlag = StringUtil.isEmpty(recommendFlag) ? CommonEnums.YesOrNo.NO.id
				: recommendFlag;
		BigDecimal _changeAmount = amount; // 默认收入
		if ("1".equals(inOrOut)) { // 支出
			_changeAmount = amount.multiply(new BigDecimal(-1));
		}

		TblFundBalance _balance = new TblFundBalance(cusId, _changeAmount, _changeTime, null);
		TblFundBalanceSeq _bSeq = new TblFundBalanceSeq(cusId, inOrOut, amount, _changeTime, changeDes, _recommendFlag);

		tblFundBalanceDao.changeBalance(_balance);
		tblFundBalanceSeqDao.insert(_bSeq);
	}


	/* (non-Javadoc)
	 * @see com.medical.manager.app.common.web.service.ITblFundBalanceService#queryByCusId(java.lang.Long)
	 */
	@Override
	public TblFundBalance findByCusId(Long cusId) {
		TblFundBalance balance = tblFundBalanceDao.findByCusId(cusId);
		// 没有账户信息，新增
		if (balance == null) {
			balance = new TblFundBalance(cusId, new BigDecimal(0), null, null);
			tblFundBalanceDao.insert(balance);
		}
		return balance;
	}
}
