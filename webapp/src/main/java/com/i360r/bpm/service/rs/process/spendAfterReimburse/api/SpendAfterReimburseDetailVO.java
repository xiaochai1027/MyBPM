package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.SpendAfterReimburseConstans;

public class SpendAfterReimburseDetailVO {
	
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private BigDecimal totalAmount;
	@ProcessVariable
	private String note;
	@ProcessVariable
	private List<SpendAfterReimburseUploadVoucherVO> uploadVoucherVOs;
	@ProcessVariable(key=SpendAfterReimburseConstans.TASK_EMPLOYEE_POSITION)
	private String applyRole;
	@ProcessVariable(key=SpendAfterReimburseConstans.TASK_EMPLOYEE_MOBILE)
	private String applyMobile;
	@ProcessVariable
	private String actualPaymentTime;
	@ProcessVariable
	private String createdByName;

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

	public List<SpendAfterReimburseUploadVoucherVO> getUploadVoucherVOs() {
		return uploadVoucherVOs;
	}

	public void setUploadVoucherVOs(
			List<SpendAfterReimburseUploadVoucherVO> uploadVoucherVOs) {
		this.uploadVoucherVOs = uploadVoucherVOs;
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

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	
}
