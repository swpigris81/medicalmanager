package com.medical.manager.authentication.web.query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TblCusBasicExpandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblCusBasicExpandExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andCusIdIsNull() {
            addCriterion("CUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCusIdIsNotNull() {
            addCriterion("CUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCusIdEqualTo(Long value) {
            addCriterion("CUS_ID =", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdNotEqualTo(Long value) {
            addCriterion("CUS_ID <>", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdGreaterThan(Long value) {
            addCriterion("CUS_ID >", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUS_ID >=", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdLessThan(Long value) {
            addCriterion("CUS_ID <", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdLessThanOrEqualTo(Long value) {
            addCriterion("CUS_ID <=", value, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdIn(List<Long> values) {
            addCriterion("CUS_ID in", values, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdNotIn(List<Long> values) {
            addCriterion("CUS_ID not in", values, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdBetween(Long value1, Long value2) {
            addCriterion("CUS_ID between", value1, value2, "cusId");
            return (Criteria) this;
        }

        public Criteria andCusIdNotBetween(Long value1, Long value2) {
            addCriterion("CUS_ID not between", value1, value2, "cusId");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNull() {
            addCriterion("UNIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNotNull() {
            addCriterion("UNIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNameEqualTo(String value) {
            addCriterion("UNIT_NAME =", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotEqualTo(String value) {
            addCriterion("UNIT_NAME <>", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThan(String value) {
            addCriterion("UNIT_NAME >", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT_NAME >=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThan(String value) {
            addCriterion("UNIT_NAME <", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThanOrEqualTo(String value) {
            addCriterion("UNIT_NAME <=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLike(String value) {
            addCriterion("UNIT_NAME like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotLike(String value) {
            addCriterion("UNIT_NAME not like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameIn(List<String> values) {
            addCriterion("UNIT_NAME in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotIn(List<String> values) {
            addCriterion("UNIT_NAME not in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameBetween(String value1, String value2) {
            addCriterion("UNIT_NAME between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotBetween(String value1, String value2) {
            addCriterion("UNIT_NAME not between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitAddressIsNull() {
            addCriterion("UNIT_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andUnitAddressIsNotNull() {
            addCriterion("UNIT_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andUnitAddressEqualTo(String value) {
            addCriterion("UNIT_ADDRESS =", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotEqualTo(String value) {
            addCriterion("UNIT_ADDRESS <>", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressGreaterThan(String value) {
            addCriterion("UNIT_ADDRESS >", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT_ADDRESS >=", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressLessThan(String value) {
            addCriterion("UNIT_ADDRESS <", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressLessThanOrEqualTo(String value) {
            addCriterion("UNIT_ADDRESS <=", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressLike(String value) {
            addCriterion("UNIT_ADDRESS like", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotLike(String value) {
            addCriterion("UNIT_ADDRESS not like", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressIn(List<String> values) {
            addCriterion("UNIT_ADDRESS in", values, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotIn(List<String> values) {
            addCriterion("UNIT_ADDRESS not in", values, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressBetween(String value1, String value2) {
            addCriterion("UNIT_ADDRESS between", value1, value2, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotBetween(String value1, String value2) {
            addCriterion("UNIT_ADDRESS not between", value1, value2, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andTollLevelIsNull() {
            addCriterion("TOLL_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andTollLevelIsNotNull() {
            addCriterion("TOLL_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andTollLevelEqualTo(String value) {
            addCriterion("TOLL_LEVEL =", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelNotEqualTo(String value) {
            addCriterion("TOLL_LEVEL <>", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelGreaterThan(String value) {
            addCriterion("TOLL_LEVEL >", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelGreaterThanOrEqualTo(String value) {
            addCriterion("TOLL_LEVEL >=", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelLessThan(String value) {
            addCriterion("TOLL_LEVEL <", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelLessThanOrEqualTo(String value) {
            addCriterion("TOLL_LEVEL <=", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelLike(String value) {
            addCriterion("TOLL_LEVEL like", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelNotLike(String value) {
            addCriterion("TOLL_LEVEL not like", value, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelIn(List<String> values) {
            addCriterion("TOLL_LEVEL in", values, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelNotIn(List<String> values) {
            addCriterion("TOLL_LEVEL not in", values, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelBetween(String value1, String value2) {
            addCriterion("TOLL_LEVEL between", value1, value2, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTollLevelNotBetween(String value1, String value2) {
            addCriterion("TOLL_LEVEL not between", value1, value2, "tollLevel");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonIsNull() {
            addCriterion("TECHNICAL_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonIsNotNull() {
            addCriterion("TECHNICAL_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonEqualTo(String value) {
            addCriterion("TECHNICAL_PERSON =", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonNotEqualTo(String value) {
            addCriterion("TECHNICAL_PERSON <>", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonGreaterThan(String value) {
            addCriterion("TECHNICAL_PERSON >", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_PERSON >=", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonLessThan(String value) {
            addCriterion("TECHNICAL_PERSON <", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonLessThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_PERSON <=", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonLike(String value) {
            addCriterion("TECHNICAL_PERSON like", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonNotLike(String value) {
            addCriterion("TECHNICAL_PERSON not like", value, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonIn(List<String> values) {
            addCriterion("TECHNICAL_PERSON in", values, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonNotIn(List<String> values) {
            addCriterion("TECHNICAL_PERSON not in", values, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonBetween(String value1, String value2) {
            addCriterion("TECHNICAL_PERSON between", value1, value2, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andTechnicalPersonNotBetween(String value1, String value2) {
            addCriterion("TECHNICAL_PERSON not between", value1, value2, "technicalPerson");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("CHECK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("CHECK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(String value) {
            addCriterion("CHECK_STATUS =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(String value) {
            addCriterion("CHECK_STATUS <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(String value) {
            addCriterion("CHECK_STATUS >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(String value) {
            addCriterion("CHECK_STATUS <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLike(String value) {
            addCriterion("CHECK_STATUS like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotLike(String value) {
            addCriterion("CHECK_STATUS not like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<String> values) {
            addCriterion("CHECK_STATUS in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<String> values) {
            addCriterion("CHECK_STATUS not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(String value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(String value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(String value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(String value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(String value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLike(String value) {
            addCriterion("CHECK_TIME like", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotLike(String value) {
            addCriterion("CHECK_TIME not like", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<String> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<String> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(String value1, String value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(String value1, String value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andAuditIsNull() {
            addCriterion("AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andAuditIsNotNull() {
            addCriterion("AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andAuditEqualTo(String value) {
            addCriterion("AUDIT =", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditNotEqualTo(String value) {
            addCriterion("AUDIT <>", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditGreaterThan(String value) {
            addCriterion("AUDIT >", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT >=", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditLessThan(String value) {
            addCriterion("AUDIT <", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditLessThanOrEqualTo(String value) {
            addCriterion("AUDIT <=", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditLike(String value) {
            addCriterion("AUDIT like", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditNotLike(String value) {
            addCriterion("AUDIT not like", value, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditIn(List<String> values) {
            addCriterion("AUDIT in", values, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditNotIn(List<String> values) {
            addCriterion("AUDIT not in", values, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditBetween(String value1, String value2) {
            addCriterion("AUDIT between", value1, value2, "audit");
            return (Criteria) this;
        }

        public Criteria andAuditNotBetween(String value1, String value2) {
            addCriterion("AUDIT not between", value1, value2, "audit");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountIsNull() {
            addCriterion("AUTHENTICATION_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountIsNotNull() {
            addCriterion("AUTHENTICATION_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountEqualTo(BigDecimal value) {
            addCriterion("AUTHENTICATION_AMOUNT =", value, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountNotEqualTo(BigDecimal value) {
            addCriterion("AUTHENTICATION_AMOUNT <>", value, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountGreaterThan(BigDecimal value) {
            addCriterion("AUTHENTICATION_AMOUNT >", value, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AUTHENTICATION_AMOUNT >=", value, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountLessThan(BigDecimal value) {
            addCriterion("AUTHENTICATION_AMOUNT <", value, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AUTHENTICATION_AMOUNT <=", value, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountIn(List<BigDecimal> values) {
            addCriterion("AUTHENTICATION_AMOUNT in", values, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountNotIn(List<BigDecimal> values) {
            addCriterion("AUTHENTICATION_AMOUNT not in", values, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AUTHENTICATION_AMOUNT between", value1, value2, "authenticationAmount");
            return (Criteria) this;
        }

        public Criteria andAuthenticationAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AUTHENTICATION_AMOUNT not between", value1, value2, "authenticationAmount");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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