
/**
 * 系统项目名称
 * com.medical.manager.customer.web.action
 * CusScoreHandler.java
 * 
 * 2015年12月4日-下午9:21:42
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.action;

import java.util.ArrayList;
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
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.dto.CusBasicInfoDto;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;

/**
 * @description 用户积分管理
 * 
 * @time 2015年12月4日 下午9:21:42
 * @author Jason
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/customer/score")
public class CusScoreHandler extends BaseHandler {
	private Logger logger = Logger.getLogger(CusBasicInfoHandler.class);

	@Resource
	private ICusBasicInfoService cusBasicInfoService;

	@RequestMapping("index.do")
	public String score(HttpSession session) {
		logger.info("CusScoreHandler.score()");
		String timeout = logginTimeOut(session);
		if (timeout != null) {
			return timeout;
		}
		return "customer/score/index";
	}
	
	@RequestMapping(value = "/queryScoreList.do", method = RequestMethod.POST)
	public void queryScoreList(HttpServletResponse response, Integer page, Integer rows,
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
	public void saveOrUpdate(HttpSession session, HttpServletResponse response, CusBasicInfoDto basicInfoDto) {
    	// 用来保存选中记录的id，逗号分隔
    	String ids = basicInfoDto.getIds();
    	if (basicInfoDto.getCreditScore() == null || basicInfoDto.getCreditScore() <= 0) {
    		writeSuccessMsg(response, "信用积分必须是大于0的数字！");
    		return;
    	}
    	if (StringUtil.isEmpty(ids)) {
    		writeSuccessMsg(response, "请选择需要扣减信用积分的用户！");
    		return;
    	}
    	String[] idArr = ids.split(",");
    	String userName = getSessionUser(session).getUserName();
    	List<CusBasicInfo> basicInfos = new ArrayList<CusBasicInfo>(idArr.length);
    	for (int i = 0; i < idArr.length; i++) {
			if (!StringUtil.isEmpty(idArr[i])) {
				CusBasicInfo info = new CusBasicInfo();
				info.setId(Long.parseLong(idArr[i]));
				info.setUpdateUser(userName);
				info.setUpdateTime(DateUtil.fmtDateToYMDHMS(new Date()));
				info.setCreditScore(basicInfoDto.getCreditScore());
				basicInfos.add(info);
			}
		}
    	
    	boolean successFlag = true;
		if (basicInfos.size() > 0) {
			try {
				if (CusBasicInfoDto.updateTypeEnum.SUB.id.equals(basicInfoDto.getUpdateType())) {
					cusBasicInfoService.updateBatchSubScore(basicInfos); // 减少积分
				} else if (CusBasicInfoDto.updateTypeEnum.ADD.id.equals(basicInfoDto.getUpdateType())) {
					cusBasicInfoService.updateBatchAddScore(basicInfos); // 增加积分
				} else {
					successFlag = false;
				}
			} catch (Exception e) {
				logger.error("锁定用户失败!", e);
				successFlag = false;
			}
		}
		if (successFlag) writeSuccessMsg(response, "操作成功！");
		else writeFailMsg(response, "操作失败，请重试！");
	}
    
	@RequestMapping("/creditScoreExport.do")
	public void creditScoreExport(HttpServletResponse response, @ModelAttribute CusBasicInfoDto basicInfoDto) {
        logger.info("导出数据。");
        
		List<CusBasicInfo> basicInfos = cusBasicInfoService.queryBasicInfoList4Export(basicInfoDto, 1, 999999);
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("nickname", "用户名");
        headMap.put("realName", "真实姓名");
        headMap.put("cusType", "类型");
        headMap.put("creditScore", "信用积分");
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=creditScoreExport.xlsx");
        export(response, headMap, basicInfos);
    }
}
