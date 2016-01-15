
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

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.system.web.model.TblSysProvince;
import com.medical.manager.system.web.service.ISysAdministrativeService;

/**
 * @description 用户基本信息管理
 * 
 * @time 2015年12月3日 下午10:17:55
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/customer/basic")
public class CusBasicInfoHandler extends BaseHandler {

	private Logger logger = Logger.getLogger(CusBasicInfoHandler.class);

	@Resource
	private ICusBasicInfoService cusBasicInfoService;
    @Resource
    private ISysAdministrativeService sysAdministrativeService;
    
	@RequestMapping("index.do")
	public String basic(HttpSession session, Model model) {
		logger.info("客户基本信息查询跳转：CusBasicInfoHandler.basic()");
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
        List<TblSysProvince> provinceList = sysAdministrativeService.findAllProvince();
        model.addAttribute("provinceList", provinceList);
		
		return "customer/basic/index";
	}
	
	@RequestMapping(value = "/queryBasicInfoList.do", method = RequestMethod.POST)
	@ResponseBody
	public void queryBasicInfoList(HttpServletResponse response, Integer page, Integer rows,
			@ModelAttribute CusBasicInfo basicInfo) {
		logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
		try {
			List<CusBasicInfo> basicInfos = cusBasicInfoService.queryBasicInfoList(basicInfo, page, rows);
			writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<CusBasicInfo>) basicInfos)));
		} catch (Exception e) {
			logger.error("查询失败", e);
			writeFailMsg(response, "查询失败");
		}
	}
	
    @RequestMapping(value = "/saveOrUpdate.do")
    @ResponseBody
	public void saveOrUpdate(HttpSession session, HttpServletResponse response, CusBasicInfo basicInfo) {
		if (basicInfo != null) {
			try {
				if (basicInfo.getId() == null) {
					logger.info("新增客户信息.....");
					basicInfo.setRegisterTime(DateUtil.fmtDateToYMDHMS(new Date()));
					basicInfo.setActivationFlag(CommonEnums.YesOrNo.NO.id);
					cusBasicInfoService.insert(basicInfo);
					writeSuccessMsg(response, "新增成功！");
				} else {
					logger.info("更新客户信息....");
					basicInfo.setUpdateTime(DateUtil.fmtDateToYMDHMS(new Date()));
					basicInfo.setUpdateUser(getSessionUser(session).getUserName());
					cusBasicInfoService.update(basicInfo);
					writeSuccessMsg(response, "更新成功！");
				}
			} catch (Exception e) {
				logger.error("操作失败！", e);
				writeFailMsg(response, "操作失败，请重试！");
			}
		}
	}
}
