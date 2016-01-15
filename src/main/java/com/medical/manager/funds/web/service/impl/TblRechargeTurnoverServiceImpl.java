
/**
 * 系统项目名称
 * com.medical.manager.funds.web.service.impl
 * TblRechargeTurnoverServiceImpl.java
 * 
 * 2015年12月7日-上午11:38:55
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.funds.web.dao.ITblRechargeTurnoverDao;
import com.medical.manager.funds.web.model.TblRechargeTurnover;
import com.medical.manager.funds.web.service.ITblRechargeTurnoverService;


/**
 * 
 * TblRechargeTurnoverServiceImpl 充值记录
 * 
 * 2015年12月7日 上午11:38:55
 * 
 * @version 1.0.0
 * 
 */
@Transactional
@Service("rechargeTurnoverService")
public class TblRechargeTurnoverServiceImpl implements ITblRechargeTurnoverService {
    @Resource
    private ITblRechargeTurnoverDao rechargeTurnoverDao;
    @Resource
    private IBaseDao<TblRechargeTurnover> baseDao;
    private final String RECHARGE_TURNOVER_PAGE_SQL = "com.medical.manager.funds.web.dao.ITblRechargeTurnoverDao.findRechargeTurnover";
    private final String RECHARGE_TURNOVER_PAGE_EXPORT_SQL = "com.medical.manager.funds.web.dao.ITblRechargeTurnoverDao.findRechargeTurnoverExport";
    
    @Override
    public List<TblRechargeTurnover> queryWithPaging(Integer page, Integer rows, Map paramMap) {
        return baseDao.queryWithPage(paramMap, page, rows, RECHARGE_TURNOVER_PAGE_SQL);
    }

    @Override
    public List<TblRechargeTurnover> queryWithPagingExport(Integer page, Integer rows, Map paramMap) {
        return baseDao.queryWithPage(paramMap, page, rows, RECHARGE_TURNOVER_PAGE_EXPORT_SQL);
    }

    @Override
    public Integer queryCostExpireNotice(String nowDate,String preDate) {
        Map<String, String> paramMap=new HashMap<String, String>();
        paramMap.put("nowDate", nowDate);
        paramMap.put("preDate", preDate);
        return this.rechargeTurnoverDao.queryCostExpireNotice(paramMap);
    }
}
