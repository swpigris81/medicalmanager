
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.model
 * TblPropaBlacklist.java
 * 
 * 2015年12月4日-下午2:14:13
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.model;

import java.io.Serializable;


/**
 * 
 * TblPropaBlacklist 黑名单
 * 
 * 2015年12月4日 下午2:14:13
 * 
 * @version 1.0.0
 * 
 */

public class TblPropaBlacklist implements Serializable {
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String userMobile;
    private String type;
    private String typeName;
    private String remark;
    private String createOper;
    private String createDate;
    private String createTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
    
    
}
