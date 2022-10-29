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
@ApiModel(value = "SimpleQuestionVO", description = "问题大概信息")
public class SimpleQuestionVO {
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

    @ApiModelProperty(
            value = "试题分数",
            example = "20"
    )
    private int score;

    @ApiModelProperty(
            value = "所属组别id",
            example = "2"
    )
    private Long groupId;
}
