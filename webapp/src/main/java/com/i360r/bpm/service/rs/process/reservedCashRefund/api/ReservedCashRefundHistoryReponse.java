package com.i360r.bpm.service.rs.process.reservedCashRefund.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class ReservedCashRefundHistoryReponse extends PagingResponse{
	private static final long serialVersionUID = 1L;
	
	private List<ReservedCashRefundHistoryVO> histories;

	public List<ReservedCashRefundHistoryVO> getHistories() {
		return histories;
	}

	public void setHistories(List<ReservedCashRefundHistoryVO> histories) {
		this.histories = histories;
	}

	
}
