package com.i360r.bpm.service.rs.process.deliverystaff.grade.api;

public class CreateDeliveryStaffGradeRequest {
	private String deliveryStaffGradeTypeCode;
	private String originDeliveryStaffGradeTypeCode;
	private String deliveryStaffId;
	private String reason;
	private String changeGradeDate;

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
