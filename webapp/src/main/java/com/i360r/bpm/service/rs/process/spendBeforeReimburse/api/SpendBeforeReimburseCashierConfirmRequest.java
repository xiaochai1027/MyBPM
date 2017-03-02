package com.i360r.bpm.service.rs.process.spendBeforeReimburse.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class SpendBeforeReimburseCashierConfirmRequest extends SpendBeforeReimburseRequest {
	@ProcessVariable
	private String actualPaymentTime;

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}

}
