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
@ApiModel(value = "CorrectInfo", description = "试卷批改信息")
@AllArgsConstructor
@NoArgsConstructor
public class CorrectInfo extends DataEntity<CorrectInfo> {

    @ApiModelProperty(
            value = "testGroupId",
            notes = "该考试分组id",
            example = "1"
    )
    private Long testGroupId;

    @ApiModelProperty(
            value = "studentId",
            notes = "学生id",
            example = "10000"
    )
    private String studentId;

    @ApiModelProperty(
            value = "score",
            notes = "该试卷得分",
            example = "100"
    )
    private Byte score;

    @ApiModelProperty(
            value = "answerPicture",
            notes = "学生提交的答案图片链接",
            example = "http://xxxx"
    )
    private String answerPicture;

    @ApiModelProperty(
            value = "correctFlag",
            notes = "该考卷是否批改",
            example = "1"
    )
    private Boolean correctFlag;


    public Long getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(Long testGroupId) {
        this.testGroupId = testGroupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }


    public String getAnswerPicture() {
        return answerPicture;
    }

    public void setAnswerPicture(String answerPicture) {
        this.answerPicture = answerPicture == null ? null : answerPicture.trim();
    }

    public Boolean getCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(Boolean correctFlag) {
        this.correctFlag = correctFlag;
    }

    public CorrectInfo(String studentId,String answerPicture,Long testGroupId){
        this.correctFlag=false;
        this.answerPicture=answerPicture;
        this.score=0;
        this.testGroupId=testGroupId;
        this.studentId=studentId;
        this.createBy=studentId;
        this.createDate=new Date();
        this.updateBy=studentId;
        this.updateDate=new Date();
    }
}