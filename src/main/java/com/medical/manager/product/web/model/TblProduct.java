package com.medical.manager.product.web.model;

import java.math.BigDecimal;

public class TblProduct {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.PRODUCT_ID
     *
     * @mbggenerated
     */
    private Long productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.BRAND_NO
     *
     * @mbggenerated
     */
    private Long brandNo;
    
    private TblBrand tblBrand;
    
    private String brandName;
    private String brandImg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.PRODUCT_NO
     *
     * @mbggenerated
     */
    private String productNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.INVENTORY_NUM
     *
     * @mbggenerated
     */
    private Long inventoryNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.LOAD_CAPACITY
     *
     * @mbggenerated
     */
    private Double loadCapacity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.FREIGHT
     *
     * @mbggenerated
     */
    private BigDecimal freight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.IS_INVOICE
     *
     * @mbggenerated
     */
    private String isInvoice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.DISCOUNT_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal discountPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.MARKET_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal marketPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.IS_MINUS_INVENTORY
     *
     * @mbggenerated
     */
    private String isMinusInventory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.ADD_TIME
     *
     * @mbggenerated
     */
    private String addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_product.INTRODUCTION
     *
     * @mbggenerated
     */
    private String introduction;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.PRODUCT_ID
     *
     * @return the value of tbl_product.PRODUCT_ID
     *
     * @mbggenerated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.PRODUCT_ID
     *
     * @param productId the value for tbl_product.PRODUCT_ID
     *
     * @mbggenerated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.BRAND_NO
     *
     * @return the value of tbl_product.BRAND_NO
     *
     * @mbggenerated
     */
    public Long getBrandNo() {
        return brandNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.BRAND_NO
     *
     * @param brandNo the value for tbl_product.BRAND_NO
     *
     * @mbggenerated
     */
    public void setBrandNo(Long brandNo) {
        this.brandNo = brandNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.PRODUCT_NO
     *
     * @return the value of tbl_product.PRODUCT_NO
     *
     * @mbggenerated
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.PRODUCT_NO
     *
     * @param productNo the value for tbl_product.PRODUCT_NO
     *
     * @mbggenerated
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.INVENTORY_NUM
     *
     * @return the value of tbl_product.INVENTORY_NUM
     *
     * @mbggenerated
     */
    public Long getInventoryNum() {
        return inventoryNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.INVENTORY_NUM
     *
     * @param inventoryNum the value for tbl_product.INVENTORY_NUM
     *
     * @mbggenerated
     */
    public void setInventoryNum(Long inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.LOAD_CAPACITY
     *
     * @return the value of tbl_product.LOAD_CAPACITY
     *
     * @mbggenerated
     */
    public Double getLoadCapacity() {
        return loadCapacity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.LOAD_CAPACITY
     *
     * @param loadCapacity the value for tbl_product.LOAD_CAPACITY
     *
     * @mbggenerated
     */
    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.FREIGHT
     *
     * @return the value of tbl_product.FREIGHT
     *
     * @mbggenerated
     */
    public BigDecimal getFreight() {
        return freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.FREIGHT
     *
     * @param freight the value for tbl_product.FREIGHT
     *
     * @mbggenerated
     */
    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.IS_INVOICE
     *
     * @return the value of tbl_product.IS_INVOICE
     *
     * @mbggenerated
     */
    public String getIsInvoice() {
        return isInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.IS_INVOICE
     *
     * @param isInvoice the value for tbl_product.IS_INVOICE
     *
     * @mbggenerated
     */
    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice == null ? null : isInvoice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.DISCOUNT_PRICE
     *
     * @return the value of tbl_product.DISCOUNT_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.DISCOUNT_PRICE
     *
     * @param discountPrice the value for tbl_product.DISCOUNT_PRICE
     *
     * @mbggenerated
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.MARKET_PRICE
     *
     * @return the value of tbl_product.MARKET_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.MARKET_PRICE
     *
     * @param marketPrice the value for tbl_product.MARKET_PRICE
     *
     * @mbggenerated
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.IS_MINUS_INVENTORY
     *
     * @return the value of tbl_product.IS_MINUS_INVENTORY
     *
     * @mbggenerated
     */
    public String getIsMinusInventory() {
        return isMinusInventory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.IS_MINUS_INVENTORY
     *
     * @param isMinusInventory the value for tbl_product.IS_MINUS_INVENTORY
     *
     * @mbggenerated
     */
    public void setIsMinusInventory(String isMinusInventory) {
        this.isMinusInventory = isMinusInventory == null ? null : isMinusInventory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.ADD_TIME
     *
     * @return the value of tbl_product.ADD_TIME
     *
     * @mbggenerated
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.ADD_TIME
     *
     * @param addTime the value for tbl_product.ADD_TIME
     *
     * @mbggenerated
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_product.INTRODUCTION
     *
     * @return the value of tbl_product.INTRODUCTION
     *
     * @mbggenerated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_product.INTRODUCTION
     *
     * @param introduction the value for tbl_product.INTRODUCTION
     *
     * @mbggenerated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * @return the tblBrand
     */
    public TblBrand getTblBrand() {
        return tblBrand;
    }

    /**
     * @param tblBrand the tblBrand to set
     */
    public void setTblBrand(TblBrand tblBrand) {
        this.tblBrand = tblBrand;
    }

    /**
     * @return the brandName
     */
    public String getBrandName() {
        return this.tblBrand.getBrandName();
    }

    /**
     * @param brandName the brandName to set
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return the brandImg
     */
    public String getBrandImg() {
        return brandImg;
    }

    /**
     * @param brandImg the brandImg to set
     */
    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }
}