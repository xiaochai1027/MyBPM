package com.i360r.bpm.service.rs.process.housingcheckout.api;

import java.io.Serializable;
import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HousingDepositVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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

}
