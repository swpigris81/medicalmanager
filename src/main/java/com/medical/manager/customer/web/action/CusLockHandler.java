
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

import java.util.ArrayList;
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
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.dto.CusBasicInfoDto;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;

/**
 * @description 锁定用户管理
 * 
 * @time 2015年12月3日 下午10:17:55
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/customer/lock")
public class CusLockHandler extends BaseHandler {

	private Logger logger = Logger.getLogger(CusLockHandler.class);

	@Resource
	private ICusBasicInfoService cusBasicInfoService;

	@RequestMapping("index.do")
	public String lock(HttpSession session) {
		logger.info("CusLockHandler.lock()");
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
		return "customer/lock/index";
	}
	
	@RequestMapping(value = "/queryLockList.do", method = RequestMethod.POST)
	public void queryLockList(HttpServletResponse response, Integer page, Integer rows,
			@ModelAttribute CusBasicInfoDto basicInfoDto) {
		logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
		try {
			List<CusBasicInfo> basicInfos = cusBasicInfoService.queryBasicInfoList(basicInfoDto, page, rows);
			writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<CusBasicInfo>) basicInfos)));
		} catch (Exception e) {
			logger.error("查询失败", e);
			writeFailMsg(response, "查询失败");
		}
	}
	
    @RequestMapping(value = "/saveOrUpdate.do")
	public void saveOrUpdate(HttpSession session, HttpServletResponse response, CusBasicInfo basicInfo) {
    	// 用来保存锁定天数
    	Long lockDays = basicInfo.getCreditScore();
    	// 用来保存选中记录的id，逗号分隔
    	String ids = basicInfo.getRemark();
    	if (lockDays == null || lockDays <= 0l) {
    		writeSuccessMsg(response, "锁定天数需是大于0的数字！");
    		return;
    	}
    	if (StringUtil.isEmpty(ids)) {
    		writeSuccessMsg(response, "请选择需要锁定的用户！");
    		return;
    	}
    	String[] idArr = ids.split(",");
    	List<CusBasicInfo> basicInfos = new ArrayList<CusBasicInfo>(idArr.length);
    	Date now = new Date();
    	String nowStr = DateUtil.fmtDateToYMDHMS(new Date());
    	String lockExpireTime = DateUtil.fmtDateToYMDHMS(DateUtil.toShortDate(DateUtil.addDay(now, lockDays.intValue())));
    	
    	String userName = getSessionUser(session).getUserName();
    	for (int i = 0; i < idArr.length; i++) {
			if (!StringUtil.isEmpty(idArr[i])) {
				CusBasicInfo info = new CusBasicInfo();
				info.setId(Long.parseLong(idArr[i]));
				info.setLockFlag(CommonEnums.YesOrNo.YES.id);
				info.setLockUser(userName);
				info.setLockTime(nowStr);
				info.setLockExpireTime(lockExpireTime);
				info.setUpdateUser(userName);
				info.setUpdateTime(nowStr);
				basicInfos.add(info);
			}
		}
    	
		if (basicInfos.size() > 0) {
			try {
				cusBasicInfoService.updateBatch(basicInfos);
				writeSuccessMsg(response, "操作成功！");
			} catch (Exception e) {
				logger.error("锁定用户失败!", e);
				writeFailMsg(response, "操作失败，请重试！");
			}
		}
	}
}
