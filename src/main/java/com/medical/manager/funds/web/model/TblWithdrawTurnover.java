/**
 * 系统项目名称
 * com.medical.manager.funds.web.model
 * TblWithdrawTurnover.java
 * 
 * 2015年12月7日-下午12:00:53
 * 2015
 * 
 */

package com.medical.manager.funds.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * TblWithdrawTurnover 提现
 * 
 * 2015年12月7日 下午12:00:53
 * 
 * @version 1.0.0
 * 
 */

public class TblWithdrawTurnover implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = 1L;
	private Long id;
	private String custId;
	private String custName;
	private String custMobile;
	private String name;
	private String withdrawAccount;
	private String withdrawBank;
	private String branchBank;
	private BigDecimal withdrawAmount;
	private String withdrawTime;
	private String withdrawStatus;
	private Long accountId;

	public TblWithdrawTurnover() {
	}
	
	public TblWithdrawTurnover(String custId, String custName,
			String custMobile, String name, String withdrawAccount,
			String withdrawBank, String branchBank, BigDecimal withdrawAmount,
			Long accountId) {
		this.custId = custId;
		this.custName = custName;
		this.custMobile = custMobile;
		this.name = name;
		this.withdrawAccount = withdrawAccount;
		this.withdrawBank = withdrawBank;
		this.branchBank = branchBank;
		this.withdrawAmount = withdrawAmount;
		this.accountId = accountId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWithdrawBank() {
		return withdrawBank;
	}

	public void setWithdrawBank(String withdrawBank) {
		this.withdrawBank = withdrawBank;
	}

	public String getBranchBank() {
		return branchBank;
	}

	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}

	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getWithdrawTime() {
		return withdrawTime;
	}

	public void setWithdrawTime(String withdrawTime) {
		this.withdrawTime = withdrawTime;
	}

	public String getWithdrawStatus() {
		return withdrawStatus;
	}

	public void setWithdrawStatus(String withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}

	public String getWithdrawAccount() {
		return withdrawAccount;
	}

	public void setWithdrawAccount(String withdrawAccount) {
		this.withdrawAccount = withdrawAccount;
	}

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}
