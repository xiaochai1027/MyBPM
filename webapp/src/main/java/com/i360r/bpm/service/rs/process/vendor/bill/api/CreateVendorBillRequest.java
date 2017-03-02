package com.i360r.bpm.service.rs.process.vendor.bill.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class CreateVendorBillRequest extends ProcessUniqueVariableRequest {

	@ProcessVariable
	@NotNull
	private String assignCode;

	@ProcessVariable(isUnique = true, uniqueScope = ProcessUniqueScope.UNFINISHED, showName = "账单结算申请")
	@NotNull
	private Integer billId;

	@ProcessVariable
	@NotNull
	private String storeAccountId;

	@ProcessVariable(isKeyword = true, keywordOrder = 1)
	@NotNull
	private String companyName;

	@ProcessVariable(isKeyword = true, keywordOrder = 2)
	@NotNull
	private String invoiceTitle;

	@ProcessVariable(isKeyword = true, keywordOrder = 3)
	@NotNull
	private String invoiceContentName;

	@ProcessVariable(isKeyword = true, keywordOrder = 4)
	@NotNull
	private String invoiceTypeName;

	@ProcessVariable
	@NotNull
	private String vendorBillCreateTime;

	@ProcessVariable
	@NotNull
	private String beginDate;

	@ProcessVariable
	@NotNull
	private String endDate;

	@ProcessVariable
	@NotNull
	private BigDecimal amount;
	
	@ProcessVariable
	@NotNull
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

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

}
