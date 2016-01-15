/**
 * 
 */
package com.medical.manager.authentication.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.medical.manager.authentication.web.dao.ITblCusPhoneChangeApplyDao;
import com.medical.manager.authentication.web.model.TblCusPhoneChangeApply;
import com.medical.manager.authentication.web.service.ITblCusPhoneChangeApplyService;
import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.customer.web.dao.ICusBasicInfoDao;
import com.medical.manager.customer.web.model.CusBasicInfo;

/**
 * @author jason
 * 
 *         下午3:37:31
 * @version 1.0.0
 */
@Service("cusPhoneChangeApplyService")
public class TblCusPhoneChangeApplyServiceImpl implements ITblCusPhoneChangeApplyService {
    
    @Resource
    private ITblCusPhoneChangeApplyDao cusPhoneChangeApplyDao;
    
    @Resource
    private IBaseDao<TblCusPhoneChangeApply> baseDao; 
    @Resource
    private ICusBasicInfoDao cusBasicInfoDao;

    @Override
    public List<TblCusPhoneChangeApply> queryPhoneChangeApplyList(Integer page, Integer rows, String beginDate,
            String endDate, String phoneNumber, String checkStatus) {
        Map<String, String> paramMap=new HashMap<String, String>();
        if (StringUtils.isNoneBlank(beginDate)) {
            paramMap.put("beginDate", beginDate+" 00:00:00");
        }
        if (StringUtils.isNoneBlank(endDate)) {
            paramMap.put("endDate", endDate+" 23:59:59");
        }
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("checkStatus", checkStatus);
        return this.baseDao.queryWithPage(paramMap, page, rows, "queryPhoneChangeApplyList");
    }

    @Override
    public void checkPhoneChangeApply(TblCusPhoneChangeApply phoneChangeApply) {
//        phoneChangeApply.set
        this.cusPhoneChangeApplyDao.updateByPrimaryKeySelective(phoneChangeApply);
        CusBasicInfo cusBasicInfo=new CusBasicInfo();
        cusBasicInfo.setId(phoneChangeApply.getUserId());
        cusBasicInfo.setPhone(phoneChangeApply.getNewPhone());
        this.cusBasicInfoDao.update(cusBasicInfo);
    }

    @Override
    public Integer queryphoneChangeCheck() {
        
        return this.cusPhoneChangeApplyDao.queryphoneChangeCheck();
    }

}
