/**
 * 系统项目名称
 * com.medical.manager.app.ambulance.web.action
 * AmbulanceHandler.java
 * 
 * 2015年12月14日-下午3:47:12
 * 2015
 * 
 */

package com.medical.manager.app.ambulance.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.medical.manager.app.ambulance.web.model.TblAlarmTask;
import com.medical.manager.app.ambulance.web.model.TblEvaluateInfo;
import com.medical.manager.app.ambulance.web.model.TblMessageCenter;
import com.medical.manager.app.ambulance.web.model.TblReservePlan;
import com.medical.manager.app.ambulance.web.service.IAmbulanceService;
import com.medical.manager.app.ambulance.web.vo.AmbulanceInfoVo;
import com.medical.manager.app.center.web.model.TblAlarmInfo;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.authentication.web.model.TblCusPhoneChangeApply;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.funds.web.model.TblRechargeTurnover;
import com.medical.manager.systemset.web.model.TblSysInfo;

/**
 * 
 * AmbulanceHandler 急救车端接口
 * 
 * 2015年12月14日 下午3:47:12
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/app/ambulance")
public class AmbulanceHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(AmbulanceHandler.class);

    @Resource
    private IAmbulanceService ambulanceService;
    @Resource
    private ICusBasicInfoService cusBasicInfoService;
    /**
     * 资质证书存放路径
     */
    @Value("${medical.aptitude.location}")
    private String aptitudeLocation;
    
    @Value("${medical.headImg.location}")
    private String headImg;

    /**
     * 查询报警任务列表 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param session
     * @exceptio
     * @since 1.0.0
     */
    @RequestMapping("queryAlarmTaskList.do")
    public void queryAlarmTaskList(HttpServletResponse response, HttpSession session, Long cusId) {
        logger.info("查询报警任务列表");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId不能为空");
            }
            List<TblAlarmTask> tblAlarmTasks = this.ambulanceService.queryAlarmTaskList(cusId);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功");
            responseMap.put("data", tblAlarmTasks);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }

    /**
     * 查看报警人详情 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param alarmId
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryAlarmInfo.do")
    public void queryAlarmInfo(HttpServletResponse response, Long alarmId) {
        logger.info("查看报警人详情");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (alarmId==null || alarmId==0) {
                throw new Exception("alarmId不能为空");
            }
            TblAlarmInfo tblAlarmInfo = this.ambulanceService.queryAlarmInfo(alarmId);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功");
            if(tblAlarmInfo==null){
                tblAlarmInfo=new TblAlarmInfo();
            }
            responseMap.put("data", tblAlarmInfo);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }

    /**
     * 查看预案 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param alarmId
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryReservePlan.do")
    public void queryReservePlan(HttpServletResponse response, Long cusId) {
        logger.info("查看预案");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId不能为空");
            }
            TblReservePlan reservePlan = this.ambulanceService.queryReservePlan(cusId);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功");
            if(reservePlan==null){
                reservePlan=new TblReservePlan();
            }
            responseMap.put("data", reservePlan);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }

    /**
     * 编辑/新增预案 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param alarmId
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("editReservePlan.do")
    public void editReservePlan(HttpServletResponse response, TblReservePlan reservePlan) {
        logger.info("编辑预案");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (reservePlan==null) {
                throw new Exception("预案为空，不能操作！");
            }
            if (reservePlan.getId() != null) {
                this.ambulanceService.editReservePlan(reservePlan);
            } else {
                this.ambulanceService.saveReservePlan(reservePlan);
            }
            responseMap.put("success", true);
            responseMap.put("msg", "编辑成功");
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "编辑失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }

    /**
     * 接任务 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param alarmId
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("receiveAlarmTask.do")
    public void receiveAlarmTask(HttpServletResponse response, TblAlarmTask tblAlarmTask) {
        logger.info("接任务");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (tblAlarmTask==null || tblAlarmTask.getId()==null) {
                throw new Exception("任务为空，无法接任务");
            }
            CusBasicInfo cusBasicInfo=this.cusBasicInfoService.queryById(tblAlarmTask.getAmbulanceId().toString());
            if (cusBasicInfo.getYearScore()==0) {
                throw new Exception("积分为0,无法接任务，请到120中心学习！");
            }
            this.ambulanceService.receiveAlarmTask(tblAlarmTask);
            responseMap.put("success", true);
            responseMap.put("msg", "接任务成功");
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "接任务失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }

    /**
     * 处理任务 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param alarmId
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("dealAlarmTask.do")
    public void dealAlarmTask(HttpServletResponse response, TblAlarmTask tblAlarmTask) {
        logger.info("接任务");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (tblAlarmTask==null || tblAlarmTask.getId()==null) {
                throw new Exception("任务为空，无法处理任务");
            }
            this.ambulanceService.dealAlarmTask(tblAlarmTask);
            responseMap.put("success", true);
            responseMap.put("msg", "处理任务成功");
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "处理任务失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }

    /**
     * 查询报警任务列表 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryCompleteAlarmTaskList.do")
    public void queryCompleteAlarmTaskList(HttpServletResponse response, HttpSession session, Long cusId,
            String beginDate, String endDate) {
        logger.info("查询报警任务列表");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId不能为空");
            }
            List<TblAlarmInfo> tblAlarmInfos = this.ambulanceService.queryCompleteAlarmTaskList(cusId,
                    CommonEnums.TaskStatus.COMPLETE.id, beginDate, endDate);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功");
            responseMap.put("data", tblAlarmInfos);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * <p>Discription:[提交120中心资质]</p>
     * @param response
     * @param legalPersonFile 文件
     * @param organizationFile 组织机构文件
     * @param request
     * @param expand 资质信息
     * @author: Jason
     * @update: 2015年12月15日 Jason[变更描述]
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("submitAptitude.do")
    public void submitAptitude(HttpServletResponse response, 
            @RequestParam MultipartFile legalPersonFile, 
            @RequestParam MultipartFile organizationFile, 
            HttpServletRequest request,
            TblCusBasicExpand expand){
        logger.info("提交资质");
        Map responseMap = new HashMap();
        try{
            if(expand == null){
                throw new Exception("资质信息为空，不能提交！");
            }
            String uploadPath = request.getSession().getServletContext().getRealPath("/");
            uploadPath += aptitudeLocation + "/" + expand.getSubmitCustMobile() + "/";
            File uploadDir=new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            //遍历文件夹，所有文件全部提取
            String legalPersonCertificateFile = uploadPath + "";
            String organizationCertificateFile = uploadPath + "";
            if(legalPersonFile != null){
                legalPersonCertificateFile += legalPersonFile.getOriginalFilename();
                legalPersonFile.transferTo(new File(legalPersonCertificateFile));
                String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + 
                        aptitudeLocation + "/" + expand.getSubmitCustMobile() + "/" + legalPersonFile.getOriginalFilename();
                expand.setLegalPersonCertificate(imgUrl);
            }
            if(organizationFile != null){
                organizationCertificateFile += organizationFile.getOriginalFilename();
                organizationFile.transferTo(new File(organizationCertificateFile));
                String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + 
                        aptitudeLocation + "/" + expand.getSubmitCustMobile() + "/" + organizationFile.getOriginalFilename();
                expand.setLegalPersonCertificate(imgUrl);
            }
            expand.setCheckStatus(CommonEnums.CheckStatus.AUDIT.id);
            this.ambulanceService.doSubmitAptitude(expand);
            responseMap.put("success", true);
            responseMap.put("msg", "提交资质信息成功，请等待审核！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "提交资质信息失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 急救车首页信息
     * (这里描述这个方法的适用条件 - 可选)
     * @param response
     * @param cusId
     *@exception
     *@since 1.0.0
     */
    @RequestMapping("ambulanceIndex.do")
    public void ambulanceIndex(HttpServletResponse response,Long cusId){
        logger.info("查询急救车首页信息");
        Map<String, Object> responseMap=new HashMap<String,Object>();
        Map<String, Object> dataMap=new HashMap<String,Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId不能为空");
            }
            CusBasicInfo cusBasicInfo=this.cusBasicInfoService.queryById(cusId.toString());
            dataMap.put("status", cusBasicInfo==null?"":cusBasicInfo.getAmbulanceStatus());
            dataMap.put("statusReason", cusBasicInfo==null?"":cusBasicInfo.getStatusReason());
            List<CusBasicInfo> cusBasicInfos=this.cusBasicInfoService.query120DispatchCenter(cusId);
            Map<String, Object> cusMap=null;
            List<Object> datas=new ArrayList<Object>();
            for (CusBasicInfo basicInfo : cusBasicInfos) {
                cusMap=new HashMap<String, Object>();
                cusMap.put("name", basicInfo.getRealName());
                cusMap.put("phone", basicInfo.getPhone());
                cusMap.put("unitName", basicInfo.getNickname());
                cusMap.put("headImg", basicInfo.getHeadImg());
                datas.add(cusMap);
            }
            dataMap.put("center", datas);
            TblRechargeTurnover rechargeTurnover=this.ambulanceService.findById(cusId);
            dataMap.put("expireDate", rechargeTurnover==null?"":rechargeTurnover.getExpireDate());

            Map<String, String> map=this.ambulanceService.countEvaluate(cusId);
            dataMap.putAll(map);
            responseMap.put("success", true);
            responseMap.put("data", dataMap);
            responseMap.put("msg", "查询成功！");
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败！"+e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 查询评论列表 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryEvaluateList.do")
    public void queryEvaluateList(HttpServletResponse response, Long cusId) {
        logger.info("查询评论列表");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId不能为空");
            }
            List<TblEvaluateInfo> evaluateInfos=this.ambulanceService.queryEvaluateList(cusId);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功");
            responseMap.put("data", evaluateInfos);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 查询充值记录 (这里描述这个方法适用条件 – 可选)
     * 
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryRechargeRecordList.do")
    public void queryRechargeRecordList(HttpServletResponse response, Long cusId,String beginDate,String endDate) {
        logger.info("查询充值记录");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId不能为空");
            }
            List<TblRechargeTurnover> rechargeTurnovers=this.ambulanceService.queryRechargeRecordList(cusId,beginDate,endDate);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功");
            responseMap.put("data", rechargeTurnovers);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 变更手机号
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("changePhoneNumber.do")
    public void changePhoneNumber(HttpServletResponse response, TblCusPhoneChangeApply cusPhoneChangeApply) {
        logger.info("变更手机号");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusPhoneChangeApply==null || cusPhoneChangeApply.getUserId()==null) {
                throw new Exception("userId不能为空");
            }
            CusBasicInfo cusBasicInfo=this.cusBasicInfoService.queryByMobileNo(cusPhoneChangeApply.getNewPhone());
            if (cusBasicInfo!=null) {
                throw new Exception("提交失败，该手机号已注册！");
            }
            this.ambulanceService.changePhoneNumber(cusPhoneChangeApply);
            responseMap.put("success", true);
            responseMap.put("msg", "变更申请提交成功,请等待审核！");
            responseMap.put("data", cusPhoneChangeApply);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "变更申请提交查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 设置急救车状态
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("setAmbulanceStatus.do")
    public void setAmbulanceStatus(HttpServletResponse response, CusBasicInfo cusBasicInfo) {
        logger.info("设置急救车状态");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusBasicInfo==null || cusBasicInfo.getId()==null) {
                throw new Exception("急救车信息为空！");
            }
            this.cusBasicInfoService.update(cusBasicInfo);
            responseMap.put("success", true);
            responseMap.put("msg", "设置急救车状态成功！");
            responseMap.put("data", cusBasicInfo);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "设置急救车状态失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 充值缴费信息页
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryRechargeIndex.do")
    public void queryRechargeIndex(HttpServletResponse response, Long cusId,String cusType) {
        logger.info("充值主页信息查询");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("急救车信息为空！");
            }
            TblSysInfo sysInfo=null;
            if (CommonEnums.CustomType.A120.id.equals(cusType)) {
                TblCusBasicExpand basicExpand=this.ambulanceService.queryCusBasicExpand(cusId);
                if (basicExpand!=null) {
                    if (CommonEnums.TollLevel.PROVINCE.id.equals(basicExpand.getTollLevel())) {
                        sysInfo=this.ambulanceService.querySysInfoByType(CommonEnums.SystemParamType.A120_PROVINCE_MONTH_USE_COST.id);
                    }
                    if (CommonEnums.TollLevel.CITY.id.equals(basicExpand.getTollLevel())) {
                        sysInfo=this.ambulanceService.querySysInfoByType(CommonEnums.SystemParamType.A120_CITY_MONTH_USE_COST.id);
                    }
                    if (CommonEnums.TollLevel.COUNTY.id.equals(basicExpand.getTollLevel())) {
                        sysInfo=this.ambulanceService.querySysInfoByType(CommonEnums.SystemParamType.A120_COUNTY_MONTH_USE_COST.id);
                    }
                }
            }else if (CommonEnums.CustomType.OLD_PERSON.id.equals(cusType)) {
                sysInfo=this.ambulanceService.querySysInfoByType(CommonEnums.SystemParamType.OLD_MONTH_USE_COST.id);
            }else {
                sysInfo=this.ambulanceService.querySysInfoByType(CommonEnums.SystemParamType.AMBULANCE_MONTH_USE_COST.id);
            }
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            if(sysInfo==null){
                sysInfo=new TblSysInfo();
            }
            responseMap.put("data", sysInfo);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 充值
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("submitRecharge.do")
    public void submitRecharge(HttpServletResponse response, TblRechargeTurnover rechargeTurnover) {
        logger.info("充值");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (rechargeTurnover==null || rechargeTurnover.getCustId()==null) {
                throw new Exception("充值用户为空！");
            }
            this.ambulanceService.submitRecharge(rechargeTurnover);
            responseMap.put("success", true);
            responseMap.put("msg", "充值成功！");
            responseMap.put("data", rechargeTurnover);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "充值失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 修改头像和昵称
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("modifyCusBasicInfo.do")
    public void modifyCusBasicInfo(HttpServletResponse response, CusBasicInfo cusBasicInfo,@RequestParam MultipartFile headImgFile,HttpServletRequest request) {
        logger.info("修改基本信息");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusBasicInfo==null || cusBasicInfo.getId()==null) {
                throw new Exception("客户信息为空！");
            }
            
            String uploadPath = request.getSession().getServletContext().getRealPath("/");
            uploadPath += headImg + "/" + cusBasicInfo.getId() + "/";
            File uploadDir=new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            String headImgPathFile = uploadPath + "/";
            if(headImgFile != null){
                headImgPathFile += headImgFile.getOriginalFilename();
                headImgFile.transferTo(new File(headImgPathFile));
                String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + 
                        headImg + "/" +cusBasicInfo.getId()  + "/" + headImgFile.getOriginalFilename();
                cusBasicInfo.setHeadImg(imgUrl);
            }
            responseMap.put("success", true);
            responseMap.put("msg", "修改成功！");
            responseMap.put("data", cusBasicInfo);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "修改失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 查询我的页面信息
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryMyIndexInfo.do")
    public void queryMyIndexInfo(HttpServletResponse response, Long cusId) {
        logger.info("查询我的页面信息");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId为空！");
            }
            CusBasicInfo cusBasicInfo=this.cusBasicInfoService.queryById(cusId.toString());
            int countMessage=this.ambulanceService.countMessage(cusId);
            Map<String, Object> dataMap=new HashMap<String, Object>();
            dataMap.put("headImg", cusBasicInfo.getHeadImg());
            dataMap.put("nickName", cusBasicInfo.getNickname());
            dataMap.put("countMessage", countMessage);
            dataMap.put("yearScore", cusBasicInfo.getYearScore());
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", dataMap);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 查询消息列表
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("queryMessageList.do")
    public void queryMessageList(HttpServletResponse response, Long cusId) {
        logger.info("查询消息列表");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            if (cusId==null || cusId==0) {
                throw new Exception("cusId为空！");
            }
            List<TblMessageCenter> messageCenters=this.ambulanceService.queryMessageList(cusId);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", messageCenters);
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * 急救车信息查询，包括急救车用户评分信息
     * 这里是方法描述
     *
     * @author Json
     * @date 2015年12月21日 下午3:16:29
     * @param response
     * @param longitude	经度
     * @param latitude	纬度
     */
    @RequestMapping("queryNearAmbulance.do")
    public void queryNearAmbulance(HttpServletResponse response, String longitude, String latitude) {
    	logger.info("查询急救车信息...");
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	try {
    		if (StringUtil.isEmpty(longitude) || StringUtil.isEmpty(latitude)) {
    			throw new Exception("longitude/latitude不能为空！");
    		}
    		double _longitude = 0;
    		double _latitude = 0;
    		try {
    			_longitude = NumberUtils.toDouble(longitude);
    			_latitude = NumberUtils.toDouble(latitude);
    		} catch (Exception e) {
    			throw new Exception("经纬度格式不对，必须为数字");
    		}
    		
    		List<AmbulanceInfoVo> datas = ambulanceService.queryNearAmbulance(_longitude, _latitude);
    		responseMap.put("success", true);
    		responseMap.put("msg", "查询成功！");
    		responseMap.put("data", datas);
    	} catch (Exception e) {
    		responseMap.put("success", false);
    		responseMap.put("msg", "查询失败！" + e.getMessage());
    	}
    	writeMsg(response, responseMap);
    }
    
}
