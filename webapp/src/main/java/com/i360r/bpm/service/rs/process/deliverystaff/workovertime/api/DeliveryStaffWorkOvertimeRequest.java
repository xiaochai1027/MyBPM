package com.i360r.bpm.service.rs.process.deliverystaff.workovertime.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class DeliveryStaffWorkOvertimeRequest extends BusinessAreaBasedRequest {

	@NotNull
	@ProcessVariable(isKeyword = true, isUnique = true, showName = "调休转加班费申请", uniqueScope = ProcessUniqueScope.CREATOR_UNFINISHED)
	private String fullName;
	@NotNull
	@ProcessVariable
	private String employeePositionCode;
	@NotNull
	@ProcessVariable
	private String deliveryStaffCode;
	@NotNull
	@ProcessVariable
	private BigDecimal overtimeDays;
	@NotNull
	@ProcessVariable
	private String reason;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public BigDecimal getOvertimeDays() {
		return overtimeDays;
	}

	public void setOvertimeDays(BigDecimal overtimeDays) {
		this.overtimeDays = overtimeDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEmployeePositionCode() {
		return employeePositionCode;
	}

	public void setEmployeePositionCode(String employeePositionCode) {
		this.employeePositionCode = employeePositionCode;
	}

	public String getDeliveryStaffCode() {
		return deliveryStaffCode;
	}

	public void setDeliveryStaffCode(String deliveryStaffCode) {
		this.deliveryStaffCode = deliveryStaffCode;
	}

}
