package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import com.i360r.framework.common.service.rs.api.PagingRequest;

public class SpendAfterReimburseHistoryItemRequest extends PagingRequest{
	private String processInstanceId;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}
