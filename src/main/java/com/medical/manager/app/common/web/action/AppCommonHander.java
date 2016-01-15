
/**
 * 系统项目名称
 * com.medical.manager.app.common.web.action
 * AppCommonHander.java
 * 
 * 2015年12月14日-下午12:50:27
 * 2015
 * 
 */
 
package com.medical.manager.app.common.web.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medical.manager.app.common.service.ICommonService;
import com.medical.manager.app.common.web.dao.ITblFundBalanceDao;
import com.medical.manager.app.common.web.dao.ITblFundBalanceSeqDao;
import com.medical.manager.app.common.web.model.TblFundBalance;
import com.medical.manager.app.common.web.model.TblFundBalanceSeq;
import com.medical.manager.app.common.web.model.TblFundWithdrawAcc;
import com.medical.manager.app.common.web.service.ITblFundWithdrawAccService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.funds.web.model.TblWithdrawTurnover;
import com.medical.manager.funds.web.service.ITblWithdrawTurnoverService;
import com.medical.manager.product.web.service.IOrderService;


/**
 * 
 * AppCommonHander 公共接口
 * 
 * 2015年12月14日 下午12:50:27
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/app/common")
public class AppCommonHander extends BaseHandler {
    private Logger logger = Logger.getLogger(AppCommonHander.class);
    
    @Resource
    private ITblFundBalanceDao tblFundBalanceDao;
    @Resource
    private ITblFundBalanceSeqDao tblFundBalanceSeqDao;
    
    @Resource
    private IOrderService orderService;
    @Resource
    private ICommonService commonService;
    @Resource
    private ICusBasicInfoService cusBasicInfoService;
    @Resource
    private ITblWithdrawTurnoverService withdrawTurnoverService;
    @Resource
    private ITblFundWithdrawAccService tblFundWithdrawAccService;
    
	@RequestMapping("withdraw.do")
	public void withdraw(HttpServletResponse response, String cusId, String withdrawAccId, String withdrawAmount) {
		logger.info("AppCommonHander.withdraw()提现...");
		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		try {
			if (StringUtil.isEmpty(cusId) || StringUtil.isEmpty(withdrawAccId)
					|| StringUtil.isEmpty(withdrawAmount)) {
				throw new Exception("必填参数为空，请检查cusId/accountId/withdrawAmount");
			}
			CusBasicInfo info = cusBasicInfoService.queryById(cusId);
			if (info == null) {
				throw new Exception("用户不存在!");
			}
			TblFundWithdrawAcc acc = tblFundWithdrawAccService.findById(Long.valueOf(withdrawAccId));
			if (acc == null) {
				throw new Exception("提现账户不存在!");
			}
			TblWithdrawTurnover turnover = new TblWithdrawTurnover(cusId,
					info.getNickname(), info.getPhone(), info.getRealName(),
					acc.getAcc_no(), null, acc.getOpen_bank(), 
					new BigDecimal(withdrawAmount), Long.valueOf(withdrawAccId));
			withdrawTurnoverService.insert(turnover);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("deleteAccount.do")
	public void deleteAccount(HttpServletResponse response, String cusId) {
		logger.info("AppCommonHander.deleteAccount()删除账户...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId) ) {
				throw new Exception("必填参数为空，请检查cusId");
			}
			cusBasicInfoService.delete(Long.valueOf(cusId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("insertWithdrawAcc.do")
	public void insertWithdrawAcc(HttpServletResponse response, String cusId,
			String accType, String openBank, String accName, String accNo,
			String defaultFlag) {
		logger.info("AppCommonHander.insertWithdrawAcc()新增提现账户...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId) || StringUtil.isEmpty(accType)
					|| StringUtil.isEmpty(accNo)) {
				throw new Exception("必填参数为空，请检查cusId/accType/accNo");
			}
			// 账户类型为银行卡时，开户行和开户人姓名必填
			if (CommonEnums.FundWithdrawAccType.BANK_CARD.id.equals(accType)
					&& (StringUtil.isEmpty(accName) || StringUtil.isEmpty(openBank))) {
				throw new Exception("账户类型为银行卡，则accName和openBank为必填项！");
			}
			TblFundWithdrawAcc acc = new TblFundWithdrawAcc(
					Long.valueOf(cusId), accType, openBank, accName, accNo,
					defaultFlag, null, null);
			tblFundWithdrawAccService.insert(acc);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "新增成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "新增失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("updateWithdrawAcc.do")
	public void updateWithdrawAcc(HttpServletResponse response, String withdrawAccId, String openBank, String accName,
			String accNo, String defaultFlag) {
		logger.info("AppCommonHander.updateWithdrawAcc()修改提现账户...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(withdrawAccId)) {
				throw new Exception("withdrawAccId不能为空");
			}
			TblFundWithdrawAcc acc = new TblFundWithdrawAcc(null, null,
					openBank, accName, accNo, defaultFlag, null, null);
			acc.setId(Long.valueOf(withdrawAccId));
			tblFundWithdrawAccService.update(acc);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryWithdrawAccList.do")
	public void queryWithdrawAccList(HttpServletResponse response, String cusId) {
		logger.info("AppCommonHander.queryWithdrawAccList()查询提现账户列表...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("cusId不能为空");
			}
			List<TblFundWithdrawAcc> accs = tblFundWithdrawAccService.queryByCusId(Long.valueOf(cusId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, accs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
    
	@RequestMapping("queryBalanceByCusId.do")
	public void queryBalanceByCusId(HttpServletResponse response, String cusId) {
		logger.info("AppCommonHander.queryBalanceByCusId()查询余额...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("客户id不能为空！");
			}
			TblFundBalance balance = tblFundBalanceDao.findByCusId(Long.parseLong(cusId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, balance);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryBalanceSeqByCusId.do")
	public void queryBalanceSeqByCusId(HttpServletResponse response, String cusId) {
		logger.info("AppCommonHander.queryBalanceSeqByCusId()查询收支记录...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("客户id不能为空！");
			}
			List<TblFundBalanceSeq> seqs = tblFundBalanceSeqDao.queryBalanceSeqByCusId(Long.parseLong(cusId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, seqs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
    
    /**
     * sendSms 发送短信验证码接口
     * (这里描述这个方法适用条件 – 可选)
     * @param request
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("sendSms.do")
    public void sendSms(HttpServletResponse response, String mobileNo){
        logger.info("sendSms...");
        Map responseMap = new HashMap();
        try{
            if(StringUtil.isEmpty(mobileNo)){
                throw new Exception("手机号码不能为空！");
            }
            logger.info("向手机【" + mobileNo + "】发送短信");
            String smsCode = commonService.sendSmsCode(mobileNo);
            if(smsCode != null && !"".equals(smsCode.trim())){
                responseMap.put("success", true);
                responseMap.put("msg", "短信发送成功！");
            }else{
                throw new Exception("短信发送失败！");
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "系统异常！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * checkStatus 检测手机号是否已经注册
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param mobileNo 手机号 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("checkUser.do")
    public void checkUser(HttpServletResponse response, String mobileNo){
        logger.info("checkUser");
        Map responseMap = new HashMap();
        try{
            if(StringUtil.isEmpty(mobileNo)){
                throw new Exception("手机号码不能为空！");
            }
            logger.info("检查手机号【" + mobileNo + "】是否已注册");
            boolean isMember = commonService.isRegistered(mobileNo);
            responseMap.put("success", true);
            responseMap.put("msg", "交易请求成功");
            //true:已注册，false:未注册
            responseMap.put("status", isMember);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "系统异常！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * userLogin 用户登录（适用于亲属端、老人端、急救单位端）
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param mobileNo 手机号
     * @param smsCode 短信验证码
     * @param invitationCode 邀请码
     * @param custType 客户类型：01老人端02亲属端03急救车端04：120中心
     * @param orgSmsCode 原验证码，在获取验证码的时候，会将此验证码回传至APP端，在用户登录时，APP需要将此原样传递请求
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("userLogin.do")
    public void userLogin(HttpServletResponse response, String mobileNo,
            String  invitationCode, String custType){
        logger.info("用户注册/登录");
        Map responseMap = new HashMap();
        try{
            if(StringUtil.isEmpty(mobileNo)){
                throw new Exception("手机号码不能为空！");
            }
            
            boolean isMember = commonService.isRegistered(mobileNo);
            CusBasicInfo basicInfo = null;
            if(isMember){
                //已注册的
                basicInfo = cusBasicInfoService.queryByMobileNo(mobileNo);
            }else{
                //未注册的
                basicInfo = new CusBasicInfo();
                basicInfo.setPhone(mobileNo);
                if(StringUtils.isEmpty(custType)){
                    throw new Exception("用户类型不存在，请上送用户类型：custType");
                }
                basicInfo.setCusType(custType);
                cusBasicInfoService.insert(basicInfo);
            }
            responseMap.put("success", true);
            responseMap.put("msg", "用户注册/登录成功");
            responseMap.put("mobileNo", mobileNo);
            responseMap.put("custId", basicInfo.getId());
            responseMap.put("custName", basicInfo.getRealName());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "系统异常！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * userLogin 用户登录（适用于亲属端、老人端、急救单位端）
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param orderId	订单id
     * @param cusId		客户id
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("balancePay.do")
    public void balancePay(HttpServletResponse response, String orderNumber, String cusId){
    	logger.info("余额付款...");
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	try{
    		if(StringUtil.isEmpty(orderNumber) || StringUtil.isEmpty(cusId)){
    			throw new Exception("orderId/cusId不能为空！");
    		}
    		orderService.balancePay(orderNumber, cusId);
    		responseMap.put("success", true);
    		responseMap.put("msg", "余额付款成功！");
    	}catch(Exception e){
    		logger.error(e.getMessage(), e);
    		responseMap.put("success", false);
    		responseMap.put("msg", "余额付款失败！" + e.getMessage());
    	}
    	writeMsg(response, responseMap);
    }
    
    /**
     * uploadLongitudeLatitude 上传更新用户的经纬度
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param custId
     * @param longitude 经度
     * @param latitude 纬度
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("uploadLongitudeLatitude.do")
    public void uploadLongitudeLatitude(HttpServletResponse response,
            Long custId, String longitude, String latitude){
        logger.info("更新用户：" + custId + "的经纬度。");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try{
            if(custId == null){
                throw new Exception("用户不存在！");
            }
            CusBasicInfo info = cusBasicInfoService.queryById(custId.toString());
            if (info == null) {
                throw new Exception("用户不存在!");
            }
            info.setLatitude(latitude);
            info.setLongitude(longitude);
            cusBasicInfoService.update(info);
            responseMap.put("success", true);
            responseMap.put("msg", "经纬度信息更新成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "更新经纬度失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
}
