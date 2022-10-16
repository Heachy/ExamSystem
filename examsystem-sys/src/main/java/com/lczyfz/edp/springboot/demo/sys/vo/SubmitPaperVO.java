package com.lczyfz.edp.springboot.demo.sys.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 86189
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SubmitPaperVO", description = "含学生提交答案的试卷信息")
public class SubmitPaperVO {

    @ApiModelProperty(
            value = "detailedTestPaperVO",
            notes = "试卷信息"
    )
    DetailedTestPaperVO detailedTestPaperVO;

    @ApiModelProperty(
            value = "answerPictureUrl",
            notes = "答案图片链接",
            example = "http://xxxxxx"
    )
    String answerPictureUrl;

    @ApiModelProperty(
            value = "correctId",
            notes = "改的试卷id",
            example = "1"
    )
    Long correctId;

    @ApiModelProperty(
            value = "score",
            notes = "成绩",
            example = "100"
    )
    Integer score;

    @ApiModelProperty(
            value = "correctFlag",
            notes = "是否批改标记,批改过显示成绩，否则不显示",
            example = "true"
    )
    Boolean correctFlag;

}
