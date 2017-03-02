package com.i360r.bpm.service.rs.task.api;

import com.i360r.framework.common.service.rs.api.PagingRequest;

public class DeleteProcessRequest extends PagingRequest {

	private String  processInstanceId;
	private String  deleteReason;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	
}
