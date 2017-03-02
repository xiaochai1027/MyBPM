package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class SpendAfterReimburseHistoryResponse extends PagingResponse{
	private static final long serialVersionUID = 1L;
	
	private List<SpendAfterReimburseHistoryVO> histories;

	public List<SpendAfterReimburseHistoryVO> getHistories() {
		return histories;
	}

	public void setHistories(List<SpendAfterReimburseHistoryVO> histories) {
		this.histories = histories;
	}

	
	
	
}
