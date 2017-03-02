package com.i360r.bpm.service.rs.process.reservedCashRefund.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class ReservedCashRefundRequest {
	@ProcessVariable(isUnique=true, uniqueScope=ProcessUniqueScope.UNFINISHED, showName="备用金降级申请")
	@NotNull
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	@NotNull
	private String businessAreaName;
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private BigDecimal changeAmount;
	@ProcessVariable
	private String cityName;
	@ProcessVariable
	private String provinceName;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}
