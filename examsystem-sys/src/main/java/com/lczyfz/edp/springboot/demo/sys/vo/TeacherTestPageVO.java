package com.lczyfz.edp.springboot.demo.sys.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TeacherTestPageVO", description = "教师创建的考试列表信息")
public class TeacherTestPageVO {
    @ApiModelProperty(
            value = "testId",
            notes = "考试id",
            example = "1"
    )
    private Long testId;
    @ApiModelProperty(
            value = "testName",
            notes = "考试名称",
            example = "李四"
    )
    private String testName;

    @ApiModelProperty(
            value = "timeLimit",
            notes = "考试时间限制",
            example = "60/min"
    )
    private Integer timeLimit;

    @ApiModelProperty(
            value = "createDate",
            notes = "创建考试时间",
            example = "2020/02/22 14:22:22"
    )
    private Date createDate;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "TeacherTestPageVO{" +
                "testId=" + testId +
                ", name='" + testName + '\'' +
                ", timeLimit=" + timeLimit +
                ", createDate=" + createDate +
                '}';
    }
}
