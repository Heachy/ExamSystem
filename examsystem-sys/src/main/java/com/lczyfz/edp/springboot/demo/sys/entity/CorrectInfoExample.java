package com.lczyfz.edp.springboot.demo.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CorrectInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CorrectInfoExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdIsNull() {
            addCriterion("test_group_id is null");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdIsNotNull() {
            addCriterion("test_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdEqualTo(Long value) {
            addCriterion("test_group_id =", value, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdNotEqualTo(Long value) {
            addCriterion("test_group_id <>", value, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdGreaterThan(Long value) {
            addCriterion("test_group_id >", value, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_group_id >=", value, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdLessThan(Long value) {
            addCriterion("test_group_id <", value, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("test_group_id <=", value, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdIn(List<Long> values) {
            addCriterion("test_group_id in", values, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdNotIn(List<Long> values) {
            addCriterion("test_group_id not in", values, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdBetween(Long value1, Long value2) {
            addCriterion("test_group_id between", value1, value2, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andTestGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("test_group_id not between", value1, value2, "testGroupId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Byte value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Byte value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Byte value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Byte value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Byte value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Byte value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Byte> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Byte> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Byte value1, Byte value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Byte value1, Byte value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Boolean value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Boolean value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Boolean value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Boolean value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Boolean> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Boolean> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordIsNull() {
            addCriterion("is_new_record is null");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordIsNotNull() {
            addCriterion("is_new_record is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordEqualTo(Byte value) {
            addCriterion("is_new_record =", value, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordNotEqualTo(Byte value) {
            addCriterion("is_new_record <>", value, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordGreaterThan(Byte value) {
            addCriterion("is_new_record >", value, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_new_record >=", value, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordLessThan(Byte value) {
            addCriterion("is_new_record <", value, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordLessThanOrEqualTo(Byte value) {
            addCriterion("is_new_record <=", value, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordIn(List<Byte> values) {
            addCriterion("is_new_record in", values, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordNotIn(List<Byte> values) {
            addCriterion("is_new_record not in", values, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordBetween(Byte value1, Byte value2) {
            addCriterion("is_new_record between", value1, value2, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andIsNewRecordNotBetween(Byte value1, Byte value2) {
            addCriterion("is_new_record not between", value1, value2, "isNewRecord");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureIsNull() {
            addCriterion("answer_picture is null");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureIsNotNull() {
            addCriterion("answer_picture is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureEqualTo(String value) {
            addCriterion("answer_picture =", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureNotEqualTo(String value) {
            addCriterion("answer_picture <>", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureGreaterThan(String value) {
            addCriterion("answer_picture >", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureGreaterThanOrEqualTo(String value) {
            addCriterion("answer_picture >=", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureLessThan(String value) {
            addCriterion("answer_picture <", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureLessThanOrEqualTo(String value) {
            addCriterion("answer_picture <=", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureLike(String value) {
            addCriterion("answer_picture like", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureNotLike(String value) {
            addCriterion("answer_picture not like", value, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureIn(List<String> values) {
            addCriterion("answer_picture in", values, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureNotIn(List<String> values) {
            addCriterion("answer_picture not in", values, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureBetween(String value1, String value2) {
            addCriterion("answer_picture between", value1, value2, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andAnswerPictureNotBetween(String value1, String value2) {
            addCriterion("answer_picture not between", value1, value2, "answerPicture");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagIsNull() {
            addCriterion("correct_flag is null");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagIsNotNull() {
            addCriterion("correct_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagEqualTo(Boolean value) {
            addCriterion("correct_flag =", value, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagNotEqualTo(Boolean value) {
            addCriterion("correct_flag <>", value, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagGreaterThan(Boolean value) {
            addCriterion("correct_flag >", value, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("correct_flag >=", value, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagLessThan(Boolean value) {
            addCriterion("correct_flag <", value, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("correct_flag <=", value, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagIn(List<Boolean> values) {
            addCriterion("correct_flag in", values, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagNotIn(List<Boolean> values) {
            addCriterion("correct_flag not in", values, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("correct_flag between", value1, value2, "correctFlag");
            return (Criteria) this;
        }

        public Criteria andCorrectFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("correct_flag not between", value1, value2, "correctFlag");
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