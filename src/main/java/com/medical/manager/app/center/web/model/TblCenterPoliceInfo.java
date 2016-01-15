
/**
 * 系统项目名称
 * com.supermarket.system.web.model
 * TblFindPassword.java
 * 
 * 2015年6月26日-下午2:09:11
 * 2015版权所有
 * 
 */
 
package com.medical.manager.app.center.web.model;

import java.io.Serializable;


/**
 * @description 找回密码
 * 
 * @time 2015年6月26日 下午2:09:11
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblCenterPoliceInfo implements Serializable {

    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 所属120中心-关联客户基本信息表中，类型为120中心的数据
     */
    private Long certenId;
    private String name;
    private String workNo;
    private String mobileNo;
    private String password;
    /**
     * 用户类型00-超级管理员01-管理员02-接警员
     */
    private String userType;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the certenId
     */
    public Long getCertenId() {
        return certenId;
    }
    /**
     * @param certenId the certenId to set
     */
    public void setCertenId(Long certenId) {
        this.certenId = certenId;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the workNo
     */
    public String getWorkNo() {
        return workNo;
    }
    /**
     * @param workNo the workNo to set
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }
    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }
    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }
    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
