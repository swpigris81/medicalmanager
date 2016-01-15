/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.model
 * TblCusAddress.java
 * 
 * 2015年12月16日-下午9:44:27
 * 2015版权所有
 * 
 */

package com.medical.manager.app.relatives.web.model;

import java.io.Serializable;

/**
 * @description
 * 
 * @time 2015年12月16日 下午9:44:27
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblCusAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6486451140148981992L;
	/** 自增主键 **/
	private Long id;
	/** 客户id **/
	private Long cus_id;
	/** 收货人姓名 **/
	private String receiver;
	/** 联系电话 **/
	private String phone;
	/** 备用电话 **/
	private String backup_phone;
	/** 邮编 **/
	private String zip_code;
	/** 收货地址 **/
	private String address;
	/** 顺序 **/
	private String default_flag;
	/** 创建时间 **/
	private String create_time;
	/** 备注 **/
	private String remark;

	public TblCusAddress() {
	}

	public TblCusAddress(Long cus_id, String receiver, String phone,
			String zip_code, String address, String default_flag) {
		this.cus_id = cus_id;
		this.receiver = receiver;
		this.phone = phone;
		this.zip_code = zip_code;
		this.address = address;
		this.default_flag = default_flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCus_id() {
		return cus_id;
	}

	public void setCus_id(Long cus_id) {
		this.cus_id = cus_id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBackup_phone() {
		return backup_phone;
	}

	public void setBackup_phone(String backup_phone) {
		this.backup_phone = backup_phone;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDefault_flag() {
		return default_flag;
	}

	public void setDefault_flag(String default_flag) {
		this.default_flag = default_flag;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
