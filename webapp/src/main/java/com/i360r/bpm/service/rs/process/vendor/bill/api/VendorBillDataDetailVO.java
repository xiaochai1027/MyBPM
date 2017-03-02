package com.i360r.bpm.service.rs.process.vendor.bill.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;

public class VendorBillDataDetailVO {
	@ProcessVariable
	private String assignCode;
	@ProcessVariable
	private Integer billId;
	@ProcessVariable
	private String storeAccountId;
	@ProcessVariable
	private String companyName;
	@ProcessVariable
	private String invoiceTitle;
	@ProcessVariable
	private String invoiceContentName;
	@ProcessVariable
	private String invoiceTypeName;
	@ProcessVariable
	private String vendorBillCreateTime;
	@ProcessVariable
	private String beginDate;
	@ProcessVariable
	private String endDate;
	@ProcessVariable
	private BigDecimal amount;
	@ProcessVariable
	private BigDecimal realAmount;
	@ProcessVariable
	private String remark;
	@ProcessVariable
	private String receivedDate;
	@ProcessVariable
	private String vendorId;

	public String getAssignCode() {
		return assignCode;
	}

	public void setAssignCode(String assignCode) {
		this.assignCode = assignCode;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getStoreAccountId() {
		return storeAccountId;
	}

	public void setStoreAccountId(String storeAccountId) {
		this.storeAccountId = storeAccountId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceContentName() {
		return invoiceContentName;
	}

	public void setInvoiceContentName(String invoiceContentName) {
		this.invoiceContentName = invoiceContentName;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public String getVendorBillCreateTime() {
		return vendorBillCreateTime;
	}

	public void setVendorBillCreateTime(String vendorBillCreateTime) {
		this.vendorBillCreateTime = vendorBillCreateTime;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

}
