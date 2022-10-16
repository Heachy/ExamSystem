package com.lczyfz.edp.springboot.demo.sys.entity;

import com.lczyfz.edp.springboot.core.entity.DataEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * test_paper_infoEntity
 * @author Haechi
 * @version 2022-10-08
 */

public class TestPaperInfo extends DataEntity<TestPaperInfo> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(
			value = "试卷名称",
			example = "物理第三章"
	)
	private String paperName;

	public TestPaperInfo() {
		this(null);
	}
	
	public TestPaperInfo(String id){
		super(id);
	}
	
	@NotBlank(message="试卷名称不能为空")
	@Size(min=0, max=10, message="试卷名称长度不能超过 10 个字符")
	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public TestPaperInfo(String createBy, String paperName, String remarks){
		this.paperName=paperName;
		this.createBy=createBy;
		this.createDate=new Date();
		this.updateBy=createBy;
		this.updateDate=new Date();
		this.remarks=remarks;
	}

	@Override
	public String toString() {
		return "TestPaperInfo{" +
				"paperName='" + paperName + '\'' +
				", remarks='" + remarks + '\'' +
				", createBy='" + createBy + '\'' +
				", createDate=" + createDate +
				", updateBy='" + updateBy + '\'' +
				", updateDate=" + updateDate +
				", id='" + id + '\'' +
				", isNewRecord=" + isNewRecord +
				'}';
	}
}