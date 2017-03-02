package com.i360r.bpm.service.rs.process.fixedasset.citydistribute.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class FixedAssetCityDistributeNumbers extends ApproveRequest {
	
	@ProcessVariable
	private List<FixedAssetDistributeNumberDetail> fixedAssetDistributeNumberDetails;

	public List<FixedAssetDistributeNumberDetail> getFixedAssetDistributeNumberDetails() {
		return fixedAssetDistributeNumberDetails;
	}

	public void setFixedAssetDistributeNumberDetails(
			List<FixedAssetDistributeNumberDetail> fixedAssetDistributeNumberDetails) {
		this.fixedAssetDistributeNumberDetails = fixedAssetDistributeNumberDetails;
	}

	
	

}
