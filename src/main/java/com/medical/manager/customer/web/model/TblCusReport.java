/**
 * 系统项目名称
 * com.medical.manager.customer.web.model
 * TblCusReport.java
 * 
 * 2015年12月7日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.model;

import java.io.Serializable;

/**
 * @description 客户举报管理
 * 
 * @time 2015年12月7日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblCusReport implements Serializable {
	/**  **/
	private static final long serialVersionUID = 3722910493171555303L;
	/** 自增主键 **/
	private Long id;
	/** 举报人 **/
	private String informants;
	/** 被举报人 **/
	private String reported;
	/** 举报类型 **/
	private String reportType;
	/** 举报信息 **/
	private String reportInfo;
	/** 举报时间 **/
	private String reportTime;
	/** 处理状态 **/
	private String dealStatus;
	/** 处理人 **/
	private String dealUser;
	/** 处理时间 **/
	private String dealTime;
	/** 处理信息 **/
	private String dealInfo;
	/** 备注 **/
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInformants() {
		return informants;
	}

	public void setInformants(String informants) {
		this.informants = informants;
	}

	public String getReported() {
		return reported;
	}

	public void setReported(String reported) {
		this.reported = reported;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealInfo() {
		return dealInfo;
	}

	public void setDealInfo(String dealInfo) {
		this.dealInfo = dealInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
