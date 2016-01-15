/**
 * 系统项目名称
 * com.medical.manager.customer.web.model
 * TblCusReportDto.java
 * 
 * 2015年12月3日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.dto;

import java.io.Serializable;

/**
 * @description 客户举报管理
 * 
 * @time 2015年12月3日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblCusReportDto implements Serializable {
	private static final long serialVersionUID = 8534128332361064767L;
	/** 自增主键 **/
	private Long id;
	/** 举报人 **/
	private String informants;
	/** 被举报人 **/
	private String reported;
	/** 举报类型 **/
	private String reportType;
	/** 处理状态 **/
	private String dealStatus;

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

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

}
