package com.lczyfz.edp.springboot.demo.sys.entity;

import com.lczyfz.edp.springboot.core.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * question_infoEntity
 * @author Haechi
 * @version 2022-10-03
 */
@ApiModel(value = "Question", description = "问题")
@AllArgsConstructor
public class QuestionInfo extends DataEntity<QuestionInfo> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(
			value = "问题描述",
			example = "1+1等于几"
	)
	private String description;
	@ApiModelProperty(
			value = "图片链接",
			example = "http://xxxxxxx"
	)
	private String picture;


	public QuestionInfo() {
		this(null);
	}

	public QuestionInfo(String id){
		super(id);
	}
	
	@NotBlank(message="问题描述不能为空")
	@Size(min=0, max=100, message="问题描述长度不能超过 100 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank(message="相关图片,可为空不能为空")
	@Size(min=0, max=100, message="相关图片,可为空长度不能超过 100 个字符")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public QuestionInfo(String createBy, String description, String picture, String remarks){
		this.isNewRecord=true;
		this.description=description;
		this.createBy=createBy;
		this.picture=picture;
		this.createDate=new Date();
		this.updateBy=createBy;
		this.updateDate=new Date();
		this.remarks=remarks;
	}

	public QuestionInfo(String description, String picture, String remarks){
		this.isNewRecord=true;
		this.description=description;
		this.picture=picture;
		this.createDate=new Date();
		this.updateDate=new Date();
		this.remarks=remarks;
	}


	
}