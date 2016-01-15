
/**
 * 系统项目名称
 * com.medical.manager.customer.web.model
 * TblCusBasicInfo.java
 * 
 * 2015年12月3日-下午8:19:25
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.dto;

import java.io.Serializable;

/**
 * @description 客户基本信息管理实体
 * 
 * @time 2015年12月3日 下午8:19:25
 * @author Jason
 * @version 1.0.0
 * 
 */

public class CusBasicInfoDto implements Serializable {
	private static final long serialVersionUID = 8534128332361064767L;
	private Long id;
	private String nickname;
	private String realName;
	private String phone;
	private String idCardNo;
	private String cusType;
	private String province;
	private String city;
	private String county;
	private Long creditScore;
	private String activationFlag;
	private String lockFlag;

	private String provinceName;
	private String cityName;

	private Long creditScoreStart;
	private Long creditScoreEnd;
	private String registerTimeStart;
	private String registerTimeEnd;
	private String serviceExpireTimeStart;
	private String serviceExpireTimeEnd;
	private String lastLoginTimeStart;
	private String lastLoginTimeEnd;
	private String activationTimeStart;
	private String activationTimeEnd;
	private String lockTimeStart;
	private String lockTimeEnd;
	private String updateTimeStart;
	private String updateTimeEnd;
	private String lockExpireTimeStart;
	private String lockExpireTimeEnd;
	
	private String ids;
	/** 操作类型：1加，2减 **/
	private String updateType;
	
	public static enum updateTypeEnum {
		ADD("1", "加"), SUB("2", "减");
		public String id, value;

		private updateTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public Long getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Long creditScore) {
		this.creditScore = creditScore;
	}

	public String getActivationFlag() {
		return activationFlag;
	}

	public void setActivationFlag(String activationFlag) {
		this.activationFlag = activationFlag;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
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

	public Long getCreditScoreStart() {
		return creditScoreStart;
	}

	public void setCreditScoreStart(Long creditScoreStart) {
		this.creditScoreStart = creditScoreStart;
	}

	public Long getCreditScoreEnd() {
		return creditScoreEnd;
	}

	public void setCreditScoreEnd(Long creditScoreEnd) {
		this.creditScoreEnd = creditScoreEnd;
	}

	public String getRegisterTimeStart() {
		return registerTimeStart;
	}

	public void setRegisterTimeStart(String registerTimeStart) {
		this.registerTimeStart = registerTimeStart;
	}

	public String getRegisterTimeEnd() {
		return registerTimeEnd;
	}

	public void setRegisterTimeEnd(String registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
	}

	public String getServiceExpireTimeStart() {
		return serviceExpireTimeStart;
	}

	public void setServiceExpireTimeStart(String serviceExpireTimeStart) {
		this.serviceExpireTimeStart = serviceExpireTimeStart;
	}

	public String getServiceExpireTimeEnd() {
		return serviceExpireTimeEnd;
	}

	public void setServiceExpireTimeEnd(String serviceExpireTimeEnd) {
		this.serviceExpireTimeEnd = serviceExpireTimeEnd;
	}

	public String getLastLoginTimeStart() {
		return lastLoginTimeStart;
	}

	public void setLastLoginTimeStart(String lastLoginTimeStart) {
		this.lastLoginTimeStart = lastLoginTimeStart;
	}

	public String getLastLoginTimeEnd() {
		return lastLoginTimeEnd;
	}

	public void setLastLoginTimeEnd(String lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public String getActivationTimeStart() {
		return activationTimeStart;
	}

	public void setActivationTimeStart(String activationTimeStart) {
		this.activationTimeStart = activationTimeStart;
	}

	public String getActivationTimeEnd() {
		return activationTimeEnd;
	}

	public void setActivationTimeEnd(String activationTimeEnd) {
		this.activationTimeEnd = activationTimeEnd;
	}

	public String getLockTimeStart() {
		return lockTimeStart;
	}

	public void setLockTimeStart(String lockTimeStart) {
		this.lockTimeStart = lockTimeStart;
	}

	public String getLockTimeEnd() {
		return lockTimeEnd;
	}

	public void setLockTimeEnd(String lockTimeEnd) {
		this.lockTimeEnd = lockTimeEnd;
	}

	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getLockExpireTimeStart() {
		return lockExpireTimeStart;
	}

	public void setLockExpireTimeStart(String lockExpireTimeStart) {
		this.lockExpireTimeStart = lockExpireTimeStart;
	}

	public String getLockExpireTimeEnd() {
		return lockExpireTimeEnd;
	}

	public void setLockExpireTimeEnd(String lockExpireTimeEnd) {
		this.lockExpireTimeEnd = lockExpireTimeEnd;
	}

}
