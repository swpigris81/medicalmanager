/**
 * 系统项目名称
 * com.medical.manager.stat.web.model.mapper
 * CusGeneralStat.java
 * 
 * 2015年12月8日-上午10:14:37
 * 2015版权所有
 * 
 */
package com.medical.manager.stat.web.model;

import java.io.Serializable;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月8日 上午10:14:37
 * @author Jason
 * @version 1.0.0
 */
public class CusGeneralStat implements Serializable {

	/**  **/
	private static final long serialVersionUID = -8225655123030426068L;
	private String generalStatItemKey;
	private String generalStatItem;
	private String generalStatValue;

	public String getGeneralStatItem() {
		return generalStatItem;
	}

	public void setGeneralStatItem(String generalStatItem) {
		this.generalStatItem = generalStatItem;
	}

	public String getGeneralStatValue() {
		return generalStatValue;
	}

	public void setGeneralStatValue(String generalStatValue) {
		this.generalStatValue = generalStatValue;
	}

	public String getGeneralStatItemKey() {
		return generalStatItemKey;
	}

	public void setGeneralStatItemKey(String generalStatItemKey) {
		this.generalStatItemKey = generalStatItemKey;
	}
	
	public CusGeneralStat(){}

	public CusGeneralStat(String generalStatItemKey, String generalStatItem, String generalStatValue) {
		this.generalStatItemKey = generalStatItemKey;
		this.generalStatItem = generalStatItem;
		this.generalStatValue = generalStatValue;
	}
}
