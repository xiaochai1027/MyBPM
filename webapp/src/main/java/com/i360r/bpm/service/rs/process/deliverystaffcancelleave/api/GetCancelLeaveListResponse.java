package com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class GetCancelLeaveListResponse extends PagingResponse {

	private static final long serialVersionUID = 1L;

	private List<DeliveryStaffCancelLeaveVO> processList;

	public List<DeliveryStaffCancelLeaveVO> getProcessList() {
		return processList;
	}

	public void setProcessList(List<DeliveryStaffCancelLeaveVO> processList) {
		this.processList = processList;
	}
}
