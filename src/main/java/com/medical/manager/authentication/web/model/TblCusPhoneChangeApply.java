package com.medical.manager.authentication.web.model;

import java.math.BigDecimal;

public class TblCusPhoneChangeApply {
    private Long id;

    private Long userId;

    private String userName;

    private String realName;

    private String originalPhone;

    private String newPhone;

    private BigDecimal rechargeAmount;

    private String applyTime;
    
    private String checkTime;

    private String applyStatus;

    private String applyReason;
    
    private String audit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getOriginalPhone() {
        return originalPhone;
    }

    public void setOriginalPhone(String originalPhone) {
        this.originalPhone = originalPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime == null ? null : applyTime.trim();
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus == null ? null : applyStatus.trim();
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }

    /**
     * @return the audit
     */
    public String getAudit() {
        return audit;
    }

    /**
     * @param audit the audit to set
     */
    public void setAudit(String audit) {
        this.audit = audit;
    }

    /**
     * @return the checkTime
     */
    public String getCheckTime() {
        return checkTime;
    }

    /**
     * @param checkTime the checkTime to set
     */
    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
}