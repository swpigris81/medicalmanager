package com.medical.manager.product.web.model;


public class TblMachineUse {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.OPEN_TIME
     *
     * @mbggenerated
     */
    private String openTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.USER_ID
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.USER_NAME
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.PHONE_NUMBER
     *
     * @mbggenerated
     */
    private String phoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.MACHINE_ID
     *
     * @mbggenerated
     */
    private Long machineId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.MACHINE_CODE
     *
     * @mbggenerated
     */
    private String machineCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_machine_use.REMARK
     *
     * @mbggenerated
     */
    private String remark;
    
	public TblMachineUse() {
	}

    public TblMachineUse(String openTime, Long userId, String userName,
			String phoneNumber, Long machineId, String machineCode,
			String remark) {
		this.openTime = openTime;
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.machineId = machineId;
		this.machineCode = machineCode;
		this.remark = remark;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.ID
     *
     * @return the value of tbl_machine_use.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.ID
     *
     * @param id the value for tbl_machine_use.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.OPEN_TIME
     *
     * @return the value of tbl_machine_use.OPEN_TIME
     *
     * @mbggenerated
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.OPEN_TIME
     *
     * @param openTime the value for tbl_machine_use.OPEN_TIME
     *
     * @mbggenerated
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.USER_ID
     *
     * @return the value of tbl_machine_use.USER_ID
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.USER_ID
     *
     * @param userId the value for tbl_machine_use.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.USER_NAME
     *
     * @return the value of tbl_machine_use.USER_NAME
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.USER_NAME
     *
     * @param userName the value for tbl_machine_use.USER_NAME
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.PHONE_NUMBER
     *
     * @return the value of tbl_machine_use.PHONE_NUMBER
     *
     * @mbggenerated
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.PHONE_NUMBER
     *
     * @param phoneNumber the value for tbl_machine_use.PHONE_NUMBER
     *
     * @mbggenerated
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.MACHINE_ID
     *
     * @return the value of tbl_machine_use.MACHINE_ID
     *
     * @mbggenerated
     */
    public Long getMachineId() {
        return machineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.MACHINE_ID
     *
     * @param machineId the value for tbl_machine_use.MACHINE_ID
     *
     * @mbggenerated
     */
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.MACHINE_CODE
     *
     * @return the value of tbl_machine_use.MACHINE_CODE
     *
     * @mbggenerated
     */
    public String getMachineCode() {
        return machineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.MACHINE_CODE
     *
     * @param machineCode the value for tbl_machine_use.MACHINE_CODE
     *
     * @mbggenerated
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode == null ? null : machineCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_machine_use.REMARK
     *
     * @return the value of tbl_machine_use.REMARK
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_machine_use.REMARK
     *
     * @param remark the value for tbl_machine_use.REMARK
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}