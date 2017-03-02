package com.i360r.bpm.business.handler.housing.checkout;

import javax.annotation.Resource;

import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.housing.checkout.api.IHousingCheckoutHandler;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.house.CheckoutHousingRequest;
import com.i360r.oas.api.internal.rs.house.IHousingInternalService;

public class HousingCheckoutHandler  extends AbstractBusinessHandler implements IHousingCheckoutHandler {

	private static final Log LOG = Log.getLog(HousingCheckoutHandler.class);
	
	@Resource
	private IHousingInternalService housingInternalServiceClient;
	
	@Override
	public void checkoutHousing(
			CheckoutHousingRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = housingInternalServiceClient.checkoutHousing(request);
		} catch (Exception e) {
			LOG.error("checkout housing failed!", e);
			throw new Exception("checkout housing failed!", e);
		}
		checkResponseSuccess(response);
	}
	
}
