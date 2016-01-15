
/**
 * 系统项目名称
 * com.medical.manager.app.center.web.action
 * CenterHandler.java
 * 
 * 2015年12月14日-下午3:46:31
 * 2015
 * 
 */
 
package com.medical.manager.app.center.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.medical.manager.app.center.web.model.TblAlarmInfo;
import com.medical.manager.app.center.web.model.TblCenterPoliceInfo;
import com.medical.manager.app.center.web.model.TblCustMedicalRecords;
import com.medical.manager.app.center.web.model.TblScheduingInfo;
import com.medical.manager.app.center.web.service.ICenterService;
import com.medical.manager.app.center.web.vo.EvaluationInfo;
import com.medical.manager.app.relatives.web.model.TblCustRelation;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.MD5Util;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.funds.web.model.TblRechargeTurnover;


/**
 * 
 * CenterHandler 120中心端接口
 * 
 * 2015年12月14日 下午3:46:31
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/app/center")
public class CenterHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(CenterHandler.class);
    @Resource
    private ICenterService centerService;
    /**
     * 用户病历存放路径
     */
    @Value("${medical.records.location}")
    private String medicalRecordsLocation;
    /**
     * 资质证书存放路径
     */
    @Value("${medical.aptitude.location}")
    private String aptitudeLocation;
    
    /**
     * queryCenterInfo 接警员登录之前的搜索120中心信息
     * (这里描述这个方法适用条件 – 可选)
     * @param request
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("queryCenterInfo.do")
    public void queryCenterInfo(HttpServletResponse response, String keyWord){
        logger.info("queryCenterInfo...");
        Map responseMap = new HashMap();
        try{
            List<TblCusBasicExpand> list = centerService.queryCenterList(keyWord);
            responseMap.put("success", true);
            responseMap.put("msg", "系统查询成功");
            responseMap.put("data", list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "系统异常！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * queryCenter 查询120中心
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param keyWord 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("queryCenter.do")
    public void queryCenter(HttpServletResponse response, CusBasicInfo searchVo){
        logger.info("queryCenterInfo...");
        Map responseMap = new HashMap();
        try{
            List<CusBasicInfo> list = centerService.queryCenterList(searchVo);
            responseMap.put("success", true);
            responseMap.put("msg", "系统查询成功");
            responseMap.put("data", list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "系统异常！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    
    /**
     * <p>Discription:[接警员登录]</p>
     * @param response
     * @param mobileNo 手机号
     * @param password 密码
     * @param centerId 接警员所属120中心主键
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    @RequestMapping("policeLogin.do")
    public void policeLogin(HttpServletRequest request, HttpServletResponse response, 
            String mobileNo, String password, Long centerId){
        logger.info("接警员登录");
        Map responseMap = new HashMap();
        try{
            String ip = getClientIp(request);
            TblCenterPoliceInfo policeInfo = centerService.doPoliceLogin(mobileNo, password, centerId, ip);
            if(policeInfo != null){
                responseMap.put("success", true);
                responseMap.put("msg", "登录成功");
                responseMap.put("data", policeInfo);
            }else{
                throw new Exception("用户名或密码错误！");
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "登录失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * <p>Discription:[管理员登录]</p>
     * @param response
     * @param mobileNo 手机号
     * @param password 密码
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    @RequestMapping("managerLogin.do")
    public void managerLogin(HttpServletRequest request, HttpServletResponse response, String mobileNo,
            String password){
        logger.info("管理员登录");
        Map responseMap = new HashMap();
        try{
            String ip = getClientIp(request);
            TblCenterPoliceInfo policeInfo = centerService.doManagerLogin(mobileNo, password, ip);
            if(policeInfo != null){
                responseMap.put("success", true);
                responseMap.put("msg", "登录成功");
                responseMap.put("data", policeInfo);
            }else{
                throw new Exception("用户名或密码错误！");
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "登录失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * <p>Discription:[找回密码]</p>
     * @param response
     * @param mobileNo 手机号
     * @param password 新密码
     * @param password2 新密码2
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    @RequestMapping("findPassword.do")
    public void findPassword(HttpServletResponse response, String mobileNo, String password, String password2){
        logger.info("找回密码");
        Map responseMap = new HashMap();
        try{
            if(StringUtil.isEmpty(password) || StringUtil.isEmpty(password2) || !password.equals(password2)){
                throw new Exception("新密码为空或两次输入密码不一致！");
            }
            centerService.doFindPassword(mobileNo, password);
            responseMap.put("success", true);
            responseMap.put("msg", "密码重置成功");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "找回密码失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * <p>Discription:[管理员注册]</p>
     * @param response
     * @param mobileNo 手机号
     * @param password 密码
     * @param password2 密码2
     * @param inviteNo 邀请码
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    @RequestMapping("managerRegister.do")
    public void managerRegister(HttpServletResponse response, String mobileNo, String password, 
            String password2, String inviteNo){
        logger.info("找回密码");
        Map responseMap = new HashMap();
        try{
            if(StringUtil.isEmpty(password) || StringUtil.isEmpty(password2) || !password.equals(password2)){
                throw new Exception("密码为空或两次输入密码不一致！");
            }
            TblCenterPoliceInfo policeInfo = centerService.doManagerRegister(mobileNo, password, inviteNo);
            if(policeInfo != null){
                responseMap.put("success", true);
                responseMap.put("msg", "管理员注册成功");
                responseMap.put("data", policeInfo);
            }else{
                throw new Exception("管理员注册失败！");
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "找回密码失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * receiveAlarm 接收到报警信息
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param alarmInfo 报警信息
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("receiveAlarm.do")
    public void receiveAlarm(HttpServletResponse response, TblAlarmInfo alarmInfo){
        logger.info("接收到报警信息");
        Map responseMap = new HashMap();
        try{
            if(alarmInfo == null){
                throw new Exception("报警信息为空！");
            }
            if(alarmInfo.getId() == null){
                //新增
                alarmInfo.setStatus(CommonEnums.AlarmStatus.WAITING.id);
                centerService.doReceiveAlarm(alarmInfo);
            }else{
                //修改
                centerService.doUpdateAlarm(alarmInfo);
            }
            responseMap.put("success", true);
            responseMap.put("msg", "报警信息已保存！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "保存报警信息失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * <p>Discription:[警情查询]</p>
     * @param response
     * @param alarmInfo
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    @RequestMapping("queryAlarm.do")
    public void queryAlarm(HttpServletResponse response, TblAlarmInfo alarmInfo){
        logger.info("查询报警信息");
        Map responseMap = new HashMap();
        try{
            List<TblAlarmInfo> alarmList = centerService.queryAlarm(alarmInfo);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", alarmList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询信息失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * <p>Discription:[调度查询]</p>
     * @param response
     * @param alarmInfo
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    @RequestMapping("queryScheduling.do")
    public void queryScheduling(HttpServletResponse response, TblScheduingInfo scheduingInfo){
        logger.info("查询调度信息");
        Map responseMap = new HashMap();
        try{
            List<TblScheduingInfo> scheduingInfoList = centerService.queryScheduling(scheduingInfo);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", scheduingInfoList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询信息失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    
    /**
     * queryMedicalRecords 查看用户病历
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param mobileNo 用户手机号
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("queryMedicalRecords.do")
    public void queryMedicalRecords(HttpServletRequest request, 
            HttpServletResponse response, String mobileNo){
        logger.info("查看病历");
        Map responseMap = new HashMap();
        try{
            List<TblCustMedicalRecords> list = centerService.queryMedicalRecords(mobileNo);
            if(list == null || list.isEmpty()){
                throw new Exception("用户暂无病历记录！");
            }
            
            String uploadPath = request.getSession().getServletContext().getRealPath("/");
            uploadPath += medicalRecordsLocation + "/" + mobileNo + "/";
            File uploadDir=new File(uploadPath);
            if(!uploadDir.exists()){
                logger.info("用户病历文件夹不存在！");
                throw new Exception("用户暂无病历记录！");
            }
            
            //遍历文件夹，所有文件全部提取
            List<String> fileUrlList = new ArrayList<String>();
            if(uploadDir.isDirectory()){
                String[] fileList = uploadDir.list();
                for(String fileName : fileList){
                    String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + 
                            medicalRecordsLocation + "/" + mobileNo + "/" + fileName;
                    fileUrlList.add(imgUrl);
                }
            }
            responseMap.put("success", true);
            responseMap.put("msg", "报警信息已保存！");
            responseMap.put("data", fileUrlList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询病历失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * queryRelation 查询关系人列表
     * (这里描述这个方法适用条件 – 可选)
     * @param response 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("queryRelation.do")
    public void queryRelation(HttpServletResponse response, String mobileNo){
        logger.info("查询关系人列表");
        Map responseMap = new HashMap();
        try{
            Map paramMap = new HashMap();
            paramMap.put("mobileNo", mobileNo);
            paramMap.put("relationStatus", CommonEnums.YesOrNop.YES.id);
            List<TblCustRelation> relationList = centerService.queryRelationList(paramMap);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", relationList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询关系人列表失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * queryRelation 搜索关系人列表
     * (这里描述这个方法适用条件 – 可选)
     * @param response 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("searchRelation.do")
    public void searchRelation(HttpServletResponse response, String relationMobile, String relationName){
        logger.info("查询关系人列表");
        Map responseMap = new HashMap();
        try{
            Map paramMap = new HashMap();
            paramMap.put("relationMobile", relationMobile);
            paramMap.put("relationName", relationName);
            paramMap.put("relationStatus", CommonEnums.YesOrNop.YES.id);
            
            List<TblCustRelation> relationList = centerService.queryRelationList(paramMap);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", relationList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询关系人列表失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * modifyRelation 修改联系人
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param relation 关系人
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("modifyRelation.do")
    public void modifyRelation(HttpServletResponse response, TblCustRelation relation){
        logger.info("修改关系人");
        Map responseMap = new HashMap();
        try{
            Map paramMap = new HashMap();
            if(relation == null || relation.getId() == null){
                throw new Exception("关系人为空，不能修改！");
            }
            if (StringUtils.isNoneBlank(relation.getMobileNo())) {
                CusBasicInfo basicInfo=this.centerService.queryCusBasicInfo(relation.getRelationMobile());
                if (basicInfo==null) {
                    throw new Exception("关系人手机号未注册，不能添加！");
                }
                relation.setRelationType(basicInfo.getCusType());
            }
            centerService.doModifyRelation(relation);
            responseMap.put("success", true);
            responseMap.put("msg", "修改成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "修改关系人失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * deleteRelation 删除联系人
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param relation 关系人
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("deleteRelation.do")
    public void deleteRelation(HttpServletResponse response, TblCustRelation relation){
        logger.info("删除关系人");
        Map responseMap = new HashMap();
        try{
            Map paramMap = new HashMap();
            if(relation == null || relation.getId() == null){
                throw new Exception("关系人为空，不能删除！");
            }
            centerService.doDeleteRelation(relation);
            responseMap.put("success", true);
            responseMap.put("msg", "删除成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "删除关系人失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * addRelation 新增关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("addRelation.do")
    public void addRelation(HttpServletResponse response, TblCustRelation relation){
        logger.info("新增关系人");
        Map responseMap = new HashMap();
        try{
            Map paramMap = new HashMap();
            if(relation == null || relation.getCustId() == null || StringUtil.isEmpty(relation.getRelationMobile())){
                throw new Exception("关系人手机号为空，不能添加！");
            }
            CusBasicInfo basicInfo=this.centerService.queryCusBasicInfo(relation.getRelationMobile());
            if (basicInfo==null) {
                throw new Exception("关系人手机号未注册，不能添加！");
            }
            relation.setRelationType(basicInfo.getCusType());
            relation.setRelationStatus(CommonEnums.YesOrNop.NO.id);
            centerService.doAddRelation(relation);
            responseMap.put("success", true);
            responseMap.put("msg", "新增成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "新增关系人失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * queryBeRelation 查询被加入的列表（有哪些人添加我为他们的关系人）
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param mobileNo 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("queryBeRelation.do")
    public void queryBeRelation(HttpServletResponse response, String mobileNo){
        logger.info("查询申请添加我为关系人的请求列表");
        Map responseMap = new HashMap();
        try{
            if(StringUtil.isEmpty(mobileNo)){
                throw new Exception("手机号为空，不能查询！");
            }
            Map paramMap = new HashMap();
            paramMap.put("relationMobile", mobileNo);
            //paramMap.put("relationStatus", CommonEnums.YesOrNop.YES.id);
            List<TblCustRelation> relationList = centerService.queryRelationList(paramMap);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", relationList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询关系人失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * checkBeRelation 审核被加入的列表（有哪些人添加我为他们的关系人）
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("checkBeRelation.do")
    public void checkBeRelation(HttpServletResponse response, TblCustRelation relation){
        logger.info("查询申请添加我为关系人的请求列表");
        Map responseMap = new HashMap();
        try{
            if(relation == null || relation.getId() == null){
                throw new Exception("申请信息为空，不能审核！");
            }
            centerService.doCheckRelation(relation);
            responseMap.put("success", true);
            responseMap.put("msg", "审核成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "审核关系人失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * <p>Discription:[120中心添加员工]</p>
     * @param response
     * @param policeInfo
     * @author: Jason
     * @update: 2015年12月15日 Jason[变更描述]
     */
    @RequestMapping("addEmployee.do")
    public void addEmployee(HttpServletResponse response, TblCenterPoliceInfo policeInfo, String mobile){
        logger.info("添加员工");
        Map responseMap = new HashMap();
        try{
            if(policeInfo == null || StringUtil.isEmpty(mobile)){
                throw new Exception("员工信息为空，不能添加！");
            }
            policeInfo.setPassword(MD5Util.md5Encode(policeInfo.getPassword()));
            centerService.doAddEmployee(policeInfo, mobile);
            responseMap.put("success", true);
            responseMap.put("msg", "员工信息添加成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "员工信息添加失败！" + e.getMessage());
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
            List<String> fileUrlList = new ArrayList<String>();
            String legalPersonCertificateFile = uploadPath + "";
            String organizationCertificateFile = uploadPath + "";
            if(legalPersonFile != null){
                legalPersonCertificateFile += legalPersonFile.getOriginalFilename();
                legalPersonFile.transferTo(new File(legalPersonCertificateFile));
                String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "" + request.getContextPath() + 
                        aptitudeLocation + "/" + expand.getSubmitCustMobile() + "/" + legalPersonFile.getOriginalFilename();
                expand.setLegalPersonCertificate(imgUrl);
            }
            if(organizationFile != null){
                organizationCertificateFile += organizationFile.getOriginalFilename();
                organizationFile.transferTo(new File(organizationCertificateFile));
                String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "" + request.getContextPath() + 
                        aptitudeLocation + "/" + expand.getSubmitCustMobile() + "/" + organizationFile.getOriginalFilename();
                expand.setLegalPersonCertificate(imgUrl);
            }
            expand.setCheckStatus(CommonEnums.CheckStatus.AUDIT.id);
            centerService.doSubmitAptitude(expand);
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
     * <p>Discription:[评价查询]</p>
     * @param response
     * @param policeInfo
     * @param mobile
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    @RequestMapping("queryEvaluation.do")
    public void queryEvaluation(HttpServletResponse response, String type, String beginDate, String endDate){
        logger.info("查询评价");
        Map responseMap = new HashMap();
        try{
            List<EvaluationInfo> evaluationList = centerService.queryEvaluation(type, beginDate, endDate);
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", evaluationList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * <p>Discription:[查询急救车]</p>
     * @param response
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    @RequestMapping("queryAmbulance.do")
    public void queryAmbulance(HttpServletResponse response){
        logger.info("查询急救车");
        Map responseMap = new HashMap();
        try{
            List<CusBasicInfo> basicInfoList = centerService.queryAmbulance();
            responseMap.put("success", true);
            responseMap.put("msg", "查询成功！");
            responseMap.put("data", basicInfoList);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "查询失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    
    /**
     * <p>Discription:[派任务]</p>
     * @param response
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    @RequestMapping("assignmentAmbulance.do")
    public void assignmentAmbulance(HttpServletResponse response, Long ambulanceId, 
            Long alarmId, String mobileNo){
        logger.info("急救车派任务");
        Map responseMap = new HashMap();
        try{
            centerService.doAssignmentAmbulance(ambulanceId, alarmId, mobileNo);
            responseMap.put("success", true);
            responseMap.put("msg", "任务派送成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "任务派送失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * confirmAmount 确认认证金额
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param ambulanceId
     * @param alarmId
     * @param mobileNo 
     * void
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("confirmAmount.do")
    public void confirmAmount(HttpServletResponse response, String amount, String mobileNo){
        logger.info("确认认证金额");
        Map responseMap = new HashMap();
        try{
            centerService.doConfirmAmount(mobileNo, amount);
            responseMap.put("success", true);
            responseMap.put("msg", "确认认证金额成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "确认认证金额失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * recharge 充值
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param recharge 
     * void
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("recharge.do")
    public void recharge(HttpServletResponse response, TblRechargeTurnover recharge){
        logger.info("充值记录");
        Map responseMap = new HashMap();
        try{
            if(recharge == null){
                throw new Exception("充值记录为空！");
            }
            centerService.doRecharge(recharge);
            responseMap.put("success", true);
            responseMap.put("msg", "充值记录保存成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "充值记录保存失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
    /**
     * rechargeRecords 充值记录
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param recharge 
     * void
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("rechargeRecords.do")
    public void rechargeRecords(HttpServletResponse response, TblRechargeTurnover recharge){
        logger.info("查询充值记录");
        Map responseMap = new HashMap();
        try{
            if(recharge == null || recharge.getCustMobile() == null || "".equals(recharge.getCustMobile())){
                throw new Exception("查询用户为空！");
            }
            List<TblRechargeTurnover> records = centerService.doRechargeRecords(recharge);
            responseMap.put("success", true);
            responseMap.put("msg", "充值记录查询成功！");
            responseMap.put("data", records);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap.put("success", false);
            responseMap.put("msg", "充值记录查询失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
}
