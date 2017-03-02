package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.io.Serializable;
import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class SpendAfterReimburseHistoryItemResponse extends PagingResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<SpendAfterReimburseHistoryItemVO> histories;

	public List<SpendAfterReimburseHistoryItemVO> getHistories() {
		return histories;
	}

	public void setHistories(List<SpendAfterReimburseHistoryItemVO> histories) {
		this.histories = histories;
	}

	
	
}
