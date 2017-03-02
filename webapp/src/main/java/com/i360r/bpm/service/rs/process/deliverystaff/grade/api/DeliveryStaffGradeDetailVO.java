package com.i360r.bpm.service.rs.process.deliverystaff.grade.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedVO;

public class DeliveryStaffGradeDetailVO extends BusinessAreaBasedVO {

	@ProcessVariable
	private String deliveryStaffId;
	@ProcessVariable(key = "fullName")
	private String fullName;
	@ProcessVariable
	private String identityCard;
	@ProcessVariable(key = "mobile")
	private String mobile;
	@ProcessVariable
	private String cityName;
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable(key = "businessAreaName")
	private String businessAreaName;
	@ProcessVariable
	private String originDeliveryStaffGradeTypeName;
	@ProcessVariable
	private String originDeliveryStaffGradeTypeCode;
	@ProcessVariable
	private String deliveryStaffGradeTypeName;
	@ProcessVariable
	private String deliveryStaffGradeTypeCode;
	@ProcessVariable
	private String reason;
	@ProcessVariable
	private String changeGradeDate;

	public String getDeliveryStaffId() {
		return deliveryStaffId;
	}

	public void setDeliveryStaffId(String deliveryStaffId) {
		this.deliveryStaffId = deliveryStaffId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

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

	public String getOriginDeliveryStaffGradeTypeName() {
		return originDeliveryStaffGradeTypeName;
	}

	public void setOriginDeliveryStaffGradeTypeName(String originDeliveryStaffGradeTypeName) {
		this.originDeliveryStaffGradeTypeName = originDeliveryStaffGradeTypeName;
	}

	public String getOriginDeliveryStaffGradeTypeCode() {
		return originDeliveryStaffGradeTypeCode;
	}

	public void setOriginDeliveryStaffGradeTypeCode(String originDeliveryStaffGradeTypeCode) {
		this.originDeliveryStaffGradeTypeCode = originDeliveryStaffGradeTypeCode;
	}

	public String getDeliveryStaffGradeTypeName() {
		return deliveryStaffGradeTypeName;
	}

	public void setDeliveryStaffGradeTypeName(String deliveryStaffGradeTypeName) {
		this.deliveryStaffGradeTypeName = deliveryStaffGradeTypeName;
	}

	public String getDeliveryStaffGradeTypeCode() {
		return deliveryStaffGradeTypeCode;
	}

	public void setDeliveryStaffGradeTypeCode(String deliveryStaffGradeTypeCode) {
		this.deliveryStaffGradeTypeCode = deliveryStaffGradeTypeCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getChangeGradeDate() {
		return changeGradeDate;
	}

	public void setChangeGradeDate(String changeGradeDate) {
		this.changeGradeDate = changeGradeDate;
	}

}
