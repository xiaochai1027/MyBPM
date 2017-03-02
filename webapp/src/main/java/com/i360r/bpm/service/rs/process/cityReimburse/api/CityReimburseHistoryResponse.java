package com.i360r.bpm.service.rs.process.cityReimburse.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class CityReimburseHistoryResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;
	
	private List<CityReimburseHistoryVO> histories;

	public List<CityReimburseHistoryVO> getHistories() {
		return histories;
	}

	public void setHistories(List<CityReimburseHistoryVO> histories) {
		this.histories = histories;
	}

	
	
}
