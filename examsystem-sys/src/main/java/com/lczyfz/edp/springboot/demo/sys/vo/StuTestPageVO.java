package com.lczyfz.edp.springboot.demo.sys.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "StuTestPageVO", description = "学生考试列表信息")
public class StuTestPageVO {
    @ApiModelProperty(
            value = "testId",
            notes = "考试id",
            example = "1"
    )
    private Long testId;

    @ApiModelProperty(
            value = "testGroupId",
            notes = "考试分组id",
            example = "2"
    )
    private Long testGroupId;
    @ApiModelProperty(
            value = "testName",
            notes = "考试名称",
            example = "李四"
    )
    private String testName;

    @ApiModelProperty(
            value = "status",
            notes = "考试状态",
            example = "ing"
    )
    private String status;

    public Long getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(Long testGroupId) {
        this.testGroupId = testGroupId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String name) {
        this.testName = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StuTestPageVO{" +
                "testId=" + testId +
                ", testGroupId=" + testGroupId +
                ", testName='" + testName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
