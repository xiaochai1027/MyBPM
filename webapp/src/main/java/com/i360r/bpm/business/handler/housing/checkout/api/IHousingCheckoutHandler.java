package com.i360r.bpm.business.handler.housing.checkout.api;

import com.i360r.oas.api.internal.rs.house.CheckoutHousingRequest;

public interface IHousingCheckoutHandler {
	
	public void checkoutHousing(CheckoutHousingRequest request) throws Exception;
}
