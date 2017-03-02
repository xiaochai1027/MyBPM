package com.i360r.bpm.service.rs.task.api;

import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.framework.util.DateTimeUtility;

public class ProcessVO {

	private String processInstanceId;
	private String processDefinitionKey;
	private String processDefinitionName;
	private String createdByName;
	private String createTime;
	private String endTime;
	private String currentAssignee;
	private String currentTaskName;
	private String keyword;
	private String statusName;
	
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCurrentTaskName() {
		return currentTaskName;
	}
	public void setCurrentTaskName(String currentTaskName) {
		this.currentTaskName = currentTaskName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCurrentAssignee() {
		return currentAssignee;
	}
	public void setCurrentAssignee(String currentAssignee) {
		this.currentAssignee = currentAssignee;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public static ProcessVO toVO(HistoricProcessInstance hpi, ProcessDefinition pd, Map<String, Object> variables, String currentAssignee, String currentTaskName) {
		ProcessVO vo = new ProcessVO();
		
		vo.setProcessInstanceId(hpi.getId());
		vo.setProcessDefinitionKey(pd.getKey());
		vo.setProcessDefinitionName(pd.getName());
		
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getStartTime()));
		vo.setEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getEndTime()));
		vo.setCreatedByName((String)variables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		vo.setKeyword((String)variables.get(ProcessConstants.KEY_KEYWORD));
		
		vo.setCurrentAssignee(currentAssignee);
		vo.setCurrentTaskName(currentTaskName);
		
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
