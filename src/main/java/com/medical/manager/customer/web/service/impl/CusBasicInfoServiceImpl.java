
/**
 * 系统项目名称
 * com.medical.manager.customer.web.service.impl
 * TblCusBasicInfoServiceImpl.java
 * 
 * 2015年12月3日-下午8:27:32
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.authentication.web.dao.ITblCusBasicExpandDao;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.SerialNumberUtil;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.customer.web.dao.ICusBasicInfoDao;
import com.medical.manager.customer.web.dto.CusBasicInfoDto;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;

/**
 * @description 客户基本信息管理实现类
 * 
 * @time 2015年12月3日 下午8:27:32
 * @author Jason
 * @version 1.0.0
 * 
 */

@Transactional
@Service("cusBasicInfoService")
public class CusBasicInfoServiceImpl implements ICusBasicInfoService {

	private static final String QUERY_BASIC_INFO_LIST = "com.medical.manager.customer.web.dao.ICusBasicInfoDao.queryBasicInfoList";
	private static final String QUERY_BASIC_INFO_LIST_EXPORT = "com.medical.manager.customer.web.dao.ICusBasicInfoDao.queryBasicInfoList4Export";
	
	@Resource
	private IBaseDao<CusBasicInfo> baseDao;
	@Resource
	private ICusBasicInfoDao cusBasicInfoDao;
	@Resource ITblCusBasicExpandDao cusBasicExpandDao;
    
	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#queryCity120(java.lang.String)
	 */
	@Override
	public List<TblCusBasicExpand> queryCity120(String cityId) {
		Map<String, String> paramMap = new HashMap<String, String>(2);
		paramMap.put("cusType", CommonEnums.CustomType.A120.id);
		paramMap.put("useCity", cityId);
		return cusBasicInfoDao.queryCity120(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#queryProvince120(java.lang.String)
	 */
	@Override
	public List<TblCusBasicExpand> queryProvince120(String provinceId) {
		Map<String, String> paramMap = new HashMap<String, String>(2);
		paramMap.put("cusType", CommonEnums.CustomType.A120.id);
		paramMap.put("useProvince", provinceId);
		return cusBasicInfoDao.queryProvince120(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ITblCusBasicInfoService#queryById(java.lang.String)
	 */
	@Override
	public CusBasicInfo queryById(String id) {
		return cusBasicInfoDao.queryById(NumberUtils.toLong(id, 0));
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ITblCusBasicInfoService#update(com.medical.manager.customer.web.model.TblCusBasicInfo)
	 */
	@Override
	public void update(CusBasicInfo basicInfo) {
		 cusBasicInfoDao.update(basicInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#updateBatch(java.util.List)
	 */
	@Override
	public void updateBatch(List<CusBasicInfo> basicInfos) {
		if (basicInfos != null && basicInfos.size() > 0) {
			for (CusBasicInfo cusBasicInfo : basicInfos) {
				cusBasicInfoDao.update(cusBasicInfo);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#insert(com.medical.manager.customer.web.model.CusBasicInfo)
	 */
	@Override
	public void insert(CusBasicInfo basicInfo) {
		cusBasicInfoDao.insert(basicInfo);
		basicInfo.setInvitationCode(SerialNumberUtil.toSerialNumber(basicInfo.getId(), 6));
		update(basicInfo);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#queryList(com.medical.manager.customer.web.model.CusBasicInfo, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CusBasicInfo> queryBasicInfoList(CusBasicInfo info, Integer page, Integer rows) {
		Map<String, String> paramMap = new HashMap<String, String>(5);
		if(info != null) {
			if (!StringUtil.isEmpty(info.getPhone())) paramMap.put("phone", info.getPhone()); 
			if (!StringUtil.isEmpty(info.getRealName())) paramMap.put("realName", info.getRealName()); 
			if (!StringUtil.isEmpty(info.getProvince())) paramMap.put("province", info.getProvince()); 
			if (!StringUtil.isEmpty(info.getCity())) paramMap.put("city", info.getCity()); 
			if (!StringUtil.isEmpty(info.getCusType())) paramMap.put("cusType", info.getCusType()); 
			if (!StringUtil.isEmpty(info.getActivationFlag())) paramMap.put("activationFlag", info.getActivationFlag()); 
			if (!StringUtil.isEmpty(info.getLockFlag())) paramMap.put("lockFlag", info.getLockFlag()); 
		}
		return baseDao.queryWithPage(paramMap, page, rows, QUERY_BASIC_INFO_LIST);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#queryBasicInfoList(com.medical.manager.customer.web.dto.CusBasicInfoDto, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CusBasicInfo> queryBasicInfoList(CusBasicInfoDto infoDto, Integer page, Integer rows) {
		Map<String, String> paramMap = setParam(infoDto);
		return baseDao.queryWithPage(paramMap, page, rows, QUERY_BASIC_INFO_LIST);
	}
	
	private Map<String, String> setParam(CusBasicInfoDto infoDto) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if(infoDto != null) {
			if (!StringUtil.isEmpty(infoDto.getPhone())) paramMap.put("phone", infoDto.getPhone()); 
			if (!StringUtil.isEmpty(infoDto.getRealName())) paramMap.put("realName", infoDto.getRealName()); 
			if (!StringUtil.isEmpty(infoDto.getProvince())) paramMap.put("province", infoDto.getProvince()); 
			if (!StringUtil.isEmpty(infoDto.getCity())) paramMap.put("city", infoDto.getCity()); 
			if (!StringUtil.isEmpty(infoDto.getCusType())) paramMap.put("cusType", infoDto.getCusType()); 
			if (!StringUtil.isEmpty(infoDto.getActivationFlag())) paramMap.put("activationFlag", infoDto.getActivationFlag()); 
			if (!StringUtil.isEmpty(infoDto.getLockFlag())) paramMap.put("lockFlag", infoDto.getLockFlag());
			if (!StringUtil.isEmpty(infoDto.getRegisterTimeStart())) paramMap.put("registerTimeStart", infoDto.getRegisterTimeStart()); 
			if (!StringUtil.isEmpty(infoDto.getRegisterTimeEnd())) paramMap.put("registerTimeEnd", infoDto.getRegisterTimeEnd());
			if (!StringUtil.isEmpty(infoDto.getNickname())) paramMap.put("nickname", infoDto.getNickname());
			if (infoDto.getCreditScore() != null) paramMap.put("creditScore", infoDto.getCreditScore().toString());
			if (infoDto.getCreditScoreStart() != null) paramMap.put("creditScoreStart", infoDto.getCreditScoreStart().toString());
			if (infoDto.getCreditScoreEnd() != null) paramMap.put("creditScoreEnd", infoDto.getCreditScoreEnd().toString());
		}
		return paramMap;
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#updateBatchSub(java.util.List)
	 */
	@Override
	public void updateBatchSubScore(List<CusBasicInfo> basicInfos) {
		if (basicInfos != null && basicInfos.size() > 0) {
			for (CusBasicInfo info : basicInfos) {
				CusBasicInfo temp = cusBasicInfoDao.queryById(info.getId());
				if (temp == null) continue;
				long score = temp.getCreditScore() - info.getCreditScore();
				score = score > 0 ? score : 0;
				info.setCreditScore(score);
				cusBasicInfoDao.updateCreditScore(info);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#updateBatchAddScore(java.util.List)
	 */
	@Override
	public void updateBatchAddScore(List<CusBasicInfo> basicInfos) {
		if (basicInfos != null && basicInfos.size() > 0) {
			for (CusBasicInfo info : basicInfos) {
				cusBasicInfoDao.updateAddScore(info);
			}
		}		
	}

    @Override
    public Integer queryAmbulanceAuthentication(String type) {
        
        return this.cusBasicInfoDao.queryAmbulanceAuthentication(type);
    }

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#queryBasicInfoList4Export(com.medical.manager.customer.web.dto.CusBasicInfoDto, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CusBasicInfo> queryBasicInfoList4Export(CusBasicInfoDto infoDto, Integer page, Integer rows) {
		Map<String, String> paramMap = setParam(infoDto);
		return baseDao.queryWithPage(paramMap, page, rows, QUERY_BASIC_INFO_LIST_EXPORT);
	}

    @Override
    public CusBasicInfo queryByMobileNo(String mobileNo) {
        return cusBasicInfoDao.queryByMobileNo(mobileNo);
    }

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#queryPersonCenterDisplay(java.lang.Long)
	 */
	@Override
	public Map queryRelativesIndex(Long cusId) {
		return cusBasicInfoDao.queryRelativesIndex(cusId);
	}

    @Override
    public List<CusBasicInfo> query120DispatchCenter(Long cusId) {
        return this.cusBasicInfoDao.query120DispatchCenter(cusId);
    }

	/* (non-Javadoc)
	 * @see com.medical.manager.customer.web.service.ICusBasicInfoService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		cusBasicInfoDao.delete(id);
		cusBasicExpandDao.delete(id);
	}
}
