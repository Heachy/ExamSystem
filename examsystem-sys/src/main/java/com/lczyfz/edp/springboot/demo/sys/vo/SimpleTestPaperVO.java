package com.lczyfz.edp.springboot.demo.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 86189
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SimpleTestPaperVO", description = "试卷列表信息")
public class SimpleTestPaperVO {
    @ApiModelProperty(
            value = "paperId",
            notes = "试卷id",
            example = "10000"
    )
    private Long paperId;
    @ApiModelProperty(
            value = "teacherName",
            notes = "试卷创建者名字",
            example = "张三"
    )
    private String teacherName;
    @ApiModelProperty(
            value = "paperName",
            notes = "试卷名称",
            example = "http://xxxxx"
    )
    private String paperName;

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTestName() {
        return paperName;
    }

    public void setTestName(String testName) {
        this.paperName = testName;
    }

    @Override
    public String toString() {
        return "SimpleTestPaperVO{" +
                "paperId=" + paperId +
                ", teacherName='" + teacherName + '\'' +
                ", testName='" + paperName + '\'' +
                '}';
    }
}
