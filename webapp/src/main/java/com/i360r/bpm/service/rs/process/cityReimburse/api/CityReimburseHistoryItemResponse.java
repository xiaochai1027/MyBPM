package com.i360r.bpm.service.rs.process.cityReimburse.api;

import java.io.Serializable;
import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class CityReimburseHistoryItemResponse extends PagingResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<CityReimburseHistoryItemVO> histories;

	public List<CityReimburseHistoryItemVO> getHistories() {
		return histories;
	}

	public void setHistories(List<CityReimburseHistoryItemVO> histories) {
		this.histories = histories;
	}

	
	
	
}
