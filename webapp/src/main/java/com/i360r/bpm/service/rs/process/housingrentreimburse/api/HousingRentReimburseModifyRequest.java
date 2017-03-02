package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

import com.i360r.bpm.service.rs.process.api.IReapplyTaskRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HousingRentReimburseModifyRequest extends
		HousingRentReimburseRequest implements IReapplyTaskRequest {

	@NotNull
	private String taskId;
	@NotNull
	private Boolean reapply;
	
	@Override
	public String getTaskId() {
		return this.taskId;
	}

	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public Boolean getReapply() {
		return this.reapply;
	}

	@Override
	public void setReapply(Boolean reapply) {
		this.reapply = reapply;
	}

}
