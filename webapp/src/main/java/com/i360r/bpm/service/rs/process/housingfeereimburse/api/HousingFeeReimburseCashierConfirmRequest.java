package com.i360r.bpm.service.rs.process.housingfeereimburse.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class HousingFeeReimburseCashierConfirmRequest extends HousingFeeReimburseRequest {
	
	@ProcessVariable
	private String actualPaymentDate;

	public String getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(String actualPaymentDate) {
		this.actualPaymentDate= actualPaymentDate;
	}
}
