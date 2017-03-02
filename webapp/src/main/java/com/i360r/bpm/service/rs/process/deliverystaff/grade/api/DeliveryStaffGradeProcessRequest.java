package com.i360r.bpm.service.rs.process.deliverystaff.grade.api;

import com.i360r.bpm.business.model.DeliveryStaffGradeType;
import com.i360r.bpm.business.model.ProcessVariable;

public class DeliveryStaffGradeProcessRequest {
	@ProcessVariable
	private String deliveryStaffGradeTypeCode;
	@ProcessVariable
	private String originDeliveryStaffGradeTypeCode;
	@ProcessVariable
	private String deliveryStaffId;
	@ProcessVariable(isKeyword=true,keywordOrder=2)
	private String fullName;
	@ProcessVariable
	private String identityCard;
	@ProcessVariable(isKeyword=true,keywordOrder=3)
	private String mobile;
	@ProcessVariable
	private String cityName;
	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true,keywordOrder=1)
	private String businessAreaName;
	@ProcessVariable
	private String originDeliveryStaffGradeTypeName;
	@ProcessVariable
	private String deliveryStaffGradeTypeName;
	@ProcessVariable
	private String reason;
	@ProcessVariable
	private String changeGradeDate;

	public DeliveryStaffGradeProcessRequest() {

	}

	public DeliveryStaffGradeProcessRequest(CreateDeliveryStaffGradeRequest request) {
		if (request == null) {
			return;
		}

		this.deliveryStaffGradeTypeCode = request.getDeliveryStaffGradeTypeCode();
		this.originDeliveryStaffGradeTypeCode = request.getOriginDeliveryStaffGradeTypeCode();
		this.deliveryStaffGradeTypeName = DeliveryStaffGradeType.fromCode(request.getDeliveryStaffGradeTypeCode()).getName();
		this.originDeliveryStaffGradeTypeName = DeliveryStaffGradeType.fromCode(request.getOriginDeliveryStaffGradeTypeCode()).getName();
		this.deliveryStaffId = request.getDeliveryStaffId();
		this.reason = request.getReason();
		this.changeGradeDate = request.getChangeGradeDate();
	}

	public String getDeliveryStaffGradeTypeCode() {
		return deliveryStaffGradeTypeCode;
	}

	public void setDeliveryStaffGradeTypeCode(String deliveryStaffGradeTypeCode) {
		this.deliveryStaffGradeTypeCode = deliveryStaffGradeTypeCode;
	}

	public String getOriginDeliveryStaffGradeTypeCode() {
		return originDeliveryStaffGradeTypeCode;
	}

	public void setOriginDeliveryStaffGradeTypeCode(String originDeliveryStaffGradeTypeCode) {
		this.originDeliveryStaffGradeTypeCode = originDeliveryStaffGradeTypeCode;
	}

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

	public String getDeliveryStaffGradeTypeName() {
		return deliveryStaffGradeTypeName;
	}

	public void setDeliveryStaffGradeTypeName(String deliveryStaffGradeTypeName) {
		this.deliveryStaffGradeTypeName = deliveryStaffGradeTypeName;
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
