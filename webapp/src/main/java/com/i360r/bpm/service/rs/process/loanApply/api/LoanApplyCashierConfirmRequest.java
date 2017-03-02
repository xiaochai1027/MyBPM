package com.i360r.bpm.service.rs.process.loanApply.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class LoanApplyCashierConfirmRequest extends ApproveRequest {
	@ProcessVariable
	private String actualPaymentTime;

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}
	
}
