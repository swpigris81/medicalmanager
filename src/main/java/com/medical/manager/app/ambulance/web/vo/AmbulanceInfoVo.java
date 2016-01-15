/**
 * 系统项目名称
 * com.medical.manager.app.ambulance.web.vo
 * AmbulanceInfo.java
 * 
 * 2015年12月21日-下午3:52:51
 * 2015版权所有
 * 
 */
package com.medical.manager.app.ambulance.web.vo;

import java.io.Serializable;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月21日 下午3:52:51
 * @author Json
 * @version 1.0.0
 */
public class AmbulanceInfoVo implements Serializable {

	/**  **/
	private static final long serialVersionUID = -1317690719950488664L;
	/** 车牌号 **/
	private String idCardNo;
	/** 急救车id **/
	private String cusId;
	/** 评价评价星级，四舍五入 **/
	private String evaluateStar;
	/** 单位名称 **/
	private String unitName;
	/** 绑定手机号码 **/
	private String bindingPhone;
	/** 固定电话 **/
	private String fixTelephone;
	/** 状态：00待接，01已接，02已完成，03未完成 **/
	private String status;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getEvaluateStar() {
		return evaluateStar;
	}

	public void setEvaluateStar(String evaluateStar) {
		this.evaluateStar = evaluateStar;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getBindingPhone() {
		return bindingPhone;
	}

	public void setBindingPhone(String bindingPhone) {
		this.bindingPhone = bindingPhone;
	}

	public String getFixTelephone() {
		return fixTelephone;
	}

	public void setFixTelephone(String fixTelephone) {
		this.fixTelephone = fixTelephone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
