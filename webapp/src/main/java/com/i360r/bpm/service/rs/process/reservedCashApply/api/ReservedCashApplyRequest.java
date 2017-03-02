package com.i360r.bpm.service.rs.process.reservedCashApply.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class ReservedCashApplyRequest {
	@ProcessVariable(isUnique=true, uniqueScope=ProcessUniqueScope.UNFINISHED, showName="备用金申请/变更")
	@NotNull
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	@NotNull
	private String businessAreaName;
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private BigDecimal changeAmount;
	@ProcessVariable
	@NotNull
	private BigDecimal reservedCashAmount;
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

	public BigDecimal getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getReservedCashAmount() {
		return reservedCashAmount;
	}

	public void setReservedCashAmount(BigDecimal reservedCashAmount) {
		this.reservedCashAmount = reservedCashAmount;
	}
}
