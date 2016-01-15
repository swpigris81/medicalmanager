
/**
 * 系统项目名称
 * com.supermarket.system.web.model
 * TblFindPassword.java
 * 
 * 2015年6月26日-下午2:09:11
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.model;

import java.io.Serializable;


/**
 * @description 找回密码
 * 
 * @time 2015年6月26日 下午2:09:11
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblFindPassword implements Serializable {

    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private String userCode;
    private String checkCode;
    private String createOper;
    private String createDate;
    private String createTime;
    private String modifyOper;
    private String modifyDate;
    private String modifyTime;
    private String remark;
    
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
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getCheckCode() {
        return checkCode;
    }
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
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
    
}
