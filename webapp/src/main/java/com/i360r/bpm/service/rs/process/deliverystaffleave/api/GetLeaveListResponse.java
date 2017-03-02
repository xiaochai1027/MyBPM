package com.i360r.bpm.service.rs.process.deliverystaffleave.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class GetLeaveListResponse extends PagingResponse {

	private static final long serialVersionUID = 1L;

	private List<DeliveryStaffLeaveVO> processList;

	public List<DeliveryStaffLeaveVO> getProcessList() {
		return processList;
	}

	public void setProcessList(List<DeliveryStaffLeaveVO> processList) {
		this.processList = processList;
	}
}
