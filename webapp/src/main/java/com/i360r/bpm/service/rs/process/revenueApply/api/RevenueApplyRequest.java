package com.i360r.bpm.service.rs.process.revenueApply.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class RevenueApplyRequest {
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
	@NotNull
	private BigDecimal currentAmount;
	@ProcessVariable
	private String note;
	@ProcessVariable
	@NotNull
	private String revenueTypeCode;
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private String revenueTypeName;
	@ProcessVariable(isKeyword=true, keywordOrder=4)
	@NotNull
	private String revenueCreateTime;
	@ProcessVariable(isUnique=true, uniqueScope=ProcessUniqueScope.UNFINISHED, showName="营业额申请")
	@NotNull
	private Integer revenueId;

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
