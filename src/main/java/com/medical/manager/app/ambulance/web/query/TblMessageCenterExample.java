package com.medical.manager.app.ambulance.web.query;

import java.util.ArrayList;
import java.util.List;

public class TblMessageCenterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblMessageCenterExample() {
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

        public Criteria andMessageTitleIsNull() {
            addCriterion("MESSAGE_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNotNull() {
            addCriterion("MESSAGE_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleEqualTo(String value) {
            addCriterion("MESSAGE_TITLE =", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotEqualTo(String value) {
            addCriterion("MESSAGE_TITLE <>", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThan(String value) {
            addCriterion("MESSAGE_TITLE >", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGE_TITLE >=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThan(String value) {
            addCriterion("MESSAGE_TITLE <", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThanOrEqualTo(String value) {
            addCriterion("MESSAGE_TITLE <=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLike(String value) {
            addCriterion("MESSAGE_TITLE like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotLike(String value) {
            addCriterion("MESSAGE_TITLE not like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIn(List<String> values) {
            addCriterion("MESSAGE_TITLE in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotIn(List<String> values) {
            addCriterion("MESSAGE_TITLE not in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleBetween(String value1, String value2) {
            addCriterion("MESSAGE_TITLE between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotBetween(String value1, String value2) {
            addCriterion("MESSAGE_TITLE not between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTimeIsNull() {
            addCriterion("MESSAGE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMessageTimeIsNotNull() {
            addCriterion("MESSAGE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTimeEqualTo(String value) {
            addCriterion("MESSAGE_TIME =", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeNotEqualTo(String value) {
            addCriterion("MESSAGE_TIME <>", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeGreaterThan(String value) {
            addCriterion("MESSAGE_TIME >", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGE_TIME >=", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeLessThan(String value) {
            addCriterion("MESSAGE_TIME <", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeLessThanOrEqualTo(String value) {
            addCriterion("MESSAGE_TIME <=", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeLike(String value) {
            addCriterion("MESSAGE_TIME like", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeNotLike(String value) {
            addCriterion("MESSAGE_TIME not like", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeIn(List<String> values) {
            addCriterion("MESSAGE_TIME in", values, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeNotIn(List<String> values) {
            addCriterion("MESSAGE_TIME not in", values, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeBetween(String value1, String value2) {
            addCriterion("MESSAGE_TIME between", value1, value2, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeNotBetween(String value1, String value2) {
            addCriterion("MESSAGE_TIME not between", value1, value2, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNull() {
            addCriterion("MESSAGE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNotNull() {
            addCriterion("MESSAGE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andMessageContentEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT =", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT <>", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThan(String value) {
            addCriterion("MESSAGE_CONTENT >", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT >=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThan(String value) {
            addCriterion("MESSAGE_CONTENT <", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThanOrEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT <=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLike(String value) {
            addCriterion("MESSAGE_CONTENT like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotLike(String value) {
            addCriterion("MESSAGE_CONTENT not like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentIn(List<String> values) {
            addCriterion("MESSAGE_CONTENT in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotIn(List<String> values) {
            addCriterion("MESSAGE_CONTENT not in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentBetween(String value1, String value2) {
            addCriterion("MESSAGE_CONTENT between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotBetween(String value1, String value2) {
            addCriterion("MESSAGE_CONTENT not between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNull() {
            addCriterion("IS_READ is null");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNotNull() {
            addCriterion("IS_READ is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadEqualTo(String value) {
            addCriterion("IS_READ =", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotEqualTo(String value) {
            addCriterion("IS_READ <>", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThan(String value) {
            addCriterion("IS_READ >", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThanOrEqualTo(String value) {
            addCriterion("IS_READ >=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThan(String value) {
            addCriterion("IS_READ <", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThanOrEqualTo(String value) {
            addCriterion("IS_READ <=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLike(String value) {
            addCriterion("IS_READ like", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotLike(String value) {
            addCriterion("IS_READ not like", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadIn(List<String> values) {
            addCriterion("IS_READ in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotIn(List<String> values) {
            addCriterion("IS_READ not in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadBetween(String value1, String value2) {
            addCriterion("IS_READ between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotBetween(String value1, String value2) {
            addCriterion("IS_READ not between", value1, value2, "isRead");
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