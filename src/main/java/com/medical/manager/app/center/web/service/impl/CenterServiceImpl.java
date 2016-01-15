/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月14日
 */
package com.medical.manager.app.center.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.ambulance.web.dao.ITblEvaluateInfoDao;
import com.medical.manager.app.ambulance.web.dao.TblAlarmTaskDao;
import com.medical.manager.app.ambulance.web.model.TblAlarmTask;
import com.medical.manager.app.center.web.dao.TblAlarmInfoDao;
import com.medical.manager.app.center.web.dao.TblCenterPoliceInfoDao;
import com.medical.manager.app.center.web.dao.TblMedicalRecordsDao;
import com.medical.manager.app.center.web.dao.TblScheduingInfoDao;
import com.medical.manager.app.center.web.model.TblAlarmInfo;
import com.medical.manager.app.center.web.model.TblCenterPoliceInfo;
import com.medical.manager.app.center.web.model.TblCustMedicalRecords;
import com.medical.manager.app.center.web.model.TblScheduingInfo;
import com.medical.manager.app.center.web.service.ICenterService;
import com.medical.manager.app.center.web.vo.EvaluationInfo;
import com.medical.manager.app.relatives.web.dao.TblCustRelationDao;
import com.medical.manager.app.relatives.web.model.TblCustRelation;
import com.medical.manager.app.relatives.web.service.ITblCusRelationService;
import com.medical.manager.authentication.web.dao.ITblCusBasicExpandDao;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.MD5Util;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.customer.web.dao.ICusBasicInfoDao;
import com.medical.manager.customer.web.dao.ICusInvitationDao;
import com.medical.manager.customer.web.dao.ITblCusLoginSeqDao;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.model.TblCusInvitation;
import com.medical.manager.customer.web.model.TblCusLoginSeq;
import com.medical.manager.funds.web.dao.ITblRechargeTurnoverDao;
import com.medical.manager.funds.web.model.TblRechargeTurnover;

/**
 * <p>Discription:[120中心服务]</p>
 * @author: Jason
 * @update: 2015年12月14日 Jason[变更描述]
 */
@Transactional
@Service("centerService")
public class CenterServiceImpl implements ICenterService {
    private Logger logger = Logger.getLogger(CenterServiceImpl.class);
    @Resource
    private TblCenterPoliceInfoDao centerPoliceInfoDao;
    @Resource
    private ICusBasicInfoDao cusBasicInfoDao;
    @Resource
    private ICusInvitationDao invitationDao;
    @Resource
    private ITblCusLoginSeqDao cusLoginSeqDao;
    @Resource
    private TblAlarmInfoDao alarmInfoDao;
    @Resource
    private TblMedicalRecordsDao medicalRecordsDao;
    @Resource
    private TblCustRelationDao custRelationDao;
    @Resource
    private ITblCusBasicExpandDao cusBasicExpandDao;
    @Resource
    private TblScheduingInfoDao scheduingInfoDao;
    @Resource
    private ITblEvaluateInfoDao tblEvaluateInfoDao;
    @Resource
    private TblAlarmTaskDao tblAlarmTaskDao;
    @Resource
    private ITblCusRelationService tblCusRelationService;
    @Resource
    private ITblRechargeTurnoverDao rechargeTurnoverDao;
    
    public List<TblCusBasicExpand> queryCenterList(String keyWord){
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("keyword", keyWord);
        paramMap.put("cusType", CommonEnums.CustomType.A120.id);
        return cusBasicInfoDao.queryCenterList(paramMap);
    }

    @Override
    public TblCenterPoliceInfo doPoliceLogin(String mobileNo, String password, Long centerId, String ip) {
        Map paramMap = new HashMap();
        paramMap.put("mobileNo", mobileNo);
        paramMap.put("certenId", centerId);
        paramMap.put("password", MD5Util.md5Encode(password));
        List<TblCenterPoliceInfo> infoList = centerPoliceInfoDao.findPoliceLogin(paramMap);
        if(infoList != null && !infoList.isEmpty()){
            TblCenterPoliceInfo policeInfo = infoList.get(0);
            TblCusLoginSeq seq = new TblCusLoginSeq();
            seq.setCusId(new BigDecimal(policeInfo.getId()));
            seq.setLoginIp(ip);
            seq.setLoginTime(DateUtil.getNowDate() + DateUtil.getNowTime());
            seq.setRemark("接警员登录");
            return policeInfo;
        }
        return null;
    }

