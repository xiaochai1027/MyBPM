package com.i360r.bpm.service.rs.process.loanApply.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class LoanApplyRequest {
	@ProcessVariable
	@NotNull
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	@NotNull
	private String businessAreaName;
	@ProcessVariable(isKeyword=true, keywordOrder=3)
	@NotNull
	private BigDecimal amount;
	@ProcessVariable
	private String note;
	@ProcessVariable
	@NotNull
	private String loanTypeCode;
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private String loanTypeName;

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
}
