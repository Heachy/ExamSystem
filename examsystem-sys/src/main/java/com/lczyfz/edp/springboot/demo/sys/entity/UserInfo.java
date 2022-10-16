package com.lczyfz.edp.springboot.demo.sys.entity;

import com.lczyfz.edp.springboot.core.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * @author 86189
 */
@ApiModel(value = "User", description = "用户")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends DataEntity<UserInfo> {

    @ApiModelProperty(
            value = "用户角色",
            example = "1/老师,2/学生"
    )
    private Byte role;

    @ApiModelProperty(
            value = "密码",
            example = "123456"
    )
    private String password;

    @ApiModelProperty(
            value = "姓名",
            example = "张三"
    )
    private String name;

    @ApiModelProperty(
            value = "邮箱",
            example = "xxxx@qq.com"
    )
    private String mail;

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }
}