package com.i360r.bpm.service.rs.process.reservedCashApply.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class ReservedCashCashierConfirmRequest extends ApproveRequest{
	@ProcessVariable
	private String actualPaymentTime;

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}
	
	
}
