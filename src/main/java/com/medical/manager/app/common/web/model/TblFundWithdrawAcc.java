/**
 * 系统项目名称
 * com.medical.manager.app.common.web.model
 * TblFundWithdrawAcc.java
 * 
 * 2015年12月17日-下午1:49:58
 * 2015版权所有
 * 
 */
package com.medical.manager.app.common.web.model;

import java.io.Serializable;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月17日 下午1:49:58
 * @author Json
 * @version 1.0.0
 */
public class TblFundWithdrawAcc implements Serializable {

	/**  **/
	private static final long serialVersionUID = 6315993464072117106L;
	/** 自增主键 **/
	private Long id;
	/** 客户id **/
	private Long cus_id;
	/** 账户类型（0-支付宝，1-财付通，3-微信，4银行卡） **/
	private String acc_type;
	/** 开户行 **/
	private String open_bank;
	/** 账户名 **/
	private String acc_name;
	/** 账号（支付宝等保存账号，银行卡保存卡号） **/
	private String acc_no;
	/** 是否默认提现账户（Y-是，N-否） **/
	private String default_flag;
	/** 创建时间 **/
	private String create_time;
	/** 备注 **/
	private String remark;

	public TblFundWithdrawAcc() {
	}

	public TblFundWithdrawAcc(Long cus_id, String acc_type, String open_bank,
			String acc_name, String acc_no, String default_flag,
			String create_time, String remark) {
		this.cus_id = cus_id;
		this.acc_type = acc_type;
		this.open_bank = open_bank;
		this.acc_name = acc_name;
		this.acc_no = acc_no;
		this.default_flag = default_flag;
		this.create_time = create_time;
		this.remark = remark;
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

	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	public String getOpen_bank() {
		return open_bank;
	}

	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}

	public String getAcc_name() {
		return acc_name;
	}

	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
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
