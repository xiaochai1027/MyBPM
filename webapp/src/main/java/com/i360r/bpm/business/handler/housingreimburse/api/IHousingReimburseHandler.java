package com.i360r.bpm.business.handler.housingreimburse.api;

import com.i360r.oas.api.internal.rs.house.CreateHousingFeeReimburseRequest;
import com.i360r.oas.api.internal.rs.house.CreateHousingRentReimburseRequest;

public interface IHousingReimburseHandler {

	public void createHousingRentReimburse(CreateHousingRentReimburseRequest request) throws Exception;
	
	public void createHousingFeeReimburse(CreateHousingFeeReimburseRequest request) throws Exception;
	
}
