package com.i360r.bpm.service.rs.process.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class BusinessAreaBasedRequest {

	@ProcessVariable
	@NotNull
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true,keywordOrder=2)
	@NotNull
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
