package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

/**
 * 房屋租金申请request
 * 
 * @author liugang
 *
 */
public class HousingRentReimburseRequest {
	
	@ProcessVariable
	@NotNull
	private String contractId;
	
	@ProcessVariable
	@NotNull
	private String address;
	
	@ProcessVariable
	@NotNull
	private String typeName; // 报销项目一级分类，此处固定为房屋类
	
	@ProcessVariable
	@NotNull
	private String typeCode; // 报销项目一级分类，此处固定为房屋类
	
	@ProcessVariable
	@NotNull
	private String subTypeCode; // 房屋类的子报销项，此处固定为房租的code
	
	@ProcessVariable
	@NotNull
	private String subTypeName; // 房屋类的子报销项，此处固定为房租
	
	@ProcessVariable
	@NotNull
	private String cityCode;
	
	@ProcessVariable(isKeyword=true, keywordOrder=1)
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
	private String letterInterbankNumber; // 银行联行号，非中行卡号需要此字段
	
	@ProcessVariable
	private String letterBankInstitution;
	
	@ProcessVariable(isKeyword=true, keywordOrder=3)
	@NotNull
	private BigDecimal amount;
	
	@ProcessVariable
	@NotNull
	private String beginDate;
	
	@ProcessVariable
	@NotNull
	private String endDate;
	
	@ProcessVariable
	@NotNull
	private Boolean lastPay;
	
	@ProcessVariable
	private String nextPayDate;

	@ProcessVariable
	private String note;

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getSubTypeCode() {
		return subTypeCode;
	}

	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
