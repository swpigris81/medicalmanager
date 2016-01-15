
/**
 * 系统项目名称
 * com.medical.manager.funds.web.action
 * RechargeHandler.java
 * 
 * 2015年12月7日-下午1:34:39
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
import com.medical.manager.common.util.PoiUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.funds.web.model.TblRechargeTurnover;
import com.medical.manager.funds.web.service.ITblRechargeTurnoverService;


/**
 * 
 * RechargeHandler 充值
 * 
 * 2015年12月7日 下午1:34:39
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/recharge")
public class RechargeHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(RechargeHandler.class);
    @Resource
    private ITblRechargeTurnoverService rechargeTurnoverService;
    /**
     * index 首页
     * (这里描述这个方法适用条件 – 可选)
     * @return String 首页
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/index.do")
    public String index(HttpSession session, Model model){
        logger.info("用户充值首页");
        String timeOut = logginTimeOut(session);
        if(timeOut != null){
            return timeOut;
        }
        return "funds/recharge/index";
    }
    
    /**
     * fundsList 查询用户提现明细
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param searchVo 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/rechargeList.do")
    public void rechargeList(HttpServletResponse response, TblRechargeTurnover searchVo,
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
        List<TblRechargeTurnover> rechargeTurnoverList = rechargeTurnoverService.queryWithPaging(page, rows, paramMap);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblRechargeTurnover>) rechargeTurnoverList)));
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
    @RequestMapping("/rechargeExport.do")
    public void rechargeExport(HttpServletResponse response, TblRechargeTurnover searchVo,
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
        List<TblRechargeTurnover> rechargeTurnoverList = rechargeTurnoverService.queryWithPagingExport(1, 999999, paramMap);
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("rechargeUnit", "单位");
        headMap.put("rechargeType", "充值类型");
        headMap.put("rechargeAmount", "充值金额");
        headMap.put("receiptBankAccount", "收款账户");
        headMap.put("rechargeTime", "充值时间");
        headMap.put("rechargeStatus", "状态");
        headMap.put("rechargeMode", "充值方式");
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=export.xlsx");
        export(response, headMap, rechargeTurnoverList);
    }
}
