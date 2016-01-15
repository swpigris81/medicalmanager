package com.medical.manager.app.ambulance.web.model;

public class TblEvaluateInfo {
    private Long id;

    private Long custId;
    
    private String custName;
    private String headImg;

    private Long evaluatedId;

    private String evaluateTime;

    private String evaluateStar;

    private String evaluateContent;

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

    public Long getEvaluatedId() {
        return evaluatedId;
    }

    public void setEvaluatedId(Long evaluatedId) {
        this.evaluatedId = evaluatedId;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime == null ? null : evaluateTime.trim();
    }

    public String getEvaluateStar() {
        return evaluateStar;
    }

    public void setEvaluateStar(String evaluateStar) {
        this.evaluateStar = evaluateStar == null ? null : evaluateStar.trim();
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
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
}