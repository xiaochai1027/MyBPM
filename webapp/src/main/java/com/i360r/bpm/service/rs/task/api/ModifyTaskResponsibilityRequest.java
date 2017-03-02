package com.i360r.bpm.service.rs.task.api;

public class ModifyTaskResponsibilityRequest {

	private String processInstanceId;
	private String responsibilityCode;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getResponsibilityCode() {
		return responsibilityCode;
	}
	public void setResponsibilityCode(String responsibilityCode) {
		this.responsibilityCode = responsibilityCode;
	}
	
	
}
