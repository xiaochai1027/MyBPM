package com.i360r.bpm.service.rs.process.loanApply.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class LoanApplyHistoryResponse extends PagingResponse{
	private static final long serialVersionUID = 1L;
	private List<LoanApplyHistoryVO> histories;
	public List<LoanApplyHistoryVO> getHistories() {
		return histories;
	}
	public void setHistories(List<LoanApplyHistoryVO> histories) {
		this.histories = histories;
	}

	
	
}
