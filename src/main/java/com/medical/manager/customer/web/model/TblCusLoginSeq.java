/**
 * 系统项目名称
 * com.medical.manager.customer.web.model
 * TblCusLoginSeq.java
 * 
 * 2015年12月7日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 用户登陆流水
 * 
 * @time 2015年12月7日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblCusLoginSeq implements Serializable {
	/**  **/
	private static final long serialVersionUID = -823319365039267470L;
	/** 自增主键 **/
	private Long id;
	/** 客户系统数据主键 **/
	private BigDecimal cusId;
	/** 登陆时间 **/
	private String loginTime;
	/** 登陆ip **/
	private String loginIp;
	/** 备注 **/
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCusId() {
		return cusId;
	}

	public void setCusId(BigDecimal cusId) {
		this.cusId = cusId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
