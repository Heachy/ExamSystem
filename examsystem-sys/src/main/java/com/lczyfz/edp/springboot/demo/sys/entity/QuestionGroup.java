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
@ApiModel(value = "QuestionGroup", description = "问题分组")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGroup extends DataEntity<QuestionGroup> {

    @ApiModelProperty(
            value = "所属试卷id",
            example = "2"
    )
    private Long paperId;

    @ApiModelProperty(
            value = "该题题目id",
            example = "3"
    )
    private Long questionId;

    @ApiModelProperty(
            value = "该题在这份试卷中所占分数",
            example = "20"
    )
    private Byte score;

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }


    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }

    public QuestionGroup(String createBy,long paperId,long questionId,byte score){
        this.paperId=paperId;
        this.questionId=questionId;
        this.createBy=createBy;
        this.score=score;
        this.createDate=new Date();
        this.updateBy=createBy;
        this.updateDate=new Date();
        this.isNewRecord=true;
    }

    @Override
    public String toString() {
        return "QuestionGroup{" +
                "paperId=" + paperId +
                ", questionId=" + questionId +
                ", score=" + score +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", id='" + id+
                '}';
    }
}