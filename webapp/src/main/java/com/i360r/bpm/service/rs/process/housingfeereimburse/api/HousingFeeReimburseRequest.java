package com.i360r.bpm.service.rs.process.housingfeereimburse.api;

import java.math.BigDecimal;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class HousingFeeReimburseRequest extends ApproveRequest {
	
	private List<HousingFeeReimburseItemVO> rejectItems;

	//当没有驳回项时,默认为空,当有驳回项时,修改为“有驳回项目”
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	private String rejectItemsRemind;
	
	@ProcessVariable(isKeyword=true, keywordOrder=4)
	private BigDecimal totalAmount;
	
	public List<HousingFeeReimburseItemVO> getRejectItems() {
		return rejectItems;
	}

	public void setRejectItems(List<HousingFeeReimburseItemVO> rejectItems) {
		this.rejectItems = rejectItems;
	}

	public String getRejectItemsRemind() {
		return rejectItemsRemind;
	}

	public void setRejectItemsRemind(String rejectItemsRemind) {
		this.rejectItemsRemind = rejectItemsRemind;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
