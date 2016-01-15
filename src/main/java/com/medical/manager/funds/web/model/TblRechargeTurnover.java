
/**
 * 系统项目名称
 * com.medical.manager.funds.web.model
 * TblRechargeTurnover.java
 * 
 * 2015年12月7日-上午11:21:04
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * TblRechargeTurnover 用户充值流水
 * 
 * 2015年12月7日 上午11:21:04
 * 
 * @version 1.0.0
 * 
 */
public class TblRechargeTurnover implements Serializable {
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String custId;
    private String custName;
    private String custMobile;
    private String rechargeUnit;
    private String rechargeType;
    private BigDecimal rechargeAmount;
    private String receiptBankAccount;
    private String rechargeTime;
    private Integer rechargeMonth;
    private String expireDate;
    private String rechargeStatus;
    private String rechargeMode;
    private String rechargeRemark;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustMobile() {
        return custMobile;
    }
    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }
    public String getRechargeUnit() {
        return rechargeUnit;
    }
    public void setRechargeUnit(String rechargeUnit) {
        this.rechargeUnit = rechargeUnit;
    }
    public String getRechargeType() {
        return rechargeType;
    }
    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }
    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }
    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
    public String getReceiptBankAccount() {
        return receiptBankAccount;
    }
    public void setReceiptBankAccount(String receiptBankAccount) {
        this.receiptBankAccount = receiptBankAccount;
    }
    public String getRechargeTime() {
        return rechargeTime;
    }
    public void setRechargeTime(String rechargeTime) {
        this.rechargeTime = rechargeTime;
    }
    public String getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    public String getRechargeStatus() {
        return rechargeStatus;
    }
    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }
    public String getRechargeMode() {
        return rechargeMode;
    }
    public void setRechargeMode(String rechargeMode) {
        this.rechargeMode = rechargeMode;
    }
    public String getRechargeRemark() {
        return rechargeRemark;
    }
    public void setRechargeRemark(String rechargeRemark) {
        this.rechargeRemark = rechargeRemark;
    }
    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }
    /**
     * @param custId the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }
    /**
     * @return the rechargeMonth
     */
    public Integer getRechargeMonth() {
        return rechargeMonth;
    }
    /**
     * @param rechargeMonth the rechargeMonth to set
     */
    public void setRechargeMonth(Integer rechargeMonth) {
        this.rechargeMonth = rechargeMonth;
    }
    
    
}
