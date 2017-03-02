package com.i360r.bpm.service.rs.process.deliverystaffleave.api;

import com.i360r.framework.common.service.rs.api.PagingRequest;

public class GetLeaveListRequest extends PagingRequest {
	private String processStatusCode;

	public String getProcessStatusCode() {
		return processStatusCode;
	}

	public void setProcessStatusCode(String processStatusCode) {
		this.processStatusCode = processStatusCode;
	}

}
