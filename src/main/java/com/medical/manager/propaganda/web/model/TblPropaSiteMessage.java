
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.model
 * TblPropaSiteMessage.java
 * 
 * 2015年12月4日-下午1:28:44
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.model;

import java.io.Serializable;


/**
 * 
 * TblPropaSiteMessage 站内信
 * 
 * 2015年12月4日 下午1:28:44
 * 
 * @version 1.0.0
 * 
 */

public class TblPropaSiteMessage implements Serializable {

    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private String sendOper;
    private String receiveOper;
    private String content;
    private Integer province;
    private String provinceName;
    private Integer city;
    private String cityName;
    private String type;
    private String typeName;
    private String createOper;
    private String createDate;
    private String createTime;
    private String status;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSendOper() {
        return sendOper;
    }
    public void setSendOper(String sendOper) {
        this.sendOper = sendOper;
    }
    public String getReceiveOper() {
        return receiveOper;
    }
    public void setReceiveOper(String receiveOper) {
        this.receiveOper = receiveOper;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getProvince() {
        return province;
    }
    public void setProvince(Integer province) {
        this.province = province;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public Integer getCity() {
        return city;
    }
    public void setCity(Integer city) {
        this.city = city;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
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
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
