package com.i360r.bpm.service.rs.process.spendBeforeReimburse.api;

import java.io.Serializable;
import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class SpendBeforeReimburseHistoryItemResponse extends PagingResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<SpendBeforeReimburseHistoryItemVO> histories;

	public List<SpendBeforeReimburseHistoryItemVO> getHistories() {
		return histories;
	}

	public void setHistories(List<SpendBeforeReimburseHistoryItemVO> histories) {
		this.histories = histories;
	}

	
	
	
}
