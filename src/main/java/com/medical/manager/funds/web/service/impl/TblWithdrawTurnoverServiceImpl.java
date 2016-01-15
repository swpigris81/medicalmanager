
/**
 * 系统项目名称
 * com.medical.manager.funds.web.service.impl
 * TblWithdrawTurnoverServiceImpl.java
 * 
 * 2015年12月7日-下午12:55:02
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.ambulance.web.service.IAmbulanceService;
import com.medical.manager.app.common.web.model.TblFundBalance;
import com.medical.manager.app.common.web.service.ITblFundBalanceService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.funds.web.dao.ITblWithdrawTurnoverDao;
import com.medical.manager.funds.web.model.TblWithdrawTurnover;
import com.medical.manager.funds.web.service.ITblWithdrawTurnoverService;
import com.medical.manager.systemset.web.dao.ITblSysInfoDao;
import com.medical.manager.systemset.web.model.TblSysInfo;


/**
 * 
 * TblWithdrawTurnoverServiceImpl
 * 
 * 2015年12月7日 下午12:55:02
 * 
 * @version 1.0.0
 * 
 */
@Transactional
@Service("withdrawTurnoverService")
public class TblWithdrawTurnoverServiceImpl implements ITblWithdrawTurnoverService {
	
	@Resource
	private ITblSysInfoDao tblSysInfoDao;
    @Resource
    private ITblWithdrawTurnoverDao withdrawTurnoverDao;
    
    @Resource
    private IAmbulanceService ambulanceService;
    @Resource
    private ITblFundBalanceService tblFundBalanceService; 
    @Resource
    private IBaseDao<TblWithdrawTurnover> baseDao;
    
    private final String QUERY_WITHDRAW_TURNOVER_SQL = "com.medical.manager.funds.web.dao.ITblWithdrawTurnoverDao.findWithdrawTurnover";
    private final String QUERY_WITHDRAW_TURNOVER_EXPORT_SQL = "com.medical.manager.funds.web.dao.ITblWithdrawTurnoverDao.findWithdrawTurnoverExport";
    
    public void insert(TblWithdrawTurnover turnover) throws Exception {
    	if (turnover != null) {
    		Long cusId = Long.valueOf(turnover.getCustId());
    		
    		
    		// 查询当前余额
    		TblFundBalance balance = tblFundBalanceService.findByCusId(cusId);
    		// 余额小于提现金额，不允许提现
    		if(balance.getBalance().compareTo(turnover.getWithdrawAmount()) < 0) {
    			throw new Exception("余额小于提现金额，不允许提现！");
    		}
    		// 余额小于系统设定金额，不允许提现
    		TblSysInfo sInfo = ambulanceService.querySysInfoByType(CommonEnums.SystemParamType.LOWEST_CASH.id);
    		if (turnover.getWithdrawAmount().compareTo(new BigDecimal(sInfo.getParamValue())) < 0) {
    			throw new Exception("提现金额小于最低提现额，不允许提现！");
    		}
    		
    		turnover.setWithdrawTime(DateUtil.fmtDateToYMDHMS(new Date()));
    		turnover.setWithdrawStatus(CommonEnums.FundWithdrawStatus.NOT.id);
    		// 保存提现流水
    		withdrawTurnoverDao.insert(turnover);
    		// 变更余额信息
			tblFundBalanceService.changeBalance(cusId, turnover.getWithdrawAmount(),
					"1", "提现", CommonEnums.YesOrNo.NO.id);
    	}
    }
    
    @Override
    public List<TblWithdrawTurnover> queryWithPaging(Integer page, Integer rows, Map paramMap) {
        return baseDao.queryWithPage(paramMap, page, rows, QUERY_WITHDRAW_TURNOVER_SQL);
    }

    @Override
    public void doUpdateWithdraw(TblWithdrawTurnover withdrawTurnover) {
        withdrawTurnoverDao.updateWithdrawTurnover(withdrawTurnover);
    }

    @Override
    public List<TblWithdrawTurnover> queryWithPagingExport(Integer page, Integer rows, Map paramMap) {
        return baseDao.queryWithPage(paramMap, page, rows, QUERY_WITHDRAW_TURNOVER_EXPORT_SQL);
    }

    @Override
    public Integer queryWaitWithdrawals() {
        
        return this.withdrawTurnoverDao.queryWaitWithdrawals();
    }

}
