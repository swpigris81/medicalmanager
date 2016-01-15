
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.model
 * TblCusRelatives.java
 * 
 * 2015年12月15日-下午8:45:04
 * 2015版权所有
 * 
 */

package com.medical.manager.app.relatives.web.model;

import java.io.Serializable;

/**
 * @description 客户亲属
 * 
 * @time 2015年12月15日 下午8:45:04
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblCusRelatives implements Serializable {
	/** **/
	private static final long serialVersionUID = 9073434383959717994L;
	/** '主键' **/
	private Long id;
	/** '客户ID' **/
	private Long cusId;
	/** '亲属名称' **/
	private String relativesName;
	/** '亲属关系（父亲、母亲、女儿等）' **/
	private String relativesRelation;
	/** '手机号' **/
	private String phone;
	/** '小头像' **/
	private String smallImageUrl;
	/** '大头像' **/
	private String bigImageUrl;
	/** '年龄' **/
	private Integer age;
	/** '身高' **/
	private String height;
	/** '体重' **/
	private String weight;
	/** '星座' **/
	private String constellation;
	/** '备注' **/
	private String remark;
	/** '生日' **/
	private String birthday;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCusId() {
		return cusId;
	}

	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}

	public String getRelativesName() {
		return relativesName;
	}

	public void setRelativesName(String relativesName) {
		this.relativesName = relativesName;
	}

	public String getRelativesRelation() {
		return relativesRelation;
	}

	public void setRelativesRelation(String relativesRelation) {
		this.relativesRelation = relativesRelation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public String getBigImageUrl() {
		return bigImageUrl;
	}

	public void setBigImageUrl(String bigImageUrl) {
		this.bigImageUrl = bigImageUrl;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
