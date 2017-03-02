package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class ReimburseRentHistoriesResponse extends PagingResponse {

	private static final long serialVersionUID = -9068916223846274733L;

	private List<ReimburseRentHistoryVO> histories;

	public List<ReimburseRentHistoryVO> getHistories() {
		return histories;
	}

	public void setHistories(List<ReimburseRentHistoryVO> histories) {
		this.histories = histories;
	}

	
}
