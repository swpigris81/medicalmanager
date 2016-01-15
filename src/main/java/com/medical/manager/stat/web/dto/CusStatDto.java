/**
 * 系统项目名称
 * com.medical.manager.stat.web.dto
 * StatAreaAlarmDto.java
 * 
 * 2015年12月8日-下午12:04:44
 * 2015版权所有
 * 
 */
package com.medical.manager.stat.web.dto;

import java.io.Serializable;

/**
 * @description 这里是方法描述
 *
 * @time 2015年12月8日 下午12:04:44
 * @author Jason
 * @version 1.0.0
 */
public class CusStatDto implements Serializable {

	private static final long serialVersionUID = -823319365039267470L;

	public static enum statTimeOptionEnum {
		DAY("0", "当日"), MONTH("1", "当月"), YEAR("2", "当年"), CUSTOM("3", "自定义时间段");
		public String id, value;

		private statTimeOptionEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}
	}

	private String statTimeStart;
	private String statTimeEnd;
	private String statTimeOption;
	private String alarmCenter;
	/** 管理员管辖区域 **/
	private String alarmArea;
	/** 管理员隶属省市级标志,U区县级，C市区级，P省市级 **/
	private String alarmAreaType;

	public String getStatTimeStart() {
		return statTimeStart;
	}

	public void setStatTimeStart(String statTimeStart) {
		this.statTimeStart = statTimeStart;
	}

	public String getStatTimeEnd() {
		return statTimeEnd;
	}

	public void setStatTimeEnd(String statTimeEnd) {
		this.statTimeEnd = statTimeEnd;
	}

	public String getStatTimeOption() {
		return statTimeOption;
	}

	public void setStatTimeOption(String statTimeOption) {
		this.statTimeOption = statTimeOption;
	}

	public String getAlarmCenter() {
		return alarmCenter;
	}

	public void setAlarmCenter(String alarmCenter) {
		this.alarmCenter = alarmCenter;
	}

	public String getAlarmArea() {
		return alarmArea;
	}

	public void setAlarmArea(String alarmArea) {
		this.alarmArea = alarmArea;
	}

	public String getAlarmAreaType() {
		return alarmAreaType;
	}

	public void setAlarmAreaType(String alarmAreaType) {
		this.alarmAreaType = alarmAreaType;
	}

}
