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

import java.util.List;

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
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.action.CusBasicInfoHandler;
import com.medical.manager.stat.web.dto.CusLoginStatDto;
import com.medical.manager.stat.web.service.ICusLoginStatService;

/**
 * @description 用户登陆统计
 * 
 * @time 2015年12月7日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/stat/login")
public class CusLoginStatHandler extends BaseHandler {
	private Logger logger = Logger.getLogger(CusBasicInfoHandler.class);

	@Resource
	private ICusLoginStatService tblCusLoginSeqService;

	@RequestMapping("index.do")
	public String index(HttpSession session) {
		logger.info("CusLoginStatHandler.index()");
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
		return "stat/login/index";
	}
	
	@RequestMapping(value = "/queryStatLoginList.do", method = RequestMethod.POST)
	public void queryStatLoginList(HttpServletResponse response, Integer page, Integer rows,
			@ModelAttribute CusLoginStatDto loginStatDto) {
		logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
		try {
			List<CusLoginStatDto> statDtos = tblCusLoginSeqService.queryStatLoginList(loginStatDto, page, rows);
			writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<CusLoginStatDto>) statDtos)));
		} catch (Exception e) {
			logger.error("查询失败", e);
			writeFailMsg(response, "查询失败");
		}
	}
}