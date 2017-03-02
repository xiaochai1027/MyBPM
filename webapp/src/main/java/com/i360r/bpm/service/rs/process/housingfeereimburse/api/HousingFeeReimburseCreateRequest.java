package com.i360r.bpm.service.rs.process.housingfeereimburse.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HousingFeeReimburseCreateRequest {
	
	@ProcessVariable
	@NotNull
	private String cityCode;
	
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private String cityName;
	
	@ProcessVariable
	private String businessAreaCode;
	
	@ProcessVariable(isKeyword=true, keywordOrder=3)
	private String businessAreaName;
	
	@ProcessVariable
	private String bankAccountName;
	
	@ProcessVariable
	private String bankAccountNumber;
	
	@ProcessVariable
	@NotNull
	private String note;
	
	@ProcessVariable(isKeyword=true, keywordOrder=4)
	@NotNull
	private BigDecimal totalAmount;
	
	@ProcessVariable
	@NotNull
	private List<HousingFeeReimburseItemVO> items;

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<HousingFeeReimburseItemVO> getItems() {
		return items;
	}

	public void setItems(List<HousingFeeReimburseItemVO> items) {
		this.items = items;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
}
