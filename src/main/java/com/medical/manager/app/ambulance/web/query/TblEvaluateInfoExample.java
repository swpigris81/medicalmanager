package com.medical.manager.app.ambulance.web.query;

import java.util.ArrayList;
import java.util.List;

public class TblEvaluateInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblEvaluateInfoExample() {
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

        public Criteria andCustIdIsNull() {
            addCriterion("CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(Long value) {
            addCriterion("CUST_ID =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(Long value) {
            addCriterion("CUST_ID <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(Long value) {
            addCriterion("CUST_ID >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUST_ID >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(Long value) {
            addCriterion("CUST_ID <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(Long value) {
            addCriterion("CUST_ID <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<Long> values) {
            addCriterion("CUST_ID in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<Long> values) {
            addCriterion("CUST_ID not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(Long value1, Long value2) {
            addCriterion("CUST_ID between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(Long value1, Long value2) {
            addCriterion("CUST_ID not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdIsNull() {
            addCriterion("EVALUATED_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdIsNotNull() {
            addCriterion("EVALUATED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdEqualTo(Long value) {
            addCriterion("EVALUATED_ID =", value, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdNotEqualTo(Long value) {
            addCriterion("EVALUATED_ID <>", value, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdGreaterThan(Long value) {
            addCriterion("EVALUATED_ID >", value, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EVALUATED_ID >=", value, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdLessThan(Long value) {
            addCriterion("EVALUATED_ID <", value, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdLessThanOrEqualTo(Long value) {
            addCriterion("EVALUATED_ID <=", value, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdIn(List<Long> values) {
            addCriterion("EVALUATED_ID in", values, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdNotIn(List<Long> values) {
            addCriterion("EVALUATED_ID not in", values, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdBetween(Long value1, Long value2) {
            addCriterion("EVALUATED_ID between", value1, value2, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluatedIdNotBetween(Long value1, Long value2) {
            addCriterion("EVALUATED_ID not between", value1, value2, "evaluatedId");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeIsNull() {
            addCriterion("EVALUATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeIsNotNull() {
            addCriterion("EVALUATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeEqualTo(String value) {
            addCriterion("EVALUATE_TIME =", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotEqualTo(String value) {
            addCriterion("EVALUATE_TIME <>", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeGreaterThan(String value) {
            addCriterion("EVALUATE_TIME >", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATE_TIME >=", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeLessThan(String value) {
            addCriterion("EVALUATE_TIME <", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeLessThanOrEqualTo(String value) {
            addCriterion("EVALUATE_TIME <=", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeLike(String value) {
            addCriterion("EVALUATE_TIME like", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotLike(String value) {
            addCriterion("EVALUATE_TIME not like", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeIn(List<String> values) {
            addCriterion("EVALUATE_TIME in", values, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotIn(List<String> values) {
            addCriterion("EVALUATE_TIME not in", values, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeBetween(String value1, String value2) {
            addCriterion("EVALUATE_TIME between", value1, value2, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotBetween(String value1, String value2) {
            addCriterion("EVALUATE_TIME not between", value1, value2, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarIsNull() {
            addCriterion("EVALUATE_STAR is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarIsNotNull() {
            addCriterion("EVALUATE_STAR is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarEqualTo(String value) {
            addCriterion("EVALUATE_STAR =", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarNotEqualTo(String value) {
            addCriterion("EVALUATE_STAR <>", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarGreaterThan(String value) {
            addCriterion("EVALUATE_STAR >", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATE_STAR >=", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarLessThan(String value) {
            addCriterion("EVALUATE_STAR <", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarLessThanOrEqualTo(String value) {
            addCriterion("EVALUATE_STAR <=", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarLike(String value) {
            addCriterion("EVALUATE_STAR like", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarNotLike(String value) {
            addCriterion("EVALUATE_STAR not like", value, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarIn(List<String> values) {
            addCriterion("EVALUATE_STAR in", values, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarNotIn(List<String> values) {
            addCriterion("EVALUATE_STAR not in", values, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarBetween(String value1, String value2) {
            addCriterion("EVALUATE_STAR between", value1, value2, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateStarNotBetween(String value1, String value2) {
            addCriterion("EVALUATE_STAR not between", value1, value2, "evaluateStar");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIsNull() {
            addCriterion("EVALUATE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIsNotNull() {
            addCriterion("EVALUATE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT =", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT <>", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentGreaterThan(String value) {
            addCriterion("EVALUATE_CONTENT >", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT >=", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLessThan(String value) {
            addCriterion("EVALUATE_CONTENT <", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLessThanOrEqualTo(String value) {
            addCriterion("EVALUATE_CONTENT <=", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLike(String value) {
            addCriterion("EVALUATE_CONTENT like", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotLike(String value) {
            addCriterion("EVALUATE_CONTENT not like", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIn(List<String> values) {
            addCriterion("EVALUATE_CONTENT in", values, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotIn(List<String> values) {
            addCriterion("EVALUATE_CONTENT not in", values, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentBetween(String value1, String value2) {
            addCriterion("EVALUATE_CONTENT between", value1, value2, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotBetween(String value1, String value2) {
            addCriterion("EVALUATE_CONTENT not between", value1, value2, "evaluateContent");
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