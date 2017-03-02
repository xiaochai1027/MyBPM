package com.i360r.bpm.service.rs.task.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class SearchMyHistoryTaskListResponse extends PagingResponse {

	private static final long serialVersionUID = 1L;
	
	private List<ProcessVO> processes;

	public List<ProcessVO> getProcesses() {
		return processes;
	}

	public void setProcesses(List<ProcessVO> processes) {
		this.processes = processes;
	}

}
