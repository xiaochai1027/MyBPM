package com.i360r.bpm.service.rs.task.api;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.framework.util.DateTimeUtility;

public class TaskVO {

	private String taskId;
	private String taskDefinitionKey;
	private String taskDefinitionName;
	private String processInstanceId;
	private String processDefinitionKey;
	private String processDefinitionName;
	private String createdByName;
	private String createTime;
	private String keyword;
	private String taskStatus;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public String getTaskDefinitionName() {
		return taskDefinitionName;
	}
	public void setTaskDefinitionName(String taskDefinitionName) {
		this.taskDefinitionName = taskDefinitionName;
	}
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public static TaskVO toVO(Task task, ProcessInstance pi, Map<String, Object> variables) {
		TaskVO vo = new TaskVO();
		
		vo.setTaskId(task.getId());
		vo.setTaskDefinitionKey(task.getTaskDefinitionKey());
		vo.setTaskDefinitionName(task.getName());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS((Date)variables.get(ProcessConstants.KEY_CREATE_TIME)));
		
		vo.setProcessInstanceId(task.getProcessInstanceId());
		vo.setProcessDefinitionKey(pi.getProcessDefinitionKey());
		vo.setProcessDefinitionName(pi.getProcessDefinitionName());
		
		vo.setCreatedByName((String)variables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		vo.setKeyword((String)variables.get(ProcessConstants.KEY_KEYWORD));
		
		Boolean pass = (Boolean)variables.get(ProcessConstants.KEY_PASS);
		if (pass == null) {
			vo.setTaskStatus(ProcessStatus.APPROVING.getName());
		} else if (pass) {
			vo.setTaskStatus(ProcessStatus.PASS.getName());
		} else {
			vo.setTaskStatus(ProcessStatus.NOT_PASS.getName());
		}
		
		return vo;
	}
	
	

}
