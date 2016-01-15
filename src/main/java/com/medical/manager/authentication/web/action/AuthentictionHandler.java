/**
 * 
 */
package com.medical.manager.authentication.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.authentication.web.model.TblCusPhoneChangeApply;
import com.medical.manager.authentication.web.service.ITblCusBasicExpandService;
import com.medical.manager.authentication.web.service.ITblCusPhoneChangeApplyService;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.system.web.model.TblSysProvince;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.service.ISysAdministrativeService;

/**
 * @author jason
 * 
 *         下午3:40:12
 * @version 1.0.0
 */
@Controller
@RequestMapping("/authentiction")
public class AuthentictionHandler extends BaseHandler {

    private static Logger logger = Logger.getLogger(AuthentictionHandler.class);

    @Resource
    private ITblCusPhoneChangeApplyService cusPhoneChangeApplyService;
    
    @Resource
    private ITblCusBasicExpandService cusBasicExpandService;
    
    @Resource
    private ISysAdministrativeService sysAdministrativeService;
    @Resource
    private ICusBasicInfoService cusBasicInfoService;

    /**
     * 跳转到手机变更申请主页
     * 
     * @param session
     * @return
     */
    @RequestMapping("/phoneChangeApplyIndex.do")
    public String phoneChangeApplyIndex(HttpSession session) {
        String timeout = logginTimeOut(session);
        if (timeout != null) {
            return timeout;
        }
        return "authentiction/phoneChangeApplyList";
    }

    /**
     * 查询手机变更申请
     * 
     * @param response
     * @param page
     * @param rows
     * @param beginDate
     * @param endDate
     * @param phoneNumber
     * @param checkStatus
     */
    @RequestMapping("/queryPhoneChangeApplyList.do")
    public void queryPhoneChangeApplyList(HttpServletResponse response, Integer page, Integer rows, String beginDate,
            String endDate, String phoneNumber, String checkStatus) {
        logger.info("查询手机变更申请");
        try {
            List<TblCusPhoneChangeApply> changeApplies = this.cusPhoneChangeApplyService.queryPhoneChangeApplyList(
                    page, rows, beginDate, endDate, phoneNumber, checkStatus);
            writeMsg(
                    response,
                    JSON.toJSONString(PagerInfo
                            .converFromPage((Page<com.medical.manager.authentication.web.model.TblCusPhoneChangeApply>) changeApplies)));
        } catch (Exception e) {
            logger.error("查询手机变更申请失败", e);
            writeFailMsg(response, "查询手机变更申请失败");
        }
    }
    
    /**
     * 审核手机变更申请
     * @param response
     * @param phoneChangeApply
     */
    @RequestMapping("/checkPhoneChangeApply.do")
    public void checkPhoneChangeApply(HttpSession session, HttpServletResponse response,TblCusPhoneChangeApply phoneChangeApply ){
        logger.info("审核手机变更申请");
        TblUser user=(TblUser) session.getAttribute(SESSION_USER);
        phoneChangeApply.setAudit(user.getUserName());
        try {
            CusBasicInfo cusBasicInfo= this.cusBasicInfoService.queryByMobileNo(phoneChangeApply.getNewPhone());
            if (cusBasicInfo!=null) {
                throw new Exception("审核失败，该手机号已注册过!");
            }
            this.cusPhoneChangeApplyService.checkPhoneChangeApply(phoneChangeApply);
            writeSuccessMsg(response, "审核成功");
        } catch (Exception e) {
            logger.error("审核手机变更申请失败！",e);
            writeFailMsg(response, "审核手机变更申请失败"+e.getMessage());
        }
    }
    
    /**
     * 跳转到客户注册列表页面
     * @param session
     * @return
     */
    @RequestMapping("/cusBasicInfoIndex.do")
    public String cusBasicInfoIndex(HttpSession session,Model model){
        String timeout=logginTimeOut(session);
        if (timeout!=null) {
            return timeout;
        }
        List<TblSysProvince> provincesList=this.sysAdministrativeService.findAllProvince();
        model.addAttribute("provinceList", provincesList);
        return "authentiction/cusBasicInfoList";
    }
    
    /**
     * 查询客户注册信息
     * @param response
     * @param page
     * @param rows
     * @param phoneNumber
     * @param realName
     * @param beginDate
     * @param endDate
     * @param chekStatus
     */
    @RequestMapping("/queryCusBasicExpandList.do")
    public void queryCusBasicExpandList(HttpServletResponse response,Integer page,Integer rows,String phoneNumber,String realName,String beginDate,String endDate,String checkStatus){
        logger.info("查询客户注册信息");
        Map<String, String> paramMap=new HashMap<String, String>();
        if (StringUtils.isNoneBlank(beginDate)) {
            paramMap.put("beginDate", beginDate+" 00:00:00");
        }
        if (StringUtils.isNoneBlank(endDate)) {
            paramMap.put("endDate", endDate);
        }
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("realName", realName);
        paramMap.put("checkStatus", checkStatus);
        try {
            List<TblCusBasicExpand> cusBasicExpands=this.cusBasicExpandService.queryCusBasicExpandList(page,rows,paramMap);
            writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblCusBasicExpand>) cusBasicExpands)));
        } catch (Exception e) {
            logger.error("查询客户注册信息失败", e);
            writeFailMsg(response, "查询客户注册信息失败");
        }
    }
    
    /**
     * 审核客户基本资料
     * @param response
     * @param cusBasicExpand
     */
    @RequestMapping("/checkCusBasicExpand.do")
    public void checkCusBasicExpand(HttpServletResponse response,HttpSession session,TblCusBasicExpand cusBasicExpand){
        logger.info("审核客户基本资料");
       TblUser user=getSessionUser(session);
       cusBasicExpand.setAudit(user.getUserName());
        try {
            this.cusBasicExpandService.checkCusBasicExpand(cusBasicExpand);
            writeSuccessMsg(response, "审核成功");
        } catch (Exception e) {
            logger.error("审核失败！",e);
            writeFailMsg(response, "审核失败！");
        }
    }
    
    /**
     * 保存/修改客户基本资料
     * @param response
     * @param cusBasicExpand
     */
    @RequestMapping("/saveOrUpdateCusBasicExpand.do")
    public void saveOrUpdateCusBasicExpand(HttpServletResponse response,TblCusBasicExpand cusBasicExpand){
        if (cusBasicExpand.getCusId()==null) {
            logger.info("保存客户基本资料");
            try {
                this.cusBasicExpandService.saveCusBasicExpand(cusBasicExpand);
                writeSuccessMsg(response, "保存成功");
            } catch (Exception e) {
                logger.error("保存客户基本资料失败！", e);
                writeFailMsg(response, "保存客户基本资料失败");
            }
        }else {
            try {
                logger.info("更新客户基本资料");
                this.cusBasicExpandService.updateCusBasicExpand(cusBasicExpand);
                writeSuccessMsg(response, "修改成功");
            } catch (Exception e) {
                logger.error("修改客户基本资料失败！",e);
                writeFailMsg(response, "修改失败！");
            }
        }
    }

}
