
/**
 * 系统项目名称
 * com.medical.manager.app.center.web.model
 * TblAlarmInfo.java
 * 
 * 2015年12月15日-上午11:20:14
 * 2015
 * 
 */
 
package com.medical.manager.app.center.web.model;

import java.io.Serializable;


/**
 * 
 * TblAlarmInfo 报警记录
 * 
 * 2015年12月15日 上午11:20:14
 * 
 * @version 1.0.0
 * 
 */

public class TblAlarmInfo implements Serializable {

    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    /**
     * 报警手机号
     */
    private String mobileNo;
    /**
     * 报警时间
     */
    private String alarmTime;
    /**
     * 联系人
     */
    private String linkInfo;
    /**
     * 报警标题
     */
    private String alarmTitle;
    /**
     * 求救信息
     */
    private String alarmInfo;
    /**
     * 地址
     */
    private String alarmAddress;
    /**
     * 救治人数
     */
    private Integer saveNum;
    /**
     * 需要救护车数量
     */
    private Integer needAnbulanceNum;
    /**
     * 是否突发事件0-否1-是
     */
    private String isAttack;
    /**
     * 是否指定医院0-否1-是
     */
    private String isSpecifyHos;
    /**
     * 收治医院
     */
    private String hospital;
    /**
     * 事发时间
     */
    private String incidentTime;
    /**
     * 候车地点
     */
    private String waitingAdd;
    /**
     * 所属片区
     */
    private String belongArea;
    /**
     * 患者姓名
     */
    private String patientName;
    /**
     * 患者性别
     */
    private String patientSex;
    /**
     * 患者年龄
     */
    private Integer patientAge;
    /**
     * 患者电话
     */
    private String patientMobile;
    /**
     * 是否骚扰电话0-否1-是
     */
    private String isHarass;
    /**
     * 接警人ID
     */
    private Long policeId;
    /**
     * 接警人手机
     */
    private String policeMobile;
    /**
     * 接警人姓名
     */
    private String policeName;
    /**
     * 状态00-等待处理01-报警自挂02-出警反馈
     */
    private String status;
    
    private String province;
    private String city;
    
    private Long talkTime;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getAlarmTime() {
        return alarmTime;
    }
    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }
    public String getLinkInfo() {
        return linkInfo;
    }
    public void setLinkInfo(String linkInfo) {
        this.linkInfo = linkInfo;
    }
    public String getAlarmTitle() {
        return alarmTitle;
    }
    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
    }
    public String getAlarmInfo() {
        return alarmInfo;
    }
    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }
    public String getAlarmAddress() {
        return alarmAddress;
    }
    public void setAlarmAddress(String alarmAddress) {
        this.alarmAddress = alarmAddress;
    }
    public Integer getSaveNum() {
        return saveNum;
    }
    public void setSaveNum(Integer saveNum) {
        this.saveNum = saveNum;
    }
    public Integer getNeedAnbulanceNum() {
        return needAnbulanceNum;
    }
    public void setNeedAnbulanceNum(Integer needAnbulanceNum) {
        this.needAnbulanceNum = needAnbulanceNum;
    }
    public String getIsAttack() {
        return isAttack;
    }
    public void setIsAttack(String isAttack) {
        this.isAttack = isAttack;
    }
    public String getIsSpecifyHos() {
        return isSpecifyHos;
    }
    public void setIsSpecifyHos(String isSpecifyHos) {
        this.isSpecifyHos = isSpecifyHos;
    }
    public String getIncidentTime() {
        return incidentTime;
    }
    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }
    public String getWaitingAdd() {
        return waitingAdd;
    }
    public void setWaitingAdd(String waitingAdd) {
        this.waitingAdd = waitingAdd;
    }
    public String getBelongArea() {
        return belongArea;
    }
    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getPatientSex() {
        return patientSex;
    }
    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }
    public Integer getPatientAge() {
        return patientAge;
    }
    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }
    public String getPatientMobile() {
        return patientMobile;
    }
    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }
    public String getIsHarass() {
        return isHarass;
    }
    public void setIsHarass(String isHarass) {
        this.isHarass = isHarass;
    }
    public Long getPoliceId() {
        return policeId;
    }
    public void setPoliceId(Long policeId) {
        this.policeId = policeId;
    }
    public String getPoliceMobile() {
        return policeMobile;
    }
    public void setPoliceMobile(String policeMobile) {
        this.policeMobile = policeMobile;
    }
    public String getPoliceName() {
        return policeName;
    }
    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }
    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the talkTime
     */
    public Long getTalkTime() {
        return talkTime;
    }
    /**
     * @param talkTime the talkTime to set
     */
    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }
    public String getHospital() {
        return hospital;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    
    
}
