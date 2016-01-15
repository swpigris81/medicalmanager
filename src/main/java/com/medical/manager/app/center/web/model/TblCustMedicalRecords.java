
/**
 * 系统项目名称
 * com.medical.manager.app.center.web.model
 * TblCustMedicalRecords.java
 * 
 * 2015年12月15日-下午12:41:00
 * 2015
 * 
 */
 
package com.medical.manager.app.center.web.model;

import java.io.Serializable;


/**
 * 
 * TblCustMedicalRecords 用户病历
 * 
 * 2015年12月15日 下午12:41:00
 * 
 * @version 1.0.0
 * 
 */
public class TblCustMedicalRecords implements Serializable {
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long custId;
    private String mobileNo;
    private String recordNo;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustId() {
        return custId;
    }
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getRecordNo() {
        return recordNo;
    }
    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }
    
    
}
