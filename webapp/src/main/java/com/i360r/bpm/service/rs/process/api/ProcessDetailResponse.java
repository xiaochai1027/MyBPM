package com.i360r.bpm.service.rs.process.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.ServiceResponse;

public class ProcessDetailResponse<T> extends ServiceResponse {

	private static final long serialVersionUID = 1L;

	private String processInstanceId;
	
	private List<TaskResultVO> taskList;
	
	private T detail;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public List<TaskResultVO> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskResultVO> taskList) {
		this.taskList = taskList;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}

}
