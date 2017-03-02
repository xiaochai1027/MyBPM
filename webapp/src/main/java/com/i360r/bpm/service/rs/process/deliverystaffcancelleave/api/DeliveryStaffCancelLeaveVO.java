package com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api;

import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.bpm.service.rs.process.deliverystaffleave.DeliveryStaffLeaveConstants;
import com.i360r.framework.util.DateTimeUtility;

public class DeliveryStaffCancelLeaveVO {

	private String processInstanceId;
	private String createdByName;
	private String leaveTypeCode;
	private String createTime;
	private String statusName;
	
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
	
	public static DeliveryStaffCancelLeaveVO toVO(HistoricProcessInstance hpi, Map<String, Object> variables) {
		DeliveryStaffCancelLeaveVO vo = new DeliveryStaffCancelLeaveVO();
		
		vo.setProcessInstanceId(hpi.getId());
		vo.setCreatedByName((String)variables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		vo.setCreateTime(DateTimeUtility.formatHHMM(hpi.getStartTime()));
		
		vo.setLeaveTypeCode((String)variables.get(DeliveryStaffLeaveConstants.KEY_LEAVE_TYPE_CODE));
		
		Boolean pass = (Boolean)variables.get(ProcessConstants.KEY_PASS);
		if (pass == null) {
			vo.setStatusName(ProcessStatus.APPROVING.getName());
		} else if (pass) {
			vo.setStatusName(ProcessStatus.PASS.getName());
		} else {
			vo.setStatusName(ProcessStatus.NOT_PASS.getName());
		}
		
		return vo;
	}
}
