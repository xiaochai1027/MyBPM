package com.i360r.bpm.service.rs.process.api;

import com.i360r.framework.common.service.rs.api.PagingRequest;

public class ProcessDetailRequest extends PagingRequest{

	private String processInstanceId;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
