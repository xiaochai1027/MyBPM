package com.i360r.bpm.service.rs.process.revenueApply.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.revenueApply.RevenueApplyConstants;

public class RevenueApplyDetailVO {
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private BigDecimal amount;
	@ProcessVariable
	private BigDecimal currentAmount;
	@ProcessVariable
	private String note;
	@ProcessVariable
	private String revenueTypeCode;
	@ProcessVariable
	private String revenueTypeName;
	@ProcessVariable
	private String revenueCreateTime;
	@ProcessVariable
	private Integer revenueId;
	@ProcessVariable(key=RevenueApplyConstants.TASK_EMPLOYEE_POSITION)
	private String applyRole;
	@ProcessVariable(key=RevenueApplyConstants.TASK_EMPLOYEE_MOBILE)
	private String applyMobile;

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

	public String getRevenueTypeCode() {
		return revenueTypeCode;
	}

	public void setRevenueTypeCode(String revenueTypeCode) {
		this.revenueTypeCode = revenueTypeCode;
	}

	public Integer getRevenueId() {
		return revenueId;
	}

	public void setRevenueId(Integer revenueId) {
		this.revenueId = revenueId;
	}

	public String getRevenueTypeName() {
		return revenueTypeName;
	}

	public void setRevenueTypeName(String revenueTypeName) {
		this.revenueTypeName = revenueTypeName;
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

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getRevenueCreateTime() {
		return revenueCreateTime;
	}

	public void setRevenueCreateTime(String revenueCreateTime) {
		this.revenueCreateTime = revenueCreateTime;
	}
}
