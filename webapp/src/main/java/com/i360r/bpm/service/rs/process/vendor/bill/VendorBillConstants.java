package com.i360r.bpm.service.rs.process.vendor.bill;

public class VendorBillConstants {
	public static final String PROCESS_DEFINITION_KEY = "vendorBillApply";

	public static final String TASK_CREATE = "create";
	public static final String TASK_DETAILS = "detail";
	

	// 账单负责人
	public static final String TASK_BUSINESS_MANANGER_APPROVE = "businessManagerApprove";
	public static final String TASK_BUSINESS_MANANGER_APPROVED = "businessManagerApproved";

	// 出纳
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMD = "cashierConfirmed";
	
	//重新指派负责人
	public static final String TASK_REASSIGN_BUSINESS_MANANGER_APPROVE = "reassignbusinessManagerApprove";
	public static final String TASK_REASSIGN_BUSINESS_MANANGER_APPROVED = "reassignbusinessManagerApproved";
}
