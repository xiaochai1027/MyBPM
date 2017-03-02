package com.i360r.bpm.service.rs.process.deliverystaffdimission.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;



public class DeliveryStaffDimissionRequest {
	
	@ProcessVariable(isUnique=true, uniqueScope=ProcessUniqueScope.UNFINISHED, showName="离职流程")
	@NotNull
	private String deliveryStaffCode;
	@ProcessVariable(isKeyword=true,keywordOrder=2)
	@NotNull
	private String deliveryStaffName;
	@ProcessVariable(isKeyword=true,keywordOrder=1)
	@NotNull
	private String cityName;
	@ProcessVariable
	@NotNull
	private String dimissionTypeCode;
	@ProcessVariable
	@NotNull
	private String dimissionReasonCode;
	@ProcessVariable
	@NotNull
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
	private String dimissionReasonName;
	@ProcessVariable
	private String dimissionTypeName;
	
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
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
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
}
