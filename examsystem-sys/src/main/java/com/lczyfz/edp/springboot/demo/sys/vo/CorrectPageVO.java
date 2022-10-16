package com.lczyfz.edp.springboot.demo.sys.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 86189
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CorrectPageVO", description = "待批改列表信息")
public class CorrectPageVO {
    @ApiModelProperty(
            value = "testGroupId",
            notes = "考试组号",
            example = "1"
    )
    private Long testGroupId;

    @ApiModelProperty(
            value = "stuName",
            notes = "学生姓名",
            example = "王五"
    )
    private String stuName;

    @ApiModelProperty(
            value = "stuId",
            notes = "学号",
            example = "10000"
    )
    private String stuId;

    @ApiModelProperty(
            value = "testName",
            notes = "考试的名称",
            example = "大物期中考"
    )
    private String testName;

    @ApiModelProperty(
            value = "submitTime",
            notes = "提交时间",
            example = "2022/10/02 15:30:30"
    )
    private Date submitTime;

    public Long getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(Long testGroupId) {
        this.testGroupId = testGroupId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "CorrectPageVO{" +
                "testGroupId=" + testGroupId +
                ", stuName='" + stuName + '\'' +
                ", stuId='" + stuId + '\'' +
                ", testName='" + testName + '\'' +
                ", submitTime=" + submitTime +
                '}';
    }
}
