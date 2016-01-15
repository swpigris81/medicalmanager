/**
 * 系统项目名称
 * com.medical.manager.stat.web.action
 * StatAreaAlarmHandler.java
 * 
 * 2015年12月7日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.stat.web.action;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.action.CusBasicInfoHandler;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.stat.web.dto.CusStatDto;
import com.medical.manager.stat.web.model.CusStatAreaAlarm;
import com.medical.manager.stat.web.service.ICusStatService;
import com.medical.manager.system.web.model.TblUser;

/**
 * @description 综合统计
 * 
 * @time 2015年12月7日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/stat/alarm")
public class CusStatAreaAlarmHandler extends BaseHandler {
	private Logger logger = Logger.getLogger(CusBasicInfoHandler.class);

	@Resource
	private ICusStatService cusStatService;
	@Resource
	private ICusBasicInfoService cusBasicInfoService;

	@RequestMapping("index.do")
	public String index(HttpSession session, Model model) {
		logger.info("StatAreaAlarmHandler.index()");
		
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
		
		try {
			TblUser user = getSessionUser(session);
			List<TblCusBasicExpand> alarmCenterList = null;
			if (!StringUtil.isEmpty(user.getUserCity())) {
				alarmCenterList = cusBasicInfoService.queryCity120(user.getUserCity());
			} else {
				alarmCenterList = cusBasicInfoService.queryProvince120(user.getUserProvince());
			}
			model.addAttribute("alarmCenterList", alarmCenterList);
		} catch (Exception e) {
			logger.error("StatAreaAlarmHandler.index()查询报警中心异常", e);
		}
		
		return "stat/alarm/index";
	}
	
	@RequestMapping(value = "/queryStatAreaAlarmList.do", method = RequestMethod.POST)
	public void queryStatAreaAlarmList(HttpSession session,
			HttpServletResponse response, Integer page, Integer rows,
			@ModelAttribute CusStatDto dto) {
		logger.info("综合统计...");
		try {
			setQueryParamter(session, dto);
			List<CusStatAreaAlarm> alarms = cusStatService.queryStatAreaAlarmList(dto, page, rows);
			List statsValue = cusStatService.queryStatAreaAlarmStat(dto);
			PagerInfo pInfo = PagerInfo.converFromPage((Page<CusStatAreaAlarm>) alarms);
			pInfo.setFooter(statsValue);
			
			writeMsg(response, JSON.toJSONString(pInfo));
		} catch (Exception e) {
			logger.error("查询失败", e);
			writeFailMsg(response, "查询失败");
		}
	}

	@RequestMapping("/exportStatAreaAlarm.do")
	public void exportStatAreaAlarm(HttpSession session,
			HttpServletResponse response, @ModelAttribute CusStatDto statDto) {
        logger.info("导出数据。");
        setQueryParamter(session, statDto);
		List<CusStatAreaAlarm> statsValue = cusStatService.queryStatAreaAlarmStat(statDto);
		List<CusStatAreaAlarm> stats = cusStatService.queryStatAreaAlarmList(statDto, 1, 999999);
		statsValue.addAll(stats);
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("alarmCenter", "报警中心");
        headMap.put("alarmTime", "报警时间");
        headMap.put("scheduleUser", "调度员");
        headMap.put("phone", "电话号码");
        headMap.put("callDuration", "通话时长");
        headMap.put("scheduleVehicle", "调度车辆");
        headMap.put("diseaseType", "病情类型");
        headMap.put("hospital", "收治医院");
        headMap.put("trackInfo", "跟踪情况");
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=areaAlarmExport.xlsx");
        export(response, headMap, statsValue);
    }
	
	private void setQueryParamter(HttpSession session, CusStatDto statDto) {
		Date now = new Date();
		String statTimeOption = statDto.getStatTimeOption();
		if (CusStatDto.statTimeOptionEnum.MONTH.id.equals(statTimeOption)) { // 当月
			statDto.setStatTimeStart(DateUtil.getFirstSecondOfMonth(now));
			statDto.setStatTimeEnd(DateUtil.getLastSecondOfMonth(now));
		} else if (CusStatDto.statTimeOptionEnum.YEAR.id.equals(statTimeOption)) { // 当年
			statDto.setStatTimeStart(DateUtil.getFirstSecondOfYear(now));
			statDto.setStatTimeEnd(DateUtil.getLastSecondOfYear(now));
		} else if (CusStatDto.statTimeOptionEnum.CUSTOM.id.equals(statTimeOption)){
			statDto.setStatTimeStart(statDto.getStatTimeStart());
			statDto.setStatTimeEnd(statDto.getStatTimeEnd());
		} else { // 默认当日
			statDto.setStatTimeStart(DateUtil.getFirstSecondOfDay(now));
			statDto.setStatTimeEnd(DateUtil.getLastSecondOfDay(now));
		}
		
		TblUser user = getSessionUser(session);
		if (!StringUtil.isEmpty(user.getUserCity())) {
			statDto.setAlarmArea(user.getUserCity());
			statDto.setAlarmAreaType(CommonEnums.CityProvince.CITY.id);
		} else {
			statDto.setAlarmArea(user.getUserProvince());
			statDto.setAlarmAreaType(CommonEnums.CityProvince.PROVINCE.id);
		}
	}
}