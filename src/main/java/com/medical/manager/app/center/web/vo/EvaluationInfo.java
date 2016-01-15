/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月17日
 */
package com.medical.manager.app.center.web.vo;

/**
 * <p>Discription:[]</p>
 * @author: Jason
 * @update: 2015年12月17日 Jason[变更描述]
 */
public class EvaluationInfo {
    private Long id;

    private Long custId;
    
    private String custName;
    private String headImg;

    private Long evaluatedId;

    private String evaluateTime;

    private String evaluateStar;

    private String evaluateContent;
    
    private String type;
    private String orgName;
    private String mobileNo;
    private String lastTime;
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
     * @return the custId
     */
    public Long getCustId() {
        return custId;
    }
    /**
     * @param custId the custId to set
     */
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }
    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }
    /**
     * @return the headImg
     */
    public String getHeadImg() {
        return headImg;
    }
    /**
     * @param headImg the headImg to set
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    /**
     * @return the evaluatedId
     */
    public Long getEvaluatedId() {
        return evaluatedId;
    }
    /**
     * @param evaluatedId the evaluatedId to set
     */
    public void setEvaluatedId(Long evaluatedId) {
        this.evaluatedId = evaluatedId;
    }
    /**
     * @return the evaluateTime
     */
    public String getEvaluateTime() {
        return evaluateTime;
    }
    /**
     * @param evaluateTime the evaluateTime to set
     */
    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime;
    }
    /**
     * @return the evaluateStar
     */
    public String getEvaluateStar() {
        return evaluateStar;
    }
    /**
     * @param evaluateStar the evaluateStar to set
     */
    public void setEvaluateStar(String evaluateStar) {
        this.evaluateStar = evaluateStar;
    }
    /**
     * @return the evaluateContent
     */
    public String getEvaluateContent() {
        return evaluateContent;
    }
    /**
     * @param evaluateContent the evaluateContent to set
     */
    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }
    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
     * @return the lastTime
     */
    public String getLastTime() {
        return lastTime;
    }
    /**
     * @param lastTime the lastTime to set
     */
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
    
    
}
