package com.i360r.bpm.service.rs.task.api;

import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.framework.util.DateTimeUtility;

public class SearchProcessVO extends ProcessVO {
	
	private String taskId;
	
	private String assigneeCode;
	private String createdByCode;
	
	public String getAssigneeCode() {
		return assigneeCode;
	}

	public void setAssigneeCode(String assigneeCode) {
		this.assigneeCode = assigneeCode;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public static SearchProcessVO toVO(HistoricProcessInstance hpi, ProcessDefinition pd, Map<String, Object> variables, String taskId, String  assigneeCode, String currentAssignee, String currentTaskName) {
		SearchProcessVO vo = new SearchProcessVO();
		
		vo.setProcessInstanceId(hpi.getId());
		vo.setProcessDefinitionKey(pd.getKey());
		vo.setProcessDefinitionName(pd.getName());
		vo.setTaskId(taskId);
		vo.setAssigneeCode(assigneeCode);
		
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getStartTime()));
		vo.setEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getEndTime()));
		
		if (variables != null && variables.size() > 0) {
			
			vo.setCreatedByName((String)variables.get(ProcessConstants.KEY_CREATED_BY_NAME));
			if (variables.get(ProcessConstants.KEY_CREATED_BY_CODE) != null) {
				vo.setCreatedByCode((String)variables.get(ProcessConstants.KEY_CREATED_BY_CODE));
			}

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
		}

		return vo;
	}
	
	public static SearchProcessVO toVO(HistoricProcessInstance hpi, ProcessDefinition pd, Map<String, Object> variables, String currentAssigneeName,Task task) {
		SearchProcessVO vo = new SearchProcessVO();
		
		vo.setProcessInstanceId(hpi.getId());
		vo.setProcessDefinitionKey(pd.getKey());
		vo.setProcessDefinitionName(pd.getName());
		vo.setTaskId(task.getId());
		vo.setAssigneeCode(task.getAssignee());
		
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getStartTime()));
		vo.setEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getEndTime()));
		vo.setCreatedByName((String)variables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		vo.setCreatedByCode((String)variables.get(ProcessConstants.KEY_CREATED_BY_CODE));
		vo.setKeyword((String)variables.get(ProcessConstants.KEY_KEYWORD));
		
		vo.setCurrentAssignee(currentAssigneeName);
		vo.setCurrentTaskName(task.getName());
		
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

	public String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}

}
