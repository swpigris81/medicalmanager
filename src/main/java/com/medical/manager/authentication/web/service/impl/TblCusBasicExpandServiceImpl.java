/**
 * 
 */
package com.medical.manager.authentication.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.manager.authentication.web.dao.ITblCusBasicExpandDao;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.authentication.web.service.ITblCusBasicExpandService;
import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.DateUtil;

/**
 * @author jason
 *
 * 下午3:39:10
 *  @version 1.0.0
 */
@Service("cusBasicExpandService")
public class TblCusBasicExpandServiceImpl implements ITblCusBasicExpandService {
    
    @Resource
    private ITblCusBasicExpandDao cusBasicExpandDao;
    
    @Resource
    private IBaseDao<TblCusBasicExpand> baseDao;

    @Override
    public List<TblCusBasicExpand> queryCusBasicExpandList(Integer page, Integer rows, Map<String, String> paramMap) {
        return this.baseDao.queryWithPage(paramMap, page, rows, "queryCusBasicExpandList");
    }

    @Override
    public void checkCusBasicExpand(TblCusBasicExpand cusBasicExpand) {
        cusBasicExpand.setCheckTime(DateUtil.getNowTimes());
        this.cusBasicExpandDao.checkCusBasicExpand(cusBasicExpand);
    }

    @Override
    public void saveCusBasicExpand(TblCusBasicExpand cusBasicExpand) {
        this.cusBasicExpandDao.insert(cusBasicExpand);
    }

    @Override
    public void updateCusBasicExpand(TblCusBasicExpand cusBasicExpand) {
        this.cusBasicExpandDao.updateByKeySelective(cusBasicExpand);
        
    }
	

}
