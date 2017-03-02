package com.i360r.bpm.service.rs.process.reservedCashApply.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.reservedCashApply.ReservedCashApplyConstants;

public class ReservedCashApplyDetailVO {
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private BigDecimal changeAmount;
	@ProcessVariable
	private BigDecimal reservedCashAmount;
	@ProcessVariable
	private String note;
	@ProcessVariable(key=ReservedCashApplyConstants.TASK_EMPLOYEE_POSITION)
	private String applyRole;
	@ProcessVariable(key=ReservedCashApplyConstants.TASK_EMPLOYEE_MOBILE)
	private String applyMobile;
	@ProcessVariable
	private String actualPaymentTime;
	@ProcessVariable
	private String createdByName;

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

	public String getApplyRole() {
		return applyRole;
	}

	public void setApplyRole(String applyRole) {
		this.applyRole = applyRole;
	}

	public String getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile;
	}

	public BigDecimal getReservedCashAmount() {
		return reservedCashAmount;
	}

	public void setReservedCashAmount(BigDecimal reservedCashAmount) {
		this.reservedCashAmount = reservedCashAmount;
	}

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	
	
}
