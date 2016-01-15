/**
 * 系统项目名称
 * com.medical.manager.customer.web.model
 * TblCusBasicInfo.java
 * 
 * 2015年12月3日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.model;

import java.io.Serializable;

import com.medical.manager.common.CommonEnums;

/**
 * @description 邀请管理
 * 
 * @time 2015年12月3日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblCusInvitation implements Serializable {
	private static final long serialVersionUID = 8534128332361064767L;
	private Long id;
	private Long cusId;
	private String invitationCode;
	private String remark;
	private String backCashFlag;
	private String backCashTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the cusId
	 */
	public Long getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}

	/**
	 * @return the invitationCode
	 */
	public String getInvitationCode() {
		return invitationCode;
	}

	/**
	 * @param invitationCode
	 *            the invitationCode to set
	 */
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBackCashFlag() {
		return backCashFlag;
	}

	public void setBackCashFlag(String backCashFlag) {
		this.backCashFlag = backCashFlag;
	}

	public String getBackCashTime() {
		return backCashTime;
	}

	public void setBackCashTime(String backCashTime) {
		this.backCashTime = backCashTime;
	}

	public boolean getIsBackCashFlag() {
		return CommonEnums.YesOrNo.YES.id.equals(backCashFlag);
	}
}
