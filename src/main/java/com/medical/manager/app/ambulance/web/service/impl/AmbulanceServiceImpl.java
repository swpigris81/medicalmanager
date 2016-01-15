/**
 * 
 */
package com.medical.manager.app.ambulance.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.app.ambulance.web.dao.IAmbulanceInfoVoDao;
import com.medical.manager.app.ambulance.web.dao.ITblEvaluateInfoDao;
import com.medical.manager.app.ambulance.web.dao.ITblMessageCenterDao;
import com.medical.manager.app.ambulance.web.dao.ITblReservePlanDao;
import com.medical.manager.app.ambulance.web.dao.TblAlarmTaskDao;
import com.medical.manager.app.ambulance.web.model.TblAlarmTask;
import com.medical.manager.app.ambulance.web.model.TblEvaluateInfo;
import com.medical.manager.app.ambulance.web.model.TblMessageCenter;
import com.medical.manager.app.ambulance.web.model.TblReservePlan;
import com.medical.manager.app.ambulance.web.query.TblMessageCenterExample;
import com.medical.manager.app.ambulance.web.service.IAmbulanceService;
import com.medical.manager.app.ambulance.web.vo.AmbulanceInfoVo;
import com.medical.manager.app.center.web.dao.TblAlarmInfoDao;
import com.medical.manager.app.center.web.model.TblAlarmInfo;
import com.medical.manager.app.common.web.service.ITblFundBalanceService;
import com.medical.manager.authentication.web.dao.ITblCusBasicExpandDao;
import com.medical.manager.authentication.web.dao.ITblCusPhoneChangeApplyDao;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.authentication.web.model.TblCusPhoneChangeApply;
import com.medical.manager.authentication.web.query.TblCusBasicExpandExample;
import com.medical.manager.authentication.web.query.TblCusBasicExpandExample.Criteria;
import com.medical.manager.authentication.web.query.TblCusPhoneChangeApplyExample;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.DistanceUtil;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.customer.web.dao.ICusBasicInfoDao;
import com.medical.manager.customer.web.dao.ICusInvitationDao;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.model.TblCusInvitation;
import com.medical.manager.funds.web.dao.ITblRechargeTurnoverDao;
import com.medical.manager.funds.web.model.TblRechargeTurnover;
import com.medical.manager.systemset.web.dao.ITblSysInfoDao;
import com.medical.manager.systemset.web.model.TblSysInfo;
import com.medical.manager.systemset.web.query.TblSysInfoExample;

/**
 * @author jason
 * 
 * 2015-12-16 上午10:40:26
 * 
 * @version 1.0.0
 */
@Transactional
@Service("ambulanceService")
public class AmbulanceServiceImpl implements IAmbulanceService {
	
	private Logger log = Logger.getLogger(AmbulanceServiceImpl.class);

	@Resource
	private TblAlarmInfoDao alarmInfoDao;
    @Resource
    private TblAlarmTaskDao tblAlarmTaskDao;
    @Resource
    private ITblReservePlanDao tblReservePlanDao;
    @Resource
    private IAmbulanceInfoVoDao ambulanceInfoVoDao;
    @Resource
    private ITblCusBasicExpandDao cusBasicExpandDao;
    @Resource
    private ITblRechargeTurnoverDao rechargeTurnoverDao;
    @Resource
    private ITblEvaluateInfoDao tblEvaluateInfoDao;
    @Resource
    private ITblCusPhoneChangeApplyDao cusPhoneChangeApplyDao;
    @Resource
    private ITblSysInfoDao tblSysInfoDao;
    @Resource
    private ITblMessageCenterDao tblMessageCenterDao;
    @Resource
    private ICusBasicInfoDao cusBasicInfoDao;
    @Resource
    private ICusInvitationDao invitationDao;
    @Resource
    private ITblFundBalanceService tblFundBalanceService;

    /** 两经纬度间距离小于等于3km，则算作附近 **/
	private static final double NEAR_DISTANCE = 3;

    @Override
    public List<TblAlarmTask> queryAlarmTaskList(Long cusId) {
        return this.tblAlarmTaskDao.queryAlarmTaskList(cusId);
    }

    @Override
    public TblAlarmInfo queryAlarmInfo(Long alarmId) {
        return this.alarmInfoDao.findById(alarmId);
    }

    @Override
    public TblReservePlan queryReservePlan(Long cusId) {
        return this.tblReservePlanDao.selectByPrimaryKey(cusId);
    }

