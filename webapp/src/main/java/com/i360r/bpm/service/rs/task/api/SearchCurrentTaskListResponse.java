package com.i360r.bpm.service.rs.task.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class SearchCurrentTaskListResponse extends PagingResponse {

	private static final long serialVersionUID = 1L;
	
	private List<SearchProcessVO> processes;

	public List<SearchProcessVO> getProcesses() {
		return processes;
	}

	public void setProcesses(List<SearchProcessVO> processes) {
		this.processes = processes;
	}

}
