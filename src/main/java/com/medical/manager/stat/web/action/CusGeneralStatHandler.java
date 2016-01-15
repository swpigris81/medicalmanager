/**
 * 系统项目名称
 * com.medical.manager.stat.web.action
 * TblCusLoginStatHandler.java
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.action.CusBasicInfoHandler;
import com.medical.manager.stat.web.dto.CusStatDto;
import com.medical.manager.stat.web.model.CusGeneralStat;
import com.medical.manager.stat.web.service.ICusStatService;

/**
 * @description 综合统计
 * 
 * @time 2015年12月7日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/stat/general")
public class CusGeneralStatHandler extends BaseHandler {
	private Logger logger = Logger.getLogger(CusBasicInfoHandler.class);

	@Resource
	private ICusStatService cusGeneralStatService;

	@RequestMapping("index.do")
	public String index(HttpSession session) {
		logger.info("CusLoginStatHandler.index()");
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
		return "stat/general/index";
	}
	
	@RequestMapping(value = "/queryGeneralStatList.do", method = RequestMethod.POST)
	public void queryGeneralStatList(HttpServletResponse response, Integer page, Integer rows,
			@ModelAttribute CusStatDto statDto ){	
		logger.info("综合统计...");
		try {
			List<CusGeneralStat> statDtos = cusGeneralStatService.queryGeneralStatList(setQueryParamter(statDto), page, rows);
			writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<CusGeneralStat>) statDtos)));
		} catch (Exception e) {
			logger.error("查询失败", e);
			writeFailMsg(response, "查询失败");
		}
	}

	private CusStatDto setQueryParamter(CusStatDto statDto) {
		Date now = new Date();
		CusStatDto dto = new CusStatDto();
		String statTimeOption = statDto.getStatTimeOption();
		if (CusStatDto.statTimeOptionEnum.MONTH.id.equals(statTimeOption)) { // 当月
			dto.setStatTimeStart(DateUtil.getFirstSecondOfMonth(now));
			dto.setStatTimeEnd(DateUtil.getLastSecondOfMonth(now));
		} else if (CusStatDto.statTimeOptionEnum.YEAR.id.equals(statTimeOption)) { // 当年
			dto.setStatTimeStart(DateUtil.getFirstSecondOfYear(now));
			dto.setStatTimeEnd(DateUtil.getLastSecondOfYear(now));
		} else if (CusStatDto.statTimeOptionEnum.CUSTOM.id.equals(statTimeOption)){
			dto.setStatTimeStart(statDto.getStatTimeStart());
			dto.setStatTimeEnd(statDto.getStatTimeEnd());
		} else { // 默认当日
			dto.setStatTimeStart(DateUtil.getFirstSecondOfDay(now));
			dto.setStatTimeEnd(DateUtil.getLastSecondOfDay(now));
		}
		return dto;
	}
	
	@RequestMapping("/exportGeneralStat.do")
	public void exportGeneralStat(HttpServletResponse response,
			@ModelAttribute CusStatDto statDto) {
        logger.info("导出数据。");
        
		List<CusGeneralStat> stats = cusGeneralStatService.queryGeneralStatList(setQueryParamter(statDto), 1, 999999);
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("generalStatItem", "统计项");
        headMap.put("generalStatValue", "统计值");
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=generalStatExport.xlsx");
        export(response, headMap, stats);
    }
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(DateUtil.getFirstSecondOfYear(date));
		System.out.println(DateUtil.getLastSecondOfYear(date));
		System.out.println(DateUtil.getFirstSecondOfMonth(date));
		System.out.println(DateUtil.getLastSecondOfMonth(date));
		System.out.println(DateUtil.getFirstSecondOfDay(date));
		System.out.println(DateUtil.getLastSecondOfDay(date));
	}
}