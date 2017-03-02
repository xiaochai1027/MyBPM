package com.i360r.bpm.service.rs.process.loanRefund.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class LoanRefundDetailVO {
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String amount;
	@ProcessVariable
	private String note;
	@ProcessVariable
	private Integer loanApplyId;
	@ProcessVariable
	private String loanTypeCode;
	@ProcessVariable
	private String loanTypeName;
	@ProcessVariable
	private String actualTurnoverTime;

	public String getBusinessAreaCode() {
		return businessAreaCode;
	}

	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(Integer loanApplyId) {
		this.loanApplyId = loanApplyId;
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

	public String getActualTurnoverTime() {
		return actualTurnoverTime;
	}

	public void setActualTurnoverTime(String actualTurnoverTime) {
		this.actualTurnoverTime = actualTurnoverTime;
	}
	
	
}
