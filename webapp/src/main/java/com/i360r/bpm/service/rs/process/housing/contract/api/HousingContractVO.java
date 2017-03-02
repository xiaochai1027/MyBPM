package com.i360r.bpm.service.rs.process.housing.contract.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HousingContractVO {
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
	private String operatorCode;
	@ProcessVariable
	private String operatorName;
	@ProcessVariable
	private String operatorBankCardNumber;
	@ProcessVariable
	private String operatorInterbankNumber;
	@ProcessVariable
	private BigDecimal agentCommissionFee;
	@ProcessVariable
	private BigDecimal monthlyRentFee;
	@ProcessVariable
	private BigDecimal firstTotalRentFee;
	@ProcessVariable
	private int periodInMonth;
	@ProcessVariable
	private List<HousingDepositVO> housingDeposits;
	@ProcessVariable
	private int rentDepositNumber;
	@ProcessVariable
	private int rentPaymentNumber;
	@ProcessVariable
	private String address;
	@ProcessVariable
	private BigDecimal areaSize;
	@ProcessVariable
	private int livableNumber;
	@ProcessVariable
	private String note;
	@ProcessVariable
	@NotNull
	private String latestPayStartDate;
	@ProcessVariable
	private String latestPayEndDate;
	@ProcessVariable
	private Boolean isLastPay;
	@ProcessVariable
	private String nextPayDate;
	@ProcessVariable
	@NotNull
	private String letter;
	@ProcessVariable
	private String actualPayee;
	@ProcessVariable
	private String letterBankCardNumber;
	@ProcessVariable
	private String letterInterbankNumber;
	@ProcessVariable
	private String letterBankInstitution;
	@ProcessVariable
	private String effectiveStartDate;
	@ProcessVariable
	@NotNull
	private String effectiveEndDate;
	@ProcessVariable
	private int livedNumber;
	@ProcessVariable
	private List<HousingContractAttachmentVO> housingContractAttachments;
	@ProcessVariable
	private BigDecimal totalAmount;
	@ProcessVariable
	private String rentPaymentType;
	@ProcessVariable
	private String actualPaymentDate;
	@ProcessVariable
	private String provinceCode;

	public String getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(String actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
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

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorBankCardNumber() {
		return operatorBankCardNumber;
	}

	public void setOperatorBankCardNumber(String operatorBankCardNumber) {
		this.operatorBankCardNumber = operatorBankCardNumber;
	}

	public String getOperatorInterbankNumber() {
		return operatorInterbankNumber;
	}

	public void setOperatorInterbankNumber(String operatorInterbankNumber) {
		this.operatorInterbankNumber = operatorInterbankNumber;
	}

	public BigDecimal getAgentCommissionFee() {
		return agentCommissionFee;
	}

	public void setAgentCommissionFee(BigDecimal agentCommissionFee) {
		this.agentCommissionFee = agentCommissionFee;
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

	public int getRentDepositNumber() {
		return rentDepositNumber;
	}

	public void setRentDepositNumber(int rentDepositNumber) {
		this.rentDepositNumber = rentDepositNumber;
	}

	public int getRentPaymentNumber() {
		return rentPaymentNumber;
	}

	public void setRentPaymentNumber(int rentPaymentNumber) {
		this.rentPaymentNumber = rentPaymentNumber;
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

	public int getLivableNumber() {
		return livableNumber;
	}

	public void setLivableNumber(int livableNumber) {
		this.livableNumber = livableNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<HousingContractAttachmentVO> getHousingContractAttachments() {
		return housingContractAttachments;
	}

	public void setHousingContractAttachments(
			List<HousingContractAttachmentVO> housingContractAttachments) {
		this.housingContractAttachments = housingContractAttachments;
	}

	public String getLatestPayStartDate() {
		return latestPayStartDate;
	}

	public void setLatestPayStartDate(String latestPayStartDate) {
		this.latestPayStartDate = latestPayStartDate;
	}

	public String getLatestPayEndDate() {
		return latestPayEndDate;
	}

	public void setLatestPayEndDate(String latestPayEndDate) {
		this.latestPayEndDate = latestPayEndDate;
	}

	public Boolean getIsLastPay() {
		return isLastPay;
	}

	public void setIsLastPay(Boolean isLastPay) {
		this.isLastPay = isLastPay;
	}

	public String getNextPayDate() {
		return nextPayDate;
	}

	public void setNextPayDate(String nextPayDate) {
		this.nextPayDate = nextPayDate;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getLetterInterbankNumber() {
		return letterInterbankNumber;
	}

	public void setLetterInterbankNumber(String letterInterbankNumber) {
		this.letterInterbankNumber = letterInterbankNumber;
	}

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public int getLivedNumber() {
		return livedNumber;
	}

	public void setLivedNumber(int livedNumber) {
		this.livedNumber = livedNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getFirstTotalRentFee() {
		return firstTotalRentFee;
	}

	public void setFirstTotalRentFee(BigDecimal firstTotalRentFee) {
		this.firstTotalRentFee = firstTotalRentFee;
	}

	public int getPeriodInMonth() {
		return periodInMonth;
	}

	public void setPeriodInMonth(int periodInMonth) {
		this.periodInMonth = periodInMonth;
	}

	public String getRentPaymentType() {
		return rentPaymentType;
	}

	public void setRentPaymentType(String rentPaymentType) {
		this.rentPaymentType = rentPaymentType;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getLetterBankInstitution() {
		return letterBankInstitution;
	}

	public void setLetterBankInstitution(String letterBankInstitution) {
		this.letterBankInstitution = letterBankInstitution;
	}

	public String getActualPayee() {
		return actualPayee;
	}

	public void setActualPayee(String actualPayee) {
		this.actualPayee = actualPayee;
	}

	public String getLetterBankCardNumber() {
		return letterBankCardNumber;
	}

	public void setLetterBankCardNumber(String letterBankCardNumber) {
		this.letterBankCardNumber = letterBankCardNumber;
	}
	
}
