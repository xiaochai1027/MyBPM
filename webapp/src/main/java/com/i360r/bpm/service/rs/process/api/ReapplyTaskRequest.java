package com.i360r.bpm.service.rs.process.api;

import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class ReapplyTaskRequest extends TaskRequest implements
		IReapplyTaskRequest {

	@NotNull
	private Boolean reapply;
	
	@Override
	public Boolean getReapply() {
		return reapply;
	}

	@Override
	public void setReapply(Boolean reapply) {
		this.reapply = reapply;
	}

}
