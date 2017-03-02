package com.i360r.bpm.service.rs.process.api;

import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class TaskRequest implements ITaskRequest {

	@NotNull
	private String taskId;

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
