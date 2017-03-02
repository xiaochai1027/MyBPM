package com.i360r.bpm.service.rs.process.housingfeereimburse.api;

import java.io.Serializable;
import java.math.BigDecimal;

public class HousingFeeReimburseItemVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String contractId;
	private String address;
	private String housingUsageTypeCode;
	private String housingUsageTypeName;
	
	private String itemId; 
	private String subTypeCode;
	private String subTypeName;
	private String beginDate;
	private String endDate;
	private String note;

	private BigDecimal amount;

	private String attachmentUrl; // 小图url
	private String origAttachmentUrl; // 大图url
	private String payTypeCode;
	private String payTypeName;
	private BigDecimal scale;//缴纳度数
	private BigDecimal unitPrice;//单价
	
	private String rejectReason;
	private String rejectPersonName;
	private boolean rejected;
	
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPayTypeCode() {
		return payTypeCode;
	}

	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public BigDecimal getScale() {
		return scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getRejectPersonName() {
		return rejectPersonName;
	}

	public void setRejectPersonName(String rejectPersonName) {
		this.rejectPersonName = rejectPersonName;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
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

	public String getHousingUsageTypeCode() {
		return housingUsageTypeCode;
	}

	public void setHousingUsageTypeCode(String housingUsageTypeCode) {
		this.housingUsageTypeCode = housingUsageTypeCode;
	}

	public String getHousingUsageTypeName() {
		return housingUsageTypeName;
	}

	public void setHousingUsageTypeName(String housingUsageTypeName) {
		this.housingUsageTypeName = housingUsageTypeName;
	}
	
}
