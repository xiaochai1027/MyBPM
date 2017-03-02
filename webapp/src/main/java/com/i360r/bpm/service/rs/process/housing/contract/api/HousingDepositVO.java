package com.i360r.bpm.service.rs.process.housing.contract.api;

import java.io.Serializable;
import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HousingDepositVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ProcessVariable
	@NotNull
	private int id;
	@ProcessVariable
	@NotNull
	private BigDecimal amount;
	@ProcessVariable
	@NotNull
	private String note;
	@ProcessVariable
	@NotNull
	private String housingDepositTypeCode;
	@ProcessVariable
	@NotNull
	private String housingDepositTypeName;
	@ProcessVariable
	private String effectiveDate;
	@ProcessVariable
	private String payTime;
	@ProcessVariable
	private String paidByCode;
	@ProcessVariable
	private String paidByName;
	@ProcessVariable
	private String actualPaymentTime;
	@ProcessVariable
	private String attachmentUrl;
	@ProcessVariable
	private String origAttachmentUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getHousingDepositTypeCode() {
		return housingDepositTypeCode;
	}

	public void setHousingDepositTypeCode(String housingDepositTypeCode) {
		this.housingDepositTypeCode = housingDepositTypeCode;
	}

	public String getHousingDepositTypeName() {
		return housingDepositTypeName;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPaidByCode() {
		return paidByCode;
	}

	public void setPaidByCode(String paidByCode) {
		this.paidByCode = paidByCode;
	}

	public String getPaidByName() {
		return paidByName;
	}

	public void setPaidByName(String paidByName) {
		this.paidByName = paidByName;
	}

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}

	public void setHousingDepositTypeName(String housingDepositTypeName) {
		this.housingDepositTypeName = housingDepositTypeName;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getOrigAttachmentUrl() {
		return origAttachmentUrl;
	}

	public void setOrigAttachmentUrl(String origAttachmentUrl) {
		this.origAttachmentUrl = origAttachmentUrl;
	}
}
