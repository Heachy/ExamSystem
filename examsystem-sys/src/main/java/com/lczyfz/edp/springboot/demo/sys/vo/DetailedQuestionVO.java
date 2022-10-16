package com.lczyfz.edp.springboot.demo.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lczyfz.edp.springboot.demo.sys.entity.QuestionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 86189
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Question", description = "问题信息")
public class DetailedQuestionVO {
    @ApiModelProperty(
            value = "id",
            notes = "题目id",
            example = "10000"
    )
    private Long id;
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
    @ApiModelProperty(
            value = "创建者id",
            example = "111"
    )
    private String creator;

    @ApiModelProperty(
            value = "是否是老师",
            example = "true"
    )
    private Boolean isTeacher;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(
            value = "创建时间",
            example = "2022-10-03 20:21:36"
    )
    private Date createDate;
    public DetailedQuestionVO(QuestionInfo questionInfo, Boolean isTeacher){
        this.id= Long.valueOf(questionInfo.getId());
        this.creator=questionInfo.getCreateBy();
        this.description=questionInfo.getDescription();
        this.createDate=questionInfo.getCreateDate();
        this.picture=questionInfo.getPicture();
        this.isTeacher=isTeacher;
    }
}
