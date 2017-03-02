package com.i360r.bpm.business.handler.vendor.bill;

import com.i360r.oas.api.internal.rs.vendor.bill.ModifyVendorBillRequest;
import com.i360r.oas.api.internal.rs.vendor.bill.VendorBillInfoResponse;

public interface IVendorBillHandler {
	
	public VendorBillInfoResponse getVendorBillInfo(Integer billId) throws Exception;
	
	public void modifyVendorBill(ModifyVendorBillRequest request) throws Exception;
}
