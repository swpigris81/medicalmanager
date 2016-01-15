/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.model
 * TblCusDevice.java
 * 
 * 2015年12月16日-下午12:00:57
 * 2015版权所有
 * 
 */
package com.medical.manager.app.relatives.web.model;

import java.io.Serializable;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月16日 下午12:00:57
 * @author Json
 * @version 1.0.0
 */
public class TblCusDevice implements Serializable {

	/**  **/
	private static final long serialVersionUID = -4632006341277177298L;
	/** 自增主键 **/
	private Long id;
	/** 设备编号 **/
	private String device_no;
	/** 机器码id（非机器码） **/
	private Long machine_code_id;

	/** 开通标识（Y开通，N-未开通） **/
	private String open_flag;
	/** 插入标识（Y插入，N-未插入） **/
	private String insert_flag;
	/** 报警标识（Y报警，N-未报警） **/
	private String alarm_flag;

	/** 开通时间 **/
	private String open_time;
	/** 使用人ID **/
	private Long use_id;
	/** 开通人 **/
	private Long open_id;
	/** 备注 **/
	private String remark;

	public TblCusDevice() {
	}

	public TblCusDevice(String device_no, Long machine_code_id,
			String open_flag, String insert_flag, String alarm_flag,
			Long use_id, Long open_id, String open_time) {
		this.device_no = device_no;
		this.machine_code_id = machine_code_id;
		this.open_flag = open_flag;
		this.insert_flag = insert_flag;
		this.alarm_flag = alarm_flag;
		this.use_id = use_id;
		this.open_id = open_id;
		this.open_time = open_time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public Long getMachine_code_id() {
		return machine_code_id;
	}

	public void setMachine_code_id(Long machine_code_id) {
		this.machine_code_id = machine_code_id;
	}

	public String getOpen_flag() {
		return open_flag;
	}

	public void setOpen_flag(String open_flag) {
		this.open_flag = open_flag;
	}

	public String getInsert_flag() {
		return insert_flag;
	}

	public void setInsert_flag(String insert_flag) {
		this.insert_flag = insert_flag;
	}

	public String getAlarm_flag() {
		return alarm_flag;
	}

	public void setAlarm_flag(String alarm_flag) {
		this.alarm_flag = alarm_flag;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public Long getUse_id() {
		return use_id;
	}

	public void setUse_id(Long use_id) {
		this.use_id = use_id;
	}

	public Long getOpen_id() {
		return open_id;
	}

	public void setOpen_id(Long open_id) {
		this.open_id = open_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
