package com.i360r.bpm.service.rs.process.deliverystaffdimission.api;

import org.apache.commons.lang.StringUtils;

import com.i360r.bpm.business.model.DimissionReasonType;
import com.i360r.bpm.business.model.DimissionType;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedVO;


public class DeliveryStaffDimissionDetailVO extends BusinessAreaBasedVO {

	@ProcessVariable
	private String deliveryStaffCode;
	@ProcessVariable
	private String deliveryStaffName;
	@ProcessVariable
	private String dimissionTypeCode;
	@ProcessVariable
	private String dimissionReasonCode;
	@ProcessVariable
	private String dimissionDate;
	@ProcessVariable
	private String detail;
	@ProcessVariable
	private String pictureUrl1;
	@ProcessVariable
	private String origPictureUrl1;
	@ProcessVariable
	private String pictureUrl2;
	@ProcessVariable
	private String origPictureUrl2;
	@ProcessVariable
	private String entryDate;
	@ProcessVariable
	private String dimissionReasonName;
	@ProcessVariable
	private String dimissionTypeName;
	
	public String getOrigPictureUrl1() {
		return origPictureUrl1;
	}
	public void setOrigPictureUrl1(String origPictureUrl1) {
		this.origPictureUrl1 = origPictureUrl1;
	}
	public String getOrigPictureUrl2() {
		return origPictureUrl2;
	}
	public void setOrigPictureUrl2(String origPictureUrl2) {
		this.origPictureUrl2 = origPictureUrl2;
	}
	public String getDeliveryStaffCode() {
		return deliveryStaffCode;
	}
	public void setDeliveryStaffCode(String deliveryStaffCode) {
		this.deliveryStaffCode = deliveryStaffCode;
	}
	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}
	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}
	public String getDimissionTypeCode() {
		return dimissionTypeCode;
	}
	public void setDimissionTypeCode(String dimissionTypeCode) {
		this.dimissionTypeCode = dimissionTypeCode;
	}
	public String getDimissionReasonCode() {
		return dimissionReasonCode;
	}
	public void setDimissionReasonCode(String dimissionReasonCode) {
		this.dimissionReasonCode = dimissionReasonCode;
	}
	public String getDimissionDate() {
		return dimissionDate;
	}
	public void setDimissionDate(String dimissionDate) {
		this.dimissionDate = dimissionDate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPictureUrl1() {
		return pictureUrl1;
	}
	public void setPictureUrl1(String pictureUrl1) {
		this.pictureUrl1 = pictureUrl1;
	}
	public String getPictureUrl2() {
		return pictureUrl2;
	}
	public void setPictureUrl2(String pictureUrl2) {
		this.pictureUrl2 = pictureUrl2;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getDimissionReasonName() {
		return dimissionReasonName;
	}
	public void setDimissionReasonName(String dimissionReasonName) {
		this.dimissionReasonName = dimissionReasonName;
	}
	public String getDimissionTypeName() {
		return dimissionTypeName;
	}
	public void setDimissionTypeName(String dimissionTypeName) {
		this.dimissionTypeName = dimissionTypeName;
	}
	
	public void initOtherProperties() {
		if (StringUtils.isNotBlank(this.dimissionReasonCode)) {
			this.dimissionReasonName = DimissionReasonType.fromCode(this.dimissionReasonCode).getDescription();
		}
		if (StringUtils.isNotBlank(this.dimissionTypeCode)) {
			this.dimissionTypeName = DimissionType.fromCode(this.dimissionTypeCode).getDescription();
		}
	}
}
