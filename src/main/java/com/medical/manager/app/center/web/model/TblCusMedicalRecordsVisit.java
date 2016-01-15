/**
 * 系统项目名称
 * com.medical.manager.app.center.web.model
 * TblCusMedicalRecordsVisit.java
 * 
 * 2015年12月21日-下午5:01:11
 * 2015版权所有
 * 
 */
package com.medical.manager.app.center.web.model;

import java.io.Serializable;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月21日 下午5:01:11
 * @author Json
 * @version 1.0.0
 */
public class TblCusMedicalRecordsVisit implements Serializable {

	/**  **/
	private static final long serialVersionUID = -7812671278661668663L;
	private Long id;
	/** 病历id **/
	private Long record_id;
	/** 访问病历者id **/
	private Long visit_cus_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Long record_id) {
		this.record_id = record_id;
	}

	public Long getVisit_cus_id() {
		return visit_cus_id;
	}

	public void setVisit_cus_id(Long visit_cus_id) {
		this.visit_cus_id = visit_cus_id;
	}

}
