package com.i360r.bpm.service.rs.process.reservedCashRefund.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;

public class ReservedCashRefundDetailVO {

	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private String cityName;
	@ProcessVariable
	private String provinceName;
	@ProcessVariable
	private BigDecimal changeAmount;
	@ProcessVariable
	private BigDecimal reservedCashAmount;
	@ProcessVariable
	private String note;
	@ProcessVariable
	private String actualPaymentTime;

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

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
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
