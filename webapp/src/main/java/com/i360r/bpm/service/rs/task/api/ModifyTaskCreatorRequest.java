package com.i360r.bpm.service.rs.task.api;

public class ModifyTaskCreatorRequest {

	private String  taskId; 
	private String  createdByCode;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}

}
