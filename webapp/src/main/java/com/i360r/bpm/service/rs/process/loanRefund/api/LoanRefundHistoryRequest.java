package com.i360r.bpm.service.rs.process.loanRefund.api;

import com.i360r.framework.common.service.rs.api.PagingRequest;

public class LoanRefundHistoryRequest extends PagingRequest{
	private String businessAreaCode;
	private String processInstanceId;

	public String getBusinessAreaCode() {
		return businessAreaCode;
	}

	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
