package com.ssm.util;

public class MessageResult {
	
	//执行成功0，失败1
	private Integer flag;
	
	//提示消息
	private String message;
	
	public MessageResult(Integer flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
