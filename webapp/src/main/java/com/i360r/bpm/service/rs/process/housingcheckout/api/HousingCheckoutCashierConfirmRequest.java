package com.i360r.bpm.service.rs.process.housingcheckout.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class HousingCheckoutCashierConfirmRequest extends ApproveRequest {
	
	@ProcessVariable
	private String actualTurnoverDate;

	public String getActualTurnoverDate() {
		return actualTurnoverDate;
	}

	public void setActualTurnoverDate(String actualTurnoverDate) {
		this.actualTurnoverDate = actualTurnoverDate;
	}

}
