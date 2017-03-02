package com.i360r.bpm.service.rs.process.api;

import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class ContinueTaskRequest extends TaskRequest implements
		IContinueTaskRequest {

	@NotNull
	private Boolean continuation;

	@Override
	public Boolean getContinuation() {
		return continuation;
	}

	@Override
	public void setContinuation(Boolean continuation) {
		this.continuation = continuation;
	}

}
