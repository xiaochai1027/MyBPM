package com.i360r.bpm.service.rs.process.spendBeforeReimburse.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class SpendBeforeReimburseHistoryResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;
	
	private List<SpendBeforeReimburseHistoryVO> histories;

	public List<SpendBeforeReimburseHistoryVO> getHistories() {
		return histories;
	}

	public void setHistories(List<SpendBeforeReimburseHistoryVO> histories) {
		this.histories = histories;
	}

	
	
}
