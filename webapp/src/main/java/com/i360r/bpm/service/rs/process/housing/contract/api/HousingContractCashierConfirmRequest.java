package com.i360r.bpm.service.rs.process.housing.contract.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class HousingContractCashierConfirmRequest extends ApproveRequest {
	@ProcessVariable
	private String actualPaymentDate;

	public String getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(String actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}
	
}
