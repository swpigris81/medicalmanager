/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月14日
 */
package com.medical.manager.app.center.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.app.center.web.model.TblAlarmInfo;
import com.medical.manager.app.center.web.model.TblCenterPoliceInfo;
import com.medical.manager.app.center.web.model.TblCustMedicalRecords;
import com.medical.manager.app.center.web.model.TblScheduingInfo;
import com.medical.manager.app.center.web.vo.EvaluationInfo;
import com.medical.manager.app.relatives.web.model.TblCustRelation;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.funds.web.model.TblRechargeTurnover;

/**
 * <p>Discription:[]</p>
 * @author: Jason
 * @update: 2015年12月14日 Jason[变更描述]
 */
public interface ICenterService {
    /**
     * <p>Discription:[关键字查询120中心信息]</p>
     * @param keyWord
     * @return
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    List<TblCusBasicExpand> queryCenterList(String keyWord);
    /**
     * <p>Discription:[接警员登录]</p>
     * @param mobileNo 手机号
     * @param password 密码
     * @param centerId 120中心ID
     * @return 登录是否成功
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    TblCenterPoliceInfo doPoliceLogin(String mobileNo, String password, Long centerId, String ip);
    
    /**
     * <p>Discription:[管理员登录]</p>
     * @param mobileNo 手机号
     * @param password 密码
     * @return 登录是否成功
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    TblCenterPoliceInfo doManagerLogin(String mobileNo, String password, String ip);
    /**
     * <p>Discription:[找回密码，重置密码]</p>
     * @param mobileNo 手机号
     * @param password 新密码
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    void doFindPassword(String mobileNo, String password);
    /**
     * <p>Discription:[120中心管理员注册]</p>
     * @param mobileNo 手机号
     * @param password 密码
     * @param inviteNo 邀请号
     * @return
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    TblCenterPoliceInfo doManagerRegister(String mobileNo, String password, String inviteNo);
    /**
     * doReceiveAlarm 新增报警信息
     * (这里描述这个方法适用条件 – 可选)
     * @param alarmInfo 
     * @exception 
     * @since  1.0.0
     */
    void doReceiveAlarm(TblAlarmInfo alarmInfo);
    
    /**
     * queryAlarm 查询报警信息
     * (这里描述这个方法适用条件 – 可选)
     * @param alarmInfo 
     * @exception 
     * @since  1.0.0
     */
    List<TblAlarmInfo> queryAlarm(TblAlarmInfo alarmInfo);
    /**
     * doUpdateAlarm 修改报警信息
     * (这里描述这个方法适用条件 – 可选)
     * @param alarmInfo 
     * @exception 
     * @since  1.0.0
     */
    void doUpdateAlarm(TblAlarmInfo alarmInfo);
    /**
     * queryMedicalRecords 查询用户病历
     * (这里描述这个方法适用条件 – 可选)
     * @param mobileNo 手机号
     * @return List<TblCustMedicalRecords>
     * @exception 
     * @since  1.0.0
     */
    List<TblCustMedicalRecords> queryMedicalRecords(String mobileNo);
    /**
     * queryRelationList 查询用户的关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param mobileNo 手机号
     * @return List<TblCustRelation>
     * @exception 
     * @since  1.0.0
     */
    List<TblCustRelation> queryRelationList(Map paramMap);
    /**
     * doModifyRelation 修改关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    void doModifyRelation(TblCustRelation relation);
    /**
     * doDeleteRelation 删除关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    void doDeleteRelation(TblCustRelation relation);
    /**
     * doAddRelation 新增关系人
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    void doAddRelation(TblCustRelation relation) throws Exception;
    /**
     * doCheckRelation 审核关系人申请请求
     * (这里描述这个方法适用条件 – 可选)
     * @param relation 
     * @exception 
     * @since  1.0.0
     */
    void doCheckRelation(TblCustRelation relation);
    /**
     * <p>Discription:[添加员工]</p>
     * @param policeInfo 员工信息
     * @param mobile 当前登录用户的手机号
     * @author: Jason
     * @update: 2015年12月15日 Jason[变更描述]
     */
    void doAddEmployee(TblCenterPoliceInfo policeInfo, String mobile);
    /**
     * <p>Discription:[提交120中心资质信息]</p>
     * @param expand
     * @author: Jason
     * @update: 2015年12月15日 Jason[变更描述]
     */
    void doSubmitAptitude(TblCusBasicExpand expand);
    /**
     * 查询客户基本信息
     * (这里描述这个方法适用条件 – 可选)
     * @param relationMobile
     * @return
     * @exception
     * @since 1.0.0
     */
    CusBasicInfo queryCusBasicInfo(String relationMobile);
    /**
     * <p>Discription:[查询调度信息]</p>
     * @param scheduingInfo
     * @return
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    List<TblScheduingInfo> queryScheduling(TblScheduingInfo scheduingInfo);
    /**
     * <p>Discription:[评价查询]</p>
     * @param type 类型
     * @param beginDate 评价时间
     * @param endDate 评价时间
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    List<EvaluationInfo> queryEvaluation(String type, String beginDate, String endDate);
    /**
     * <p>Discription:[查询急救车]</p>
     * @return
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    List<CusBasicInfo> queryAmbulance();
    /**
     * <p>Discription:[派送任务]</p>
     * @param ambulanceId 急救车ID
     * @param mobileNo 当前登录用户的手机号
     * @param alarmId 报警ID
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    void doAssignmentAmbulance(Long ambulanceId, Long alarmId, String mobileNo);
    /**
     * doConfirmAmount 确认认证金额
     * (这里描述这个方法适用条件 – 可选)
     * @param mobileNo
     * @param amount 
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    void doConfirmAmount(String mobileNo, String amount) throws Exception;
    /**
     * doRecharge 充值记录
     * (这里描述这个方法适用条件 – 可选)
     * @param recharge
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    void doRecharge(TblRechargeTurnover recharge) throws Exception;
    /**
     * doRechargeRecords 查询充值记录
     * (这里描述这个方法适用条件 – 可选)
     * @param recharge 查询条件
     * @return List<TblRechargeTurnover>
     * @exception 
     * @since  1.0.0
     */
    List<TblRechargeTurnover> doRechargeRecords(TblRechargeTurnover recharge);
    /**
     * queryCenterList 查询120中心
     * (这里描述这个方法适用条件 – 可选)
     * @param searchVo
     * @return List<CusBasicInfo>
     * @exception 
     * @since  1.0.0
     */
    List<CusBasicInfo> queryCenterList(CusBasicInfo searchVo);
    
    
}
