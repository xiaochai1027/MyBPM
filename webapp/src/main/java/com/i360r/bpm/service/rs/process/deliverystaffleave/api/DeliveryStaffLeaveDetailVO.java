package com.i360r.bpm.service.rs.process.deliverystaffleave.api;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.LeaveType;
import com.i360r.bpm.business.model.ProcessVariable;


public class DeliveryStaffLeaveDetailVO {

	@ProcessVariable
	private String fromDate;
	@ProcessVariable
	private Boolean fromAM;
	@ProcessVariable
	private String toDate;
	@ProcessVariable
	private Boolean toAM;
	@ProcessVariable
	private BigDecimal totalLeaveDays;
	@ProcessVariable
	private String leaveTypeCode;
	@ProcessVariable
	private String reason;
	@ProcessVariable
	private String businessAreaName;
	@ProcessVariable
	private String entryDate;
	@ProcessVariable(key=ProcessConstants.KEY_CREATED_BY_NAME)
	private String deliveryStaffName;
	private String leaveTypeName;

	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}
	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}
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
	public BigDecimal getTotalLeaveDays() {
		return totalLeaveDays;
	}
	public void setTotalLeaveDays(BigDecimal totalLeaveDays) {
		this.totalLeaveDays = totalLeaveDays;
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
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
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
