package com.i360r.bpm.service.rs.process.loanRefund.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class LoanRefundCashierConfirmRequest extends ApproveRequest{
	@ProcessVariable
	private String actualTurnoverTime;

	public String getActualTurnoverTime() {
		return actualTurnoverTime;
	}

	public void setActualTurnoverTime(String actualTurnoverTime) {
		this.actualTurnoverTime = actualTurnoverTime;
	}

	
	
}
