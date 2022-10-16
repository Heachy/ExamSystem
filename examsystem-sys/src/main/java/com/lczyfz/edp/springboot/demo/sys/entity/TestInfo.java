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
@ApiModel(value = "TestInfo", description = "考试信息")
@AllArgsConstructor
@NoArgsConstructor
public class TestInfo extends DataEntity<TestInfo> {

    @ApiModelProperty(
            value = "paperId",
            notes = "该考试使用的试卷id",
            example = "1"
    )
    private Long paperId;

    @ApiModelProperty(
            value = "testName",
            notes = "该考试的名称",
            example = "大物期中考"
    )
    private String testName;

    @ApiModelProperty(
            value = "timeLimit",
            notes = "该考试时长限制/min",
            example = "60"
    )
    private Integer timeLimit;

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public TestInfo(String createBy, String testName, Long paperId,int timeLimit, String remarks){
        this.isNewRecord=true;
        this.createBy=createBy;
        this.createDate=new Date();
        this.updateBy=createBy;
        this.updateDate=new Date();
        this.remarks=remarks;
        this.paperId=paperId;
        this.testName=testName;
        this.timeLimit=timeLimit;
    }

}