package com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class DeliveryStaffCancelLeaveRequest extends ProcessUniqueVariableRequest {

	@ProcessVariable
	@NotNull
	private String leaveFromDate;
	@ProcessVariable
	@NotNull
	private Boolean leaveFromAM;
	@ProcessVariable
	@NotNull
	private String leaveToDate;
	@ProcessVariable
	@NotNull
	private Boolean leaveToAM;
	@ProcessVariable
	@NotNull
	private String leaveTypeCode;
	
	@ProcessVariable(isUnique=true, required=true, showName="销假流程", uniqueScope=ProcessUniqueScope.CREATOR_UNFINISHED)
	private String deliveryStaffName;
	@ProcessVariable
	@NotNull
	private Integer deliveryStaffLeaveId;
	@ProcessVariable
	@NotNull
	private List<HalfDate> cancelLeaves;

	@ProcessVariable(isKeyword = true)
	private String businessAreaName;

	public String getLeaveFromDate() {
		return leaveFromDate;
	}

	public void setLeaveFromDate(String leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	public Boolean getLeaveFromAM() {
		return leaveFromAM;
	}

	public void setLeaveFromAM(Boolean leaveFromAM) {
		this.leaveFromAM = leaveFromAM;
	}

	public String getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(String leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	public Boolean getLeaveToAM() {
		return leaveToAM;
	}

	public void setLeaveToAM(Boolean leaveToAM) {
		this.leaveToAM = leaveToAM;
	}

	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}

	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}

	public String getBusinessAreaName() {
		return businessAreaName;
	}

	public void setBusinessAreaName(String businessAreaName) {
		this.businessAreaName = businessAreaName;
	}

	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}

	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}

	public Integer getDeliveryStaffLeaveId() {
		return deliveryStaffLeaveId;
	}

	public void setDeliveryStaffLeaveId(Integer deliveryStaffLeaveId) {
		this.deliveryStaffLeaveId = deliveryStaffLeaveId;
	}

	public List<HalfDate> getCancelLeaves() {
		return cancelLeaves;
	}

	public void setCancelLeaves(List<HalfDate> cancelLeaves) {
		this.cancelLeaves = cancelLeaves;
	}

}
