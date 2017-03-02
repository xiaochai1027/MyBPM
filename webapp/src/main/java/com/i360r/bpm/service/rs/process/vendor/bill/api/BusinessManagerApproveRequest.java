package com.i360r.bpm.service.rs.process.vendor.bill.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class BusinessManagerApproveRequest extends ApproveRequest {
	private Integer billId;
	
	@ProcessVariable
	@NotNull
	private BigDecimal realAmount;
	
	@ProcessVariable
	@NotNull
	private String remark;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
