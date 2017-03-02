package com.i360r.bpm.service.rs.process.vendor.bill.api;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class ReassignBillHandleRequest extends ApproveRequest {
	private String assignCode;

	public String getAssignCode() {
		return assignCode;
	}

	public void setAssignCode(String assignCode) {
		this.assignCode = assignCode;
	}

}