    @Override
    public TblCenterPoliceInfo doManagerLogin(String mobileNo, String password, String ip) {
        Map paramMap = new HashMap();
        paramMap.put("mobileNo", mobileNo);
        paramMap.put("password", MD5Util.md5Encode(password));
        List<TblCenterPoliceInfo> infoList = centerPoliceInfoDao.findPoliceLogin(paramMap);
        if(infoList != null && !infoList.isEmpty()){
            TblCenterPoliceInfo policeInfo = infoList.get(0);
            if(policeInfo != null){
                if(CommonEnums.PoliceType.MANAGER.id.equals(policeInfo.getUserType()) 
                        || CommonEnums.PoliceType.SUPER_MANAGER.id.equals(policeInfo.getUserType())){
                    TblCusLoginSeq seq = new TblCusLoginSeq();
                    seq.setCusId(new BigDecimal(policeInfo.getId()));
                    seq.setLoginIp(ip);
                    seq.setLoginTime(DateUtil.getNowDate() + DateUtil.getNowTime());
                    seq.setRemark("管理员登录");
                    return policeInfo;
                }
            }
        }
        return null;
    }

    @Override
    public void doFindPassword(String mobileNo, String password) {
        TblCenterPoliceInfo policeInfo = new TblCenterPoliceInfo();
        policeInfo.setPassword(MD5Util.md5Encode(password));
        policeInfo.setMobileNo(mobileNo);
        centerPoliceInfoDao.updatePolice(policeInfo);
    }

    @Override
    public TblCenterPoliceInfo doManagerRegister(String mobileNo, String password, String inviteNo) {
        TblCenterPoliceInfo policeInfo = new TblCenterPoliceInfo();
        policeInfo.setPassword(MD5Util.md5Encode(password));
        policeInfo.setMobileNo(mobileNo);
        policeInfo.setUserType(CommonEnums.PoliceType.MANAGER.id);
        centerPoliceInfoDao.addNewCenterPoliceInfo(policeInfo);
        
        if(!StringUtil.isEmpty(inviteNo)){
            TblCusInvitation invitation = new TblCusInvitation();
            invitation.setCusId(policeInfo.getId());
            invitation.setInvitationCode(inviteNo);
            invitation.setRemark("管理员注册输入邀请码");
            invitationDao.addNewCusInvitation(invitation);
        }
        return policeInfo;
    }

    @Override
    public void doReceiveAlarm(TblAlarmInfo alarmInfo) {
        alarmInfoDao.addNewAlarmInfo(alarmInfo);
    }
    
