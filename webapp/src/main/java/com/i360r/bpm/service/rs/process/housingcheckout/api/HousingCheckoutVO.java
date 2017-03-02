package com.i360r.bpm.service.rs.process.housingcheckout.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;

public class HousingCheckoutVO {
	@ProcessVariable
	private Integer contractId;
	@ProcessVariable
	private String housingUsageTypeCode;
	@ProcessVariable
	private String housingUsageTypeName;
	@ProcessVariable
	private String cityCode;
	@ProcessVariable
	private String cityName;
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private BigDecimal monthlyRentFee;
	@ProcessVariable
	private List<HousingCheckoutVO> housingDeposits;
	@ProcessVariable
	private String address;
	@ProcessVariable
	private BigDecimal areaSize;
	@ProcessVariable
	private String checkoutNote;
	@ProcessVariable
	private BigDecimal totalBackFee;
	@ProcessVariable
	private BigDecimal rentBackFee;
	@ProcessVariable
	private String checkoutDate;
	@ProcessVariable
	private String effectiveDateRange;
	@ProcessVariable
	private String actualTurnoverDate;
	
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

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

	public BigDecimal getMonthlyRentFee() {
		return monthlyRentFee;
	}

	public void setMonthlyRentFee(BigDecimal monthlyRentFee) {
		this.monthlyRentFee = monthlyRentFee;
	}

	public List<HousingCheckoutVO> getHousingDeposits() {
		return housingDeposits;
	}

	public void setHousingDeposits(List<HousingCheckoutVO> housingDeposits) {
		this.housingDeposits = housingDeposits;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(BigDecimal areaSize) {
		this.areaSize = areaSize;
	}

	public String getCheckoutNote() {
		return checkoutNote;
	}

	public void setCheckoutNote(String checkoutNote) {
		this.checkoutNote = checkoutNote;
	}

	public String getHousingUsageTypeCode() {
		return housingUsageTypeCode;
	}

	public void setHousingUsageTypeCode(String housingUsageTypeCode) {
		this.housingUsageTypeCode = housingUsageTypeCode;
	}

	public String getHousingUsageTypeName() {
		return housingUsageTypeName;
	}

	public void setHousingUsageTypeName(String housingUsageTypeName) {
		this.housingUsageTypeName = housingUsageTypeName;
	}

	public BigDecimal getTotalBackFee() {
		return totalBackFee;
	}

	public void setTotalBackFee(BigDecimal totalBackFee) {
		this.totalBackFee = totalBackFee;
	}

	public BigDecimal getRentBackFee() {
		return rentBackFee;
	}

	public void setRentBackFee(BigDecimal rentBackFee) {
		this.rentBackFee = rentBackFee;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getEffectiveDateRange() {
		return effectiveDateRange;
	}

	public void setEffectiveDateRange(String effectiveDateRange) {
		this.effectiveDateRange = effectiveDateRange;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getActualTurnoverDate() {
		return actualTurnoverDate;
	}

	public void setActualTurnoverDate(String actualTurnoverDate) {
		this.actualTurnoverDate = actualTurnoverDate;
	}

}
