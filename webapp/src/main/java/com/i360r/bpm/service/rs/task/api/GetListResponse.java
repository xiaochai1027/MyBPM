package com.i360r.bpm.service.rs.task.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class GetListResponse extends PagingResponse{

	private static final long serialVersionUID = 1L;
	
	private List<GetTaskListVO> tasks;

	public List<GetTaskListVO> getTasks() {
		return tasks;
	}

	public void setTasks(List<GetTaskListVO> tasks) {
		this.tasks = tasks;
	}
}
