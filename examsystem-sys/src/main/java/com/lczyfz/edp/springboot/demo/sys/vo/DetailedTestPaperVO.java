package com.lczyfz.edp.springboot.demo.sys.vo;

import com.lczyfz.edp.springboot.demo.sys.entity.TestPaperInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 86189
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DetailedTestPaperVO", description = "详细的试卷")
public class DetailedTestPaperVO {
    @ApiModelProperty(
            value = "试卷id",
            example = "2"
    )
    private Long paperId;

    @ApiModelProperty(
            value = "试卷名称",
            example = "大物期中考"
    )
    private String paperName;

    @ApiModelProperty(
            value = "备注",
            example = "不难"
    )
    private String remarks;

    @ApiModelProperty(
            value = "题目列表"
    )
    private List<SimpleQuestionVO> questionList;

    @ApiModelProperty(
            value = "是否是老师",
            example = "true"
    )
    private Boolean isTeacher;

    public DetailedTestPaperVO(TestPaperInfo testPaperInfo, List<SimpleQuestionVO> questionList){
        this.paperId= Long.valueOf(testPaperInfo.getId());
        this.paperName=testPaperInfo.getPaperName();
        this.remarks=testPaperInfo.getRemarks();
        this.questionList=questionList;
        this.isTeacher=true;
    }

}
