package com.i360r.bpm.service.rs.process.vendor.bill.api;

import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;

public class VendorBillDataRequest extends ProcessUniqueVariableRequest {

	private String assignCode;
	private Integer billId;
	private String handleByName;

	public String getAssignCode() {
		return assignCode;
	}

	public void setAssignCode(String assignCode) {
		this.assignCode = assignCode;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getHandleByName() {
		return handleByName;
	}

	public void setHandleByName(String handleByName) {
		this.handleByName = handleByName;
	}

}
