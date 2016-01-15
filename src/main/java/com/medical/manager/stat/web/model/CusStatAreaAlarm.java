/**
 * 系统项目名称
 * com.medical.manager.stat.web.model
 * StatAreaAlarm.java
 * 
 * 2015年12月8日-上午11:31:24
 * 2015版权所有
 * 
 */
package com.medical.manager.stat.web.model;

import java.io.Serializable;

/**
 * @description 辖区报警数据统计
 *
 * @time 2015年12月8日 上午11:31:24
 * @author Jason
 * @version 1.0.0
 */
public class CusStatAreaAlarm implements Serializable {
	/**  **/
	private static final long serialVersionUID = -8747342209990478607L;
	/** 主键 **/
	private Long id;
	/** 报警中心 **/
	private String alarmCenter;
	/** 报警时间 **/
	private String alarmTime;
	/** 调度员 **/
	private String scheduleUser;
	/** 电话号码 **/
	private String phone;
	/** 通话时长 **/
	private String callDuration;
	/** 调度车辆 **/
	private String scheduleVehicle;
	/** 病情类型 **/
	private String diseaseType;
	/** 收治医院 **/
	private String hospital;
	/** 跟踪情况 **/
	private String trackInfo;
	/** 备注 **/
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}

	public String getAlarmCenter() {
		return alarmCenter;
	}

	public void setAlarmCenter(String alarmCenter) {
		this.alarmCenter = alarmCenter;
	}

	public String getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getScheduleUser() {
		return scheduleUser;
	}

	public void setScheduleUser(String scheduleUser) {
		this.scheduleUser = scheduleUser;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCallDuration() {
		return callDuration;
	}

	public String getScheduleVehicle() {
		return scheduleVehicle;
	}

	public void setScheduleVehicle(String scheduleVehicle) {
		this.scheduleVehicle = scheduleVehicle;
	}

	public String getDiseaseType() {
		return diseaseType;
	}

	public void setDiseaseType(String diseaseType) {
		this.diseaseType = diseaseType;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getTrackInfo() {
		return trackInfo;
	}

	public void setTrackInfo(String trackInfo) {
		this.trackInfo = trackInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
