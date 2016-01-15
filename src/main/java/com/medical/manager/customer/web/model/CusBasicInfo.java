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

import com.medical.manager.authentication.web.model.TblCusBasicExpand;

/**
 * @description 客户基本信息管理实体
 * 
 * @time 2015年12月3日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

public class CusBasicInfo implements Serializable {
	private static final long serialVersionUID = 8534128332361064767L;
	private Long id;
	private String nickname;
	private String realName;
	private String phone;
	private String idCardNo;
	private String cusType;
	private String headImg;
	private String province;
	private String city;
	private String county;
	private String registerTime;
	private String serviceExpireTime;
	private Long creditScore;
	private String lastLoginIp;
	private String lastLoginTime;
	private String activationFlag;
	private String activationTime;
	private String activationUser;
	private String lockFlag;
	private String lockTime;
	private String lockUser;
	private String remark;
	private String updateUser;
	private String updateTime;
	private String lockExpireTime;

	private String provinceName;
	private String cityName;
	/**
	 * 是否已发送过手机短信0-否（默认）1-是
	 */
	private String hasSent;

	private TblCusBasicExpand expand;
	private String ambulanceStatus;
	private String statusReason;
	private Integer yearScore;
	private String scoreYear;
	private String invitationCode;

	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 维度
	 */
	private String latitude;
	
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

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getServiceExpireTime() {
		return serviceExpireTime;
	}

	public void setServiceExpireTime(String serviceExpireTime) {
		this.serviceExpireTime = serviceExpireTime;
	}

	public Long getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Long creditScore) {
		this.creditScore = creditScore;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getActivationFlag() {
		return activationFlag;
	}

	public void setActivationFlag(String activationFlag) {
		this.activationFlag = activationFlag;
	}

	public String getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(String activationTime) {
		this.activationTime = activationTime;
	}

	public String getActivationUser() {
		return activationUser;
	}

	public void setActivationUser(String activationUser) {
		this.activationUser = activationUser;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	public String getLockUser() {
		return lockUser;
	}

	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLockExpireTime() {
		return lockExpireTime;
	}

	public void setLockExpireTime(String lockExpireTime) {
		this.lockExpireTime = lockExpireTime;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHasSent() {
		return hasSent;
	}

	public void setHasSent(String hasSent) {
		this.hasSent = hasSent;
	}

	public TblCusBasicExpand getExpand() {
		return expand;
	}

	public void setExpand(TblCusBasicExpand expand) {
		this.expand = expand;
	}

	/**
	 * @return the ambulanceStatus
	 */
	public String getAmbulanceStatus() {
		return ambulanceStatus;
	}

	/**
	 * @param ambulanceStatus
	 *            the ambulanceStatus to set
	 */
	public void setAmbulanceStatus(String ambulanceStatus) {
		this.ambulanceStatus = ambulanceStatus;
	}

	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}

	/**
	 * @param statusReason
	 *            the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	/**
	 * @return the headImg
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * @param headImg
	 *            the headImg to set
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/**
	 * @return the yearScore
	 */
	public Integer getYearScore() {
		return yearScore;
	}

	/**
	 * @param yearScore
	 *            the yearScore to set
	 */
	public void setYearScore(Integer yearScore) {
		this.yearScore = yearScore;
	}

	/**
	 * @return the scoreYear
	 */
	public String getScoreYear() {
		return scoreYear;
	}

	/**
	 * @param scoreYear
	 *            the scoreYear to set
	 */
	public void setScoreYear(String scoreYear) {
		this.scoreYear = scoreYear;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

}
