package com.medical.manager.app.ambulance.web.query;

import java.util.ArrayList;
import java.util.List;

public class TblAlarmTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblAlarmTaskExample() {
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

        public Criteria andAlarmIdIsNull() {
            addCriterion("ALARM_ID is null");
            return (Criteria) this;
        }

        public Criteria andAlarmIdIsNotNull() {
            addCriterion("ALARM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmIdEqualTo(Long value) {
            addCriterion("ALARM_ID =", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotEqualTo(Long value) {
            addCriterion("ALARM_ID <>", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdGreaterThan(Long value) {
            addCriterion("ALARM_ID >", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ALARM_ID >=", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdLessThan(Long value) {
            addCriterion("ALARM_ID <", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdLessThanOrEqualTo(Long value) {
            addCriterion("ALARM_ID <=", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdIn(List<Long> values) {
            addCriterion("ALARM_ID in", values, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotIn(List<Long> values) {
            addCriterion("ALARM_ID not in", values, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdBetween(Long value1, Long value2) {
            addCriterion("ALARM_ID between", value1, value2, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotBetween(Long value1, Long value2) {
            addCriterion("ALARM_ID not between", value1, value2, "alarmId");
            return (Criteria) this;
        }

        public Criteria andCenterIdIsNull() {
            addCriterion("CENTER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCenterIdIsNotNull() {
            addCriterion("CENTER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCenterIdEqualTo(Long value) {
            addCriterion("CENTER_ID =", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdNotEqualTo(Long value) {
            addCriterion("CENTER_ID <>", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdGreaterThan(Long value) {
            addCriterion("CENTER_ID >", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CENTER_ID >=", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdLessThan(Long value) {
            addCriterion("CENTER_ID <", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdLessThanOrEqualTo(Long value) {
            addCriterion("CENTER_ID <=", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdIn(List<Long> values) {
            addCriterion("CENTER_ID in", values, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdNotIn(List<Long> values) {
            addCriterion("CENTER_ID not in", values, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdBetween(Long value1, Long value2) {
            addCriterion("CENTER_ID between", value1, value2, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdNotBetween(Long value1, Long value2) {
            addCriterion("CENTER_ID not between", value1, value2, "centerId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdIsNull() {
            addCriterion("AMBULANCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdIsNotNull() {
            addCriterion("AMBULANCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdEqualTo(Long value) {
            addCriterion("AMBULANCE_ID =", value, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdNotEqualTo(Long value) {
            addCriterion("AMBULANCE_ID <>", value, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdGreaterThan(Long value) {
            addCriterion("AMBULANCE_ID >", value, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("AMBULANCE_ID >=", value, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdLessThan(Long value) {
            addCriterion("AMBULANCE_ID <", value, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdLessThanOrEqualTo(Long value) {
            addCriterion("AMBULANCE_ID <=", value, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdIn(List<Long> values) {
            addCriterion("AMBULANCE_ID in", values, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdNotIn(List<Long> values) {
            addCriterion("AMBULANCE_ID not in", values, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdBetween(Long value1, Long value2) {
            addCriterion("AMBULANCE_ID between", value1, value2, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andAmbulanceIdNotBetween(Long value1, Long value2) {
            addCriterion("AMBULANCE_ID not between", value1, value2, "ambulanceId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNull() {
            addCriterion("TASK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("TASK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(String value) {
            addCriterion("TASK_STATUS =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(String value) {
            addCriterion("TASK_STATUS <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(String value) {
            addCriterion("TASK_STATUS >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(String value) {
            addCriterion("TASK_STATUS <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLike(String value) {
            addCriterion("TASK_STATUS like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotLike(String value) {
            addCriterion("TASK_STATUS not like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<String> values) {
            addCriterion("TASK_STATUS in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<String> values) {
            addCriterion("TASK_STATUS not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(String value1, String value2) {
            addCriterion("TASK_STATUS between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(String value1, String value2) {
            addCriterion("TASK_STATUS not between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskReasonIsNull() {
            addCriterion("TASK_REASON is null");
            return (Criteria) this;
        }

        public Criteria andTaskReasonIsNotNull() {
            addCriterion("TASK_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andTaskReasonEqualTo(String value) {
            addCriterion("TASK_REASON =", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonNotEqualTo(String value) {
            addCriterion("TASK_REASON <>", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonGreaterThan(String value) {
            addCriterion("TASK_REASON >", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_REASON >=", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonLessThan(String value) {
            addCriterion("TASK_REASON <", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonLessThanOrEqualTo(String value) {
            addCriterion("TASK_REASON <=", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonLike(String value) {
            addCriterion("TASK_REASON like", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonNotLike(String value) {
            addCriterion("TASK_REASON not like", value, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonIn(List<String> values) {
            addCriterion("TASK_REASON in", values, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonNotIn(List<String> values) {
            addCriterion("TASK_REASON not in", values, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonBetween(String value1, String value2) {
            addCriterion("TASK_REASON between", value1, value2, "taskReason");
            return (Criteria) this;
        }

        public Criteria andTaskReasonNotBetween(String value1, String value2) {
            addCriterion("TASK_REASON not between", value1, value2, "taskReason");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeIsNull() {
            addCriterion("DISTRIBUTE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeIsNotNull() {
            addCriterion("DISTRIBUTE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeEqualTo(String value) {
            addCriterion("DISTRIBUTE_TIME =", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeNotEqualTo(String value) {
            addCriterion("DISTRIBUTE_TIME <>", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeGreaterThan(String value) {
            addCriterion("DISTRIBUTE_TIME >", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTE_TIME >=", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeLessThan(String value) {
            addCriterion("DISTRIBUTE_TIME <", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeLessThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTE_TIME <=", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeLike(String value) {
            addCriterion("DISTRIBUTE_TIME like", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeNotLike(String value) {
            addCriterion("DISTRIBUTE_TIME not like", value, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeIn(List<String> values) {
            addCriterion("DISTRIBUTE_TIME in", values, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeNotIn(List<String> values) {
            addCriterion("DISTRIBUTE_TIME not in", values, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeBetween(String value1, String value2) {
            addCriterion("DISTRIBUTE_TIME between", value1, value2, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andDistributeTimeNotBetween(String value1, String value2) {
            addCriterion("DISTRIBUTE_TIME not between", value1, value2, "distributeTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIsNull() {
            addCriterion("TASK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIsNotNull() {
            addCriterion("TASK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTimeEqualTo(String value) {
            addCriterion("TASK_TIME =", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotEqualTo(String value) {
            addCriterion("TASK_TIME <>", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeGreaterThan(String value) {
            addCriterion("TASK_TIME >", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TIME >=", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeLessThan(String value) {
            addCriterion("TASK_TIME <", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeLessThanOrEqualTo(String value) {
            addCriterion("TASK_TIME <=", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeLike(String value) {
            addCriterion("TASK_TIME like", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotLike(String value) {
            addCriterion("TASK_TIME not like", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIn(List<String> values) {
            addCriterion("TASK_TIME in", values, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotIn(List<String> values) {
            addCriterion("TASK_TIME not in", values, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeBetween(String value1, String value2) {
            addCriterion("TASK_TIME between", value1, value2, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotBetween(String value1, String value2) {
            addCriterion("TASK_TIME not between", value1, value2, "taskTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeIsNull() {
            addCriterion("DEAL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDealTimeIsNotNull() {
            addCriterion("DEAL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDealTimeEqualTo(String value) {
            addCriterion("DEAL_TIME =", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeNotEqualTo(String value) {
            addCriterion("DEAL_TIME <>", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeGreaterThan(String value) {
            addCriterion("DEAL_TIME >", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DEAL_TIME >=", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeLessThan(String value) {
            addCriterion("DEAL_TIME <", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeLessThanOrEqualTo(String value) {
            addCriterion("DEAL_TIME <=", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeLike(String value) {
            addCriterion("DEAL_TIME like", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeNotLike(String value) {
            addCriterion("DEAL_TIME not like", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeIn(List<String> values) {
            addCriterion("DEAL_TIME in", values, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeNotIn(List<String> values) {
            addCriterion("DEAL_TIME not in", values, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeBetween(String value1, String value2) {
            addCriterion("DEAL_TIME between", value1, value2, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeNotBetween(String value1, String value2) {
            addCriterion("DEAL_TIME not between", value1, value2, "dealTime");
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