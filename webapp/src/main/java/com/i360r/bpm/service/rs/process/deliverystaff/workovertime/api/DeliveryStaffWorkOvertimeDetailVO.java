package com.i360r.bpm.service.rs.process.deliverystaff.workovertime.api;

import java.math.BigDecimal;
import java.util.Set;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedVO;

public class DeliveryStaffWorkOvertimeDetailVO extends BusinessAreaBasedVO {

	@ProcessVariable
	private String createdByName;
	@ProcessVariable
	private Set<String> creatorRoleNames;
	@ProcessVariable
	private String creatorMobile;
	@ProcessVariable(key = "fullName")
	private String fullName;
	@ProcessVariable
	private BigDecimal overtimeDays;
	@ProcessVariable
	private String reason;

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Set<String> getCreatorRoleNames() {
		return creatorRoleNames;
	}

	public void setCreatorRoleNames(Set<String> creatorRoleNames) {
		this.creatorRoleNames = creatorRoleNames;
	}

	public String getCreatorMobile() {
		return creatorMobile;
	}

	public void setCreatorMobile(String creatorMobile) {
		this.creatorMobile = creatorMobile;
	}

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

}
