
/**
 * 系统项目名称
 * com.medical.manager.customer.web.action
 * TblCusBasicInfoHandler.java
 * 
 * 2015年12月3日-下午10:17:55
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.action;

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
import com.medical.manager.customer.web.dto.TblCusReportDto;
import com.medical.manager.customer.web.model.TblCusReport;
import com.medical.manager.customer.web.service.ITblCusReportService;

/**
 * @description 用户举报管理
 * 
 * @time 2015年12月3日 下午10:17:55
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/customer/report")
public class CusReportHandler extends BaseHandler {

	private Logger logger = Logger.getLogger(CusReportHandler.class);

	@Resource
	private ITblCusReportService tblCusReportService;

	@RequestMapping("index.do")
	public String report(HttpSession session) {
		logger.info("CusReportHandler.report()");
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
		return "customer/report/index";
	}

	@RequestMapping(value = "/queryReportList.do", method = RequestMethod.POST)
	public void queryReportList(HttpServletResponse response, Integer page,
			Integer rows, @ModelAttribute TblCusReportDto reportDto) {
		logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
		try {
			List<TblCusReport> reports = tblCusReportService
					.queryTblCusReportList(reportDto, page, rows);
			writeMsg(response, JSON.toJSONString(PagerInfo
					.converFromPage((Page<TblCusReport>) reports)));
		} catch (Exception e) {
			logger.error("查询失败", e);
			writeFailMsg(response, "查询失败");
		}
	}
}
