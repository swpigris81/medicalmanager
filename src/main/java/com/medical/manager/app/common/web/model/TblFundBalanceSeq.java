/**
 * 系统项目名称
 * com.medical.manager.app.common.web.model
 * TblFundBalanceSeq.java
 * 
 * 2015年12月16日-下午8:33:51
 * 2015版权所有
 * 
 */

package com.medical.manager.app.common.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 用户余额变动流水
 * 
 * @time 2015年12月16日 下午8:33:51
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblFundBalanceSeq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7024210383979375364L;

	/** 自增主键 **/
	private Long id;
	/** 用户id **/
	private Long cus_id;
	/** 收支标识（0收入借，1支出贷） **/
	private String debit_credit;
	/** 变动金额（单位分） **/
	private BigDecimal change_amount;
	/** 变动时间 **/
	private String change_time;
	/** 变动描述 **/
	private String change_des;
	/** 备注 **/
	private String remark;
	/** 备注 **/
	private String recommend_flag;

	public TblFundBalanceSeq() {
	}

	public TblFundBalanceSeq(Long cus_id, String debit_credit,
			BigDecimal change_amount, String change_time, String change_des,
			String recommend_flag) {
		this.cus_id = cus_id;
		this.debit_credit = debit_credit;
		this.change_amount = change_amount;
		this.change_time = change_time;
		this.change_des = change_des;
		this.recommend_flag = recommend_flag;
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

	public String getDebit_credit() {
		return debit_credit;
	}

	public void setDebit_credit(String debit_credit) {
		this.debit_credit = debit_credit;
	}

	public BigDecimal getChange_amount() {
		return change_amount;
	}

	public void setChange_amount(BigDecimal change_amount) {
		this.change_amount = change_amount;
	}

	public String getChange_time() {
		return change_time;
	}

	public void setChange_time(String change_time) {
		this.change_time = change_time;
	}

	public String getChange_des() {
		return change_des;
	}

	public void setChange_des(String change_des) {
		this.change_des = change_des;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecommend_flag() {
		return recommend_flag;
	}

	public void setRecommend_flag(String recommend_flag) {
		this.recommend_flag = recommend_flag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
