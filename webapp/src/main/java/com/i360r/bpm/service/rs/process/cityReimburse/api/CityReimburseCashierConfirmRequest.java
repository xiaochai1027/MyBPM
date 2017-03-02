package com.i360r.bpm.service.rs.process.cityReimburse.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class CityReimburseCashierConfirmRequest extends CityReimburseRequest {
	@ProcessVariable
	private String actualPaymentTime;

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}

}
