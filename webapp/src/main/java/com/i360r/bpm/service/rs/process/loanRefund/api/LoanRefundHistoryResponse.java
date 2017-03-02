package com.i360r.bpm.service.rs.process.loanRefund.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class LoanRefundHistoryResponse extends PagingResponse{
	private static final long serialVersionUID = 1L;
	
	private List<LoanRefundHistoryVO> histories;

	public List<LoanRefundHistoryVO> getHistories() {
		return histories;
	}

	public void setHistories(List<LoanRefundHistoryVO> histories) {
		this.histories = histories;
	}

	
	
}
