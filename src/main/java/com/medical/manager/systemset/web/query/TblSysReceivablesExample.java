package com.medical.manager.systemset.web.query;

import java.util.ArrayList;
import java.util.List;

public class TblSysReceivablesExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public TblSysReceivablesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAlipayNameIsNull() {
            addCriterion("ALIPAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAlipayNameIsNotNull() {
            addCriterion("ALIPAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayNameEqualTo(String value) {
            addCriterion("ALIPAY_NAME =", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotEqualTo(String value) {
            addCriterion("ALIPAY_NAME <>", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameGreaterThan(String value) {
            addCriterion("ALIPAY_NAME >", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameGreaterThanOrEqualTo(String value) {
            addCriterion("ALIPAY_NAME >=", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameLessThan(String value) {
            addCriterion("ALIPAY_NAME <", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameLessThanOrEqualTo(String value) {
            addCriterion("ALIPAY_NAME <=", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameLike(String value) {
            addCriterion("ALIPAY_NAME like", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotLike(String value) {
            addCriterion("ALIPAY_NAME not like", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameIn(List<String> values) {
            addCriterion("ALIPAY_NAME in", values, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotIn(List<String> values) {
            addCriterion("ALIPAY_NAME not in", values, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameBetween(String value1, String value2) {
            addCriterion("ALIPAY_NAME between", value1, value2, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotBetween(String value1, String value2) {
            addCriterion("ALIPAY_NAME not between", value1, value2, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNull() {
            addCriterion("ALIPAY_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNotNull() {
            addCriterion("ALIPAY_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountEqualTo(String value) {
            addCriterion("ALIPAY_ACCOUNT =", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotEqualTo(String value) {
            addCriterion("ALIPAY_ACCOUNT <>", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThan(String value) {
            addCriterion("ALIPAY_ACCOUNT >", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ALIPAY_ACCOUNT >=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThan(String value) {
            addCriterion("ALIPAY_ACCOUNT <", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThanOrEqualTo(String value) {
            addCriterion("ALIPAY_ACCOUNT <=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLike(String value) {
            addCriterion("ALIPAY_ACCOUNT like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotLike(String value) {
            addCriterion("ALIPAY_ACCOUNT not like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIn(List<String> values) {
            addCriterion("ALIPAY_ACCOUNT in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotIn(List<String> values) {
            addCriterion("ALIPAY_ACCOUNT not in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountBetween(String value1, String value2) {
            addCriterion("ALIPAY_ACCOUNT between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotBetween(String value1, String value2) {
            addCriterion("ALIPAY_ACCOUNT not between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyIsNull() {
            addCriterion("PUBLIC_ACCOUNT_COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyIsNotNull() {
            addCriterion("PUBLIC_ACCOUNT_COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY =", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyNotEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY <>", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyGreaterThan(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY >", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY >=", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyLessThan(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY <", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyLessThanOrEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY <=", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyLike(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY like", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyNotLike(String value) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY not like", value, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyIn(List<String> values) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY in", values, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyNotIn(List<String> values) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY not in", values, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyBetween(String value1, String value2) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY between", value1, value2, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountCompanyNotBetween(String value1, String value2) {
            addCriterion("PUBLIC_ACCOUNT_COMPANY not between", value1, value2, "publicAccountCompany");
            return (Criteria) this;
        }

        public Criteria andPublicAccountIsNull() {
            addCriterion("PUBLIC_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andPublicAccountIsNotNull() {
            addCriterion("PUBLIC_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPublicAccountEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT =", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountNotEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT <>", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountGreaterThan(String value) {
            addCriterion("PUBLIC_ACCOUNT >", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT >=", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountLessThan(String value) {
            addCriterion("PUBLIC_ACCOUNT <", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountLessThanOrEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT <=", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountLike(String value) {
            addCriterion("PUBLIC_ACCOUNT like", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountNotLike(String value) {
            addCriterion("PUBLIC_ACCOUNT not like", value, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountIn(List<String> values) {
            addCriterion("PUBLIC_ACCOUNT in", values, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountNotIn(List<String> values) {
            addCriterion("PUBLIC_ACCOUNT not in", values, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBetween(String value1, String value2) {
            addCriterion("PUBLIC_ACCOUNT between", value1, value2, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountNotBetween(String value1, String value2) {
            addCriterion("PUBLIC_ACCOUNT not between", value1, value2, "publicAccount");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankIsNull() {
            addCriterion("PUBLIC_ACCOUNT_BANK is null");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankIsNotNull() {
            addCriterion("PUBLIC_ACCOUNT_BANK is not null");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK =", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankNotEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK <>", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankGreaterThan(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK >", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK >=", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankLessThan(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK <", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankLessThanOrEqualTo(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK <=", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankLike(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK like", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankNotLike(String value) {
            addCriterion("PUBLIC_ACCOUNT_BANK not like", value, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankIn(List<String> values) {
            addCriterion("PUBLIC_ACCOUNT_BANK in", values, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankNotIn(List<String> values) {
            addCriterion("PUBLIC_ACCOUNT_BANK not in", values, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankBetween(String value1, String value2) {
            addCriterion("PUBLIC_ACCOUNT_BANK between", value1, value2, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPublicAccountBankNotBetween(String value1, String value2) {
            addCriterion("PUBLIC_ACCOUNT_BANK not between", value1, value2, "publicAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNull() {
            addCriterion("PERSON_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("PERSON_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("PERSON_NAME =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("PERSON_NAME <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("PERSON_NAME >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("PERSON_NAME >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("PERSON_NAME <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("PERSON_NAME <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("PERSON_NAME like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("PERSON_NAME not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("PERSON_NAME in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("PERSON_NAME not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("PERSON_NAME between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("PERSON_NAME not between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonAccountIsNull() {
            addCriterion("PERSON_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andPersonAccountIsNotNull() {
            addCriterion("PERSON_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPersonAccountEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT =", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountNotEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT <>", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountGreaterThan(String value) {
            addCriterion("PERSON_ACCOUNT >", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountGreaterThanOrEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT >=", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountLessThan(String value) {
            addCriterion("PERSON_ACCOUNT <", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountLessThanOrEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT <=", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountLike(String value) {
            addCriterion("PERSON_ACCOUNT like", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountNotLike(String value) {
            addCriterion("PERSON_ACCOUNT not like", value, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountIn(List<String> values) {
            addCriterion("PERSON_ACCOUNT in", values, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountNotIn(List<String> values) {
            addCriterion("PERSON_ACCOUNT not in", values, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBetween(String value1, String value2) {
            addCriterion("PERSON_ACCOUNT between", value1, value2, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountNotBetween(String value1, String value2) {
            addCriterion("PERSON_ACCOUNT not between", value1, value2, "personAccount");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankIsNull() {
            addCriterion("PERSON_ACCOUNT_BANK is null");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankIsNotNull() {
            addCriterion("PERSON_ACCOUNT_BANK is not null");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT_BANK =", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankNotEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT_BANK <>", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankGreaterThan(String value) {
            addCriterion("PERSON_ACCOUNT_BANK >", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankGreaterThanOrEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT_BANK >=", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankLessThan(String value) {
            addCriterion("PERSON_ACCOUNT_BANK <", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankLessThanOrEqualTo(String value) {
            addCriterion("PERSON_ACCOUNT_BANK <=", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankLike(String value) {
            addCriterion("PERSON_ACCOUNT_BANK like", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankNotLike(String value) {
            addCriterion("PERSON_ACCOUNT_BANK not like", value, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankIn(List<String> values) {
            addCriterion("PERSON_ACCOUNT_BANK in", values, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankNotIn(List<String> values) {
            addCriterion("PERSON_ACCOUNT_BANK not in", values, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankBetween(String value1, String value2) {
            addCriterion("PERSON_ACCOUNT_BANK between", value1, value2, "personAccountBank");
            return (Criteria) this;
        }

        public Criteria andPersonAccountBankNotBetween(String value1, String value2) {
            addCriterion("PERSON_ACCOUNT_BANK not between", value1, value2, "personAccountBank");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}