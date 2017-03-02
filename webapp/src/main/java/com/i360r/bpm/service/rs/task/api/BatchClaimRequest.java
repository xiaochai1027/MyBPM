package com.i360r.bpm.service.rs.task.api;

import java.util.List;

public class BatchClaimRequest {

	private List<String> taskIds;

	public List<String> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(List<String> taskIds) {
		this.taskIds = taskIds;
	}

}
