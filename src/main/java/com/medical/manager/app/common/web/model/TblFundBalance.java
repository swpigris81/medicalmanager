/**
 * 系统项目名称
 * com.medical.manager.app.common.web.model
 * TblFundBalance.java
 * 
 * 2015年12月16日-下午1:45:10
 * 2015版权所有
 * 
 */
package com.medical.manager.app.common.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月16日 下午1:45:10
 * @author Json
 * @version 1.0.0
 */
public class TblFundBalance implements Serializable {

	/**  **/
	private static final long serialVersionUID = -3382110895179914869L;
	/** 客户id **/
	private Long cus_id;
	/** 余额 **/
	private BigDecimal balance;
	/** 更新时间 **/
	private String update_time;
	/** 备注 **/
	private String remark;

	public TblFundBalance() {
	}

	public TblFundBalance(Long cus_id, BigDecimal balance, String update_time,
			String remark) {
		this.cus_id = cus_id;
		this.balance = balance;
		this.update_time = update_time;
		this.remark = remark;
	}

	public Long getCus_id() {
		return cus_id;
	}

	public void setCus_id(Long cus_id) {
		this.cus_id = cus_id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
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
