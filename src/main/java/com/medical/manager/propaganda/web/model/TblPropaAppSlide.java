
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.model
 * TblAppSlide.java
 * 
 * 2015年12月4日-下午1:12:32
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.model;

import java.io.Serializable;


/**
 * 
 * TblAppSlide 幻灯片
 * 
 * 2015年12月4日 下午1:12:32
 * 
 * @version 1.0.0
 * 
 */
public class TblPropaAppSlide implements Serializable {
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String imageUrl;
    private String imageLocation;
    private String type;
    private String userType;
    private String createOper;
    private String createDate;
    private String createTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageLocation() {
        return imageLocation;
    }
    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
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