    @Override
    public List<TblAlarmInfo> queryAlarm(TblAlarmInfo alarmInfo){
        try {
            return alarmInfoDao.findAlarmInfo(BeanUtils.describe(alarmInfo));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void doUpdateAlarm(TblAlarmInfo alarmInfo) {
        alarmInfoDao.updateAlarmInfo(alarmInfo);
    }

    @Override
    public List<TblCustMedicalRecords> queryMedicalRecords(String mobileNo) {
        Map paramMap = new HashMap();
        paramMap.put("mobileNo", mobileNo);
        return medicalRecordsDao.findMedicalRecords(paramMap);
    }

    @Override
    public List<TblCustRelation> queryRelationList(Map paramMap) {
        return custRelationDao.findCustRelation(paramMap);
    }

    @Override
    public void doModifyRelation(TblCustRelation relation) {
        custRelationDao.updateCustRelation(relation);
    }

    @Override
    public void doDeleteRelation(TblCustRelation relation) {
        custRelationDao.deleteRelation(relation.getId());
    }

    @Override
    public void doAddRelation(TblCustRelation relation) throws Exception {
        String relationMobile = relation.getRelationMobile();
        TblCenterPoliceInfo info = centerPoliceInfoDao.findByMobileNo(relationMobile);
        if(info == null){
            throw new Exception("要添加的关系人不存在！");
        }
        Map paramMap = new HashMap();
        paramMap.put("relationMobile", relationMobile);
        List<TblCustRelation> relationList = queryRelationList(paramMap);
        if(relationList != null && !relationList.isEmpty()){
            throw new Exception("您已添加过该用户未关系人！请勿重复添加！");
        }
        relation.setRelationMobile(relationMobile);
        relation.setRelationName(info.getName());
        relation.setRelationId(info.getId());
        tblCusRelationService.addNewCustRelation(relation);
    }

    @Override
    public void doCheckRelation(TblCustRelation relation) {
        custRelationDao.updateCustRelation(relation);
    }

    @Override
    public void doAddEmployee(TblCenterPoliceInfo policeInfo, String mobile) {
        TblCenterPoliceInfo info = centerPoliceInfoDao.findByMobileNo(mobile);
        policeInfo.setCertenId(info.getCertenId());
        policeInfo.setUserType(CommonEnums.PoliceType.POLICE.id);
        centerPoliceInfoDao.addNewCenterPoliceInfo(policeInfo);
    }

    @Override
    public void doSubmitAptitude(TblCusBasicExpand expand) {
        CusBasicInfo cusBasicInfo = new CusBasicInfo();
        cusBasicInfo.setCusType(CommonEnums.CustomType.A120.id);
        cusBasicInfo.setActivationFlag(CommonEnums.YesOrNo.NO.id);
        cusBasicInfo.setRegisterTime(DateUtil.getNowDate() + DateUtil.getNowTime());
        cusBasicInfo.setProvince(expand.getUseProvince());
        cusBasicInfo.setCity(expand.getUseCity());
        cusBasicInfo.setCounty(expand.getUseCounty());
        cusBasicInfo.setPhone(expand.getSubmitCustMobile());
        cusBasicInfo.setRealName(expand.getSubmitCustName());
        cusBasicInfo.setNickname("用户" + expand.getId());
        cusBasicInfoDao.insert(cusBasicInfo);
        expand.setCusId(cusBasicInfo.getId());
        expand.setSubmiTime(DateUtil.getNowDate() + DateUtil.getNowTime());
        cusBasicExpandDao.insert(expand);
        TblCenterPoliceInfo info = centerPoliceInfoDao.findByMobileNo(expand.getSubmitCustMobile());
        info.setCertenId(cusBasicInfo.getId());
        centerPoliceInfoDao.updatePolice(info);
    }

    @Override
    public CusBasicInfo queryCusBasicInfo(String relationMobile) {
        return this.cusBasicInfoDao.queryByMobileNo(relationMobile);
    }

    @Override
    public List<TblScheduingInfo> queryScheduling(TblScheduingInfo scheduingInfo) {
        try {
            return scheduingInfoDao.findScheduingInfo(BeanUtils.describe(scheduingInfo));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<EvaluationInfo> queryEvaluation(String type, String beginDate, String endDate) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("type", type);
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        return tblEvaluateInfoDao.queryEvaluateInfoList(paramMap);
    }

    @Override
    public List<CusBasicInfo> queryAmbulance() {
        Map paramMap = new HashMap();
        paramMap.put("cusType", "03");
        return cusBasicInfoDao.queryBasicInfoList(paramMap);
    }

    @Override
    public void doAssignmentAmbulance(Long ambulanceId, Long alarmId, String mobileNo) {
        CusBasicInfo custId = cusBasicInfoDao.queryByMobileNo(mobileNo);
        TblAlarmTask task = new TblAlarmTask();
        task.setAmbulanceId(ambulanceId);
        task.setCenterId(custId.getId());
        task.setAlarmId(alarmId);
        task.setDistributeTime(DateUtil.getNowDate() + DateUtil.getNowTime());
        task.setTaskStatus("00");
        task.setTaskReason("派发任务");
        tblAlarmTaskDao.insert(task);
    }

    @Override
    public void doConfirmAmount(String mobileNo, String amount) throws Exception {
        CusBasicInfo custInfo = cusBasicInfoDao.queryByMobileNo(mobileNo);
        List<TblCusBasicExpand> list = cusBasicExpandDao.selectByCustId(custInfo.getId());
        if(list != null && !list.isEmpty()){
            TblCusBasicExpand expand = list.get(0);
            if(expand.getAuthenticationAmount() != null && expand.getAuthenticationAmount().compareTo(new BigDecimal(amount)) == 0){
                
            }else{
                throw new Exception("认证金额输入错误！");
            }
        }
    }

    @Override
    public void doRecharge(TblRechargeTurnover recharge) throws Exception {
        CusBasicInfo custInfo = cusBasicInfoDao.queryByMobileNo(recharge.getCustMobile());
        recharge.setCustId(custInfo.getId().toString());
        recharge.setCustName(custInfo.getRealName());
        recharge.setExpireDate(DateUtil.fmtDateToStr(DateUtil.addMonth(DateUtil.fmtStrToDate(recharge.getRechargeTime(), "yyyyMMdd"), recharge.getRechargeMonth()), "yyyyMMdd"));
        rechargeTurnoverDao.insert(recharge);
    }

    @Override
    public List<TblRechargeTurnover> doRechargeRecords(TblRechargeTurnover recharge) {
        Map paramMap = new HashMap();
        paramMap.put("custMobile", recharge.getCustMobile());
        return rechargeTurnoverDao.findRechargeTurnover(paramMap);
    }

    @Override
    public List<CusBasicInfo> queryCenterList(CusBasicInfo searchVo) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cusType", CommonEnums.CustomType.A120.id);
        try {
            paramMap.putAll(BeanUtils.describe(searchVo));
            return cusBasicInfoDao.queryCenterListByParams(paramMap);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
