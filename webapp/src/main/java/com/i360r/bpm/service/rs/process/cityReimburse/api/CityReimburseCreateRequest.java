package com.i360r.bpm.service.rs.process.cityReimburse.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class CityReimburseCreateRequest {
	
	@ProcessVariable
	@NotNull
	private String cityCode;
	@ProcessVariable(isKeyword=true, keywordOrder=3)
	@NotNull
	private String cityName;
	@ProcessVariable
	@NotNull
	private List<CityReimburseItemVO> reimburseItems;
	
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private BigDecimal totalAmount;
	@ProcessVariable
	private String note;

	@ProcessVariable
	private String bankAccountName;
	
	@ProcessVariable
	private String bankAccountNumber;


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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<CityReimburseItemVO> getReimburseItems() {
		return reimburseItems;
	}

	public void setReimburseItems(List<CityReimburseItemVO> reimburseItems) {
		this.reimburseItems = reimburseItems;
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
