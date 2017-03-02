package com.i360r.bpm.business.handler.housingreimburse;

import javax.annotation.Resource;

import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.housingreimburse.api.IHousingReimburseHandler;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.oas.api.internal.rs.house.CreateHousingFeeReimburseRequest;
import com.i360r.oas.api.internal.rs.house.CreateHousingRentReimburseRequest;
import com.i360r.oas.api.internal.rs.house.IHousingInternalService;

public class HousingReimburseHandler extends AbstractBusinessHandler implements IHousingReimburseHandler {

	@Resource
	private IHousingInternalService housingInternalServiceClient;
	
	@Override
	public void createHousingRentReimburse(
			CreateHousingRentReimburseRequest request) throws Exception {
		
		ServiceResponse response = housingInternalServiceClient.createHousingRentReimburse(request);
		
		checkResponseSuccess(response);
	}

	@Override
	public void createHousingFeeReimburse(
			CreateHousingFeeReimburseRequest request) throws Exception {
		
		ServiceResponse response = housingInternalServiceClient.createHousingFeeReimburse(request);
		
		checkResponseSuccess(response);
	}

}
