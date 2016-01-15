
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.model
 * TblCustRelation.java
 * 
 * 2015年12月15日-下午1:11:40
 * 2015
 * 
 */

package com.medical.manager.app.relatives.web.model;

import java.io.Serializable;

/**
 * 
 * TblCustRelation 关系人
 * 
 * 2015年12月15日 下午1:11:40
 * 
 * @version 1.0.0
 * 
 */

public class TblCustRelation implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long custId;
	private String custName;
	private String custType;
	private String mobileNo;
	private Long relationId;
	private String relationMobile;
	private String relationName;
	private String relationType;
	/**
	 * 状态0-未通过1-已通过
	 */
	private String relationStatus;
	/** 同意添加标2识(Y-同意，N-不同意) add 2015-12-19 **/
	private String agreeFlag;
	/** 允许关系人访问我的足迹标识(Y-允许，N-不允许) add 2015-12-19 **/
	private String allowFlag;
	/** 我被允许访问关系人的足迹标识(Y-允许，N-不允许) add 2015-12-19 **/
	private String allowedFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public String getRelationMobile() {
		return relationMobile;
	}

	public void setRelationMobile(String relationMobile) {
		this.relationMobile = relationMobile;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getRelationStatus() {
		return relationStatus;
	}

	public void setRelationStatus(String relationStatus) {
		this.relationStatus = relationStatus;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType
	 *            the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the relationType
	 */
	public String getRelationType() {
		return relationType;
	}

	/**
	 * @param relationType
	 *            the relationType to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getAgreeFlag() {
		return agreeFlag;
	}

	public void setAgreeFlag(String agreeFlag) {
		this.agreeFlag = agreeFlag;
	}

	public String getAllowFlag() {
		return allowFlag;
	}

	public void setAllowFlag(String allowFlag) {
		this.allowFlag = allowFlag;
	}

	public String getAllowedFlag() {
		return allowedFlag;
	}

	public void setAllowedFlag(String allowedFlag) {
		this.allowedFlag = allowedFlag;
	}

}
