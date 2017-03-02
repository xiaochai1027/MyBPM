package com.i360r.bpm.service.rs.process.reservedCashApply.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class ReservedCashApplyHistoryResponse extends PagingResponse{
	private static final long serialVersionUID = 1L;
	
	private List<ReservedCashApplyHistoryVO> histories;

	public List<ReservedCashApplyHistoryVO> getHistories() {
		return histories;
	}

	public void setHistories(List<ReservedCashApplyHistoryVO> histories) {
		this.histories = histories;
	}

	
	
}
