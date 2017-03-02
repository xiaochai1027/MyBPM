package com.i360r.bpm.service.rs.process.spendBeforeReimburse.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class SpendBeforeReimburseCreateRequest {
	
	@ProcessVariable
	@NotNull
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true, keywordOrder=3)
	@NotNull
	private String businessAreaName;
	@ProcessVariable
	@NotNull
	private List<SpendBeforeReimburseItemVO> reimburseItems;
	
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private BigDecimal totalAmount;
	@ProcessVariable
	private String note;

	@ProcessVariable
	private String bankAccountName;
	
	@ProcessVariable
	private String bankAccountNumber;
	
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

	public List<SpendBeforeReimburseItemVO> getReimburseItems() {
		return reimburseItems;
	}

	public void setReimburseItems(List<SpendBeforeReimburseItemVO> reimburseItems) {
		this.reimburseItems = reimburseItems;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
}
