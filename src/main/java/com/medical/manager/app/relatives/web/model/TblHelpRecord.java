/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.model
 * TblHelpRecord.java
 * 
 * 2015年12月18日-下午3:35:41
 * 2015版权所有
 * 
 */
package com.medical.manager.app.relatives.web.model;

import java.io.Serializable;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月18日 下午3:35:41
 * @author Json
 * @version 1.0.0
 */
public class TblHelpRecord implements Serializable {
	/**  **/
	private static final long serialVersionUID = 1458007993961162025L;
	private Long id;
	/** 呼救方手机号码 **/
	private String cus_phone;
	/** 呼救方ID **/
	private Long cus_id;
	/** 救援方ID **/
	private Long rescue_id;
	/** 救援方名称（120中心或者医院） **/
	private String rescue_name;
	/** 急救车ID **/
	private Long ambulance_id;
	/** 急救车车牌号 **/
	private String ambulance_no;
	/** 救援方评价状态（Y已评价，N未评价） **/
	private String rescue_state;
	/** 急救车评价状态（Y已评价，N未评价） **/
	private String ambulance_state;
	/** 救援方评价id **/
	private Long rescue_evaluation_id;
	/** 车牌号评价id **/
	private Long ambulance_evaluation_id;

	public TblHelpRecord() {
	}

	public TblHelpRecord(String cus_phone, Long cus_id, Long rescue_id,
			String rescue_name, Long ambulance_id, String ambulance_no) {
		this.cus_phone = cus_phone;
		this.cus_id = cus_id;
		this.rescue_id = rescue_id;
		this.rescue_name = rescue_name;
		this.ambulance_id = ambulance_id;
		this.ambulance_no = ambulance_no;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCus_phone() {
		return cus_phone;
	}

	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}

	public Long getCus_id() {
		return cus_id;
	}

	public void setCus_id(Long cus_id) {
		this.cus_id = cus_id;
	}

	public Long getRescue_id() {
		return rescue_id;
	}

	public void setRescue_id(Long rescue_id) {
		this.rescue_id = rescue_id;
	}

	public String getRescue_name() {
		return rescue_name;
	}

	public void setRescue_name(String rescue_name) {
		this.rescue_name = rescue_name;
	}

	public Long getAmbulance_id() {
		return ambulance_id;
	}

	public void setAmbulance_id(Long ambulance_id) {
		this.ambulance_id = ambulance_id;
	}

	public String getAmbulance_no() {
		return ambulance_no;
	}

	public void setAmbulance_no(String ambulance_no) {
		this.ambulance_no = ambulance_no;
	}

	public String getRescue_state() {
		return rescue_state;
	}

	public void setRescue_state(String rescue_state) {
		this.rescue_state = rescue_state;
	}

	public String getAmbulance_state() {
		return ambulance_state;
	}

	public void setAmbulance_state(String ambulance_state) {
		this.ambulance_state = ambulance_state;
	}

	public Long getRescue_evaluation_id() {
		return rescue_evaluation_id;
	}

	public void setRescue_evaluation_id(Long rescue_evaluation_id) {
		this.rescue_evaluation_id = rescue_evaluation_id;
	}

	public Long getAmbulance_evaluation_id() {
		return ambulance_evaluation_id;
	}

	public void setAmbulance_evaluation_id(Long ambulance_evaluation_id) {
		this.ambulance_evaluation_id = ambulance_evaluation_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
