package com.ssm.pojo;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @description 基本类用户记录创建时间和修改时间

 * @version 1.0
 */
public abstract class BasePojo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date createTime;
	private Date updateTime;


	private Long createBy;
	private Long updateBy;

	private String createName;
	private String updateName;

	

	//是否废弃
	private Integer isActivited;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getIsActivited() {
		return isActivited;
	}

	public void setIsActivited(Integer isActivited) {
		this.isActivited = isActivited;
	}
}
