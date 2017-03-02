package com.i360r.bpm.service.rs.process.loanApply.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;

public class LoanApplyDetailVO {
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private BigDecimal amount;
	@ProcessVariable
	private String note;
	@ProcessVariable
	private String loanTypeCode;
	@ProcessVariable
	private String loanTypeName;
	@ProcessVariable
	private String actualPaymentTime;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLoanTypeCode() {
		return loanTypeCode;
	}

	public void setLoanTypeCode(String loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}
	
	
}
