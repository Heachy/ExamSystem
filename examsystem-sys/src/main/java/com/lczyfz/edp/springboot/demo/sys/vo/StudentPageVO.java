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
@ApiModel(value = "StudentPageVO", description = "学生列表信息")
public class StudentPageVO {
    @ApiModelProperty(
            value = "studentId",
            notes = "学生id",
            example = "10000"
    )
    private String studentId;
    @ApiModelProperty(
            value = "name",
            notes = "名字",
            example = "李四"
    )
    private String name;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentPageVO{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
