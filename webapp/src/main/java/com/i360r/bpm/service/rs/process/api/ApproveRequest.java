package com.i360r.bpm.service.rs.process.api;

import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class ApproveRequest extends TaskRequest {

	@NotNull
	private Boolean approved;
	
	private String suggestion;

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
}
