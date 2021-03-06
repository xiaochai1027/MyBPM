package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.io.Serializable;
import java.math.BigDecimal;

public class SpendAfterReimburseUploadVoucherVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String reimburseSubTypeCode;
	private String reimburseSubTypeName;
	private String reimburseTypeCode;
	private String reimburseTypeName;
	private BigDecimal amount;
	private String beginTime;
	private String endTime;
	private String note;
	private String attachmentUrl;
	private String origAttachmentUrl;
	private String excelAttachmentUrl;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getReimburseSubTypeCode() {
		return reimburseSubTypeCode;
	}

	public void setReimburseSubTypeCode(String reimburseSubTypeCode) {
		this.reimburseSubTypeCode = reimburseSubTypeCode;
	}

	public String getReimburseSubTypeName() {
		return reimburseSubTypeName;
	}

	public void setReimburseSubTypeName(String reimburseSubTypeName) {
		this.reimburseSubTypeName = reimburseSubTypeName;
	}

	public String getReimburseTypeCode() {
		return reimburseTypeCode;
	}

	public void setReimburseTypeCode(String reimburseTypeCode) {
		this.reimburseTypeCode = reimburseTypeCode;
	}

	public String getReimburseTypeName() {
		return reimburseTypeName;
	}

	public void setReimburseTypeName(String reimburseTypeName) {
		this.reimburseTypeName = reimburseTypeName;
	}

	public String getOrigAttachmentUrl() {
		return origAttachmentUrl;
	}

	public void setOrigAttachmentUrl(String origAttachmentUrl) {
		this.origAttachmentUrl = origAttachmentUrl;
	}

	public String getExcelAttachmentUrl() {
		return excelAttachmentUrl;
	}

	public void setExcelAttachmentUrl(String excelAttachmentUrl) {
		this.excelAttachmentUrl = excelAttachmentUrl;
	}
	
}
