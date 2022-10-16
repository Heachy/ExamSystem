package com.lczyfz.edp.springboot.demo.sys.entity;

import com.lczyfz.edp.springboot.core.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 86189
 */
@ApiModel(value = "TestGroup", description = "考试信息")
@AllArgsConstructor
@NoArgsConstructor
public class TestGroup extends DataEntity<TestGroup> {

    @ApiModelProperty(
            value = "testId",
            notes = "分到的考试id",
            example = "1"
    )
    private Long testId;

    @ApiModelProperty(
            value = "studentId",
            notes = "学生id",
            example = "1"
    )
    private String studentId;

    @ApiModelProperty(
            value = "status",
            notes = "该学生的这场考试的状态",
            example = "notStart/ing/waitCorrect/end"
    )
    private String status;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public TestGroup(String createBy, Long testId,String studentId){
        this.isNewRecord=true;
        this.createBy=createBy;
        this.createDate=new Date();
        this.updateBy=createBy;
        this.updateDate=new Date();
        this.status="notStart";
        this.testId=testId;
        this.studentId= studentId;
    }
}