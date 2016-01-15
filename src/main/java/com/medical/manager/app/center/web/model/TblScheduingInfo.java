/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月17日
 */
package com.medical.manager.app.center.web.model;

import java.io.Serializable;

/**
 * <p>Discription:[调度信息]</p>
 * @author: Jason
 * @update: 2015年12月17日 Jason[变更描述]
 */
public class TblScheduingInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long alarmId;
    private String incidentTime;
    private String schedulingPhone;
    private String schedulingOrg;
    private String callingNumber;
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the alarmId
     */
    public Long getAlarmId() {
        return alarmId;
    }
    /**
     * @param alarmId the alarmId to set
     */
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }
    /**
     * @return the incidentTime
     */
    public String getIncidentTime() {
        return incidentTime;
    }
    /**
     * @param incidentTime the incidentTime to set
     */
    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }
    /**
     * @return the schedulingPhone
     */
    public String getSchedulingPhone() {
        return schedulingPhone;
    }
    /**
     * @param schedulingPhone the schedulingPhone to set
     */
    public void setSchedulingPhone(String schedulingPhone) {
        this.schedulingPhone = schedulingPhone;
    }
    /**
     * @return the schedulingOrg
     */
    public String getSchedulingOrg() {
        return schedulingOrg;
    }
    /**
     * @param schedulingOrg the schedulingOrg to set
     */
    public void setSchedulingOrg(String schedulingOrg) {
        this.schedulingOrg = schedulingOrg;
    }
    /**
     * @return the callingNumber
     */
    public String getCallingNumber() {
        return callingNumber;
    }
    /**
     * @param callingNumber the callingNumber to set
     */
    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }
    
    
}
