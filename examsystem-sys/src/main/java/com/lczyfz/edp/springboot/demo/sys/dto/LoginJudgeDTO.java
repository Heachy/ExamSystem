package com.lczyfz.edp.springboot.demo.sys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 86189
 * 验证用户密码传递信息的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginJudgeDTO {

    private Boolean judge;

    private String mes;

    private Integer role;

    @Override
    public String toString() {
        return "JudgeDTO{" +
                "judge=" + judge +
                ", mes='" + mes + '\'' +
                ", role=" + role +
                '}';
    }
}
