
/**
 * 系统项目名称
 * com.medical.manager.funds.web.action
 * FundsHandler.java
 * 
 * 2015年12月7日-上午11:18:56
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.funds.web.model.TblRechargeTurnover;
import com.medical.manager.funds.web.service.ITblRechargeTurnoverService;


/**
 * 
 * FundsHandler 用户资金管理
 * 
 * 2015年12月7日 上午11:18:56
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/funds")
public class FundsHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(FundsHandler.class);
    @Resource
    private ITblRechargeTurnoverService rechargeTurnoverService;
    /**
     * index 用户资金管理首页
     * (这里描述这个方法适用条件 – 可选)
     * @return String 首页
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/index.do")
    public String index(HttpSession session, Model model){
        logger.info("用户资金管理首页");
        String timeOut = logginTimeOut(session);
        if(timeOut != null){
            return timeOut;
        }
        return "funds/index";
    }
    
    /**
     * fundsList 查询用户资金明细
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/fundsList.do")
    public void fundsList(HttpServletResponse response, TblRechargeTurnover searchVo,
            Integer page, Integer rows){
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        Map paramMap = new HashMap();
        if(searchVo != null){
            try {
                paramMap = BeanUtils.describe(searchVo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        List<TblRechargeTurnover> rechargeTurnoverList = rechargeTurnoverService.queryWithPaging(page, rows, paramMap);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblRechargeTurnover>) rechargeTurnoverList)));
    }
}
