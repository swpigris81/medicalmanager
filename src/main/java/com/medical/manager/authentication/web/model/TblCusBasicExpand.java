package com.medical.manager.authentication.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.medical.manager.customer.web.model.CusBasicInfo;

public class TblCusBasicExpand implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long cusId;

    private String unitName;

    private String unitAddress;

    private String tollLevel;

    private String technicalPerson;

    private String checkStatus;

    private String checkTime;

    private String audit;
    
    private CusBasicInfo cusBasicInfo;
    
    private String useProvince;
    private String useCity;
    private String useCounty;
    private String bindingPhone;

    private BigDecimal authenticationAmount;
    
    private String fixTelephone;
    private String legalPersonCertificate;
    private String organizationCertificate;
    private String accountName;
    private String bankName;
    private String bankBranch;
    private String accountNo;
    private Long submitCustId;
    private String submitCustName;
    private String submitCustMobile;
    private String submiTime;

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress == null ? null : unitAddress.trim();
    }

    public String getTollLevel() {
        return tollLevel;
    }

    public void setTollLevel(String tollLevel) {
        this.tollLevel = tollLevel == null ? null : tollLevel.trim();
    }

    public String getTechnicalPerson() {
        return technicalPerson;
    }

    public void setTechnicalPerson(String technicalPerson) {
        this.technicalPerson = technicalPerson == null ? null : technicalPerson.trim();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime == null ? null : checkTime.trim();
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public BigDecimal getAuthenticationAmount() {
        return authenticationAmount;
    }

    public void setAuthenticationAmount(BigDecimal authenticationAmount) {
        this.authenticationAmount = authenticationAmount;
    }

    /**
     * @return the cusBasicInfo
     */
    public CusBasicInfo getCusBasicInfo() {
        return cusBasicInfo;
    }

    /**
     * @param cusBasicInfo the cusBasicInfo to set
     */
    public void setCusBasicInfo(CusBasicInfo cusBasicInfo) {
        this.cusBasicInfo = cusBasicInfo;
    }

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
     * @return the useProvince
     */
    public String getUseProvince() {
        return useProvince;
    }

    /**
     * @param useProvince the useProvince to set
     */
    public void setUseProvince(String useProvince) {
        this.useProvince = useProvince;
    }

    /**
     * @return the useCity
     */
    public String getUseCity() {
        return useCity;
    }

    /**
     * @param useCity the useCity to set
     */
    public void setUseCity(String useCity) {
        this.useCity = useCity;
    }

    /**
     * @return the useCounty
     */
    public String getUseCounty() {
        return useCounty;
    }

    /**
     * @param useCounty the useCounty to set
     */
    public void setUseCounty(String useCounty) {
        this.useCounty = useCounty;
    }

    /**
     * @return the bindingPhone
     */
    public String getBindingPhone() {
        return bindingPhone;
    }

    /**
     * @param bindingPhone the bindingPhone to set
     */
    public void setBindingPhone(String bindingPhone) {
        this.bindingPhone = bindingPhone;
    }

    /**
     * @return the fixTelephone
     */
    public String getFixTelephone() {
        return fixTelephone;
    }

    /**
     * @param fixTelephone the fixTelephone to set
     */
    public void setFixTelephone(String fixTelephone) {
        this.fixTelephone = fixTelephone;
    }

    /**
     * @return the legalPersonCertificate
     */
    public String getLegalPersonCertificate() {
        return legalPersonCertificate;
    }

    /**
     * @param legalPersonCertificate the legalPersonCertificate to set
     */
    public void setLegalPersonCertificate(String legalPersonCertificate) {
        this.legalPersonCertificate = legalPersonCertificate;
    }

    /**
     * @return the organizationCertificate
     */
    public String getOrganizationCertificate() {
        return organizationCertificate;
    }

    /**
     * @param organizationCertificate the organizationCertificate to set
     */
    public void setOrganizationCertificate(String organizationCertificate) {
        this.organizationCertificate = organizationCertificate;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the bankBranch
     */
    public String getBankBranch() {
        return bankBranch;
    }

    /**
     * @param bankBranch the bankBranch to set
     */
    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return the submitCustId
     */
    public Long getSubmitCustId() {
        return submitCustId;
    }

    /**
     * @param submitCustId the submitCustId to set
     */
    public void setSubmitCustId(Long submitCustId) {
        this.submitCustId = submitCustId;
    }

    /**
     * @return the submitCustName
     */
    public String getSubmitCustName() {
        return submitCustName;
    }

    /**
     * @param submitCustName the submitCustName to set
     */
    public void setSubmitCustName(String submitCustName) {
        this.submitCustName = submitCustName;
    }

    /**
     * @return the submitCustMobile
     */
    public String getSubmitCustMobile() {
        return submitCustMobile;
    }

    /**
     * @param submitCustMobile the submitCustMobile to set
     */
    public void setSubmitCustMobile(String submitCustMobile) {
        this.submitCustMobile = submitCustMobile;
    }

    /**
     * @return the submiTime
     */
    public String getSubmiTime() {
        return submiTime;
    }

    /**
     * @param submiTime the submiTime to set
     */
    public void setSubmiTime(String submiTime) {
        this.submiTime = submiTime;
    }
    
}