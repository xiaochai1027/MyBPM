package com.i360r.bpm.service.rs.process.loanRefund.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.AbstractUniqueIdRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class LoanRefundRequest extends AbstractUniqueIdRequest {
	@ProcessVariable
	@NotNull
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	@NotNull
	private String businessAreaName;
	@ProcessVariable(isKeyword=true, keywordOrder=3)
	@NotNull
	private String amount;
	@ProcessVariable
	private String note;
	@ProcessVariable(isUnique=true, showName="借款上缴")
	@NotNull
	private Integer loanApplyId;
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
}
