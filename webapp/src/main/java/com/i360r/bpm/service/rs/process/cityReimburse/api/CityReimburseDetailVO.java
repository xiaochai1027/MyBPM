package com.i360r.bpm.service.rs.process.cityReimburse.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.SpendBeforeReimburseConstants;

public class CityReimburseDetailVO {
	@ProcessVariable
	private String cityCode;
	@ProcessVariable
	private String cityName;
	@ProcessVariable
	private List<CityReimburseItemVO> reimburseItems;
	@ProcessVariable
	private BigDecimal totalAmount;
	@ProcessVariable
	private String note;
	@ProcessVariable(key=SpendBeforeReimburseConstants.TASK_EMPLOYEE_POSITION)
	private String applyRole;
	@ProcessVariable(key=SpendBeforeReimburseConstants.TASK_EMPLOYEE_MOBILE)
	private String applyMobile;
	@ProcessVariable
	private String actualPaymentTime;
	@ProcessVariable
	private String createdByName;
	@ProcessVariable
	private String bankAccountName;
	@ProcessVariable
	private String bankAccountNumber;
	
	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
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

	public List<CityReimburseItemVO> getReimburseItems() {
		return reimburseItems;
	}

	public void setReimburseItems(List<CityReimburseItemVO> reimburseItems) {
		this.reimburseItems = reimburseItems;
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

	public String getApplyRole() {
		return applyRole;
	}

	public void setApplyRole(String applyRole) {
		this.applyRole = applyRole;
	}

	public String getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
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

	public List<CityReimburseItemVO> getApprovedItems() {
		List<CityReimburseItemVO> list = new ArrayList<CityReimburseItemVO>();
		for (CityReimburseItemVO vo : this.getReimburseItems()) {
			if (!vo.isRejected()) {
				list.add(vo);
			}
		}
		return list;
	}
	
	public List<CityReimburseItemVO> getRejectedItems() {
		List<CityReimburseItemVO> list = new ArrayList<CityReimburseItemVO>();
		for (CityReimburseItemVO vo : this.getReimburseItems()) {
			if (vo.isRejected()) {
				list.add(vo);
			}
		}
		return list;
	}
}
