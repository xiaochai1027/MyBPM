package com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class FixedAssetCityDistributeUncheckedNumbers extends ApproveRequest {
	
	@ProcessVariable
	private List<FixedAssetDistributeUncheckedNumberDetail> fixedAssetDistributeNumberDetails;

	public List<FixedAssetDistributeUncheckedNumberDetail> getFixedAssetDistributeNumberDetails() {
		return fixedAssetDistributeNumberDetails;
	}

	public void setFixedAssetDistributeNumberDetails(
			List<FixedAssetDistributeUncheckedNumberDetail> fixedAssetDistributeNumberDetails) {
		this.fixedAssetDistributeNumberDetails = fixedAssetDistributeNumberDetails;
	}

	
	

}
