package com.i360r.bpm.business.model;

public enum MessageReceiveType {
	APP_PUSH(1, "APP_PUSH", "APP推送消息"), 
	INNER_MESSAGE(2, "INNER_MESSAGE", "站内消息"), 
	MOBILE_MESSAGE(3, "MOBILE_MESSAGE","短信消息");

	private Integer id;
	private String code;
	private String description;

	private MessageReceiveType(Integer id, String code, String description) {
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
