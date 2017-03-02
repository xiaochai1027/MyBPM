package com.i360r.bpm.business.handler.housing.contract.api;

import com.i360r.oas.api.internal.rs.house.CreateHousingContractRequest;
import com.i360r.oas.api.internal.rs.house.ReletHousingContractRequest;

public interface IHousingContractHandler {
	public void createHousingContract(CreateHousingContractRequest housingContractRequest) throws Exception;

	public void reletHousingContract(ReletHousingContractRequest reletHousingContractRequest) throws Exception;
	
	public Integer createHousingRentReimburse(String executionId) throws Exception;
	
}
