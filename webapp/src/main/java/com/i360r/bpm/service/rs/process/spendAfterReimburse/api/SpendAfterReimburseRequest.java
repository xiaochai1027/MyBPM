package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class SpendAfterReimburseRequest {
	
	@ProcessVariable
	@NotNull
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	@NotNull
	private String businessAreaName;
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private BigDecimal totalAmount;
	@ProcessVariable
	private String note;

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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
