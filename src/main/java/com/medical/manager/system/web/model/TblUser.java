
/**
 * 系统项目名称
 * com.supermarket.system.web.model
 * TblUser.java
 * 
 * 2015年6月26日-下午1:52:16
 *  2015XX公司-版权所有
 * 
 */
 
package com.medical.manager.system.web.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSON;


/**
 * 
 * @description 用户信息
 * @time 2015年6月26日 下午1:54:50
 * @author Jason
 * @version 1.0.0
 *
 */
@Alias(value="TblUser")
public class TblUser implements Serializable {

    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    /** 用户登录名称 **/
    private String userCode;
    /** 用户名 **/
    private String userName;
    private String userMobile;
    private String userEmail;
    private String createOper;
    private String createDate;
    private String createTime;
    private String modifyOper;
    private String modifyDate;
    private String modifyTime;
    private String remark;
    
    private String userUnit;
    private String userProvince;
    private String userCity;
    private String lastLoginTime;
    private String lastLoginIp;
    /**
     * 00正常09-禁用
     */
    private String currentStatus;
    /**
     * 用户角色
     */
    private TblUserRole userRole;
    
    
    public TblUser() {
    }

    public TblUser(Long id, String userCode, String userName, String userMobile, String userEmail, String createOper,
            String createDate, String createTime, String modifyOper, String modifyDate, String modifyTime, String remark) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.createOper = createOper;
        this.createDate = createDate;
        this.createTime = createTime;
        this.modifyOper = modifyOper;
        this.modifyDate = modifyDate;
        this.modifyTime = modifyTime;
        this.remark = remark;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserMobile() {
        return userMobile;
    }
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getCreateOper() {
        return createOper;
    }
    public void setCreateOper(String createOper) {
        this.createOper = createOper;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getModifyOper() {
        return modifyOper;
    }
    public void setModifyOper(String modifyOper) {
        this.modifyOper = modifyOper;
    }
    public String getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    public String getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String toString(){
        if(this != null){
            return JSON.toJSONString(this);
        }
        return null;
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public TblUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(TblUserRole userRole) {
        this.userRole = userRole;
    }

}
