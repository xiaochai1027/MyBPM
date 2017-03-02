package com.i360r.bpm.service.rs.process.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class BusinessAreaBasedVO {

	@ProcessVariable
	private String businessAreaCode;

	@ProcessVariable
	private String businessAreaName;

	public String getBusinessAreaCode() {
		return businessAreaCode;
	}

	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}

	public String getBusinessAreaName() {
		return businessAreaName;
	}

	public void setBusinessAreaName(String businessAreaName) {
		this.businessAreaName = businessAreaName;
	}
}
