
/**
 * 系统项目名称
 * com.medical.manager.funds.web.action
 * WithdrawHandler.java
 * 
 * 2015年12月7日-上午11:59:37
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.medical.manager.funds.web.model.TblWithdrawTurnover;
import com.medical.manager.funds.web.service.ITblWithdrawTurnoverService;


/**
 * 
 * WithdrawHandler 提现
 * 
 * 2015年12月7日 上午11:59:37
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(WithdrawHandler.class);
    @Resource
    private ITblWithdrawTurnoverService withdrawTurnoverService;
    /**
     * index 用户提现首页
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
        return "funds/withdraw/index";
    }
    
    /**
     * fundsList 查询用户提现明细
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param searchVo 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/withdrawList.do")
    public void withdrawList(HttpServletResponse response, TblWithdrawTurnover searchVo,
            String beginDate, String endDate,
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
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        List<TblWithdrawTurnover> rechargeTurnoverList = withdrawTurnoverService.queryWithPaging(page, rows, paramMap);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblWithdrawTurnover>) rechargeTurnoverList)));
    }
    /**
     * saveWithdraw 保存更新提现信息
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param withdrawTurnover 提现信息
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/saveWithdraw.do")
    public void saveWithdraw(HttpServletResponse response, TblWithdrawTurnover withdrawTurnover){
        logger.info("更新提现信息");
        Map<String, Object> resuleMap = new HashMap<String, Object>();
        boolean success = false;
        String resultMsg = "";
        try{
            if(withdrawTurnover == null || withdrawTurnover.getId() == null){
                throw new Exception("提现信息为空！");
            }
            withdrawTurnoverService.doUpdateWithdraw(withdrawTurnover);
            success = true;
            resultMsg = "提现信息保存成功！";
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            resultMsg = "提现信息保存失败！" + e.getMessage();
        }
        resuleMap.put("success", success);
        resuleMap.put("msg", resultMsg);
        writeMsg(response, resuleMap);
    }
    
    /**
     * rechargeExport 导出
     * (这里描述这个方法适用条件 – 可选)
     * @param response 响应
     * @param searchVo 查询条件
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/withdrawExport.do")
    public void withdrawExport(HttpServletResponse response, TblWithdrawTurnover searchVo,
            String beginDate, String endDate){
        logger.info("导出数据。");
        Map paramMap = new HashMap();
        if(searchVo != null){
            try {
                paramMap = BeanUtils.describe(searchVo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        List<TblWithdrawTurnover> rechargeTurnoverList = withdrawTurnoverService.queryWithPagingExport(1, 9999999, paramMap);
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("custMobile", "手机号");
        headMap.put("name", "姓名");
        headMap.put("withdrawAccount", "提现账号");
        headMap.put("withdrawBank", "提现银行");
        headMap.put("branchBank", "支行名称");
        headMap.put("withdrawAmount", "提现金额");
        headMap.put("withdrawTime", "提现时间");
        headMap.put("withdrawStatus", "提现状态");
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=withdrawExport.xlsx");
        export(response, headMap, rechargeTurnoverList);
    }
}
