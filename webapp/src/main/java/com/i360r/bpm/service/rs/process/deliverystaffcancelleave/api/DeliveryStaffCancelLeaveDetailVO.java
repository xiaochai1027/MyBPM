package com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.LeaveType;
import com.i360r.bpm.business.model.ProcessVariable;

public class DeliveryStaffCancelLeaveDetailVO {

	@ProcessVariable
	private String leaveFromDate;
	@ProcessVariable
	private Boolean leaveFromAM;
	@ProcessVariable
	private String leaveToDate;
	@ProcessVariable
	private Boolean leaveToAM;
	@ProcessVariable
	private String leaveTypeCode;
	@ProcessVariable
	private List<HalfDate> cancelLeaves;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable(key = ProcessConstants.KEY_CREATED_BY_NAME)
	private String employeeName;
	
	private String leaveTypeName;

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

	public List<HalfDate> getCancelLeaves() {
		return cancelLeaves;
	}

	public void setCancelLeaves(List<HalfDate> cancelLeaves) {
		this.cancelLeaves = cancelLeaves;
	}

	public String getBusinessAreaName() {
		return businessAreaName;
	}

	public void setBusinessAreaName(String businessAreaName) {
		this.businessAreaName = businessAreaName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public void initOtherProperties() {
		if (StringUtils.isNotBlank(this.leaveTypeCode)) {
			this.leaveTypeName = LeaveType.fromCode(this.leaveTypeCode).getDescription();
		}
	}

}
