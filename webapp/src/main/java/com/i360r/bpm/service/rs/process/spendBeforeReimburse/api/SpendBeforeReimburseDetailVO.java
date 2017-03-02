package com.i360r.bpm.service.rs.process.spendBeforeReimburse.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.SpendBeforeReimburseConstants;

public class SpendBeforeReimburseDetailVO {
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private List<SpendBeforeReimburseItemVO> reimburseItems;
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

	public String getBusinessAreaCode() {
		return businessAreaCode;
	}

	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}

	public List<SpendBeforeReimburseItemVO> getReimburseItems() {
		return reimburseItems;
	}

	public void setReimburseItems(
			List<SpendBeforeReimburseItemVO> reimburseItems) {
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

	public String getBusinessAreaName() {
		return businessAreaName;
	}

	public void setBusinessAreaName(String businessAreaName) {
		this.businessAreaName = businessAreaName;
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

	public List<SpendBeforeReimburseItemVO> getApprovedItems() {
		List<SpendBeforeReimburseItemVO> list = new ArrayList<SpendBeforeReimburseItemVO>();
		for (SpendBeforeReimburseItemVO vo : this.getReimburseItems()) {
			if (!vo.isRejected()) {
				list.add(vo);
			}
		}
		return list;
	}

	public List<SpendBeforeReimburseItemVO> getRejectedItems() {
		List<SpendBeforeReimburseItemVO> list = new ArrayList<SpendBeforeReimburseItemVO>();
		for (SpendBeforeReimburseItemVO vo : this.getReimburseItems()) {
			if (vo.isRejected()) {
				list.add(vo);
			}
		}
		return list;
	}
}
