package com.coretek.pack.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table log_info
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table log_info
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table log_info
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    public LogInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
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
     * This method corresponds to the database table log_info
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
     * This method corresponds to the database table log_info
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
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
     * This class corresponds to the database table log_info
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andDateTimeIsNull() {
            addCriterion("date_time is null");
            return (Criteria) this;
        }

        public Criteria andDateTimeIsNotNull() {
            addCriterion("date_time is not null");
            return (Criteria) this;
        }

        public Criteria andDateTimeEqualTo(Date value) {
            addCriterion("date_time =", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotEqualTo(Date value) {
            addCriterion("date_time <>", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeGreaterThan(Date value) {
            addCriterion("date_time >", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("date_time >=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLessThan(Date value) {
            addCriterion("date_time <", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("date_time <=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeIn(List<Date> values) {
            addCriterion("date_time in", values, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotIn(List<Date> values) {
            addCriterion("date_time not in", values, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeBetween(Date value1, Date value2) {
            addCriterion("date_time between", value1, value2, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("date_time not between", value1, value2, "dateTime");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureIsNull() {
            addCriterion("pack_mode_structure is null");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureIsNotNull() {
            addCriterion("pack_mode_structure is not null");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureEqualTo(String value) {
            addCriterion("pack_mode_structure =", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureNotEqualTo(String value) {
            addCriterion("pack_mode_structure <>", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureGreaterThan(String value) {
            addCriterion("pack_mode_structure >", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureGreaterThanOrEqualTo(String value) {
            addCriterion("pack_mode_structure >=", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureLessThan(String value) {
            addCriterion("pack_mode_structure <", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureLessThanOrEqualTo(String value) {
            addCriterion("pack_mode_structure <=", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureLike(String value) {
            addCriterion("pack_mode_structure like", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureNotLike(String value) {
            addCriterion("pack_mode_structure not like", value, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureIn(List<String> values) {
            addCriterion("pack_mode_structure in", values, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureNotIn(List<String> values) {
            addCriterion("pack_mode_structure not in", values, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureBetween(String value1, String value2) {
            addCriterion("pack_mode_structure between", value1, value2, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andPackModeStructureNotBetween(String value1, String value2) {
            addCriterion("pack_mode_structure not between", value1, value2, "packModeStructure");
            return (Criteria) this;
        }

        public Criteria andIndateIsNull() {
            addCriterion("indate is null");
            return (Criteria) this;
        }

        public Criteria andIndateIsNotNull() {
            addCriterion("indate is not null");
            return (Criteria) this;
        }

        public Criteria andIndateEqualTo(Integer value) {
            addCriterion("indate =", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotEqualTo(Integer value) {
            addCriterion("indate <>", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThan(Integer value) {
            addCriterion("indate >", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThanOrEqualTo(Integer value) {
            addCriterion("indate >=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThan(Integer value) {
            addCriterion("indate <", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThanOrEqualTo(Integer value) {
            addCriterion("indate <=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateIn(List<Integer> values) {
            addCriterion("indate in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotIn(List<Integer> values) {
            addCriterion("indate not in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateBetween(Integer value1, Integer value2) {
            addCriterion("indate between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotBetween(Integer value1, Integer value2) {
            addCriterion("indate not between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andVersionInfoIsNull() {
            addCriterion("version_info is null");
            return (Criteria) this;
        }

        public Criteria andVersionInfoIsNotNull() {
            addCriterion("version_info is not null");
            return (Criteria) this;
        }

        public Criteria andVersionInfoEqualTo(String value) {
            addCriterion("version_info =", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoNotEqualTo(String value) {
            addCriterion("version_info <>", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoGreaterThan(String value) {
            addCriterion("version_info >", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoGreaterThanOrEqualTo(String value) {
            addCriterion("version_info >=", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoLessThan(String value) {
            addCriterion("version_info <", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoLessThanOrEqualTo(String value) {
            addCriterion("version_info <=", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoLike(String value) {
            addCriterion("version_info like", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoNotLike(String value) {
            addCriterion("version_info not like", value, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoIn(List<String> values) {
            addCriterion("version_info in", values, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoNotIn(List<String> values) {
            addCriterion("version_info not in", values, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoBetween(String value1, String value2) {
            addCriterion("version_info between", value1, value2, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andVersionInfoNotBetween(String value1, String value2) {
            addCriterion("version_info not between", value1, value2, "versionInfo");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathIsNull() {
            addCriterion("svn_net_path is null");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathIsNotNull() {
            addCriterion("svn_net_path is not null");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathEqualTo(String value) {
            addCriterion("svn_net_path =", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathNotEqualTo(String value) {
            addCriterion("svn_net_path <>", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathGreaterThan(String value) {
            addCriterion("svn_net_path >", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathGreaterThanOrEqualTo(String value) {
            addCriterion("svn_net_path >=", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathLessThan(String value) {
            addCriterion("svn_net_path <", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathLessThanOrEqualTo(String value) {
            addCriterion("svn_net_path <=", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathLike(String value) {
            addCriterion("svn_net_path like", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathNotLike(String value) {
            addCriterion("svn_net_path not like", value, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathIn(List<String> values) {
            addCriterion("svn_net_path in", values, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathNotIn(List<String> values) {
            addCriterion("svn_net_path not in", values, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathBetween(String value1, String value2) {
            addCriterion("svn_net_path between", value1, value2, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andSvnNetPathNotBetween(String value1, String value2) {
            addCriterion("svn_net_path not between", value1, value2, "svnNetPath");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrIsNull() {
            addCriterion("date_time_str is null");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrIsNotNull() {
            addCriterion("date_time_str is not null");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrEqualTo(String value) {
            addCriterion("date_time_str =", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrNotEqualTo(String value) {
            addCriterion("date_time_str <>", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrGreaterThan(String value) {
            addCriterion("date_time_str >", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrGreaterThanOrEqualTo(String value) {
            addCriterion("date_time_str >=", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrLessThan(String value) {
            addCriterion("date_time_str <", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrLessThanOrEqualTo(String value) {
            addCriterion("date_time_str <=", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrLike(String value) {
            addCriterion("date_time_str like", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrNotLike(String value) {
            addCriterion("date_time_str not like", value, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrIn(List<String> values) {
            addCriterion("date_time_str in", values, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrNotIn(List<String> values) {
            addCriterion("date_time_str not in", values, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrBetween(String value1, String value2) {
            addCriterion("date_time_str between", value1, value2, "dateTimeStr");
            return (Criteria) this;
        }

        public Criteria andDateTimeStrNotBetween(String value1, String value2) {
            addCriterion("date_time_str not between", value1, value2, "dateTimeStr");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table log_info
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
     * This class corresponds to the database table log_info
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