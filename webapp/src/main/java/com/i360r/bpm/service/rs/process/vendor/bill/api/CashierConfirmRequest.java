package com.i360r.bpm.service.rs.process.vendor.bill.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class CashierConfirmRequest extends ApproveRequest {
	private Integer billId;
	
	@ProcessVariable
	private String receivedDate;

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

}
