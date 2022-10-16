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
@ApiModel(value = "QuestionPageVO", description = "问题列表信息")
public class QuestionPageVO {
    @ApiModelProperty(
            value = "questionId",
            notes = "题目id",
            example = "10000"
    )
    private Long questionId;
    @ApiModelProperty(
            value = "描述",
            notes = "题目描述",
            example = "1+1等于几"
    )
    private String description;
    @ApiModelProperty(
            value = "图片链接",
            example = "http://xxxxx"
    )
    private String picture;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "QuestionPageVO{" +
                "questionId=" + questionId +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
