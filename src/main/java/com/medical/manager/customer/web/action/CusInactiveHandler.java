
/**
 * 系统项目名称
 * com.medical.manager.customer.web.action
 * CusInactiveHandler.java
 * 
 * 2015年12月4日-下午9:23:45
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.action;

import java.util.Date;
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
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.dto.CusBasicInfoDto;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;

/**
 * @description 未激活用户管理
 * 
 * @time 2015年12月4日 下午9:23:45
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/customer/inactive")
public class CusInactiveHandler extends BaseHandler {
	private Logger logger = Logger.getLogger(CusBasicInfoHandler.class);
	
	@Resource
	private ICusBasicInfoService cusBasicInfoService;
	
	@RequestMapping("index.do")
	public String inactive(HttpSession session) {
		logger.info("CusInactiveHandler.inactive()");
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
		return "customer/inactive/index";
	}
	
	@RequestMapping(value = "/queryInactiveList.do", method = RequestMethod.POST)
	public void queryInactiveList(HttpServletResponse response, Integer page, Integer rows,
			@ModelAttribute CusBasicInfoDto basicInfoDto) {
		logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
		try {
			basicInfoDto.setActivationFlag(CommonEnums.YesOrNo.NO.id);
			List<CusBasicInfo> basicInfos = cusBasicInfoService.queryBasicInfoList(basicInfoDto, page, rows);
			writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<CusBasicInfo>) basicInfos)));
		} catch (Exception e) {
			logger.error("查询失败", e);
			writeFailMsg(response, "查询失败");
		}
	}
	
    @RequestMapping(value = "/saveOrUpdate.do")
	public void saveOrUpdate(HttpSession session, HttpServletResponse response, CusBasicInfo basicInfo) {
		if (basicInfo != null) {
			try {
				if (basicInfo.getId() == null) {
					logger.info("请选择需要激活的用户！");
					writeFailMsg(response, "请选择需要激活的用户!");
				} else {
					logger.info("更新客户信息....激活用户");
					String now = DateUtil.fmtDateToYMDHMS(new Date());
					String userName = getSessionUser(session).getUserName();
					basicInfo.setActivationFlag(CommonEnums.YesOrNo.YES.id);
					basicInfo.setActivationUser(userName);
					basicInfo.setActivationTime(now);
					basicInfo.setUpdateUser(userName);
					basicInfo.setUpdateTime(now);
					cusBasicInfoService.update(basicInfo);
					writeSuccessMsg(response, "操作成功！");
				}
			} catch (Exception e) {
				logger.error("激活用户失败!", e);
				writeFailMsg(response, "操作失败，请重试！");
			}
		}
	}
}
