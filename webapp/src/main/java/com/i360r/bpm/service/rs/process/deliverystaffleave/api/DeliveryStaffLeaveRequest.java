package com.i360r.bpm.service.rs.process.deliverystaffleave.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.AbstractCreatorUniqueTimeRangeRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class DeliveryStaffLeaveRequest extends AbstractCreatorUniqueTimeRangeRequest {
	
	@ProcessVariable
	@NotNull
	private String fromDate;
	@ProcessVariable
	@NotNull
	private Boolean fromAM;
	@ProcessVariable
	@NotNull
	private String toDate;
	@ProcessVariable
	@NotNull
	private Boolean toAM;
	@ProcessVariable(isUnique=true, required=true, showName="请假类型", uniqueScope=ProcessUniqueScope.CREATOR_UNFINISHED)
	private String leaveTypeCode;
	@ProcessVariable
	private String reason;

	@ProcessVariable(isKeyword=true)
	private String businessAreaName;
	@ProcessVariable(isKeyword=true)
	private String fullName;
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public Boolean getFromAM() {
		return fromAM;
	}
	public void setFromAM(Boolean fromAM) {
		this.fromAM = fromAM;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Boolean getToAM() {
		return toAM;
	}
	public void setToAM(Boolean toAM) {
		this.toAM = toAM;
	}
	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}
	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBusinessAreaName() {
		return businessAreaName;
	}
	public void setBusinessAreaName(String businessAreaName) {
		this.businessAreaName = businessAreaName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
