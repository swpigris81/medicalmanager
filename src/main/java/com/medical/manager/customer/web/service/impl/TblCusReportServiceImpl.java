/**
 * 系统项目名称
 * com.medical.manager.customer.web.service.impl
 * TblCusReportServiceImpl.java
 * 
 * 2015年12月7日 上午11:56:19
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.customer.web.dao.ITblCusReportDao;
import com.medical.manager.customer.web.dto.TblCusReportDto;
import com.medical.manager.customer.web.model.TblCusReport;
import com.medical.manager.customer.web.service.ITblCusReportService;

/**
 * @description 客户举报管理实现类
 * 
 * @date 2015年12月7日 上午11:56:19
 * @author Jason
 * @version 1.0.0
 * 
 */

@Transactional
@Service("tblCusReportService")
public class TblCusReportServiceImpl implements ITblCusReportService {

	@Resource
	private IBaseDao<TblCusReport> baseDao;
	@Resource
	private ITblCusReportDao tblCusReportDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.customer.web.service.ITblCusReportService#queryById
	 * (java.lang.String)
	 */
	@Override
	public TblCusReport queryById(String id) {
		return tblCusReportDao.queryById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.customer.web.service.ITblCusReportService#update(
	 * com.medical.manager.customer.web.model.TblCusReport)
	 */
	@Override
	public void update(TblCusReport report) {
		tblCusReportDao.update(report);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.customer.web.service.ITblCusReportService#updateBatch
	 * (java.util.List)
	 */
	@Override
	public void updateBatch(List<TblCusReport> reports) {
		if (reports != null && reports.size() > 0) {
			for (TblCusReport report : reports) {
				tblCusReportDao.update(report);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.customer.web.service.ITblCusReportService#insert(
	 * com.medical.manager.customer.web.model.TblCusReport)
	 */
	@Override
	public void insert(TblCusReport report) {
		tblCusReportDao.insert(report);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medical.manager.customer.web.service.ITblCusReportService#
	 * queryBasicInfoList(com.medical.manager.customer.web.dto.TblCusReportDto,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<TblCusReport> queryTblCusReportList(TblCusReportDto reportDto,
			Integer page, Integer rows) {
		Map<String, String> paramMap = setParam(reportDto);
		return baseDao.queryWithPage(paramMap, page, rows, "queryTblCusReportList");
	}
	
	private Map<String, String> setParam(TblCusReportDto dto) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (dto != null) {
			if (!StringUtil.isEmpty(dto.getInformants()))
				paramMap.put("informants", dto.getInformants());
			if (!StringUtil.isEmpty(dto.getReported()))
				paramMap.put("reported", dto.getReported());
			if (!StringUtil.isEmpty(dto.getReportType()))
				paramMap.put("reportType", dto.getReportType());
			if (!StringUtil.isEmpty(dto.getDealStatus()))
				paramMap.put("dealStatus", dto.getDealStatus());
			if (dto.getId() != null)
				paramMap.put("id", dto.getId().toString());
		}
		return paramMap;
	}
}
