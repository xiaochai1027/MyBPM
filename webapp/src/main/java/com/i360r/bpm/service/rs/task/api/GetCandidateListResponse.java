package com.i360r.bpm.service.rs.task.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class GetCandidateListResponse extends PagingResponse {

	private static final long serialVersionUID = 1L;
	
	private List<TaskVO> tasks;

	public List<TaskVO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskVO> tasks) {
		this.tasks = tasks;
	}
}
