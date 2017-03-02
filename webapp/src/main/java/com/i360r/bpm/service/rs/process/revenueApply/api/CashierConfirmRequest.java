package com.i360r.bpm.service.rs.process.revenueApply.api;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class CashierConfirmRequest extends ApproveRequest {
	private String payTime;

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
}
