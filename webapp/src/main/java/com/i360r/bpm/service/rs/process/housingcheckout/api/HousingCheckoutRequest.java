package com.i360r.bpm.service.rs.process.housingcheckout.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HousingCheckoutRequest {

	@ProcessVariable(isUnique=true, showName="退房合同", uniqueScope = ProcessUniqueScope.UNFINISHED_PASS)
	@NotNull
	private Integer contractId;
	@ProcessVariable
	@NotNull
	private String housingUsageTypeCode;
	@ProcessVariable
	@NotNull
	private String housingUsageTypeName;
	@ProcessVariable
	@NotNull
	private String cityCode;
	@ProcessVariable(isKeyword = true, keywordOrder = 1)
	@NotNull
	private String cityName;
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable(isKeyword = true, keywordOrder = 2)
	private String businessAreaName;
	@ProcessVariable
	@NotNull
	private BigDecimal monthlyRentFee;//月房租
	@ProcessVariable
	private List<HousingDepositVO> housingDeposits;//房子押金
	@ProcessVariable
	@NotNull
	private String address;
	@ProcessVariable
	@NotNull
	private BigDecimal areaSize;
	@ProcessVariable
	@NotNull
	private String checkoutNote;
	@ProcessVariable(isKeyword = true, keywordOrder = 3)
	@NotNull
	private BigDecimal totalBackFee;
	@ProcessVariable
	@NotNull
	private BigDecimal rentBackFee;
	@ProcessVariable
	@NotNull
	private String checkoutDate;
	@ProcessVariable
	@NotNull
	private String effectiveDateRange;
	
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

	public List<HousingDepositVO> getHousingDeposits() {
		return housingDeposits;
	}

	public void setHousingDeposits(List<HousingDepositVO> housingDeposits) {
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

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getEffectiveDateRange() {
		return effectiveDateRange;
	}

	public void setEffectiveDateRange(String effectiveDateRange) {
		this.effectiveDateRange = effectiveDateRange;
	}

}
