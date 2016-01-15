/**
 * 
 */
package com.medical.manager.app.ambulance.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.app.ambulance.web.model.TblAlarmTask;
import com.medical.manager.app.ambulance.web.model.TblEvaluateInfo;
import com.medical.manager.app.ambulance.web.model.TblMessageCenter;
import com.medical.manager.app.ambulance.web.model.TblReservePlan;
import com.medical.manager.app.ambulance.web.vo.AmbulanceInfoVo;
import com.medical.manager.app.center.web.model.TblAlarmInfo;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.authentication.web.model.TblCusPhoneChangeApply;
import com.medical.manager.funds.web.model.TblRechargeTurnover;
import com.medical.manager.systemset.web.model.TblSysInfo;

/**
 * @author jason
 *
 * 2015-12-16 上午10:39:48
 * 
 * @version 1.0.0
 */
public interface IAmbulanceService {

    /**
     * 查询报警任务列表
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblAlarmTask> queryAlarmTaskList(Long cusId);

    /**
     * 查询报警人详情
     * (这里描述这个方法适用条件 – 可选)
     * @param alarmId
     * @return
     * @exception
     * @since 1.0.0
     */
    TblAlarmInfo queryAlarmInfo(Long alarmId);

    /**
     * 查看预案
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    TblReservePlan queryReservePlan(Long cusId);

    /**
     * 编辑预案
     * (这里描述这个方法适用条件 – 可选)
     * @param reservePlan
     * @exception
     * @since 1.0.0
     */
    void editReservePlan(TblReservePlan reservePlan);

    /**
     * 保存预案
     * (这里描述这个方法适用条件 – 可选)
     * @param reservePlan
     * @exception
     * @since 1.0.0
     */
    void saveReservePlan(TblReservePlan reservePlan);

    /**
     * 接任务
     * (这里描述这个方法适用条件 – 可选)
     * @param tblAlarmTask
     * @exception
     * @since 1.0.0
     */
    void receiveAlarmTask(TblAlarmTask tblAlarmTask);

    /**
     * 处理任务
     * (这里描述这个方法适用条件 – 可选)
     * @param tblAlarmTask
     * @exception
     * @since 1.0.0
     */
    void dealAlarmTask(TblAlarmTask tblAlarmTask);

    /**
     * 查询任务记录
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @param taskStatus 
     * @param endDate 
     * @param beginDate 
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblAlarmInfo> queryCompleteAlarmTaskList(Long cusId, String taskStatus, String beginDate, String endDate);

    /**
     * 提交资质证书
     * (这里描述这个方法适用条件 – 可选)
     * @param expand
     * @exception
     * @since 1.0.0
     */
    void doSubmitAptitude(TblCusBasicExpand expand);

    /**
     * 查询用户充值流水
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    TblRechargeTurnover findById(Long cusId);

    /**
     * 查询评论总数以及评价平均值
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    Map<String, String> countEvaluate(Long cusId);

    /**
     * 查询评论列表
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblEvaluateInfo> queryEvaluateList(Long cusId);

    /**
     * 查询充值记录
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @param endDate 
     * @param beginDate 
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblRechargeTurnover> queryRechargeRecordList(Long cusId, String beginDate, String endDate);

    /**
     * 变更手机号
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @param phoneNumber
     * @exception
     * @since 1.0.0
     */
    void changePhoneNumber(TblCusPhoneChangeApply cusPhoneChangeApply);

    /**
     * 查询系统参数
     * (这里描述这个方法适用条件 – 可选)
     * @param id
     * @return
     * @exception
     * @since 1.0.0
     */
    TblSysInfo querySysInfoByType(String id);

    /**
     * 充值
     * (这里描述这个方法适用条件 – 可选)
     * @param rechargeTurnover
     * @exception
     * @since 1.0.0
     */
    void submitRecharge(TblRechargeTurnover rechargeTurnover);

    /**
     * 统计消息
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    int countMessage(Long cusId);

    /**
     * 查询消息列表
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblMessageCenter> queryMessageList(Long cusId);
    
    /**
     * 
     * 这里是方法描述
     *
     * @author Json
     * @date 2015年12月21日 下午3:17:45
     * @param abscissa	横坐标
     * @param ordinate	纵坐标
     * @return List<AmbulanceInfoVo>
     */
	List<AmbulanceInfoVo> queryNearAmbulance(final double longitude,
			final double latitude) throws Exception;

	/**
     * 查询客户扩展信息
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    TblCusBasicExpand queryCusBasicExpand(Long cusId);

}
