package com.i360r.bpm.service.rs.task.api;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.framework.util.DateTimeUtility;

public class GetTaskListVO {

	private String taskId;
	private String taskDefinitionKey;
	private String processInstanceId;
	private String processDefinitionName;
	private String processDefinitionKey;
	private String createTime;
	private String lastTaskDefinitionName;
	private String lastTaskApproveEndTime;
	private String keyword;
	
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
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastTaskDefinitionName() {
		return lastTaskDefinitionName;
	}
	public void setLastTaskDefinitionName(String lastTaskDefinitionName) {
		this.lastTaskDefinitionName = lastTaskDefinitionName;
	}
	public String getLastTaskApproveEndTime() {
		return lastTaskApproveEndTime;
	}
	public void setLastTaskApproveEndTime(String lastTaskApproveEndTime) {
		this.lastTaskApproveEndTime = lastTaskApproveEndTime;
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public static GetTaskListVO toVO(Task task, ProcessInstance pi, Map<String, Object> variables, HistoricActivityInstance hai) {
		GetTaskListVO vo = new GetTaskListVO();
		
		vo.setTaskId(task.getId());
		vo.setTaskDefinitionKey(task.getTaskDefinitionKey());
		vo.setProcessInstanceId(task.getProcessInstanceId());
		vo.setProcessDefinitionName(pi.getProcessDefinitionName());
		vo.setProcessDefinitionKey(pi.getProcessDefinitionKey());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDD((Date)variables.get(ProcessConstants.KEY_CREATE_TIME)));
		vo.setKeyword((String)variables.get(ProcessConstants.KEY_KEYWORD));
		vo.setLastTaskApproveEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS((Date)hai.getEndTime()));
		vo.setLastTaskDefinitionName(hai.getActivityName());
		return vo;
	}
	
	public static GetTaskListVO toVO(HistoricProcessInstance hpi, ProcessDefinition pd, Map<String, Object> variables,  HistoricActivityInstance hai) {
		GetTaskListVO vo = new GetTaskListVO();
		
		vo.setTaskDefinitionKey(pd.getKey());
		vo.setProcessInstanceId(hpi.getId());
		vo.setProcessDefinitionName(pd.getName());
		vo.setProcessDefinitionKey(pd.getKey());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDD(hpi.getStartTime()));
		vo.setLastTaskApproveEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS((Date)hai.getEndTime()));
		vo.setLastTaskDefinitionName(hai.getActivityName());
		if (variables != null && variables.get(ProcessConstants.KEY_KEYWORD) != null) {
			vo.setKeyword((String)variables.get(ProcessConstants.KEY_KEYWORD));
		}
		return vo;
	}
	
}
