/**
 * 系统项目名称
 * com.medical.manager.stat.web.service.impl
 * CusGeneralStatServiceImpl.java
 * 
 * 2015年12月8日-上午9:53:47
 * 2015版权所有
 * 
 */
package com.medical.manager.stat.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.authentication.web.dao.ITblCusBasicExpandDao;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.stat.web.dao.ICusStatDao;
import com.medical.manager.stat.web.dto.CusStatDto;
import com.medical.manager.stat.web.model.CusGeneralStat;
import com.medical.manager.stat.web.model.CusStatAreaAlarm;
import com.medical.manager.stat.web.service.ICusStatService;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月8日 上午9:53:47
 * @author Jason
 * @version 1.0.0
 */
@Transactional
@Service("cusStatService")
public class CusStatServiceImpl implements ICusStatService {

	@Resource
	private IBaseDao baseDao;
	@Resource
	private ICusStatDao cusStatDao;
	@Resource
	private ITblCusBasicExpandDao cusBasicExpandDao;

	private static final String EXPORT_GENERAL_STAT = "com.medical.manager.stat.web.dao.ICusStatDao.exportGeneralStat";
	private static final String QUERY_STAT_AREA_ALARM_LIST = "com.medical.manager.stat.web.dao.ICusStatDao.queryStatAreaAlarmList";
	private static final String EXPORT_STAT_AREA_ALARM = "com.medical.manager.stat.web.dao.ICusStatDao.exportStatAreaAlarm";
	private static final String QUERY_FROM_TEMP_TABLE = "com.medical.manager.stat.web.dao.ICusStatDao.queryGeneralStatListFromTempTable";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.medical.manager.stat.web.service.ICusGeneralStatService#
	 * queryGeneralStatList(com.medical.manager.stat.web.dto.CusGeneralStatDto,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CusGeneralStat> queryGeneralStatList(CusStatDto statDto,
			Integer page, Integer rows) {
		
		String[] CustomTypeIdArr = CommonEnums.CustomType.getIds(); 	// 注册客户类型统计
		String[] RechargeTypeArr = CommonEnums.RechargeType.getIds(); 	// 充值类型统计
		String[] serviceOpenArr = {"CustomType0401", "CustomType0301"}; // 开通统计：120中心开通统计、急救车开通统计
		String WithdrawType00 = "WithdrawType00";	// 提现统计
		String HardwareOpen00 = "HardwareOpen00";	// 智能硬件开通统计
		String Recommend00 = "Recommend00"; 		// 推荐费统计
		String Sales00 = "Sales00"; 				// 销售统计
		
		// 查询所有统计
		Map<String, String> paramMap = setParam(statDto);
		
		List<CusGeneralStat> stats = cusStatDao.queryGeneralStatList(paramMap);
		// 将查询结果转换为Map方便遍历
		Map<String, CusGeneralStat> statMap = new HashMap<String, CusGeneralStat>(stats.size());
		for (CusGeneralStat stat : stats) {
			statMap.put(stat.getGeneralStatItemKey(), stat);
		}
		
		List<CusGeneralStat> rets = new ArrayList<CusGeneralStat>();
		// 注册统计
		for (String ct : CustomTypeIdArr) {
			String key = "CustomType" + ct;
			if (statMap.get(key) == null) 
				rets.add(new CusGeneralStat(key, null, "0"));
			else 
				rets.add(statMap.get(key));
		}
		// 充值统计
		for (String rt : RechargeTypeArr) {
			String key = "RechargeType" + rt;
			if (statMap.get(key) == null) 
				rets.add(new CusGeneralStat(key, null, "0"));
			else 
				rets.add(statMap.get(key));
		}
		// 销售统计
		rets.add(statMap.get(Sales00));
		// 推荐费统计
		rets.add(statMap.get(Recommend00));
		// 提现统计
		rets.add(statMap.get(WithdrawType00));
		// 智能硬件开通统计
		rets.add(statMap.get(HardwareOpen00));
		// 开通统计
		for (String so : serviceOpenArr) {
			if (statMap.get(so) == null) 
				rets.add(new CusGeneralStat(so, null, "0"));
			else 
				rets.add(statMap.get(so));
		}
			
		for (CusGeneralStat stat : rets) {
			cusStatDao.updateTblStatGeneral(stat);
		}
		
		return baseDao.queryWithPage(null, page, rows, QUERY_FROM_TEMP_TABLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.medical.manager.stat.web.service.ICusGeneralStatService#exportGeneralStat
	 * (com.medical.manager.stat.web.dto.CusGeneralStatDto, java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public List<CusGeneralStat> exportGeneralStat(CusStatDto statDto,
			Integer page, Integer rows) {
		Map<String, String> paramMap = setParam(statDto);
		return baseDao.queryWithPage(paramMap, page, rows, EXPORT_GENERAL_STAT);
	}

	private Map<String, String> setParam(CusStatDto statDto) {
		Map<String, String> paramMap = new HashMap<String, String>(2);
		if (!StringUtil.isEmpty(statDto.getStatTimeEnd()))
			paramMap.put("statTimeEnd", statDto.getStatTimeEnd());
		if (!StringUtil.isEmpty(statDto.getStatTimeStart()))
			paramMap.put("statTimeStart", statDto.getStatTimeStart());
		paramMap.put("checkStatus", CommonEnums.CheckStatus.CHECK_PASS.id);
		paramMap.put("isPay", CommonEnums.YesOrNo.YES.id);
		
		// 辖区报警数据专有条件
		final String _alarmAreaKey = "alarmArea";
		final String _alarmAreaTypeKey = "alarmAreaType";
		paramMap.put(_alarmAreaKey, statDto.getAlarmArea());
		paramMap.put(_alarmAreaTypeKey, statDto.getAlarmAreaType());
		if (!StringUtil.isEmpty(statDto.getAlarmCenter())) {
			/* 报警中心不为空:
			 * 1、查询信息（报警中心保存了用户扩展信息的主键）
			 * 2、判断use_county、use_city、use_province，county为空取city，city为空取province
			 * 3、重置区域类型，county为0，city为1，province为2
			 * 4、查询的时候，根据区域类型设置相应项
			 */
			TblCusBasicExpand expand = cusBasicExpandDao.selectById(statDto.getAlarmCenter());
			if (expand != null) {
				if (!StringUtil.isEmpty(expand.getUseCounty())) {
					paramMap.put(_alarmAreaKey, expand.getUseCounty());
					paramMap.put(_alarmAreaTypeKey, CommonEnums.CityProvince.COUNTY.id);
				} else if (!StringUtil.isEmpty(expand.getUseCity())) {
					paramMap.put(_alarmAreaKey, expand.getUseCity());
					paramMap.put(_alarmAreaTypeKey, CommonEnums.CityProvince.CITY.id);
				} else {
					paramMap.put(_alarmAreaKey, expand.getUseProvince());
					paramMap.put(_alarmAreaTypeKey, CommonEnums.CityProvince.PROVINCE.id);
				}
			}
		}
		
		return paramMap;
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.stat.web.service.ICusStatService#queryStatAreaAlarmList(com.medical.manager.stat.web.dto.StatDto, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CusStatAreaAlarm> queryStatAreaAlarmList(CusStatDto statDto,
			Integer page, Integer rows) {
		Map<String, String> paramMap = setParam(statDto);
		return baseDao.queryWithPage(paramMap, page, rows, QUERY_STAT_AREA_ALARM_LIST);
	}
	
	public List queryStatAreaAlarmStat(CusStatDto statDto) {
		Map<String, String> paramMap = setParam(statDto);
		return cusStatDao.queryStatAreaAlarmStat(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.medical.manager.stat.web.service.ICusStatService#exportStatAreaAlarm(com.medical.manager.stat.web.dto.StatDto, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CusStatAreaAlarm> exportStatAreaAlarm(CusStatDto statDto,
			Integer page, Integer rows) {
		Map<String, String> paramMap = setParam(statDto);
		return baseDao.queryWithPage(paramMap, page, rows, EXPORT_STAT_AREA_ALARM);
	}
}