    @Override
    public void editReservePlan(TblReservePlan reservePlan) {
        reservePlan.setEditTime(DateUtil.fmtDateToStr("yyyy年MM月dd日 HH:mm"));
        reservePlan.setCheckStatus(CommonEnums.CheckStatus.AUDIT.id);
        this.tblReservePlanDao.updateByPrimaryKeySelective(reservePlan);
    }

    @Override
    public void saveReservePlan(TblReservePlan reservePlan) {
        reservePlan.setCheckStatus(CommonEnums.CheckStatus.AUDIT.id);
        reservePlan.setEditTime(DateUtil.fmtDateToStr("yyyy年MM月dd日 HH:mm"));
        this.tblReservePlanDao.insert(reservePlan);
    }

    @Override
    public void receiveAlarmTask(TblAlarmTask tblAlarmTask) {
        tblAlarmTask.setTaskStatus(CommonEnums.TaskStatus.RECEIVED.id);
        tblAlarmTask.setTaskTime(DateUtil.getNowTimes());
        this.tblAlarmTaskDao.updateByPrimaryKeySelective(tblAlarmTask);
    }

    @Override
    public void dealAlarmTask(TblAlarmTask tblAlarmTask) {
        tblAlarmTask.setDealTime(DateUtil.getNowTimes());
        this.tblAlarmTaskDao.updateByPrimaryKeySelective(tblAlarmTask);
    }

