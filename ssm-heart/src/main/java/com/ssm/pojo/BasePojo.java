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




	public Integer getIsActivited() {
		return isActivited;
	}

	public void setIsActivited(Integer isActivited) {
		this.isActivited = isActivited;
	}
}
