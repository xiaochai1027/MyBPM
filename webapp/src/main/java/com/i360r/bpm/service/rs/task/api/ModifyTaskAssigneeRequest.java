package com.i360r.bpm.service.rs.task.api;

public class ModifyTaskAssigneeRequest {

	private String   taskId;
	private String   assigneeCode;

	public String getTaskId() {
		return taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAssigneeCode() {
		return assigneeCode;
	}

	public void setAssigneeCode(String assigneeCode) {
		this.assigneeCode = assigneeCode;
	}
	
}