    @Override
    public List<TblAlarmInfo> queryCompleteAlarmTaskList(Long cusId, String taskStatus, String beginDate,
            String endDate) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cusId", cusId);
        paramMap.put("taskStatus", taskStatus);
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        return this.alarmInfoDao.queryCompleteAlarmTaskList(paramMap);
    }

    @Override
    public void doSubmitAptitude(TblCusBasicExpand expand) {
        expand.setCusId(expand.getSubmitCustId());
        expand.setSubmiTime(DateUtil.getNowDate() + DateUtil.getNowTime());
        TblCusBasicExpandExample example = new TblCusBasicExpandExample();
        Criteria criteria = example.createCriteria();
        criteria.andCusIdEqualTo(expand.getCusId());
        List<TblCusBasicExpand> expands = this.cusBasicExpandDao.selectByExample(example);
        if (expands != null && expands.size() > 0) {
            expand.setId(expands.get(0).getId());
            this.cusBasicExpandDao.updateByKeySelective(expand);
        } else {
            this.cusBasicExpandDao.insert(expand);
        }

    }

    @Override
    public TblRechargeTurnover findById(Long cusId) {
        return this.rechargeTurnoverDao.findById(cusId);
    }

    @Override
    public Map<String, String> countEvaluate(Long cusId) {
        return this.tblEvaluateInfoDao.countEvaluate(cusId);
    }

    @Override
    public List<TblEvaluateInfo> queryEvaluateList(Long cusId) {
        return this.tblEvaluateInfoDao.queryEvaluateList(cusId);
    }

    @Override
    public List<TblRechargeTurnover> queryRechargeRecordList(Long cusId, String beginDate, String endDate) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cusId", cusId.toString());
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        return this.rechargeTurnoverDao.findRechargeTurnover(paramMap);
    }

    @Override
    public void changePhoneNumber(TblCusPhoneChangeApply cusPhoneChangeApply) {
        cusPhoneChangeApply.setApplyStatus(CommonEnums.CheckStatus.AUDIT.id);
        cusPhoneChangeApply.setApplyTime(DateUtil.getNowTimes());
        TblCusPhoneChangeApplyExample example=new TblCusPhoneChangeApplyExample();
        com.medical.manager.authentication.web.query.TblCusPhoneChangeApplyExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(Long.valueOf(cusPhoneChangeApply.getUserId()));
        List<TblCusPhoneChangeApply> changeApplies=this.cusPhoneChangeApplyDao.selectByExample(example);
        if (changeApplies==null || changeApplies.size()==0) {
            CusBasicInfo basicInfo=this.cusBasicInfoDao.queryById(cusPhoneChangeApply.getUserId());
            cusPhoneChangeApply.setOriginalPhone(basicInfo.getPhone());
            cusPhoneChangeApply.setUserName(basicInfo.getNickname());
            cusPhoneChangeApply.setRealName(basicInfo.getRealName());
            this.cusPhoneChangeApplyDao.insert(cusPhoneChangeApply);
        }else {
            cusPhoneChangeApply.setId(changeApplies.get(0).getId());
            this.cusPhoneChangeApplyDao.updateByPrimaryKeySelective(cusPhoneChangeApply);
        }
    }

    @Override
    public TblSysInfo querySysInfoByType(String type) {
        TblSysInfoExample example = new TblSysInfoExample();
        com.medical.manager.systemset.web.query.TblSysInfoExample.Criteria criteria = example.createCriteria();
        criteria.andParamTyleEqualTo(type);
        List<TblSysInfo> sysInfos = this.tblSysInfoDao.selectByExample(example);
        if (sysInfos != null && sysInfos.size() > 0) {
            return sysInfos.get(0);
        }
        return null;
    }

    @Override
    public void submitRecharge(TblRechargeTurnover rechargeTurnover) {
        List<TblRechargeTurnover> rechargeTurnovers = this.queryRechargeRecordList(NumberUtils.toLong(rechargeTurnover.getCustId(),0),
                null, null);
        int expireDays=0;
        TblSysInfo sysInfo =null;
        if (CommonEnums.RechargeType.CENTER120.id.equals(rechargeTurnover.getRechargeType())) {
            //TODO 120中心
            TblCusBasicExpand basicExpand=this.queryCusBasicExpand(NumberUtils.toLong(rechargeTurnover.getCustId(),0));
            if (basicExpand!=null) {
                if (CommonEnums.TollLevel.PROVINCE.id.equals(basicExpand.getTollLevel())) {
                    sysInfo=this.querySysInfoByType(CommonEnums.SystemParamType.A120_PROVINCE_MONTH_USE_COST.id);
                }
                if (CommonEnums.TollLevel.CITY.id.equals(basicExpand.getTollLevel())) {
                    sysInfo=this.querySysInfoByType(CommonEnums.SystemParamType.A120_CITY_MONTH_USE_COST.id);
                }
                if (CommonEnums.TollLevel.COUNTY.id.equals(basicExpand.getTollLevel())) {
                    sysInfo=this.querySysInfoByType(CommonEnums.SystemParamType.A120_COUNTY_MONTH_USE_COST.id);
                }
            }
        }else if (CommonEnums.CustomType.OLD_PERSON.id.equals(rechargeTurnover.getRechargeType())) {
            sysInfo=this.querySysInfoByType(CommonEnums.CustomType.OLD_PERSON.id);
        }else {
            sysInfo = this.querySysInfoByType(CommonEnums.SystemParamType.AMBULANCE_MONTH_USE_COST.id);
        }
        int freeMonth = rechargeTurnover.getRechargeMonth() / Integer.valueOf(sysInfo.getRecharge())
                * Integer.valueOf(sysInfo.getFree());
        //按照一个月30天计算
        expireDays=(freeMonth+rechargeTurnover.getRechargeMonth())*30;
        if (rechargeTurnovers==null || rechargeTurnovers.size()==0) {
            if (CommonEnums.CustomType.OLD_PERSON.id.equals(rechargeTurnover.getRechargeType())) {
                sysInfo=this.querySysInfoByType(CommonEnums.SystemParamType.OLD_REGISTER_SEND.id);
            }else {
                sysInfo=this.querySysInfoByType(CommonEnums.SystemParamType.NEW_CUSTOMER_SEND.id);
            }
            expireDays+=Integer.valueOf(sysInfo.getParamValue());
        }
        CusBasicInfo cusBasicInfo=this.cusBasicInfoDao.queryById(NumberUtils.toLong(rechargeTurnover.getCustId(), 0));
        String expireDate=DateUtil.fmtDateToStr(DateUtil.addDay(new Date(), expireDays),"yyyyMMdd");
        rechargeTurnover.setExpireDate(expireDate);
        rechargeTurnover.setRechargeTime(DateUtil.getNowTimes());
        rechargeTurnover.setCustName(cusBasicInfo.getRealName());
        rechargeTurnover.setRechargeStatus(CommonEnums.RechargeStatus.SUCCESS.id);
        rechargeTurnover.setRechargeMode("00");
        // 不管邀请返现是否成功，不能影响充值正常流程
        try {
			updateInvitation(rechargeTurnover);
		} catch (Exception e) {
			log.error("120中心或者急救车充值给邀请人返现操作异常", e);
		}
        this.rechargeTurnoverDao.insert(rechargeTurnover);
    }
    
    /**
     * 更新邀请返现信息，并做相关处理
     *
     * @date 2015年12月23日 上午11:48:57
     * @param rechargeTurnover
     * void
     */
    private void updateInvitation(TblRechargeTurnover rechargeTurnover) {
    	
    	Long cusId = Long.valueOf(rechargeTurnover.getCustId());
    	
    	// 查询受邀请的用户是否已经给邀请人返现
    	TblCusInvitation invitation = invitationDao.queryBackCashByCusId(cusId);
		if (invitation == null || invitation.getIsBackCashFlag())
			return;
		
		TblSysInfo sInfo = tblSysInfoDao.queryBack4AmbOr120ByCusId(cusId);
		if (sInfo == null)
			return;
		
		// 查询客户累计成功充值金额
		Long sumRecharge = rechargeTurnoverDao.querySumRecharge(cusId);
		// 查询急救车/120中心被邀请人充值多少给邀请人返现参数值
		Long paramValue = Long.valueOf(sInfo.getParamValue());
		// 达到返现条件，更新邀请信息，并给邀请者增加相应的余额
		if (sumRecharge >= paramValue) {
			invitation.setBackCashFlag(CommonEnums.YesOrNo.YES.id);
			invitation.setBackCashFlag(DateUtil.fmtDateToYMDHMS(new Date()));
			invitationDao.updateBackCashByCusId(invitation);
			tblFundBalanceService.changeBalance(cusId, new BigDecimal(
					paramValue), "0", "被推荐人充值后返现", CommonEnums.YesOrNo.YES.id);
		}
    }

    @Override
    public int countMessage(Long cusId) {
        TblMessageCenterExample example=new TblMessageCenterExample();
        com.medical.manager.app.ambulance.web.query.TblMessageCenterExample.Criteria criteria=example.createCriteria();
        criteria.andCusIdEqualTo(cusId);
        criteria.andIsReadEqualTo(CommonEnums.YesOrNo.NO.id);
        return this.tblMessageCenterDao.countByExample(example);
    }

    @Override
    public List<TblMessageCenter> queryMessageList(Long cusId) {
        TblMessageCenterExample example=new TblMessageCenterExample();
        com.medical.manager.app.ambulance.web.query.TblMessageCenterExample.Criteria criteria=example.createCriteria();
        criteria.andCusIdEqualTo(cusId);
        criteria.andIsReadEqualTo(CommonEnums.YesOrNo.NO.id);
        example.setOrderByClause("MESSAGE_TIME");
        return this.tblMessageCenterDao.selectByExample(example);
    }

    @Override
    public TblCusBasicExpand queryCusBasicExpand(Long cusId) {
        TblCusBasicExpandExample example=new TblCusBasicExpandExample();
        Criteria criteria=example.createCriteria();
        criteria.andCusIdEqualTo(cusId);
        List<TblCusBasicExpand> basicExpands=this.cusBasicExpandDao.selectByExample(example);
        if (basicExpands!=null && basicExpands.size()>0) {
            return basicExpands.get(0);
        }
        return null;
    }
	/* (non-Javadoc)
	 * @see com.medical.manager.app.ambulance.web.service.IAmbulanceService#queryAmbulanceInfo(java.lang.String)
	 */
	@Override
	public List<AmbulanceInfoVo> queryNearAmbulance(final double longitude,
			final double latitude) throws Exception {
		// 查询所有急救车
		List<AmbulanceInfoVo> _infoVos = ambulanceInfoVoDao.queryNearAmbulance(null);
		List<AmbulanceInfoVo> _retInfoVos = new ArrayList<AmbulanceInfoVo>();
		if (_infoVos != null && !_infoVos.isEmpty()) {
			for (AmbulanceInfoVo info : _infoVos) {
				String _longitude = info.getLongitude();
				String _latitude = info.getLatitude();
				try {
					if (!StringUtil.isEmpty(_longitude)
							&& !StringUtil.isEmpty(_latitude)) {
						double longitude2 = NumberUtils.toDouble(_longitude);
						double latitude2 = NumberUtils.toDouble(_latitude);
						double actualDistance = DistanceUtil.GetDistance(
								longitude, latitude, longitude2, latitude2);
						if (actualDistance <= NEAR_DISTANCE) {
							_retInfoVos.add(info);
						}
					}
				} catch (Exception e) {
					log.error("急救车经纬度格式不对，转换异常！急救车id=" + info.getCusId(), e);
				}
			}
		}
		return _retInfoVos;
	}
}
