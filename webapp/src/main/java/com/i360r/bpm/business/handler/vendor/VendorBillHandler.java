package com.i360r.bpm.business.handler.vendor;

import javax.annotation.Resource;

import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.vendor.bill.IVendorBillHandler;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.oas.api.internal.rs.vendor.bill.IVendorBillInternalService;
import com.i360r.oas.api.internal.rs.vendor.bill.ModifyVendorBillRequest;
import com.i360r.oas.api.internal.rs.vendor.bill.VendorBillInfoResponse;

public class VendorBillHandler extends AbstractBusinessHandler implements IVendorBillHandler {
    
	@Resource
	private IVendorBillInternalService vendorBillInternalServiceClient;
	
	@Override
	public VendorBillInfoResponse getVendorBillInfo(Integer billId) throws Exception {
		VendorBillInfoResponse response = vendorBillInternalServiceClient.getVendorBillInfo(billId);
		checkResponseSuccess(response);
		return response;
	}

	@Override
	public void modifyVendorBill(ModifyVendorBillRequest request) throws Exception {
		ServiceResponse response = vendorBillInternalServiceClient.modifyVendorBill(request);
		checkResponseSuccess(response);
	}

}