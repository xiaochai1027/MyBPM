package com.i360r.bpm.service.rs.process.housing.relet.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HouseReletApplyRequest {

	@ProcessVariable(isUnique = true, showName="该房屋的续租申请", uniqueScope = ProcessUniqueScope.UNFINISHED)
	@NotNull
	private String oldContractId;
	@ProcessVariable
	@NotNull
	private String housingUsageTypeCode;
	@ProcessVariable
	@NotNull
	private String housingUsageTypeName;
	@ProcessVariable
	@NotNull
	private String housingContractStatusCode;
	@ProcessVariable
	@NotNull
	private String housingContractStatusName;
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
	private String letter;
	@ProcessVariable
	@NotNull
	private String actualPayee;
	@ProcessVariable
	@NotNull
	private String letterBankCardNumber;
	@ProcessVariable
	private String letterInterbankNumber;
	@ProcessVariable
	private String letterBankInstitution;
	@ProcessVariable
	@NotNull
	private String operatorCode;
	@ProcessVariable
	@NotNull
	private String operatorName;
	@ProcessVariable
	@NotNull
	private String operatorBankCardNumber;
	@ProcessVariable
	private String operatorInterbankNumber;
	@ProcessVariable
	@NotNull
	private BigDecimal agentCommissionFee;
	@ProcessVariable
	@NotNull
	private BigDecimal monthlyRentFee;
	@ProcessVariable
	@NotNull
	private int periodInMonth;
	@ProcessVariable
	@NotNull
	private BigDecimal firstTotalRentFee;
	@ProcessVariable
	@NotNull
	private int rentDepositNumber;
	@ProcessVariable
	@NotNull
	private int rentPaymentNumber;
	@ProcessVariable
	@NotNull
	private Boolean lastPay;
	@ProcessVariable
	private String nextPayDate;
	@ProcessVariable
	@NotNull
	private String address;
	@ProcessVariable
	@NotNull
	private BigDecimal areaSize;
	@ProcessVariable
	private int livableNumber;
	@ProcessVariable
	private int livedNumber;
	@ProcessVariable
	@NotNull
	private String note;
	@ProcessVariable
	@NotNull
	private String effectiveStartDate;
	@ProcessVariable
	@NotNull
	private String effectiveEndDate;
	@ProcessVariable
	private String actualPaymentDate;
	@ProcessVariable(isKeyword = true, keywordOrder = 3)
	private BigDecimal totalAmount;
	@ProcessVariable
	private String provinceCode;
	@ProcessVariable
	private String latestPayStartDate;
	@ProcessVariable
	private String latestPayEndDate;

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

	public int getPeriodInMonth() {
		return periodInMonth;
	}

	public void setPeriodInMonth(int periodInMonth) {
		this.periodInMonth = periodInMonth;
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

	public String getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(String actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
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

	public String getOldContractId() {
		return oldContractId;
	}

	public void setOldContractId(String oldContractId) {
		this.oldContractId = oldContractId;
	}

	public String getHousingContractStatusCode() {
		return housingContractStatusCode;
	}

	public void setHousingContractStatusCode(String housingContractStatusCode) {
		this.housingContractStatusCode = housingContractStatusCode;
	}

	public String getHousingContractStatusName() {
		return housingContractStatusName;
	}

	public void setHousingContractStatusName(String housingContractStatusName) {
		this.housingContractStatusName = housingContractStatusName;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
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

	public String getLetterInterbankNumber() {
		return letterInterbankNumber;
	}

	public void setLetterInterbankNumber(String letterInterbankNumber) {
		this.letterInterbankNumber = letterInterbankNumber;
	}

	public String getLetterBankInstitution() {
		return letterBankInstitution;
	}

	public void setLetterBankInstitution(String letterBankInstitution) {
		this.letterBankInstitution = letterBankInstitution;
	}

	public Boolean getLastPay() {
		return lastPay;
	}

	public void setLastPay(Boolean lastPay) {
		this.lastPay = lastPay;
	}

	public String getNextPayDate() {
		return nextPayDate;
	}

	public void setNextPayDate(String nextPayDate) {
		this.nextPayDate = nextPayDate;
	}

	public int getLivedNumber() {
		return livedNumber;
	}

	public void setLivedNumber(int livedNumber) {
		this.livedNumber = livedNumber;
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
	
}