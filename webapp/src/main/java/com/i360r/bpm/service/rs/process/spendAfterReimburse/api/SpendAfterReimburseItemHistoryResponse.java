package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.io.Serializable;
import java.util.List;

import com.i360r.framework.common.service.rs.api.PagingResponse;

public class SpendAfterReimburseItemHistoryResponse extends PagingResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<SpendAfterReimburseHistoryVO> itemVos;

	public List<SpendAfterReimburseHistoryVO> getItemVos() {
		return itemVos;
	}

	public void setItemVos(List<SpendAfterReimburseHistoryVO> itemVos) {
		this.itemVos = itemVos;
	}
	
	
}
