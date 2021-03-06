package com.medical.manager.product.web.model;

import java.math.BigDecimal;

public class TblOrder {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.ORDER_NUMBER
	 *
	 * @mbggenerated
	 */
	private Long orderNumber;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.BRAND_NO
	 *
	 * @mbggenerated
	 */
	private Long brandNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.BRAND_NAME
	 *
	 * @mbggenerated
	 */
	private String brandName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.PRODUCT_ID
	 *
	 * @mbggenerated
	 */
	private Long productId;

	private TblProduct tblProduct;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.NUMBER
	 *
	 * @mbggenerated
	 */
	private Long number;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.PRODUCT_NO
	 *
	 * @mbggenerated
	 */
	private String productNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.USER_ID
	 *
	 * @mbggenerated
	 */
	private Long userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.PHONE_NUMBER
	 *
	 * @mbggenerated
	 */
	private Integer phoneNumber;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.RECEIVE_ADRESS
	 *
	 * @mbggenerated
	 */
	private String receiveAddress;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.IS_PAY
	 *
	 * @mbggenerated
	 */
	private String isPay;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.IS_DELIVERY
	 *
	 * @mbggenerated
	 */

	private String isDelivery;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tbl_order.ORDER_TIME
	 *
	 * @mbggenerated
	 */
	private String orderTime;
	private String payTime;
	private String deliveryTime;
	private BigDecimal orderAmount;
	private String remark;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.ORDER_NUMBER
	 *
	 * @return the value of tbl_order.ORDER_NUMBER
	 *
	 * @mbggenerated
	 */
	public Long getOrderNumber() {
		return orderNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.ORDER_NUMBER
	 *
	 * @param orderNumber
	 *            the value for tbl_order.ORDER_NUMBER
	 *
	 * @mbggenerated
	 */
	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.BRAND_NO
	 *
	 * @return the value of tbl_order.BRAND_NO
	 *
	 * @mbggenerated
	 */
	public Long getBrandNo() {
		return brandNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.BRAND_NO
	 *
	 * @param brandNo
	 *            the value for tbl_order.BRAND_NO
	 *
	 * @mbggenerated
	 */
	public void setBrandNo(Long brandNo) {
		this.brandNo = brandNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.BRAND_NAME
	 *
	 * @return the value of tbl_order.BRAND_NAME
	 *
	 * @mbggenerated
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.BRAND_NAME
	 *
	 * @param brandName
	 *            the value for tbl_order.BRAND_NAME
	 *
	 * @mbggenerated
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.PRODUCT_ID
	 *
	 * @return the value of tbl_order.PRODUCT_ID
	 *
	 * @mbggenerated
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.PRODUCT_ID
	 *
	 * @param productId
	 *            the value for tbl_order.PRODUCT_ID
	 *
	 * @mbggenerated
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.NUMBER
	 *
	 * @return the value of tbl_order.NUMBER
	 *
	 * @mbggenerated
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.NUMBER
	 *
	 * @param number
	 *            the value for tbl_order.NUMBER
	 *
	 * @mbggenerated
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.PRODUCT_NO
	 *
	 * @return the value of tbl_order.PRODUCT_NO
	 *
	 * @mbggenerated
	 */
	public String getProductNo() {
		return productNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.PRODUCT_NO
	 *
	 * @param productNo
	 *            the value for tbl_order.PRODUCT_NO
	 *
	 * @mbggenerated
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo == null ? null : productNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.USER_ID
	 *
	 * @return the value of tbl_order.USER_ID
	 *
	 * @mbggenerated
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.USER_ID
	 *
	 * @param userId
	 *            the value for tbl_order.USER_ID
	 *
	 * @mbggenerated
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.PHONE_NUMBER
	 *
	 * @return the value of tbl_order.PHONE_NUMBER
	 *
	 * @mbggenerated
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.PHONE_NUMBER
	 *
	 * @param phoneNumber
	 *            the value for tbl_order.PHONE_NUMBER
	 *
	 * @mbggenerated
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.RECEIVE_ADRESS
	 *
	 * @return the value of tbl_order.RECEIVE_ADRESS
	 *
	 * @mbggenerated
	 */
	public String getReceiveAddress() {
		return receiveAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.RECEIVE_ADRESS
	 *
	 * @param receiveAdress
	 *            the value for tbl_order.RECEIVE_ADRESS
	 *
	 * @mbggenerated
	 */
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress == null ? null : receiveAddress
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.IS_PAY
	 *
	 * @return the value of tbl_order.IS_PAY
	 *
	 * @mbggenerated
	 */
	public String getIsPay() {
		return isPay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.IS_PAY
	 *
	 * @param isPay
	 *            the value for tbl_order.IS_PAY
	 *
	 * @mbggenerated
	 */
	public void setIsPay(String isPay) {
		this.isPay = isPay == null ? null : isPay.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.IS_DELIVERY
	 *
	 * @return the value of tbl_order.IS_DELIVERY
	 *
	 * @mbggenerated
	 */
	public String getIsDelivery() {
		return isDelivery;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.IS_DELIVERY
	 *
	 * @param isDelivery
	 *            the value for tbl_order.IS_DELIVERY
	 *
	 * @mbggenerated
	 */
	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery == null ? null : isDelivery.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tbl_order.ORDER_TIME
	 *
	 * @return the value of tbl_order.ORDER_TIME
	 *
	 * @mbggenerated
	 */
	public String getOrderTime() {
		return orderTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tbl_order.ORDER_TIME
	 *
	 * @param orderTime
	 *            the value for tbl_order.ORDER_TIME
	 *
	 * @mbggenerated
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime == null ? null : orderTime.trim();
	}

	/**
	 * @return the tblProduct
	 */
	public TblProduct getTblProduct() {
		return tblProduct;
	}

	/**
	 * @param tblProduct
	 *            the tblProduct to set
	 */
	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}