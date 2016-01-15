
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.service.impl
 * TblCusAddressServiceImpl.java
 * 
 * 2015年12月17日-上午11:48:59
 * 2015版权所有
 * 
 */
package com.medical.manager.app.relatives.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.relatives.web.dao.ITblCusAddressDao;
import com.medical.manager.app.relatives.web.model.TblCusAddress;
import com.medical.manager.app.relatives.web.service.ITblCusAddressService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.StringUtil;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月17日 上午11:48:59
 * @author Json
 * @version 1.0.0
 */

@Transactional
@Service("tblCusAddressService")
public class TblCusAddressServiceImpl implements ITblCusAddressService {

	@Resource
	private ITblCusAddressDao tblCusAddressDao;
	
	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblCusAddressService#findById(java.lang.Long)
	 */
	@Override
	public TblCusAddress findById(Long id) {
		return tblCusAddressDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblCusAddressService#queryByCusId(java.lang.Long)
	 */
	@Override
	public List<TblCusAddress> queryByCusId(Long cusId) {
		return tblCusAddressDao.queryByCusId(cusId);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblCusAddressService#insert(com.medical.manager.app.relatives.web.model.TblCusAddress)
	 */
	@Override
	public void insert(TblCusAddress address) {
		if (address != null) {
			address.setCreate_time(DateUtil.fmtDateToYMDHMS(new Date()));
			preSaveOrUpdate(address);
			tblCusAddressDao.insert(address);
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblCusAddressService#update(com.medical.manager.app.relatives.web.model.TblCusAddress)
	 */
	@Override
	public void update(TblCusAddress address) {
		if (address != null) {
			TblCusAddress _aAddress = findById(address.getId());
			if (_aAddress != null) {
				address.setCus_id(_aAddress.getCus_id());
				preSaveOrUpdate(address);
				tblCusAddressDao.update(address);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.app.relatives.web.service.ITblCusAddressService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		tblCusAddressDao.delete(id);
	}
	
	private void preSaveOrUpdate(TblCusAddress address) {
		if (address != null) {
			if (StringUtil.isEmpty(address.getDefault_flag())) {
				address.setDefault_flag(CommonEnums.YesOrNo.NO.id);
			}
			// 默认地址，则需要修改之前的所有地址为非默认地址
			if (CommonEnums.YesOrNo.YES.id.equals(address.getDefault_flag())) {
				tblCusAddressDao.updateDefaultToNonDefault(address.getCus_id());
			}
		}
	}
}
