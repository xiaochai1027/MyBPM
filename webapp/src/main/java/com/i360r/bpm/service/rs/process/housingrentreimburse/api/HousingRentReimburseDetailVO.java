package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;

public class HousingRentReimburseDetailVO {

	@ProcessVariable
	private String contractId;
	
	@ProcessVariable
	private String address;
	
	@ProcessVariable
	private String typeName; // 报销项目一级分类，此处固定为房屋类
	
	@ProcessVariable
	private String typeCode; // 报销项目一级分类，此处固定为房屋类
	
	@ProcessVariable
	private String subTypeCode; // 房屋类的子报销项，此处固定为房租的code
	
	@ProcessVariable
	private String subTypeName; // 房屋类的子报销项，此处固定为房租
	
	@ProcessVariable
	private String cityCode;
	
	@ProcessVariable
	private String cityName;
	
	@ProcessVariable
	private String businessAreaCode;
	
	@ProcessVariable
	private String businessAreaName;
	
	@ProcessVariable
	private String letter;
	
	@ProcessVariable
	private String actualPayee;
	
	@ProcessVariable
	private String letterBankCardNumber;
	
	@ProcessVariable
	private String letterInterbankNumber; // 银行联行号，非中行卡号需要此字段
	
	@ProcessVariable
	private String letterBankInstitution;

	// TODO 兼容老版本，恢复以前4个字段
	@ProcessVariable
	private String operatorCode;

	@ProcessVariable
	private String operatorName;

	@ProcessVariable
	private String operatorBankCardNumber; // 银行卡号

	@ProcessVariable
	private String operatorInterbankNumber; // 银行联行号，非中行卡号需要此字段

	@ProcessVariable
	private BigDecimal amount;
	
	@ProcessVariable
	private String actualPaymentDate;
	
	@ProcessVariable
	private String beginDate;
	
	@ProcessVariable
	private String endDate;
	
	@ProcessVariable
	private Boolean lastPay;
	
	@ProcessVariable
	private String nextPayDate;

	@ProcessVariable
	private String note;

	@ProcessVariable
	private String attachmentUrl;
	
	@ProcessVariable
	private String origAttachmentUrl;
	
	@ProcessVariable
	private String createdByName; // 申请人名字
	@ProcessVariable
	private String employeePosition; // 申请人岗位
	@ProcessVariable
	private String employeeMobile; // 申请人手机号

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

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
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

	public String getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}

	public String getEmployeeMobile() {
		return employeeMobile;
	}

	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}

	public String getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(String actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
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

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
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
