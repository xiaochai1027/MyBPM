package com.i360r.bpm.service.rs.process.deliverystaffleave.api;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.service.rs.process.deliverystaffleave.DeliveryStaffLeaveConstants;

public class DeliveryStaffLeaveVO {

	private String processInstanceId;
	private String createdByName;
	private String leaveTypeCode;
	private BigDecimal totalLeaveDays;
	private String createTime;
	private String statusName;
	private String statusCode;
	private String fromDate;
	private Boolean fromAM;
	private String toDate;
	private Boolean toAM;
	
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
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}
	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}
	public BigDecimal getTotalLeaveDays() {
		return totalLeaveDays;
	}
	public void setTotalLeaveDays(BigDecimal totalLeaveDays) {
		this.totalLeaveDays = totalLeaveDays;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public static DeliveryStaffLeaveVO toVO(HistoricProcessInstance hpi, Map<String, Object> variables) {
		DeliveryStaffLeaveVO vo = new DeliveryStaffLeaveVO();
		
		vo.setProcessInstanceId(hpi.getId());
		vo.setCreatedByName((String)variables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		vo.setCreateTime(DateTimeUtility.formatHHMM(hpi.getStartTime()));
		
		vo.setLeaveTypeCode((String)variables.get(DeliveryStaffLeaveConstants.KEY_LEAVE_TYPE_CODE));
		vo.setTotalLeaveDays((BigDecimal)variables.get(DeliveryStaffLeaveConstants.KEY_TOTAL_LEAVE_DAYS));
		
		Boolean pass = (Boolean)variables.get(ProcessConstants.KEY_PASS);
		if (pass == null) {
			vo.setStatusName(ProcessStatus.APPROVING.getName());
			vo.setStatusCode(ProcessStatus.APPROVING.getCode());
		} else if (pass) {
			vo.setStatusName(ProcessStatus.PASS.getName());
			vo.setStatusCode(ProcessStatus.PASS.getCode());
		} else {
			vo.setStatusName(ProcessStatus.NOT_PASS.getName());
			vo.setStatusCode(ProcessStatus.NOT_PASS.getCode());
		}
		
		String fromDateHour = (String) variables.get(DeliveryStaffLeaveConstants.KEY_FROM_DATE_HOUR);
		String toDateHour = (String) variables.get(DeliveryStaffLeaveConstants.KEY_TO_DATE_HOUR);
		try {
			Date from = DateTimeUtility.parseYYYYMMDDHHMMSS(fromDateHour);
			Date to = DateTimeUtility.parseYYYYMMDDHHMMSS(toDateHour);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(from);
			int hourFrom = calendar.get(Calendar.HOUR_OF_DAY);
			vo.setFromAM(hourFrom == 0);
			
			calendar.setTime(to);
			int hourTo = calendar.get(Calendar.HOUR_OF_DAY);
			vo.setFromDate(DateTimeUtility.formatYYYYMMDD(from));
			
			if (hourTo != 0) {
				vo.setToAM(true);
				vo.setToDate(DateTimeUtility.formatYYYYMMDD(to));
			} else {
				vo.setToAM(false);
				vo.setToDate(DateTimeUtility.formatYYYYMMDD(DateTimeUtility.addDays(to, -1)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
}
